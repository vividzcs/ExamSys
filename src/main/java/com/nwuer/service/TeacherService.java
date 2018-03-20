package com.nwuer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public int add(Teacher t) {
		return this.teacherDaoImpl.add(t);
	}
}
