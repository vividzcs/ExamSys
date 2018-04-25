package com.nwuer.daoimpl;

import java.util.Collections;
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
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().update(t);
	}
	
	public void delete(Paper t) {
		this.getHibernateTemplate().delete(t);
	}
	
	/**
	 * 根据专业和科目得到未绑定学生的试卷
	 * @return
	 */
	public Paper getNonePaperByMajorAndSubject(int m_id,int sub_id) {
		List<Paper> list = (List<Paper>) this.getHibernateTemplate().find("from Paper where major.m_id=? and subject.sub_id=? and status=0", m_id,sub_id);
		if(list != null && list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
	public Paper getPraticePaperByMajorAndSubject(int m_id,int sub_id) {
		List<Paper> list = (List<Paper>) this.getHibernateTemplate().find("from Paper where pap_kind=0 and major.m_id=? and subject.sub_id=?",m_id,sub_id);
		if(list!=null && list.size()>0) {
			Collections.shuffle(list);
			return list.get(0);
		}else
			return null;
		
	}
	
	public List<Paper> getPaperByPid(int pid) {
		return (List<Paper>) this.getHibernateTemplate().find("from Paper where p_id=?", pid);
	}
	
	public void clear() {
		this.getSessionFactory().getCurrentSession().createNativeQuery("truncate table t_paper").executeUpdate();
	}
}
