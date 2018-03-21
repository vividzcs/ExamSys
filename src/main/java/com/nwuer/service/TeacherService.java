package com.nwuer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.TeacherDaoImpl;
import com.nwuer.entity.Teacher;

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
		t.setCreate_time(System.currentTimeMillis());
		return this.teacherDaoImpl.add(t);
	}
	@Override
	public List<Teacher> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
