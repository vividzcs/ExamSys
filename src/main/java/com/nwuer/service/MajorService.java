package com.nwuer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwuer.daoimpl.MajorDaoImpl;
import com.nwuer.entity.Major;

@Service
public class MajorService implements BaseService<Major> {
	@Autowired
	private MajorDaoImpl majorDaoImpl;

	@Override
	public int add(Major t) {
		return 0;
	}
	
	public int getIdByName(String name) {
		return this.majorDaoImpl.getIdByName(name);
	}

}
