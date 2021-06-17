package com.school.platform.pojo;

import java.util.List;

import com.school.platform.entity.Grade;

public class SortedGrade {
	private String subject;
	private int classYear;
	private List<Grade> grades;
	private float average;
	
	public SortedGrade() {
	}

	public SortedGrade(String subject, int classYear, List<Grade> grades) {
		this.subject = subject;
		this.classYear = classYear;
		this.grades = grades;
		
		int sum = 0;
		int markCounter = 0;
		int thesisWeight = 0;
		int thesisGrade = 0;
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
			this.average = (float) (sum / markCounter);
			if(thesisGrade > 0) {
				this.average = (float)((this.average*thesisWeight) + thesisGrade)/4; // Formula: (((sum/nrNote) * 3) + teza)/4
			}
		}
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getClassYear() {
		return classYear;
	}

	public void setClassYear(int classYear) {
		this.classYear = classYear;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public float getAverage() {
		return average;
	}

	public void setAverage(float average) {
		this.average = average;
	}

}
