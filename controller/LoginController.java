package com.school.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String getLoginPage() {
		return "login-form";
	}
	@GetMapping("/access-denied")
	public String getAccessDeniedPage() {
		return "access-denied";
	}
}
