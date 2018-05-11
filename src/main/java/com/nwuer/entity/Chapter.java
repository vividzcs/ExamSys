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
@Table(name="t_chapter")
public class Chapter {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int cpt_id;
	private int cpt_cpt;  //章节
	@Column(columnDefinition="float not null default 0")
	private int single_choice_num; //单选个数
	@Column(columnDefinition="tinyint not null default 0")
	private int judge_num; //判断题个数
	@Column(columnDefinition="tinyint not null default 0")
	private int blank_num;  //填空题个数
	@Column(columnDefinition="tinyint not null default 0")
	private int translate_num; //名词解释题个数
	@Column(columnDefinition="tinyint not null default 0")
	private int simple_question_num; //简答题个数
	@Column(columnDefinition="tinyint not null default 0")
	private int compute_num; //计算题个数
	@Column(columnDefinition="tinyint not null default 0")
	private int mix_num; //综合题个数
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="rule_chapter")
	private PaperRule rule;
	
	public int getCpt_id() {
		return cpt_id;
	}
	public void setCpt_id(int cpt_id) {
		this.cpt_id = cpt_id;
	}
	public int getCpt_cpt() {
		return cpt_cpt;
	}
	public void setCpt_cpt(int cpt_cpt) {
		this.cpt_cpt = cpt_cpt;
	}
	public PaperRule getRule() {
		return rule;
	}
	public void setRule(PaperRule rule) {
		this.rule = rule;
	}
	public int getSingle_choice_num() {
		return single_choice_num;
	}
	public void setSingle_choice_num(int single_choice_num) {
		this.single_choice_num = single_choice_num;
	}
	public int getJudge_num() {
		return judge_num;
	}
	public void setJudge_num(int judge_num) {
		this.judge_num = judge_num;
	}
	public int getBlank_num() {
		return blank_num;
	}
	public void setBlank_num(int blank_num) {
		this.blank_num = blank_num;
	}
	public int getTranslate_num() {
		return translate_num;
	}
	public void setTranslate_num(int translate_num) {
		this.translate_num = translate_num;
	}
	public int getSimple_question_num() {
		return simple_question_num;
	}
	public void setSimple_question_num(int simple_question_num) {
		this.simple_question_num = simple_question_num;
	}
	public int getCompute_num() {
		return compute_num;
	}
	public void setCompute_num(int compute_num) {
		this.compute_num = compute_num;
	}
	public int getMix_num() {
		return mix_num;
	}
	public void setMix_num(int mix_num) {
		this.mix_num = mix_num;
	}
	
	
}
