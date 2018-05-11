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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nwuer.entity.Academy;
import com.nwuer.entity.GuardianShip;
import com.nwuer.entity.Paper;
import com.nwuer.entity.PaperRule;
import com.nwuer.entity.Student;
import com.nwuer.entity.StudentRegister;
import com.nwuer.entity.SubjectiveAnswer;
import com.nwuer.entity.Teacher;
import com.nwuer.service.AcademyService;
import com.nwuer.service.GuardianShipService;
import com.nwuer.service.PaperRuleService;
import com.nwuer.service.PaperService;
import com.nwuer.service.StudentRegisterService;
import com.nwuer.service.StudentService;
import com.nwuer.service.SubjectiveAnswerService;
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
public class TeacherAction extends ActionSupport implements ModelDriven<Teacher> {
	private Teacher teacher = new Teacher();
	@Override
	public Teacher getModel() {
		return teacher;
	}  //用模型驱动获取前端数据
	String info;
	
	private Map<String,String> result = new HashMap<String,String>();
	public Map<String,String> getResult() {
		return result;
	}
	public void setResult(Map<String,String> result) {
		this.result = result;
	} //传回前端的数据
	
	HttpServletRequest req = ServletActionContext.getRequest();
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private PaperService paperService;
	@Autowired
	private PaperRuleService paperRuleService;
	@Autowired
	private AcademyService academyService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private GuardianShipService guardianShipService;
	@Autowired
	private StudentRegisterService studentRegisterService;
	@Autowired
	private SubjectiveAnswerService subjectiveAnswerService;
	@Autowired
	private Crpty crpty;
	@Autowired
	private ValidateUtil validateUtil;
	
	/**
	 * 确认开始阅卷
	 * @return
	 */
	public String reviewConfirm() {
		//首先在监考信息表中查是否有资格阅卷
		Teacher t = (Teacher) req.getSession().getAttribute("teacher");
		List<GuardianShip> list = this.guardianShipService.getCanReviewByTid(t.getT_id());
		if(list==null || list.size()==0) {
			req.setAttribute("info", "没有阅卷信息");
			return ERROR;
		}
		return "before";
	}
	
	public String beginReview() {
		String mes = this.reviewConfirm();
		if(mes.equals("error")) {
			req.setAttribute("info", "系统错误");
		}
		String m_id = req.getParameter("m_id");
		String sub_id = req.getParameter("sub_id");
		if(this.validateUtil.isNumber(m_id)!=null || this.validateUtil.isNumber(sub_id)!=null) {
			req.setAttribute("info", "专业科目错误");
			return ERROR;
		}
		int mid = Integer.parseInt(m_id);
		int subid = Integer.parseInt(sub_id);
		//开始挑选试卷
		Teacher t = (Teacher) req.getSession().getAttribute("teacher");
		StudentRegister sr = this.studentRegisterService.getCanBeReviewed(mid,subid,t.getT_id());
		if(sr == null) {
			//可能已经阅完卷了
			if(this.studentRegisterService.isReviewDone(mid, subid)) {
				req.setAttribute("info", "此科目已经阅卷完毕");
				return "showSuccess";
			}else {
				req.setAttribute("info", "另一位老师正在阅最后一份试卷");
				return "showSuccess";
			}
		}else {
			sr.setT_id(t.getT_id());
			PaperRule rule = this.paperRuleService.getByIdEager(this.paperService.getByIdEager(sr.getPaper()).getP_id());
			this.studentRegisterService.update(sr);
			List<SubjectiveAnswer> list = this.subjectiveAnswerService.getByUuid(sr.getPaper());
			req.setAttribute("list", list);
			req.setAttribute("rule", rule);
			return "view";
		}
		
	}
	
	public String pushReview() {
		String uuid = req.getParameter("uuid");
		List<SubjectiveAnswer> list = this.subjectiveAnswerService.getByUuid(uuid);
		Paper p = this.paperService.getByIdEager(uuid);
		StudentRegister sr = this.studentRegisterService.getByUuid(uuid);
		if(p == null || list==null || list.size()==0 || sr==null) {
			req.setAttribute("info", "系统错误");
			return ERROR;
		}
		SubjectiveAnswer sAnswer = null;
		double sScore = 0.0;
		double grade = 0;
		String g = null;
		for(int i=0; i<list.size(); i++) {
			sAnswer = list.get(i);
			String name = "_"+ sAnswer.getKind()+ "_" +sAnswer.getSequence();
			g = req.getParameter(name);
			if(!this.validateUtil.isDouble(g)) {
				req.setAttribute("info", "分数错误");
				return ERROR;
			}
			grade = Double.parseDouble(req.getParameter(name));
			sScore += grade;
			sAnswer.setGrade(grade);
			this.subjectiveAnswerService.update(sAnswer);
		}
		sr.setGrade(sr.getGrade()+sScore);
		sr.setStatus(new Byte(5+""));
		this.studentRegisterService.update(sr);
		p.setStatus(new Byte(4+""));
		
		req.setAttribute("m_id", sr.getMajor().getM_id());
		req.setAttribute("sub_id", sr.getSubject().getSub_id());
		return "next";
		
		
	}
	
