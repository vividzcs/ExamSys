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
@Table(name="t_choice_question")
public class ChoiceQuestion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int cho_id; 
	@Column(length=500)
	private String cho_question; //题目
	private String cho_answer; //正确答案
	private String cho_answer_1;
	private String cho_answer_2;
	private String cho_answer_3;
	private byte degree; //难度  0:简单  1:较简单  2:中等  3 较难  4:较难
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
	@JoinColumn(name="sub_id")
	private Subject subject;
	public int getCho_id() {
		return cho_id;
	}
	public void setCho_id(int cho_id) {
		this.cho_id = cho_id;
	}
	public String getCho_question() {
		return cho_question;
	}
	public void setCho_question(String cho_question) {
		this.cho_question = cho_question;
	}
	public String getCho_answer() {
		return cho_answer;
	}
	public void setCho_answer(String cho_answer) {
		this.cho_answer = cho_answer;
	}
	public String getCho_answer_1() {
		return cho_answer_1;
	}
	public void setCho_answer_1(String cho_answer_1) {
		this.cho_answer_1 = cho_answer_1;
	}
	public String getCho_answer_2() {
		return cho_answer_2;
	}
	public void setCho_answer_2(String cho_answer_2) {
		this.cho_answer_2 = cho_answer_2;
	}
	public String getCho_answer_3() {
		return cho_answer_3;
	}
	public void setCho_answer_3(String cho_answer_3) {
		this.cho_answer_3 = cho_answer_3;
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
