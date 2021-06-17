package com.school.platform.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.platform.dao.GradeDAO;
import com.school.platform.entity.Course;
import com.school.platform.entity.Grade;
import com.school.platform.entity.StudentDetails;
import com.school.platform.entity.User;
import com.school.platform.pojo.CourseAvg;
import com.school.platform.pojo.Ranking;
import com.school.platform.pojo.SortedGrade;

@Service
public class GradeServiceImpl implements GradeService {
	@Autowired
	private GradeDAO gradeDAO;
	@Autowired
	private UserService userService;
	@Autowired
	private StudentDetailsService studentDetailsService;

	@Override
	@Transactional
	public List<Grade> getGrades() {
		return gradeDAO.getGrades();
	}

	@Override
	@Transactional
	public Grade getGrade(int id) {
		return gradeDAO.getGrade(id);
	}

	@Override
	@Transactional
	public void saveGrade(Grade grade) {
		gradeDAO.saveGrade(grade);
	}


	@Override
	public List<SortedGrade> getSortedMarks(User theUser) {
		List<SortedGrade> sortedGrades = new ArrayList<>();
		for(int i=1 ; i<=12 ; i++) {
			List<Grade> gradesFromThisYear = new ArrayList<>();
			List<String> subjectsFromThisYear = new ArrayList<>();
			for(Grade grade : theUser.getStudentDetails().getGrades()) {
				if(grade.getClassYear() == i) {
					gradesFromThisYear.add(grade);
					subjectsFromThisYear.add(grade.getCourse().getCourseName());
				}
			}
			if(gradesFromThisYear.isEmpty()) {
				continue;
			}
			Set<String> set = new HashSet<>(subjectsFromThisYear);
			subjectsFromThisYear.clear();
			subjectsFromThisYear.addAll(set);
			
			for(String subject : subjectsFromThisYear) {
				
				List<Grade> gradesForThisSubject = new ArrayList<>();
				for(Grade grade : gradesFromThisYear) {
					if(grade.getCourse().getCourseName() == subject) {
						gradesForThisSubject.add(grade);
					}
				}
				sortedGrades.add(new SortedGrade(subject, i, gradesForThisSubject));
			}
		}
		return sortedGrades;
	}

	@Override
	public Ranking getRanking(User theUser, Course theCourse) {
		Ranking ranking = new Ranking();
		
		List<StudentDetails> studentsDetails = studentDetailsService.getSchoolStudentDetails(theUser.getStudentDetails().getClassroom().getSchool());
		List<Float> averagesForSchool = new ArrayList<>();
		List<Float> averagesForClassroom = new ArrayList<>();
		Float userAverage = 0.0f;
		
		for(StudentDetails studentDetails : studentsDetails) {
			List<Grade> grades = new ArrayList<>();
			for(Grade grade : studentDetails.getGrades()) {
				if(!grade.getCourse().getCourseName().equals(theCourse.getCourseName()) || grade.getCourse().getClassroom().getStudyYear() != theCourse.getClassroom().getStudyYear()) {
					continue;
				}
					
				grades.add(grade);
			}
			
			int sum = 0;
			int markCounter = 0;
			int thesisWeight = 0;
			int thesisGrade = 0;
			float average = 0.0f;
			for(Grade grade : grades) {
				if(grade.getWeight() == 1) {
					markCounter ++;
					sum += grade.getMark();
				} else if(grade.getWeight() > 1) { 
					thesisGrade = grade.getMark();
					thesisWeight = grade.getWeight();
				}
			}
			if(sum != 0) {
				average = (float) (sum / markCounter);
				if(thesisGrade > 0) {
					average = (float)((average*thesisWeight) + thesisGrade)/4; // Formula: (((sum/nrNote) * 3) + teza)/4
				}
			}
			System.out.println("Average for StudentDetails.id = " + studentDetails.getId() + " is: " + average);
			if(average > 0) {
				averagesForSchool.add(average);
				System.out.println("added for School");
				if(studentDetails.getClassroom().getId() == theUser.getStudentDetails().getClassroom().getId()) {
					averagesForClassroom.add(average);
					System.out.println("added for Classroom");
				}
			}

			if(studentDetails.getId() == theUser.getStudentDetails().getId()) {
				userAverage = average;
				System.out.println("userAverage = " + userAverage);
			}
		}
		float schoolAverage = 0.0f;
		float classroomAverage = 0.0f;
		
		float avgSum = 0.0f;
		for(Float avg : averagesForSchool) {
			avgSum += avg;
		}
		if(avgSum > 0) {
			schoolAverage = avgSum / averagesForSchool.size();
			System.out.println("schoolAverage = " + schoolAverage);
		}
		
		avgSum = 0.0f;
		for(Float avg : averagesForClassroom) {
			System.out.println("avg= " + avg);
			avgSum += avg;
		}
		
		if(avgSum > 0) {
			classroomAverage = avgSum / averagesForClassroom.size();
			System.out.println("classroomAverage = " + classroomAverage);
		}
		
		int classroomRanking = Math.round((userAverage * 50) / classroomAverage);
		int schoolRanking = Math.round((userAverage * 50) / schoolAverage);
		
		ranking.setClassroomRanking(classroomRanking);
		ranking.setSchoolRanking(schoolRanking);
		
		return ranking;
	}
	
	

}