	/**
	 * 查找学生信息
	 */
	private String s_number;
	public String getS_number() {
		return s_number;
	}
	public void setS_number(String s_number) {
		this.s_number = s_number;
	}
	public String findStu() {
		//检查数据合法性
		info = validateUtil.isNumber(s_number);
		if(info != null) {
			req.setAttribute("info","学号"+ info);
			return ERROR;
		}
		
		List<Student> list = this.studentService.getByNumber(s_number);
		Student s = null;
		for(int i=0; i<list.size(); i++) {
			s = list.get(i);
			s.setS_pass(crpty.decrypt(s.getS_pass()));
		}
		
		req.setAttribute("list", list);
		return "find";
	}
	
	
	/**
	 * 更新教师信息
	 * @return
	 */
	public String update() {
		//验证信息
		HttpSession session = req.getSession();
		String passConfirm = req.getParameter("passConfirm");
		String oldPass = req.getParameter("oldPass");
		if(passConfirm!=null && oldPass!=null && passConfirm.equals(this.teacher.getT_pass())) {
			Teacher t = this.teacherService.getByIdEager(((Teacher) session.getAttribute("teacher")).getT_id());
			if(!t.getT_pass().equals(oldPass)) {
				req.setAttribute("info", "旧密码错误");
				return ERROR;
			}
			//旧密码正确
			t.setT_pass(teacher.getT_pass());
			this.teacherService.update(t);
			
			return SUCCESS;
		}
		
		req.setAttribute("info", "请认真填写重试");
		return ERROR;
	}
	
