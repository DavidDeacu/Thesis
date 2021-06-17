package com.school.platform.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.platform.dao.CourseDAO;
import com.school.platform.entity.Course;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDAO courseDAO;

	@Override
	@Transactional
	public void saveCourse(Course course) {
		courseDAO.saveCourse(course);
	}

	@Override
	@Transactional
	public Course getCourse(int courseId) {
		return courseDAO.getCourse(courseId);
	}

	@Override
	@Transactional
	public String getFieldValue(String courseId, String field) {
		return courseDAO.getFieldValue(courseId, field);
	}

	@Override
	@Transactional
	public List<Course> getTeacherCourses(int teacherId) {
		return courseDAO.getTeacherCourses(teacherId);
	}

}
