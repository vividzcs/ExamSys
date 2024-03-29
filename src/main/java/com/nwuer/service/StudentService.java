package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nwuer.daoimpl.StudentDaoImpl;
import com.nwuer.entity.Student;
import com.nwuer.utils.Crpty;

@Service
public class StudentService implements BaseService<Student> {
	@Autowired
	private StudentDaoImpl StudentDaoImpl;
	@Autowired
	private Crpty crpty;
	
	public Student getByNumberAndPass(Student stu) {
		stu.setS_pass(crpty.encrypt(stu.getS_pass()));
		
		return this.StudentDaoImpl.getByNumberAndPass(stu);
	}
	
	@Override
	@Transactional
	public int add(Student t) {
		//处理数据
		t.setCreate_time(System.currentTimeMillis());
		t.setS_pass(crpty.encrypt(t.getS_pass()));
		return this.StudentDaoImpl.add(t);
	}
	
	@Transactional
	public void clear() {
		//this.StudentDaoImpl.clear();
	}
	
	public boolean hasData() {
		return this.StudentDaoImpl.hasData();
	}

	@Override
	public List<Student> getAll() {
		return this.StudentDaoImpl.getAll();
	}

	@Override
	public Student getById(int id) {
		Student s = this.StudentDaoImpl.getById(id);
		s.setS_pass(crpty.decrypt(s.getS_pass()));
		return s;
	}

	@Override
	public List<Student> getAllByTimeDesc() {
		return StudentDaoImpl.getAllByTimeDesc();
	}

	@Override
	public Student getByIdEager(Serializable id) {
		Student s = this.StudentDaoImpl.getByIdEager(id);
		s.setS_pass(crpty.decrypt(s.getS_pass()));
		return s;
	}

	@Override
	@Transactional
	public void update(Student t) {
		//
		t.setS_pass(crpty.encrypt(t.getS_pass()));
		
		this.StudentDaoImpl.update(t);
	}
	
	public List<Student> getByNumber(String number) {
		return this.StudentDaoImpl.getByNumber(number);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.StudentDaoImpl.delete(id);
	}
	
	public String getNameByNumber(String number) {
		return this.StudentDaoImpl.getNameByNumber(number);
	}
	
	public	Student getByNumberE(String number) {
		return this.StudentDaoImpl.getByNumberE(number);
	}
}
