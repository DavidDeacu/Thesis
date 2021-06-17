package com.school.platform.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.platform.entity.School;
import com.school.platform.entity.StudentDetails;

@Repository
public class StudentDetailsDAOImpl implements StudentDetailsDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<StudentDetails> getSchoolStudentDetails(School school) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery(
	          "SELECT sd FROM StudentDetails sd JOIN sd.classroom c WHERE c.school=:theSchool", StudentDetails.class);
		theQuery.setParameter("theSchool", school);
	    List<StudentDetails> resultList = theQuery.getResultList();
		return resultList;
	}
	
	@Override
	public void saveStudentDetails(StudentDetails studentDetails) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(studentDetails);
	}

	@Override
	public StudentDetails getStudentDetails(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		StudentDetails theStudentDetails = currentSession.get(StudentDetails.class, id);
		return theStudentDetails;
	}

}
