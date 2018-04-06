package com.nwuer.daoimpl;

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
}
