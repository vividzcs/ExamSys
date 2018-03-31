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
	} //模型驱动获取数据
	
	@Autowired
	private SchoolService schoolService;
	
	/**
	 * 展示编辑学校信息
	 * @return
	 */
	public String edit() {
		School school = this.schoolService.get();
		ServletActionContext.getRequest().setAttribute("school", school);
		return "edit";
	}
	
	/**
	 * 更新学校信息
	 * @return
	 */
	public String update() {
		//验证信息
		
		this.schoolService.update(school);
		return SUCCESS;
	}
	
}
