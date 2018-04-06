package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.SubjectiveQuestionTestDaoImpl;
import com.nwuer.entity.SubjectiveQuestion;
import com.nwuer.entity.SubjectiveQuestionTest;

@Service
public class SubjectiveQuestionTestService implements BaseService<SubjectiveQuestionTest> {
	@Autowired
	private SubjectiveQuestionTestDaoImpl subjectiveQuestionTestDaoImpl;
	
	@Override
	@Transactional
	public int add(SubjectiveQuestionTest t) {
		return this.subjectiveQuestionTestDaoImpl.add(t);
	}

	@Override
	public List<SubjectiveQuestionTest> getAll() {
		return this.subjectiveQuestionTestDaoImpl.getAll();
	}

	@Override
	public SubjectiveQuestionTest getById(int id) {
		return this.subjectiveQuestionTestDaoImpl.getById(id);
	}

	@Override
	public List<SubjectiveQuestionTest> getAllByTimeDesc() {
		return this.subjectiveQuestionTestDaoImpl.getAllByTimeDesc();
	}

	@Override
	public SubjectiveQuestionTest getByIdEager(Serializable id) {
		return this.subjectiveQuestionTestDaoImpl.getByIdEager(id);
	}

	@Override
	public void update(SubjectiveQuestionTest t) {
	}
	
	public boolean hasData() {
		return this.subjectiveQuestionTestDaoImpl.hasData();
	}
	
	@Transactional
	public void clear() {
		this.subjectiveQuestionTestDaoImpl.clear();
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.subjectiveQuestionTestDaoImpl.delete(id);
	}
	
	public List<SubjectiveQuestionTest> getAllByKind(byte kind){
		return this.subjectiveQuestionTestDaoImpl.getAllByKind(kind);
	}
}
