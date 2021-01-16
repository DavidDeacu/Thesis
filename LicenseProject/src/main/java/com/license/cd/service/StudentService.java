package com.license.cd.service;

import java.util.List;

import com.license.cd.entity.Mark;
import com.license.cd.entity.Student;

public interface StudentService {
	
	public List<Student> getStudents();
	
	public void saveStudent(Student student);
	
	public Student getStudent(int theId);
	
	public void deleteStudent(int theId);
	
	public void addMark(Student student, Mark mark);

}
