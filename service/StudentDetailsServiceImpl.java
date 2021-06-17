package com.school.platform.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.platform.dao.StudentDetailsDAO;
import com.school.platform.entity.School;
import com.school.platform.entity.StudentDetails;

@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {
	@Autowired
	private StudentDetailsDAO studentDetailsDAO;
	
	@Override
	@Transactional
	public List<StudentDetails> getSchoolStudentDetails(School school) {
		return studentDetailsDAO.getSchoolStudentDetails(school);
	}
	
	@Override
	@Transactional
	public void saveStudentDetails(StudentDetails studentDetails) {
		studentDetailsDAO.saveStudentDetails(studentDetails);
	}

	@Override
	@Transactional
	public StudentDetails getStudentDetails(int id) {
		return studentDetailsDAO.getStudentDetails(id);
	}


	
}
