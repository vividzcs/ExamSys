package com.nwuer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 监考阅卷人员
 * @author vividzc
 *
 */
@Entity
@Table(name="t_guardianship")
public class GuardianShip {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int g_id;
	
	private long create_time;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="major_guard_ship")
	private Major major;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="subject_guard_ship")
	private Subject subject;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="guard1_guard_ship")
	private Teacher guard_1;
	
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="guard2_guard_ship")
	private Teacher guard_2;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="read1_guard_ship")
	private Teacher read_1;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="read2_guard_ship")
	private Teacher read_2;
	@ManyToOne(cascade=CascadeType.DETACH,fetch=FetchType.LAZY)
	@JoinColumn(name="read3_guard_ship")
	private Teacher read_3;
	@OneToOne(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
	@JoinColumn(name="paper_guard")
	private Paper paper;
	
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public Paper getPaper() {
		return paper;
	}
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public Teacher getGuard_1() {
		return guard_1;
	}
	public void setGuard_1(Teacher guard_1) {
		this.guard_1 = guard_1;
	}
	public Teacher getGuard_2() {
		return guard_2;
	}
	public void setGuard_2(Teacher guard_2) {
		this.guard_2 = guard_2;
	}
	public Teacher getRead_1() {
		return read_1;
	}
	public void setRead_1(Teacher read_1) {
		this.read_1 = read_1;
	}
	public Teacher getRead_2() {
		return read_2;
	}
	public void setRead_2(Teacher read_2) {
		this.read_2 = read_2;
	}
	public Teacher getRead_3() {
		return read_3;
	}
	public void setRead_3(Teacher read_3) {
		this.read_3 = read_3;
	}
	
	
	
}
