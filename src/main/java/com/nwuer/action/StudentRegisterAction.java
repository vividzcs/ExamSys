package com.nwuer.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.GuardianShip;
import com.nwuer.entity.StudentRegister;
import com.nwuer.entity.SubjectiveQuestion;
import com.nwuer.service.ExamInfoService;
import com.nwuer.service.GuardianShipService;
import com.nwuer.service.StudentRegisterService;
import com.nwuer.service.StudentService;
import com.nwuer.utils.ValidateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Controller
@Scope("prototype")
public class StudentRegisterAction extends ActionSupport implements ModelDriven<StudentRegister> {
	private StudentRegister studentRegister = new StudentRegister();
	@Override
	public StudentRegister getModel() {
		return studentRegister;
	}
	String info;
	HttpServletRequest req = ServletActionContext.getRequest();
	
	@Autowired
	private GuardianShipService guardianShipService;
	@Autowired
	private StudentRegisterService studentRegisterService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ExamInfoService examInfoService;
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
		req.setAttribute("list", sList);
		return "list";
	}
	
	public String add() {
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
		//考试信息
		int e_id = this.examInfoService.getIdByMajorAndSubject(this.studentRegister.getMajor().getM_id(), this.studentRegister.getSubject().getSub_id());		
		this.studentRegister.setE_id(e_id);
		int id = this.studentRegisterService.add(this.studentRegister);
		if(id<=0) {
			req.setAttribute("info", "添加失败,请重试!");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public void export() {
		int m_id = this.studentRegister.getMajor().getM_id();
		int sub_id = this.studentRegister.getSubject().getSub_id();
		List<StudentRegister> list = this.studentRegisterService.getAllByMajorAndSubjectAndStatus(m_id, sub_id, (byte)5);
		HttpServletResponse response = ServletActionContext.getResponse();
		ServletOutputStream sos = null;
		WritableWorkbook wwk = null;
		try {
			sos = response.getOutputStream();
			wwk = Workbook.createWorkbook(sos);
			WritableSheet sheet = wwk.createSheet("成绩", 0);
			response.setContentType("application/octet-stream");
			
			if(list.size()==0) {
				response.setHeader("Content-Disposition","attachment;fileName="+new String("未到导出状态.xls".getBytes("UTF-8"),"ISO-8859-1"));
				wwk.write();
				wwk.close();
				sos.close();
				sos.flush();
				return;
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		StudentRegister tmp = list.get(0);
		StringBuilder name = new StringBuilder();
		name.append(tmp.getMajor().getM_name());
		name.append(tmp.getSubject().getSub_name());
		name.append(".xls");
		String mName = null;
		try {
			mName = new String(name.toString().getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition","attachment;fileName=" + mName);
		try {
			sos = response.getOutputStream();
			//创建一个可以写的工作簿
			wwk = Workbook.createWorkbook(sos);
			//创建可写入的工作表
			WritableSheet sheet = wwk.createSheet("成绩", 0);
			sheet.setColumnView(0, 15);//根据内容自动设置列宽
			sheet.setColumnView(1, 15);//根据内容自动设置列宽
			sheet.setColumnView(2, 15);//根据内容自动设置列宽
			sheet.setColumnView(3, 15);//根据内容自动设置列宽
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			//先创建表头    列 行
			Label lab_00 = new Label(0, 0, "姓名");
			Label lab_10 = new Label(1, 0, "学号");
			Label lab_20 = new Label(2, 0, "专业");
			Label lab_30 = new Label(3, 0, "科目");
			Label lab_40 = new Label(4, 0, "成绩");
			
			sheet.addCell(lab_00);
			sheet.addCell(lab_10);
			sheet.addCell(lab_20);
			sheet.addCell(lab_30);
			sheet.addCell(lab_40);
			//开始写入表内容
			StudentRegister sr = null;
			for(int i=1; i<=list.size(); i++) {
				sr = list.get(i-1);
				Label lab_0 = new Label(0, i, sr.getSr_name());
				Label lab_1 = new Label(1, i, sr.getSr_number());
				Label lab_2 = new Label(2, i, sr.getMajor().getM_name());
				Label lab_3 = new Label(3, i, sr.getSubject().getSub_name());
				Label lab_4 = new Label(4, i, String.valueOf(sr.getGrade()));
				
				sheet.addCell(lab_0);
				sheet.addCell(lab_1);
				sheet.addCell(lab_2);
				sheet.addCell(lab_3);
				sheet.addCell(lab_4);
				
			}
			
			//添加完成
			wwk.write();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if(wwk != null)
						wwk.close();
					if(sos != null) {
						response.setHeader("Content-Disposition","attachment;fileName="+new String("导入主观题出错.xls".getBytes("UTF-8"),"ISO-8859-1"));
						sos.close();
						sos.flush();
					}
						
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
