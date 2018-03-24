package com.nwuer.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.School;
import com.nwuer.service.SchoolService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class SchoolAction extends ActionSupport implements ModelDriven<School> {
	private School school = new School();
	@Override
	public School getModel() {
		return school;
	} //ģ��������ȡ����
	
	@Autowired
	private SchoolService schoolService;
	
	/**
	 * չʾ�༭ѧУ��Ϣ
	 * @return
	 */
	public String edit() {
		School school = this.schoolService.get();
		ServletActionContext.getRequest().setAttribute("school", school);
		return "edit";
	}
	
	/**
	 * ����ѧУ��Ϣ
	 * @return
	 */
	public String update() {
		//��֤��Ϣ
		
		this.schoolService.update(school);
		return SUCCESS;
	}
	
}
