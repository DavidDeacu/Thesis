package com.school.platform.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.platform.entity.Course;
import com.school.platform.entity.StudentDetails;

@Repository
public class CourseDAOImpl implements CourseDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveCourse(Course course) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(course);
	}

	@Override
	public Course getCourse(int courseId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Course theCourse = currentSession.get(Course.class, courseId);
		return theCourse;
	}

	@Override
	public String getFieldValue(String courseId, String field) {
		Course course = getCourse(Integer.parseInt(courseId));
		switch(field) {
		case "course.courseDescription":
			return course.getCourseDescription();
		default:
			System.out.println("Error: Retrieved field does not match.");
			return null;
		}
	}

	@Override
	public List<Course> getTeacherCourses(int teacherId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery(
		          "SELECT c FROM Course c JOIN c.teacher t WHERE t.id=:teacherId", Course.class);
			theQuery.setParameter("teacherId", teacherId);
		    List<Course> resultList = theQuery.getResultList();
		    
		    return resultList;
	}

}
