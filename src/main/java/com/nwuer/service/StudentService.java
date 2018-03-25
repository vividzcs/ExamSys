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

	public Student getByNumberAndPass(Student stu) {
		return this.StudentDaoImpl.getByNumberAndPass(stu);
	}
	
	@Override
	@Transactional
	public int add(Student t) {
		//处理数据
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		Crpty crpty = (Crpty) applicationContext.getBean("crpty");
		t.setCreate_time(System.currentTimeMillis());
		t.setS_pass(crpty.encrypt(t.getS_pass()));
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
		return this.StudentDaoImpl.getAll();
	}

	@Override
	public Student getById(int id) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		Crpty crpty = (Crpty) applicationContext.getBean("crpty");
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
		return this.getByIdEager(id);
	}

	@Override
	@Transactional
	public void update(Student t) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		Crpty crpty = (Crpty) applicationContext.getBean("crpty");
		t.setS_pass(crpty.encrypt(t.getS_pass()));
		
		this.StudentDaoImpl.update(t);
	}
	
	public Student getByNumber(String number) {
		return this.StudentDaoImpl.getByNumber(number);
	}
}
