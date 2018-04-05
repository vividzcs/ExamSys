package com.nwuer.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.PaperRule;
import com.nwuer.service.PaperRuleService;
import com.nwuer.utils.ValidateUtil;
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
	String info;
	
	@Autowired
	private PaperRuleService paperRuleService;
	@Autowired
	public ValidateUtil validateUtil;
	
	
	public String add() {
		//验证信息
		HttpServletRequest req = ServletActionContext.getRequest();
		String start = req.getParameter("beginTime");
		String end = req.getParameter("endTime");
		//检查数据
		info = this.validateUtil.isDate(start);
		if(info!=null) {
			req.setAttribute("info", "日期填写错误,请重试!");
			return ERROR;
		}
		info = this.validateUtil.isDate(end);
		if(info!=null) {
			req.setAttribute("info", "日期填写错误,请重试!");
			return ERROR;
		}
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			Date date = format.parse(start);
			long start_time = date.getTime();
			long create_time = System.currentTimeMillis();
			//检查开始时间
			if(start_time<create_time) {
				req.setAttribute("info", "日期填写错误,请重试!");
				return ERROR;
			}
			paperRule.setStart_time(start_time);
			date = format.parse(end);
			long end_time = date.getTime();
			//检查结束时间
			if(end_time<start_time || end_time<create_time) {
				req.setAttribute("info", "日期填写错误,请重试!");
				return ERROR;
			}
			paperRule.setEnd_time(end_time);
			
		} catch (ParseException e) {
			e.printStackTrace();
			req.setAttribute("info", "日期格式错误,请重试!");
			return ERROR;
		}
		int id = this.paperRuleService.add(paperRule);
		if(id > 0) {
			//添加成功
			return SUCCESS;
		} else {
			//添加失败
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
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
		PaperRule p = this.paperRuleService.getByIdEager(paperRule.getP_id());
		if(p == null) {
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		this.paperRuleService.delete(this.paperRule.getP_id());
		return SUCCESS;
	}
	
	public String edit() {
		PaperRule pr = this.paperRuleService.getById(paperRule.getP_id());
		if(pr == null) {
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		ServletActionContext.getRequest().setAttribute("rule", pr);
		return "edit";
	}
	
	public String update() {
		return SUCCESS;
	}
	
	public String detail() {
		PaperRule rule = this.paperRuleService.getById(paperRule.getP_id());
		if(rule == null) {
			ServletActionContext.getRequest().setAttribute("info", "无此信息,请重试或联系维护人员");
			return ERROR;
		}
		ServletActionContext.getRequest().setAttribute("rule", rule);
		return "detail";
	}
}
