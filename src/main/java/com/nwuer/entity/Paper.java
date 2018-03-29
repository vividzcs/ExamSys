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

/**
 * 试卷表
 * @author vividzc
 *
 */
@Entity
@Table(name="t_paper")
public class Paper {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int pap_id;
	private String pap_url; //生成的试卷存放位置
	@Column(columnDefinition="tinyint not null default 0")
	private byte pap_kind; //试卷类型, 0:考试, 1:练习
	private long start_time;
	private long end_time;
	private long create_time;
	@Column(columnDefinition="text")
	private String pap_desc;
	
	private String pap_answer_url;  //直接存一段文本信息
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="sub_paper")
	private Subject subject;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="rule_paper")
	private PaperRule paperRule;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="reg_paper")
	private ExamRegister examRegister;
	
}
