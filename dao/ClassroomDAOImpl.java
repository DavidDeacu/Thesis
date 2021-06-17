package com.school.platform.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.platform.entity.Classroom;
import com.school.platform.entity.User;

@Repository
public class ClassroomDAOImpl implements ClassroomDAO {
	@Autowired
	private SessionFactory sessionFactory;

//	@Override
//	public List<Classroom> getSchoolClassrooms(String theCity) {
//		Session currentSession = sessionFactory.getCurrentSession();
//		Query<Classroom> theQuery = currentSession.createQuery("SELECT c FROM Classroom c inner join c.school s where s.city=:theCity", Classroom.class);
//		theQuery.setParameter("theCity", theCity);
//		List<Classroom> results = theQuery.getResultList();
//		return results;
//	}

	@Override
	public Classroom getClassroom(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Classroom theClassroom = currentSession.get(Classroom.class, id);
		return theClassroom;
	}

	@Override
	public void saveClassroom(Classroom classroom) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(classroom);
	}
	
	@Override
	public String getFieldValue(String classroomId, String field) {
		Classroom classroom = getClassroom(Integer.parseInt(classroomId));
		switch(field) {
		case "classroom.classname":
			return classroom.getClassName();
		case "classroom.classProfile":
			return classroom.getClassProfile();
		case "classroom.studyYear":
			return classroom.getStudyYear().toString();
		case "classroom.startDate":
			return classroom.getStartDate();
		case "classroom.finishDate":
			return classroom.getFinishDate();
		default:
			System.out.println("Error: Retrieved field does not match.");
			return null;
		}
	}

}
