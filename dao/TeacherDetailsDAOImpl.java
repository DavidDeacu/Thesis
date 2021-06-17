package com.school.platform.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.platform.entity.School;
import com.school.platform.entity.Teacher;
import com.school.platform.entity.User;

@Repository
public class TeacherDetailsDAOImpl implements TeacherDetailsDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveTeacherDetails(Teacher teacherDetails) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(teacherDetails);
	}

	@Override
	public List<Teacher> getTeachersFromSchool(School school) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Teacher> theQuery = currentSession.createQuery("FROM Teacher where school.id=:schoolId order by user.username", Teacher.class);
		theQuery.setParameter("schoolId", school.getId());
		
		List<Teacher> teachers = theQuery.getResultList();
		return teachers;
	}

	@Override
	public Teacher getTeacher(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Teacher theTeacher = currentSession.get(Teacher.class, id);
		return theTeacher;
	}
}
