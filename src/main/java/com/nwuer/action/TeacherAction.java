package com.nwuer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nwuer.entity.Academy;
import com.nwuer.entity.Teacher;
import com.nwuer.service.AcademyService;
import com.nwuer.service.TeacherService;
import com.nwuer.utils.Crpty;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Controller
public class TeacherAction extends ActionSupport implements ModelDriven<Teacher> {
	private Teacher teacher = new Teacher();
	@Override
	public Teacher getModel() {
		return teacher;
	}  //��ģ��������ȡǰ������
	
	private Map<String,String> result = new HashMap<String,String>();
	public Map<String,String> getResult() {
		return result;
	}
	public void setResult(Map<String,String> result) {
		this.result = result;
	} //����ǰ�˵�����
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private AcademyService academyService;
	
	public String login() {
		//��֤
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//��֤��֤��
		String code = request.getParameter("code");
		String codeReal = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if(!codeReal.equalsIgnoreCase(code)) {
			result = new HashMap<String,String>();
			result.put("status","0");
			result.put("msg", "��֤�����");
			return ERROR;
		}
		
		Teacher confirmTeacher = this.teacherService.getByNumberAndPass(teacher);
		if(confirmTeacher != null) {
			//��¼�ɹ�
			confirmTeacher.setT_pass(null);
			session.setAttribute("teacher", confirmTeacher);
			result.put("status","1");
			result.put("msg", "��¼�ɹ�");
			return SUCCESS;
		}else {
			result = new HashMap<String,String>();
			result.put("status","0");
			result.put("msg", "�û������������");
			return ERROR;
		}
		
	}
	
	
	public String list() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//��ѯ����Ժϵ
		List<Academy> aca_list = this.academyService.getAll();
		//��ѯ��ʦ����
		List<Teacher> tea_list = this.teacherService.getAllByTimeDesc();
		
		
		request.setAttribute("aca_list", aca_list);
		request.setAttribute("tea_list", tea_list);
		return "list";
	}
	
	public String add() {
		int id = this.teacherService.add(teacher);
		if(id > 0)
			return SUCCESS;
		else
			return ERROR;
	}
	
	private File file_upload; //�ϴ����ļ�,  ���ϴ������nameֵ
	private String file_uploadFileName; //�ϴ��ļ�����,���ϴ�����ļ���+FileName
	public File getFile_upload() {
		return file_upload;
	}
	public void setFile_upload(File file_upload) {
		this.file_upload = file_upload;
	}
	public String getFile_uploadFileName() {
		return file_uploadFileName;
	}
	public void setFile_uploadFileName(String file_uploadFileName) {
		this.file_uploadFileName = file_uploadFileName;
	} //�ϴ��ļ����
	/**
	 * 
	 * @return
	 */
	public String importTeacher() {
		//�������
		
		//��ʼ����
		FileInputStream excel = null;
		Workbook wb =  null;
		String info = "";
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			ApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
			//����ʼ
			//�õ�Excel�ļ�
			excel = new FileInputStream(file_upload);
			//��ȡ����������
			wb = Workbook.getWorkbook(excel);//ֻ��
			//��ʼ��excel���ݽ��в���(��,��)
			//ѧ��	����	����	�Ա�	Ժϵ	רҵ	�Ƿ��ܵ�¼(0:����,1:��)
			Sheet sheet = wb.getSheet(0);
			//����Ҫ�õ��ж�����
			int rows = 0;
			int rowsAll = sheet.getRows();
			for(int i=0; i<rowsAll; i++) {
				Cell cell = sheet.getCell(0,i);
				if(cell.getContents().trim().equals("")) {
					rows = i;
					break;
				}
			}
			rows = rows == 0 ? rowsAll : rows; //����ж�����о͵���ʵ����,�����������
			for(int j=1; j<rows; j++) {
				Teacher teacher = new Teacher();
				 //����ѧ��
				Cell numCell = sheet.getCell(0, j);
				String number = numCell.getContents();
				if(number.trim().length() != 10) {
					//����
					info =  "��" + j + "�й�������" ;
					req.setAttribute("info", info);
					return ERROR;
				}
				//��������
				Cell passCell = sheet.getCell(1,j);
				String pass = passCell.getContents();
				if(pass.trim().equals("")) {
					//����
					info = "��" + j + "�����벻��Ϊ��";
					req.setAttribute("info", info);
					return ERROR;
				}
				//md5����
				Crpty crpty = (Crpty) application.getBean("crpty");
				pass = crpty.encrypt(pass);
				
				//��������
				Cell nameCell = sheet.getCell(2,j);
				String name = nameCell.getContents();
				if(name.trim().equals("")) {
					//����
					info = "��" + j + "����������Ϊ��";
					req.setAttribute("info", info);
					return ERROR;
				}
				
				//�����Ա�
				Cell sexCell = sheet.getCell(3,j);
				String sex = sexCell.getContents();
				if(!sex.trim().equals("��") && !sex.trim().equals("Ů")) {
					//����
					info = "��" + j + "���Ա���д����";
					req.setAttribute("info", info);
					return ERROR;
				}
				byte s_sex = (byte) (sex.trim().equals("��") ? 1 : 0);
				
				//����Ժϵ
				Cell academyCell = sheet.getCell(4,j);
				String academy = academyCell.getContents();
				int a_id = 0;
				if(academy.trim().equals("") || (a_id = this.academyService.getIdByName(academy)) ==0 ) {
					info = "��" + j + "��Ժϵ��д����";
					req.setAttribute("info", info);
					return ERROR;
				}
				
				//�����Ƿ��ܵ�¼
				Cell loginCell = sheet.getCell(5,j);
				String login = loginCell.getContents();
				if(!login.trim().equals("0") && !login.trim().equals("1")) {
					//����
					info = "��" + j + "�е�¼״̬��д����";
					req.setAttribute("info", info);
					return ERROR;
				}
				byte status = Byte.parseByte(login);
				
				//��֤ͨ��
				Academy s_academy = new Academy();
				s_academy.setA_id(a_id);
				
				teacher.setT_name(name);
				teacher.setT_number(number);
				teacher.setT_pass(pass);
				teacher.setT_sex(s_sex);
				teacher.setStatus(status);
				teacher.setAcademy(s_academy);
				int id = this.teacherService.add(teacher);
				if(id <= 0) {
					//���ʧ��
					info = "��" + j + "��ѧ�����ʧ��,������";
					req.setAttribute("info", info);
					return ERROR;
				}
			}
			
			//������
			return SUCCESS;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if(excel != null)
						excel.close();
					if(wb != null)
						wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return ERROR;
	}
}
