package com.nwuer.service;

import java.util.List;

public interface BaseService<T> {
	
	/**
	 * ����
	 * @return
	 */
	public int add(T t);
	
	/**
	 * ��ѯ����
	 * @return
	 */
	public List<T> getAll();
}
