package com.nwuer.daoimpl;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nwuer.entity.Student;
import com.nwuer.utils.MD5Util;

@Repository
@Transactional
public class StudentDaoImpl extends BaseDaoImpl<Student> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public Student getByNumberAndPass(Student stu) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		MD5Util md5 = (MD5Util) applicationContext.getBean("mD5Util");
		List<Student> list = (List<Student>) this.getHibernateTemplate().find("from Student where s_number=? and s_pass=?", stu.getS_number(),md5.encrypt(stu.getS_pass()));
		if(list != null && list.size()>0)
			return list.get(0);
		else
			return null;
	}
}
