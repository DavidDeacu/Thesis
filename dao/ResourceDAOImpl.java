package com.school.platform.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.platform.entity.Course;
import com.school.platform.entity.Resource;

@Repository
public class ResourceDAOImpl implements ResourceDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveResource(Resource resource) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(resource);
	}

	@Override
	public List<Resource> getCourseResources(Course course) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery(
	          "SELECT r FROM Resource r WHERE r.course=:theCourse", Resource.class);
		theQuery.setParameter("theCourse", course);
	    List<Resource> resultList = theQuery.getResultList();
		return resultList;
	}

}
