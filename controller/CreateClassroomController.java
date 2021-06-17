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
import com.school.platform.entity.Teacher;
import com.school.platform.entity.User;
import com.school.platform.pojo.InputError;
import com.school.platform.service.AccountDetailsService;
import com.school.platform.service.AuthorityService;
import com.school.platform.service.ClassroomService;
import com.school.platform.service.ParentsDetailsService;
import com.school.platform.service.StudentDetailsService;
import com.school.platform.service.TeacherDetailsService;
import com.school.platform.service.UserService;
import com.school.platform.service.ValidationService;

@Controller
@RequestMapping("/create-classroom")
public class CreateClassroomController {
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
	private TeacherDetailsService teacherDetailsService;
	@Autowired
	private ValidationService validationService;
	
	@GetMapping("")
	public String createClassroom(HttpServletRequest request, Model theModel) {
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		theModel.addAttribute("teachersList", teacherDetailsService.getTeachersFromSchool(userService.getUser(username).getTeacherDetails().getSchool()));
		
		return "create-classroom";
	}
	
	@PostMapping("/save-classroom")
	public String saveTeacher(HttpServletRequest request,
			@RequestParam(name="className", required=false) String className,
			@RequestParam(name="classProfile", required=false) String classProfile,
			@RequestParam(name="studyYear", required=false) String studyYear,
			@RequestParam(name="startDate", required=false) String startDate,
			@RequestParam(name="finishDate", required=false) String finishDate,
			@RequestParam(name="teacherId", required=false) String teacherId, Model theModel) {
		
		List<InputError> inputValidationResults = validationService.validate(className, classProfile, studyYear,
				startDate, finishDate, teacherId);
		theModel.addAttribute("inputValidationResults", inputValidationResults);
		
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		if(inputValidationResults.size() == 0) {
			Classroom theClassroom = new Classroom(className, classProfile, Integer.parseInt(studyYear), startDate, finishDate, userService.getUser(username).getTeacherDetails().getSchool(), teacherDetailsService.getTeacher(Integer.parseInt(teacherId)).getUser().getAccountDetails());
			classroomService.saveClassroom(theClassroom);
			
			theModel.addAttribute("pageReview", "create-classroom");
			theModel.addAttribute("theClassroom", theClassroom);
			return "confirmation-page";
		} else {
			for(InputError inputError : inputValidationResults) {
				theModel.addAttribute(inputError.getInputFieldName(), inputError);
			}
			validationService.loadClassroomModelAttribute(theModel, className, classProfile, startDate, finishDate);
			theModel.addAttribute("teachersList", teacherDetailsService.getTeachersFromSchool(userService.getUser(username).getTeacherDetails().getSchool()));
			
			return "create-classroom";
		}
	}
}
