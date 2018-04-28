package com.nwuer.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nwuer.entity.Major;
import com.nwuer.entity.Teacher;

@Repository
public class MajorDaoImpl extends BaseDaoImpl<Major> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public int getIdByName(String name) {
		List<Integer> list = (List<Integer>) this.getHibernateTemplate().find("select m_id from Major where m_name=?", name);
		if(list != null && list.size()>0) {
			this.getHibernateTemplate().clear();
			return list.get(0);
		}
		else
			return 0;
	}
	
	public boolean getHasByAId(int a_id) {
		List<Major> list = (List<Major>) this.getHibernateTemplate().find("from Major where academy.a_id=?", a_id);
		if(list != null  && list.size()>0) {
			return true;
		}else
			return false;
	}
	
}
