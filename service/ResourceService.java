package com.school.platform.service;

import java.util.List;

import com.school.platform.entity.Course;
import com.school.platform.entity.Resource;

public interface ResourceService {
	public void saveResource(Resource resource);
	public List<Resource> getCourseResources(Course course);
}
