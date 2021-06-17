package com.school.platform.dao;

import java.util.List;

import com.school.platform.entity.Course;
import com.school.platform.entity.Resource;

public interface ResourceDAO {
	
	public void saveResource(Resource resource);
	public List<Resource> getCourseResources(Course course);
}
