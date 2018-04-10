package com.nwuer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Academy;
import com.nwuer.entity.Major;
import com.nwuer.entity.ObjectiveAnswer;
import com.nwuer.entity.Paper;
import com.nwuer.entity.PaperRule;
import com.nwuer.entity.Student;
import com.nwuer.entity.StudentRegister;
import com.nwuer.entity.Subject;
import com.nwuer.entity.SubjectiveAnswer;
import com.nwuer.service.AcademyService;
import com.nwuer.service.MajorService;
import com.nwuer.service.ObjectiveAnswerService;
import com.nwuer.service.PaperRuleService;
import com.nwuer.service.PaperService;
import com.nwuer.service.StudentRegisterService;
import com.nwuer.service.StudentService;
import com.nwuer.service.SubjectiveAnswerService;
import com.nwuer.utils.Crpty;
import com.nwuer.utils.ValidateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Controller
@Scope("prototype")
public class StudentAction extends ActionSupport implements ModelDriven<Student> {
	private Student student = new Student();
	@Override
	public Student getModel() {
		return student;
	}  //模型驱动获取数据
	String info;
	
	private Map result = new HashMap();
	public Map getResult() {
		return result;
	}
	public void setResult(Map result) {
		this.result = result;
	} //传回前端得Json数据
	@Autowired
	private StudentService studentService;
	@Autowired
	private PaperService paperService;
	@Autowired
	private PaperRuleService paperRuleService;
	@Autowired
	private AcademyService academyService; 
	@Autowired
	private MajorService majorService;
	@Autowired
	private StudentRegisterService studentRegisterService;
	@Autowired
	private ObjectiveAnswerService objectiveAnswerService;
	@Autowired
	private SubjectiveAnswerService SubjectiveAnswerService;
	@Autowired
	private Crpty crpty;
	@Autowired
	private ValidateUtil validateUtil;
	HttpServletRequest req = ServletActionContext.getRequest();

	/**
	 * 登录
	 * @return
	 */
	public String login() {
		//验证
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		//验证验证码
		String code = req.getParameter("code");
		String codeReal = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if(!codeReal.equalsIgnoreCase(code)) {
			result.put("status","0");
			result.put("msg", "验证码错误");
			return ERROR;
		}
		
		info = this.validateUtil.validateNumber(student.getS_number(), 10);
		if(info != null) {
			result.put("status","0");
			result.put("msg", "编号"+ info);
			return ERROR;
		}
		
		Student studentConfirm = this.studentService.getByNumberAndPass(student);
		if(studentConfirm != null) {
			//登录成功
			studentConfirm.setS_pass(null);
			session.setAttribute("student", studentConfirm);
			
			this.result.put("status", "1");
			this.result.put("msg", "登录成功");
			return SUCCESS;
		}else {
			this.result.put("status", "0");
			this.result.put("msg", "用户名或密码错误");
			return ERROR;
		}
		
	}
	
	/**
	 * 确认开始考试
	 * @return
	 */
	public String confirm() {
		//验证
		
		info = this.validateUtil.validateNumber(this.student.getS_number(), 10);
		if(info!= null) {
			req.setAttribute("info", "学号"+info);
			return ERROR;
		}
		//检查登录的考生和现在确认的考生是否同一个
		Student stu = (Student) req.getSession().getAttribute("student"); 
		if(!stu.getS_number().equals(this.student.getS_number())){
			req.setAttribute("info","登录考生和确认考生不一致" );
			return ERROR;
		}
		
		Student s = this.studentService.getByNumberAndPass(this.student);
		List<StudentRegister> list = this.studentRegisterService.getCanExamByNumber(this.student.getS_number());
		
		if(s != null && list!= null && list.size()>0) {
			return "before";
		}else {
			req.setAttribute("info", "学号或密码错误或没有考试或未生成试卷");
			return ERROR;
		}
		
	}
	
