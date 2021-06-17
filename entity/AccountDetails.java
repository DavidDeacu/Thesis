package com.school.platform.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account_details")
public class AccountDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="date_of_birth")
	private String dateOfBirth;
	@Column(name="cnp")
	private String cnp;
	@Column(name="email")
	private String email;
	@Column(name="home_address_street")
	private String homeAddressStreet;
	@Column(name="home_address_number")
	private String homeAddressNumber;
	@Column(name="personal_telephone")
	private String personalTelephone;
	@Column(name="country")
	private String country;
	@Column(name="city")
	private String city;
	@OneToOne(mappedBy="accountDetails", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private User user;
	@OneToOne(mappedBy="headTeacherAccountDetails", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private Classroom classroomInChargeForTeacher;
	@OneToOne(mappedBy="headStudentAccountDetails", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private Classroom classroomInChargeForStudent;
	
	
	public AccountDetails() {
	}

	public AccountDetails(String firstName, String lastName, String dateOfBirth, String cnp, String email, String homeAddressStreet,
			String homeAddressNumber, String personalTelephone, String country, String city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.cnp = cnp;
		this.email = email;
		this.homeAddressStreet = homeAddressStreet;
		this.homeAddressNumber = homeAddressNumber;
		this.personalTelephone = personalTelephone;
		this.country = country;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeAddressStreet() {
		return homeAddressStreet;
	}

	public void setHomeAddressStreet(String homeAddressStreet) {
		this.homeAddressStreet = homeAddressStreet;
	}

	public String getHomeAddressNumber() {
		return homeAddressNumber;
	}

	public void setHomeAddressNumber(String homeAddressNumber) {
		this.homeAddressNumber = homeAddressNumber;
	}

	public String getPersonalTelephone() {
		return personalTelephone;
	}

	public void setPersonalTelephone(String personalTelephone) {
		this.personalTelephone = personalTelephone;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Classroom getClassroomInChargeForTeacher() {
		return classroomInChargeForTeacher;
	}

	public void setClassroomInChargeForTeacher(Classroom classroomInChargeForTeacher) {
		this.classroomInChargeForTeacher = classroomInChargeForTeacher;
	}

	public Classroom getClassroomInChargeForStudent() {
		return classroomInChargeForStudent;
	}

	public void setClassroomInChargeForStudent(Classroom classroomInChargeForStudent) {
		this.classroomInChargeForStudent = classroomInChargeForStudent;
	}
}
