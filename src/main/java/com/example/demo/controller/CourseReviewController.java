package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entitiy.CourseReview;
import com.example.demo.service.CourseReviewService;

@Controller
@RequestMapping("/api/course-review")
public class CourseReviewController {
	
	@Autowired
	private CourseReviewService courseReviewService;
	
	@PostMapping(path = "")
	public ResponseEntity<?> addCourseReview(@RequestBody CourseReview theCourseReview,@RequestParam String studentId,@RequestParam String courseId){
		return courseReviewService.addCourseReview(theCourseReview,Long.parseLong(studentId),Long.parseLong(courseId));
	}
	
	@DeleteMapping(path = "")
	public ResponseEntity<?> deleteCourseReview(@RequestParam String studentId,@RequestParam String courseReviewId){
		return courseReviewService.deleteCourseReview(Long.parseLong(courseReviewId),Long.parseLong(studentId));
	}
	
}
