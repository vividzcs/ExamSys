package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.StudentRegisterDaoImpl;
import com.nwuer.entity.StudentRegister;
@Service
public class StudentRegisterService implements BaseService<StudentRegister> {
	@Autowired
	private StudentRegisterDaoImpl studentRegisterDaoImpl;
	
	@Override
	@Transactional
	public int add(StudentRegister t) {
		t.setCreate_time(System.currentTimeMillis());
		return this.studentRegisterDaoImpl.add(t);
	}

	@Override
	public List<StudentRegister> getAll() {
		return this.studentRegisterDaoImpl.getAll();
	}

	@Override
	public StudentRegister getById(int id) {
		return this.studentRegisterDaoImpl.getById(id);
	}

	@Override
	public List<StudentRegister> getAllByTimeDesc() {
		return this.studentRegisterDaoImpl.getAllByTimeDesc();
	}

	@Override
	public StudentRegister getByIdEager(Serializable id) {
		return this.studentRegisterDaoImpl.getByIdEager(id);
	}

	@Override
	@Transactional
	public void update(StudentRegister t) {
		this.studentRegisterDaoImpl.update(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.studentRegisterDaoImpl.delete(id);
	}
	
	public boolean hasData() {
		return this.studentRegisterDaoImpl.hasData();
	}
	
	@Transactional
	public void clear() {
		this.studentRegisterDaoImpl.clear();
	}
	
	public List<StudentRegister> getStudentRegisterByNumberAndStatus(String number,byte status) {
		return this.studentRegisterDaoImpl.getStudentRegisterByNumberAndStatus(number,status);
	}
	
	@Transactional
	public int updateStatus(String number,int sub_id,byte status) {
		return this.studentRegisterDaoImpl.updateStatus(number,sub_id,status);
	}

	public List<StudentRegister> getAllByMajorAndSubjectAndStatus(int m_id,int sub_id,byte status) {
		return this.studentRegisterDaoImpl.getAllByMajorAndSubjectAndStatus(m_id,sub_id,status);
	}
	
	public List<StudentRegister> getCanExamByNumber(String number){
		return this.studentRegisterDaoImpl.getCanExamByNumber(number);
	}
	
	public StudentRegister getByUuid(String uuid) {
		return this.studentRegisterDaoImpl.getByUuid(uuid);
	}
	
	public StudentRegister getCanBeReviewed(int m_id,int sub_id,int t_id){
		return this.studentRegisterDaoImpl.getCanBeReviewed(m_id,sub_id,t_id);
	}
	
	/**
	 * 是否阅卷完成
	 * @return
	 */
	public boolean isReviewDone(int m_id,int sub_id) {
		return this.studentRegisterDaoImpl.isReviewDone(m_id, sub_id);
	}
	
	public List<Integer> getExamInfoIds(){
		return this.studentRegisterDaoImpl.getExamInfoIds();
	}
	
	public StudentRegister getByMajorAndSubjectAndNumber(int m_id,int sub_id,String number) {
		return this.studentRegisterDaoImpl.getByMajorAndSubjectAndNumber(m_id, sub_id, number);
	}
	
	public int getMajorIdByNumberAndSubject(String number,int sub_id) {
		return this.studentRegisterDaoImpl.getMajorIdByNumberAndSubject(number, sub_id);
	}
}
