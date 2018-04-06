package com.nwuer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.nwuer.entity.Student;
import com.nwuer.entity.StudentRegister;
import com.nwuer.entity.Subject;
import com.nwuer.service.AcademyService;
import com.nwuer.service.MajorService;
import com.nwuer.service.StudentRegisterService;
import com.nwuer.service.StudentService;
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
	
	private Map<String,String> result = new HashMap<String,String>();
	public Map<String,String> getResult() {
		return result;
	}
	public void setResult(Map<String,String> result) {
		this.result = result;
	} //传回前端得Json数据
	@Autowired
	private StudentService studentService;
	@Autowired
	private AcademyService academyService; 
	@Autowired
	private MajorService majorService;
	@Autowired
	private StudentRegisterService studentRegisterService;
	@Autowired
	private Crpty crpty;
	@Autowired
	private ValidateUtil validateUtil;

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
		HttpServletRequest req = ServletActionContext.getRequest();
		
		info = this.validateUtil.validateNumber(this.student.getS_number(), 10);
		if(info!= null) {
			req.setAttribute("info", "学号"+info);
			return ERROR;
		}
		
		
		Student s = this.studentService.getByNumberAndPass(this.student);
		if(s != null) {
			return "before";
		}else {
			req.setAttribute("info", "学号或密码错误");
			return ERROR;
		}
		
	}
	
	/**
	 * 得到可以注册的科目
	 * @return
	 */
	public String getSubjects() {
		Student s = (Student) ServletActionContext.getRequest().getSession().getAttribute("student");
		List<StudentRegister> list = this.studentRegisterService.getStudentRegisterByNumber(s.getS_number());
		for(int i=0; i<list.size(); i++) {
			Subject sub = list.get(i).getSubject();
			this.result.put(String.valueOf(sub.getSub_id()), sub.getSub_name());
		}
		return "subs";
	}
	
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
