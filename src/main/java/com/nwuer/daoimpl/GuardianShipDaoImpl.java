package com.nwuer.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nwuer.entity.GuardianShip;

@Repository
public class GuardianShipDaoImpl extends BaseDaoImpl<GuardianShip> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public boolean hasData() {
		List<Number> list = (List<Number>) this.getHibernateTemplate().find(" select count(*) from GuardianShip");
		if(list != null)
			return list.get(0).intValue() == 0 ? false : true;
		else
			return false;
	}
	
	public void clear() {
		this.getSessionFactory().getCurrentSession().createNativeQuery("truncate table t_guardianship").executeUpdate();
	}
	
	public int getByMajorAndSubject(int m_id,int sub_id) {
		List list = this.getSessionFactory().getCurrentSession().createNativeQuery("select g_id from t_guardianship where major_guard_ship="+m_id+" and subject_guard_ship="+sub_id).getResultList();
		if(list != null && list.size()>0)
			return (int) list.get(0);
		else
			return 0;
	}
	
	public GuardianShip getGuardByMajorAndSubject(int m_id,int sub_id) {
		int id = this.getByMajorAndSubject(m_id, sub_id);
		if(id!=0) {
			return this.getByIdEager(id);
		}else
			return null;
	}
}
