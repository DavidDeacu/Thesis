package com.license.cd.dao;

import java.util.List;

import com.license.cd.entity.Student;

public interface StudentDAO {
	
	public List<Student> getStudents();
	
	public void saveStudent(Student student);
	
	public Student getStudent(int theId);
	
	public void deleteStudent(int theId);

}
