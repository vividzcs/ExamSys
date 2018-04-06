package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.JudgeQuestionTestDaoImpl;
import com.nwuer.entity.JudgeQuestionTest;

@Service
public class JudgeQuestionTestService implements BaseService<JudgeQuestionTest> {
	@Autowired
	private JudgeQuestionTestDaoImpl judgeQuestionTestDaoImpl;
	
	
	@Override
	@Transactional
	public int add(JudgeQuestionTest t) {
		return this.judgeQuestionTestDaoImpl.add(t);
	}

	@Override
	public List<JudgeQuestionTest> getAll() {
		return this.judgeQuestionTestDaoImpl.getAll();
	}

	@Override
	public JudgeQuestionTest getById(int id) {
		return this.judgeQuestionTestDaoImpl.getById(id);
	}

	@Override
	public List<JudgeQuestionTest> getAllByTimeDesc() {
		return this.judgeQuestionTestDaoImpl.getAllByTimeDesc();
	}

	@Override
	public JudgeQuestionTest getByIdEager(Serializable id) {
		return this.judgeQuestionTestDaoImpl.getByIdEager(id);
	}

	@Override
	public void update(JudgeQuestionTest t) {
	}
	
	public boolean hasData() {
		return this.judgeQuestionTestDaoImpl.hasData();
	}
	
	@Transactional
	public void clear() {
		this.judgeQuestionTestDaoImpl.clear();
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.judgeQuestionTestDaoImpl.delete(id);
	}

}
