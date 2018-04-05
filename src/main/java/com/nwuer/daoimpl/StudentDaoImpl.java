package com.nwuer.daoimpl;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
		List<Student> list = (List<Student>) this.getHibernateTemplate().find("from Student where s_number=? and s_pass=?", stu.getS_number(),stu.getS_pass());
		if(list != null && list.size()>0)
			return list.get(0);
		else
			return null;
	}
	
	/**
	 * 清空学生表的数据
	 */
	public void clear() {
		this.getSessionFactory().getCurrentSession().createNativeQuery("truncate table t_student").executeUpdate();
	}
	
	/**
	 * 检测学生表是否有数据
	 * @return
	 */
	public boolean hasData() {
		List<Number> list = (List<Number>) this.getHibernateTemplate().find(" select count(*) from Student");
		if(list != null)
			return list.get(0).intValue() == 0 ? false : true;
		else
			return false;
	}
	
	public	List<Student> getByNumber(String number) {
		/*List<Student> list = (List<Student>) this.getHibernateTemplate().find("from Student where s_number=?", number);
		if(list != null && list.size()>0)
			return list.get(0);
		else
			return null;*/
		DetachedCriteria criteria = DetachedCriteria.forClass(Student.class);
		criteria.add(Restrictions.like("s_number", "%" + number + "%"));
		
		return (List<Student>) this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	public String getNameByNumber(String number) {
		List<String> list = (List<String>) this.getHibernateTemplate().find("select s_name from Student where s_number=?",number );
		if(list != null && list.size()>0)
			return list.get(0);
		else
			return null;
	}
}

