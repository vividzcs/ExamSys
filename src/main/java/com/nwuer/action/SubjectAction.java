package com.nwuer.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Subject;
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
}