	/**
	 * 开始考试,挑选时间正确的
	 * @return
	 */
	public String beginExam() {
		Student stu = (Student) req.getSession().getAttribute("student"); 
		//检查是否已注册
		List<StudentRegister> list = this.studentRegisterService.getCanExamByNumber(stu.getS_number()); 
		if(list==null || list.size()==0) {
			req.setAttribute("info", "该生未注册,或未生成试卷");
			return ERROR;
		}
		//开始挑卷子
		StudentRegister sr = null;
		Paper p = null;
		PaperRule rule = null;
		for(int i=0;i<list.size();i++) {
			sr = list.get(i);
			p = this.paperService.getByIdEager(sr.getPaper());
			rule = this.paperRuleService.getByIdEager(p.getP_id());
			if(rule.getStart_time()>System.currentTimeMillis() || rule.getEnd_time()<System.currentTimeMillis()) {
				System.out.println(rule.getStart_time());
				System.out.println(System.currentTimeMillis());
				System.out.println(rule.getEnd_time());
				p=null;
				rule=null;
				sr=null;
				continue; //说明还没有开始考试
			}else {
				//已经可以考试
				sr.setStatus(new Byte(3+""));
				this.studentRegisterService.update(sr);
				break;
			}
		}
		//
		if(p==null || sr==null) {
			//说明不能考试
			req.setAttribute("info", "考试时间未到或已过");
			return ERROR;
		}else {
			//可以考试考试
			try {
				ServletActionContext.getResponse().sendRedirect(req.getContextPath() + "/" +p.getPap_url());
				return NONE;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		req.setAttribute("info", "请检查考试时间后重试");
		return ERROR;
	}
	
	/**
	 * 开始考试,挑选时间正确的
	 * @return
	 */
	public String beginPratice() {
		//开始挑卷子
		String m_id = req.getParameter("m_id");
		String sub_id = req.getParameter("sub_id");
		if(this.validateUtil.isNumber(m_id)!=null || this.validateUtil.isNumber(sub_id)!=null) {
			req.setAttribute("info", "专业或科目错误");
			return ERROR;
		}
		Paper p = this.paperService.getPraticePaperByMajorAndSubject(Integer.parseInt(m_id), Integer.parseInt(sub_id));
		if(p==null) {
			//说明未生成练习试卷
			req.setAttribute("info", "未生成练习试卷");
			return ERROR;
		}else {
			//可以考试考试
			try {
				ServletActionContext.getResponse().sendRedirect(req.getContextPath() + "/" +p.getPap_url());
				return NONE;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		req.setAttribute("info", "系统错误");
		return ERROR;
	}
	/**
	 * 提交考试试卷
	 * @return
	 */
	public String pushPaper() {
		//0:名词解释,1:填空,2:简答,3:计算,4:综合 ,5:选择 ,6:判断
		Map map = req.getParameterMap();
		String uuid =  ((String[])map.get("uuid"))[0];
		//对是否有交卷资格进行验证
		StudentRegister sr = this.studentRegisterService.getByUuid(uuid);
		if(sr==null || sr.getStatus()!=3) {
			req.setAttribute("info", "系统错误");
			return ERROR;
		}
		
		Paper p = this.paperService.getById(uuid);
		PaperRule rule = this.paperRuleService.getByIdEager(this.paperService.getByIdEager(uuid).getP_id());
		if(rule ==null || p==null) {
			req.setAttribute("info", "系统错误");
			return ERROR;
		}
		//给客观题打分
		double objectiveScore = rule.getSingle_choice_score()+rule.getJudge_score();
		double sChoiceScore = rule.getSingle_choice_score()/rule.getSingle_choice_num();
		double sJudgeScore = rule.getJudge_score()/rule.getJudge_num();
		ObjectiveAnswer oAnswer = this.objectiveAnswerService.getByUuid(uuid);
		String oAnswerStr = oAnswer.getAnswer_right();
		String[] oAnswerArr = oAnswerStr.split(","); // 5_0_0 A
		String[] singleAnswerArr = null;
		String choice = "choice";
		String judge = "judge";
		int choiceNum=0;
		int judgeNum=0;
		StringBuilder answer_write = new StringBuilder();
		for(int i=0; i<oAnswerArr.length-1; i++) {  //减一是因为最后一个为空
			singleAnswerArr = oAnswerArr[i].split("_");
			
			if(singleAnswerArr[0].equals("5")) { //选择题
				 if(!map.get(choice+choiceNum).equals(singleAnswerArr[2])) {
					 //是选择题,并且错了
					 objectiveScore -= sChoiceScore;
				 }
				 //将答案拼接
				 answer_write.append("5_");
				 answer_write.append(choiceNum);
				 answer_write.append("_");
				 answer_write.append(singleAnswerArr[2]);
				 
				choiceNum++;
				
			}else if(singleAnswerArr[0].equals("6")) { //判断题
				if(!map.get(judge+judgeNum++).equals(singleAnswerArr[2])) {
					 //是选择题,并且错了  
					 objectiveScore -= sJudgeScore;
				 }
				 //将答案拼接
				 answer_write.append("6_");
				 answer_write.append(judgeNum);
				 answer_write.append("_");
				 answer_write.append(singleAnswerArr[2]);
				 
				judgeNum++;
			}
		}
		//考试,需要入库
		
		//打完分将成绩更新到ObjectiveAnswer表里面
		oAnswer.setGrade(objectiveScore);
		oAnswer.setAnswer_write(answer_write.toString());
		
		this.objectiveAnswerService.update(oAnswer);
		
		//将主观题入库
		List<SubjectiveAnswer> sAnswerList = this.SubjectiveAnswerService.getByUuid(uuid);
		String[] blank = (String[]) map.get("blank");
		String[] translate = (String[]) map.get("translate");
		String[] simple = (String[]) map.get("simple");
		String[] compute = (String[]) map.get("compute");
		String[] mix = (String[]) map.get("mix");
		SubjectiveAnswer sAnswer = null;
		for(int i=0;i<sAnswerList.size();i++) {
			sAnswer = sAnswerList.get(i);
			switch(sAnswer.getKind()) {  //0:名词解释,1:填空,2:简答,3:计算,4:综合 
			case 0: 
				sAnswer.setAnswer_write(translate[sAnswer.getSequence()]);
				break;
			case 1:
				sAnswer.setAnswer_write(blank[sAnswer.getSequence()]);
				break;
			case 2:
				sAnswer.setAnswer_write(simple[sAnswer.getSequence()]);
				break;
			case 3:
				sAnswer.setAnswer_write(mix[sAnswer.getSequence()]);
				break;
			}
			//一个题目的解答设置完毕
			
			this.SubjectiveAnswerService.update(sAnswer);
		}
		
		//3 已交卷
		p.setStatus(new Byte(3+""));
		this.paperService.update(p);
		sr.setGrade(objectiveScore);
		sr.setStatus(new Byte(4+""));
		this.studentRegisterService.update(sr);
		

		
		
		
		
		req.setAttribute("info", "交卷成功");
		return "showSuccess";
	}
	/**
	 * 提交练习试卷,计算分数后即返回
	 * @return
	 */
	public String pushPractice() {
		//0:名词解释,1:填空,2:简答,3:计算,4:综合 ,5:选择 ,6:判断
		Map map = req.getParameterMap();
		String uuid =  ((String[])map.get("uuid"))[0];
		Paper p = this.paperService.getById(uuid);
		PaperRule rule = this.paperRuleService.getByIdEager(this.paperService.getByIdEager(uuid).getP_id());
		if(rule ==null || p==null) {
			req.setAttribute("info", "系统错误");
			return ERROR;
		}
		//给客观题打分
		double objectiveScore = rule.getSingle_choice_score()+rule.getJudge_score();
		double sChoiceScore = rule.getSingle_choice_score()/rule.getSingle_choice_num();
		double sJudgeScore = rule.getJudge_score()/rule.getJudge_num();
		ObjectiveAnswer oAnswer = this.objectiveAnswerService.getByUuid(uuid);
		String oAnswerStr = oAnswer.getAnswer_right();
		String[] oAnswerArr = oAnswerStr.split(","); // 5_0_0 A
		String[] singleAnswerArr = null;
		String choice = "choice";
		String judge = "judge";
		int choiceNum=0;
		int judgeNum=0;
		StringBuilder answer_write = new StringBuilder();
		for(int i=0; i<oAnswerArr.length-1; i++) {  //减一是因为最后一个为空
			singleAnswerArr = oAnswerArr[i].split("_");
			
			if(singleAnswerArr[0].equals("5")) { //选择题
				 if(!map.get(choice+choiceNum).equals(singleAnswerArr[2])) {
					 //是选择题,并且错了
					 objectiveScore -= sChoiceScore;
				 }
				 //将答案拼接
				 answer_write.append("5_");
				 answer_write.append(choiceNum);
				 answer_write.append("_");
				 answer_write.append(singleAnswerArr[2]);
				 
				choiceNum++;
				
			}else if(singleAnswerArr[0].equals("6")) { //判断题
				if(!map.get(judge+judgeNum++).equals(singleAnswerArr[2])) {
					 //是选择题,并且错了  
					 objectiveScore -= sJudgeScore;
				 }
				 //将答案拼接
				 answer_write.append("6_");
				 answer_write.append(judgeNum);
				 answer_write.append("_");
				 answer_write.append(singleAnswerArr[2]);
				 
				judgeNum++;
			}
		}
		req.setAttribute("info", "客观题(选择,判断)分数:"+objectiveScore);
		return "showSuccess";
	}
	
	
	/**
	 * 得到可以注册的科目
	 * @return
	 */
	public String getSubjects() {
		Student s = (Student) ServletActionContext.getRequest().getSession().getAttribute("student");
		List<StudentRegister> list = this.studentRegisterService.getStudentRegisterByNumberAndStatus(s.getS_number(),new Byte(0+"")); //0:未注册
		for(int i=0; i<list.size(); i++) {
			Subject sub = list.get(i).getSubject();
			this.result.put(String.valueOf(sub.getSub_id()), sub.getSub_name());
		}
		return "subs";
	}
	
	/**
	 * 注册考试
	 * @return
	 */
	public String signin() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String sub_id = req.getParameter("sub");
		//验证数据
		info = this.validateUtil.isNumber(sub_id);
		if(info !=null) {
			this.result.put("status", "0");
			this.result.put("msg", "注册失败");
			return "subs";
		}
		Student s = this.studentService.getByNumberAndPass(student);
		if(s == null) {
			this.result.put("status", "0");
			this.result.put("msg", "注册失败");
			return "subs";
		}
		int rows = this.studentRegisterService.updateStatus(student.getS_number(), Integer.parseInt(sub_id), Byte.parseByte("1"));
		if(rows <=0) {
			this.result.put("status", "0");
			this.result.put("msg", "注册失败");
			return "subs";
		}else {
			this.result.put("status", "1");
			this.result.put("msg", "注册成功,如果还有科目需要考试,请继续注册");
			return "subs";
		}
		
	}
	
	/**
	 * 退出
	 * @return
	 */
	public String logout() {
		ServletActionContext.getRequest().getSession().setAttribute("student", null);
		return "logout";
	}
	
	public String list() {
		List<Student> s_list = this.studentService.getAllByTimeDesc();
		
		ServletActionContext.getRequest().setAttribute("s_list", s_list);
		return "list";
	}
	
	/**
	 * 返回专业和科目的联动数据
	 * @return
	 */
	public String mesFS() {
		List<Major> majors = this.majorService.getAll();
		
		/*{
	      	department:'信息科学与技术学院',
	      	profess:['软件工程','计算机科学与技术','电子信息','通信工程']
	      },*/
		Set<Subject> subs = null;
		for(int i=0; i<majors.size(); i++) {
			Map<String,Object> map = new HashMap<String,Object>(); //存放专业和科目的地址
			Major maj = majors.get(i); //得到专业
			map.put("profess", maj.getM_name()); //第一个元素值放专业
			Map<Integer,String> sub = new HashMap<Integer,String>();  //存放科目数据
			subs = maj.getS_set();  //得到专业
			Subject s = null;
			Iterator<Subject> it = subs.iterator();
			while (it.hasNext()) {
				s = it.next();
				sub.put(s.getSub_id(), s.getSub_name());
			}
			//
			map.put("subject", sub);
			this.result.put(maj.getM_id(), map);
		}
		return SUCCESS;
	}
	
	/**
	 * 添加学生
	 * @return
	 */
	public String add() {
		//验证
		HttpServletRequest req = ServletActionContext.getRequest();
		info = this.validateUtil.validateNumber(student.getS_number(), 10);
		if(info != null) {
			req.setAttribute("info","编号"+ info);
			return ERROR;
		}
		
		int id = (int) this.studentService.add(student);
		if(id > 0) {
			//添加成功
			return SUCCESS;
		} else {
			//添加失败
			req.setAttribute("info", "系统错误,请重试");
			return ERROR;
		}
	}
	/**
	 * 删除
	 * @return
	 */
	public String delete() {
		//验证id信息
		Student s = this.studentService.getByIdEager(student.getS_id());
		if(s == null) {
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		this.studentService.delete(this.student.getS_id());
		return SUCCESS;
	}
	
	public String edit() {
		return "edit";
	}
	
	public String update() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		String passConfirm = req.getParameter("passConfirm");
		String oldPass = req.getParameter("oldPass");
		if(passConfirm!=null && oldPass!=null && passConfirm.equals(this.student.getS_pass())) {
			Student s = this.studentService.getByIdEager(((Student) session.getAttribute("student")).getS_id());
			if(!s.getS_pass().equals(oldPass)) {
				req.setAttribute("info", "旧密码错误");
				return ERROR;
			}
			//旧密码正确
			s.setS_pass(this.student.getS_pass());
			this.studentService.update(s);
			
			return SUCCESS;
		}
		req.setAttribute("info", "请认真填写重试");
		return ERROR;
	}
	
	/**
	 * 管理员修改
	 * @return
	 */
	public String editA() {
		Student stu = this.studentService.getById(this.student.getS_id());
		ServletActionContext.getRequest().setAttribute("stu", stu);
		return "edit";
	}
	/**
	 * 管理员修改
	 * @return
	 */
	public String updateA() {
		//验证
		HttpServletRequest req = ServletActionContext.getRequest();
		info = this.validateUtil.validateNumber(student.getS_number(), 10);
		if(info != null) {
			req.setAttribute("info","编号"+ info);
			return ERROR;
		}
		
		Student stu = this.studentService.getById(student.getS_id());
		student.setCreate_time(stu.getCreate_time());
		student.setLast_login(stu.getLast_login());
		student.setStatus(stu.getStatus());
		this.studentService.update(student);
		
		student.setS_pass(null);
		req.getSession().setAttribute("student", student);;
		return SUCCESS;
	}
	
	public String exportStudent() {
		//开始写入
		ServletOutputStream sos = null;
		WritableWorkbook wwk = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/octet-stream");
		String a_name = "全部学生名单.xls";
		try {
			a_name = new String(a_name.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition","attachment;fileName=" + a_name);
		try {
			sos = response.getOutputStream();
			//创建一个可以写的工作簿
			wwk = Workbook.createWorkbook(sos);
			//创建可写入的工作表
			WritableSheet sheet = wwk.createSheet("全部学生信息", 0);
			
			sheet.setColumnView(0, 25);//根据内容自动设置列宽
			sheet.setColumnView(1, 25);//根据内容自动设置列宽
			sheet.setColumnView(2, 20);//根据内容自动设置列宽
			sheet.setColumnView(3, 12);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 10);
			
			//先创建表头    列 行
			Label lab_00 = new Label(0, 0, "学号");
			Label lab_10 = new Label(1, 0, "密码");
			Label lab_20 = new Label(2, 0, "姓名");
			Label lab_30 = new Label(3, 0, "性别");
			Label lab_40 = new Label(4, 0, "院系");
			Label lab_50 = new Label(5, 0, "专业");
			Label lab_60 = new Label(6, 0, "状态");
			sheet.addCell(lab_00);
			sheet.addCell(lab_10);
			sheet.addCell(lab_20);
			sheet.addCell(lab_30);
			sheet.addCell(lab_40);
			sheet.addCell(lab_50);
			sheet.addCell(lab_60);
			
			//开始写入表内容
			List<Student> list = this.studentService.getAll();
			for(int i=1; i<=list.size(); i++) {
				Student stu = list.get(i-1);
				Label lab_0 = new Label(0, i, stu.getS_number());
				Label lab_1 = new Label(1, i, crpty.decrypt(stu.getS_pass()));
				Label lab_2 = new Label(2, i, stu.getS_name());
				String sex = stu.getS_sex() == 1 ? "男" : "女";
				Label lab_3 = new Label(3, i, sex);
				Label lab_4 = new Label(4, i, stu.getAcademy().getA_name());
				Label lab_5 = new Label(5, i, stu.getMajor().getM_name());
				Label lab_6 = new Label(6, i, Byte.toString(stu.getStatus()));
				
				sheet.addCell(lab_0);
				sheet.addCell(lab_1);
				sheet.addCell(lab_2);
				sheet.addCell(lab_3);
				sheet.addCell(lab_4);
				sheet.addCell(lab_5);
				sheet.addCell(lab_6);
				
			}
			
			//添加完成
			wwk.write();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if(wwk != null)
						wwk.close();
					if(sos != null) {
						sos.close();
						sos.flush();
						response.setHeader("Content-Disposition","attachment;fileName=导出教师出错");
					}
						
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return NONE;
	}
	
	/**
	 * 导入学生
	 */
	private File file_upload; //上传的文件,  是上传表单项的name值
	private String file_uploadFileName; //上传文件名称,表单上传项的文件名+FileName
	public File getFile_upload() {
		return file_upload;
	}
	public void setFile_upload(File file_upload) {
		this.file_upload = file_upload;
	}
	public String getFile_uploadFileName() {
		return file_uploadFileName;
	}
	public void setFile_uploadFileName(String file_uploadFileName) {
		this.file_uploadFileName = file_uploadFileName;
	} //上传文件相关
	
	public String importStudent() {
		//先清空Student表,先判断学生表是否有数据
		HttpServletRequest req = ServletActionContext.getRequest();
		if(this.studentService.hasData()) {
			this.studentService.clear();
			if(this.studentService.hasData()) {
				//没有清空表
				info = "历史学生数据无法清空!请稍后再试";
				req.setAttribute("info", info);
				return ERROR;
			}
		}
		
		FileInputStream excel = null;
		Workbook wb =  null;
		try {
			//事务开始
			//得到Excel文件
			excel = new FileInputStream(file_upload);
			//获取工作簿对象
			wb = Workbook.getWorkbook(excel);//只读
			//开始对excel数据进行操作(行,列)
			//学号	密码	姓名	性别	院系	专业	是否能登录(0:不能,1:能)
			Sheet sheet = wb.getSheet(0);
			//首先要得到有多少行
			int rows = 0;
			int rowsAll = sheet.getRows();
			for(int i=0; i<rowsAll; i++) {
				Cell cell = sheet.getCell(0,i);
				if(cell.getContents().trim().equals("")) {
					rows = i;
					break;
				}
			}
			rows = rows == 0 ? rowsAll : rows; //如果有多余空行就等于实际行,否则等于总行
			for(int j=1; j<rows; j++) {
				Student student = new Student();
				 //处理学号
				Cell numCell = sheet.getCell(0, j);
				String number = numCell.getContents();
				if(number.trim().length() != 10) {
					//不对
					info = "第" + j + "行学号有误";
					req.setAttribute("info", info);
					return ERROR;
				}
				//处理密码
				Cell passCell = sheet.getCell(1,j);
				String pass = passCell.getContents();
				if(pass.trim().equals("")) {
					//不对
					info = "第" + j + "行密码不能为空";
					req.setAttribute("info", info);
					return ERROR;
				}
				
				//处理姓名
				Cell nameCell = sheet.getCell(2,j);
				String name = nameCell.getContents();
				if(name.trim().equals("")) {
					//不对
					info = "第" + j + "行姓名不能为空";
					req.setAttribute("info", info);
					return ERROR;
				}
				
				//处理性别
				Cell sexCell = sheet.getCell(3,j);
				String sex = sexCell.getContents();
				if(!sex.trim().equals("男") && !sex.trim().equals("女")) {
					//不对
					info = "第" + j + "行性别填写错误" ;
					req.setAttribute("info", info);
					return ERROR;
				}
				byte s_sex = (byte) (sex.trim().equals("男") ? 1 : 0);
				
				//处理院系
				Cell academyCell = sheet.getCell(4,j);
				String academy = academyCell.getContents();
				int a_id = 0;
				if(academy.trim().equals("") || (a_id = this.academyService.getIdByName(academy)) ==0 ) {
					info = "第" + j + "行院系填写错误";
					req.setAttribute("info", info);
					return ERROR;
				}
				
				//处理专业
				Cell majorCell = sheet.getCell(5,j);
				String major = majorCell.getContents();
				int m_id = 0;
				if(major.trim().equals("") || (m_id=this.majorService.getIdByName(major))== 0) {
					
					info = "第" + j + "行专业填写错误";
					req.setAttribute("info", info);
					return ERROR;
				}
				
				//处理是否能登录
				Cell loginCell = sheet.getCell(6,j);
				String login = loginCell.getContents();
				if(!login.trim().equals("0") && !login.trim().equals("1")) {
					//不对
					info = "第" + j + "行登录状态填写错误";
					req.setAttribute("info", info);
					return ERROR;
				}
				byte status = Byte.parseByte(login);
				
				//验证通过
				Academy s_academy = new Academy();
				s_academy.setA_id(a_id);
				Major s_major = new Major();
				s_major.setM_id(m_id);
				
				student.setS_name(name);
				student.setS_number(number);
				student.setS_pass(pass);
				student.setS_sex(s_sex);
				student.setStatus(status);
				student.setAcademy(s_academy);
				student.setMajor(s_major);
				int id = this.studentService.add(student);
				if(id <= 0) {
					//添加失败
					info = "第" + j + "个学生添加失败,请重试";
					req.setAttribute("info", info);
					return ERROR;
				}
			}
			
			//添加完成
			return SUCCESS;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if(excel != null)
						excel.close();
					if(wb != null)
						wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		info = "系统错误,请重试";
		req.setAttribute("info", info);
		return ERROR;
	}
}
