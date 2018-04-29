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
		return "".equals(str) ? "字段不能为空" : null;
	}
	
	/**
	 * 验证长度是否正确
	 * @param str
	 * @return
	 */
	public String validateLength(String str, int len) {
		if(str == null)
			return "字段格式错误";
		return str.length() == len ? null : "字段长度错误";
	}
	
	public String validateMinLength(String str, int len) {
		if(str == null)
			return "字段格式错误";
		return str.length() >= len ? null : "字段长度错误";
	}
	/**
	 * 验证字符串为数字字符串,并且长度确定
	 * @param number
	 * @return
	 */
	public String validateNumber(String number,int len) {
		if(number == null)
			return "字段格式或长度错误";
		
		if(!number.matches("[0-9]{"+len+"}")) {
			return "字段格式错误";
		}else
			return null;
	}
	/**
	 * 是否是数字
	 * @param number
	 * @return
	 */
	public String isNumber(String number) {
		if(number == null||number.equals(""))
			return "字段格式或长度错误";
		
		if(!number.matches("[0-9]{0,10}")) {
			return "字段格式或长度错误";
		}else
			return null;
		
	}
	/**
	 * 是否是0或1
	 * @param sex
	 * @return
	 */
	public String isSex(byte sex) {
		if(sex != 0 && sex != 1)
			return "字段格式错误";
		else
			return null;
	}
	
	public String isDate(String date) {
		if(date == null)
			return "字段格式或长度错误";
		
		if(!date.matches("[0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}")) 
			return "字段格式或长度错误";
		else
			return null;
	}
	
	public boolean isDouble(String d) {
		String pattern = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][-+]?\\d+)?[dD]?$";
		return d.matches(pattern);
	}
	
}
