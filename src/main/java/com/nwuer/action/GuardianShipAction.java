package com.nwuer.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.ExamInfo;
import com.nwuer.entity.GuardianShip;
import com.nwuer.service.ExamInfoService;
import com.nwuer.service.GuardianShipService;
import com.nwuer.service.TeacherService;
import com.nwuer.utils.ValidateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class GuardianShipAction extends ActionSupport implements ModelDriven<GuardianShip> {
	private GuardianShip guardianShip = new GuardianShip();
	@Override
	public GuardianShip getModel() {
		return guardianShip;
	} //模型驱动获取数据
	
	@Autowired
	private GuardianShipService guardianShipService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ExamInfoService examInfoService;
	@Autowired
	private ValidateUtil validateUtil;
	
	public String add() {
		//验证
		
		HttpServletRequest req = ServletActionContext.getRequest();
		String mes1 = validateUtil.validateNumber(this.guardianShip.getGuard_1().getT_number(), 10);
		String mes2 = validateUtil.validateNumber(this.guardianShip.getGuard_2().getT_number(), 10);
		String mes3 = validateUtil.validateNumber(this.guardianShip.getRead_1().getT_number(), 10);
		String mes4 = validateUtil.validateNumber(this.guardianShip.getRead_2().getT_number(), 10);
		String mes5 = validateUtil.validateNumber(this.guardianShip.getRead_3().getT_number(), 10);
		if(mes1!=null || mes2!=null || mes3!=null || mes4!=null || mes5!=null) {
			req.setAttribute("info", "工号错误,请确认后重试");
			return ERROR;
		}
		
		this.guardianShip.setGuard_1(teacherService.getByNumberE((this.guardianShip.getGuard_1().getT_number())));
		this.guardianShip.setGuard_2(teacherService.getByNumberE((this.guardianShip.getGuard_2().getT_number())));
		this.guardianShip.setRead_1(teacherService.getByNumberE((this.guardianShip.getRead_1().getT_number())));
		this.guardianShip.setRead_2(teacherService.getByNumberE((this.guardianShip.getRead_2().getT_number())));
		this.guardianShip.setRead_3(teacherService.getByNumberE((this.guardianShip.getRead_3().getT_number())));
		
		int row = this.guardianShipService.getByMajorAndSubject(this.guardianShip.getMajor().getM_id(), this.guardianShip.getSubject().getSub_id());
		if(row > 0) {
			req.setAttribute("info", "已有监考信息,请勿重复添加!");
			return ERROR;
		}
		//考试信息
		
		int id = this.guardianShipService.add(this.guardianShip);
		if(id<=0) {
			req.setAttribute("info", "添加失败,请重试!");
			return ERROR;
		}
		//更新考场信息
		ExamInfo ei = this.examInfoService.getByMajorAndSubject(guardianShip.getMajor().getM_id(),guardianShip.getSubject().getSub_id());
		ei.setP_id(id);
		
		return SUCCESS;
	}
}
