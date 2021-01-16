package com.license.cd.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.license.cd.entity.Mark;

@Repository
public class MarkDAOImpl implements MarkDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public List<Mark> getAllMarks(int id) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ordered by subject
		Query<Mark> theQuery = currentSession.createQuery("from Mark where student_id like :theId", Mark.class);
		theQuery.setParameter("theId", id);
	
		
		//execute the query and get result list
		List<Mark> marks = theQuery.getResultList();
		
		//return the results
		return marks;
	}

	@Override
	public void saveMark(Mark mark) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save or update the student
		currentSession.saveOrUpdate(mark);
	}

	@Override
	public Mark getMark(int theId) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve or read from database using the primary key
		Mark theMark = currentSession.get(Mark.class, theId);
		
		return theMark;
	}

	@Override
	public void deleteMark(int theId) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete the object using the primary key
		Query theQuery = currentSession.createQuery("delete from Mark where id=:markId");
		theQuery.setParameter("markId", theId);
		
		theQuery.executeUpdate();
	}

}
