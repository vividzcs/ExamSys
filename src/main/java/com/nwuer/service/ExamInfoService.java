 package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.ExamInfoDaoImpl;
import com.nwuer.entity.ExamInfo;

@Service
public class ExamInfoService implements BaseService<ExamInfo> {
	@Autowired
	private ExamInfoDaoImpl examInfoDaoImpl;
	
	@Override
	@Transactional
	public int add(ExamInfo t) {
		return this.examInfoDaoImpl.add(t);
	}

	@Override
	public List<ExamInfo> getAll() {
		return this.examInfoDaoImpl.getAll();
	}

	@Override
	public ExamInfo getById(int id) {
		return this.examInfoDaoImpl.getById(id);
	}

	@Override
	public List<ExamInfo> getAllByTimeDesc() {
		return this.examInfoDaoImpl.getAllByTimeDesc();
	}

	@Override
	public ExamInfo getByIdEager(Serializable id) {
		return this.examInfoDaoImpl.getByIdEager(id);
	}

	@Override
	@Transactional
	public void update(ExamInfo t) {
		this.examInfoDaoImpl.update(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.examInfoDaoImpl.delete(id);
	}
	
	@Transactional
	public boolean increaseExamNumAll(int m_id,int sub_id) {
		int id = getIdByMajorAndSubject(m_id, sub_id);
		if(id<=0) {
			return false;
		}
		this.examInfoDaoImpl.increaseExamNumAll(id);
		return true;
	}
	
	@Transactional
	public boolean increaseExamNum(int m_id,int sub_id) {
		int id = getIdByMajorAndSubject(m_id, sub_id);
		if(id<=0) {
			return false;
		}
		this.examInfoDaoImpl.increaseExamNum(getIdByMajorAndSubject(m_id, sub_id));
		return true;
	}
	
	@Transactional
	public boolean increaseExamNumReach(int m_id,int sub_id) {
		int id = getIdByMajorAndSubject(m_id, sub_id);
		if(id<=0) {
			return false;
		}
		this.examInfoDaoImpl.increaseExamNumReach(getIdByMajorAndSubject(m_id, sub_id));
		return true;
	}
	
	public int getIdByMajorAndSubject(int m_id,int sub_id) {
		return this.examInfoDaoImpl.getIdByMajorAndSubject(m_id, sub_id);
	}
	
	public ExamInfo getByMajorAndSubject(int m_id,int sub_id) {
		return this.examInfoDaoImpl.getByMajorAndSubject(m_id, sub_id);
	}
	
	public int getEIdByPId(int p_id) {
		return this.examInfoDaoImpl.getEIdByPId(p_id);
	}
}
