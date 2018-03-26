package com.nwuer.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Academy;
import com.nwuer.service.AcademyService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class AcademyAction extends ActionSupport implements ModelDriven<Academy> {
	private Academy academy = new Academy();
	@Override
	public Academy getModel() {
		return academy;
	} //ģ��������ȡ����
	
	@Autowired
	private AcademyService academyService;
	
	/**
	 * չʾ���Ժϵ
	 * @return
	 */
	
	public String list() {
		List<Academy> list = this.academyService.getAllByTimeDesc();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	/**
	 * ���Ժϵ
	 * @return
	 */
	public String add() {
		//��֤
		try {
			this.academyService.add(academy);
		}catch(Exception e) {
			String info = "��Ժϵ�»�������רҵ,���������Ժϵ�µ�רҵ";
			
		}
		
		return SUCCESS;
	}
	
	public String delete() {
		//��֤
		
		this.academyService.delete(this.academy.getA_id());
		return SUCCESS;
	}
	
}
