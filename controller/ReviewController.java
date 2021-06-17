package com.school.platform.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.platform.service.ClassroomService;
import com.school.platform.service.CourseService;
import com.school.platform.service.UserService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private UserService userService;
	@Autowired
	private ClassroomService classroomService;
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/edit")
	public String editAccountInformation(HttpServletRequest request, 
			@RequestParam(name="username", required=false) String username,
			@RequestParam(name="classroomId", required=false) String classroomId,
			@RequestParam(name="courseId", required=false) String courseId,
			@RequestParam("field-to-edit") String field,
			@RequestParam("pageReview") String pageReview, Model theModel) {
		
		theModel.addAttribute("field", field);
		String fieldName = field.substring(field.lastIndexOf(".")+1);
		theModel.addAttribute("fieldName", fieldName);
		if(username != null && !username.isEmpty()) {
			theModel.addAttribute("theUser", userService.getUser(username));
			theModel.addAttribute("fieldValue", userService.getFieldValue(username, field));
		} else if(classroomId != null && !classroomId.isEmpty()) {
			theModel.addAttribute("theClassroom", classroomService.getClassroom(Integer.parseInt(classroomId)));
			theModel.addAttribute("fieldValue", classroomService.getFieldValue(classroomId, field));
		} else if(courseId != null && !courseId.isEmpty()) {
			theModel.addAttribute("theCourse", courseService.getCourse(Integer.parseInt(courseId)));
			theModel.addAttribute("fieldValue", courseService.getFieldValue(courseId, field));
		}
		
		if(fieldName.equals("classroom") || fieldName.equals("headTeacherAccountDetails")) {
			Principal principal = request.getUserPrincipal();
			String adminUsername = principal.getName();
			
			theModel.addAttribute("trigger", 2);
			if(fieldName.equals("classroom")) {
				theModel.addAttribute("classroomsList", classroomService.getSchoolClassrooms(userService.getUser(adminUsername)));
			} else {
				
			}
		} else {
			theModel.addAttribute("trigger", 1);
		}
		theModel.addAttribute("pageReview", pageReview);
		return "confirmation-page";
		
		// trebuie sa intri in fiecare create-xxx.jsp, sa vezi ce campuri au dropdown si sa le faci sa mearga editate
		// totodata trb sa pui validation rules dupa ce a fost editata smekeria
	}
}
