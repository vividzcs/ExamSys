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
	private ObjectiveAnswerDaoImpl objectiveAnswerDaoImpl;
	
	@Override
	@Transactional
	public int add(ObjectiveAnswer t) {
		t.setCreate_time(System.currentTimeMillis());
		return this.objectiveAnswerDaoImpl.add(t);
	}

	@Override
	public List<ObjectiveAnswer> getAll() {
		return this.objectiveAnswerDaoImpl.getAll();
	}

	@Override
	public ObjectiveAnswer getById(int id) {
		return this.objectiveAnswerDaoImpl.getById(id);
	}

	@Override
	public List<ObjectiveAnswer> getAllByTimeDesc() {
		return this.objectiveAnswerDaoImpl.getAllByTimeDesc();
	}

	@Override
	public ObjectiveAnswer getByIdEager(Serializable id) {
		return this.objectiveAnswerDaoImpl.getByIdEager(id);
	}

	@Override
	@Transactional
	public void update(ObjectiveAnswer t) {
		this.objectiveAnswerDaoImpl.update(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.objectiveAnswerDaoImpl.delete(id);
	}
	
	public ObjectiveAnswer getByUuid(String uuid) {
		return this.objectiveAnswerDaoImpl.getByUuid(uuid);
	}
	

}
