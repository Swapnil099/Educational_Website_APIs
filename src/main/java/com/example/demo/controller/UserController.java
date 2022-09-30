package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entitiy.LibUser;
import com.example.demo.service.LibUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private LibUserService libUserService;
	
//	@ModelAttribute("libUser")
//	public LibUser libUser() {
//		return new LibUser();
//	}
	
	@GetMapping("/login")
	public String showLoginSignupForm(Model model) {
		model.addAttribute("libUser", new LibUser());
		return "login-signup";
	}
	
	@PostMapping("/register")
	public String registerNewUser(@ModelAttribute LibUser libUser) {
		System.out.println("------------ -----------"+libUser);
		libUserService.saveUser(libUser);
		return "redirect:/user/login?success";
	}
	
	
}
