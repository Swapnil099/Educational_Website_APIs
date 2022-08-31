package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entitiy.Comment;
import com.example.demo.entitiy.Course;
import com.example.demo.entitiy.CourseReview;
import com.example.demo.entitiy.Doubt;
import com.example.demo.entitiy.Student;
import com.example.demo.entitiy.TeachingAssistant;

public interface StudentService {
	ResponseEntity<?> saveStudentByLibUserId(Student theStudent,Long libUserId);
	
	ResponseEntity<?> getStudentById(Long studentId);
	
	ResponseEntity<?> getDoubtList(Long studentId);
	ResponseEntity<?> getCourseList(Long studentId);
	ResponseEntity<?> getReviewList(Long studentId);
	ResponseEntity<?> getCommentList(Long studentId);
	ResponseEntity<?> getTeachingAssistantList(Long studentId);
	
	ResponseEntity<?> assignCourse(Long courseId,Long studentId);
	
}
