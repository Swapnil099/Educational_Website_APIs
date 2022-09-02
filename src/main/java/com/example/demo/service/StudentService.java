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
	
	Student getStudentById(Long studentId);
	
	List<Doubt> getDoubtList(Long studentId);
    List<Course> getCourseList(Long studentId);
	ResponseEntity<?> getReviewList(Long studentId);
	ResponseEntity<?> getCommentList(Long studentId);
	List<TeachingAssistant> getTeachingAssistantList(Long studentId);
	
	ResponseEntity<?> assignCourse(Long courseId,Long studentId);
	
}
