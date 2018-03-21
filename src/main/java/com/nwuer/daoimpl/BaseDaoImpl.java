package com.nwuer.daoimpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.nwuer.dao.BaseDao;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class<T> classType;
	public BaseDaoImpl() {
		//得到当前运行类的Class
		Class clazz = this.getClass();
		//得到父类的参数化类型,  直接在泛型类里面是获取不到的,必须在子类中进行获取
		Type type  = clazz.getGenericSuperclass();  //BaseDaoImpl<com.nwuer.entity.Teacher>
		//将参数化类型转化成参数化类型
		ParameterizedType ptype = (ParameterizedType)type;  //BaseDaoImpl<com.nwuer.entity.Teacher>
		//得到实际类型参数
		Type[] types = ptype.getActualTypeArguments();  //[Ljava.lang.reflect.Type;@6ae40994
		//将实际类型参数转化为Class
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
