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
	public List<StudentRegister> getStudentRegisterByNumberAndStatus(String number,byte status){
		return (List<StudentRegister>) this.getHibernateTemplate().find("from StudentRegister where sr_number=? and status=?", number,status);
	}
	
	public int updateStatus(String s_number,int sub_id,byte status) {
		int row = this.getSessionFactory().getCurrentSession().createNativeQuery("update t_student_register set status="+status+" where sr_number="+s_number+" and sub_stu_reg="+sub_id ).executeUpdate();
		this.getSessionFactory().getCurrentSession().flush();
		return row;
		
	}

	public List<StudentRegister> getAllByMajorAndSubjectAndStatus(int m_id, int sub_id,byte status) {
		return (List<StudentRegister>) this.getHibernateTemplate().find("from StudentRegister where major.m_id=? and subject.sub_id=? and status=?", m_id,sub_id,status);
	}
	
	public List<StudentRegister> getCanExamByNumber(String number){
		return (List<StudentRegister>) this.getHibernateTemplate().find("from StudentRegister where sr_number=? and status in(2,3)", number);
	}
	
	public StudentRegister getByUuid(String uuid) {
		List<StudentRegister> list = (List<StudentRegister>) this.getHibernateTemplate().find("from StudentRegister where paper=?", uuid);
		if(list != null && list.size()>0)
			return list.get(0);
		else
			return null;
	}
	/**
	 * //到时阅卷时选择 t_id in(0,本教师id)&&status=4
	 * @return
	 */
	public StudentRegister getCanBeReviewed(int m_id,int sub_id,int t_id){
		//先找已经绑定要这个老师阅卷的试卷
		List<StudentRegister> list = (List<StudentRegister>) this.getHibernateTemplate().find("from StudentRegister where major.m_id=? and subject.sub_id=? and status=4 and t_id=?", m_id,sub_id,t_id);
		if(list==null || list.size()==0) {
			//没找到已经绑定的,就找可以阅卷的
			list = (List<StudentRegister>) this.getHibernateTemplate().find("from StudentRegister where major.m_id=? and subject.sub_id=? and status=4 and t_id=0", m_id,sub_id);
			if(list !=null && list.size()>0) {
				return list.get(0);
			}else
				return null;
		}
		return list.get(0);
	}
	
	public boolean isReviewDone(int m_id,int sub_id) {
		List<StudentRegister> list = (List<StudentRegister>) this.getHibernateTemplate().find("from StudentRegister where major.m_id=? and subject.sub_id=? and status=4", m_id,sub_id);
		if(list!=null && list.size()>0) {
			return false;
		}else
			return true;
	}
	/**
	 * 得到考场信息表的id
	 * @return
	 */
	public List<Integer> getExamInfoIds(){
		return this.getSessionFactory().getCurrentSession().createNativeQuery("select e_id from t_student_register group by e_id").getResultList();
	}
	
	public StudentRegister getByMajorAndSubjectAndNumber(int m_id,int sub_id,String number) {
		List<StudentRegister> list = (List<StudentRegister>) this.getHibernateTemplate().find("from StudentRegister where major.m_id=? and subject.sub_id=? and sr_number=?", m_id,sub_id,number);
		if(list != null && list.size()>0) {
			return list.get(0);
		}else
			return null;
	}
	
	public int getMajorIdByNumberAndSubject(String number,int sub_id) {
		List<Number> list = (List<Number>) this.getHibernateTemplate().find("select major.m_id from StudentRegister where sr_number=? and subject.sub_id=?",number,sub_id);
		if(list != null && list.size()>0) {
			return list.get(0).intValue();
		}else
			return 0;
	}
	
	public List<StudentRegister> getByEId(int e_id){
		return (List<StudentRegister>) this.getHibernateTemplate().find("from StudentRegister where e_id=?", e_id);
	}
}

