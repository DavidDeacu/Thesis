package com.school.platform.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.school.platform.pojo.InputError;
import com.school.platform.useful.Matcher;

@Service
public class ValidationServiceImpl implements ValidationService {
	@Autowired
	@Qualifier("gregorianDateMatcher")
	private Matcher dateMatcher;
	@Autowired
	@Qualifier("integerMatcher")
	private Matcher integerMatcher;
	@Autowired
	@Qualifier("firstNameMatcher")
	private Matcher firstNameMatcher;
	@Autowired
	@Qualifier("letterMatcher")
	private Matcher letterMatcher;
	@Autowired
	@Qualifier("emailMatcher")
	private Matcher emailMatcher;
	@Autowired
	@Qualifier("letterIntegerMatcher")
	private Matcher letterIntegerMatcher;
	@Autowired
	@Qualifier("phoneNumberMatcher")
	private Matcher phoneNumberMatcher;
	@Autowired
	@Qualifier("schoolYearMatcher")
	private Matcher schoolYearMatcher;

	@Override
	public List<InputError> validate(String firstName, String lastName, String dateOfBirth, String cnp, String email,
			String homeAddressStreet, String homeAddressNumber, String personalTelephone, String country, String city,
			String classroomId, String fatherFirstName, String fatherLastName, String fatherTelephone,
			String motherFirstName, String motherLastName, String motherTelephone) {
		
		List<InputError> invalidInputs = new ArrayList<>();
		if(!validateFirstName(firstName))
			invalidInputs.add(new InputError("firstName", "First name must be 'firstName-secondName'"));
		if(!validateLastName(lastName))
			invalidInputs.add(new InputError("lastName", "Last name should contain just letters"));
		if(!validateDateOfBirth(dateOfBirth))
			invalidInputs.add(new InputError("dateOfBirth", "The date of birth must be a valid date in the 'yyyy-mm-dd' format"));
		if(!validateCnp(cnp))
			invalidInputs.add(new InputError("cnp", "The CNP should contain 13 digits"));
		if(!validateEmail(email))
			invalidInputs.add(new InputError("email", "The email must be in the 'emailAddress@email.domain' format"));
		if(!validateHomeAddressStreet(homeAddressStreet))
			invalidInputs.add(new InputError("homeAddressStreet", "Cannot be null"));
		if(!validateHomeAddressNumber(homeAddressNumber))
			invalidInputs.add(new InputError("homeAddressNumber", "Should contain digits and/or letters"));
		if(!validatePersonalTelephone(personalTelephone))
			invalidInputs.add(new InputError("personalTelephone", "The phone number can contain just digits and the '+' character"));
		if(!validateCountry(country))
			invalidInputs.add(new InputError("country", "Should contain only letters"));
		if(!validateCity(city))
			invalidInputs.add(new InputError("city", "Should contain only letters"));
		if(!validateClassroomId(classroomId))
			invalidInputs.add(new InputError("classroomId", "Cannot be null"));
		if(!validateFatherFirstName(fatherFirstName))
			invalidInputs.add(new InputError("fatherFirstName", "Should contain only letters without space"));
		if(!validateFatherLastName(fatherLastName))
			invalidInputs.add(new InputError("fatherLastName", "Should contain only letters"));
		if(!validateFatherTelephone(fatherTelephone))
			invalidInputs.add(new InputError("fatherTelephone", "The phone number can contain just digits and the '+' character"));
		if(!validateMotherFirstName(motherFirstName))
			invalidInputs.add(new InputError("motherFirstName", "Should contain only letters without space"));
		if(!validateMotherLastName(motherLastName))
			invalidInputs.add(new InputError("motherLastName", "Should contain only letters"));
		if(!validateMotherTelephone(motherTelephone))
			invalidInputs.add(new InputError("motherTelephone", "The phone number can contain just digits and the '+' character"));
		return invalidInputs;
	}
	
