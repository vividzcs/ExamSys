package com.nwuer.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.nwuer.entity.Teacher;
import com.nwuer.service.TeacherService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
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

	private TeacherService teacherService;
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
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
