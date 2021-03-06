package com.license.cd.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.license.cd.entity.Mark;
import com.license.cd.entity.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {
	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Student> getStudents() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ordered by last name
		Query<Student> theQuery = currentSession.createQuery("from Student order by lastName", Student.class);
		
		//execute the query and get result list
		List<Student> students = theQuery.getResultList();
		
		//return the results
		return students;
	}

	@Override
	public void saveStudent(Student student) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save or update the student
		currentSession.saveOrUpdate(student);
		
	}

	@Override
	public Student getStudent(int theId) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve or read from database using the primary key
		Student theStudent = currentSession.get(Student.class, theId);
		
		return theStudent;
	}

	@Override
	public void deleteStudent(int theId) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Student theStudent = currentSession.get(Student.class, theId);
		
		//delete the object using the primary key
		currentSession.delete(theStudent);
	}

	@Override
	public void addMark(Student student, Mark mark) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Student theStudent = currentSession.get(Student.class, student.getId());
		
		List<Mark> marks = theStudent.getMarks();
		
		if(marks == null) {
			marks = new ArrayList<>();
		}
		
		marks.add(mark);
		mark.setStudent(student);
	}

}
