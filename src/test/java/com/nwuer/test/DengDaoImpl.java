package com.nwuer.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class DengDaoImpl<T> implements DengDao<T> {
	private Class classType;
	public DengDaoImpl() {
		Class clazz = this.getClass();
		System.out.println(clazz);
		Type type  = clazz.getGenericSuperclass();
		System.out.println(type);
		ParameterizedType ptype = (ParameterizedType)type;
		System.out.println(ptype);
		Type[] types = ptype.getActualTypeArguments();
		System.out.println(types);
		Class classParameter = (Class)types[0];
		
		this.classType = classParameter;
		
		
		System.out.println(classType);
	}
}
