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
@Table(name="t_judge_question")
public class JudgeQuestion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int jud_id;
	@Column(length=500)
	private String jud_question;
	private byte jud_answer; //0:F , 1:T
	private byte degree;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="sub_judge")
	private Subject subject;
	public int getJud_id() {
		return jud_id;
	}
	public void setJud_id(int jud_id) {
		this.jud_id = jud_id;
	}
	public String getJud_question() {
		return jud_question;
	}
	public void setJud_question(String jud_question) {
		this.jud_question = jud_question;
	}
	public byte getJud_answer() {
		return jud_answer;
	}
	public void setJud_answer(byte jud_answer) {
		this.jud_answer = jud_answer;
	}
	public byte getDegree() {
		return degree;
	}
	public void setDegree(byte degree) {
		this.degree = degree;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}
