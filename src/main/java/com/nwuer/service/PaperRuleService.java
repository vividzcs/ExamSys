package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.PaperRuleDaoImpl;
import com.nwuer.entity.PaperRule;

@Service
public class PaperRuleService implements BaseService<PaperRule>{
	@Autowired
	private PaperRuleDaoImpl paperRuleDaoImpl;
	@Override
	@Transactional
	public int add(PaperRule t) {
		return this.paperRuleDaoImpl.add(t);
	}

	@Override
	public List<PaperRule> getAll() {
		return this.paperRuleDaoImpl.getAll();
	}

	@Override
	public PaperRule getById(int id) {
		return this.paperRuleDaoImpl.getById(id);
	}

	@Override
	public List<PaperRule> getAllByTimeDesc() {
		return this.getAllByTimeDesc();
	}

	@Override
	public PaperRule getByIdEager(Serializable id) {
		return this.paperRuleDaoImpl.getByIdEager(id);
	}

	@Override
	@Transactional
	public void update(PaperRule t) {
		this.paperRuleDaoImpl.update(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.paperRuleDaoImpl.delete(id);
	}
	
	public int getIdByMajorAndSubject(int m_id,int sub_id) {
		return this.paperRuleDaoImpl.getIdByMajorAndSubject(m_id, sub_id);
	}

}
