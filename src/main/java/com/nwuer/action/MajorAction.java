package com.nwuer.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Academy;
import com.nwuer.entity.Major;
import com.nwuer.service.AcademyService;
import com.nwuer.service.MajorService;
import com.nwuer.utils.ValidateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class MajorAction extends ActionSupport implements ModelDriven<Major> {
	private Map<String,String> result = new HashMap<String,String>();
	public Map<String, String> getResult() {
		return result;
	}
	public void setResult(Map<String, String> result) {
		this.result = result;
	}  //返回JSON数据
	private Major major = new Major();
	@Override
	public Major getModel() {
		return major;
	} //模型驱动获取数据
	
	String info; 
	
	@Autowired
	private MajorService majorService;
	@Autowired
	private AcademyService academyService;
	@Autowired
	private ValidateUtil validateUtil;
	
	public String showAdd() {
		List<Academy> list = this.academyService.getAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "showAdd";
	}
	
	public String list() {
		List<Major> list = this.majorService.getAllByTimeDesc();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	public String add() {
		//检验数据  m_number
		HttpServletRequest req = ServletActionContext.getRequest();
		info = validateUtil.validateNumber(major.getM_number(), 4);
		if(info != null) {
			req.setAttribute("info", "专业" +info);
			return ERROR;
		}
		
		//添加
		int id = this.majorService.add(major);
		if(id>0) {
			return SUCCESS;
		}else {
			req.setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		
	}
	
	public String delete() {
		//验证数据
		Major m = this.majorService.getByIdEager(major.getM_id());
		if(m == null) {
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		this.majorService.delete(this.major.getM_id());
		return SUCCESS;
	}
	
	public String edit() {
		Major m = this.majorService.getById(this.major.getM_id());
		if(m == null) {
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		List<Academy> list = this.academyService.getAll();
		
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setAttribute("major", m);
		req.setAttribute("list", list);
		return "edit";
	}
	
	public String update() {
		//验证信息
		
		Major m = this.majorService.getById(this.major.getM_id());
		if(m == null) {
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		
		major.setCreate_time(m.getCreate_time());
		major.setM_num(m.getM_num());
		
		this.majorService.update(major);
		return SUCCESS;
	}

}
