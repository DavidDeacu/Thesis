package com.school.platform.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="users")
public class User {
		@Id
		@NotNull(message="is required")
		@Size(min=1, message="is required")
		@Column(name="username")
	private String username;
		@NotNull(message="is required")
		@Size(min=1, message="is required")
		@Column(name="password")
	private String password;
		@Column(name="enabled")
	private int enabled;
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="student_details_id")
	private StudentDetails studentDetails;
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="account_details_id")
	private AccountDetails accountDetails;
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="teacher_id")
	private Teacher teacherDetails;
		@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
		@LazyCollection(LazyCollectionOption.FALSE)
	private List<Authority> authorities;
	
	public User() {
		enabled = 1;
	}

	public User(String username, String password, int enabled, StudentDetails studentDetails,
			AccountDetails accountDetails, Teacher teacherDetails) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.studentDetails = studentDetails;
		this.accountDetails = accountDetails;
		this.teacherDetails = teacherDetails;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public StudentDetails getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(StudentDetails studentDetails) {
		this.studentDetails = studentDetails;
	}

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}

	public Teacher getTeacherDetails() {
		return teacherDetails;
	}

	public void setTeacherDetails(Teacher teacherDetails) {
		this.teacherDetails = teacherDetails;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public void addAuthority(Authority tempAuthority) {
		if(authorities == null) {
			authorities = new ArrayList<>();
		}
		authorities.add(tempAuthority);
		tempAuthority.setUser(this);
	}
}
