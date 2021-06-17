package com.school.platform.service;

import java.util.List;

import com.school.platform.entity.Classroom;
import com.school.platform.entity.User;

public interface ClassroomService {
//	public List<Classroom> getSchoolClassrooms(String theCity);
	public List<Classroom> getSchoolClassrooms(User theUser);
	public Classroom getClassroom(int id);
	public void saveClassroom(Classroom classroom);
	public String getFieldValue(String classroomId, String field);
}
