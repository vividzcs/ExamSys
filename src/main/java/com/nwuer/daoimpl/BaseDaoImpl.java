package com.nwuer.daoimpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.nwuer.dao.BaseDao;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class<T> classType;
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
	public T getById(Serializable id) {
		return (T) this.getHibernateTemplate().get(this.classType, id);
	}

	@Override
	public int add(T t) {
		return  (int) this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(Serializable id) {
		this.getHibernateTemplate().delete(this.getById(id));
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

}
