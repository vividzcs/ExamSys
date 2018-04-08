package com.nwuer.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nwuer.entity.StudentRegister;

@Repository
public class StudentRegisterDaoImpl extends BaseDaoImpl<StudentRegister> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public boolean hasData() {
		List<Number> list = (List<Number>) this.getHibernateTemplate().find(" select count(*) from StudentRegister");
		if(list != null)
			return list.get(0).intValue() == 0 ? false : true;
		else
			return false;
	}
	
	public void clear() {
		this.getSessionFactory().getCurrentSession().createNativeQuery("truncate table t_student_register").executeUpdate();
	}
	
	/**
	 * 得到学生注册表中相应学号对应的数据并且是未注册状态,已注册就不用注册了
	 * @param number
	 * @return
	 */
	public List<StudentRegister> getStudentRegisterByNumber(String number){
		return (List<StudentRegister>) this.getHibernateTemplate().find("from StudentRegister where sr_number=? and status=0", number);
	}
	
	public int updateStatus(String s_number,int sub_id,byte status) {
		return this.getSessionFactory().getCurrentSession().createNativeQuery("update t_student_register set status="+status+" where sr_number="+s_number+" and sub_stu_reg="+sub_id ).executeUpdate();
	}

	public List<StudentRegister> getAllByMajorAndSubject(int m_id, int sub_id) {
		return (List<StudentRegister>) this.getHibernateTemplate().find("from StudentRegister where major.m_id=? and subject.sub_id=?", m_id,sub_id);
	}
}
