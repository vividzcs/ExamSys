package com.nwuer.daoimpl;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nwuer.entity.Student;
import com.nwuer.utils.Crpty;

@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public Student getByNumberAndPass(Student stu) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		Crpty md5 = (Crpty) applicationContext.getBean("mD5Util");
		List<Student> list = (List<Student>) this.getHibernateTemplate().find("from Student where s_number=? and s_pass=?", stu.getS_number(),md5.encrypt(stu.getS_pass()));
		if(list != null && list.size()>0)
			return list.get(0);
		else
			return null;
	}
	
	public void clear() {
		this.getSessionFactory().getCurrentSession().createNativeQuery("truncate table t_student").executeUpdate();
	}
	
	public boolean hasData() {
//		Query query =  this.getSessionFactory().getCurrentSession().createQuery("select count(*) from Student");
//		int rows = ((Number)query.list().get(0)).intValue();
//		return  rows == 0 ? false : true;
		List<Student> list = (List<Student>) this.getHibernateTemplate().find("from Student where s_id=1");
		return (list!=null && list.size()>0) ? true : false;
	}
}
