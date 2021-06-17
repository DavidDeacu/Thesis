package com.school.platform.dao;

import java.util.List;

import com.school.platform.entity.Grade;

public interface GradeDAO {
	public List<Grade> getGrades();
	public Grade getGrade(int id);
	public void saveGrade(Grade grade);
}
