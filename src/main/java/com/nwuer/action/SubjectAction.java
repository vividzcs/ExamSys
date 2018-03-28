package com.nwuer.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Major;
import com.nwuer.entity.Subject;
import com.nwuer.service.MajorService;
import com.nwuer.service.SubjectService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Scope("prototype")
public class SubjectAction extends ActionSupport implements ModelDriven<Subject> {
	private Subject subject = new Subject();
	@Override
	public Subject getModel() {
		return subject;
	}//模型驱动获取数据
	
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private MajorService majorService;
	
	public String delete() {
		//验证
		
		this.subjectService.delete(this.subject.getSub_id());
		return SUCCESS;
	}
	
	public String list() {
		List<Subject> list = this.subjectService.getAll();
		
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	public String showAdd() {
		List<Major> list = this.majorService.getAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "showAdd";
	}
	
	public String add() {
		//验证
		
		int id = this.subjectService.add(subject);
		if(id > 0) {
			//添加成功
			return SUCCESS;
		} else {
			//添加失败
			return ERROR;
		}
	}
}

