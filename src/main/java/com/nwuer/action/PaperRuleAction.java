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
	} //模型驱动获取数据
	
	@Autowired
	private PaperRuleService paperRuleService;
	
	
	public String add() {
		//验证信息
		
		int id = this.paperRuleService.add(paperRule);
		if(id > 0) {
			//添加成功
			return SUCCESS;
		} else {
			//添加失败
			return ERROR;
		}
	}
	
	public String list() {
		List<PaperRule> list = this.paperRuleService.getAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	public String delete() {
		//验证id信息
		
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