	public String delete() {
		//验证
		Teacher t = this.teacherService.getByIdEager(teacher.getT_id());
		if(t == null) {
			req.setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		try {
			this.teacherService.delete(this.teacher.getT_id());
		}catch(Exception e) {
			req.setAttribute("info", "请先清理相关的监考信息!");
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 管理员修改
	 * @return
	 */
	public String editA() {
		Teacher t = this.teacherService.getById(this.teacher.getT_id());
		List<Academy> list = this.academyService.getAll();
		
		req.setAttribute("teacher", t);
		req.setAttribute("list", list);
		return "edit";
	}
	
	/**
	 * 管理员修改
	 * @return
	 */
	public String updateA() {
		//验证信息
		info = validateUtil.validateNumber(teacher.getT_number(), 10);
		if(info != null) {
			req.setAttribute("info", info);
			return ERROR;
		}
		
		Teacher t = this.teacherService.getById(this.teacher.getT_id());
		teacher.setCreate_time(t.getCreate_time());
		teacher.setLast_login(t.getLast_login());
		teacher.setStatus(t.getStatus());
		
		this.teacherService.update(teacher);
		return SUCCESS;
	}
	
	
	
	public String logout() {
		req.getSession().setAttribute("teacher", null);
		return "logout";
	}
	
	/**
	 * 登录
	 * @return
	 */
	public String login() {
		//验证
		HttpSession session = req.getSession();
		//验证验证码
		String code = req.getParameter("code");
		String codeReal = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if(!codeReal.equalsIgnoreCase(code)) {
			result = new HashMap<String,String>();
			result.put("status","0");
			result.put("msg", "验证码错误");
			return ERROR;
		}
		
		info = validateUtil.validateNumber(teacher.getT_number(), 10);
		if(info != null) {
			req.setAttribute("info", info);
			return ERROR;
		}
		
		Teacher confirmTeacher = this.teacherService.getByNumberAndPass(teacher);
		if(confirmTeacher != null) {
			//登录成功
			this.teacher.setT_pass(null);
			this.teacherService.updateLastLogin(System.currentTimeMillis(),confirmTeacher.getT_id());
			session.setAttribute("teacher", confirmTeacher);
			result.put("status","1");
			result.put("msg", "登录成功");
			return SUCCESS;
		}else {
			result = new HashMap<String,String>();
			result.put("status","0");
			result.put("msg", "用户名或密码错误");
			return ERROR;
		}
		
	}
	private int a_id;  //会上传院系id根据院系id导入教师名单
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}//导入教师名单
	public String downloadTeacher() {
		Academy academy = academyService.getById(a_id);
		Set<Teacher> set = academy.getT_set();
		//开始写入
		ServletOutputStream sos = null;
		WritableWorkbook wwk = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/octet-stream");
		String a_name = academy.getA_name() + "教师名单.xls";
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
			WritableSheet sheet = wwk.createSheet("教师信息", 0);
			
			sheet.setColumnView(0, 15);//根据内容自动设置列宽
			sheet.setColumnView(1, 30);//根据内容自动设置列宽
			sheet.setColumnView(2, 12);//根据内容自动设置列宽
			sheet.setColumnView(4, 15);//根据内容自动设置列宽
			//先创建表头    列 行
			Label lab_00 = new Label(0, 0, "工号");
			Label lab_10 = new Label(1, 0, "密码");
			Label lab_20 = new Label(2, 0, "姓名");
			Label lab_30 = new Label(3, 0, "性别");
			Label lab_40 = new Label(4, 0, "院系");
			Label lab_50 = new Label(5, 0, "状态");
			sheet.addCell(lab_00);
			sheet.addCell(lab_10);
			sheet.addCell(lab_20);
			sheet.addCell(lab_30);
			sheet.addCell(lab_40);
			sheet.addCell(lab_50);
			//开始写入表内容
			Iterator<Teacher> iterator = set.iterator();
			int i=1;
			Teacher teacher = null;
			while(iterator.hasNext()) {
				teacher = iterator.next();
				Label lab_0 = new Label(0, i, teacher.getT_number());
				Label lab_1 = new Label(1, i, crpty.decrypt(teacher.getT_pass()));
				Label lab_2 = new Label(2, i, teacher.getT_name());
				String sex = teacher.getT_sex() == 1 ? "男" : "女";
				Label lab_3 = new Label(3, i, sex);
				Label lab_4 = new Label(4, i, teacher.getAcademy().getA_name());
				Label lab_5 = new Label(5, i, Byte.toString(teacher.getStatus()));
				
				sheet.addCell(lab_0);
				sheet.addCell(lab_1);
				sheet.addCell(lab_2);
				sheet.addCell(lab_3);
				sheet.addCell(lab_4);
				sheet.addCell(lab_5);
				
				i++;
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
	
	public String list() {
		//查询所有院系
		List<Academy> aca_list = this.academyService.getAll();
		//查询老师名单
		List<Teacher> tea_list = this.teacherService.getAll();
		
		
		req.setAttribute("aca_list", aca_list);
		req.setAttribute("tea_list", tea_list);
		return "list";
	}
	
	public String add() {
		info = validateUtil.validateNumber(teacher.getT_number(), 10);
		if(info != null) {
			req.setAttribute("info", info);
			return ERROR;
		}
		
		int id = this.teacherService.add(teacher);
		if(id > 0)
			return SUCCESS;
		else {
			req.setAttribute("info", "添加失败,请重试");
			return ERROR;
		}
			
	}
	
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
	/**
	 * 
	 * @return
	 */
	public String importTeacher() {
		//检查数据
		
		//开始导入
		FileInputStream excel = null;
		Workbook wb =  null;
		try {
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
				Teacher teacher = new Teacher();
				 //处理学号
				Cell numCell = sheet.getCell(0, j);
				String number = numCell.getContents();
				if(number.trim().length() != 10) {
					//不对
					req.setAttribute("info", "第" + (j+1) + "行工号有误");
					return ERROR;
				}
				//工号不能重复
				
				
				//处理密码
				Cell passCell = sheet.getCell(1,j);
				String pass = passCell.getContents();
				if(pass.trim().equals("")) {
					//不对
					req.setAttribute("info", "第" + (j+1) + "行密码不能为空");
					return ERROR;
				}

				//处理姓名
				Cell nameCell = sheet.getCell(2,j);
				String name = nameCell.getContents();
				if(name.trim().equals("")) {
					//不对
					req.setAttribute("info", "第" + (j+1) + "行姓名不能为空");
					return ERROR;
				}
				
				//处理性别
				Cell sexCell = sheet.getCell(3,j);
				String sex = sexCell.getContents();
				if(!sex.trim().equals("男") && !sex.trim().equals("女")) {
					//不对
					req.setAttribute("info", "第" + (j+1) + "行性别填写错误");
					return ERROR;
				}
				byte s_sex = (byte) (sex.trim().equals("男") ? 1 : 0);
				
				//处理院系
				Cell academyCell = sheet.getCell(4,j);
				String academy = academyCell.getContents();
				int a_id = 0;
				if(academy.trim().equals("") || (a_id = this.academyService.getIdByName(academy)) ==0 ) {
					req.setAttribute("info", "第" + (j+1) + "行院系填写错误");
					return ERROR;
				}
				
				/*//处理是否能登录
				Cell loginCell = sheet.getCell(5,j);
				String login = loginCell.getContents();
				if(!login.trim().equals("0") && !login.trim().equals("1")) {
					//不对
					req.setAttribute("info", "第" + (j+1) + "行登录状态填写错误");
					return ERROR;
				}
				byte status = Byte.parseByte(login);*/
				
				//验证通过
				Academy s_academy = new Academy();
				s_academy.setA_id(a_id);
				
				teacher.setT_name(name);
				teacher.setT_number(number);
				teacher.setT_pass(pass);
				teacher.setT_sex(s_sex);
//				teacher.setStatus(status);
				teacher.setAcademy(s_academy);
				int id = this.teacherService.add(teacher);
				if(id <= 0) {
					//添加失败
					info = "第" + (j+1) + "个学生添加失败,请重试";
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
		req.setAttribute("info", "添加失败,请重试");
		return ERROR;
	}
}
