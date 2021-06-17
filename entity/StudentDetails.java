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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="student_details")
public class StudentDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="current_year_of_study")
	private Integer currentYearOfStudy;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="parents_details_id")
	private ParentsDetails parentsDetails;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="class_id")
	private Classroom classroom;
	@OneToOne(mappedBy="studentDetails", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private User user;
	@ManyToMany
	@JoinTable(name="course_studentdetails",
			joinColumns=@JoinColumn(name="student_details_id"),
			inverseJoinColumns=@JoinColumn(name="course_id")
			)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Course> courses;
	
	@OneToMany(mappedBy="studentDetails", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Grade> grades;
	
	public StudentDetails() {

	}

	public StudentDetails(Integer currentYearOfStudy, ParentsDetails parentsDetails, Classroom classroom, User user) {
		this.currentYearOfStudy = currentYearOfStudy;
		this.parentsDetails = parentsDetails;
		this.classroom = classroom;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCurrentYearOfStudy() {
		return currentYearOfStudy;
	}

	public void setCurrentYearOfStudy(Integer currentYearOfStudy) {
		this.currentYearOfStudy = currentYearOfStudy;
	}

	public ParentsDetails getParentsDetails() {
		return parentsDetails;
	}

	public void setParentsDetails(ParentsDetails parentsDetails) {
		this.parentsDetails = parentsDetails;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	
	public void addGrade(Grade tempGrade) {
		if(grades == null) {
			grades = new ArrayList<>();
		}
		grades.add(tempGrade);
		tempGrade.setStudentDetails(this);
	}
}
