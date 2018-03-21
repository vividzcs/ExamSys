package com.nwuer.service;

import java.util.List;

public interface BaseService<T> {
	
	/**
	 * 增加
	 * @return
	 */
	public int add(T t);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<T> getAll();
}
