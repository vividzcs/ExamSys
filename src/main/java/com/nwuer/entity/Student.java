package com.nwuer.entity;

import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_student",indexes=@Index(name="student_index_number",columnList="s_number",unique=true))
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int s_id;
	@Column(length=10,unique=true)
	private String s_number;  //学号
	private String s_pass;  //密码
	private String s_name;
	@Column(columnDefinition="tinyint not null default 1")
	private byte s_sex;
	
	private long create_time;
	private long start_school_time; //入学时间
	@Column(columnDefinition="tinyint default 1")
	private byte status;
	@Column(columnDefinition="bigint not null default 0")
	private long last_login;
	//专业
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="major_stu")
	private Major major;
	//院系
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="academy_stu")
	private Academy academy;
	
	public byte getS_sex() {
		return s_sex;
	}
	public void setS_sex(byte s_sex) {
		this.s_sex = s_sex;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getS_number() {
		return s_number;
	}
	public void setS_number(String s_number) {
		this.s_number = s_number;
	}
	public String getS_pass() {
		return s_pass;
	}
	public void setS_pass(String s_pass) {
		this.s_pass = s_pass;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
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
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public Academy getAcademy() {
		return academy;
	}
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
	public long getLast_login() {
		return last_login;
	}
	public void setLast_login(long last_login) {
		this.last_login = last_login;
	}
	public long getStart_school_time() {
		return start_school_time;
	}
	public void setStart_school_time(long start_school_time) {
		this.start_school_time = start_school_time;
	}
	
}
