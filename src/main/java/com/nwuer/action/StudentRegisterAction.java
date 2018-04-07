package com.nwuer.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.GuardianShip;
import com.nwuer.entity.StudentRegister;
import com.nwuer.service.GuardianShipService;
import com.nwuer.service.StudentRegisterService;
import com.nwuer.service.StudentService;
import com.nwuer.utils.ValidateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class StudentRegisterAction extends ActionSupport implements ModelDriven<StudentRegister> {
	private StudentRegister studentRegister = new StudentRegister();
	@Override
	public StudentRegister getModel() {
		return studentRegister;
	}
	String info;
	
	@Autowired
	private GuardianShipService guardianShipService;
	@Autowired
	private StudentRegisterService studentRegisterService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ValidateUtil validateUtil;
	
	
	
	public String list() {
		List<StudentRegister> sList = this.studentRegisterService.getAll();
		StudentRegister stu = null;
		GuardianShip guard = null;
		
		for(int i=0; i<sList.size(); i++) {
			stu = sList.get(i);
			stu.setGuardianShip(this.guardianShipService.getGuardByMajorAndSubject(stu.getMajor().getM_id(), stu.getSubject().getSub_id()));
		}
		ServletActionContext.getRequest().setAttribute("list", sList);
		return "list";
	}
	
	public String add() {
		HttpServletRequest req = ServletActionContext.getRequest();
		info = validateUtil.validateNumber(this.studentRegister.getSr_number(), 10);
		if(info!=null) {
			req.setAttribute("info", "学号"+info);
			return ERROR;
		}
		String sr_name = this.studentService.getNameByNumber(this.studentRegister.getSr_number());
		if(sr_name == null) {
			req.setAttribute("info", "学号错误");
			return ERROR;
		}
		this.studentRegister.setSr_name(sr_name);
				
		int id = this.studentRegisterService.add(this.studentRegister);
		if(id<=0) {
			req.setAttribute("info", "添加失败,请重试!");
			return ERROR;
		}
		return SUCCESS;
	}
}
