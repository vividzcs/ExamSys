package com.nwuer.daoimpl;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nwuer.entity.Student;
import com.nwuer.entity.Teacher;
import com.nwuer.utils.Crpty;
@Repository
public class TeacherDaoImpl extends BaseDaoImpl<Teacher> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public Teacher getByNumberAndPass(Teacher teacher) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		Crpty crpty = (Crpty) applicationContext.getBean("crpty");
		List<Teacher> list = (List<Teacher>) this.getHibernateTemplate().find("from Teacher where t_number=? and t_pass=?", teacher.getT_number(),crpty.encrypt(teacher.getT_pass()));
		if(list != null && list.size()>0)
			return list.get(0);
		else
			return null;
		
	}
	
	public void updateLastLogin(long t,int id) {
		this.getHibernateTemplate().clear();
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createNativeQuery("update t_teacher set last_login=" + t + " where t_id=" + id);
		query.executeUpdate();
	}
	
	public List<Teacher> getByNumber(String number) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Teacher.class);
		criteria.add(Restrictions.like("t_number", "%" + number + "%"));
		
		return (List<Teacher>) this.getHibernateTemplate().findByCriteria(criteria);
	}
}
