package com.nwuer.utils;

import org.hibernate.dialect.MySQLDialect;

public class MySQLDialectUTF8 extends MySQLDialect {

	@Override
	public String getTableTypeString() {
		// TODO �ı����ݿ⽨����ַ���
		return "Engine=InnoDB default charset=utf8";
	}
	
}
