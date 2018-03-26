package com.nwuer.service;

import java.io.Serializable;
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
	
	/**
	 * 得到分页Bean
	 */
	//public BaseBean list(int nowPage,int size);
	
	/**
	 * 根据id进行查询
	 * @param id
	 * @return
	 */
	public T getById(int id);
	
	/**
	 * 根据时间倒叙查询所有数据
	 * @return
	 */
	public List<T> getAllByTimeDesc();
	
	public T getByIdEager(Serializable id);
	
	/**
	 * 更新
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
}
