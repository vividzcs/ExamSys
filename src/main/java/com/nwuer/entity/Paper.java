package com.nwuer.entity;
/**
 * �Ծ��
 * @author vividzc
 *
 */
public class Paper {
	private int pap_id;
	private String pap_url; //���ɵ��Ծ���λ��
	private byte pap_kind; //�Ծ�����, 0:����, 1:��ϰ
	private long start_time;
	private long end_time;
	private long create_time;
	private String pap_desc;
	
	private String pap_answer_url;  //ֱ�Ӵ�һ���ı���Ϣ
	
	private Subject subject;
	
	private PaperRule paperRule;
	
	private ExamRegister examRegister;
	
}
