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
	} //模型驱动获取数据
	
	@Autowired
	private AcademyService academyService;
	
	/**
	 * 展示添加院系
	 * @return
	 */
	
	public String list() {
		List<Academy> list = this.academyService.getAllByTimeDesc();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	/**
	 * 添加院系
	 * @return
	 */
	public String add() {
		//验证
		try {
			this.academyService.add(academy);
		}catch(Exception e) {
			String info = "此院系下还有其他专业,请先清理此院系下的专业";
			
		}
		
		return SUCCESS;
	}
	
	public String delete() {
		//验证
		
		this.academyService.delete(this.academy.getA_id());
		return SUCCESS;
	}
	
}
