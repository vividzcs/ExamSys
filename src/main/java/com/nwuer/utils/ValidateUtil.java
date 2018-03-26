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
		return "".equals(str) ? "���ֶβ���Ϊ��" : null;
	}
	
	/**
	 * ��֤�����Ƿ���ȷ
	 * @param str
	 * @return
	 */
	public String validateLength(int len, String str) {
		if(str == null)
			return "���ֶθ�ʽ����";
		return str.length() == len ? null : "���ֶγ��ȴ���";
	}
	
	
}
