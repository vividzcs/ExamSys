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
@Table(name="t_subjective_question")
public class SubjectiveQuestion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int sq_id;
	@Column(columnDefinition="text")
	private String sq_question;
	@Column(columnDefinition="text")
	private String sq_answer;
	private int chapter; //章节
	@Column(columnDefinition="tinyint not null default 0")
	private byte degree;
	private long create_time;  //创建时间
	private byte sq_kind; // 0:名词解释,1:填空,2:简答,3:计算,4:综合
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="major_subj")
	private Major major = new Major();
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="sub_subj")
	private Subject subject = new Subject();
	
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public int getSq_id() {
		return sq_id;
	}
	public void setSq_id(int sq_id) {
		this.sq_id = sq_id;
	}
	public String getSq_question() {
		return sq_question;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	public void setSq_question(String sq_question) {
		this.sq_question = sq_question;
	}
	public String getSq_answer() {
		return sq_answer;
	}
	public void setSq_answer(String sq_answer) {
		this.sq_answer = sq_answer;
	}
	public byte getDegree() {
		return degree;
	}
	public void setDegree(byte degree) {
		this.degree = degree;
	}
	public byte getSq_kind() {
		return sq_kind;
	}
	public void setSq_kind(byte sq_kind) {
		this.sq_kind = sq_kind;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}
