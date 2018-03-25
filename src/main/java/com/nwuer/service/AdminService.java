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

	public Admin getByNumberAndPass(Admin admin) {
		return this.adminDaoImpl.getByNumberAndPass(admin);
	}
	
	@Transactional
	public void update(Admin t) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		Crpty crpty = (Crpty) applicationContext.getBean("crpty");
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
		
		return this.adminDaoImpl.getById(id);
	}

	@Override
	public List<Admin> getAllByTimeDesc() {
		return this.adminDaoImpl.getAllByTimeDesc();
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
