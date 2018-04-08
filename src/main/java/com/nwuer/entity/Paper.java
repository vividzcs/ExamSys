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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@GeneratedValue(strategy=GenerationType.AUTO,generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	private String pap_id;
	private String pap_url; //生成的试卷存放位置
	@Column(columnDefinition="tinyint not null default 0")
	private byte pap_kind; //试卷类型, 0:练习, 1:考试
	
	private long create_time;
	private byte status;  //0:刚生成, 1:考试中, 2 已交卷, 3 已阅卷, 
	
//	private Integer subjective_answer_id;  //关联答案表
//	private Integer objective_answer_id;  //关联答案表
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
	@JoinColumn(name="sub_paper")
	private Subject subject;
//	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
//	@JoinColumn(name="rule_paper")
//	private PaperRule paperRule;
//	@OneToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
//	@JoinColumn(name="reg_paper")
//	private StudentRegister studentRegister;
//	@OneToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
//	@JoinColumn(name="guard_paper")
//	private GuardianShip guardianShip;
	private Integer p_id; //试卷生成规则表的id
	private Integer sr_id; //学生注册表的id
	private Integer g_id; //监考人员表的id
	
	@Transient
	private PaperRule paperRule;
	@Transient
	private StudentRegister studentRegister;
	
	
	public StudentRegister getStudentRegister() {
		return studentRegister;
	}
	public void setStudentRegister(StudentRegister studentRegister) {
		this.studentRegister = studentRegister;
	}
	public PaperRule getPaperRule() {
		return paperRule;
	}
	public void setPaperRule(PaperRule paperRule) {
		this.paperRule = paperRule;
	}
	public String getPap_id() {
		return pap_id;
	}
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public Integer getG_id() {
		return g_id;
	}
	public void setG_id(Integer g_id) {
		this.g_id = g_id;
	}
	public Integer getSr_id() {
		return sr_id;
	}
	public void setSr_id(Integer sr_id) {
		this.sr_id = sr_id;
	}
	public void setPap_id(String pap_id) {
		this.pap_id = pap_id;
	}
	public String getPap_url() {
		return pap_url;
	}
	public void setPap_url(String pap_url) {
		this.pap_url = pap_url;
	}
	public byte getPap_kind() {
		return pap_kind;
	}
	public void setPap_kind(byte pap_kind) {
		this.pap_kind = pap_kind;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}
