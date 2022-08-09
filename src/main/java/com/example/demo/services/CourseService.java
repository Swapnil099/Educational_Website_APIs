package com.example.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entities.Course;

public interface CourseService {
	
	public List<Course> getCourses();
	public String addCourse(Course course);
	public Course getCourseByID(long id);
	public ResponseEntity<String> updateCourse(Course course); 
	public ResponseEntity<String> deleteCourseById(String courseId);
}
