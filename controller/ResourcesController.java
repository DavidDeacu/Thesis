package com.school.platform.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.platform.service.CourseService;
import com.school.platform.service.ResourceService;
import com.school.platform.service.UserService;

@Controller
@RequestMapping("/resources")
public class ResourcesController {
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private ResourceService resourceService;
	
	@GetMapping("")
	public String getClassroomsList(HttpServletRequest request, Model theModel) {
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		theModel.addAttribute("courses", courseService.getTeacherCourses(userService.getUser(username).getTeacherDetails().getId()));
		return "resources-courses-list";
	}
	
	@GetMapping("/see-resources")
	public String seeResources(@RequestParam("courseId") String courseId, Model theModel) {
		theModel.addAttribute("resources", resourceService.getCourseResources(courseService.getCourse(Integer.parseInt(courseId))));
	}
}
