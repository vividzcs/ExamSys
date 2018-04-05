package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nwuer.daoimpl.TeacherDaoImpl;
import com.nwuer.entity.Teacher;
import com.nwuer.utils.Crpty;

@Service
public class TeacherService implements BaseService<Teacher> {
	@Autowired
	private TeacherDaoImpl teacherDaoImpl;
	@Autowired
	private Crpty crpty;
	
	public Teacher getByNumberAndPass(Teacher teacher) {
		teacher.setT_pass(crpty.encrypt(teacher.getT_pass()));
		return this.teacherDaoImpl.getByNumberAndPass(teacher);
	}
	@Override
	@Transactional
	public int add(Teacher t) {
		t.setCreate_time(System.currentTimeMillis());
		t.setT_pass(crpty.encrypt(t.getT_pass()));
		return this.teacherDaoImpl.add(t);
	}
	@Override
	public List<Teacher> getAll() {
		return this.teacherDaoImpl.getAll();
	}
	@Override
	public Teacher getById(int id) {
		Teacher t = this.teacherDaoImpl.getById(id);
		t.setT_pass(crpty.decrypt(t.getT_pass()));
		return t;
	}
	@Override
	public List<Teacher> getAllByTimeDesc() {
		return this.teacherDaoImpl.getAllByTimeDesc();
	}
	@Override
	public Teacher getByIdEager(Serializable id) {
		Teacher t = this.teacherDaoImpl.getByIdEager(id);
		t.setT_pass(this.crpty.decrypt(t.getT_pass()));
		return t;
	}
	@Transactional
	public void updateLastLogin(long t,int id) {
		this.teacherDaoImpl.updateLastLogin(t,id);
	}
	@Override
	@Transactional
	public void update(Teacher t) {
		t.setT_pass(crpty.encrypt(t.getT_pass()));
		
		this.teacherDaoImpl.update(t);
		
	}
	@Override
	@Transactional
	public void delete(int id) {
		this.teacherDaoImpl.delete(id);
	}
	
	public List<Teacher> getByNumber(String number) {
		return this.teacherDaoImpl.getByNumber(number);
	}
	
	public Teacher getByNumberE(String number) {
		return this.teacherDaoImpl.getByNumberE(number);
	}
	
	
}
