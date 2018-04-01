package com.nwuer.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Academy;
import com.nwuer.service.AcademyService;
import com.nwuer.utils.ValidateUtil;
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
	
	String info;
	
	@Autowired
	private AcademyService academyService;
	@Autowired
	private ValidateUtil validateUtil; 
	
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
		//验证   a_number
		info = validateUtil.validateNumber(this.academy.getA_number(),10 );
		HttpServletRequest req = ServletActionContext.getRequest();
		if(info != null) {
			req.setAttribute("info", "编号"+info);
			return ERROR;
		}
			
		
		try {
			this.academyService.add(academy);
		}catch(Exception e) {
			info = "此院系下还有其他专业,请先清理此院系下的专业";
			req.setAttribute("info", info);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String delete() {
		//从数据库验证是否存在验证 
		Academy a = this.academyService.getByIdEager(this.academy.getA_id());
		if(a == null) {
			info = "系统错误,请刷新重试或联系维护人员";
			ServletActionContext.getRequest().setAttribute("info", info);
			return ERROR;
		}
		
		this.academyService.delete(this.academy.getA_id());
		return SUCCESS;
	}
	
	/**
	 * 显示修改院系
	 * @return
	 */
	public String edit() {
		Academy a = this.academyService.getById(this.academy.getA_id());
		ServletActionContext.getRequest().setAttribute("academy", a);
		return "edit";
	}
	/**
	 * 修改院系
	 * @return
	 */
	public String update() {
		//验证
		info = validateUtil.validateNumber(this.academy.getA_number(),10 );
		HttpServletRequest req = ServletActionContext.getRequest();
		if(info != null) {
			req.setAttribute("info", "编号"+info);
			return ERROR;
		}
		
		Academy a = this.academyService.getById(this.academy.getA_id());
		a.setA_name(academy.getA_name());
		a.setA_number(academy.getA_number());
		this.academyService.update(a);
		return SUCCESS;
	}
	
}
