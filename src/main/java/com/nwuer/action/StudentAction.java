package com.nwuer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nwuer.entity.Academy;
import com.nwuer.entity.Major;
import com.nwuer.entity.Student;
import com.nwuer.service.AcademyService;
import com.nwuer.service.MajorService;
import com.nwuer.service.StudentService;
import com.nwuer.utils.Crpty;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Controller
@Scope("prototype")
public class StudentAction extends ActionSupport implements ModelDriven<Student> {
	private Student student = new Student();
	@Override
	public Student getModel() {
		return student;
	}  //ģ��������ȡ����
	
	private Map result = new HashMap();
	public Map getResult() {
		return result;
	}
	public void setResult(Map result) {
		this.result = result;
	} //����ǰ�˵�Json����
	@Autowired
	private StudentService studentService;
	@Autowired
	private AcademyService academyService; 
	@Autowired
	private MajorService majorService;
	

	/**
	 * ��¼
	 * @return
	 */
	public String login() {
		//��֤
		
		
		Student studentConfirm = this.studentService.getByNumberAndPass(student);
		if(studentConfirm != null) {
			//��¼�ɹ�
			studentConfirm.setS_pass(null);
			ServletActionContext.getRequest().getSession().setAttribute("student", studentConfirm);
			
			this.result.put("status", "1");
			this.result.put("msg", "��¼�ɹ�");
			return SUCCESS;
		}else {
			this.result.put("status", "0");
			this.result.put("msg", "�û������������");
			return ERROR;
		}
		
	}
	
	/**
	 * ���ѧ��
	 * @return
	 */
	public String add() {
		//��֤
		
		int id = (int) this.studentService.add(student);
		if(id > 0) {
			//��ӳɹ�
			return SUCCESS;
		} else {
			//���ʧ��
			return ERROR;
		}
	}
	
	public String delete() {
		
		return SUCCESS;
	}
	
	public String importStudent() {
		//�����Student��,���ж�ѧ�����Ƿ�������
		if(this.studentService.hasData()) {
			this.studentService.clear();
			if(this.studentService.hasData()) {
				//û����ձ�
				result.put("status", "0");
				result.put("msg", "��ʷѧ�������޷����!���Ժ�����" );
				return ERROR;
			}
		}
		
		FileInputStream excel = null;
		Workbook wb =  null;
		try {
			ApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
			//����ʼ
			File f = new File(ServletActionContext.getServletContext().getResource("/modellist/studentListTemplate.xls").getPath());
			//�õ�Excel�ļ�
			excel = new FileInputStream(f);
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
				Student student = new Student();
				 //����ѧ��
				Cell numCell = sheet.getCell(0, j);
				String number = numCell.getContents();
				if(number.trim().length() != 10) {
					//����
					result.put("status", "0");
					result.put("msg", "��" + j + "��ѧ������" );
					return ERROR;
				}
				//��������
				Cell passCell = sheet.getCell(1,j);
				String pass = passCell.getContents();
				if(pass.trim().equals("")) {
					//����
					result.put("status", "0");
					result.put("msg", "��" + j + "�����벻��Ϊ��" );
					return ERROR;
				}
				
				//��������
				Cell nameCell = sheet.getCell(2,j);
				String name = nameCell.getContents();
				if(name.trim().equals("")) {
					//����
					result.put("status", "0");
					result.put("msg", "��" + j + "����������Ϊ��" );
					return ERROR;
				}
				
				//�����Ա�
				Cell sexCell = sheet.getCell(3,j);
				String sex = sexCell.getContents();
				if(!sex.trim().equals("��") && !sex.trim().equals("Ů")) {
					//����
					result.put("status", "0");
					result.put("msg", "��" + j + "���Ա���д����" );
					return ERROR;
				}
				byte s_sex = (byte) (sex.trim().equals("��") ? 1 : 0);
				
				//����Ժϵ
				Cell academyCell = sheet.getCell(4,j);
				String academy = academyCell.getContents();
				int a_id = 0;
				if(academy.trim().equals("") || (a_id = this.academyService.getIdByName(academy)) ==0 ) {
					result.put("status", "0");
					result.put("msg", "��" + j + "��Ժϵ��д����" );
					return ERROR;
				}
				
				//����רҵ
				Cell majorCell = sheet.getCell(5,j);
				String major = majorCell.getContents();
				int m_id = 0;
				if(major.trim().equals("") || (m_id=this.majorService.getIdByName(major))== 0) {
					
					result.put("status", "0");
					result.put("msg", "��" + j + "��רҵ��д����" );
					return ERROR;
				}
				
				//�����Ƿ��ܵ�¼
				Cell loginCell = sheet.getCell(6,j);
				String login = loginCell.getContents();
				if(!login.trim().equals("0") && !login.trim().equals("1")) {
					//����
					result.put("status", "0");
					result.put("msg", "��" + j + "�е�¼״̬��д����" );
					return ERROR;
				}
				byte status = Byte.parseByte(login);
				
				//��֤ͨ��
				Academy s_academy = new Academy();
				s_academy.setA_id(a_id);
				Major s_major = new Major();
				s_major.setM_id(m_id);
				
				student.setS_name(name);
				student.setS_number(number);
				student.setS_pass(pass);
				student.setS_sex(s_sex);
				student.setStatus(status);
				student.setAcademy(s_academy);
				student.setMajor(s_major);
				int id = this.studentService.add(student);
				if(id <= 0) {
					//���ʧ��
					result.put("status", "0");
					result.put("msg", "��" + j + "��ѧ�����ʧ��,������" );
					return ERROR;
				}
			}
			
			//������
			result.put("status", "1");
			result.put("msg", "������" );
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
