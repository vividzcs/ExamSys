package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nwuer.daoimpl.AdminDaoImpl;
import com.nwuer.entity.Admin;
import com.nwuer.utils.Crpty;

@Service
public class AdminService implements BaseService<Admin> {
	@Autowired
	private AdminDaoImpl adminDaoImpl;
	@Autowired
	private Crpty crpty;

	public Admin getByNumberAndPass(Admin admin) {
		admin.setAd_pass(crpty.encrypt(admin.getAd_pass()));
		return this.adminDaoImpl.getByNumberAndPass(admin);
	}
	
	@Transactional
	public void update(Admin t) {
		t.setAd_pass(crpty.encrypt(t.getAd_pass()));
		this.adminDaoImpl.update(t);
	}

	@Override
	public int add(Admin t) {
		return 0;
	}

	@Override
	public List<Admin> getAll() {
		return this.adminDaoImpl.getAll();
	}

	@Override
	public Admin getById(int id) {
		Admin admin = this.adminDaoImpl.getById(id);
		admin.setAd_pass(crpty.decrypt(admin.getAd_pass()));
		return admin;
	}

	@Override
	public List<Admin> getAllByTimeDesc() {
		return this.adminDaoImpl.getAllByTimeDesc();
	}

	@Override
	public Admin getByIdEager(Serializable id) {
		Admin admin = this.adminDaoImpl.getByIdEager(id);
		admin.setAd_pass(this.crpty.decrypt(admin.getAd_pass()));
		return admin;
	}
	
	@Transactional
	public void updateLastLogin(long t,int id) {
		this.adminDaoImpl.updateLastLogin(t,id);
	}

	@Override
	public void delete(int id) {
	}
}
