package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.PaperDaoImpl;
import com.nwuer.entity.Paper;

@Service
public class PaperService {
	@Autowired
	private PaperDaoImpl paperDaoImpl;
	
	@Transactional
	public String add(Paper t) {
		t.setCreate_time(System.currentTimeMillis());
		return this.paperDaoImpl.add(t);
	}

	public List<Paper> getAll() {
		return this.paperDaoImpl.getAll();
	}

	public Paper getById(String uuid) {
		return this.paperDaoImpl.getById(uuid);
	}

	public List<Paper> getAllByTimeDesc() {
		return null;
	}

	public Paper getByIdEager(String uuid) {
		return this.paperDaoImpl.getByIdEager(uuid);
	}

	public void update(Paper t) {
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
