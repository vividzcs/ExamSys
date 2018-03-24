package com.nwuer.entity;

/**
 * 学校的信息会存到config.properties文件中
 * @author vividzc
 *
 */
public class School {
	private String sch_number; //学校编号  5位
	private String sch_name; //名称
	private String sch_address;
	private String sch_website;
	private String sch_desc; //简介
	private int sch_aca_count;  //院系数
	private int sch_major_count; //专业数
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
	public String getSch_number() {
		return sch_number;
	}
	public void setSch_number(String sch_number) {
		this.sch_number = sch_number;
	}
}
