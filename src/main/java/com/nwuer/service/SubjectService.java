package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.SubjectDaoImpl;
import com.nwuer.entity.Subject;
@Service
public class SubjectService implements BaseService<Subject> {
	@Autowired
	private SubjectDaoImpl subjectDaoImpl;
	
	@Override
	@Transactional
	public int add(Subject t) {
		return this.subjectDaoImpl.add(t);
	}

	@Override
	public List<Subject> getAll() {
		return this.subjectDaoImpl.getAll();
	}

	@Override
	public Subject getById(int id) {
		return this.subjectDaoImpl.getById(id);
	}

	@Override
	public List<Subject> getAllByTimeDesc() {
		return this.subjectDaoImpl.getAllByTimeDesc();
	}

	@Override
	public Subject getByIdEager(Serializable id) {
		return this.getByIdEager(id);
	}

	@Override
	@Transactional
	public void update(Subject t) {
		this.subjectDaoImpl.update(t);
	}
	
	public int getIdByName(String name) {
		return this.subjectDaoImpl.getIdByName(name);
	}

}
