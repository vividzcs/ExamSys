package com.nwuer.dao;

public interface BaseDao<T> {
	/**
	 * 根据id进行查询
	 * @param id
	 * @return
	 */
	public T getById(int id);
	
	/**
	 * 添加
	 * @param t
	 */
	public int add(T t);
	
	/**
	 * 删除
	 * @param t
	 */
	public void delete(T t);
	
	/**
	 * 修改
	 * @param t
	 */
	public void update(T t); 
}
