package com.nwuer.utils;

import org.springframework.stereotype.Component;

/**
 * ��֤������
 * @author vividzc
 * ��֤ͨ������null,���򷵻ش�����Ϣ
 */
@Component
public class ValidateUtil {
	
	/**
	 * �Ƿ�Ϊ��
	 * @param str
	 * @return
	 */
	public String validateEmpty(String str) {
		return "".equals(str) ? "�ֶβ���Ϊ��" : null;
	}
	
	/**
	 * ��֤�����Ƿ���ȷ
	 * @param str
	 * @return
	 */
	public String validateLength(String str, int len) {
		if(str == null)
			return "�ֶθ�ʽ����";
		return str.length() == len ? null : "�ֶγ��ȴ���";
	}
	
	public String validateMinLength(String str, int len) {
		if(str == null)
			return "�ֶθ�ʽ����";
		return str.length() >= len ? null : "�ֶγ��ȴ���";
	}
	/**
	 * ��֤�ַ���Ϊ�����ַ���,���ҳ���ȷ��
	 * @param number
	 * @return
	 */
	public String validateNumber(String number,int len) {
		if(!number.matches("[0-9]{10}")) {
			return "�ֶθ�ʽ����";
		}else
			return null;
	}
	
	
}
