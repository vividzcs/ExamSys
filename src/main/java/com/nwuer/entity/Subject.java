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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_subject")
public class Subject {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int sub_id;
	private String sub_name;
	@Column(length=8)
	private long create_time;  //创建时间
	private String sub_number; //科目编号
	@Column(columnDefinition="text")
	private String sub_desc;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="major_sub")
	private Major major; //专业
	
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public String getSub_number() {
		return sub_number;
	}
	public void setSub_number(String sub_number) {
		this.sub_number = sub_number;
	}
	public String getSub_desc() {
		return sub_desc;
	}
	public void setSub_desc(String sub_desc) {
		this.sub_desc = sub_desc;
	}
}
