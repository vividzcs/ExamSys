package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.bean.AcademyBean;
import com.nwuer.bean.BaseBean;
import com.nwuer.daoimpl.AcademyDaoImpl;
import com.nwuer.entity.Academy;
@Service
public class AcademyService implements BaseService<Academy>{
	@Autowired
	private AcademyDaoImpl academyDaoImpl;

	
	@Override
	@Transactional
	public int add(Academy t) {
		t.setCreate_time(System.currentTimeMillis());
		return this.academyDaoImpl.add(t);
	}
	
	/**
	 * 根据院系名查询ID
	 * @param name
	 * @return
	 */
	public int getIdByName(String name) {
		return this.academyDaoImpl.getIdByName(name);
	}

	@Override
	public List<Academy> getAll() {
		return this.academyDaoImpl.getAll();
	}

	@Override
	public Academy getById(int id) {
		return this.academyDaoImpl.getById(id);
	}

	@Override
	public List<Academy> getAllByTimeDesc() {
		return this.academyDaoImpl.getAllByTimeDesc();
	}

	@Override
	public Academy getByIdEager(Serializable id) {
		return this.getByIdEager(id);
	}

	@Override
	@Transactional
	public void update(Academy t) {
		this.academyDaoImpl.update(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.academyDaoImpl.delete(id);
	}

	


}