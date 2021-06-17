package com.school.platform.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.school.platform.entity.Grade;
import com.school.platform.entity.ParentsDetails;
import com.school.platform.entity.StudentDetails;
import com.school.platform.entity.User;
import com.school.platform.pojo.InputError;
import com.school.platform.pojo.SortedGrade;
import com.school.platform.service.CourseService;
import com.school.platform.service.GradeService;
import com.school.platform.service.StudentDetailsService;
import com.school.platform.service.UserService;
import com.school.platform.service.ValidationService;

@Controller
@RequestMapping("/grades")
public class GradesController {
	@Autowired
	private UserService userService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private StudentDetailsService studentDetailsService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private ValidationService validationService;

	@GetMapping("")
	public String getGrades(HttpServletRequest request, Model theModel) {
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		User theUser = userService.getUser(username);
		theModel.addAttribute("user", theUser);
		theModel.addAttribute("sortedGradesList", gradeService.getSortedMarks(theUser));
		return "grades-page";
	}
	
	@GetMapping("/show-marks")
	public String showMarks(@RequestParam("username") String username, 
			@RequestParam("sortedGradeIndex") int sortedGradeIndex, Model theModel) {
		User theUser = userService.getUser(username);
		List<SortedGrade> sortedGradesList = gradeService.getSortedMarks(theUser);
		theModel.addAttribute("user", theUser);
		theModel.addAttribute("sortedGradesList", sortedGradesList);
		theModel.addAttribute("modalTrigger", 1);
		theModel.addAttribute("listOfMarks", sortedGradesList.get(sortedGradeIndex).getGrades());
		return "grades-page";
	}
	
	@GetMapping("select-course")
	public String selectCourse(HttpServletRequest request, Model theModel) {
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		User theUser = userService.getUser(username);
		List<Course> coursesList = theUser.getTeacherDetails().getCourses();
		theModel.addAttribute("courses", coursesList);
		return "select-course-page";
	}
	
	@GetMapping("select-student")
	public String selectStudent(@RequestParam("courseId") String courseId, HttpServletRequest request, Model theModel) {
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		User theUser = userService.getUser(username);
		List<Course> coursesList = theUser.getTeacherDetails().getCourses();
		theModel.addAttribute("courses", coursesList);
		theModel.addAttribute("theCourse", coursesList.get(Integer.parseInt(courseId)));
		theModel.addAttribute("modalTrigger", 1);
		return "select-course-page";
	}
	
	@GetMapping("give-mark")
	public String giveMark(@RequestParam("studentDetailsId") String studentDetailsId, 
			@RequestParam("courseId") String courseId, Model theModel) {
		theModel.addAttribute("studentDetails", studentDetailsService.getStudentDetails(Integer.parseInt(studentDetailsId)));
		theModel.addAttribute("theCourse", courseService.getCourse(Integer.parseInt(courseId)));
		theModel.addAttribute("marksList", new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10)));
		return "create-mark";
		
		//fa jspu pt create mark cu formular, mai fa o metoda pt create mark si redirect la course-page cu trigger 1
	}
	
	@PostMapping("save-mark")
	public String saveMark(HttpServletRequest request,
			@RequestParam(name="mark", required=false) String mark,
			@RequestParam(name="date", required=false) String date,
			@RequestParam(name="description", required=false) String description,
			@RequestParam(name="weight", required=false) String weightValue,
			@RequestParam("courseId") String courseId,
			@RequestParam("studentDetailsId") String studentDetailsId, Model theModel) {
		
		List<InputError> inputValidationResults = validationService.validate(mark, date, description);
		theModel.addAttribute("inputValidationResults", inputValidationResults);
		
		int weight;
		if(weightValue != null && weightValue.equals("thesis")) {
			weight = 3;
		} else if(weightValue != null && weightValue.equals("notCountable")) {
			weight = 0;
		} else {
			weight = 1;
		}
		
		Course theCourse = courseService.getCourse(Integer.parseInt(courseId));
		StudentDetails theStudentDetails = studentDetailsService.getStudentDetails(Integer.parseInt(studentDetailsId));
		
		if(inputValidationResults.size() == 0) {
			Grade theGrade = new Grade(Integer.parseInt(mark), date, theCourse, theStudentDetails, description, weight, theCourse.getClassroom().getStudyYear());
			theStudentDetails.addGrade(theGrade);
			gradeService.saveGrade(theGrade);
			studentDetailsService.saveStudentDetails(theStudentDetails);
			
			Principal principal = request.getUserPrincipal();
			String username = principal.getName();
			User theUser = userService.getUser(username);
			List<Course> coursesList = theUser.getTeacherDetails().getCourses();
			theModel.addAttribute("courses", coursesList);
			theModel.addAttribute("theCourse", theCourse);
			theModel.addAttribute("modalTrigger", 1);
			
			return "select-course-page";
		} else {
			for(InputError inputError : inputValidationResults) {
				theModel.addAttribute(inputError.getInputFieldName(), inputError);
			}
			validationService.loadGradeModelAttribute(theModel, description);
			
			theModel.addAttribute("studentDetails", theStudentDetails);
			theModel.addAttribute("theCourse", theCourse);
			theModel.addAttribute("marksList", new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10)));
			return "create-mark";
		}
	}
}
