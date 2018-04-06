package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.ChoiceQuestionTestDaoImpl;
import com.nwuer.entity.ChoiceQuestionTest;

@Service
public class ChoiceQuestionTestService implements BaseService<ChoiceQuestionTest> {
	@Autowired
	private ChoiceQuestionTestDaoImpl choiceQuestionTestDaoImpl;
	
	@Override
	@Transactional
	public int add(ChoiceQuestionTest t) {
		t.setCreate_time(System.currentTimeMillis());
		return this.choiceQuestionTestDaoImpl.add(t);
	}

	@Override
	public List<ChoiceQuestionTest> getAll() {
		return this.choiceQuestionTestDaoImpl.getAll();
	}

	@Override
	public ChoiceQuestionTest getById(int id) {
		return this.choiceQuestionTestDaoImpl.getById(id);
	}

	@Override
	public List<ChoiceQuestionTest> getAllByTimeDesc() {
		return this.choiceQuestionTestDaoImpl.getAllByTimeDesc();
	}

	@Override
	public ChoiceQuestionTest getByIdEager(Serializable id) {
		return this.choiceQuestionTestDaoImpl.getByIdEager(id);
	}

	@Override
	public void update(ChoiceQuestionTest t) {
	}
	
	public boolean hasData() {
		return this.choiceQuestionTestDaoImpl.hasData();
	}
	@Transactional
	public void clear() {
		this.choiceQuestionTestDaoImpl.clear();
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.choiceQuestionTestDaoImpl.delete(id);
	}

}
