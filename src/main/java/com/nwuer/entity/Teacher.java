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
import javax.persistence.Table;

@Entity
@Table(name="t_teacher")  //Entity没注明映射表的名称时默认和name和table是同一值
public class Teacher {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int t_id;
	@Column(length=10)
	private String t_number;  //工号 
	@Column(length=32)
	private String t_pass;  //密码
	private String t_name;  //姓名
	private byte t_sex;
	public byte getT_sex() {
		return t_sex;
	}
	public void setT_sex(byte t_sex) {
		this.t_sex = t_sex;
	}
	private long create_time;  //创建时间
	private long last_login;  //上次登录时间
	@Column(columnDefinition="TINYINT default 1")
	private byte status;  //是否能登录, 0不能,1能
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="a_id")
	private Academy academy;  //一个老师对应一个院系
	
	public Academy getAcademy() {
		return academy;
	}
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getT_number() {
		return t_number;
	}
	public void setT_number(String t_number) {
		this.t_number = t_number;
	}
	public String getT_pass() {
		return t_pass;
	}
	public void setT_pass(String t_pass) {
		this.t_pass = t_pass;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public long getLast_login() {
		return last_login;
	}
	public void setLast_login(long last_login) {
		this.last_login = last_login;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
}