	@Override
	public List<InputError> validate(String firstName, String lastName, String dateOfBirth, String cnp, String email,
			String homeAddressStreet, String homeAddressNumber, String personalTelephone, String country, String city,
			String subject, String position, String graduationYear) {
		List<InputError> invalidInputs = new ArrayList<>();
		if(!validateFirstName(firstName))
			invalidInputs.add(new InputError("firstName", "First name must be 'firstName-secondName'"));
		if(!validateLastName(lastName))
			invalidInputs.add(new InputError("lastName", "Last name should contain just letters"));
		if(!validateDateOfBirth(dateOfBirth))
			invalidInputs.add(new InputError("dateOfBirth", "The date of birth must be a valid date in the 'yyyy-mm-dd' format"));
		if(!validateCnp(cnp))
			invalidInputs.add(new InputError("cnp", "The CNP should contain 13 digits"));
		if(!validateEmail(email))
			invalidInputs.add(new InputError("email", "The email must be in the 'emailAddress@email.domain' format"));
		if(!validateHomeAddressStreet(homeAddressStreet))
			invalidInputs.add(new InputError("homeAddressStreet", "Cannot be null"));
		if(!validateHomeAddressNumber(homeAddressNumber))
			invalidInputs.add(new InputError("homeAddressNumber", "Should contain digits and/or letters"));
		if(!validatePersonalTelephone(personalTelephone))
			invalidInputs.add(new InputError("personalTelephone", "The phone number can contain just digits and the '+' character"));
		if(!validateCountry(country))
			invalidInputs.add(new InputError("country", "Should contain only letters"));
		if(!validateCity(city))
			invalidInputs.add(new InputError("city", "Should contain only letters"));
		if(!validateSubject(subject))
			invalidInputs.add(new InputError("subject", "Should be one from the list"));
		if(!validatePosition(position))
			invalidInputs.add(new InputError("position", "Should be one from the list"));
		if(!validateGraduationYear(graduationYear))
			invalidInputs.add(new InputError("graduationYear", "The year should be 4 digits yyyy"));
	
		return invalidInputs;
	}
	
	@Override
	public List<InputError> validate(String teacherId) {
		List<InputError> invalidInputs = new ArrayList<>();
		if(!validateTeacherId(teacherId))
			invalidInputs.add(new InputError("teacherId", "Cannot be null"));
		
		return invalidInputs;
	}
	
	@Override
	public List<InputError> validate(String courseDescription, String classroomId) {
		List<InputError> invalidInputs = new ArrayList<>();
		if(!validateCourseDescription(courseDescription))
			invalidInputs.add(new InputError("courseDescription", "Cannot be null"));
		if(!validateClassroomId(classroomId))
			invalidInputs.add(new InputError("classroomId", "Cannot be null"));
		return invalidInputs;
	}
	
	@Override
	public List<InputError> validate(String mark, String date, String description) {
		List<InputError> invalidInputs = new ArrayList<>();
		if(!validateMark(mark))
			invalidInputs.add(new InputError("mark", "Cannot be null"));
		if(!validateDate(date))
			invalidInputs.add(new InputError("date", "Cannot be null"));
		if(!validateDescription(description))
			invalidInputs.add(new InputError("description", "Cannot be null"));
		return invalidInputs;
	}
	
	@Override
	public List<InputError> validate(String className, String classProfile, String studyYear,
			String startDate, String finishDate, String teacherId) {
		List<InputError> invalidInputs = new ArrayList<>();
		if(!validateClassName(className))
			invalidInputs.add(new InputError("className", "Cannot be null"));
		if(!validateClassProfile(classProfile))
			invalidInputs.add(new InputError("classProfile", "Cannot be null"));
		if(!validateStudyYear(studyYear))
			invalidInputs.add(new InputError("studyYear", "Must contain digits between 0-12"));
		if(!validateStartDate(startDate)) {
			invalidInputs.add(new InputError("startDate", "The date must be a valid date in the 'yyyy-mm-dd' format"));
			if(!validateFinishDate(finishDate)) {
				System.out.println("validateFinishDate=" + validateFinishDate(finishDate));
				invalidInputs.add(new InputError("finishDate", "The date must be a valid date in the 'yyyy-mm-dd' format"));
			}
		} else {
			if(!validateFinishDate(finishDate)) {
				invalidInputs.add(new InputError("finishDate", "The date must be a valid date in the 'yyyy-mm-dd' format"));
			} else {
				if(!validateStartFinishDateOccurrence(startDate, finishDate)) {
					invalidInputs.add(new InputError("startDate", "The start date must be before the finish date"));
					invalidInputs.add(new InputError("finishDate", "The finish date must be after the start date"));
				}
			}
		}
		if(!validateTeacherId(teacherId))
			invalidInputs.add(new InputError("headTeacherAccountDetails", "Cannot be null"));
		
		return invalidInputs;
	}
	
