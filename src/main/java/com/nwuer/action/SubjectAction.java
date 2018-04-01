package com.nwuer.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Major;
import com.nwuer.entity.Subject;
import com.nwuer.service.MajorService;
import com.nwuer.service.SubjectService;
import com.nwuer.utils.ValidateUtil;
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
	String info;
	
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private ValidateUtil validateUtil;
	
	public String delete() {
		//验证
		Subject s = this.subjectService.getByIdEager(subject.getSub_id());
		if(s == null) {
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
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
		HttpServletRequest req = ServletActionContext.getRequest();
		info = validateUtil.validateNumber(subject.getSub_number(), 6);
		if(info != null) {
			req.setAttribute("info", info);
			return ERROR;
		}
		
		int id = this.subjectService.add(subject);
		if(id > 0) {
			//添加成功
			return SUCCESS;
		} else {
			//添加失败
			req.setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
	}
	
	public String edit() {
		Subject s = this.subjectService.getById(this.subject.getSub_id());
		if(s == null) {
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		List<Major> list = this.majorService.getAll();
		
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setAttribute("subject", s);
		req.setAttribute("list", list);
		return "edit";
	}
	
	public String update() {
		Subject s = this.subjectService.getById(subject.getSub_id());
		if(s == null) {
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		subject.setCreate_time(s.getCreate_time());
		
		this.subjectService.update(subject);
		return SUCCESS;
	}
}

