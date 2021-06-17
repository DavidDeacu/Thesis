package com.school.platform.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.platform.entity.AccountDetails;
import com.school.platform.entity.Authority;
import com.school.platform.entity.Classroom;
import com.school.platform.entity.ParentsDetails;
import com.school.platform.entity.StudentDetails;
import com.school.platform.entity.User;
import com.school.platform.pojo.InputError;
import com.school.platform.service.AccountDetailsService;
import com.school.platform.service.AuthorityService;
import com.school.platform.service.ClassroomService;
import com.school.platform.service.ParentsDetailsService;
import com.school.platform.service.StudentDetailsService;
import com.school.platform.service.UserService;
import com.school.platform.service.ValidationService;

@Controller
@RequestMapping("/create-user")
public class CreateUserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private AccountDetailsService accountDetailsService;
	@Autowired
	private ClassroomService classroomService;
	@Autowired
	private StudentDetailsService studentDetailsService;
	@Autowired
	private ParentsDetailsService parentsDetailsService;
	@Autowired
	private ValidationService validationService;
	
	@GetMapping("")
	public String createUser(HttpServletRequest request, Model theModel) {
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		theModel.addAttribute("classroomsList", classroomService.getSchoolClassrooms(userService.getUser(username)));
		return "create-user";
	}
	@PostMapping("/save-user")
	public String saveUser(HttpServletRequest request,
			@RequestParam(name="firstName", required=false) String firstName,
			@RequestParam(name="lastName", required=false) String lastName,
			@RequestParam(name="dateOfBirth", required=false) String dateOfBirth,
			@RequestParam(name="cnp", required=false) String cnp,
			@RequestParam(name="email", required=false) String email,
			@RequestParam(name="homeAddressStreet", required=false) String homeAddressStreet,
			@RequestParam(name="homeAddressNumber", required=false) String homeAddressNumber,
			@RequestParam(name="personalTelephone", required=false) String personalTelephone,
			@RequestParam(name="country", required=false) String country,
			@RequestParam(name="city", required=false) String city,
			@RequestParam(name="classroomId", required=false) String classroomId,
			@RequestParam(name="fatherFirstName", required=false) String fatherFirstName,
			@RequestParam(name="fatherLastName", required=false) String fatherLastName,
			@RequestParam(name="fatherTelephone", required=false) String fatherTelephone,
			@RequestParam(name="motherFirstName", required=false) String motherFirstName,
			@RequestParam(name="motherLastName", required=false) String motherLastName,
			@RequestParam(name="motherTelephone", required=false) String motherTelephone, Model theModel) {
		
		
		
		List<InputError> inputValidationResults = validationService.validate(firstName, lastName, dateOfBirth,
				cnp, email, homeAddressStreet, homeAddressNumber, personalTelephone, country, city, 
				classroomId, fatherFirstName, fatherLastName, fatherTelephone, motherFirstName,
				motherLastName, motherTelephone);
		theModel.addAttribute("inputValidationResults", inputValidationResults);
		
		if(inputValidationResults.size() == 0) {
			BCryptPasswordEncoder enc = new  BCryptPasswordEncoder();
			//USER
			User theUser = new User();
			theUser.setUsername(firstName + "." + lastName);
			theUser.setPassword("{bcrypt}" + enc.encode(cnp.substring(7)));
			//AUTHORITY
			Authority theAuthority = new Authority("ROLE_STUDENT");
			theUser.addAuthority(theAuthority);
			//ACCOUNT DETAILS
			AccountDetails theAccountDetails = new AccountDetails(firstName, lastName, dateOfBirth, cnp, email, 
					homeAddressStreet, homeAddressNumber, personalTelephone, country, city);
			theUser.setAccountDetails(theAccountDetails);
			//STUDENT DETAILS
			StudentDetails theStudentDetails = new StudentDetails();
			Classroom theClassroom = classroomService.getClassroom(Integer.parseInt(classroomId));
			theStudentDetails.setClassroom(theClassroom);
			theStudentDetails.setCurrentYearOfStudy(theClassroom.getStudyYear());
			theStudentDetails.setCourses(theClassroom.getCourses());
			theUser.setStudentDetails(theStudentDetails);
			//PARENTS DETAILS
			ParentsDetails theParentsDetails = new ParentsDetails(fatherFirstName, fatherLastName, fatherTelephone,
					motherFirstName, motherLastName, motherTelephone);
			theStudentDetails.setParentsDetails(theParentsDetails);
			
			theModel.addAttribute("theUser", theUser);
			userService.saveUser(theUser);
			authorityService.saveAuthority(theAuthority);
			accountDetailsService.saveAccountDetails(theAccountDetails);
			parentsDetailsService.saveParentsDetails(theParentsDetails);
			studentDetailsService.saveStudentDetails(theStudentDetails);

			theModel.addAttribute("pageReview", "create-user");
			return "confirmation-page";
		} else {
			for(InputError inputError : inputValidationResults) {
				theModel.addAttribute(inputError.getInputFieldName(), inputError);
			}
			validationService.loadStudentModelAttribute(theModel, firstName, lastName, dateOfBirth, cnp, email, homeAddressStreet, homeAddressNumber, personalTelephone, country, city, fatherFirstName, fatherLastName, fatherTelephone, motherFirstName, motherLastName, motherTelephone);
			
			Principal principal = request.getUserPrincipal();
			String username = principal.getName();
			theModel.addAttribute("classroomsList", classroomService.getSchoolClassrooms(userService.getUser(username)));
			
			return "create-user";
		}
		
		
		
		
	}
}
