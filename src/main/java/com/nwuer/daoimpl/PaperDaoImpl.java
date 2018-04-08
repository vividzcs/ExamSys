package com.nwuer.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.nwuer.entity.Paper;

@Repository
public class PaperDaoImpl extends HibernateDaoSupport  {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public String add(Paper paper) {
		this.getHibernateTemplate().clear();
		return (String) this.getHibernateTemplate().save(paper);
	}
	
	public Paper getById(String uuid) {
		return this.getHibernateTemplate().load(Paper.class, uuid);
	}
	
	public Paper getByIdEager(String uuid) {
		return this.getHibernateTemplate().get(Paper.class, uuid);
	}
	
	public List<Paper> getAll(){
		return (List<Paper>) this.getHibernateTemplate().find("from Paper");
	}

	public void update(Paper t) {
		this.getHibernateTemplate().update(t);
	}
}
