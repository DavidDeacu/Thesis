package com.school.platform.dao;

import com.school.platform.entity.Classroom;

public interface ClassroomDAO {
//	public List<Classroom> getSchoolClassrooms(String theCity);
	public Classroom getClassroom(int id);
	public void saveClassroom(Classroom classroom);
	public String getFieldValue(String classroomId, String field);
}
