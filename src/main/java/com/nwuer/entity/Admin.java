package com.nwuer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_admin")
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int ad_id;
	@Column(length=10)
	private String ad_number; //¹¤ºÅ
	private String ad_pass;
	private String ad_name;
	@Column(columnDefinition="bigint not null default 0")
	private Long last_login;
	
	public void setLast_login(Long last_login) {
		this.last_login = last_login;
	}

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
}
