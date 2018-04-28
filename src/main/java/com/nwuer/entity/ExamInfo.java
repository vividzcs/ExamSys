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
 * 考试信息监控
 * @author vividzc
 * 试卷生成规则生成的时候考场信息就已经生成好了
 * 导入监考老师时设置监考信息
 * 考试人数即导入注册学生时确定
 * 注册人数是学生注册时++
 * 到达人数是点击开始考试时++
 */
@Entity
@Table(name="t_exam_info")
public class ExamInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int e_id;
	@Column(columnDefinition="tinyint default 0")
	private byte is_change_paper;
	@Column(columnDefinition="tinyint default 0")
	private byte status;  // 0:未开始  1:考试中 2:已结束
	
	@Column(columnDefinition="int not null default 0")
	private int exam_num_all; //考试总人数,即StudentRegister得人数
	@Column(columnDefinition="int not null default 0")
	private int exam_num; //即注册的人数ExamRegister的数目
	@Column(columnDefinition="int not null default 0")
	private int exam_num_reach;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="major_exam_info")
	private Major major = new Major();
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="sub_exam_info")
	private Subject subject = new Subject();
	
//	@OneToOne(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
//	@JoinColumn(name="guard_info")
//	private GuardianShip guard_ship;
//	@OneToOne(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
//	@JoinColumn(name="rule_info")
//	private PaperRule rule;   //考试状态和试卷生成规则共生,在删除规则时也就没有了这个信息
	
	private Integer g_id; //监考人员
	private int p_id; //试卷生成规则
	
	@Transient
	private GuardianShip guardianShip;
	@Transient
	private PaperRule rule;
	
	public GuardianShip getGuardianShip() {
		return guardianShip;
	}

	public void setGuardianShip(GuardianShip guardianShip) {
		this.guardianShip = guardianShip;
	}

	public PaperRule getRule() {
		return rule;
	}

	public void setRule(PaperRule rule) {
		this.rule = rule;
	}

	public int getE_id() {
		return e_id;
	}

	public Integer getG_id() {
		return g_id;
	}

	public void setG_id(Integer g_id) {
		this.g_id = g_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
	}

	public byte getIs_change_paper() {
		return is_change_paper;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public void setIs_change_paper(byte is_change_paper) {
		this.is_change_paper = is_change_paper;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}


	public int getExam_num_all() {
		return exam_num_all;
	}

	public void setExam_num_all(int exam_num_all) {
		this.exam_num_all = exam_num_all;
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

	public int getExam_num() {
		return exam_num;
	}

	public void setExam_num(int exam_num) {
		this.exam_num = exam_num;
	}

	public int getExam_num_reach() {
		return exam_num_reach;
	}

	public void setExam_num_reach(int exam_num_reach) {
		this.exam_num_reach = exam_num_reach;
	}

	
}
