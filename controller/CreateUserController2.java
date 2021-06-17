//package com.school.platform.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.school.platform.entity.AccountDetails;
//import com.school.platform.entity.Authority;
//import com.school.platform.entity.ParentsDetails;
//import com.school.platform.entity.StudentDetails;
//import com.school.platform.entity.User;
//import com.school.platform.service.AccountDetailsService;
//import com.school.platform.service.AuthorityService;
//import com.school.platform.service.ClassroomService;
//import com.school.platform.service.ParentsDetailsService;
//import com.school.platform.service.StudentDetailsService;
//import com.school.platform.service.UserService;
//
//@Controller
//@RequestMapping("/create-user")
//public class CreateUserController2 {
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private AuthorityService authorityService;
//	@Autowired
//	private AccountDetailsService accountDetailsService;
//	@Autowired
//	private ClassroomService classroomService;
//	@Autowired
//	private StudentDetailsService studentDetailsService;
//	@Autowired
//	private ParentsDetailsService parentsDetailsService;
//	
//	@GetMapping("")
//	public String createUser(Model theModel) {
//		theModel.addAttribute("theUser", new User());
//		theModel.addAttribute("entity", "User");
//		return "create-user";
//	}
//	@PostMapping("/save-user")
//	public String saveUser(@ModelAttribute("theUser") User theUser, Model theModel) {
//		Authority authority = new Authority("ROLE_STUDENT");
//		theUser.addAuthority(authority);
//		userService.saveUser(theUser);
//		authorityService.saveAuthority(authority);
//		
//		theModel.addAttribute("theUser", theUser);
//		theModel.addAttribute("theAccountDetails", new AccountDetails());
//		theModel.addAttribute("entity", "AccountDetails");
//		return "create-user";
//	}
//	@PostMapping("/save-account-details")
//	public String saveAccountDetails(@ModelAttribute("theAccountDetails") AccountDetails theAccountDetails, @RequestParam("userUsername") String username, Model theModel) {
//		User theUser = userService.getUser(username);
//		theUser.setAccountDetails(theAccountDetails);
//		accountDetailsService.saveAccountDetails(theAccountDetails);
//		userService.saveUser(theUser);
//		
//		theModel.addAttribute("theUser", theUser);
//		theModel.addAttribute("classroomsList", classroomService.getSchoolClassrooms(theUser.getAccountDetails().getCity()));
//		theModel.addAttribute("entity", "StudentDetails");
//		return "create-user";
//	}
//	@PostMapping("/save-student-details")
//	public String saveStudentDetails(@RequestParam("currentYearOfStudy") int currentYearOfStudy, @RequestParam("classroomId") int classroomId, @RequestParam("userUsername") String username, Model theModel) {
//		StudentDetails theStudentDetails = new StudentDetails();
//		theStudentDetails.setCurrentYearOfStudy(currentYearOfStudy);
//		theStudentDetails.setClassroom(classroomService.getClassroom(classroomId));
//		User theUser = userService.getUser(username);
//		theUser.setStudentDetails(theStudentDetails);
//		studentDetailsService.saveStudentDetails(theStudentDetails);
//		userService.saveUser(theUser);
//		
//		theModel.addAttribute("theUser", theUser);
//		theModel.addAttribute("theParentsDetails", new ParentsDetails());
//		theModel.addAttribute("entity", "ParentsDetails");
//		return "create-user";
//	}
//	@PostMapping("/save-parents-details")
//	public String saveParentsDetails(@ModelAttribute("theParentsDetails") ParentsDetails theParentsDetails, @RequestParam("userUsername") String username, Model theModel) {
//		User theUser = userService.getUser(username);
//		theUser.getStudentDetails().setParentsDetails(theParentsDetails);
//		parentsDetailsService.saveParentsDetails(theParentsDetails);
//		userService.saveUser(theUser);
//		
//		theModel.addAttribute("theUser", theUser);
//		theModel.addAttribute("confirmationTitle", "A new user has been successfully created");
//		return "confirmation-page";
//	}
//	@GetMapping("/create-user/edit")
//	public String editAccountInformation(@RequestParam ("username") String username, @RequestParam ("field-to-edit") String field, Model theModel) {
//		theModel.addAttribute("user", userService.getUser(username));
//		theModel.addAttribute("field", field);
//		theModel.addAttribute("fieldValue", userService.getFieldValue(username, field));
//		theModel.addAttribute("trigger", 1);
//		return "account-info-page";
//	}
//	@PostMapping("/update-account-information")
//	public String setAccountInformation(@RequestParam ("username") String username, @RequestParam ("field-to-edit") String field, @RequestParam ("input-box-value") String value) {
//		userService.setUserField(username, field, value);
//		return "redirect:account-information";
//	}
//	
//}
