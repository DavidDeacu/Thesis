package com.school.platform.pojo;

public class Ranking {
	private int classroomRanking;
	private int schoolRanking;
	
	public Ranking() {
	}
	
	public Ranking(int classroomRanking, int schoolRanking) {
		this.classroomRanking = classroomRanking;
		this.schoolRanking = schoolRanking;
	}

	public int getClassroomRanking() {
		return classroomRanking;
	}
	public void setClassroomRanking(int classroomRanking) {
		this.classroomRanking = classroomRanking;
	}
	public int getSchoolRanking() {
		return schoolRanking;
	}
	public void setSchoolRanking(int schoolRanking) {
		this.schoolRanking = schoolRanking;
	}
	
	
}
