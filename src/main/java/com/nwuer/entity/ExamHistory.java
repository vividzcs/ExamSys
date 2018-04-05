package com.nwuer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Column(length=10)
	private String sr_number;  //学生学号
	private String sr_name; //学生姓名
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
	public String getSr_number() {
		return sr_number;
	}
	public void setSr_number(String sr_number) {
		this.sr_number = sr_number;
	}
	public String getSr_name() {
		return sr_name;
	}
	public void setSr_name(String sr_name) {
		this.sr_name = sr_name;
	}
	
}
