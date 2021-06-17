package com.school.platform.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.platform.dao.TeacherDetailsDAO;
import com.school.platform.entity.School;
import com.school.platform.entity.Teacher;

@Service
public class TeacherDetailsServiceImpl implements TeacherDetailsService {
	@Autowired
	private TeacherDetailsDAO teacherDetailsDAO;

	@Override
	@Transactional
	public void saveTeacherDetails(Teacher teacherDetails) {
		teacherDetailsDAO.saveTeacherDetails(teacherDetails);
	}

	@Override
	public List<String> getPositions() {
		List<String> positions = new ArrayList<>();
		positions.add("Gradul didactic I");
		positions.add("Gradul didactic II");
		return positions;
	}

	@Override
	public List<String> getSubjects() {
		List<String> subjects = new ArrayList<>();
		subjects.add("Mathematics");
		subjects.add("Literature");
		subjects.add("Physics");
		subjects.add("Chemistry");
		subjects.add("Biology");
		subjects.add("Physical education");
		subjects.add("Music");
		subjects.add("Art");
		subjects.add("Information technology");
		subjects.add("Natural sciences");
		subjects.add("History");
		subjects.add("Geography");
		subjects.add("Foreign languages");
		return subjects;
	}

	@Override
	@Transactional
	public List<Teacher> getTeachersFromSchool(School school) {
		return teacherDetailsDAO.getTeachersFromSchool(school);
	}

	@Override
	@Transactional
	public Teacher getTeacher(int id) {
		return teacherDetailsDAO.getTeacher(id);
	}
	
}
