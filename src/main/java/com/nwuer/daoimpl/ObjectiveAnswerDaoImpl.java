package com.nwuer.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nwuer.entity.ObjectiveAnswer;

@Repository
public class ObjectiveAnswerDaoImpl extends BaseDaoImpl<ObjectiveAnswer> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public ObjectiveAnswer getByUuid(String uuid) {
		List<ObjectiveAnswer> list =  (List<ObjectiveAnswer>) this.getHibernateTemplate().find("from ObjectiveAnswer where uuid=?", uuid);
		if(list!=null && list.size()>0)
			return list.get(0);
		else
			return null;
	}
}
