package com.license.cd.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.license.cd.entity.Mark;
import com.license.cd.entity.Student;
import com.license.cd.service.MarkService;
import com.license.cd.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	//need to inject the student and mark service
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MarkService markService;
	
	@GetMapping("/list")
	public String listStudents(Model theModel) {
		
		//get students from the service
		List<Student> theStudents = studentService.getStudents();
		
		//add the students to the model
		theModel.addAttribute("students", theStudents);
		
		return "students-list";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create the model attribute to bind the form data
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		
		return "student-form";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student theStudent) {
		
		//save the student using the injected service
		studentService.saveStudent(theStudent);
		
		return "redirect:/student/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
		
		//get the student from the service
		Student theStudent = studentService.getStudent(theId);
		
		//set the student as a model attribute to pre-populate the form
		theModel.addAttribute("student", theStudent);
		
		//send it over to the form
		return "student-form";
	}
	
	@GetMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int theId) {
		
		//delete the student
		studentService.deleteStudent(theId);
		
		return "redirect:/student/list";
	}
	
	
	
	
	
	
	@GetMapping("/showMarks")
	public String showMarks(@RequestParam("studentId") int theId, Model theModel) {
		
		Student theStudent = studentService.getStudent(theId);
		theModel.addAttribute("student", theStudent);
		
		List<Mark> theMarks = markService.getAllMarks(theId);
		
		theModel.addAttribute("marks", theMarks);
		theModel.addAttribute("studentId", theId);
		
		return "marks-list";
	}
	
	@GetMapping("/showFormForAddMark")
	public String showFormForAddMark(@RequestParam("studentId") int theStudentId, Model theModel) {
		
		//create the model attribute to bind the form data
		Mark theMark = new Mark();

		theModel.addAttribute("mark", theMark);
		theModel.addAttribute("studentId", theStudentId);
		
		return "mark-form";
	}
	
	@PostMapping("/saveMark")
	public String saveMark(@ModelAttribute("mark") Mark theMark, @RequestParam("theStudentId") int theId, Model theModel) {
		
		Student theStudent = studentService.getStudent(theId);
		studentService.addMark(theStudent, theMark);

		//save the student using the injected service
		markService.saveMark(theMark);
		
		theModel.addAttribute("studentId", theId);
		
		return "redirect:/student/showMarks";
	}	
	
	@GetMapping("/showFormForUpdateMark")
	public String showFormForUpdateMarks(@RequestParam("studentId") int theStudentId, @RequestParam("markId") int theId, Model theModel) {
		
		//get the mark from the service
		Mark theMark = markService.getMark(theId);
		
		//set the mark as a model attribute to pre-populate the form
		theModel.addAttribute("mark", theMark);
		theModel.addAttribute("studentId", theStudentId);
		
		//send it over to the form
		return "mark-form";
	}
	
	@GetMapping("/deleteMark")
	public String deleteStudentMark(@RequestParam("studentId") int theStudentId, @RequestParam("markId") int theId, Model theModel) {
		
		//delete the student
		markService.deleteMark(theId);
		
		theModel.addAttribute("studentId", theStudentId);
		
		
		return "redirect:/student/showMarks";
	}
	
}
