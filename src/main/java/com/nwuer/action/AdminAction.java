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
import com.nwuer.entity.Admin;
import com.nwuer.entity.ChoiceQuestion;
import com.nwuer.entity.JudgeQuestion;
import com.nwuer.entity.Major;
import com.nwuer.entity.Subject;
import com.nwuer.entity.SubjectiveQuestion;
import com.nwuer.entity.Teacher;
import com.nwuer.service.AcademyService;
import com.nwuer.service.AdminService;
import com.nwuer.service.ChoiceQuestionService;
import com.nwuer.service.JudgeQuestionService;
import com.nwuer.service.MajorService;
import com.nwuer.service.SubjectService;
import com.nwuer.service.SubjectiveQuestionService;
import com.nwuer.service.TeacherService;
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
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {
	private Admin admin = new Admin();
	@Override
	public Admin getModel() {
		return admin;
	}  //模型驱动获取数据
	@Autowired
	private AdminService adminService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private AcademyService academyService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ChoiceQuestionService choiceQuestionService;
	@Autowired
	private JudgeQuestionService judgeQuestionService;
	@Autowired
	private SubjectiveQuestionService subjectiveQuestionService;
	@Autowired
	private Crpty crpty;
	@Autowired
	private ValidateUtil validateUtil;
	
	private Map result = new HashMap();
	public Map getResult() {
		return result;
	}
	public void setResult(Map result) {
		this.result = result;
	}  //返回JSON数据
	
	/**
	 * 维护管理员信息
	 * @return
	 */
	public String update() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String newPass = req.getParameter("newPass");
		//验证密码
		
		//验证管理员用户信息
		
		
		//查询数据
		int ad_id = ((Admin)req.getSession().getAttribute("admin")).getAd_id();
		Admin newAdmin = this.adminService.getById(ad_id);
		//修改
		
		newAdmin.setAd_name(admin.getAd_name());
		
		this.adminService.update(newAdmin);
		
		newAdmin.setAd_pass(null);
		req.getSession().setAttribute("admin", newAdmin);
		this.result.put("status","1");
		this.result.put("msg", "修改成功");
		return SUCCESS;
	}
	
	public String logout() {
		ServletActionContext.getRequest().getSession().setAttribute("admin", null);
		return "logout";
	}
	
	/**
	 * 登录
	 * @return
	 */
	public String login() {
		//验证
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		//验证验证码
		String code = request.getParameter("code");
		String codeReal = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if(!codeReal.equalsIgnoreCase(code)) {
			result.put("status","0");
			result.put("msg", "验证码错误");
			return ERROR;
		}
		//验证登录信息
		String msg = this.validateUtil.validateNumber(this.admin.getAd_number(), 10);
		if(msg!= null) {
			result.put("status","0");
			result.put("msg", "验证码错误");
			return ERROR;
		}
		msg = this.validateUtil.validateMinLength(this.admin.getAd_pass(), 6);
		if(msg!= null) {
			result.put("status","0");
			result.put("msg", "验证码错误");
			return ERROR;
		}
		Admin adminConfirm = this.adminService.getByNumberAndPass(this.admin);
		if(adminConfirm != null) {
			//成功
			adminConfirm.setAd_pass(null);
			this.adminService.updateLastLogin(System.currentTimeMillis(), adminConfirm.getAd_id());
			session.setAttribute("admin", adminConfirm);
			this.result.put("status","1");
			this.result.put("msg", "登录成功");
			return SUCCESS;
		}else {
			this.result.put("status","0");
			this.result.put("msg", "用户名或密码错误");
			return ERROR;
		}
			
		
	}
	/**
	 * 查询教师信息
	 */
	private String t_number;
	public String getT_number() {
		return t_number;
	}
	public void setT_number(String t_number) {
		this.t_number = t_number;
	}
	public String findTeacher() {
		//检查数据合法性
		if(t_number == null) {
			return "find";
		}
		List<Teacher> list = this.teacherService.getByNumber(t_number);
		Teacher t = null;
		for(int i=0; i<list.size(); i++) {
			t = list.get(i);
			t.setT_pass(crpty.decrypt(t.getT_pass()));
		}
		
		ServletActionContext.getRequest().setAttribute("list", list);
		return "find";
	}
	
	/**
	 * 返回院系和专业的联动数据
	 * @return
	 */
	public String mesDF() {
		List<Academy> academys = this.academyService.getAll();
		
		/*{
	      	department:'信息科学与技术学院',
	      	profess:['软件工程','计算机科学与技术','电子信息','通信工程']
	      },*/
		Set<Major> majors = null;
		for(int i=0; i<academys.size(); i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			Academy aca = academys.get(i);
			map.put("department", aca.getA_name());
			Map<Integer,String> profess = new HashMap<Integer,String>();
			majors = aca.getM_set();
			Major maj = null;
			Iterator<Major> it = majors.iterator();
			while (it.hasNext()) {
				maj = it.next();
				profess.put(maj.getM_id(), maj.getM_name());
			}
			//
			map.put("profess", profess);
			this.result.put(aca.getA_id(), map);
		}
		return SUCCESS;
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
	 * 导入题库相关数据
	 */
	private int kind; //题库类型
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
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	/**
	 * 导入题库
	 * @return
	 */
	public String importQuestionBank() {
		//检查数据
		
		//开始导入
		String flag = "";
		switch (kind) {
		case 0 :
			//选择题
			flag = importChoiceQuestion();
			if(flag == null) {
				//导入成功
				return SUCCESS;
			}
			break;
		case 1 :
			//判断题
			flag = importJudgeQuestion();
			if(flag == null) {
				//导入成功
				return SUCCESS;
			}
			break;
		case 2 :
			//主观题
			flag = importSubjectiveQuestion();
			if(flag == null) {
				//导入成功
				return SUCCESS;
			}
			break;
		}
		
		//到这里说明导入失败
		return ERROR;
		
	}
	
	/**
	 * 导入选择题
	 * @return 成功返回null,否则返回错误信息
	 */
	public String importChoiceQuestion() {
		//添加也得清空
		String info = "";
		//先清空选择题库,先判断选择题库是否有数据
				if(this.choiceQuestionService.hasData()) {
					this.choiceQuestionService.clear();
					if(this.choiceQuestionService.hasData()) {
						//没有清空表
						info = "历史选择题库数据无法清空!请稍后再试" ;
						return info;
					}
				}
		//开始导入
		FileInputStream excel = null;
		Workbook wb =  null;
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			//得到Excel文件
			excel = new FileInputStream(file_upload);
			//获取工作簿对象
			wb = Workbook.getWorkbook(excel);//只读
			//开始对excel数据进行操作(行,列)
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
				ChoiceQuestion choiceQuestion = new ChoiceQuestion();
				 //处理题目
				Cell questionCell = sheet.getCell(0, j);
				String question = questionCell.getContents();
				if(question.trim().equals("")) {
					//不对
					info =  "第" + j + "行题目不能为空" ;
					return info;
				}
				
				//处理正确选项
				Cell answerCell = sheet.getCell(1,j);
				String answer = answerCell.getContents();
				if(answer.trim().equals("")) {
					//不对
					info = "第" + j + "行正确选项不能为空";
					return info;
				}

				//处理其他选项1
				Cell other1Cell = sheet.getCell(2,j);
				String other1 = other1Cell.getContents();
				if(other1.trim().equals("")) {
					//不对
					info = "第" + j + "行其他选项1不能为空";
					return info;
				}
				
				//处理其他选项2
				Cell other2Cell = sheet.getCell(3,j);
				String other2 = other2Cell.getContents();
				if(other2.trim().equals("")) {
					//不对
					info = "第" + j + "行其他选项2填写错误";
					return info;
				}
				
				//处理其他选项3
				Cell other3Cell = sheet.getCell(4,j);
				String other3 = other3Cell.getContents();
				if(other3.trim().equals("")) {
					info = "第" + j + "行其他选项3填写错误";
					return info;
				}
				
				//处理难易程度
				Cell degreeCell = sheet.getCell(5,j);
				String degree = degreeCell.getContents();
				if(!degree.trim().equals("0") && !degree.trim().equals("1") && !degree.trim().equals("2")) {
					//不对
					info = "第" + j + "行难易程度填写错误";
					return info;
				}
				byte deg = Byte.parseByte(degree);
				
				//处理专业
				Cell majorCell = sheet.getCell(6,j);
				String major = majorCell.getContents();
				int m_id = 0;
				if(major.trim().equals("") || (m_id=this.majorService.getIdByName(major))== 0) {
					
					info = "第" + j + "行专业填写错误";
					return info;
				}
				
				//处理科目
				Cell subjectCell = sheet.getCell(7,j);
				String sub = subjectCell.getContents();
				int sub_id = 0;
				if(sub.trim().equals("") || (sub_id=this.subjectService.getIdByName(sub))== 0) {
					
					info = "第" + j + "行科目填写错误";
					return info;
				}
				
				//验证通过
				Major m = new Major();
				m.setM_id(m_id);
				Subject s = new Subject();
				s.setSub_id(sub_id);
				choiceQuestion.setCho_question(question);
				choiceQuestion.setCho_answer(answer);
				choiceQuestion.setCho_choice_1(other1);
				choiceQuestion.setCho_choice_2(other2);
				choiceQuestion.setCho_choice_3(other3);
				choiceQuestion.setDegree(deg);
				choiceQuestion.setMajor(m);
				choiceQuestion.setSubject(s);
				
				int id = this.choiceQuestionService.add(choiceQuestion);
				if(id <= 0) {
					//添加失败
					info = "第" + j + "个题目添加失败,请重试";
					return info;
				}
			}
			
			//添加完成
			return null;
			
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
		return "系统错误,请稍后再试!";
	}
	
	/**
	 * 导入判断题
	 * 题目	答案	难度 专业	所属科目
	 * @return 成功返回null,否则返回错误信息
	 */
	public String importJudgeQuestion() {
		//添加也得清空
				String info = "";
				//先清空判断题,先判断判断题是否有数据
						if(this.judgeQuestionService.hasData()) {
							this.judgeQuestionService.clear();
							if(this.judgeQuestionService.hasData()) {
								//没有清空表
								info = "历史判断题数据无法清空!请稍后再试" ;
								return info;
							}
						}
				//开始导入
				FileInputStream excel = null;
				Workbook wb =  null;
				HttpServletRequest req = ServletActionContext.getRequest();
				try {
					//得到Excel文件
					excel = new FileInputStream(file_upload);
					//获取工作簿对象
					wb = Workbook.getWorkbook(excel);//只读
					//开始对excel数据进行操作(行,列)
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
						JudgeQuestion judgeQuestion = new JudgeQuestion();
						 //处理题目
						Cell questionCell = sheet.getCell(0, j);
						String question = questionCell.getContents();
						if(question.trim().equals("")) {
							//不对
							info =  "第" + j + "行题目不能为空" ;
							return info;
						}
						
						//处理答案 0 , 1
						Cell answerCell = sheet.getCell(1,j);
						String answer = answerCell.getContents();
						if(!answer.trim().equals("T") && !answer.trim().equals("F")) {
							//不对
							info = "第" + j + "行答案格式错误";
							return info;
						}
						byte sub_answer = (byte) (answer.equals("T") ? 1 : 0);
						
						//处理难易程度
						Cell degreeCell = sheet.getCell(2,j);
						String degree = degreeCell.getContents();
						if(!degree.trim().equals("0") && !degree.trim().equals("1") && !degree.trim().equals("2")) {
							//不对
							info = "第" + j + "行难易程度填写错误";
							return info;
						}
						byte deg = Byte.parseByte(degree);
						
						//处理专业
						Cell majorCell = sheet.getCell(3,j);
						String major = majorCell.getContents();
						int m_id = 0;
						if(major.trim().equals("") || (m_id=this.majorService.getIdByName(major))== 0) {
							
							info = "第" + j + "行专业填写错误";
							return info;
						}
						
						//处理科目
						Cell subjectCell = sheet.getCell(4,j);
						String sub = subjectCell.getContents();
						int sub_id = 0;
						if(sub.trim().equals("") || (sub_id=this.subjectService.getIdByName(sub))== 0) {
							
							info = "第" + j + "行科目填写错误";
							return info;
						}
						
						//验证通过
						Major m = new Major();
						m.setM_id(m_id);
						Subject s = new Subject();
						s.setSub_id(sub_id);
						judgeQuestion.setJud_question(question);
						judgeQuestion.setJud_answer(sub_answer);
						judgeQuestion.setDegree(deg);
						judgeQuestion.setMajor(m);
						judgeQuestion.setSubject(s);
						
						int id = this.judgeQuestionService.add(judgeQuestion);
						if(id <= 0) {
							//添加失败
							info = "第" + j + "个题目添加失败,请重试";
							return info;
						}
					}
					
					//添加完成
					return null;
					
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
				return "系统错误,请稍后再试!";
	}
	
	/**
	 * 主观题导入
	 * 成功返回null,否则返回错误信息
	 * @return
	 */
	public String importSubjectiveQuestion() {
		//添加也得清空
		String info = "";
		//先清空判断题,先判断判断题是否有数据
				if(this.subjectiveQuestionService.hasData()) {
					this.subjectiveQuestionService.clear();
					if(this.subjectiveQuestionService.hasData()) {
						//没有清空表
						info = "历史主观题数据无法清空!请稍后再试" ;
						return info;
					}
				}
		//开始导入
		FileInputStream excel = null;
		Workbook wb =  null;
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			//得到Excel文件
			excel = new FileInputStream(file_upload);
			//获取工作簿对象
			wb = Workbook.getWorkbook(excel);//只读
			//开始对excel数据进行操作(行,列)
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
				SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion();
				 //处理题目
				Cell questionCell = sheet.getCell(0, j);
				String question = questionCell.getContents();
				if(question.trim().equals("")) {
					//不对
					info =  "第" + j + "行题目不能为空" ;
					return info;
				}
				
				//处理答案
				Cell answerCell = sheet.getCell(1,j);
				String answer = answerCell.getContents();
				if(answer.trim().equals("")) {
					//不对
					info = "第" + j + "行答案不能为空";
					return info;
				}
				
				//处理难易程度
				Cell degreeCell = sheet.getCell(2,j);
				String degree = degreeCell.getContents();
				if(!degree.trim().equals("0") && !degree.trim().equals("1") && !degree.trim().equals("2")) {
					//不对
					info = "第" + j + "行难易程度填写错误";
					return info;
				}
				byte deg = Byte.parseByte(degree);
				
				//处理类型
				Cell questionKindCell = sheet.getCell(2,j);
				String qKind = questionKindCell.getContents();
				if(!degree.trim().equals("0") && !degree.trim().equals("1") && !degree.trim().equals("2") && !degree.trim().equals("3") && !degree.trim().equals("4")) {
					//不对
					info = "第" + j + "行主观题类型填写错误";
					return info;
				}
				byte sq_kind = Byte.parseByte(qKind);
				
				//处理专业
				Cell majorCell = sheet.getCell(4,j);
				String major = majorCell.getContents();
				int m_id = 0;
				if(major.trim().equals("") || (m_id=this.majorService.getIdByName(major))== 0) {
					
					info = "第" + j + "行专业填写错误";
					return info;
				}
				
				//处理科目
				Cell subjectCell = sheet.getCell(5,j);
				String sub = subjectCell.getContents();
				int sub_id = 0;
				if(sub.trim().equals("") || (sub_id=this.subjectService.getIdByName(sub))== 0) {
					
					info = "第" + j + "行科目填写错误";
					return info;
				}
				
				//验证通过
				subjectiveQuestion.setSq_question(question);
				subjectiveQuestion.setSq_answer(answer);
				subjectiveQuestion.setDegree(deg);
				subjectiveQuestion.setSq_kind(sq_kind);
				subjectiveQuestion.getMajor().setM_id(m_id);
				subjectiveQuestion.getSubject().setSub_id(sub_id);
				
				int id = this.subjectiveQuestionService.add(subjectiveQuestion);
				if(id <= 0) {
					//添加失败
					info = "第" + j + "个题目添加失败,请重试";
					return info;
				}
			}
			
			//添加完成
			return null;
			
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
		return "系统错误,请稍后再试!";
	}
	
	public String downloadQuestionBank() {
		//开始导出
		switch (kind) {
		case 0 :
			//选择题
			exportChoiceQuestion();
			break;
		case 1 :
			//判断题
			exportJudgeQuestion();
			break;
		case 2 :
			//主观题
			exportSubjectiveQuestion();
			break;
		}
		
		//到这里说明导入失败
		return NONE;
	}
	
	public void exportChoiceQuestion() {
		//开始写入
		ServletOutputStream sos = null;
		WritableWorkbook wwk = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/octet-stream");
		String name = "选择题题库.xls";
		try {
			name = new String(name.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition","attachment;fileName=" + name);
		try {
			sos = response.getOutputStream();
			//创建一个可以写的工作簿
			wwk = Workbook.createWorkbook(sos);
			//创建可写入的工作表
			WritableSheet sheet = wwk.createSheet("选择题题库", 0);
			sheet.setColumnView(0, 100);//根据内容自动设置列宽
			sheet.setColumnView(1, 50);//根据内容自动设置列宽
			sheet.setColumnView(2, 50);//根据内容自动设置列宽
			sheet.setColumnView(3, 50);//根据内容自动设置列宽
			sheet.setColumnView(4, 50);
			sheet.setColumnView(5, 15);
			sheet.setColumnView(6, 20);
			sheet.setColumnView(7, 20);
			//先创建表头    列 行
			Label lab_00 = new Label(0, 0, "题目");
			Label lab_10 = new Label(1, 0, "答案项");
			Label lab_20 = new Label(2, 0, "其他选项1");
			Label lab_30 = new Label(3, 0, "其他选项2");
			Label lab_40 = new Label(4, 0, "其他选项3");
			Label lab_50 = new Label(5, 0, "难易程度");
			Label lab_60 = new Label(6, 0, "专业");
			Label lab_70 = new Label(7, 0, "科目");
			
			sheet.addCell(lab_00);
			sheet.addCell(lab_10);
			sheet.addCell(lab_20);
			sheet.addCell(lab_30);
			sheet.addCell(lab_40);
			sheet.addCell(lab_50);
			sheet.addCell(lab_60);
			sheet.addCell(lab_70);
			//开始写入表内容
			List<ChoiceQuestion> list = this.choiceQuestionService.getAll();
			ChoiceQuestion choiceQ = null;
			for(int i=1; i<=list.size(); i++) {
				 choiceQ = list.get(i-1);
				Label lab_0 = new Label(0, i, choiceQ.getCho_question());
				Label lab_1 = new Label(1, i, choiceQ.getCho_answer());
				Label lab_2 = new Label(2, i, choiceQ.getCho_choice_1());
				Label lab_3 = new Label(3, i, choiceQ.getCho_choice_2());
				Label lab_4 = new Label(4, i, choiceQ.getCho_choice_3());
				Label lab_5 = new Label(5, i, String.valueOf(choiceQ.getDegree()));
				Label lab_6 = new Label(6, i, choiceQ.getMajor().getM_name());
				Label lab_7 = new Label(7, i, choiceQ.getSubject().getSub_name());
				
				sheet.addCell(lab_0);
				sheet.addCell(lab_1);
				sheet.addCell(lab_2);
				sheet.addCell(lab_3);
				sheet.addCell(lab_4);
				sheet.addCell(lab_5);
				sheet.addCell(lab_6);
				sheet.addCell(lab_7);
				
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
						response.setHeader("Content-Disposition","attachment;fileName=导出选择题出错");
						sos.close();
						sos.flush();
					}
						
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void exportJudgeQuestion() {
		//开始写入
				ServletOutputStream sos = null;
				WritableWorkbook wwk = null;
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("application/octet-stream");
				String name = "判断题题库.xls";
				try {
					name = new String(name.getBytes("UTF-8"),"ISO-8859-1");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				response.setHeader("Content-Disposition","attachment;fileName=" + name);
				try {
					sos = response.getOutputStream();
					//创建一个可以写的工作簿
					wwk = Workbook.createWorkbook(sos);
					//创建可写入的工作表
					WritableSheet sheet = wwk.createSheet("判断题题库", 0);
					sheet.setColumnView(0, 100);//根据内容自动设置列宽
					sheet.setColumnView(1, 15);//根据内容自动设置列宽
					sheet.setColumnView(2, 15);//根据内容自动设置列宽
					sheet.setColumnView(3, 20);//根据内容自动设置列宽
					sheet.setColumnView(4, 20);
					//先创建表头    列 行
					Label lab_00 = new Label(0, 0, "题目");
					Label lab_10 = new Label(1, 0, "答案项");
					Label lab_20 = new Label(2, 0, "难易程度");
					Label lab_30 = new Label(3, 0, "专业");
					Label lab_40 = new Label(4, 0, "科目");
					
					sheet.addCell(lab_00);
					sheet.addCell(lab_10);
					sheet.addCell(lab_20);
					sheet.addCell(lab_30);
					sheet.addCell(lab_40);
					//开始写入表内容
					List<JudgeQuestion> list = this.judgeQuestionService.getAll();
					JudgeQuestion judgeQ = null;
					for(int i=1; i<=list.size(); i++) {
						judgeQ = list.get(i-1);
						Label lab_0 = new Label(0, i, judgeQ.getJud_question());
						Label lab_1 = new Label(1, i, judgeQ.getJud_answer() == 0 ? "F" : "T");
						Label lab_2 = new Label(2, i, String.valueOf(judgeQ.getDegree()));
						Label lab_3 = new Label(3, i, judgeQ.getMajor().getM_name());
						Label lab_4 = new Label(4, i, judgeQ.getSubject().getSub_name());
						
						sheet.addCell(lab_0);
						sheet.addCell(lab_1);
						sheet.addCell(lab_2);
						sheet.addCell(lab_3);
						sheet.addCell(lab_4);
						
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
								response.setHeader("Content-Disposition","attachment;fileName=导出判断题出错");
								sos.close();
								sos.flush();
							}
								
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (WriteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
	}
	
	public void exportSubjectiveQuestion(){
		//开始写入
		ServletOutputStream sos = null;
		WritableWorkbook wwk = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/octet-stream");
		String name = "主观题题库.xls";
		try {
			name = new String(name.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition","attachment;fileName=" + name);
		try {
			sos = response.getOutputStream();
			//创建一个可以写的工作簿
			wwk = Workbook.createWorkbook(sos);
			//创建可写入的工作表
			WritableSheet sheet = wwk.createSheet("主观题题库", 0);
			sheet.setColumnView(0, 100);//根据内容自动设置列宽
			sheet.setColumnView(1, 100);//根据内容自动设置列宽
			sheet.setColumnView(2, 15);//根据内容自动设置列宽
			sheet.setColumnView(3, 15);//根据内容自动设置列宽
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			//先创建表头    列 行
			Label lab_00 = new Label(0, 0, "题目内容");
			Label lab_10 = new Label(1, 0, "参考答案");
			Label lab_20 = new Label(2, 0, "难易程度");
			Label lab_30 = new Label(3, 0, "主观题类型");
			Label lab_40 = new Label(4, 0, "专业");
			Label lab_50 = new Label(5, 0, "科目");
			
			sheet.addCell(lab_00);
			sheet.addCell(lab_10);
			sheet.addCell(lab_20);
			sheet.addCell(lab_30);
			sheet.addCell(lab_40);
			sheet.addCell(lab_50);
			//开始写入表内容
			List<SubjectiveQuestion> list = this.subjectiveQuestionService.getAll();
			SubjectiveQuestion subjectiveQ = null;
			for(int i=1; i<=list.size(); i++) {
				subjectiveQ = list.get(i-1);
				Label lab_0 = new Label(0, i, subjectiveQ.getSq_question());
				Label lab_1 = new Label(1, i, subjectiveQ.getSq_answer());
				Label lab_2 = new Label(2, i, String.valueOf(subjectiveQ.getDegree()));
				Label lab_3 = new Label(3, i, String.valueOf(subjectiveQ.getSq_kind()));
				Label lab_4 = new Label(4, i, subjectiveQ.getMajor().getM_name());
				Label lab_5 = new Label(5, i, subjectiveQ.getSubject().getSub_name());
				
				sheet.addCell(lab_0);
				sheet.addCell(lab_1);
				sheet.addCell(lab_2);
				sheet.addCell(lab_3);
				sheet.addCell(lab_4);
				sheet.addCell(lab_5);
				
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
						response.setHeader("Content-Disposition","attachment;fileName=导入主观题出错");
						sos.close();
						sos.flush();
					}
						
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}


