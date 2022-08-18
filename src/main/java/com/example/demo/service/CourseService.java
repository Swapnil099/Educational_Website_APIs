package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;

import com.example.demo.entitiy.Course;

public interface CourseService {
	
	public List<Course> getCourses();
	public ResponseEntity<String> addCourse(Course course)  throws Exception;
	public ResponseEntity<String> getCourseByID(long id) throws Exception;
	public ResponseEntity<String> updateCourse(Course course)  throws Exception; 
	public ResponseEntity<String> deleteCourseById(String courseId)  throws Exception;
}
