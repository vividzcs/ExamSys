package com.nwuer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 注册学生表,只有这张表的学生才能注册考试
 * @author vividzc
 *
 */
@Entity
@Table(name="t_student_register")
public class StudentRegister {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int sr_id;
	@Column(length=10)
	private String sr_number;  //学生学号
	private String sr_name; //学生姓名
	private long create_time; 
	
	private byte status; //0:未注册, 1:已注册, 2:考试中, 3: 已考完
	
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="major_stu_reg")
	private Major major;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="sub_stu_reg")
	private Subject subject;
//	@OneToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
//	@JoinColumn(name="exam_stu_reg")//学生注册表用来关联 学生,试卷,科目
//	private ExamRegister examRegister;
	private Integer r_id; //考试注册表
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="examhistory_stu_reg")
	private ExamHistory examHistory;
	private String paper;  //直接存纸卷的uuid
	
	public int getSr_id() {
		return sr_id;
	}
	public void setSr_id(int sr_id) {
		this.sr_id = sr_id;
	}
	public String getSr_number() {
		return sr_number;
	}
	public void setSr_number(String sr_number) {
		this.sr_number = sr_number;
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
	public Integer getR_id() {
		return r_id;
	}
	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}
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
