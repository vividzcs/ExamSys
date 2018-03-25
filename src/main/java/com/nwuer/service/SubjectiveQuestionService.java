package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.SubjectiveQuestionDaoImpl;
import com.nwuer.entity.SubjectiveQuestion;

@Service
public class SubjectiveQuestionService implements BaseService<SubjectiveQuestion> {
	@Autowired
	private SubjectiveQuestionDaoImpl subjectiveQuestionDaoImpl;
	
	@Override
	@Transactional
	public int add(SubjectiveQuestion t) {
		return this.subjectiveQuestionDaoImpl.add(t);
	}

	@Override
	public List<SubjectiveQuestion> getAll() {
		return this.subjectiveQuestionDaoImpl.getAll();
	}

	@Override
	public SubjectiveQuestion getById(int id) {
		return this.subjectiveQuestionDaoImpl.getById(id);
	}

	@Override
	public List<SubjectiveQuestion> getAllByTimeDesc() {
		return this.subjectiveQuestionDaoImpl.getAllByTimeDesc();
	}

	@Override
	public SubjectiveQuestion getByIdEager(Serializable id) {
		return this.subjectiveQuestionDaoImpl.getByIdEager(id);
	}

	@Override
	public void update(SubjectiveQuestion t) {
	}
	
	public boolean hasData() {
		return this.subjectiveQuestionDaoImpl.hasData();
	}
	
	@Transactional
	public void clear() {
		this.subjectiveQuestionDaoImpl.clear();
	}
}
