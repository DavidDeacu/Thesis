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
import com.school.platform.entity.Course;
import com.school.platform.entity.ParentsDetails;
import com.school.platform.entity.StudentDetails;
import com.school.platform.entity.Teacher;
import com.school.platform.entity.User;
import com.school.platform.pojo.InputError;
import com.school.platform.service.AccountDetailsService;
import com.school.platform.service.AuthorityService;
import com.school.platform.service.ClassroomService;
import com.school.platform.service.CourseService;
import com.school.platform.service.TeacherDetailsService;
import com.school.platform.service.UserService;
import com.school.platform.service.ValidationService;

@Controller
@RequestMapping("/create-course")
public class CreateCourseController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private AccountDetailsService accountDetailsService;
	@Autowired
	private ClassroomService classroomService;
	@Autowired
	private ValidationService validationService;
	@Autowired
	private TeacherDetailsService teacherDetailsService;
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/choose-teacher")
	public String chooseTeacher(HttpServletRequest request, Model theModel) {
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		theModel.addAttribute("teachersList", teacherDetailsService.getTeachersFromSchool(userService.getUser(username).getTeacherDetails().getSchool()));
		
		return "choose-teacher";
	}
	@PostMapping("/validate-teacher")
	public String validateTeacher(@RequestParam(name="teacherId", required=false) String teacherId, HttpServletRequest request, Model theModel) {
		List<InputError> inputValidationResults = validationService.validate(teacherId);
		theModel.addAttribute("inputValidationResults", inputValidationResults);
		
		if(inputValidationResults.size() == 0) {
			Teacher theTeacher = teacherDetailsService.getTeacher(Integer.parseInt(teacherId));
			theModel.addAttribute("theTeacher", theTeacher);
			theModel.addAttribute("classroomsList", classroomService.getSchoolClassrooms(theTeacher.getUser()));
			return "create-course";
		} else {
			for(InputError inputError : inputValidationResults) {
				theModel.addAttribute(inputError.getInputFieldName(), inputError);
			}
			Principal principal = request.getUserPrincipal();
			String username = principal.getName();
			theModel.addAttribute("teachersList", teacherDetailsService.getTeachersFromSchool(userService.getUser(username).getTeacherDetails().getSchool()));
			
			return "choose-teacher";
		}
	}

	@GetMapping("")
	public String createCourse(@RequestParam("username") String username, HttpServletRequest request, Model theModel) {
		
		
		theModel.addAttribute("classroomsList", classroomService.getSchoolClassrooms(userService.getUser(username)));
		return "create-course";
	}
	
	@PostMapping("/save-course")
	public String saveCourse(HttpServletRequest request,
			@RequestParam(name="courseDescription", required=false) String courseDescription,
			@RequestParam(name="classroomId", required=false) String classroomId,
			@RequestParam(name="teacherId") String teacherId, Model theModel) {
		
		
		
		List<InputError> inputValidationResults = validationService.validate(courseDescription, classroomId);
		theModel.addAttribute("inputValidationResults", inputValidationResults);
		
		Teacher theTeacher = teacherDetailsService.getTeacher(Integer.parseInt(teacherId));
		if(inputValidationResults.size() == 0) {
			Classroom theClassroom = classroomService.getClassroom(Integer.parseInt(classroomId));
			Course theCourse = new Course(theTeacher.getSubject(), courseDescription, theTeacher.getSchool(), theTeacher, theClassroom);
			theCourse.setStudentsDetails(theClassroom.getStudentsDetails());
			theTeacher.addCourse(theCourse);
			
			teacherDetailsService.saveTeacherDetails(theTeacher);
			courseService.saveCourse(theCourse);
			
			theModel.addAttribute("pageReview", "create-course");
			theModel.addAttribute("theCourse", theCourse);
			return "confirmation-page";
		} else {
			for(InputError inputError : inputValidationResults) {
				theModel.addAttribute(inputError.getInputFieldName(), inputError);
			}
			validationService.loadCourseModelAttribute(theModel, courseDescription);
			
			theModel.addAttribute("theTeacher", theTeacher);
			theModel.addAttribute("classroomsList", classroomService.getSchoolClassrooms(theTeacher.getUser()));
			
			return "create-course";
		}
	}
}
