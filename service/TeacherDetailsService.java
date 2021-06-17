package com.school.platform.service;

import java.util.List;

import com.school.platform.entity.School;
import com.school.platform.entity.Teacher;

public interface TeacherDetailsService {
	public void saveTeacherDetails(Teacher teacherDetails);
	public List<String> getPositions();
	public List<String> getSubjects();
	public List<Teacher> getTeachersFromSchool(School school);
	public Teacher getTeacher(int id);
}
