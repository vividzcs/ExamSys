package com.nwuer.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.PaperRule;
import com.nwuer.service.PaperRuleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Scope("prototype")
public class PaperRuleAction extends ActionSupport implements ModelDriven<PaperRule> {
	private PaperRule paperRule = new PaperRule();
	@Override
	public PaperRule getModel() {
		return paperRule;
	} //ģ��������ȡ����
	
	@Autowired
	private PaperRuleService paperRuleService;
	
	
	public String add() {
		//��֤��Ϣ
		
		int id = this.paperRuleService.add(paperRule);
		if(id > 0) {
			//��ӳɹ�
			return SUCCESS;
		} else {
			//���ʧ��
			return ERROR;
		}
	}
	
	public String list() {
		List<PaperRule> list = this.paperRuleService.getAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	public String delete() {
		//��֤id��Ϣ
		
		this.paperRuleService.delete(this.paperRule.getP_id());
		return SUCCESS;
	}
	
	public String edit() {
		PaperRule pr = this.paperRuleService.getById(paperRule.getP_id());
		
		ServletActionContext.getRequest().setAttribute("rule", pr);
		return "edit";
	}
	
	public String update() {
		
		return SUCCESS;
	}
}
