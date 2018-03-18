package com.nwuer.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mD5Util")
public class MD5Util {
	@Value("${salt}")
	private String salt;
	public String encrypt(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			String text = str + salt;
			md.update(text.getBytes());
			
			byte[] bytes = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for(byte b : bytes) {
				int a = b & 0xff;
				String hex = Integer.toHexString(a);
				if(hex.length() == 1) {
					hex = 0 + hex;
				}
				sb.append(hex);
			}
			
			return sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
