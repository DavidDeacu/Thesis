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
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="course")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="course_name")
	private String courseName;
	@Column(name="course_description")
	private String courseDescription;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="school_id")
	private School school;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="teacher_id")
	private Teacher teacher;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="class_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Classroom classroom;
	@ManyToMany
	@JoinTable(name="course_studentdetails",
			joinColumns=@JoinColumn(name="course_id"),
			inverseJoinColumns=@JoinColumn(name="student_details_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<StudentDetails> studentsDetails;
	@OneToMany(mappedBy="course", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Grade> grades;
	@OneToMany(mappedBy="resource", cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Resource> resources;
	
	public Course() {
	}

	public Course(String courseName, String courseDescription, School school, Teacher teacher, Classroom classroom) {
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.school = school;
		this.teacher = teacher;
		this.classroom = classroom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public List<StudentDetails> getStudentsDetails() {
		return studentsDetails;
	}

	public void setStudentsDetails(List<StudentDetails> studentsDetails) {
		this.studentsDetails = studentsDetails;
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
		tempGrade.setCourse(this);
	}
	
	public void addResource(Resource tempResource) {
		if(resources == null) {
			resources = new ArrayList<>();
		}
		resources.add(tempResource);
		tempResource.setCourse(this);
	}
	
}
