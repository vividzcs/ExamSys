package com.nwuer.test;

import org.junit.Test;

public class ValidateTest {
	@Test
	public void test() {
		String str = "2015";
		System.out.println(str.matches("[0-9]{0,10}"));
	}
	
	@Test
	public void test1() {
//		Object obj1 = "6";
//		String str = "6";
//		System.out.println(obj1.equals(str));
		
		String pattern = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][-+]?\\d+)?[dD]?$";
		
		System.out.println("86".matches(pattern));
	}
}
