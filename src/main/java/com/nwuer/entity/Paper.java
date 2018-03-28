package com.nwuer.entity;
/**
 * 试卷表
 * @author vividzc
 *
 */
public class Paper {
	private int pap_id;
	private String pap_url; //生成的试卷存放位置
	private byte pap_kind; //试卷类型, 0:考试, 1:练习
	private long start_time;
	private long end_time;
	private long create_time;
	private String pap_desc;
	
	private String pap_answer_url;  //直接存一段文本信息
	
	private Subject subject;
	
	private PaperRule paperRule;
	
	private ExamRegister examRegister;
	
}
