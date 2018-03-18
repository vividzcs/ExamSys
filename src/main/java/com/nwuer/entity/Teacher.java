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
@Table(name="t_teacher")  //Entityûע��ӳ��������ʱĬ�Ϻ�name��table��ͬһֵ
public class Teacher {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int t_id;
	@Column(length=10)
	private String t_number;  //���� 
	@Column(length=32)
	private String t_pass;  //����
	private String t_name;  //����
	private byte t_sex;
	public byte getT_sex() {
		return t_sex;
	}
	public void setT_sex(byte t_sex) {
		this.t_sex = t_sex;
	}
	private long create_time;  //����ʱ��
	private long last_login;  //�ϴε�¼ʱ��
	@Column(columnDefinition="TINYINT default 1")
	private byte status;  //�Ƿ��ܵ�¼, 0����,1��
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="a_id")
	private Academy academy;  //һ����ʦ��Ӧһ��Ժϵ
	
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
