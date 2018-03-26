package com.nwuer.service;

import java.io.Serializable;
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
	
	/**
	 * �õ���ҳBean
	 */
	//public BaseBean list(int nowPage,int size);
	
	/**
	 * ����id���в�ѯ
	 * @param id
	 * @return
	 */
	public T getById(int id);
	
	/**
	 * ����ʱ�䵹���ѯ��������
	 * @return
	 */
	public List<T> getAllByTimeDesc();
	
	public T getByIdEager(Serializable id);
	
	/**
	 * ����
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * ɾ��
	 * @param id
	 */
	public void delete(int id);
}
