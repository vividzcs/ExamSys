package com.nwuer.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.School;
import com.nwuer.service.SchoolService;
import com.nwuer.utils.ValidateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class SchoolAction extends ActionSupport implements ModelDriven<School> {
	private School school = new School();
	@Override
	public School getModel() {
		return school;
	} //模型驱动获取数据
	String info;
	HttpServletRequest req = ServletActionContext.getRequest();
	
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private ValidateUtil validateUtil;
	
	/**
	 * 展示编辑学校信息
	 * @return
	 */
	public String edit() {
		School school = this.schoolService.get();
		req.setAttribute("school", school);
		return "edit";
	}
	
	/**
	 * 更新学校信息
	 * @return
	 */
	public String update() {
		//验证信息 sch_number
		info = validateUtil.validateNumber(school.getSch_number(), 5);
		if(info != null) {
			req.setAttribute("info", "学校编号"+info);
			return ERROR;
		}
		//sch_aca_count
		info = validateUtil.isNumber(school.getSch_number());
		if(info != null) {
			req.setAttribute("info", "院系数"+info);
			return ERROR;
		}
		//sch_major_count
		info = validateUtil.isNumber(school.getSch_number());
		if(info != null) {
			req.setAttribute("info", "专业数"+info);
			return ERROR;
		}
		
		this.schoolService.update(school);
		return SUCCESS;
	}
	
	public String sMes() {
		School s = this.schoolService.get();
		req.setAttribute("school", s);
		return "showSuccess";
	}
	
}
