package com.school.platform.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="classroom")
public class Classroom {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="class_name")
	private String className;
	@Column(name="class_profile")
	private String classProfile;
	@Column(name="study_year")
	private Integer studyYear;
	@Column(name="start_date")
	private String startDate;
	@Column(name="finish_date")
	private String finishDate;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="school_id")
	private School school;
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="head_teacher_account_details_id")
	private AccountDetails headTeacherAccountDetails;
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="head_student_account_details_id")
	private AccountDetails headStudentAccountDetails;
	@OneToMany(mappedBy="classroom", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<StudentDetails> studentsDetails;
	@OneToMany(mappedBy="classroom", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Course> courses;
	
	public Classroom() {
	}

	public Classroom(String className, String classProfile, int studyYear, String startDate, String finishDate, School school,
			AccountDetails headTeacherAccountDetails) {
		this.className = className;
		this.classProfile = classProfile;
		this.studyYear = studyYear;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.school = school;
		this.headTeacherAccountDetails = headTeacherAccountDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassProfile() {
		return classProfile;
	}

	public void setClassProfile(String classProfile) {
		this.classProfile = classProfile;
	}

	public Integer getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(Integer studyYear) {
		this.studyYear = studyYear;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public AccountDetails getHeadTeacherAccountDetails() {
		return headTeacherAccountDetails;
	}

	public void setHeadTeacherAccountDetails(AccountDetails headTeacherAccountDetails) {
		this.headTeacherAccountDetails = headTeacherAccountDetails;
	}

	public AccountDetails getHeadStudentAccountDetails() {
		return headStudentAccountDetails;
	}

	public void setHeadStudentAccountDetails(AccountDetails headStudentAccountDetails) {
		this.headStudentAccountDetails = headStudentAccountDetails;
	}

	public List<StudentDetails> getStudentsDetails() {
		return studentsDetails;
	}

	public void setStudentsDetails(List<StudentDetails> studentsDetails) {
		this.studentsDetails = studentsDetails;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void addStudentDetails(StudentDetails tempStudentDetails) {
		if(studentsDetails == null) {
			studentsDetails = new ArrayList<>();
		}
		studentsDetails.add(tempStudentDetails);
		tempStudentDetails.setClassroom(this);
	}
	
	public void addCourse(Course tempCourse) {
		if(courses == null) {
			courses = new ArrayList<>();
		}
		courses.add(tempCourse);
		tempCourse.setClassroom(this);
	}
}
