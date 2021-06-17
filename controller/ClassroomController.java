package com.school.platform.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.platform.entity.Course;
import com.school.platform.entity.User;
import com.school.platform.service.CourseService;
import com.school.platform.service.GradeService;
import com.school.platform.service.UserService;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private GradeService gradeService;
	
	@GetMapping("")
	public String getClassroomHomePage(HttpServletRequest request, Model theModel) {
		Principal principal = request.getUserPrincipal();
		String username = principal.getName(); 
		theModel.addAttribute("classroom", userService.getUser(username).getStudentDetails().getClassroom());
		theModel.addAttribute("courses", userService.getUser(username).getStudentDetails().getClassroom().getCourses());
		return "classroom-home-page";
	}
	
	@GetMapping("/course")
	public String getCourse(HttpServletRequest request, @RequestParam("courseId") String courseId, Model theModel) {
		Principal principal = request.getUserPrincipal();
		String username = principal.getName(); 
		User theUser = userService.getUser(username);
		Course theCourse = courseService.getCourse(Integer.parseInt(courseId));
		theModel.addAttribute("theUser", theUser);
		theModel.addAttribute("theCourse", theCourse);
		theModel.addAttribute("ranking",gradeService.getRanking(theUser, theCourse));
		return "classroom-course-page";
	}
}
