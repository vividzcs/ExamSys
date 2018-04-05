package com.nwuer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 考试信息监控
 * @author vividzc
 *
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
	private byte status;
	
	@Column(columnDefinition="int not null default 0")
	private int exam_num_all; //考试总人数,即StudentRegister得人数
	@Column(columnDefinition="int not null default 0")
	private int exam_num; //即注册的人数ExamRegister的数目
	@Column(columnDefinition="int not null default 0")
	private int exam_num_reach;
	
//	@OneToOne(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
//	@JoinColumn(name="guard_info")
//	private GuardianShip guard_ship;
	@OneToOne(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
	@JoinColumn(name="rule_info")
	private PaperRule rule;   //考试状态和试卷生成规则共生,在删除规则时也就没有了这个信息
	
	private Integer g_id;
	
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

	public void setIs_change_paper(byte is_change_paper) {
		this.is_change_paper = is_change_paper;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public PaperRule getRule() {
		return rule;
	}

	public void setRule(PaperRule rule) {
		this.rule = rule;
	}

	public int getExam_num_all() {
		return exam_num_all;
	}

	public void setExam_num_all(int exam_num_all) {
		this.exam_num_all = exam_num_all;
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
