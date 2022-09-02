package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
	
	@GetMapping
	public String homePage() {
		return "login-signup";
	}
	
	@GetMapping("/wait")
	public String waitPage() {
		return "wait";
	}
	
	@GetMapping("/student")
	public String studentHomePage() {
		return "/STUDENT/course";
	}
	
	@GetMapping("/admin")
	public String adminHomePage() {
		return "/ADMIN/assignRole";
	}
	
	@GetMapping("/ta")
	public String TaHomePage() {
		return "ta_home";
	}
	
}
