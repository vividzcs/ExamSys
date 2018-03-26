package com.nwuer.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Subject;
import com.nwuer.service.SubjectService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Scope("prototype")
public class SubjectAction extends ActionSupport implements ModelDriven<Subject> {
	private Subject subject;
	@Override
	public Subject getModel() {
		return subject;
	}//模型驱动获取数据
	
	@Autowired
	private SubjectService subjectService;
	
	public String delete() {
		//验证
		
		this.subjectService.delete(this.subject.getSub_id());
		return SUCCESS;
	}
	
	public String list() {
		List<Subject> list = this.subjectService.getAllByTimeDesc();
		
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
}