	private boolean validateFirstName(String firstName) {
		if(!firstName.isEmpty() && !firstName.contains(" ") && firstNameMatcher.matches(firstName))
			return true;
		return false;
	}
	private boolean validateLastName(String lastName) {
		if(!lastName.isEmpty() && !lastName.contains(" ") && letterMatcher.matches(lastName))
			return true;
		return false;
	}
	private boolean validateDateOfBirth(String dateOfBirth) {
		if(!dateOfBirth.isEmpty() && dateMatcher.matches(dateOfBirth))
			return true;
		return false;
	}
	private boolean validateCnp(String cnp) {
		if(!cnp.isEmpty() && integerMatcher.matches(cnp) && cnp.length() == 13)
			return true;
		return false;
	}
	private boolean validateEmail(String email) {
		if(!email.isEmpty() && emailMatcher.matches(email))
			return true;
		return false;
	}
	private boolean validateHomeAddressStreet(String homeAddressStreet) {
		if(!homeAddressStreet.isEmpty())
			return true;
		return false;
	}
	private boolean validateHomeAddressNumber(String homeAddressNumber) {
		if(!homeAddressNumber.isEmpty() && letterIntegerMatcher.matches(homeAddressNumber))
			return true;
		return false;
	}
	private boolean validatePersonalTelephone(String personalTelephone) {
		if(!personalTelephone.isEmpty() && phoneNumberMatcher.matches(personalTelephone))
			return true;
		return false;
	}
	private boolean validateCountry(String country) {
		if(!country.isEmpty() && letterMatcher.matches(country))
			return true;
		return false;
	}
	private boolean validateCity(String city) {
		if(!city.isEmpty() && letterMatcher.matches(city))
			return true;
		return false;
	}
	private boolean validateClassroomId(String classroomId) {
		if(classroomId.equals(""))
			return false;
		return true;
	}
	private boolean validateFatherFirstName(String fatherFirstName) {
		if(!fatherFirstName.isEmpty() && !fatherFirstName.contains(" ") && letterMatcher.matches(fatherFirstName))
			return true;
		return false;
	}
	private boolean validateFatherLastName(String fatherLastName) {
		if(!fatherLastName.isEmpty() && !fatherLastName.contains(" ") && letterMatcher.matches(fatherLastName))
			return true;
		return false;
	}
	private boolean validateFatherTelephone(String fatherTelephone) {
		if(!fatherTelephone.isEmpty() && phoneNumberMatcher.matches(fatherTelephone))
			return true;
		return false;
	}
	private boolean validateMotherFirstName(String motherFirstName) {
		if(!motherFirstName.isEmpty() && !motherFirstName.contains(" ") && letterMatcher.matches(motherFirstName))
			return true;
		return false;
	}
	private boolean validateMotherLastName(String motherLastName) {
		if(!motherLastName.isEmpty() && !motherLastName.contains(" ") && letterMatcher.matches(motherLastName))
			return true;
		return false;
	}
	private boolean validateMotherTelephone(String motherTelephone) {
		if(!motherTelephone.isEmpty() && phoneNumberMatcher.matches(motherTelephone))
			return true;
		return false;
	}
	private boolean validateSubject(String subject) {
		if(subject.equals(""))
			return false;
		return true;
	}
	private boolean validatePosition(String position) {
		if(position.equals(""))
			return false;
		return true;
	}
	private boolean validateGraduationYear(String graduationYear) {
		if(!graduationYear.isEmpty() && integerMatcher.matches(graduationYear))
			return true;
		return false;
	}
	private boolean validateTeacherId(String teacherId) {
		if(teacherId.equals(""))
			return false;
		return true;
	}
	private boolean validateCourseDescription(String courseDescription) {
		if(!courseDescription.isEmpty())
			return true;
		return false;
	}
	private boolean validateClassName(String className) {
		if(!className.isEmpty())
			return true;
		return false;
	}
	private boolean validateClassProfile(String classProfile) {
		if(!classProfile.isEmpty())
			return true;
		return false;
	}
	private boolean validateStudyYear(String studyYear) {
		if(!studyYear.isEmpty() && schoolYearMatcher.matches(studyYear))
			return true;
		return false;
	}
	private boolean validateStartDate(String startDate) {
		if(!startDate.isEmpty() && dateMatcher.matches(startDate))
			return true;
		return false;
	}
	private boolean validateFinishDate(String finishDate) {
		if(!finishDate.isEmpty() && dateMatcher.matches(finishDate))
			return true;
		return false;
	}
	private boolean validateStartFinishDateOccurrence(String startDate, String finishDate) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean before = false;
		try {
			before = (sdf.parse(startDate).before(sdf.parse(finishDate)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return before;
	}
	private boolean validateDate(String date) {
		if(!date.isEmpty() && dateMatcher.matches(date))
			return true;
		return false;
	}
	private boolean validateMark(String mark) {
		if(!mark.isEmpty())
			return true;
		return false;
	}
	private boolean validateDescription(String description) {
		if(!description.isEmpty())
			return true;
		return false;
	}
	
	@Override
	public void loadStudentModelAttribute(Model theModel, String firstName, String lastName, String dateOfBirth, String cnp,
			String email, String homeAddressStreet, String homeAddressNumber, String personalTelephone, String country,
			String city, String fatherFirstName, String fatherLastName, String fatherTelephone,
			String motherFirstName, String motherLastName, String motherTelephone) {
		theModel.addAttribute("firstNameValue", firstName);
		theModel.addAttribute("lastNameValue", lastName);
		theModel.addAttribute("dateOfBirthValue", dateOfBirth);
		theModel.addAttribute("cnpValue", cnp);
		theModel.addAttribute("emailValue", email);
		theModel.addAttribute("homeAddressStreetValue", homeAddressStreet);
		theModel.addAttribute("homeAddressNumberValue", homeAddressNumber);
		theModel.addAttribute("personalTelephoneValue", personalTelephone);
		theModel.addAttribute("countryValue", country);
		theModel.addAttribute("cityValue", city);
		theModel.addAttribute("fatherFirstNameValue", fatherFirstName);
		theModel.addAttribute("fatherLastNameValue", fatherLastName);
		theModel.addAttribute("fatherTelephoneValue", fatherTelephone);
		theModel.addAttribute("motherFirstNameValue", motherFirstName);
		theModel.addAttribute("motherLastNameValue", motherLastName);
		theModel.addAttribute("motherTelephoneValue", motherTelephone);
	}

	@Override
	public void loadTeacherModelAttribute(Model theModel, String firstName, String lastName, String dateOfBirth,
			String cnp, String email, String homeAddressStreet, String homeAddressNumber, String personalTelephone,
			String country, String city, String subject, String position, String graduationYear) {
		theModel.addAttribute("firstNameValue", firstName);
		theModel.addAttribute("lastNameValue", lastName);
		theModel.addAttribute("dateOfBirthValue", dateOfBirth);
		theModel.addAttribute("cnpValue", cnp);
		theModel.addAttribute("emailValue", email);
		theModel.addAttribute("homeAddressStreetValue", homeAddressStreet);
		theModel.addAttribute("homeAddressNumberValue", homeAddressNumber);
		theModel.addAttribute("personalTelephoneValue", personalTelephone);
		theModel.addAttribute("countryValue", country);
		theModel.addAttribute("cityValue", city);
		theModel.addAttribute("subjectValue", subject);
		theModel.addAttribute("positionValue", position);
		theModel.addAttribute("graduationYearValue", graduationYear);
	}

	@Override
	public void loadCourseModelAttribute(Model theModel, String courseDescription) {
		theModel.addAttribute("courseDescriptionValue", courseDescription);
	}
	
	@Override
	public void loadGradeModelAttribute(Model theModel, String description) {
		theModel.addAttribute("descriptionValue", description);
	}

	@Override
	public void loadClassroomModelAttribute(Model theModel, String className, String classProfile, String startDate,
			String finishDate) {
		theModel.addAttribute("classNameValue", className);
		theModel.addAttribute("classProfileValue", classProfile);
		theModel.addAttribute("startDateValue", startDate);
		theModel.addAttribute("finishDateValue", finishDate);
	}



	
}
