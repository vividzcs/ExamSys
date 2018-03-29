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
 * �Ծ��
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
	private String pap_url; //���ɵ��Ծ���λ��
	@Column(columnDefinition="tinyint not null default 0")
	private byte pap_kind; //�Ծ�����, 0:����, 1:��ϰ
	private long start_time;
	private long end_time;
	private long create_time;
	@Column(columnDefinition="text")
	private String pap_desc;
	
	private String pap_answer_url;  //ֱ�Ӵ�һ���ı���Ϣ
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
