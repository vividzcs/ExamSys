package com.nwuer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 注册学生表,只有这张表的学生才能注册考试
 * @author vividzc
 *
 */
@Entity
@Table(name="t_student_register",
		indexes= {@Index(name="stu_reg_sr_number", columnList="sr_number"),
					@Index(name="uuid",columnList="paper")
})
public class StudentRegister {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int sr_id;
	@Column(length=10)
	private String sr_number;  //学生学号
	private String sr_name; //学生姓名
	private long create_time; 
	
	private byte status; //0:未注册, 1:已注册未生成试卷, 2:已注册已生成试卷  3:考试中, 4: 已考完, 5:已阅卷
	
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="major_stu_reg")
	private Major major;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="sub_stu_reg")
	private Subject subject;
//	@OneToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
//	@JoinColumn(name="exam_stu_reg")//学生注册表用来关联 学生,试卷,科目
//	private ExamRegister examRegister;
//	private Integer r_id; //考试注册表
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="examhistory_stu_reg")
	private ExamHistory examHistory;
	private String paper;  //直接存纸卷的uuid
	private Double grade;
	@Column(columnDefinition="int not null default 0")
	private int t_id;  //到时阅卷时选择 t_id in(0,本教师id)&&status=4
	
	@Transient
	private GuardianShip guardianShip;
	
	public GuardianShip getGuardianShip() {
		return guardianShip;
	}
	public void setGuardianShip(GuardianShip guardianShip) {
		this.guardianShip = guardianShip;
	}
	public int getSr_id() {
		return sr_id;
	}
	public void setSr_id(int sr_id) {
		this.sr_id = sr_id;
	}
	public String getSr_number() {
		return sr_number;
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public void setSr_number(String sr_number) {
		this.sr_number = sr_number;
	}
	public Double getGrade() {
		return grade;
	}
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	public String getSr_name() {
		return sr_name;
	}
	public byte getStatus() {
		return status;
	}
	public long getCreate_time() {
		return create_time;
	}
//	public Integer getR_id() {
//		return r_id;
//	}
//	public void setR_id(Integer r_id) {
//		this.r_id = r_id;
//	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public ExamHistory getExamHistory() {
		return examHistory;
	}
	public void setExamHistory(ExamHistory examHistory) {
		this.examHistory = examHistory;
	}
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	public void setSr_name(String sr_name) {
		this.sr_name = sr_name;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	
}
