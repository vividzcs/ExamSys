package com.nwuer.dao;

import java.io.Serializable;
import java.util.List;

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
	
	/**
	 * 查询所有信息
	 * @return
	 */
	public List<T> getAll();
	
	/**
	 * 按照时间倒序查询所有数据
	 * @return
	 */
	public List<T> getAllByTimeDesc();
	
	/**
	 * 查询总记录数
	 */
	public int count();
}
