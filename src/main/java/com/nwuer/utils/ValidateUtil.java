package com.nwuer.utils;

import org.springframework.stereotype.Component;

/**
 * 验证工具类
 * @author vividzc
 * 验证通过返回null,否则返回错误信息
 */
@Component
public class ValidateUtil {
	
	/**
	 * 是否为空
	 * @param str
	 * @return
	 */
	public String validateEmpty(String str) {
		return "".equals(str) ? "此字段不能为空" : null;
	}
	
	/**
	 * 验证长度是否正确
	 * @param str
	 * @return
	 */
	public String validateLength(int len, String str) {
		if(str == null)
			return "此字段格式错误";
		return str.length() == len ? null : "此字段长度错误";
	}
	
	
}
