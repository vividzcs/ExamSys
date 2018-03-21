package com.nwuer.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	/**
	 * ����id���в�ѯ
	 * @param id
	 * @return
	 */
	public T getById(Serializable id);
	
	/**
	 * ���
	 * @param t
	 */
	public int add(T t);
	
	/**
	 * ɾ��
	 * @param t
	 */
	public void delete(Serializable id);
	
	/**
	 * �޸�
	 * @param t
	 */
	public void update(T t); 
	
	/**
	 * ��ѯ������Ϣ
	 * @return
	 */
	public List<T> getAll();
	
}
