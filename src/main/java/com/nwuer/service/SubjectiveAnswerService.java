package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.SubjectiveAnswerDaoImpl;
import com.nwuer.entity.SubjectiveAnswer;

@Service
public class SubjectiveAnswerService implements BaseService<SubjectiveAnswer> {
	@Autowired
	private SubjectiveAnswerDaoImpl subjectiveAnswerDaoImpl;
	
	@Override
	@Transactional
	public int add(SubjectiveAnswer t) {
		t.setCreate_time(System.currentTimeMillis());
		return this.subjectiveAnswerDaoImpl.add(t);
	}

	@Override
	public List<SubjectiveAnswer> getAll() {
		return this.subjectiveAnswerDaoImpl.getAll();
	}

	@Override
	public SubjectiveAnswer getById(int id) {
		return this.subjectiveAnswerDaoImpl.getById(id);
	}

	@Override
	public List<SubjectiveAnswer> getAllByTimeDesc() {
		return this.subjectiveAnswerDaoImpl.getAllByTimeDesc();
	}

	@Override
	public SubjectiveAnswer getByIdEager(Serializable id) {
		return this.subjectiveAnswerDaoImpl.getByIdEager(id);
	}

	@Override
	@Transactional
	public void update(SubjectiveAnswer t) {
		this.subjectiveAnswerDaoImpl.update(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.subjectiveAnswerDaoImpl.delete(id);
	}
	
	public List<SubjectiveAnswer> getByUuid(String uuid){
		return this.subjectiveAnswerDaoImpl.getByUuid(uuid);
	}

}
