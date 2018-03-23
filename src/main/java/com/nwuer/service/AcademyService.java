package com.nwuer.service;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Academy> getAllByTimeDesc() {
		return this.academyDaoImpl.getAllByTimeDesc();
	}

	


}