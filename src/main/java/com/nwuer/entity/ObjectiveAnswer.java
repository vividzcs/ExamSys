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
@Table(name="t_objective_answer",indexes=@Index(name="answer_uuid",columnList="uuid"))
public class ObjectiveAnswer {
	//0:名词解释,1:填空,2:简答,3:计算,4:综合 ,5:选择 ,6:判断
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int answer_id;
	private String uuid; //与试卷关联
	private long create_time;
	@Column(columnDefinition="text")
	private String answer_right;  //标准答案,客观题所有的答案以一定格式连接成字符串,主观题一个一个存  5_0_0 A,5_0_1 B, 6_0_0 F,6_0_1 T
									// "5_0_0,5_0_1,6_0_0,6_0_1"这种形式存在数据库里
	@Column(columnDefinition="text")
	private String answer_write;  //标准答案,客观题所有的答案以一定格式连接成字符串,主观题一个一个存
									//客观题直接算好分了
	private Double grade;
	
	private Integer sr_id; //学生注册表

	public int getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}

	public String getAnswer_right() {
		return answer_right;
	}

	public String getUuid() {
		return uuid;
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
	
}
