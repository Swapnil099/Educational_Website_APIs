package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CourseDao;
import com.example.demo.entities.Course;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDao courseDao;
	
	public CourseServiceImpl() {

	}
	
	@Override
	public List<Course> getCourses() {
		return courseDao.findAll();
	}

	@Override
	public String addCourse(Course course) {
		courseDao.save(course);
		return "Course Added";
	}

	@Override
	public Course getCourseByID(long Id) {
		return courseDao.getReferenceById(Id);
	}

	@Override
	public ResponseEntity<String> updateCourse(Course course) {
		courseDao.save(course);
		return new ResponseEntity<String>("Course updated",HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<String> deleteCourseById(String courseId) {
		courseDao.deleteById(Long.parseLong(courseId));
		return new ResponseEntity<String>("Course Deleted",HttpStatus.ACCEPTED);
	}

}
