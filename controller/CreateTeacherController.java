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
import com.school.platform.entity.Teacher;
import com.school.platform.entity.User;
import com.school.platform.pojo.InputError;
import com.school.platform.service.AccountDetailsService;
import com.school.platform.service.AuthorityService;
import com.school.platform.service.TeacherDetailsService;
import com.school.platform.service.UserService;
import com.school.platform.service.ValidationService;

@Controller
@RequestMapping("/create-teacher")
public class CreateTeacherController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private AccountDetailsService accountDetailsService;
	@Autowired
	private ValidationService validationService;
	@Autowired
	private TeacherDetailsService teacherDetailsService;
	
	@GetMapping("")
	public String createTeacher(HttpServletRequest request, Model theModel) {
		theModel.addAttribute("subjectsList", teacherDetailsService.getSubjects());
		theModel.addAttribute("positionsList", teacherDetailsService.getPositions());
		return "create-teacher";
	}
	
	@PostMapping("/save-teacher")
	public String saveTeacher(HttpServletRequest request,
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
			@RequestParam(name="subject", required=false) String subject,
			@RequestParam(name="position", required=false) String position,
			@RequestParam(name="graduationYear", required=false) String graduationYear, Model theModel) {
		
		
		
		List<InputError> inputValidationResults = validationService.validate(firstName, lastName, dateOfBirth,
				cnp, email, homeAddressStreet, homeAddressNumber, personalTelephone, country, city, 
				subject, position, graduationYear);
		theModel.addAttribute("inputValidationResults", inputValidationResults);
		
		if(inputValidationResults.size() == 0) {
			BCryptPasswordEncoder enc = new  BCryptPasswordEncoder();
			//USER
			User theUser = new User();
			theUser.setUsername(firstName + "." + lastName);
			theUser.setPassword("{bcrypt}" + enc.encode(cnp.substring(7)));
			//AUTHORITY
			Authority theAuthority = new Authority("ROLE_TEACHER");
			theUser.addAuthority(theAuthority);
			//ACCOUNT DETAILS
			AccountDetails theAccountDetails = new AccountDetails(firstName, lastName, dateOfBirth, cnp, email, 
					homeAddressStreet, homeAddressNumber, personalTelephone, country, city);
			theUser.setAccountDetails(theAccountDetails);
			//TEACHER DETAILS
			Principal principal = request.getUserPrincipal();
			String username = principal.getName();
			User adminUser = userService.getUser(username);
			Teacher theTeacher = new Teacher(subject, position, Integer.parseInt(graduationYear), adminUser.getTeacherDetails().getSchool(), theUser);
			theUser.setTeacherDetails(theTeacher);
			
			theModel.addAttribute("theUser", theUser);
			userService.saveUser(theUser);
			authorityService.saveAuthority(theAuthority);
			accountDetailsService.saveAccountDetails(theAccountDetails);
			teacherDetailsService.saveTeacherDetails(theTeacher);
			theModel.addAttribute("pageReview", "create-teacher");

			return "confirmation-page";
		} else {
			for(InputError inputError : inputValidationResults) {
				theModel.addAttribute(inputError.getInputFieldName(), inputError);
			}
			validationService.loadTeacherModelAttribute(theModel, firstName, lastName, dateOfBirth, cnp, email, homeAddressStreet, homeAddressNumber, personalTelephone, country, city, subject, position, graduationYear);
			theModel.addAttribute("subjectsList", teacherDetailsService.getSubjects());
			theModel.addAttribute("positionsList", teacherDetailsService.getPositions());
			
			return "create-teacher";
		}
	}

}
