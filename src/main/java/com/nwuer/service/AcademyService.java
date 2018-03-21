package com.nwuer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.AcademyDaoImpl;
import com.nwuer.entity.Academy;
@Service
public class AcademyService implements BaseService<Academy>{
	@Autowired
	private AcademyDaoImpl academyDaoImpl;

	
	@Override
	@Transactional
	public int add(Academy t) {
		return 0;
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
	
}
