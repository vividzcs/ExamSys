package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.AdminDaoImpl;
import com.nwuer.entity.Admin;

@Service
public class AdminService implements BaseService<Admin> {
	@Autowired
	private AdminDaoImpl adminDaoImpl;

	public Admin getByNumberAndPass(Admin admin) {
		return this.adminDaoImpl.getByNumberAndPass(admin);
	}
	
	@Transactional
	public void update(Admin t) {
		this.adminDaoImpl.update(t);
	}

	@Override
	public int add(Admin t) {
		return 0;
	}

	@Override
	public List<Admin> getAll() {
		return null;
	}

	@Override
	public Admin getById(int id) {
		return this.adminDaoImpl.getById(id);
	}

	@Override
	public List<Admin> getAllByTimeDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin getByIdEager(Serializable id) {
		return this.adminDaoImpl.getByIdEager(id);
	}
	
	@Transactional
	public void updateLastLogin(long t,int id) {
		this.adminDaoImpl.updateLastLogin(t,id);
	}
}
