package com.nwuer.dao;

import java.io.Serializable;

public interface BaseDao<T> {
	/**
	 * 根据id进行查询
	 * @param id
	 * @return
	 */
	public T getById(Serializable id);
	
	/**
	 * 添加
	 * @param t
	 */
	public int add(T t);
	
	/**
	 * 删除
	 * @param t
	 */
	public void delete(Serializable id);
	
	/**
	 * 修改
	 * @param t
	 */
	public void update(T t); 
}
