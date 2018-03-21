package com.nwuer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.MajorDaoImpl;
import com.nwuer.entity.Major;

@Service
public class MajorService implements BaseService<Major> {
	@Autowired
	private MajorDaoImpl majorDaoImpl;

	@Override
	@Transactional
	public int add(Major t) {
		return 0;
	}
	
	public int getIdByName(String name) {
		return this.majorDaoImpl.getIdByName(name);
	}

	@Override
	public List<Major> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
