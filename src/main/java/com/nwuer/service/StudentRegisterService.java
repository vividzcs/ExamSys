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
	
	public List<StudentRegister> getStudentRegisterByNumber(String number) {
		return this.studentRegisterDaoImpl.getStudentRegisterByNumber(number);
	}
	
	@Transactional
	public int updateStatus(String number,int sub_id,byte status) {
		return this.studentRegisterDaoImpl.updateStatus(number,sub_id,status);
	}

	public List<StudentRegister> getAllByMajorAndSubject(int m_id,int sub_id) {
		return this.studentRegisterDaoImpl.getAllByMajorAndSubject(m_id,sub_id);
	}
}
