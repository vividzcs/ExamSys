package com.nwuer.daoimpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class classType;
	public BaseDaoImpl() {
		//�õ���ǰ�������Class
		Class clazz = this.getClass();
		//�õ�����Ĳ���������,  ֱ���ڷ����������ǻ�ȡ������,�����������н��л�ȡ
		Type type  = clazz.getGenericSuperclass();  //BaseDaoImpl<com.nwuer.entity.Teacher>
		//������������ת���ɲ���������
		ParameterizedType ptype = (ParameterizedType)type;  //BaseDaoImpl<com.nwuer.entity.Teacher>
		//�õ�ʵ�����Ͳ���
		Type[] types = ptype.getActualTypeArguments();  //[Ljava.lang.reflect.Type;@6ae40994
		//��ʵ�����Ͳ���ת��ΪClass
		Class classParameter = (Class)types[0];  //class com.nwuer.entity.Teacher
		
		this.classType = classParameter;
	}
	
	@Override
	public T getById(int id) {
		return (T) this.getHibernateTemplate().get(this.classType, id);
	}

	@Override
	public int add(T t) {
		return (int) this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

}
