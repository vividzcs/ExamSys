package com.nwuer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nwuer.entity.Academy;
import com.nwuer.entity.Student;
import com.nwuer.entity.Teacher;
import com.nwuer.service.AcademyService;
import com.nwuer.service.StudentService;
import com.nwuer.service.TeacherService;
import com.nwuer.utils.Crpty;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Controller
@Scope("prototype")
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
	@Autowired
	private StudentService studentService;
	@Autowired
	private Crpty crpty;
	
	/**
	 * ����ѧ����Ϣ
	 */
	private String s_number;
	public String getS_number() {
		return s_number;
	}
	public void setS_number(String s_number) {
		this.s_number = s_number;
	}
	public String findStu() {
		//������ݺϷ���
		if(s_number == null || s_number.equals("")) {
			return "find";
		}
		Student s = this.studentService.getByNumber(s_number);
		s.setS_pass(crpty.decrypt(s.getS_pass()));
		ServletActionContext.getRequest().setAttribute("student", s);
		return "find";
	}
	
	
	/**
	 * ���½�ʦ��Ϣ
	 * @return
	 */
	public String update() {
		//��֤��Ϣ
		
		Teacher tSelf = (Teacher) ServletActionContext.getRequest().getSession().getAttribute("teacher");
		Teacher t = this.teacherService.getByIdEager(tSelf.getT_id());
		//���� ���� Ժϵ
		t.setT_name(teacher.getT_name());
		t.setT_pass(teacher.getT_pass());
		Academy a = new Academy();
		a.setA_id(teacher.getAcademy().getA_id());
		t.setAcademy(a);
		this.teacherService.update(t);
		
		return SUCCESS;
	}
	
	public String delete() {
		//��֤
		
		this.teacherService.delete(this.teacher.getT_id());
		return SUCCESS;
	}
	
	/**
	 * ����Ա�޸�
	 * @return
	 */
	public String editA() {
		Teacher t = this.teacherService.getById(this.teacher.getT_id());
		List<Academy> list = this.academyService.getAll();
		HttpServletRequest req = ServletActionContext.getRequest();
		
		req.setAttribute("teacher", t);
		req.setAttribute("list", list);
		return "edit";
	}
	
	/**
	 * ����Ա�޸�
	 * @return
	 */
	public String updateA() {
		//��֤��Ϣ
		
		Teacher t = this.teacherService.getById(this.teacher.getT_id());
		teacher.setCreate_time(t.getCreate_time());
		teacher.setLast_login(t.getLast_login());
		teacher.setStatus(t.getStatus());
		
		this.teacherService.update(teacher);
		return SUCCESS;
	}
	
	
	/**
	 * ��ʾ�༭��ʦҳ��
	 * @return
	 */
	public String edit() {
		Teacher tSelf = (Teacher) ServletActionContext.getRequest().getSession().getAttribute("teacher");
		Teacher t = this.teacherService.getByIdEager(tSelf.getT_id());  //�õ���ʦ��Ϣ
		List<Academy> list = this.academyService.getAll();//�õ�ѧԺ��Ϣ
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setAttribute("teacher", t);
		req.setAttribute("list", list);
		
		return "edit";
	}
	
	public String logout() {
		ServletActionContext.getRequest().getSession().setAttribute("teacher", null);
		return "logout";
	}
	
	/**
	 * ��¼
	 * @return
	 */
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
			this.teacher.setT_pass(null);
			this.teacherService.updateLastLogin(System.currentTimeMillis(),confirmTeacher.getT_id());
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
	private int a_id;  //���ϴ�Ժϵid����Ժϵid�����ʦ����
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}//�����ʦ����
	public String downloadTeacher() {
		Academy academy = academyService.getById(a_id);
		Set<Teacher> set = academy.getT_set();
		//��ʼд��
		ServletOutputStream sos = null;
		WritableWorkbook wwk = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/octet-stream");
		String a_name = academy.getA_name() + "��ʦ����.xls";
		try {
			a_name = new String(a_name.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition","attachment;fileName=" + a_name);
		try {
			sos = response.getOutputStream();
			//����һ������д�Ĺ�����
			wwk = Workbook.createWorkbook(sos);
			//������д��Ĺ�����
			WritableSheet sheet = wwk.createSheet("��ʦ��Ϣ", 0);
			
			sheet.setColumnView(0, 15);//���������Զ������п�
			sheet.setColumnView(1, 30);//���������Զ������п�
			sheet.setColumnView(2, 12);//���������Զ������п�
			sheet.setColumnView(4, 15);//���������Զ������п�
			//�ȴ�����ͷ    �� ��
			Label lab_00 = new Label(0, 0, "����");
			Label lab_10 = new Label(1, 0, "����");
			Label lab_20 = new Label(2, 0, "����");
			Label lab_30 = new Label(3, 0, "�Ա�");
			Label lab_40 = new Label(4, 0, "Ժϵ");
			Label lab_50 = new Label(5, 0, "״̬");
			sheet.addCell(lab_00);
			sheet.addCell(lab_10);
			sheet.addCell(lab_20);
			sheet.addCell(lab_30);
			sheet.addCell(lab_40);
			sheet.addCell(lab_50);
			//��ʼд�������
			Iterator<Teacher> iterator = set.iterator();
			int i=1;
			Teacher teacher = null;
			while(iterator.hasNext()) {
				teacher = iterator.next();
				Label lab_0 = new Label(0, i, teacher.getT_number());
				Label lab_1 = new Label(1, i, crpty.decrypt(teacher.getT_pass()));
				Label lab_2 = new Label(2, i, teacher.getT_name());
				String sex = teacher.getT_sex() == 1 ? "��" : "Ů";
				Label lab_3 = new Label(3, i, sex);
				Label lab_4 = new Label(4, i, teacher.getAcademy().getA_name());
				Label lab_5 = new Label(5, i, Byte.toString(teacher.getStatus()));
				
				sheet.addCell(lab_0);
				sheet.addCell(lab_1);
				sheet.addCell(lab_2);
				sheet.addCell(lab_3);
				sheet.addCell(lab_4);
				sheet.addCell(lab_5);
				
				i++;
			}
			
			//������
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
						sos.close();
						sos.flush();
						response.setHeader("Content-Disposition","attachment;fileName=������ʦ����");
					}
						
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return NONE;
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
				//���Ų����ظ�
				
				
				//��������
				Cell passCell = sheet.getCell(1,j);
				String pass = passCell.getContents();
				if(pass.trim().equals("")) {
					//����
					info = "��" + j + "�����벻��Ϊ��";
					req.setAttribute("info", info);
					return ERROR;
				}

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
