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
	}  //����JSON����
	private Major major = new Major();
	@Override
	public Major getModel() {
		return major;
	} //ģ��������ȡ����
	
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
		//��������
		
		//���
		int id = this.majorService.add(major);
		if(id>0) {
			return SUCCESS;
		}else {
			return ERROR;
		}
		
	}
	
	public String delete() {
		//��֤����
		
		this.majorService.delete(this.major.getM_id());
		return SUCCESS;
	}
	
	public String edit() {
		Major m = this.majorService.getById(this.major.getM_id());
		List<Academy> list = this.academyService.getAll();
		
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setAttribute("major", m);
		req.setAttribute("list", list);
		return "edit";
	}
	
	public String update() {
		//��֤��Ϣ
		
		Major m = this.majorService.getById(this.major.getM_id());
		
		major.setCreate_time(m.getCreate_time());
		major.setM_num(m.getM_num());
		
		this.majorService.update(major);
		return SUCCESS;
	}

}
