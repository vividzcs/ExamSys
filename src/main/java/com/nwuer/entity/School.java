package com.nwuer.entity;

/**
 * ѧУ����Ϣ��浽config.properties�ļ���
 * @author vividzc
 *
 */
public class School {
	private String sch_number; //ѧУ���  5λ
	private String sch_name; //����
	private String sch_address;
	private String sch_website;
	private String sch_desc; //���
	private int sch_aca_count;  //Ժϵ��
	private int sch_major_count; //רҵ��
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
