package com.nwuer.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nwuer.entity.Academy;

@Repository
public class AcademyDaoImpl extends BaseDaoImpl<Academy> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public int getIdByName(String name) {
		List<Integer> list = (List<Integer>) this.getHibernateTemplate().find("select a_id from Academy where a_name=?", name);
		if(list != null && list.size()>0) {
			this.getHibernateTemplate().clear();
			return list.get(0);
		}
		else
			return 0;
	}
	
}
