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
	ResponseEntity<Student> saveStudent(Student theStudent);
	
	
	ResponseEntity<Student> getStudentById(Long studentId);
	ResponseEntity<Student> getStudentByLibUserId(Long libUserId);
	
	ResponseEntity<List<Doubt>> getDoubtList();
	ResponseEntity<List<Course>> getCourseList();
	ResponseEntity<List<CourseReview>> getReviewList();
	ResponseEntity<List<Comment>> getCommentList();
	ResponseEntity<List<TeachingAssistant>> getTeachingAssistantList();
}
