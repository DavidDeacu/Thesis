package com.school.platform.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.platform.entity.AccountDetails;

@Repository
public class AccountDetailsDAOImpl implements AccountDetailsDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveAccountDetails(AccountDetails accountDetails) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(accountDetails);
	}

}
