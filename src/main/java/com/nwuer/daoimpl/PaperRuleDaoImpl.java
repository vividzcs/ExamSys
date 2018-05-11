package com.nwuer.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nwuer.entity.ExamInfo;
import com.nwuer.entity.PaperRule;
@Repository
public class PaperRuleDaoImpl extends BaseDaoImpl<PaperRule> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public int getIdByMajorAndSubject(int m_id,int sub_id) {
		List<Number> list = (List<Number>) this.getHibernateTemplate().find("select p_id from PaperRule where major.m_id=? and subject.sub_id=?", m_id,sub_id);
		if(list != null && list.size()>0) {
			return list.get(0).intValue();
		}else
			return 0;
	}
	
//	@Override
//	public void update(PaperRule t) {
//		this.getHibernateTemplate().update(t);
//	}
}
