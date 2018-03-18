package com.nwuer.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Teacher;
import com.nwuer.service.TeacherService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value="prototype")
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
	
	public String login() {
		//��֤
		
		
		Teacher confirmTeacher = this.teacherService.getByNumberAndPass(teacher);
		if(confirmTeacher != null) {
			//��¼�ɹ�
			confirmTeacher.setT_pass(null);
			ServletActionContext.getRequest().getSession().setAttribute("teacher", confirmTeacher);
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

}
