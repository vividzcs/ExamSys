package com.nwuer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_school")
public class School {
	public String getSch_address() {
		return sch_address;
	}
	public void setSch_address(String sch_address) {
		this.sch_address = sch_address;
	}
	public String getSch_website() {
		return sch_website;
	}
	public void setSch_website(String sch_website) {
		this.sch_website = sch_website;
	}
	public String getSch_code() {
		return sch_code;
	}
	public void setSch_code(String sch_code) {
		this.sch_code = sch_code;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int sch_id;
	@Column(length=5)
	private String sch_num; //学校编号  5位
	private String sch_name; //名称
	private String sch_address;
	private String sch_website;
	@Column(length=6)
	private String sch_code;
	@Column(columnDefinition="text")
	private String sch_desc; //简介
	@Column(columnDefinition="int default 0")
	private int sch_aca_count;  //院系数
	@Column(columnDefinition="int default 0")
	private int sch_major_count; //专业数
	public int getSch_id() {
		return sch_id;
	}
	public void setSch_id(int sch_id) {
		this.sch_id = sch_id;
	}
	public String getSch_num() {
		return sch_num;
	}
	public void setSch_num(String sch_num) {
		this.sch_num = sch_num;
	}
	public String getSch_name() {
		return sch_name;
	}
	public void setSch_name(String sch_name) {
		this.sch_name = sch_name;
	}
	public String getSch_desc() {
		return sch_desc;
	}
	public void setSch_desc(String sch_desc) {
		this.sch_desc = sch_desc;
	}
	public int getSch_aca_count() {
		return sch_aca_count;
	}
	public void setSch_aca_count(int sch_aca_count) {
		this.sch_aca_count = sch_aca_count;
	}
	public int getSch_major_count() {
		return sch_major_count;
	}
	public void setSch_major_count(int sch_major_count) {
		this.sch_major_count = sch_major_count;
	}
}
