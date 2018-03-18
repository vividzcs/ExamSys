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
@Table(name="t_admin")
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ad_id;
	@Column(length=10)
	private String ad_number; //����
	@Column(length=32)
	private String ad_pass;
	private String ad_name;
	private long last_login;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="a_id",referencedColumnName="a_id")
	private Academy academy;

	public int getAd_id() {
		return ad_id;
	}

	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}

	public String getAd_number() {
		return ad_number;
	}

	public void setAd_number(String ad_number) {
		this.ad_number = ad_number;
	}

	public String getAd_pass() {
		return ad_pass;
	}

	public void setAd_pass(String ad_pass) {
		this.ad_pass = ad_pass;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public long getLast_login() {
		return last_login;
	}

	public void setLast_login(long last_login) {
		this.last_login = last_login;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
}