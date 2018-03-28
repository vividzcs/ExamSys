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
	}//ģ��������ȡ����
	
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private MajorService majorService;
	
	public String delete() {
		//��֤
		
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
		//��֤
		
		int id = this.subjectService.add(subject);
		if(id > 0) {
			//��ӳɹ�
			return SUCCESS;
		} else {
			//���ʧ��
			return ERROR;
		}
	}
}

