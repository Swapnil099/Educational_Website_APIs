package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entitiy.TeachingAssistant;
import com.example.demo.service.TeachingAssistantService;

@Controller
@RequestMapping("/api/teaching-assistant")
public class TeachingAssistantController {
	
	@Autowired
	private TeachingAssistantService teachingAssistantService;
	
	@PostMapping("/")
	public ResponseEntity<?> saveTeachingAssistant(@RequestBody TeachingAssistant theTeachingAssistant){
		return teachingAssistantService.saveTeachingAssistant(theTeachingAssistant);
	}
	
	@PostMapping(path = "/assign")
	public ResponseEntity<?> assignTeachingAssistant(@RequestParam String studentId,@RequestParam String teachingAssistantId){
		return teachingAssistantService.AssignTeachingAssistantToStudent(Long.parseLong(teachingAssistantId), Long.parseLong(studentId));
	}
	
}
