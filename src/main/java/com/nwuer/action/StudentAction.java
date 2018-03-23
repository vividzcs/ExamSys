package com.nwuer.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Academy;
import com.nwuer.entity.Major;
import com.nwuer.entity.Student;
import com.nwuer.service.AcademyService;
import com.nwuer.service.MajorService;
import com.nwuer.service.StudentService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class StudentAction extends ActionSupport implements ModelDriven<Student> {
	private Student student = new Student();
	@Override
	public Student getModel() {
		return student;
	}  //模型驱动获取数据
	
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
	private AcademyService academyService; 
	@Autowired
	private MajorService majorService;
	

	/**
	 * 登录
	 * @return
	 */
	public String login() {
		//验证
		
		
		Student studentConfirm = this.studentService.getByNumberAndPass(student);
		if(studentConfirm != null) {
			//登录成功
			studentConfirm.setS_pass(null);
			ServletActionContext.getRequest().getSession().setAttribute("student", studentConfirm);
			
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
	 * 添加学生
	 * @return
	 */
	public String add() {
		//验证
		
		int id = (int) this.studentService.add(student);
		if(id > 0) {
			//添加成功
			return SUCCESS;
		} else {
			//添加失败
			return ERROR;
		}
	}
	
	public String delete() {
		
		return SUCCESS;
	}
}
