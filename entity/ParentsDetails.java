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
@Table(name="parents_details")
public class ParentsDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="father_first_name")
	private String fatherFirstName;
	@Column(name="father_last_name")
	private String fatherLastName;
	@Column(name="father_telephone")
	private String fatherTelephone;
	@Column(name="mother_first_name")
	private String motherFirstName;
	@Column(name="mother_last_name")
	private String motherLastName;
	@Column(name="mother_telephone")
	private String motherTelephone;
	@OneToOne(mappedBy="parentsDetails", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private StudentDetails studentDetails;
	
	public ParentsDetails() {
	}

	public ParentsDetails(String fatherFirstName, String fatherLastName, String fatherTelephone, String motherFirstName,
			String motherLastName, String motherTelephone) {
		this.fatherFirstName = fatherFirstName;
		this.fatherLastName = fatherLastName;
		this.fatherTelephone = fatherTelephone;
		this.motherFirstName = motherFirstName;
		this.motherLastName = motherLastName;
		this.motherTelephone = motherTelephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFatherFirstName() {
		return fatherFirstName;
	}

	public void setFatherFirstName(String fatherFirstName) {
		this.fatherFirstName = fatherFirstName;
	}

	public String getFatherLastName() {
		return fatherLastName;
	}

	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}

	public String getFatherTelephone() {
		return fatherTelephone;
	}

	public void setFatherTelephone(String fatherTelephone) {
		this.fatherTelephone = fatherTelephone;
	}

	public String getMotherFirstName() {
		return motherFirstName;
	}

	public void setMotherFirstName(String motherFirstName) {
		this.motherFirstName = motherFirstName;
	}

	public String getMotherLastName() {
		return motherLastName;
	}

	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}

	public String getMotherTelephone() {
		return motherTelephone;
	}

	public void setMotherTelephone(String motherTelephone) {
		this.motherTelephone = motherTelephone;
	}

	public StudentDetails getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(StudentDetails studentDetails) {
		this.studentDetails = studentDetails;
	}

	
}
