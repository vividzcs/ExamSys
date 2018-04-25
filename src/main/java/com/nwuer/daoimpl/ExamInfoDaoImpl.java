package com.nwuer.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nwuer.entity.ExamInfo;

@Repository
public class ExamInfoDaoImpl extends BaseDaoImpl<ExamInfo> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public void increaseExamNumAll(int id) {
		this.getSessionFactory().getCurrentSession().createNativeQuery("update t_exam_info set exam_num_all=exam_num_all+1 where e_id="+id).executeUpdate();
	}
	
	public void increaseExamNum(int id) {
		this.getSessionFactory().getCurrentSession().createNativeQuery("update t_exam_info set exam_num=exam_num+1 where e_id="+id).executeUpdate();
	}
	
	public void increaseExamNumReach(int id) {
		this.getSessionFactory().getCurrentSession().createNativeQuery("update t_exam_info set exam_num_reach=exam_num_reach+1 where e_id="+id).executeUpdate();
	}
	
	public int getIdByMajorAndSubject(int m_id,int sub_id) {
		List<ExamInfo> list = (List<ExamInfo>) this.getHibernateTemplate().find("from ExamInfo where major.m_id=? and subject.sub_id=?", m_id,sub_id);
		if(list != null && list.size()>0) {
			return list.get(0).getE_id();
		}else
			return 0;
	}
	
	public ExamInfo getByMajorAndSubject(int m_id,int sub_id) {
		List<ExamInfo> list = (List<ExamInfo>) this.getHibernateTemplate().find("from ExamInfo where major.m_id=? and subject.sub_id=?", m_id,sub_id);
		if(list != null && list.size()>0) {
			return list.get(0);
		}else
			return null;
	}
}
