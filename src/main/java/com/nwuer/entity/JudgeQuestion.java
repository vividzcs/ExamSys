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
	private int chapter; //章节
	@Column(columnDefinition="tinyint not null default 0")
	private byte degree; //难度
	private long create_time;  //创建时间
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="major_judge")
	private Major major = new Major();
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="sub_judge")
	private Subject subject = new Subject();
	
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public int getJud_id() {
		return jud_id;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
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
