package com.nwuer.test;

import org.junit.Test;

public class ValidateTest {
	@Test
	public void test() {
		String str = "2015";
		System.out.println(str.matches("[0-9]{0,10}"));
	}
}
