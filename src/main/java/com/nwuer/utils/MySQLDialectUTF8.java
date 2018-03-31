package com.nwuer.utils;

import org.hibernate.dialect.MySQLDialect;

public class MySQLDialectUTF8 extends MySQLDialect {

	@Override
	public String getTableTypeString() {
		// TODO 改变数据库建表的字符集
		return "Engine=InnoDB default charset=utf8";
	}
	
}
