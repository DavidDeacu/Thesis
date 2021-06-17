package com.school.platform.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.platform.entity.Authority;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveAuthority(Authority authority) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(authority);
	}

}
