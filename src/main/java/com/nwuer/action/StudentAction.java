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
	}  //ģ��������ȡ����
	
	private Map result = new HashMap();
	public Map getResult() {
		return result;
	}
	public void setResult(Map result) {
		this.result = result;
	} //����ǰ�˵�Json����
	@Autowired
	private StudentService studentService;
	@Autowired
	private AcademyService academyService; 
	@Autowired
	private MajorService majorService;
	

	/**
	 * ��¼
	 * @return
	 */
	public String login() {
		//��֤
		
		
		Student studentConfirm = this.studentService.getByNumberAndPass(student);
		if(studentConfirm != null) {
			//��¼�ɹ�
			studentConfirm.setS_pass(null);
			ServletActionContext.getRequest().getSession().setAttribute("student", studentConfirm);
			
			this.result.put("status", "1");
			this.result.put("msg", "��¼�ɹ�");
			return SUCCESS;
		}else {
			this.result.put("status", "0");
			this.result.put("msg", "�û������������");
			return ERROR;
		}
		
	}
	
	/**
	 * ���ѧ��
	 * @return
	 */
	public String add() {
		//��֤
		
		int id = (int) this.studentService.add(student);
		if(id > 0) {
			//��ӳɹ�
			return SUCCESS;
		} else {
			//���ʧ��
			return ERROR;
		}
	}
	
	public String delete() {
		
		return SUCCESS;
	}
}
