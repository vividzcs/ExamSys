package com.nwuer.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nwuer.entity.Subject;

@Repository
public class SubjectDaoImpl extends BaseDaoImpl<Subject> {
	
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 根据科目名查询id
	 * @param name
	 * @return
	 */
	public int getIdByName(String name) {
		List<Integer> list = (List<Integer>) this.getHibernateTemplate().find("select sub_id from Subject where sub_name=?", name);
		if(list != null && list.size()>0) {
			this.getHibernateTemplate().clear();
			return list.get(0);
		}
		else
			return 0;
	}
}
