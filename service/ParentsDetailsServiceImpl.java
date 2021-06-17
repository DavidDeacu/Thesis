package com.school.platform.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.platform.dao.ParentsDetailsDAO;
import com.school.platform.entity.ParentsDetails;

@Service
public class ParentsDetailsServiceImpl implements ParentsDetailsService {
	@Autowired
	private ParentsDetailsDAO parentsDetailsDAO;
	
	@Override
	@Transactional
	public void saveParentsDetails(ParentsDetails parentsDetails) {
		parentsDetailsDAO.saveParentsDetails(parentsDetails);
	}

}
