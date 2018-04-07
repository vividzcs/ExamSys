package com.nwuer.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nwuer.daoimpl.GuardianShipDaoImpl;
import com.nwuer.entity.GuardianShip;

@Service
public class GuardianShipService implements BaseService<GuardianShip> {
	@Autowired
	private GuardianShipDaoImpl guardianShipDaoImpl;
	
	@Override
	@Transactional
	public int add(GuardianShip t) {
		t.setCreate_time(System.currentTimeMillis());
		return this.guardianShipDaoImpl.add(t);
	}

	@Override
	public List<GuardianShip> getAll() {
		return this.guardianShipDaoImpl.getAll();
	}

	@Override
	public GuardianShip getById(int id) {
		return this.guardianShipDaoImpl.getById(id);
	}

	@Override
	public List<GuardianShip> getAllByTimeDesc() {
		return this.guardianShipDaoImpl.getAllByTimeDesc();
	}

	@Override
	public GuardianShip getByIdEager(Serializable id) {
		return this.guardianShipDaoImpl.getByIdEager(id);
	}

	@Override
	@Transactional
	public void update(GuardianShip t) {
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		this.guardianShipDaoImpl.delete(id);
	}
	
	public boolean hasData() {
		return this.guardianShipDaoImpl.hasData();
	}
	
	@Transactional
	public void clear() {
		this.guardianShipDaoImpl.clear();
	}
	
	public int getByMajorAndSubject(int m_id,int sub_id) {
		return this.guardianShipDaoImpl.getByMajorAndSubject(m_id, sub_id);
	}
	
	public GuardianShip getGuardByMajorAndSubject(int m_id,int sub_id) {
		return this.guardianShipDaoImpl.getGuardByMajorAndSubject(m_id, sub_id);
	}

}
