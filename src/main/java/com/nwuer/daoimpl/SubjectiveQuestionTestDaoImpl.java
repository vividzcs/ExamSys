package com.nwuer.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nwuer.entity.SubjectiveQuestion;
import com.nwuer.entity.SubjectiveQuestionTest;

@Repository
public class SubjectiveQuestionTestDaoImpl extends BaseDaoImpl<SubjectiveQuestionTest> {
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * 检测题库是否有数据
	 * @return
	 */
	public boolean hasData() {
		List<Number> list = (List<Number>) this.getHibernateTemplate().find(" select count(*) from SubjectiveQuestionTest");
		if(list != null)
			return list.get(0).intValue() == 0 ? false : true;
		else
			return false;
	}
	
	public void clear() {
		this.getSessionFactory().getCurrentSession().createNativeQuery("truncate table t_subjective_question_test").executeUpdate();
	}
	
	public List<SubjectiveQuestionTest> getAllByKind(byte kind){
		return (List<SubjectiveQuestionTest>) this.getHibernateTemplate().find("from SubjectiveQuestionTest where sq_kind=?",kind );
	}
}
