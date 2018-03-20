package com.nwuer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwuer.daoimpl.AdminDaoImpl;
import com.nwuer.entity.Admin;

@Service
public class AdminService {
	@Autowired
	private AdminDaoImpl adminDaoImpl;

	public Admin getByNumberAndPass(Admin admin) {
		return this.adminDaoImpl.getByNumberAndPass(admin);
	}
}
