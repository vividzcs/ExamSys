package com.nwuer.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Academy;
import com.nwuer.entity.Major;
import com.nwuer.service.AcademyService;
import com.nwuer.service.MajorService;
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
	
	@Autowired
	private MajorService majorService;
	@Autowired
	private AcademyService academyService;
	
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
		//检验数据
		
		//添加
		int id = this.majorService.add(major);
		if(id>0) {
			return SUCCESS;
		}else {
			return ERROR;
		}
		
	}

}
