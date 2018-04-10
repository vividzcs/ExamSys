package com.nwuer.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nwuer.entity.SubjectiveAnswer;

@Repository
public class SubjectiveAnswerDaoImpl extends BaseDaoImpl<SubjectiveAnswer> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public List<SubjectiveAnswer> getByUuid(String uuid){
		return (List<SubjectiveAnswer>) this.getHibernateTemplate().find("from SubjectiveAnswer where uuid=?", uuid);
	}
	
}
