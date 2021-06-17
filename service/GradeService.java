package com.school.platform.service;

import java.util.List;

import com.school.platform.entity.Course;
import com.school.platform.entity.Grade;
import com.school.platform.entity.User;
import com.school.platform.pojo.CourseAvg;
import com.school.platform.pojo.Ranking;
import com.school.platform.pojo.SortedGrade;

public interface GradeService {
	public List<Grade> getGrades();
	public Grade getGrade(int id);
	public void saveGrade(Grade grade);
	public List<SortedGrade> getSortedMarks(User theUser);
	public Ranking getRanking(User theUser, Course theCourse);
}
