package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.ChoiceQuestionDaoImpl;
import com.nwuer.entity.ChoiceQuestion;

@Service
public class ChoiceQuestionService implements BaseService<ChoiceQuestion> {
	@Autowired
	private ChoiceQuestionDaoImpl choiceQuestionDaoImpl;
	
	@Override
	@Transactional
	public int add(ChoiceQuestion t) {
		t.setCreate_time(System.currentTimeMillis());
		return this.choiceQuestionDaoImpl.add(t);
	}

	@Override
	public List<ChoiceQuestion> getAll() {
		return this.choiceQuestionDaoImpl.getAll();
	}

	@Override
	public ChoiceQuestion getById(int id) {
		return this.choiceQuestionDaoImpl.getById(id);
	}

	@Override
	public List<ChoiceQuestion> getAllByTimeDesc() {
		return this.choiceQuestionDaoImpl.getAllByTimeDesc();
	}

	@Override
	public ChoiceQuestion getByIdEager(Serializable id) {
		return this.choiceQuestionDaoImpl.getByIdEager(id);
	}

	@Override
	public void update(ChoiceQuestion t) {
	}
	
	public boolean hasData() {
		return this.choiceQuestionDaoImpl.hasData();
	}
	@Transactional
	public void clear() {
		this.choiceQuestionDaoImpl.clear();
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.choiceQuestionDaoImpl.delete(id);
	}

}
