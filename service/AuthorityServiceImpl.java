package com.school.platform.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.platform.dao.AuthorityDAO;
import com.school.platform.entity.Authority;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	private AuthorityDAO authorityDAO;
	@Override
	@Transactional
	public void saveAuthority(Authority authority) {
		authorityDAO.saveAuthority(authority);
	}

}
