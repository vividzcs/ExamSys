package com.nwuer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_subjective_question")
public class SubjectiveQuestion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int sq_id;
	@Column(columnDefinition="text")
	private String sq_question;
	@Column(columnDefinition="text")
	private String sq_answer;
	private byte degree;
	private byte sq_kind; // 0:名词解释,1:简答,2:计算,3:综合
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Subject subject;
	public int getSq_id() {
		return sq_id;
	}
	public void setSq_id(int sq_id) {
		this.sq_id = sq_id;
	}
	public String getSq_question() {
		return sq_question;
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
