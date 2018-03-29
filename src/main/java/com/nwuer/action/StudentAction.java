package com.nwuer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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
	@Autowired
	private Crpty crpty;
	

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
	 * �˳�
	 * @return
	 */
	public String logout() {
		ServletActionContext.getRequest().getSession().setAttribute("student", null);
		return "logout";
	}
	
	public String list() {
		List<Student> s_list = this.studentService.getAllByTimeDesc();
		
		ServletActionContext.getRequest().setAttribute("s_list", s_list);
		return "list";
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
	/**
	 * ɾ��
	 * @return
	 */
	public String delete() {
		//��֤id��Ϣ
		
		this.studentService.delete(this.student.getS_id());
		return SUCCESS;
	}
	
	/**
	 * ����Ա�޸�
	 * @return
	 */
	public String editA() {
		Student stu = this.studentService.getById(this.student.getS_id());
		ServletActionContext.getRequest().setAttribute("stu", stu);
		return "edit";
	}
	/**
	 * ����Ա�޸�
	 * @return
	 */
	public String updateA() {
		//��֤
		
		Student stu = this.studentService.getById(student.getS_id());
		student.setCreate_time(stu.getCreate_time());
		student.setLast_login(stu.getLast_login());
		student.setStatus(stu.getStatus());
		this.studentService.update(student);
		return SUCCESS;
	}
	
	public String exportStudent() {
		//��ʼд��
		ServletOutputStream sos = null;
		WritableWorkbook wwk = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/octet-stream");
		String a_name = "ȫ��ѧ������.xls";
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
			WritableSheet sheet = wwk.createSheet("ȫ��ѧ����Ϣ", 0);
			
			sheet.setColumnView(0, 25);//���������Զ������п�
			sheet.setColumnView(1, 25);//���������Զ������п�
			sheet.setColumnView(2, 20);//���������Զ������п�
			sheet.setColumnView(3, 12);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 10);
			
			//�ȴ�����ͷ    �� ��
			Label lab_00 = new Label(0, 0, "ѧ��");
			Label lab_10 = new Label(1, 0, "����");
			Label lab_20 = new Label(2, 0, "����");
			Label lab_30 = new Label(3, 0, "�Ա�");
			Label lab_40 = new Label(4, 0, "Ժϵ");
			Label lab_50 = new Label(5, 0, "רҵ");
			Label lab_60 = new Label(6, 0, "״̬");
			sheet.addCell(lab_00);
			sheet.addCell(lab_10);
			sheet.addCell(lab_20);
			sheet.addCell(lab_30);
			sheet.addCell(lab_40);
			sheet.addCell(lab_50);
			sheet.addCell(lab_60);
			
			//��ʼд�������
			List<Student> list = this.studentService.getAll();
			for(int i=1; i<=list.size(); i++) {
				Student stu = list.get(i-1);
				Label lab_0 = new Label(0, i, stu.getS_number());
				Label lab_1 = new Label(1, i, crpty.decrypt(stu.getS_pass()));
				Label lab_2 = new Label(2, i, stu.getS_name());
				String sex = stu.getS_sex() == 1 ? "��" : "Ů";
				Label lab_3 = new Label(3, i, sex);
				Label lab_4 = new Label(4, i, stu.getAcademy().getA_name());
				Label lab_5 = new Label(5, i, stu.getMajor().getM_name());
				Label lab_6 = new Label(6, i, Byte.toString(stu.getStatus()));
				
				sheet.addCell(lab_0);
				sheet.addCell(lab_1);
				sheet.addCell(lab_2);
				sheet.addCell(lab_3);
				sheet.addCell(lab_4);
				sheet.addCell(lab_5);
				sheet.addCell(lab_6);
				
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
	
	/**
	 * ����ѧ��
	 */
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
