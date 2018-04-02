package com.nwuer.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_academy",indexes=@Index(name="academy_index_number",columnList="a_number",unique=true))
public class Academy {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int a_id;
	private String a_name;
	@Column(columnDefinition="int not null default 0")
	private int a_num;  //专业数
	private long create_time;
	@Column(length=4,unique=true)
	private String a_number; //院系编号
	
	@OneToMany(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="academy_teacher")
	private Set<Teacher> t_set = new HashSet<Teacher>();
	
	@OneToMany(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="academy_major")
	private Set<Major> m_set = new HashSet<Major>();

	public int getA_num() {
		return a_num;
	}

	public void setA_num(int a_num) {
		this.a_num = a_num;
	}

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public Set<Teacher> getT_set() {
		return t_set;
	}

	public void setT_set(Set<Teacher> t_set) {
		this.t_set = t_set;
	}

	public Set<Major> getM_set() {
		return m_set;
	}

	public void setM_set(Set<Major> m_set) {
		this.m_set = m_set;
	}
	public String getA_number() {
		return a_number;
	}

	public void setA_number(String a_number) {
		this.a_number = a_number;
	}
}
