package com.school.platform.dao;

import java.util.List;

import com.school.platform.entity.Course;

public interface CourseDAO {
	public void saveCourse(Course course);
	public Course getCourse(int courseId);
	public String getFieldValue(String courseId, String field);
	public List<Course> getTeacherCourses(int teacherId);
}
