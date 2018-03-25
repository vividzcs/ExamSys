package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.JudgeQuestionDaoImpl;
import com.nwuer.entity.JudgeQuestion;

@Service
public class JudgeQuestionService implements BaseService<JudgeQuestion> {
	@Autowired
	private JudgeQuestionDaoImpl judgeQuestionImpl;
	
	
	@Override
	@Transactional
	public int add(JudgeQuestion t) {
		return this.judgeQuestionImpl.add(t);
	}

	@Override
	public List<JudgeQuestion> getAll() {
		return this.judgeQuestionImpl.getAll();
	}

	@Override
	public JudgeQuestion getById(int id) {
		return this.judgeQuestionImpl.getById(id);
	}

	@Override
	public List<JudgeQuestion> getAllByTimeDesc() {
		return this.judgeQuestionImpl.getAllByTimeDesc();
	}

	@Override
	public JudgeQuestion getByIdEager(Serializable id) {
		return this.judgeQuestionImpl.getByIdEager(id);
	}

	@Override
	public void update(JudgeQuestion t) {
	}
	
	public boolean hasData() {
		return this.judgeQuestionImpl.hasData();
	}
	
	@Transactional
	public void clear() {
		this.judgeQuestionImpl.clear();
	}

}
