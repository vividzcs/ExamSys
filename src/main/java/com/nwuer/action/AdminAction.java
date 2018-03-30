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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Academy;
import com.nwuer.entity.Admin;
import com.nwuer.entity.ChoiceQuestion;
import com.nwuer.entity.JudgeQuestion;
import com.nwuer.entity.Major;
import com.nwuer.entity.Subject;
import com.nwuer.entity.SubjectiveQuestion;
import com.nwuer.entity.Teacher;
import com.nwuer.service.AcademyService;
import com.nwuer.service.AdminService;
import com.nwuer.service.ChoiceQuestionService;
import com.nwuer.service.JudgeQuestionService;
import com.nwuer.service.MajorService;
import com.nwuer.service.SubjectService;
import com.nwuer.service.SubjectiveQuestionService;
import com.nwuer.service.TeacherService;
import com.nwuer.utils.Crpty;
import com.nwuer.utils.ValidateUtil;
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
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {
	private Admin admin = new Admin();
	@Override
	public Admin getModel() {
		return admin;
	}  //ģ��������ȡ����
	@Autowired
	private AdminService adminService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private AcademyService academyService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ChoiceQuestionService choiceQuestionService;
	@Autowired
	private JudgeQuestionService judgeQuestionService;
	@Autowired
	private SubjectiveQuestionService subjectiveQuestionService;
	@Autowired
	private Crpty crpty;
	@Autowired
	private ValidateUtil validateUtil;
	
	private Map result = new HashMap();
	public Map getResult() {
		return result;
	}
	public void setResult(Map result) {
		this.result = result;
	}  //����JSON����
	
	/**
	 * ά������Ա��Ϣ
	 * @return
	 */
	public String update() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String newPass = req.getParameter("newPass");
		//��֤����
		
		//��֤����Ա�û���Ϣ
		
		
		//��ѯ����
		int ad_id = ((Admin)req.getSession().getAttribute("admin")).getAd_id();
		Admin newAdmin = this.adminService.getById(ad_id);
		//�޸�
		
		newAdmin.setAd_name(admin.getAd_name());
		this.adminService.update(newAdmin);
		this.result.put("status","1");
		this.result.put("msg", "�޸ĳɹ�");
		return SUCCESS;
	}
	
	public String logout() {
		ServletActionContext.getRequest().getSession().setAttribute("admin", null);
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
			result.put("status","0");
			result.put("msg", "��֤�����");
			return ERROR;
		}
		//��֤��¼��Ϣ
		String msg = this.validateUtil.validateNumber(this.admin.getAd_number(), 10);
		if(msg!= null) {
			result.put("status","0");
			result.put("msg", "��֤�����");
			return ERROR;
		}
		msg = this.validateUtil.validateMinLength(this.admin.getAd_pass(), 6);
		if(msg!= null) {
			result.put("status","0");
			result.put("msg", "��֤�����");
			return ERROR;
		}
		Admin adminConfirm = this.adminService.getByNumberAndPass(this.admin);
		if(adminConfirm != null) {
			//�ɹ�
			adminConfirm.setAd_pass(null);
			this.adminService.updateLastLogin(System.currentTimeMillis(), adminConfirm.getAd_id());
			session.setAttribute("admin", adminConfirm);
			this.result.put("status","1");
			this.result.put("msg", "��¼�ɹ�");
			return SUCCESS;
		}else {
			this.result.put("status","0");
			this.result.put("msg", "�û������������");
			return ERROR;
		}
			
		
	}
	/**
	 * ��ѯ��ʦ��Ϣ
	 */
	private String t_number;
	public String getT_number() {
		return t_number;
	}
	public void setT_number(String t_number) {
		this.t_number = t_number;
	}
	public String findTeacher() {
		//������ݺϷ���
		if(t_number == null) {
			return "find";
		}
		List<Teacher> list = this.teacherService.getByNumber(t_number);
		Teacher t = null;
		for(int i=0; i<list.size(); i++) {
			t = list.get(i);
			t.setT_pass(crpty.decrypt(t.getT_pass()));
		}
		
		ServletActionContext.getRequest().setAttribute("list", list);
		return "find";
	}
	
	/**
	 * ����Ժϵ��רҵ����������
	 * @return
	 */
	public String mesDF() {
		List<Academy> academys = this.academyService.getAll();
		
		/*{
	      	department:'��Ϣ��ѧ�뼼��ѧԺ',
	      	profess:['�������','�������ѧ�뼼��','������Ϣ','ͨ�Ź���']
	      },*/
		Set<Major> majors = null;
		for(int i=0; i<academys.size(); i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			Academy aca = academys.get(i);
			map.put("department", aca.getA_name());
			Map<Integer,String> profess = new HashMap<Integer,String>();
			majors = aca.getM_set();
			Major maj = null;
			Iterator<Major> it = majors.iterator();
			while (it.hasNext()) {
				maj = it.next();
				profess.put(maj.getM_id(), maj.getM_name());
			}
			//
			map.put("profess", profess);
			this.result.put(aca.getA_id(), map);
		}
		return SUCCESS;
	}
	
	/**
	 * ����רҵ�Ϳ�Ŀ����������
	 * @return
	 */
	public String mesFS() {
		List<Major> majors = this.majorService.getAll();
		
		/*{
	      	department:'��Ϣ��ѧ�뼼��ѧԺ',
	      	profess:['�������','�������ѧ�뼼��','������Ϣ','ͨ�Ź���']
	      },*/
		Set<Subject> subs = null;
		for(int i=0; i<majors.size(); i++) {
			Map<String,Object> map = new HashMap<String,Object>(); //���רҵ�Ϳ�Ŀ�ĵ�ַ
			Major maj = majors.get(i); //�õ�רҵ
			map.put("profess", maj.getM_name()); //��һ��Ԫ��ֵ��רҵ
			Map<Integer,String> sub = new HashMap<Integer,String>();  //��ſ�Ŀ����
			subs = maj.getS_set();  //�õ�רҵ
			Subject s = null;
			Iterator<Subject> it = subs.iterator();
			while (it.hasNext()) {
				s = it.next();
				sub.put(s.getSub_id(), s.getSub_name());
			}
			//
			map.put("subject", sub);
			this.result.put(maj.getM_id(), map);
		}
		return SUCCESS;
	}
	
	
	/**
	 * ��������������
	 */
	private int kind; //�������
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
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	/**
	 * �������
	 * @return
	 */
	public String importQuestionBank() {
		//�������
		
		//��ʼ����
		String flag = "";
		switch (kind) {
		case 0 :
			//ѡ����
			flag = importChoiceQuestion();
			if(flag == null) {
				//����ɹ�
				return SUCCESS;
			}
			break;
		case 1 :
			//�ж���
			flag = importJudgeQuestion();
			if(flag == null) {
				//����ɹ�
				return SUCCESS;
			}
			break;
		case 2 :
			//������
			flag = importSubjectiveQuestion();
			if(flag == null) {
				//����ɹ�
				return SUCCESS;
			}
			break;
		}
		
		//������˵������ʧ��
		return ERROR;
		
	}
	
	/**
	 * ����ѡ����
	 * @return �ɹ�����null,���򷵻ش�����Ϣ
	 */
	public String importChoiceQuestion() {
		//���Ҳ�����
		String info = "";
		//�����ѡ�����,���ж�ѡ������Ƿ�������
				if(this.choiceQuestionService.hasData()) {
					this.choiceQuestionService.clear();
					if(this.choiceQuestionService.hasData()) {
						//û����ձ�
						info = "��ʷѡ����������޷����!���Ժ�����" ;
						return info;
					}
				}
		//��ʼ����
		FileInputStream excel = null;
		Workbook wb =  null;
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			//�õ�Excel�ļ�
			excel = new FileInputStream(file_upload);
			//��ȡ����������
			wb = Workbook.getWorkbook(excel);//ֻ��
			//��ʼ��excel���ݽ��в���(��,��)
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
				ChoiceQuestion choiceQuestion = new ChoiceQuestion();
				 //������Ŀ
				Cell questionCell = sheet.getCell(0, j);
				String question = questionCell.getContents();
				if(question.trim().equals("")) {
					//����
					info =  "��" + j + "����Ŀ����Ϊ��" ;
					return info;
				}
				
				//������ȷѡ��
				Cell answerCell = sheet.getCell(1,j);
				String answer = answerCell.getContents();
				if(answer.trim().equals("")) {
					//����
					info = "��" + j + "����ȷѡ���Ϊ��";
					return info;
				}

				//��������ѡ��1
				Cell other1Cell = sheet.getCell(2,j);
				String other1 = other1Cell.getContents();
				if(other1.trim().equals("")) {
					//����
					info = "��" + j + "������ѡ��1����Ϊ��";
					return info;
				}
				
				//��������ѡ��2
				Cell other2Cell = sheet.getCell(3,j);
				String other2 = other2Cell.getContents();
				if(other2.trim().equals("")) {
					//����
					info = "��" + j + "������ѡ��2��д����";
					return info;
				}
				
				//��������ѡ��3
				Cell other3Cell = sheet.getCell(4,j);
				String other3 = other3Cell.getContents();
				if(other3.trim().equals("")) {
					info = "��" + j + "������ѡ��3��д����";
					return info;
				}
				
				//�������׳̶�
				Cell degreeCell = sheet.getCell(5,j);
				String degree = degreeCell.getContents();
				if(!degree.trim().equals("0") && !degree.trim().equals("1") && !degree.trim().equals("2")) {
					//����
					info = "��" + j + "�����׳̶���д����";
					return info;
				}
				byte deg = Byte.parseByte(degree);
				
				//����רҵ
				Cell majorCell = sheet.getCell(6,j);
				String major = majorCell.getContents();
				int m_id = 0;
				if(major.trim().equals("") || (m_id=this.majorService.getIdByName(major))== 0) {
					
					info = "��" + j + "��רҵ��д����";
					return info;
				}
				
				//�����Ŀ
				Cell subjectCell = sheet.getCell(7,j);
				String sub = subjectCell.getContents();
				int sub_id = 0;
				if(sub.trim().equals("") || (sub_id=this.subjectService.getIdByName(sub))== 0) {
					
					info = "��" + j + "�п�Ŀ��д����";
					return info;
				}
				
				//��֤ͨ��
				Major m = new Major();
				m.setM_id(m_id);
				Subject s = new Subject();
				s.setSub_id(sub_id);
				choiceQuestion.setCho_question(question);
				choiceQuestion.setCho_answer(answer);
				choiceQuestion.setCho_choice_1(other1);
				choiceQuestion.setCho_choice_2(other2);
				choiceQuestion.setCho_choice_3(other3);
				choiceQuestion.setDegree(deg);
				choiceQuestion.setMajor(m);
				choiceQuestion.setSubject(s);
				
				int id = this.choiceQuestionService.add(choiceQuestion);
				if(id <= 0) {
					//���ʧ��
					info = "��" + j + "����Ŀ���ʧ��,������";
					return info;
				}
			}
			
			//������
			return null;
			
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
		return "ϵͳ����,���Ժ�����!";
	}
	
	/**
	 * �����ж���
	 * ��Ŀ	��	�Ѷ� רҵ	������Ŀ
	 * @return �ɹ�����null,���򷵻ش�����Ϣ
	 */
	public String importJudgeQuestion() {
		//���Ҳ�����
				String info = "";
				//������ж���,���ж��ж����Ƿ�������
						if(this.judgeQuestionService.hasData()) {
							this.judgeQuestionService.clear();
							if(this.judgeQuestionService.hasData()) {
								//û����ձ�
								info = "��ʷ�ж��������޷����!���Ժ�����" ;
								return info;
							}
						}
				//��ʼ����
				FileInputStream excel = null;
				Workbook wb =  null;
				HttpServletRequest req = ServletActionContext.getRequest();
				try {
					//�õ�Excel�ļ�
					excel = new FileInputStream(file_upload);
					//��ȡ����������
					wb = Workbook.getWorkbook(excel);//ֻ��
					//��ʼ��excel���ݽ��в���(��,��)
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
						JudgeQuestion judgeQuestion = new JudgeQuestion();
						 //������Ŀ
						Cell questionCell = sheet.getCell(0, j);
						String question = questionCell.getContents();
						if(question.trim().equals("")) {
							//����
							info =  "��" + j + "����Ŀ����Ϊ��" ;
							return info;
						}
						
						//����� 0 , 1
						Cell answerCell = sheet.getCell(1,j);
						String answer = answerCell.getContents();
						if(!answer.trim().equals("T") && !answer.trim().equals("F")) {
							//����
							info = "��" + j + "�д𰸸�ʽ����";
							return info;
						}
						byte sub_answer = (byte) (answer.equals("T") ? 1 : 0);
						
						//�������׳̶�
						Cell degreeCell = sheet.getCell(2,j);
						String degree = degreeCell.getContents();
						if(!degree.trim().equals("0") && !degree.trim().equals("1") && !degree.trim().equals("2")) {
							//����
							info = "��" + j + "�����׳̶���д����";
							return info;
						}
						byte deg = Byte.parseByte(degree);
						
						//����רҵ
						Cell majorCell = sheet.getCell(3,j);
						String major = majorCell.getContents();
						int m_id = 0;
						if(major.trim().equals("") || (m_id=this.majorService.getIdByName(major))== 0) {
							
							info = "��" + j + "��רҵ��д����";
							return info;
						}
						
						//�����Ŀ
						Cell subjectCell = sheet.getCell(4,j);
						String sub = subjectCell.getContents();
						int sub_id = 0;
						if(sub.trim().equals("") || (sub_id=this.subjectService.getIdByName(sub))== 0) {
							
							info = "��" + j + "�п�Ŀ��д����";
							return info;
						}
						
						//��֤ͨ��
						Major m = new Major();
						m.setM_id(m_id);
						Subject s = new Subject();
						s.setSub_id(sub_id);
						judgeQuestion.setJud_question(question);
						judgeQuestion.setJud_answer(sub_answer);
						judgeQuestion.setDegree(deg);
						judgeQuestion.setMajor(m);
						judgeQuestion.setSubject(s);
						
						int id = this.judgeQuestionService.add(judgeQuestion);
						if(id <= 0) {
							//���ʧ��
							info = "��" + j + "����Ŀ���ʧ��,������";
							return info;
						}
					}
					
					//������
					return null;
					
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
				return "ϵͳ����,���Ժ�����!";
	}
	
	/**
	 * �����⵼��
	 * �ɹ�����null,���򷵻ش�����Ϣ
	 * @return
	 */
	public String importSubjectiveQuestion() {
		//���Ҳ�����
		String info = "";
		//������ж���,���ж��ж����Ƿ�������
				if(this.subjectiveQuestionService.hasData()) {
					this.subjectiveQuestionService.clear();
					if(this.subjectiveQuestionService.hasData()) {
						//û����ձ�
						info = "��ʷ�����������޷����!���Ժ�����" ;
						return info;
					}
				}
		//��ʼ����
		FileInputStream excel = null;
		Workbook wb =  null;
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			//�õ�Excel�ļ�
			excel = new FileInputStream(file_upload);
			//��ȡ����������
			wb = Workbook.getWorkbook(excel);//ֻ��
			//��ʼ��excel���ݽ��в���(��,��)
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
				SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion();
				 //������Ŀ
				Cell questionCell = sheet.getCell(0, j);
				String question = questionCell.getContents();
				if(question.trim().equals("")) {
					//����
					info =  "��" + j + "����Ŀ����Ϊ��" ;
					return info;
				}
				
				//�����
				Cell answerCell = sheet.getCell(1,j);
				String answer = answerCell.getContents();
				if(answer.trim().equals("")) {
					//����
					info = "��" + j + "�д𰸲���Ϊ��";
					return info;
				}
				
				//�������׳̶�
				Cell degreeCell = sheet.getCell(2,j);
				String degree = degreeCell.getContents();
				if(!degree.trim().equals("0") && !degree.trim().equals("1") && !degree.trim().equals("2")) {
					//����
					info = "��" + j + "�����׳̶���д����";
					return info;
				}
				byte deg = Byte.parseByte(degree);
				
				//��������
				Cell questionKindCell = sheet.getCell(2,j);
				String qKind = questionKindCell.getContents();
				if(!degree.trim().equals("0") && !degree.trim().equals("1") && !degree.trim().equals("2") && !degree.trim().equals("3") && !degree.trim().equals("4")) {
					//����
					info = "��" + j + "��������������д����";
					return info;
				}
				byte sq_kind = Byte.parseByte(qKind);
				
				//����רҵ
				Cell majorCell = sheet.getCell(4,j);
				String major = majorCell.getContents();
				int m_id = 0;
				if(major.trim().equals("") || (m_id=this.majorService.getIdByName(major))== 0) {
					
					info = "��" + j + "��רҵ��д����";
					return info;
				}
				
				//�����Ŀ
				Cell subjectCell = sheet.getCell(5,j);
				String sub = subjectCell.getContents();
				int sub_id = 0;
				if(sub.trim().equals("") || (sub_id=this.subjectService.getIdByName(sub))== 0) {
					
					info = "��" + j + "�п�Ŀ��д����";
					return info;
				}
				
				//��֤ͨ��
				subjectiveQuestion.setSq_question(question);
				subjectiveQuestion.setSq_answer(answer);
				subjectiveQuestion.setDegree(deg);
				subjectiveQuestion.setSq_kind(sq_kind);
				subjectiveQuestion.getMajor().setM_id(m_id);
				subjectiveQuestion.getSubject().setSub_id(sub_id);
				
				int id = this.subjectiveQuestionService.add(subjectiveQuestion);
				if(id <= 0) {
					//���ʧ��
					info = "��" + j + "����Ŀ���ʧ��,������";
					return info;
				}
			}
			
			//������
			return null;
			
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
		return "ϵͳ����,���Ժ�����!";
	}
	
	public String downloadQuestionBank() {
		//��ʼ����
		switch (kind) {
		case 0 :
			//ѡ����
			exportChoiceQuestion();
			break;
		case 1 :
			//�ж���
			exportJudgeQuestion();
			break;
		case 2 :
			//������
			exportSubjectiveQuestion();
			break;
		}
		
		//������˵������ʧ��
		return NONE;
	}
	
	public void exportChoiceQuestion() {
		//��ʼд��
		ServletOutputStream sos = null;
		WritableWorkbook wwk = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/octet-stream");
		String name = "ѡ�������.xls";
		try {
			name = new String(name.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition","attachment;fileName=" + name);
		try {
			sos = response.getOutputStream();
			//����һ������д�Ĺ�����
			wwk = Workbook.createWorkbook(sos);
			//������д��Ĺ�����
			WritableSheet sheet = wwk.createSheet("ѡ�������", 0);
			sheet.setColumnView(0, 100);//���������Զ������п�
			sheet.setColumnView(1, 50);//���������Զ������п�
			sheet.setColumnView(2, 50);//���������Զ������п�
			sheet.setColumnView(3, 50);//���������Զ������п�
			sheet.setColumnView(4, 50);
			sheet.setColumnView(5, 15);
			sheet.setColumnView(6, 20);
			sheet.setColumnView(7, 20);
			//�ȴ�����ͷ    �� ��
			Label lab_00 = new Label(0, 0, "��Ŀ");
			Label lab_10 = new Label(1, 0, "����");
			Label lab_20 = new Label(2, 0, "����ѡ��1");
			Label lab_30 = new Label(3, 0, "����ѡ��2");
			Label lab_40 = new Label(4, 0, "����ѡ��3");
			Label lab_50 = new Label(5, 0, "���׳̶�");
			Label lab_60 = new Label(6, 0, "רҵ");
			Label lab_70 = new Label(7, 0, "��Ŀ");
			
			sheet.addCell(lab_00);
			sheet.addCell(lab_10);
			sheet.addCell(lab_20);
			sheet.addCell(lab_30);
			sheet.addCell(lab_40);
			sheet.addCell(lab_50);
			sheet.addCell(lab_60);
			sheet.addCell(lab_70);
			//��ʼд�������
			List<ChoiceQuestion> list = this.choiceQuestionService.getAll();
			ChoiceQuestion choiceQ = null;
			for(int i=1; i<=list.size(); i++) {
				 choiceQ = list.get(i-1);
				Label lab_0 = new Label(0, i, choiceQ.getCho_question());
				Label lab_1 = new Label(1, i, choiceQ.getCho_answer());
				Label lab_2 = new Label(2, i, choiceQ.getCho_choice_1());
				Label lab_3 = new Label(3, i, choiceQ.getCho_choice_2());
				Label lab_4 = new Label(4, i, choiceQ.getCho_choice_3());
				Label lab_5 = new Label(5, i, String.valueOf(choiceQ.getDegree()));
				Label lab_6 = new Label(6, i, choiceQ.getMajor().getM_name());
				Label lab_7 = new Label(7, i, choiceQ.getSubject().getSub_name());
				
				sheet.addCell(lab_0);
				sheet.addCell(lab_1);
				sheet.addCell(lab_2);
				sheet.addCell(lab_3);
				sheet.addCell(lab_4);
				sheet.addCell(lab_5);
				sheet.addCell(lab_6);
				sheet.addCell(lab_7);
				
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
						response.setHeader("Content-Disposition","attachment;fileName=����ѡ�������");
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
	
	public void exportJudgeQuestion() {
		//��ʼд��
				ServletOutputStream sos = null;
				WritableWorkbook wwk = null;
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("application/octet-stream");
				String name = "�ж������.xls";
				try {
					name = new String(name.getBytes("UTF-8"),"ISO-8859-1");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				response.setHeader("Content-Disposition","attachment;fileName=" + name);
				try {
					sos = response.getOutputStream();
					//����һ������д�Ĺ�����
					wwk = Workbook.createWorkbook(sos);
					//������д��Ĺ�����
					WritableSheet sheet = wwk.createSheet("�ж������", 0);
					sheet.setColumnView(0, 100);//���������Զ������п�
					sheet.setColumnView(1, 15);//���������Զ������п�
					sheet.setColumnView(2, 15);//���������Զ������п�
					sheet.setColumnView(3, 20);//���������Զ������п�
					sheet.setColumnView(4, 20);
					//�ȴ�����ͷ    �� ��
					Label lab_00 = new Label(0, 0, "��Ŀ");
					Label lab_10 = new Label(1, 0, "����");
					Label lab_20 = new Label(2, 0, "���׳̶�");
					Label lab_30 = new Label(3, 0, "רҵ");
					Label lab_40 = new Label(4, 0, "��Ŀ");
					
					sheet.addCell(lab_00);
					sheet.addCell(lab_10);
					sheet.addCell(lab_20);
					sheet.addCell(lab_30);
					sheet.addCell(lab_40);
					//��ʼд�������
					List<JudgeQuestion> list = this.judgeQuestionService.getAll();
					JudgeQuestion judgeQ = null;
					for(int i=1; i<=list.size(); i++) {
						judgeQ = list.get(i-1);
						Label lab_0 = new Label(0, i, judgeQ.getJud_question());
						Label lab_1 = new Label(1, i, judgeQ.getJud_answer() == 0 ? "F" : "T");
						Label lab_2 = new Label(2, i, String.valueOf(judgeQ.getDegree()));
						Label lab_3 = new Label(3, i, judgeQ.getMajor().getM_name());
						Label lab_4 = new Label(4, i, judgeQ.getSubject().getSub_name());
						
						sheet.addCell(lab_0);
						sheet.addCell(lab_1);
						sheet.addCell(lab_2);
						sheet.addCell(lab_3);
						sheet.addCell(lab_4);
						
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
								response.setHeader("Content-Disposition","attachment;fileName=�����ж������");
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
	
	public void exportSubjectiveQuestion(){
		//��ʼд��
		ServletOutputStream sos = null;
		WritableWorkbook wwk = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/octet-stream");
		String name = "���������.xls";
		try {
			name = new String(name.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition","attachment;fileName=" + name);
		try {
			sos = response.getOutputStream();
			//����һ������д�Ĺ�����
			wwk = Workbook.createWorkbook(sos);
			//������д��Ĺ�����
			WritableSheet sheet = wwk.createSheet("���������", 0);
			sheet.setColumnView(0, 100);//���������Զ������п�
			sheet.setColumnView(1, 100);//���������Զ������п�
			sheet.setColumnView(2, 15);//���������Զ������п�
			sheet.setColumnView(3, 15);//���������Զ������п�
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			//�ȴ�����ͷ    �� ��
			Label lab_00 = new Label(0, 0, "��Ŀ����");
			Label lab_10 = new Label(1, 0, "�ο���");
			Label lab_20 = new Label(2, 0, "���׳̶�");
			Label lab_30 = new Label(3, 0, "����������");
			Label lab_40 = new Label(4, 0, "רҵ");
			Label lab_50 = new Label(5, 0, "��Ŀ");
			
			sheet.addCell(lab_00);
			sheet.addCell(lab_10);
			sheet.addCell(lab_20);
			sheet.addCell(lab_30);
			sheet.addCell(lab_40);
			sheet.addCell(lab_50);
			//��ʼд�������
			List<SubjectiveQuestion> list = this.subjectiveQuestionService.getAll();
			SubjectiveQuestion subjectiveQ = null;
			for(int i=1; i<=list.size(); i++) {
				subjectiveQ = list.get(i-1);
				Label lab_0 = new Label(0, i, subjectiveQ.getSq_question());
				Label lab_1 = new Label(1, i, subjectiveQ.getSq_answer());
				Label lab_2 = new Label(2, i, String.valueOf(subjectiveQ.getDegree()));
				Label lab_3 = new Label(3, i, String.valueOf(subjectiveQ.getSq_kind()));
				Label lab_4 = new Label(4, i, subjectiveQ.getMajor().getM_name());
				Label lab_5 = new Label(5, i, subjectiveQ.getSubject().getSub_name());
				
				sheet.addCell(lab_0);
				sheet.addCell(lab_1);
				sheet.addCell(lab_2);
				sheet.addCell(lab_3);
				sheet.addCell(lab_4);
				sheet.addCell(lab_5);
				
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
						response.setHeader("Content-Disposition","attachment;fileName=�������������");
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


