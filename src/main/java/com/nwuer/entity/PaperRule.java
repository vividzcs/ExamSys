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
@Table(name="t_paper_rule")
public class PaperRule {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int p_id;
	private String p_name; //规则名称
	@Column(columnDefinition="tinyint not null default 0")
	private byte degree; //难度
	@Column(columnDefinition="int not null default 100")
	private int full_score; //总分
	@Column(columnDefinition="tinyint not null default 0")
	private int single_choice_num; //单选个数
	@Column(columnDefinition="tinyint not null default 0")
	private int single_choice_score;//单选分数
	@Column(columnDefinition="tinyint not null default 0")
	private int judge_num; //判断题个数
	@Column(columnDefinition="tinyint not null default 0")
	private int judge_score; //判断题分数
	@Column(columnDefinition="tinyint not null default 0")
	private int blank_num;  //填空题个数
	@Column(columnDefinition="tinyint not null default 0")
	private int blank_score; //填空题分数
	@Column(columnDefinition="tinyint not null default 0")
	private int translate_num; //名词解释题个数
	@Column(columnDefinition="tinyint not null default 0")
	private int translate_score;  //名词解释题分数
	@Column(columnDefinition="tinyint not null default 0")
	private int simple_question_num; //简答题个数
	@Column(columnDefinition="tinyint not null default 0")
	private int simple_question_score; //简答题分数
	@Column(columnDefinition="tinyint not null default 0")
	private int compute_num; //计算题个数
	@Column(columnDefinition="tinyint not null default 0")
	private int compute_score; //计算题分数
	@Column(columnDefinition="tinyint not null default 0")
	private int mix_num; //综合题个数
	@Column(columnDefinition="tinyint not null default 0")
	private int mix_score;//综合体分数
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="major_paper")
	private Major major;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="sub_paper")
	private Subject subject;
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public byte getDegree() {
		return degree;
	}
	public void setDegree(byte degree) {
		this.degree = degree;
	}
	public int getFull_score() {
		return full_score;
	}
	public void setFull_score(int full_score) {
		this.full_score = full_score;
	}
	public int getSingle_choice_num() {
		return single_choice_num;
	}
	public void setSingle_choice_num(int single_choice_num) {
		this.single_choice_num = single_choice_num;
	}
	public int getSingle_choice_score() {
		return single_choice_score;
	}
	public void setSingle_choice_score(int single_choice_score) {
		this.single_choice_score = single_choice_score;
	}
	public int getJudge_num() {
		return judge_num;
	}
	public void setJudge_num(int judge_num) {
		this.judge_num = judge_num;
	}
	public int getJudge_score() {
		return judge_score;
	}
	public void setJudge_score(int judge_score) {
		this.judge_score = judge_score;
	}
	public int getBlank_num() {
		return blank_num;
	}
	public void setBlank_num(int blank_num) {
		this.blank_num = blank_num;
	}
	public int getBlank_score() {
		return blank_score;
	}
	public void setBlank_score(int blank_score) {
		this.blank_score = blank_score;
	}
	public int getTranslate_num() {
		return translate_num;
	}
	public void setTranslate_num(int translate_num) {
		this.translate_num = translate_num;
	}
	public int getTranslate_score() {
		return translate_score;
	}
	public void setTranslate_score(int translate_score) {
		this.translate_score = translate_score;
	}
	public int getSimple_question_num() {
		return simple_question_num;
	}
	public void setSimple_question_num(int simple_question_num) {
		this.simple_question_num = simple_question_num;
	}
	public int getSimple_question_score() {
		return simple_question_score;
	}
	public void setSimple_question_score(int simple_question_score) {
		this.simple_question_score = simple_question_score;
	}
	public int getCompute_num() {
		return compute_num;
	}
	public void setCompute_num(int compute_num) {
		this.compute_num = compute_num;
	}
	public int getCompute_score() {
		return compute_score;
	}
	public void setCompute_score(int compute_score) {
		this.compute_score = compute_score;
	}
	public int getMix_num() {
		return mix_num;
	}
	public void setMix_num(int mix_num) {
		this.mix_num = mix_num;
	}
	public int getMix_score() {
		return mix_score;
	}
	public void setMix_score(int mix_score) {
		this.mix_score = mix_score;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}
