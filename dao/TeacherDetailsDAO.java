package com.school.platform.dao;

import java.util.List;

import com.school.platform.entity.School;
import com.school.platform.entity.Teacher;

public interface TeacherDetailsDAO {
	public void saveTeacherDetails(Teacher teacherDetails);
	public List<Teacher> getTeachersFromSchool(School school);
	public Teacher getTeacher(int id);
}
