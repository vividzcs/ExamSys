package com.nwuer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwuer.daoimpl.AcademyDaoImpl;
import com.nwuer.entity.Academy;
@Service
public class AcademyService implements BaseService<Academy>{
	@Autowired
	private AcademyDaoImpl academyDaoImpl;

	
	@Override
	public int add(Academy t) {
		return 0;
	}
	
	public int getIdByName(String name) {
		return this.academyDaoImpl.getIdByName(name);
	}
}
