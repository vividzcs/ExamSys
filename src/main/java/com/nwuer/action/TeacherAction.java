package com.nwuer.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Academy;
import com.nwuer.entity.Teacher;
import com.nwuer.service.AcademyService;
import com.nwuer.service.TeacherService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class TeacherAction extends ActionSupport implements ModelDriven<Teacher> {
	private Teacher teacher = new Teacher();
	@Override
	public Teacher getModel() {
		return teacher;
	}  //��ģ��������ȡǰ������
	
	private Map<String,String> result = new HashMap<String,String>();
	public Map<String,String> getResult() {
		return result;
	}
	public void setResult(Map<String,String> result) {
		this.result = result;
	} //����ǰ�˵�����
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private AcademyService academyService;
	
	public String login() {
		//��֤
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//��֤��֤��
		String code = request.getParameter("code");
		String codeReal = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if(!codeReal.equalsIgnoreCase(code)) {
			result = new HashMap<String,String>();
			result.put("status","0");
			result.put("msg", "��֤�����");
			return ERROR;
		}
		
		Teacher confirmTeacher = this.teacherService.getByNumberAndPass(teacher);
		if(confirmTeacher != null) {
			//��¼�ɹ�
			confirmTeacher.setT_pass(null);
			session.setAttribute("teacher", confirmTeacher);
			result.put("status","1");
			result.put("msg", "��¼�ɹ�");
			return SUCCESS;
		}else {
			result = new HashMap<String,String>();
			result.put("status","0");
			result.put("msg", "�û������������");
			return ERROR;
		}
		
	}
	
	public String showAdd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//��ѯ����Ժϵ
		List<Academy> aca_list = this.academyService.getAll();
		//��ѯ��ʦ����
		
		request.setAttribute("aca_list", aca_list);
		return "showAdd";
	}
	
	public String add() {
		int id = this.teacherService.add(teacher);
		if(id > 0)
			return SUCCESS;
		else
			return ERROR;
	}
}
