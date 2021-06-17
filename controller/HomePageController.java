package com.school.platform.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.platform.service.UserService;

@Controller
public class HomePageController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/home-page")
	public String getHomePage(HttpServletRequest request, Model theModel) {
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		theModel.addAttribute("user", userService.getUser(username));
		return "home-page";
	}
	@GetMapping("/account-information")
	public String getAccountInformationPage(HttpServletRequest request, Model theModel) {
		Principal principal = request.getUserPrincipal();
		String username = principal.getName();
		theModel.addAttribute("user", userService.getUser(username));
		return "account-info-page";
	}
	@GetMapping("/account-information/edit")
	public String editAccountInformation(@RequestParam("username") String username, @RequestParam("field-to-edit") String field, Model theModel) {
		theModel.addAttribute("user", userService.getUser(username));
		theModel.addAttribute("field", field);
		theModel.addAttribute("fieldValue", userService.getFieldValue(username, field));
		theModel.addAttribute("trigger", 1);
		return "account-info-page";
	}
	@PostMapping("/update-account-information")
	public String setAccountInformation(@RequestParam("username") String username, @RequestParam("field-to-edit") String field, @RequestParam("input-box-value") String value) {
		userService.setUserField(username, field, value);
		return "redirect:account-information";
	}
}
