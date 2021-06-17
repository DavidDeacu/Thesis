package com.school.platform.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.platform.entity.Grade;

@Repository
public class GradeDAOImpl implements GradeDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Grade> getGrades() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Grade> theQuery = currentSession.createQuery("FROM Grade order by date", Grade.class);
		List<Grade> grades = theQuery.getResultList();
		return grades;
	}

	@Override
	public Grade getGrade(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Grade theGrade = currentSession.get(Grade.class, id);
		return theGrade;
	}

	@Override
	public void saveGrade(Grade grade) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(grade);
	}
	
	
}
