package com.nwuer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nwuer.entity.Academy;
import com.nwuer.entity.Admin;
import com.nwuer.entity.Major;
import com.nwuer.entity.Student;
import com.nwuer.service.AcademyService;
import com.nwuer.service.AdminService;
import com.nwuer.service.MajorService;
import com.nwuer.service.StudentService;
import com.nwuer.service.TeacherService;
import com.nwuer.utils.Crpty;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
@Controller
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {
	private Admin admin = new Admin();
	@Override
	public Admin getModel() {
		return admin;
	}  //模型驱动获取数据
	@Autowired
	private AdminService adminService;
	@Autowired
	private AcademyService academyService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
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
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		Crpty crpty = (Crpty) applicationContext.getBean("crpty");
		//修改
		String pass = crpty.encrypt(admin.getAd_pass());
		
		newAdmin.setAd_pass(pass);
		newAdmin.setAd_name(admin.getAd_name());
		this.adminService.update(newAdmin);
		this.result.put("status","1");
		this.result.put("msg", "修改成功");
		return SUCCESS;
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
		
		Admin adminConfirm = this.adminService.getByNumberAndPass(this.admin);
		if(adminConfirm != null) {
			//成功
			adminConfirm.setAd_pass(null);
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
	
	public String importStudent() {
		//先清空Student表,先判断学生表是否有数据
		if(this.studentService.hasData()) {
			this.studentService.clear();
			if(this.studentService.hasData()) {
				//没有清空表
				result.put("status", "0");
				result.put("msg", "历史学生数据无法清空!请稍后再试" );
				return ERROR;
			}
		}
		
		FileInputStream excel = null;
		Workbook wb =  null;
		try {
			ApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
			//事务开始
			File f = new File(ServletActionContext.getServletContext().getResource("/modellist/studentListTemplate.xls").getPath());
			//得到Excel文件
			excel = new FileInputStream(f);
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
					result.put("status", "0");
					result.put("msg", "第" + j + "行学号有误" );
					return ERROR;
				}
				//处理密码
				Cell passCell = sheet.getCell(1,j);
				String pass = passCell.getContents();
				if(pass.trim().equals("")) {
					//不对
					result.put("status", "0");
					result.put("msg", "第" + j + "行密码不能为空" );
					return ERROR;
				}
				//md5加密
				Crpty md5 = (Crpty) application.getBean("mD5Util");
				pass = md5.encrypt(pass);
				
				//处理姓名
				Cell nameCell = sheet.getCell(2,j);
				String name = nameCell.getContents();
				if(name.trim().equals("")) {
					//不对
					result.put("status", "0");
					result.put("msg", "第" + j + "行姓名不能为空" );
					return ERROR;
				}
				
				//处理性别
				Cell sexCell = sheet.getCell(3,j);
				String sex = sexCell.getContents();
				if(!sex.trim().equals("男") && !sex.trim().equals("女")) {
					//不对
					result.put("status", "0");
					result.put("msg", "第" + j + "行性别填写错误" );
					return ERROR;
				}
				byte s_sex = (byte) (sex.trim().equals("男") ? 1 : 0);
				
				//处理院系
				Cell academyCell = sheet.getCell(4,j);
				String academy = academyCell.getContents();
				int a_id = 0;
				if(academy.trim().equals("") || (a_id = this.academyService.getIdByName(academy)) ==0 ) {
					result.put("status", "0");
					result.put("msg", "第" + j + "行院系填写错误" );
					return ERROR;
				}
				
				//处理专业
				Cell majorCell = sheet.getCell(5,j);
				String major = majorCell.getContents();
				int m_id = 0;
				if(major.trim().equals("") || (m_id=this.majorService.getIdByName(major))== 0) {
					
					result.put("status", "0");
					result.put("msg", "第" + j + "行专业填写错误" );
					return ERROR;
				}
				
				//处理是否能登录
				Cell loginCell = sheet.getCell(6,j);
				String login = loginCell.getContents();
				if(!login.trim().equals("0") && !login.trim().equals("1")) {
					//不对
					result.put("status", "0");
					result.put("msg", "第" + j + "行登录状态填写错误" );
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
					result.put("status", "0");
					result.put("msg", "第" + j + "个学生添加失败,请重试" );
					return ERROR;
				}
			}
			
			//添加完成
			result.put("status", "1");
			result.put("msg", "添加完成" );
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
		return ERROR;
	}
	public String mes() {
		List<Academy> academys = this.academyService.getAll();
		
		/*{
	      	department:'信息科学与技术学院',
	      	profess:['软件工程','计算机科学与技术','电子信息','通信工程']
	      },*/
		Set<Major> majors = null;
		for(int i=0; i<academys.size(); i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("department", academys.get(i).getA_name());
			List<String> profess = new ArrayList<String>();
			majors = academys.get(i).getM_set();
			Iterator<Major> it = majors.iterator();
			while (it.hasNext()) {
				profess.add(it.next().getM_name());
			}
			//
			map.put("profess", profess);
			this.result.put(i, map);
		}
		return SUCCESS;
	}
}

