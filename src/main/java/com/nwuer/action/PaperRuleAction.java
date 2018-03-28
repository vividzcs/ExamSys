package com.nwuer.action;

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
	private PaperRule paperRule;
	@Override
	public PaperRule getModel() {
		return paperRule;
	} //ģ��������ȡ����
	
	@Autowired
	private PaperRuleService paperRuleService;
	
	
	public String add() {
		return SUCCESS;
	}
}
