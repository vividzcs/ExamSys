package com.nwuer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_subjective_answer",indexes=@Index(name="answer_uuid",columnList="uuid"))
public class SubjectiveAnswer {
	//0:名词解释,1:填空,2:简答,3:计算,4:综合 ,5:选择 ,6:判断
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int answer_id;
	private String uuid; //与试卷关联
	private long create_time;
	private Integer sequence; //主观题在试卷中的题号
	@Column(columnDefinition="text")
	private String answer_right;  //标准答案,主观题一个一个存 
									
	@Column(columnDefinition="text")
	private String answer_write;  //做的答案,主观题一个一个存
	private Double grade;
	
	private Integer sr_id; //学生注册表
	@Column(columnDefinition="tinyint not null default 0")
	private int status; //0 未阅 , 1 已阅
	

	public int getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}

	public String getUuid() {
		return uuid;
	}


	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public String getAnswer_right() {
		return answer_right;
	}

	public void setAnswer_right(String answer_right) {
		this.answer_right = answer_right;
	}

	public String getAnswer_write() {
		return answer_write;
	}

	public void setAnswer_write(String answer_write) {
		this.answer_write = answer_write;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Integer getSr_id() {
		return sr_id;
	}

	public void setSr_id(Integer sr_id) {
		this.sr_id = sr_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
