package com.school.platform.pojo;

public class CourseAvg {
	private String courseName;
	private Float averageMark;
	private int classYear;
	
	public CourseAvg() {
	}

	public CourseAvg(String courseName, Float averageMark, int classYear) {
		this.courseName = courseName;
		this.averageMark = averageMark;
		this.classYear = classYear;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Float getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(Float averageMark) {
		this.averageMark = averageMark;
	}

	public int getClassYear() {
		return classYear;
	}

	public void setClassYear(int classYear) {
		this.classYear = classYear;
	}
	
	
	
	
}
