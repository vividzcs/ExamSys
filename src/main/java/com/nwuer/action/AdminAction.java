package com.nwuer.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Admin;
import com.nwuer.service.AdminService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {
	private Admin admin = new Admin();
	@Override
	public Admin getModel() {
		return admin;
	}  //ģ��������ȡ����
	@Autowired
	private AdminService adminService;
	
	private Map<String,String> result = new HashMap<String,String>();
	public Map<String, String> getResult() {
		return result;
	}
	public void setResult(Map<String, String> result) {
		this.result = result;
	}  //����JSON����



	public String login() {
		//��֤
		
		
		Admin adminConfirm = this.adminService.getByNumberAndPass(this.admin);
		if(adminConfirm != null) {
			//�ɹ�
			adminConfirm.setAd_pass(null);
			ServletActionContext.getRequest().getSession().setAttribute("admin", adminConfirm);
			this.result.put("status","1");
			this.result.put("msg", "��¼�ɹ�");
			return SUCCESS;
		}else {
			this.result.put("status","0");
			this.result.put("msg", "�û������������");
			return ERROR;
		}
			
		
	}

}
