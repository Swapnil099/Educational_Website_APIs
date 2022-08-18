package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entitiy.Course;
import com.example.demo.repository.CourseDao;

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
	public ResponseEntity<String> addCourse(Course course) throws Exception {
		Course theCourse = courseDao.save(course);
		return new ResponseEntity<String>("Course saved : " + theCourse.toString(), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<String> getCourseByID(long Id) throws Exception {
		Course theCourse = courseDao.getReferenceById(Id);
		return new ResponseEntity<String>(theCourse.toString(), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<String> updateCourse(Course course) throws Exception {
		Course theCourse = courseDao.save(course);
		return new ResponseEntity<String>("Course updated : " + theCourse.toString(), HttpStatus.ACCEPTED);

	}

	@Override
	public ResponseEntity<String> deleteCourseById(String courseId) throws Exception {
		courseDao.deleteById(Long.parseLong(courseId));
		return new ResponseEntity<String>("Course Deleted Successfully", HttpStatus.ACCEPTED);

	}

}
