package com.school.platform.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="school")
public class School {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="school_name")
	private String schoolName;
	@Column(name="school_abbreviation")
	private String schoolAbbreviation;
	@Column(name="country")
	private String country;
	@Column(name="city")
	private String city;
	@Column(name="school_address_street")
	private String schoolAddressStreet;
	@Column(name="school_address_number")
	private String schoolAddressNumber;
	@Column(name="web_site")
	private String webSite;
	@OneToMany(mappedBy="school", cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Classroom> classrooms;
	@OneToMany(mappedBy="school", cascade=CascadeType.ALL)
	private List<Course> courses;
	@OneToMany(mappedBy="school", cascade=CascadeType.ALL)
	private List<Teacher> teachers;
	
	public School() {
	}

	public School(String schoolName, String schoolAbbreviation, String country, String city, String schoolAddressStreet,
			String schoolAddressNumber, String webSite) {
		this.schoolName = schoolName;
		this.schoolAbbreviation = schoolAbbreviation;
		this.country = country;
		this.city = city;
		this.schoolAddressStreet = schoolAddressStreet;
		this.schoolAddressNumber = schoolAddressNumber;
		this.webSite = webSite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolAbbreviation() {
		return schoolAbbreviation;
	}

	public void setSchoolAbbreviation(String schoolAbbreviation) {
		this.schoolAbbreviation = schoolAbbreviation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSchoolAddressStreet() {
		return schoolAddressStreet;
	}

	public void setSchoolAddressStreet(String schoolAddressStreet) {
		this.schoolAddressStreet = schoolAddressStreet;
	}

	public String getSchoolAddressNumber() {
		return schoolAddressNumber;
	}

	public void setSchoolAddressNumber(String schoolAddressNumber) {
		this.schoolAddressNumber = schoolAddressNumber;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public List<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List<Classroom> classrooms) {
		this.classrooms = classrooms;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public void addClassroom(Classroom tempClassroom) {
		if(classrooms == null) {
			classrooms = new ArrayList<>();
		}
		classrooms.add(tempClassroom);
		tempClassroom.setSchool(this);
	}
	
	public void addCourse(Course tempCourse) {
		if(courses == null) {
			courses = new ArrayList<>();
		}
		courses.add(tempCourse);
		tempCourse.setSchool(this);
	}
	
	public void addTeacher(Teacher tempTeacher) {
		if(teachers == null) {
			teachers = new ArrayList<>();
		}
		teachers.add(tempTeacher);
		tempTeacher.setSchool(this);
	}
}
