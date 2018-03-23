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
	private String cho_question; //��Ŀ
	private String cho_answer; //��ȷ��
	private String cho_choice_1;
	private String cho_choice_2;
	private String cho_choice_3;
	@Column(columnDefinition="tinyint not null default 0")
	private byte degree; //�Ѷ�  0:��  1:�ϼ�  2:�е�  3 ����  4:����
	private long create_time;  //����ʱ��
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
	@JoinColumn(name="sub_choice")
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
	public String getCho_choice_1() {
		return cho_choice_1;
	}
	public void setCho_choice_1(String cho_choice_1) {
		this.cho_choice_1 = cho_choice_1;
	}
	public String getCho_choice_2() {
		return cho_choice_2;
	}
	public void setCho_choice_2(String cho_choice_2) {
		this.cho_choice_2 = cho_choice_2;
	}
	public String getCho_choice_3() {
		return cho_choice_3;
	}
	public void setCho_choice_3(String cho_choice_3) {
		this.cho_choice_3 = cho_choice_3;
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
