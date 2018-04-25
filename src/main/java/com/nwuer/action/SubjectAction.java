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
	HttpServletRequest req = ServletActionContext.getRequest();
	
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
			req.setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		try {
			this.subjectService.delete(this.subject.getSub_id());
		}catch(Exception e) {
			req.setAttribute("info", "还有与此科目相关的题库,请先清理相关的题库");
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String list() {
		List<Subject> list = this.subjectService.getAll();
		
		req.setAttribute("list", list);
		return "list";
	}
	
	public String showAdd() {
		List<Major> list = this.majorService.getAll();
		req.setAttribute("list", list);
		return "showAdd";
	}
	
	public String add() {
		//验证
		info = validateUtil.validateNumber(subject.getSub_number(), 6);
		if(info != null || subject.getMajor().getM_id() == 0) {
			req.setAttribute("info", info == null ? "专业未填":info);
			return ERROR;
		}
		try {
			int id = this.subjectService.add(subject);
			if(id > 0) {
				//添加成功
				return SUCCESS;
			} else {
				//添加失败
				req.setAttribute("info", "系统错误,请稍后重试");
				return ERROR;
			}
		}catch(Exception e) {
			req.setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		
	}
	
	public String edit() {
		Subject s = this.subjectService.getById(this.subject.getSub_id());
		if(s == null) {
			req.setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		List<Major> list = this.majorService.getAll();
		
		req.setAttribute("subject", s);
		req.setAttribute("list", list);
		return "edit";
	}
	
	public String update() {
		Subject s = this.subjectService.getById(subject.getSub_id());
		if(s == null) {
			req.setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		subject.setCreate_time(s.getCreate_time());
		
		try {
			this.subjectService.update(subject);
			return SUCCESS;
		}catch(Exception e) {
			req.setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		
	}
}

