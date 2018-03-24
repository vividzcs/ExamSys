package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.StudentDaoImpl;
import com.nwuer.entity.Student;

@Service
public class StudentService implements BaseService<Student> {
	@Autowired
	private StudentDaoImpl StudentDaoImpl;

	public Student getByNumberAndPass(Student stu) {
		return this.StudentDaoImpl.getByNumberAndPass(stu);
	}
	
	@Override
	@Transactional
	public int add(Student t) {
		//处理数据
		t.setCreate_time(System.currentTimeMillis());
		return this.StudentDaoImpl.add(t);
	}
	
	@Transactional
	public void clear() {
		this.StudentDaoImpl.clear();
	}
	
	public boolean hasData() {
		return this.StudentDaoImpl.hasData();
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getAllByTimeDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getByIdEager(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Student t) {
		// TODO Auto-generated method stub
	}
	
	public Student getByNumber(String number) {
		return this.StudentDaoImpl.getByNumber(number);
	}
}
