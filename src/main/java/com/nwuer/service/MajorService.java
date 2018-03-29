package com.nwuer.service;

import java.io.Serializable;
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
		t.setCreate_time(System.currentTimeMillis());
		return this.majorDaoImpl.add(t);
	}
	
	public int getIdByName(String name) {
		return this.majorDaoImpl.getIdByName(name);
	}

	@Override
	public List<Major> getAll() {
		return this.majorDaoImpl.getAll();
	}

	@Override
	public Major getById(int id) {
		return this.majorDaoImpl.getById(id);
	}

	@Override
	public List<Major> getAllByTimeDesc() {
		return this.majorDaoImpl.getAllByTimeDesc();
	}

	@Override
	public Major getByIdEager(Serializable id) {
		return this.getByIdEager(id);
	}

	@Override
	@Transactional
	public void update(Major t) {
		this.majorDaoImpl.update(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.majorDaoImpl.delete(id);
	}

}
