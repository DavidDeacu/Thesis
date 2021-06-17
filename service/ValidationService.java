package com.school.platform.service;

import java.util.List;

import org.springframework.ui.Model;

import com.school.platform.pojo.InputError;

public interface ValidationService {
	public List<InputError> validate(String firstName, String lastName, String dateOfBirth,
			String cnp, String email, String homeAddressStreet, String homeAddressNumber, 
			String personalTelephone, String country, String city, String classroomId,
			String fatherFirstName, String fatherLastName, String fatherTelephone, 
			String motherFirstName, String motherLastName, String motherTelephone);
	public List<InputError> validate(String firstName, String lastName, String dateOfBirth,
			String cnp, String email, String homeAddressStreet, String homeAddressNumber, 
			String personalTelephone, String country, String city, 
			String subject, String position, String graduationYear);
	public List<InputError> validate(String teacherId);
	public List<InputError> validate(String courseDescription, String classroomId);
	public List<InputError> validate(String mark, String date, String description);
	public List<InputError> validate(String className, String classProfile, String studyYear,
			String startDate, String finishDate, String teacherId);
	public void loadStudentModelAttribute(Model theModel, String firstName, String lastName, String dateOfBirth,
			String cnp, String email, String homeAddressStreet, String homeAddressNumber, 
			String personalTelephone, String country, String city,
			String fatherFirstName, String fatherLastName, String fatherTelephone, 
			String motherFirstName, String motherLastName, String motherTelephone);
	public void loadTeacherModelAttribute(Model theModel, String firstName, String lastName, String dateOfBirth,
			String cnp, String email, String homeAddressStreet, String homeAddressNumber, 
			String personalTelephone, String country, String city,
			String subject, String position, String graduationYear);
	public void loadCourseModelAttribute(Model theModel, String courseDescription);
	public void loadClassroomModelAttribute(Model theModel, String className, String classProfile, String startDate, String finishDate);
	public void loadGradeModelAttribute(Model theModel, String description);
}
