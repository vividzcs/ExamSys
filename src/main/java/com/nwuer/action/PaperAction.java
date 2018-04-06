package com.nwuer.action;

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
import com.nwuer.entity.JudgeQuestion;
import com.nwuer.entity.ObjectiveAnswer;
import com.nwuer.entity.Paper;
import com.nwuer.entity.PaperRule;
import com.nwuer.entity.StudentRegister;
import com.nwuer.entity.SubjectiveAnswer;
import com.nwuer.entity.SubjectiveQuestion;
import com.nwuer.service.ChoiceQuestionService;
import com.nwuer.service.GuardianShipService;
import com.nwuer.service.JudgeQuestionService;
import com.nwuer.service.ObjectiveAnswerService;
import com.nwuer.service.PaperRuleService;
import com.nwuer.service.PaperService;
import com.nwuer.service.StudentRegisterService;
import com.nwuer.service.SubjectiveAnswerService;
import com.nwuer.service.SubjectiveQuestionService;
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
	private StudentRegisterService studentRegisterService;
	@Autowired
	private ObjectiveAnswerService objectiveAnswerService;
	@Autowired
	private SubjectiveAnswerService SubjectiveAnswerService;
	@Autowired
	private GuardianShipService guardianShipService;
	@Autowired
	private ValidateUtil validateUtil;
	@Autowired
	private FreemarkerUtil freemarkerUtil;
	
	public String showAdd() {
		List<PaperRule> list = paperRuleService.getAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "showAdd";
	}
	
	/**
	 * 生成试卷
	 */
	PaperRule rule = null;
	Map data = new HashMap();
	StringBuilder k_answer = new StringBuilder();
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
	
	public void generatePracticePaper(int num) {
		rule = paperRuleService.getByIdEager(paper.getP_id());
		List<StudentRegister> studentRegisterList = this.studentRegisterService.getAll(); 
		int s_num = studentRegisterList.size(); //注册学生人数
		int other = num-s_num;  //多余的试卷
		for(int i=0; i<num; i++) {
			
			//先插入试卷,获取uuid,然后将uuid作为试卷名
			Paper p = new Paper();
			p.setP_id(this.paper.getP_id());
			String uuid = this.paperService.add(p);
//				System.out.println(uuid);
			//根据试卷生成规则挑选数据    在生成试卷时还要生成答案
			//简单:  80% 简单 10%中 10%难
			//中等:  60% 简单 20%中 20%难
			//难   :  40%简单  30%中 30%难
			
			//不用挑,生成试卷,所有人都可以练习,不用与学生注册表关联
			
			//加选择题
			generateChoiceQuestion();
			//加判断题
			generateJudgeQuestion();
			//加名词解释题  0:名词解释,1:填空,2:简答,3:计算,4:综合
			generateSubjectiveQuestion(rule.getTranslate_num(),new Byte("0"));
			//加填空题
			generateSubjectiveQuestion(rule.getBlank_num(),new Byte("1"));
			//加简答题
			generateSubjectiveQuestion(rule.getSimple_question_num(),new Byte("2"));
			//加计算题
			generateSubjectiveQuestion(rule.getCompute_num(),new Byte("3"));
			//加综合题
			generateSubjectiveQuestion(rule.getMix_num(),new Byte("4"));
			
			
			this.freemarkerUtil.makePaper(data, uuid);
			
		}
	}
	
	public void generateExamPaper(int num) {
		rule = paperRuleService.getByIdEager(paper.getP_id());
		List<StudentRegister> studentRegisterList = this.studentRegisterService.getAll(); 
		int s_num = studentRegisterList.size(); //注册学生人数
		int other = num-s_num;  //多余的试卷
		Paper p = null;
		String sub_name = rule.getSubject().getSub_name();
		for(int i=0; i<num; i++) {
			String uuid = null;
			objectiveAnswer = new ObjectiveAnswer();
			z_answer = new ArrayList<SubjectiveAnswer>();
			if(i<s_num) { //说明是生成正常考试卷子
				//先插入试卷,获取uuid,然后将uuid作为试卷名
				p = new Paper();
				p.setP_id(this.paper.getP_id());
				int g_id = this.guardianShipService.getByMajorAndSubject(rule.getMajor().getM_id(), rule.getSubject().getSub_id());
				p.setG_id(g_id);
				p.setPap_kind(new Byte(1+""));
				uuid = this.paperService.add(p);
				p.setPap_id(uuid);
				p.setCreate_time(System.currentTimeMillis());
//				System.out.println(uuid);
				//根据试卷生成规则挑选数据    在生成试卷时还要生成答案
				//简单:  80% 简单 10%中 10%难
				//中等:  60% 简单 20%中 20%难
				//难   :  40%简单  30%中 30%难
				
				//挑一个学生来生成试卷
				StudentRegister studentRegister = studentRegisterList.get(i); 
				p.setSr_id(studentRegister.getSr_id());
				
				//加选择题
				generateChoiceQuestion();
				//加判断题
				generateJudgeQuestion();
				//加名词解释题  0:名词解释,1:填空,2:简答,3:计算,4:综合
				generateSubjectiveQuestion(rule.getTranslate_num(),new Byte("0"));
				//加填空题
				generateSubjectiveQuestion(rule.getBlank_num(),new Byte("1"));
				//加简答题
				generateSubjectiveQuestion(rule.getSimple_question_num(),new Byte("2"));
				//加计算题
				generateSubjectiveQuestion(rule.getCompute_num(),new Byte("3"));
				//加综合题
				generateSubjectiveQuestion(rule.getMix_num(),new Byte("4"));
				
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
				generateChoiceQuestion();
				//加判断题
				generateJudgeQuestion();
				//加名词解释题  0:名词解释,1:填空,2:简答,3:计算,4:综合
				generateSubjectiveQuestion(rule.getTranslate_num(),new Byte("0"));
				//加填空题
				generateSubjectiveQuestion(rule.getBlank_num(),new Byte("1"));
				//加简答题
				generateSubjectiveQuestion(rule.getSimple_question_num(),new Byte("2"));
				//加计算题
				generateSubjectiveQuestion(rule.getCompute_num(),new Byte("3"));
				//加综合题
				generateSubjectiveQuestion(rule.getMix_num(),new Byte("4"));
				
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
			data.put("subject",sub_name );
			String paperPath = this.freemarkerUtil.makePaper(data, uuid);
			p.setPap_url(paperPath);
			this.objectiveAnswerService.add(objectiveAnswer);
			for(int k=0;k<z_answer.size();k++) {
				this.SubjectiveAnswerService.add(z_answer.get(k));
			}
			//答案添加完毕
			
			
			 //END//一张卷子添加完毕
		} 
	}
	
	public void generateChoiceQuestion() {
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
			
			List<ChoiceQuestion> cList = choiceQuestionService.getAll();
			//打乱题目
			Collections.shuffle(cList);
			List cPaperList = new ArrayList();  //题目
			StringBuilder cPaperAnswerList = new StringBuilder();  //答案
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
			//将选择题添加进数据集里
			data.put("cPaperList", cPaperList);
			k_answer.append(cPaperAnswerList);
			
			
		}
	}
	
	public void generateJudgeQuestion() {
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
			
			List<JudgeQuestion> jList = judgeQuestionService.getAll();
			//打乱题目
			Collections.shuffle(jList);
			List jPaperList = new ArrayList();  //题目
			StringBuilder jPaperAnswerList = new StringBuilder();  //答案
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
			//将选择题添加进数据集里
			data.put("jPaperList", jPaperList);
			k_answer.append(jPaperAnswerList);
		}
	}

	public void generateSubjectiveQuestion(int subjective_num,byte kind) {
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
			
			List<SubjectiveQuestion> sList = subjectiveQuestionService.getAllByKind(kind);
			//打乱题目
			Collections.shuffle(sList);
			List sPaperList = new ArrayList();  //题目
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
					subjectiveAnswer.setSequence(count);
					count++;
					simpleCount--;
					
				}else if(middleCount != 0 && subjectiveQuestion.getDegree() == 1) {
					List blank = new ArrayList();
					//先加问题
					blank.add(subjectiveQuestion.getSq_question());
					sPaperList.add(blank);
					//加答案
					subjectiveAnswer.setAnswer_right(subjectiveQuestion.getSq_answer()); //将答案加到答案list中
					subjectiveAnswer.setSequence(count);
					count++;
					middleCount--;
					
				}else if(highCount != 0 && subjectiveQuestion.getDegree() == 2) {
					List blank = new ArrayList();
					//先加问题
					blank.add(subjectiveQuestion.getSq_question());
					sPaperList.add(blank);
					//加答案
					subjectiveAnswer.setAnswer_right(subjectiveQuestion.getSq_answer()); //将答案加到答案list中
					subjectiveAnswer.setSequence(count);
					count++;
					highCount--;
					
				}
				//否则已经挑选完成
				break;
				
			}
			//将选择题添加进数据集里
			data.put("jPaperList", sPaperList);
			z_answer = new ArrayList<SubjectiveAnswer>();
			z_answer.add(subjectiveAnswer);
			
		}
	}
}
