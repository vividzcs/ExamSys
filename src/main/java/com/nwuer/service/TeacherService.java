package com.nwuer.service;

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
	
	public Teacher getByNumberAndPass(Teacher teacher) {
		return this.teacherDaoImpl.getByNumberAndPass(teacher);
	}
	@Override
	@Transactional
	public int add(Teacher t) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		Crpty crpty = (Crpty) applicationContext.getBean("crpty");
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
		return this.teacherDaoImpl.getById(id);
	}
	@Override
	public List<Teacher> getAllByTimeDesc() {
		return this.teacherDaoImpl.getAllByTimeDesc();
	}
}
