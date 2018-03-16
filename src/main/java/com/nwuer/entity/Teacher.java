package com.nwuer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_teacher")  //Entity没注明映射表的名称时默认和name和table是同一值
public class Teacher {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int t_id;
	@Column(length=10)
	private String t_number; 
	private String t_pass;
	private int aca_id;
	private String t_name;
	private long create_time;
	private long last_login;
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
	public int getAca_id() {
		return aca_id;
	}
	public void setAca_id(int aca_id) {
		this.aca_id = aca_id;
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
}
