package com.nwuer.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.ChoiceQuestion;
import com.nwuer.entity.ChoiceQuestionTest;
import com.nwuer.entity.JudgeQuestion;
import com.nwuer.entity.JudgeQuestionTest;
import com.nwuer.entity.ObjectiveAnswer;
import com.nwuer.entity.Paper;
import com.nwuer.entity.PaperRule;
import com.nwuer.entity.StudentRegister;
import com.nwuer.entity.SubjectiveAnswer;
import com.nwuer.entity.SubjectiveQuestion;
import com.nwuer.entity.SubjectiveQuestionTest;
import com.nwuer.service.ChoiceQuestionService;
import com.nwuer.service.ChoiceQuestionTestService;
import com.nwuer.service.GuardianShipService;
import com.nwuer.service.JudgeQuestionService;
import com.nwuer.service.JudgeQuestionTestService;
import com.nwuer.service.ObjectiveAnswerService;
import com.nwuer.service.PaperRuleService;
import com.nwuer.service.PaperService;
import com.nwuer.service.StudentRegisterService;
import com.nwuer.service.SubjectiveAnswerService;
import com.nwuer.service.SubjectiveQuestionService;
import com.nwuer.service.SubjectiveQuestionTestService;
import com.nwuer.utils.FreemarkerUtil;
import com.nwuer.utils.ValidateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class PaperAction extends ActionSupport implements ModelDriven<Paper> {
	private Paper paper = new Paper();
	@Override
	public Paper getModel() {
		return paper;
	} //模型驱动获取数据
	
	private Map result = new HashMap();
	public Map getResult() {
		return result;
	}
	public void setResult(Map result) {
		this.result = result;
	} //返回提示信息
	
	String info;
	HttpServletRequest req = ServletActionContext.getRequest();
	
	@Autowired
	private PaperService paperService;
	@Autowired
	private PaperRuleService paperRuleService;
	@Autowired
	private ChoiceQuestionService choiceQuestionService;
	@Autowired
	private JudgeQuestionService judgeQuestionService;
	@Autowired
	private SubjectiveQuestionService subjectiveQuestionService;
	@Autowired
	private ChoiceQuestionTestService choiceQuestionTestService;
	@Autowired
	private JudgeQuestionTestService judgeQuestionTestService;
	@Autowired
	private SubjectiveQuestionTestService subjectiveQuestionTestService;
	@Autowired
	private StudentRegisterService studentRegisterService;
	@Autowired
	private ObjectiveAnswerService objectiveAnswerService;
	@Autowired
	private SubjectiveAnswerService subjectiveAnswerService;
	@Autowired
	private GuardianShipService guardianShipService;
	@Autowired
	private ValidateUtil validateUtil;
	@Autowired
	private FreemarkerUtil freemarkerUtil;
	
	/**
	 * 展示试卷
	 * @return
	 */
	public String list() {
		List<Paper> list = this.paperService.getAll();
		PaperRule rule = null;
		Paper p = null;
		StudentRegister sr = null;
		for(int i=0; i<list.size(); i++) {
			p = list.get(i);
			rule = this.paperRuleService.getByIdEager(p.getP_id());
			if(p.getSr_id() == null && p.getPap_kind() == 0) {
				sr = new StudentRegister();
				sr.setSr_name("练习试卷");
			}else if(p.getSr_id() == null && p.getPap_kind() == 1) {
				sr = new StudentRegister();
				sr.setSr_name("备份试卷");
			}else
				sr = this.studentRegisterService.getByIdEager(p.getSr_id());
			p.setPaperRule(rule);
			p.setStudentRegister(sr);
		}
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	/**
	 * 展示生成试卷页面
	 * @return
	 */
	public String showAdd() {
		List<PaperRule> list = paperRuleService.getAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "showAdd";
	}
	
	/**
	 * 删除试卷,同时删除本地存的试卷
	 * @return
	 */
	public String delete() {
		Paper p = this.paperService.getByIdEager(this.paper.getPap_id());
		if(p == null) {
			req.setAttribute("info", "删除失败");
			return ERROR;
		}
		 //0:刚生成, 1:已绑定 2:考试中, 3 已交卷, 4 已阅卷, 5,已废弃
		if(p.getStatus()==0 || p.getStatus()==4||p.getStatus()==5) {
			//得到试卷路径
			String url = p.getPap_url();
			//删除
			this.paperService.delete(p);
			
			//将答案清除
			ObjectiveAnswer oAnswer = this.objectiveAnswerService.getByUuid(paper.getPap_id());
			this.objectiveAnswerService.delete(oAnswer.getAnswer_id());
			List<SubjectiveAnswer> sList = this.subjectiveAnswerService.getByUuid(paper.getPap_id());
			for(int i=0; i<sList.size(); i++) {
				this.subjectiveAnswerService.delete(sList.get(i).getAnswer_id());
			}
			
			//  student/more/paper/4028b88162a5164a0162a51752ae0006.html
			String tmp = this.getClass().getResource("/").getPath();
			int end = tmp.indexOf("WEB-INF");
			String path = tmp.substring(0,end) + url;
			File f = new File(path);
			if(f.delete()) {
				return SUCCESS;
			}else {
				req.setAttribute("info", "删除失败");
				return ERROR;
			}
		}else {
			req.setAttribute("info", "此试卷的状态不可删除");
			return ERROR;
		}
	}
	
	/**
	 * 更改试卷
	 * @return
	 */
	public String change() {
		//检测数据
		String s_name = req.getParameter("s_name");
		String s_number = req.getParameter("s_number");
		if(s_name==null || s_name.equals("") || this.validateUtil.validateNumber(s_number, 10)!=null) {
			req.setAttribute("info", "学号或姓名错误");
			return ERROR;
		}
		List<StudentRegister> list = this.studentRegisterService.getStudentRegisterByNumberAndStatus(s_number,new Byte(3+""));  //3:考试中
		if(list==null || list.size()==0) {
			req.setAttribute("info", "该生未在注册学生里,考试期间才能更换试卷,请重新导入注册学生后重试");
			return ERROR;
		}
		//挑选出这个注册信息
		StudentRegister sr = null;
		for(int i=0; i<list.size(); i++) {
			sr = list.get(i);
			if(sr.getMajor().getM_id()== this.paper.getMajor().getM_id() && sr.getSubject().getSub_id()==this.paper.getSubject().getSub_id()) {
				break;
			}else
				sr=null;
		}
		if(sr == null) {
			req.setAttribute("info", "注册科目出错或系统错误,请确认后重试");
			return ERROR;
		}
		
		Paper p = this.paperService.getNonePaperByMajorAndSubject(this.paper.getMajor().getM_id(), this.paper.getSubject().getSub_id());
		if(p == null) {
			req.setAttribute("info", "试卷不够,请生成试卷后重新尝试!");
			return ERROR;
		}
		//挑选出注册信息,将原来的试卷作废
		Paper tmp = this.paperService.getById(sr.getPaper());
		tmp.setStatus(new Byte(5+""));
		this.paperService.update(tmp);
		
		p.setStatus(new Byte(1+""));
		p.setSr_id(sr.getSr_id());
		this.paperService.update(p);
		sr.setPaper(p.getPap_id());
		this.studentRegisterService.update(sr);
		
		
		return SUCCESS;
	}
	
	/**
	 * 得到考试结束时间
	 */
	public String getEndTime() {
		String p_id = ServletActionContext.getRequest().getParameter("p_id");
		info = this.validateUtil.isNumber(p_id);
		if(info!=null) {
			this.result.put("endTime", System.currentTimeMillis()+7200000);
			return "end";
		}
		long time = this.paperRuleService.getByIdEager(Integer.parseInt(p_id)).getEnd_time();
		this.result.put("endTime", time);
		return "end";
	}
	
	
	
	/**
	 * 生成试卷
	 */
	PaperRule rule = null;
	Map data = new HashMap();
	StringBuilder k_answer = null;
	ObjectiveAnswer objectiveAnswer = null;
	List<SubjectiveAnswer> z_answer = null;
	public String add() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String paperNum = req.getParameter("paperNum");
		info = validateUtil.isNumber(paperNum);
		if(info != null) {
			req.setAttribute("info","试卷数目"+ info);
			return ERROR;
		}
		//开始生成试卷
		int num = Integer.parseInt(paperNum);
		boolean flag = false;
		if(this.paper.getPap_kind() == 0) { //练习试卷
			generatePracticePaper(num);
			//处理答案
		}else {
			generateExamPaper(num);
			//处理答案
			
		}
		
		
		return SUCCESS;
	}
	
	/**
	 * 生成练习试卷
	 * @param num 练习试卷数目
	 */
	public void generatePracticePaper(int num) {
		rule = paperRuleService.getByIdEager(paper.getP_id());
		Paper p = null;
		String sub_name = rule.getSubject().getSub_name();
		for(int i=0; i<num; i++) {
			String uuid = null;
			objectiveAnswer = new ObjectiveAnswer();
			z_answer = new ArrayList<SubjectiveAnswer>();
			k_answer = new StringBuilder();
			//先插入试卷,获取uuid,然后将uuid作为试卷名
			p = new Paper();
			p.setP_id(this.paper.getP_id());
			int g_id = this.guardianShipService.getByMajorAndSubject(rule.getMajor().getM_id(), rule.getSubject().getSub_id());
			p.setG_id(g_id);
			p.getSubject().setSub_id(rule.getSubject().getSub_id());
			p.getMajor().setM_id(rule.getMajor().getM_id());
			p.setPap_kind(this.paper.getPap_kind());
			uuid = this.paperService.add(p);
			p.setPap_id(uuid);
			p.setCreate_time(System.currentTimeMillis());
//				System.out.println(uuid);
			//根据试卷生成规则挑选数据    在生成试卷时还要生成答案
			//简单:  80% 简单 10%中 10%难
			//中等:  60% 简单 20%中 20%难
			//难   :  40%简单  30%中 30%难
			
			//不用挑学生,生成试卷
			
			//加选择题
			generateChoiceQuestion(this.paper.getPap_kind());
			//加判断题
			generateJudgeQuestion(this.paper.getPap_kind());
			//加名词解释题  0:名词解释,1:填空,2:简答,3:计算,4:综合
			generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getTranslate_num(),new Byte("0"),"tPaperList");
			//加填空题
			generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getBlank_num(),new Byte("1"),"bPaperList");
			//加简答题
			generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getSimple_question_num(),new Byte("2"),"simPaperList");
			//加计算题
			generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getCompute_num(),new Byte("3"),"comPaperList");
			//加综合题
			generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getMix_num(),new Byte("4"),"mPaperList");
			
			//处理答案
			//处理客观题答案
			objectiveAnswer.setAnswer_right(k_answer.toString());
			objectiveAnswer.setUuid(uuid);
			for(int k=0;k<z_answer.size();k++) {
				SubjectiveAnswer s = z_answer.get(k);
				s.setUuid(uuid);
			}
				
			
			data.put("paper", p);
			data.put("rule", rule);
			data.put("subject",sub_name );
			String contextPath = ServletActionContext.getRequest().getContextPath();
			data.put("contextPath", contextPath);
			this.freemarkerUtil.makePracticePaper(data, uuid);
			p.setPap_url( "student/practice/" + uuid + ".html");
			//更新试卷信息
			this.paperService.update(p);
			this.objectiveAnswerService.add(objectiveAnswer);
			if(z_answer != null) {
				for(int k=0;k<z_answer.size();k++) {
					this.subjectiveAnswerService.add(z_answer.get(k));
				}
				//答案添加完毕
			}
			
			//清理数据
			
			 //END//一张卷子添加完毕
		} 
	}
	
	/**
	 * 生成考试试卷
	 * @param num 考试试卷数目
	 */
	public void generateExamPaper(int num) {
		rule = paperRuleService.getByIdEager(paper.getP_id());
		List<StudentRegister> studentRegisterList = this.studentRegisterService.getAllByMajorAndSubjectAndStatus(rule.getMajor().getM_id(),rule.getSubject().getSub_id(),new Byte(1+"")); 
		int s_num = studentRegisterList.size(); //注册学生人数
		int other = num-s_num;  //多余的试卷
		Paper p = null;
		StudentRegister studentRegister = null;
		String sub_name = rule.getSubject().getSub_name();
		for(int i=0; i<num; i++) {
			String uuid = null;
			objectiveAnswer = new ObjectiveAnswer();
			z_answer = new ArrayList<SubjectiveAnswer>();
			k_answer = new StringBuilder();
			if(i<s_num) { //说明是生成正常考试卷子
				//先插入试卷,获取uuid,然后将uuid作为试卷名
				p = new Paper();
				p.setP_id(this.paper.getP_id());
				int g_id = this.guardianShipService.getByMajorAndSubject(rule.getMajor().getM_id(), rule.getSubject().getSub_id());
				p.setG_id(g_id);
				p.setPap_kind(this.paper.getPap_kind());
				p.getSubject().setSub_id(rule.getSubject().getSub_id());
				p.getMajor().setM_id(rule.getMajor().getM_id());
				uuid = this.paperService.add(p);
				p.setPap_id(uuid);
				p.setCreate_time(System.currentTimeMillis());
//				System.out.println(uuid);
				//根据试卷生成规则挑选数据    在生成试卷时还要生成答案
				//简单:  80% 简单 10%中 10%难
				//中等:  60% 简单 20%中 20%难
				//难   :  40%简单  30%中 30%难
				
				//挑一个学生来生成试卷
				studentRegister = studentRegisterList.get(i); 
				p.setSr_id(studentRegister.getSr_id());
				p.setStatus(new Byte(1+""));  //绑定试卷
				
				//加选择题
				generateChoiceQuestion(this.paper.getPap_kind());
				//加判断题
				generateJudgeQuestion(this.paper.getPap_kind());
				//加名词解释题  0:名词解释,1:填空,2:简答,3:计算,4:综合
				generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getTranslate_num(),new Byte("0"),"tPaperList");
				//加填空题
				generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getBlank_num(),new Byte("1"),"bPaperList");
				//加简答题
				generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getSimple_question_num(),new Byte("2"),"simPaperList");
				//加计算题
				generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getCompute_num(),new Byte("3"),"comPaperList");
				//加综合题
				generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getMix_num(),new Byte("4"),"mPaperList");
				
				//处理答案
				//处理客观题答案
				objectiveAnswer.setAnswer_right(k_answer.toString());
				objectiveAnswer.setUuid(uuid);
				objectiveAnswer.setSr_id(studentRegister.getSr_id());
				for(int k=0;k<z_answer.size();k++) {
					SubjectiveAnswer s = z_answer.get(k);
					s.setSr_id(studentRegister.getSr_id());;
					s.setUuid(uuid);
					
				}
				
			}else { //说明是生成备用卷子
				//先插入试卷,获取uuid,然后将uuid作为试卷名
				p = new Paper();
				p.setP_id(this.paper.getP_id());
				int g_id = this.guardianShipService.getByMajorAndSubject(rule.getMajor().getM_id(), rule.getSubject().getSub_id());
				p.setG_id(g_id);
				p.setPap_kind(new Byte(1+""));
				p.getSubject().setSub_id(rule.getSubject().getSub_id());
				p.getMajor().setM_id(rule.getMajor().getM_id());
				uuid = this.paperService.add(p);
				p.setPap_id(uuid);
				p.setCreate_time(System.currentTimeMillis());
//				System.out.println(uuid);
				//根据试卷生成规则挑选数据    在生成试卷时还要生成答案
				//简单:  80% 简单 10%中 10%难
				//中等:  60% 简单 20%中 20%难
				//难   :  40%简单  30%中 30%难
				
				//不用挑学生,生成试卷
				
				//加选择题
				generateChoiceQuestion(this.paper.getPap_kind());
				//加判断题
				generateJudgeQuestion(this.paper.getPap_kind());
				//加名词解释题  0:名词解释,1:填空,2:简答,3:计算,4:综合
				generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getTranslate_num(),new Byte("0"),"tPaperList");
				//加填空题
				generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getBlank_num(),new Byte("1"),"bPaperList");
				//加简答题
				generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getSimple_question_num(),new Byte("2"),"simPaperList");
				//加计算题
				generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getCompute_num(),new Byte("3"),"comPaperList");
				//加综合题
				generateSubjectiveQuestion(this.paper.getPap_kind(),rule.getMix_num(),new Byte("4"),"mPaperList");
				
				//处理答案
				//处理客观题答案
				objectiveAnswer.setAnswer_right(k_answer.toString());
				objectiveAnswer.setUuid(uuid);
				for(int k=0;k<z_answer.size();k++) {
					SubjectiveAnswer s = z_answer.get(k);
					s.setUuid(uuid);
				}
				
			}
			
			data.put("paper", p);
			data.put("rule", rule);
			data.put("subject",sub_name );
			String contextPath = ServletActionContext.getRequest().getContextPath();
			data.put("contextPath", contextPath);
			this.freemarkerUtil.makePaper(data, uuid);
			p.setPap_url("student/more/paper/"+uuid+".html");
			//更新试卷信息,考生注册信息为已生成试卷
			this.paperService.update(p);
			if(studentRegister!=null) {
				studentRegister.setPaper(p.getPap_id());
				studentRegister.setStatus(new Byte(2+""));
				this.studentRegisterService.update(studentRegister);
			}
			
			this.objectiveAnswerService.add(objectiveAnswer);
			if(z_answer != null) {
				for(int k=0;k<z_answer.size();k++) {
					this.subjectiveAnswerService.add(z_answer.get(k));
				}
				//答案添加完毕
			}
			
			
			
			 //END//一张卷子添加完毕
		} 
	}
	
	public void generateChoiceQuestion(int type) {
		//加选择题
		int single_choice_num = rule.getSingle_choice_num();
		if(single_choice_num != 0) {
			//挑选选择题
			//rule.getSingle_choice_score();
			int simpleCount = 0; //简单数
			int middleCount = 0; //中等数
			int highCount = 0;   //难等数
			switch(rule.getDegree()) {
				case 0:
					simpleCount = (int) (single_choice_num * 0.8);
					middleCount = (int) (single_choice_num * 0.1);
					highCount = (int) (single_choice_num * 0.1);
					middleCount += single_choice_num-simpleCount-middleCount-highCount; 
					break;
				case 1:
					simpleCount = (int) (single_choice_num * 0.6);
					middleCount = (int) (single_choice_num * 0.2);
					highCount = (int) (single_choice_num * 0.2);
					middleCount += single_choice_num-simpleCount-middleCount-highCount;
					break;
				case 2:
					simpleCount = (int) (single_choice_num * 0.4);
					middleCount = (int) (single_choice_num * 0.3);
					highCount = (int) (single_choice_num * 0.3);
					middleCount += single_choice_num-simpleCount-middleCount-highCount;
					break;
			}
			
			//分练习和考试题库
			List cPaperList = new ArrayList();  //题目
			StringBuilder cPaperAnswerList = new StringBuilder();  //答案
			
			if(type == 0) {
				List<ChoiceQuestionTest> cList = choiceQuestionTestService.getAll();
				//打乱题目
				Collections.shuffle(cList);
				Random random = new Random();
				ChoiceQuestionTest choiceQuestionTest = null;
				int count = 0;
				for(int j=0; j<cList.size(); j++) {
					//挑选
					choiceQuestionTest = cList.get(j);
					if(simpleCount != 0 && choiceQuestionTest.getDegree() == 0) {
						List choice = new ArrayList();
						//先加3个错误答案  choice.add(choiceQuestion.getCho_question());
						choice.add(choiceQuestionTest.getCho_choice_1());
						choice.add(choiceQuestionTest.getCho_choice_2());
						choice.add(choiceQuestionTest.getCho_choice_3());
						Collections.shuffle(choice);
						//生成随机数字,加正确答案
						int cRight = random.nextInt(4);
						choice.add(cRight,choiceQuestionTest.getCho_answer());
						//再加答案
						choice.add(0,choiceQuestionTest.getCho_question());
						cPaperList.add(choice);
						cPaperAnswerList.append("5_"+count+"_"+cRight+","); //将答案加到答案list中
						count++;
						simpleCount--;
						
					}else if(middleCount != 0 && choiceQuestionTest.getDegree() == 1) {
						List choice = new ArrayList();
						//先加3个错误答案  choice.add(choiceQuestion.getCho_question());
						choice.add(choiceQuestionTest.getCho_choice_1());
						choice.add(choiceQuestionTest.getCho_choice_2());
						choice.add(choiceQuestionTest.getCho_choice_3());
						Collections.shuffle(choice);
						//生成随机数字,加正确答案
						int cRight = random.nextInt(4);
						choice.add(cRight,choiceQuestionTest.getCho_answer());
						//再加答案
						choice.add(0,choiceQuestionTest.getCho_question());
						cPaperList.add(choice);
						cPaperAnswerList.append("5_"+count+"_"+cRight+","); //将答案加到答案list中
						count++;
						middleCount--;
						
					}else if(highCount != 0 && choiceQuestionTest.getDegree() == 2) {
						List choice = new ArrayList();
						//先加3个错误答案  choice.add(choiceQuestion.getCho_question());
						choice.add(choiceQuestionTest.getCho_choice_1());
						choice.add(choiceQuestionTest.getCho_choice_2());
						choice.add(choiceQuestionTest.getCho_choice_3());
						Collections.shuffle(choice);
						//生成随机数字,加正确答案
						int cRight = random.nextInt(4);
						choice.add(cRight,choiceQuestionTest.getCho_answer());
						//再加答案
						choice.add(0,choiceQuestionTest.getCho_question());
						cPaperList.add(choice);
						cPaperAnswerList.append("5_"+count+"_"+cRight+","); //将答案加到答案list中
						count++;
						highCount--;
						
					}else if(simpleCount == 0 && middleCount==0 && highCount==0) {
						break; //否则已经挑选完成
					}
						
					
				}
			}else {
				List<ChoiceQuestion> cList = choiceQuestionService.getAll();
				//打乱题目
				Collections.shuffle(cList);
				Random random = new Random();
				ChoiceQuestion choiceQuestion = null;
				int count = 0;
				for(int j=0; j<cList.size(); j++) {
					//挑选
					choiceQuestion = cList.get(j);
					if(simpleCount != 0 && choiceQuestion.getDegree() == 0) {
						List choice = new ArrayList();
						//先加3个错误答案  choice.add(choiceQuestion.getCho_question());
						choice.add(choiceQuestion.getCho_choice_1());
						choice.add(choiceQuestion.getCho_choice_2());
						choice.add(choiceQuestion.getCho_choice_3());
						Collections.shuffle(choice);
						//生成随机数字,加正确答案
						int cRight = random.nextInt(4);
						choice.add(cRight,choiceQuestion.getCho_answer());
						//再加答案
						choice.add(0,choiceQuestion.getCho_question());
						cPaperList.add(choice);
						cPaperAnswerList.append("5_"+count+"_"+cRight+","); //将答案加到答案list中
						count++;
						simpleCount--;
						
					}else if(middleCount != 0 && choiceQuestion.getDegree() == 1) {
						List choice = new ArrayList();
						//先加3个错误答案  choice.add(choiceQuestion.getCho_question());
						choice.add(choiceQuestion.getCho_choice_1());
						choice.add(choiceQuestion.getCho_choice_2());
						choice.add(choiceQuestion.getCho_choice_3());
						Collections.shuffle(choice);
						//生成随机数字,加正确答案
						int cRight = random.nextInt(4);
						choice.add(cRight,choiceQuestion.getCho_answer());
						//再加答案
						choice.add(0,choiceQuestion.getCho_question());
						cPaperList.add(choice);
						cPaperAnswerList.append("5_"+count+"_"+cRight+","); //将答案加到答案list中
						count++;
						middleCount--;
						
					}else if(highCount != 0 && choiceQuestion.getDegree() == 2) {
						List choice = new ArrayList();
						//先加3个错误答案  choice.add(choiceQuestion.getCho_question());
						choice.add(choiceQuestion.getCho_choice_1());
						choice.add(choiceQuestion.getCho_choice_2());
						choice.add(choiceQuestion.getCho_choice_3());
						Collections.shuffle(choice);
						//生成随机数字,加正确答案
						int cRight = random.nextInt(4);
						choice.add(cRight,choiceQuestion.getCho_answer());
						//再加答案
						choice.add(0,choiceQuestion.getCho_question());
						cPaperList.add(choice);
						cPaperAnswerList.append("5_"+count+"_"+cRight+","); //将答案加到答案list中
						count++;
						highCount--;
						
					}else if(simpleCount == 0 && middleCount==0 && highCount==0) {
						break; //否则已经挑选完成
					}
						
					
				}
			}
			
			
			//将选择题添加进数据集里
			data.put("cPaperList", cPaperList);
			k_answer.append(cPaperAnswerList);
			
			
		}
	}
	
	public void generateJudgeQuestion(int type) {
		//加判断题
		int judge_num = rule.getJudge_num();
		if(judge_num != 0) {
			//挑选判断题
			//rule.getSingle_choice_score();
			int simpleCount = 0; //简单数
			int middleCount = 0; //中等数
			int highCount = 0;   //难等数
			switch(rule.getDegree()) {
				case 0:
					simpleCount = (int) (judge_num * 0.8);
					middleCount = (int) (judge_num * 0.1);
					highCount = (int) (judge_num * 0.1);
					middleCount += judge_num-simpleCount-middleCount-highCount; 
					break;
				case 1:
					simpleCount = (int) (judge_num * 0.6);
					middleCount = (int) (judge_num * 0.2);
					highCount = (int) (judge_num * 0.2);
					middleCount += judge_num-simpleCount-middleCount-highCount;
					break;
				case 2:
					simpleCount = (int) (judge_num * 0.4);
					middleCount = (int) (judge_num * 0.3);
					highCount = (int) (judge_num * 0.3);
					middleCount += judge_num-simpleCount-middleCount-highCount;
					break;
			}
			
			//分练习题库,考试题库
			List jPaperList = new ArrayList();  //题目
			StringBuilder jPaperAnswerList = new StringBuilder();  //答案
			
			if(type == 0) {
				List<JudgeQuestionTest> jList = judgeQuestionTestService.getAll();
				//打乱题目
				Collections.shuffle(jList);
				Random random = new Random();
				JudgeQuestionTest judgeQuestionTest = null;
				int count = 0;
				for(int j=0; j<jList.size(); j++) {
					//挑选
					judgeQuestionTest = jList.get(j);
					if(simpleCount != 0 && judgeQuestionTest.getDegree() == 0) {
						List judge = new ArrayList();
						//先加3个错误答案  choice.add(choiceQuestion.getCho_question());
						judge.add(judgeQuestionTest.getJud_question());
						jPaperList.add(judge);
						jPaperAnswerList.append("6_"+count+"_"+judgeQuestionTest.getJud_answer()+","); //将答案加到答案list中
						count++;
						simpleCount--;
						
					}else if(middleCount != 0 && judgeQuestionTest.getDegree() == 1) {
						List judge = new ArrayList();
						//先加3个错误答案  choice.add(choiceQuestion.getCho_question());
						judge.add(judgeQuestionTest.getJud_question());
						jPaperList.add(judge);
						jPaperAnswerList.append("6_"+count+"_"+judgeQuestionTest.getJud_answer()+","); //将答案加到答案list中
						count++;
						middleCount--;
						
					}else if(highCount != 0 && judgeQuestionTest.getDegree() == 2) {
						List judge = new ArrayList();
						//先加3个错误答案  choice.add(choiceQuestion.getCho_question());
						judge.add(judgeQuestionTest.getJud_question());
						jPaperList.add(judge);
						jPaperAnswerList.append("6_"+count+"_"+judgeQuestionTest.getJud_answer()+","); //将答案加到答案list中
						count++;
						highCount--;
						
					}else if(simpleCount==0 && middleCount==0 && highCount==0 )
						break;//否则已经挑选完成
					
				}
			}else {
				List<JudgeQuestion> jList = judgeQuestionService.getAll();
				//打乱题目
				Collections.shuffle(jList);
				Random random = new Random();
				JudgeQuestion judgeQuestion = null;
				int count = 0;
				for(int j=0; j<jList.size(); j++) {
					//挑选
					judgeQuestion = jList.get(j);
					if(simpleCount != 0 && judgeQuestion.getDegree() == 0) {
						List judge = new ArrayList();
						//先加3个错误答案  choice.add(choiceQuestion.getCho_question());
						judge.add(judgeQuestion.getJud_question());
						jPaperList.add(judge);
						jPaperAnswerList.append("6_"+count+"_"+judgeQuestion.getJud_answer()+","); //将答案加到答案list中
						count++;
						simpleCount--;
						
					}else if(middleCount != 0 && judgeQuestion.getDegree() == 1) {
						List judge = new ArrayList();
						//先加3个错误答案  choice.add(choiceQuestion.getCho_question());
						judge.add(judgeQuestion.getJud_question());
						jPaperList.add(judge);
						jPaperAnswerList.append("6_"+count+"_"+judgeQuestion.getJud_answer()+","); //将答案加到答案list中
						count++;
						middleCount--;
						
					}else if(highCount != 0 && judgeQuestion.getDegree() == 2) {
						List judge = new ArrayList();
						//先加3个错误答案  choice.add(choiceQuestion.getCho_question());
						judge.add(judgeQuestion.getJud_question());
						jPaperList.add(judge);
						jPaperAnswerList.append("6_"+count+"_"+judgeQuestion.getJud_answer()+","); //将答案加到答案list中
						count++;
						highCount--;
						
					}else if(simpleCount==0 && middleCount==0 && highCount==0 )
						break;//否则已经挑选完成
					
				}
			}
			
			//将选择题添加进数据集里
			data.put("jPaperList", jPaperList);
			k_answer.append(jPaperAnswerList);
		}
	}

	public void generateSubjectiveQuestion(int type,int subjective_num,byte kind,String name) {
		//加名词解释
		if(subjective_num != 0) {
			//挑选名词解释
			//rule.getSingle_choice_score();
			int simpleCount = 0; //简单数
			int middleCount = 0; //中等数
			int highCount = 0;   //难等数
			switch(rule.getDegree()) {
				case 0:
					simpleCount = (int) (subjective_num * 0.8);
					middleCount = (int) (subjective_num * 0.1);
					highCount = (int) (subjective_num * 0.1);
					middleCount += subjective_num-simpleCount-middleCount-highCount; 
					break;
				case 1:
					simpleCount = (int) (subjective_num * 0.6);
					middleCount = (int) (subjective_num * 0.2);
					highCount = (int) (subjective_num * 0.2);
					middleCount += subjective_num-simpleCount-middleCount-highCount;
					break;
				case 2:
					simpleCount = (int) (subjective_num * 0.4);
					middleCount = (int) (subjective_num * 0.3);
					highCount = (int) (subjective_num * 0.3);
					middleCount += subjective_num-simpleCount-middleCount-highCount;
					break;
			}
			
			//分练习题库,考试题库
			List sPaperList = new ArrayList();  //题目
			if(type == 0) {
				List<SubjectiveQuestionTest> sList = subjectiveQuestionTestService.getAllByKind(kind);
				//打乱题目
				Collections.shuffle(sList);
				Random random = new Random();
				SubjectiveQuestionTest subjectiveQuestionTest = null;
				SubjectiveAnswer subjectiveAnswer = null; //答案
				int count = 0;
				for(int j=0; j<sList.size(); j++) {
					//挑选
					subjectiveQuestionTest = sList.get(j);
					subjectiveAnswer = new SubjectiveAnswer();
					if(simpleCount != 0 && subjectiveQuestionTest.getDegree() == 0) {
						List blank = new ArrayList();
						//先加问题
						blank.add(subjectiveQuestionTest.getSq_question());
						sPaperList.add(blank);
						//加答案
						subjectiveAnswer.setAnswer_right(subjectiveQuestionTest.getSq_answer()); //将答案加到答案list中
						subjectiveAnswer.setSequence(count);
						z_answer.add(subjectiveAnswer);
						count++;
						simpleCount--;
						
					}else if(middleCount != 0 && subjectiveQuestionTest.getDegree() == 1) {
						List blank = new ArrayList();
						//先加问题
						blank.add(subjectiveQuestionTest.getSq_question());
						sPaperList.add(blank);
						//加答案
						subjectiveAnswer.setAnswer_right(subjectiveQuestionTest.getSq_answer()); //将答案加到答案list中
						subjectiveAnswer.setSequence(count);
						z_answer.add(subjectiveAnswer);
						count++;
						middleCount--;
						
					}else if(highCount != 0 && subjectiveQuestionTest.getDegree() == 2) {
						List blank = new ArrayList();
						//先加问题
						blank.add(subjectiveQuestionTest.getSq_question());
						sPaperList.add(blank);
						//加答案
						subjectiveAnswer.setAnswer_right(subjectiveQuestionTest.getSq_answer()); //将答案加到答案list中
						subjectiveAnswer.setSequence(count);
						z_answer.add(subjectiveAnswer);
						count++;
						highCount--;
						
					}else if(simpleCount==0 && middleCount==0 && highCount==0 )
						break;//否则已经挑选完成
					
				}
			}else {
				List<SubjectiveQuestion> sList = subjectiveQuestionService.getAllByKind(kind);
				//打乱题目
				Collections.shuffle(sList);
				Random random = new Random();
				SubjectiveQuestion subjectiveQuestion = null;
				SubjectiveAnswer subjectiveAnswer = null; //答案
				int count = 0;
				for(int j=0; j<sList.size(); j++) {
					//挑选
					subjectiveQuestion = sList.get(j);
					subjectiveAnswer = new SubjectiveAnswer();
					if(simpleCount != 0 && subjectiveQuestion.getDegree() == 0) {
						List blank = new ArrayList();
						//先加问题
						blank.add(subjectiveQuestion.getSq_question());
						sPaperList.add(blank);
						//加答案
						subjectiveAnswer.setAnswer_right(subjectiveQuestion.getSq_answer()); //将答案加到答案list中
						subjectiveAnswer.setAnswer_question(subjectiveQuestion.getSq_question());
						subjectiveAnswer.setKind(kind);
						subjectiveAnswer.setSequence(count);
						z_answer.add(subjectiveAnswer);
						count++;
						simpleCount--;
						
					}else if(middleCount != 0 && subjectiveQuestion.getDegree() == 1) {
						List blank = new ArrayList();
						//先加问题
						blank.add(subjectiveQuestion.getSq_question());
						sPaperList.add(blank);
						//加答案
						subjectiveAnswer.setAnswer_right(subjectiveQuestion.getSq_answer()); //将答案加到答案list中
						subjectiveAnswer.setAnswer_question(subjectiveQuestion.getSq_question());
						subjectiveAnswer.setKind(kind);
						subjectiveAnswer.setSequence(count);
						z_answer.add(subjectiveAnswer);
						count++;
						middleCount--;
						
					}else if(highCount != 0 && subjectiveQuestion.getDegree() == 2) {
						List blank = new ArrayList();
						//先加问题
						blank.add(subjectiveQuestion.getSq_question());
						sPaperList.add(blank);
						//加答案
						subjectiveAnswer.setAnswer_right(subjectiveQuestion.getSq_answer()); //将答案加到答案list中
						subjectiveAnswer.setAnswer_question(subjectiveQuestion.getSq_question());
						subjectiveAnswer.setKind(kind);
						subjectiveAnswer.setSequence(count);
						z_answer.add(subjectiveAnswer);
						count++;
						highCount--;
						
					}else if(simpleCount==0 && middleCount==0 && highCount==0 )
						break;//否则已经挑选完成
					
				}
			}
			
			//将选择题添加进数据集里
			data.put(name, sPaperList);
		}
	}
}
