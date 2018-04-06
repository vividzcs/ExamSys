package com.nwuer.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class FreemarkerTest {
	@Test
	public void test() {
		System.out.println(this.getClass().getClassLoader().getResource("/").getPath());
	}
	
	@Test
	public void test1() {
		List list = new ArrayList();
		list.add("你");
		list.add("好");
		list.add("我");
		list.add("爱");
		list.add("你");
		System.out.println(list.toString());
		Collections.shuffle(list);
		System.out.println(list.toString());
		
	}
	
	@Test
	public void test2() {
		List list = new ArrayList();
//		list.add("");
////		for(int i=0;i<5;i++) {
////			list.add("");
////		}
////		list.add(0,"你");
////		list.add(1,"好");
////		list.add(2,"我");
////		list.add(3,"爱");
//		list.add(2,"你");
//		
////		list.re
//		List rs = new ArrayList();
		
		String[] arr = new String[5];
		Random r = new Random();
		for(int i=0; i<arr.length;i++) {
			int index = r.nextInt(3);
			System.out.println(index);
		}
		
		
		System.out.println(list.toString());
		System.out.println(list.size());
	}

	@Test
	public void test3() {
		StringBuilder str1 = new StringBuilder("xxxxx");
		StringBuilder str2 = new StringBuilder("yyyyy");
		
		str1.append(str2);
		System.out.println(str1);
	}
}
