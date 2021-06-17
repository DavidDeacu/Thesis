package com.school.platform.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.platform.dao.AccountDetailsDAO;
import com.school.platform.entity.AccountDetails;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {
	@Autowired
	private AccountDetailsDAO accountDetailsDAO;
	
	@Override
	@Transactional
	public void saveAccountDetails(AccountDetails accountDetails) {
		accountDetailsDAO.saveAccountDetails(accountDetails);
	}

}
