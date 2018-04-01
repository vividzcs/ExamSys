package com.nwuer.entity;

import javax.persistence.CascadeType;
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
 * 注册考试表
 * @author vividzc
 *
 */
@Entity
@Table(name="t_exam_register")
public class ExamRegister {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int r_id;
	private long create_time;
	private byte status;  //是否参加考试
	
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="stu_register")
	private Student student; //注册考试的学生
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="sub_register")
	private Subject subject;
	@OneToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="stu_reg_exam_reg")
	private StudentRegister studentRegister;  //一对一双向关联
//	@ManyToOne(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
//	@JoinColumn(name="paper_register", nullable=true)
//	private Paper paper;
//	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
//	@JoinColumn(name="teacher_register")
//	private Teacher teacher;
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}
