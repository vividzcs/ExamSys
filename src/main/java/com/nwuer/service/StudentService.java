package com.nwuer.service;

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
}
