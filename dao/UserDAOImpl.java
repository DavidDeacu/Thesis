package com.school.platform.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.platform.entity.School;
import com.school.platform.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getUsers() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("FROM User order by username", User.class);
		List<User> users = theQuery.getResultList();
		return users;
	}

	@Override
	public User getUser(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		User theUser = currentSession.get(User.class, username);
		return theUser;
	}

	@Override
	public void saveUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
	}

	@Override
	public void deleteUser(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query theQuery = currentSession.createQuery("delete from User where username=:theUsername");
		theQuery.setParameter("theUsername", username);
		theQuery.executeUpdate();
	}

	@Override
	public String getFieldValue(String username, String field) {
		User user = getUser(username);
		switch(field) {
		case "user.username":
			return user.getUsername();
		case "user.accountDetails.dateOfBirth":
			return user.getAccountDetails().getDateOfBirth();
		case "user.accountDetails.email":
			return user.getAccountDetails().getEmail();
		case "user.accountDetails.homeAddressStreet":
			return user.getAccountDetails().getHomeAddressStreet();
		case "user.accountDetails.homeAddressNumber":
			return user.getAccountDetails().getHomeAddressNumber();
		case "user.accountDetails.personalTelephone":
			return user.getAccountDetails().getPersonalTelephone();
		case "user.accountDetails.country":
			return user.getAccountDetails().getCountry();
		case "user.accountDetails.city":
			return user.getAccountDetails().getCity();
		case "user.studentDetails.currentYearOfStudy":
			return user.getStudentDetails().getCurrentYearOfStudy().toString();
		case "user.studentDetails.classroom.className":
			return user.getStudentDetails().getClassroom().getId() + "";
		case "user.accountDetails.firstName":
			return user.getAccountDetails().getFirstName();
		case "user.accountDetails.lastName":
			return user.getAccountDetails().getLastName();
		case "user.studentDetails.parentsDetails.fatherFirstName":
			return user.getStudentDetails().getParentsDetails().getFatherFirstName();
		case "user.studentDetails.parentsDetails.fatherLastName":
			return user.getStudentDetails().getParentsDetails().getFatherLastName();
		case "user.studentDetails.parentsDetails.fatherTelephone":
			return user.getStudentDetails().getParentsDetails().getFatherTelephone();
		case "user.studentDetails.parentsDetails.motherFirstName":
			return user.getStudentDetails().getParentsDetails().getMotherFirstName();
		case "user.studentDetails.parentsDetails.motherLastName":
			return user.getStudentDetails().getParentsDetails().getMotherLastName();
		case "user.studentDetails.parentsDetails.motherTelephone":
			return user.getStudentDetails().getParentsDetails().getMotherTelephone();
		case "user.teacherDetails.subject":
			return user.getTeacherDetails().getSubject();
		case "user.teacherDetails.position":
			return user.getTeacherDetails().getPosition();
		case "user.teacherDetails.graduationYear":
			return user.getTeacherDetails().getGraduationYear().toString();
		default:
			System.out.println("Error: Retrieved field does not match.");
			return null;
		}
	}
	
	@Override
	public void setUserField(String username, String field, String value) {
		User user = getUser(username);
		switch(field) {
		case "user.username":
			user.setUsername(value);
			break;
		case "user.accountDetails.dateOfBirth":
			user.getAccountDetails().setDateOfBirth(value);
			break;
		case "user.accountDetails.email":
			user.getAccountDetails().setEmail(value);
			break;
		case "user.accountDetails.homeAddressStreet":
			user.getAccountDetails().setHomeAddressStreet(value);
			break;
		case "user.accountDetails.homeAddressNumber":
			user.getAccountDetails().setHomeAddressNumber(value);
			break;
		case "user.accountDetails.personalTelephone":
			user.getAccountDetails().setPersonalTelephone(value);
			break;
		case "user.accountDetails.country":
			user.getAccountDetails().setCountry(value);
			break;
		case "user.accountDetails.city":
			user.getAccountDetails().setCity(value);
			break;
		case "user.studentDetails.currentYearOfStudy":
			user.getStudentDetails().setCurrentYearOfStudy(Integer.parseInt(value));
			break;
		case "user.studentDetails.classroom.className":
			user.getStudentDetails().getClassroom();
		default:
			System.out.println("Error: Retrieved field does not match.");
		}
	}



}
