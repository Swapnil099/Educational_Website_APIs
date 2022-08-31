package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entitiy.Doubt;
import com.example.demo.service.DoubtService;

@Controller
@RequestMapping("/api/doubt")
public class DoubtController {
	
	@Autowired
	private DoubtService doubtService;
	
	@DeleteMapping("")
	public ResponseEntity<?> deleteDoubtById(@RequestParam String doubtId){
		return doubtService.deleteDoubt(Long.parseLong(doubtId));
	}
	
	@PostMapping(path = "")
	public ResponseEntity<?> addDoubt(@RequestParam String studentId,@RequestBody Doubt theDoubt){
		return doubtService.addDoubt(theDoubt,Long.parseLong(studentId));
	}
	
	@PostMapping(path = "/resolve")
	public ResponseEntity<?> resolveDoubt(@RequestParam String doubtId,@RequestParam String teachingAssistantId){
		return doubtService.ChangeDoubtStatus(Long.parseLong(doubtId),Long.parseLong(teachingAssistantId));
	}
	
	@GetMapping(path = "")
	public ResponseEntity<?> resolveDoubt(@RequestParam String courseId){
		return doubtService.getDoubtByCourseId(Long.parseLong(courseId));
	}
	
}
