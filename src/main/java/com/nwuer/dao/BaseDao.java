package com.nwuer.dao;

public interface BaseDao<T> {
	/**
	 * ����id���в�ѯ
	 * @param id
	 * @return
	 */
	public T getById(int id);
	
	/**
	 * ���
	 * @param t
	 */
	public int add(T t);
	
	/**
	 * ɾ��
	 * @param t
	 */
	public void delete(T t);
	
	/**
	 * �޸�
	 * @param t
	 */
	public void update(T t); 
}
