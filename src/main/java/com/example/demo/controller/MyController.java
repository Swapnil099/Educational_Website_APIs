package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Course;
import com.example.demo.services.CourseService;


@RestController
public class MyController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/home")
	public String home() {
		return "home page";
	}
	
	@GetMapping("/courses")
	public List<Course> getCourses(){
		return courseService.getCourses();
	}
	
	@GetMapping("/courses/{courseId}")
	public Course getCourseById(@PathVariable String courseId) {
		return courseService.getCourseByID(Long.parseLong(courseId));
	}
	
	@PostMapping("/courses")
	public String addCourse(@RequestBody Course course) {
		return courseService.addCourse(course);
	}
	
	@PutMapping("/course")
	public ResponseEntity<String> updateCourseById(@RequestBody Course course) {
		try {
			return courseService.updateCourse(course);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/course/{courseId}")
	public ResponseEntity<String> deleteCourseByById(@PathVariable String courseId) {
		try {
			return courseService.deleteCourseById(courseId);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
