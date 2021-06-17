package com.school.platform.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.platform.dao.ResourceDAO;
import com.school.platform.entity.Course;
import com.school.platform.entity.Resource;

@Service
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private ResourceDAO resourceDAO;
	
	@Override
	@Transactional
	public void saveResource(Resource resource) {
		resourceDAO.saveResource(resource);
	}

	@Override
	@Transactional
	public List<Resource> getCourseResources(Course course) {
		return resourceDAO.getCourseResources(course);
	}

}
