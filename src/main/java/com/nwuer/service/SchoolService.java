package com.nwuer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwuer.entity.School;
import com.nwuer.utils.PropertyUtil;
@Service
public class SchoolService {
	@Autowired
	private PropertyUtil propertyUtil;

	public void update(School school) {
		this.propertyUtil.update(school);
	}
	
	
	public School get() {
		return this.propertyUtil.get();
	}

}
