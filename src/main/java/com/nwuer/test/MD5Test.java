package com.nwuer.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class MD5Test {
	@Test
	public void test() {
		System.out.println(md5("nwuer"));
		System.out.println(System.currentTimeMillis());
	}
	
	public String md5(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
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
