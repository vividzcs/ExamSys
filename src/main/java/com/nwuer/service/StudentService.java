package com.nwuer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwuer.daoimpl.StudentDaoImpl;
import com.nwuer.entity.Student;

@Service
public class StudentService {
	@Autowired
	private StudentDaoImpl StudentDaoImpl;
	
	public Student getByNumberAndPass(Student stu) {
		return this.StudentDaoImpl.getByNumberAndPass(stu);
	}
}
