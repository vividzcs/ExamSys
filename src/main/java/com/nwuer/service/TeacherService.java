package com.nwuer.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nwuer.daoimpl.TeacherDaoImpl;
import com.nwuer.entity.Teacher;

@Service
public class TeacherService implements BaseService {
	@Resource
	private TeacherDaoImpl teacherDaoImpl;
	public Teacher getByNumberAndPass(Teacher teacher) {
		return this.teacherDaoImpl.getByNumberAndPass(teacher);
	}
}
