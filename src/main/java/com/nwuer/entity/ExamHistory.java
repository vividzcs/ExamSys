package com.nwuer.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 此表记录学生的考试情况
 * @author vividzc
 *
 */
@Entity
@Table(name="t_exam_history")
public class ExamHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int h_id;
	@Column(columnDefinition="float not null default 0.0")
	private double grade;
	private long putin_time; //交卷时间
//	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
//	@JoinColumn(name="stu_history")
//	private Student student;   //学生是多的一方
	@OneToMany(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="reg_history")
	private List<StudentRegister> studentRegister;
	public int getH_id() {
		return h_id;
	}
	public void setH_id(int h_id) {
		this.h_id = h_id;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public long getPutin_time() {
		return putin_time;
	}
	public void setPutin_time(long putin_time) {
		this.putin_time = putin_time;
	}
	public List<StudentRegister> getStudentRegister() {
		return studentRegister;
	}
	public void setStudentRegister(List<StudentRegister> studentRegister) {
		this.studentRegister = studentRegister;
	}
	
}
