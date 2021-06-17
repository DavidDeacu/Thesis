package com.school.platform.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.platform.entity.ParentsDetails;

@Repository
public class ParentsDetailsDAOImpl implements ParentsDetailsDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveParentsDetails(ParentsDetails parentsDetails) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(parentsDetails);
	}

}
