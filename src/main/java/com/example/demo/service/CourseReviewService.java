package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.entitiy.CourseReview;

public interface CourseReviewService {
	ResponseEntity<?> addCourseReview(CourseReview theCourseReview,Long studentId,Long courseId);
	
	ResponseEntity<?> upvoteCourseReview(Long courseReviewId);
	
	ResponseEntity<?> deleteCourseReview(Long courseReviewId,Long studentId);
}
