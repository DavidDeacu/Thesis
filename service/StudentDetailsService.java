package com.school.platform.service;

import java.util.List;

import com.school.platform.entity.School;
import com.school.platform.entity.StudentDetails;

public interface StudentDetailsService {
	public List<StudentDetails> getSchoolStudentDetails(School school);
	public void saveStudentDetails(StudentDetails studentDetails);
	public StudentDetails getStudentDetails(int id);
}
