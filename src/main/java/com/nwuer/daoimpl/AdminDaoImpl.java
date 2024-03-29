package com.nwuer.daoimpl;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nwuer.entity.Admin;
import com.nwuer.utils.Crpty;
@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public Admin getByNumberAndPass(Admin admin) {
		List<Admin> list = (List<Admin>) this.getHibernateTemplate().find("from Admin where ad_number=? and ad_pass=?", admin.getAd_number(),admin.getAd_pass());
		if(list!=null && list.size()>0)
			return list.get(0);
		else
			return null;
	}
	
	public void updateLastLogin(long t,int id) {
		this.getHibernateTemplate().clear();
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createNativeQuery("update t_admin set last_login=" + t + " where ad_id=" + id);
		query.executeUpdate();
	}
}
