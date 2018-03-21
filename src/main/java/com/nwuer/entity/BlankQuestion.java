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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_blank_question")
public class BlankQuestion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int blank_id;
	@Column(length=500)
	private String blank_question;
	private String blank_answer;
	private byte degree;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	private Subject subject;
	public int getBlank_id() {
		return blank_id;
	}
	public void setBlank_id(int blank_id) {
		this.blank_id = blank_id;
	}
	public String getBlank_question() {
		return blank_question;
	}
	public void setBlank_question(String blank_question) {
		this.blank_question = blank_question;
	}
	public String getBlank_answer() {
		return blank_answer;
	}
	public void setBlank_answer(String blank_answer) {
		this.blank_answer = blank_answer;
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
