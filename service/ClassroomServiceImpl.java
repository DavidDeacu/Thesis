package com.school.platform.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.platform.dao.ClassroomDAO;
import com.school.platform.entity.Classroom;
import com.school.platform.entity.User;

@Service
public class ClassroomServiceImpl implements ClassroomService {
	@Autowired
	private ClassroomDAO classroomDAO;

//	@Override
//	@Transactional
//	public List<Classroom> getSchoolClassrooms(String theCity) {
//		return classroomDAO.getSchoolClassrooms(theCity);
//	}
	
	@Override
	public List<Classroom> getSchoolClassrooms(User theUser) {
		return theUser.getTeacherDetails().getSchool().getClassrooms();
	}

	@Override
	@Transactional
	public Classroom getClassroom(int id) {
		return classroomDAO.getClassroom(id);
	}

	@Override
	@Transactional
	public void saveClassroom(Classroom classroom) {
		classroomDAO.saveClassroom(classroom);
	}

	@Override
	@Transactional
	public String getFieldValue(String classroomId, String field) {
		return classroomDAO.getFieldValue(classroomId, field);
	}
}
