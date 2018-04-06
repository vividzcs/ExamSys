package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.ObjectiveAnswerDaoImpl;
import com.nwuer.entity.ObjectiveAnswer;

@Service
public class ObjectiveAnswerService implements BaseService<ObjectiveAnswer> {
	@Autowired
	private ObjectiveAnswerDaoImpl answerDaoImpl;
	
	@Override
	@Transactional
	public int add(ObjectiveAnswer t) {
		t.setCreate_time(System.currentTimeMillis());
		return this.answerDaoImpl.add(t);
	}

	@Override
	public List<ObjectiveAnswer> getAll() {
		return this.answerDaoImpl.getAll();
	}

	@Override
	public ObjectiveAnswer getById(int id) {
		return this.answerDaoImpl.getById(id);
	}

	@Override
	public List<ObjectiveAnswer> getAllByTimeDesc() {
		return this.answerDaoImpl.getAllByTimeDesc();
	}

	@Override
	public ObjectiveAnswer getByIdEager(Serializable id) {
		return this.answerDaoImpl.getByIdEager(id);
	}

	@Override
	@Transactional
	public void update(ObjectiveAnswer t) {
		this.answerDaoImpl.update(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.answerDaoImpl.delete(id);
	}

}
