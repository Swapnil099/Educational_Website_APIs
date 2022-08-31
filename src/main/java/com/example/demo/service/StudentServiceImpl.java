package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entitiy.Comment;
import com.example.demo.entitiy.Course;
import com.example.demo.entitiy.CourseReview;
import com.example.demo.entitiy.Doubt;
import com.example.demo.entitiy.LibUser;
import com.example.demo.entitiy.Student;
import com.example.demo.entitiy.TeachingAssistant;
import com.example.demo.repository.CourseDao;
import com.example.demo.repository.CourseReviewDao;
import com.example.demo.repository.DoubtDao;
import com.example.demo.repository.LibUserDao;
import com.example.demo.repository.StudentDao;
import com.example.demo.repository.TeachingAssistantDao;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private LibUserDao libUserDao;
	
	@Autowired
	private DoubtDao doubtDao;
	
	@Autowired
	private  CourseDao courseDao;
	
	@Autowired
	private CourseReviewDao courseReviewDao;
	
	@Autowired
	private TeachingAssistantDao teachingAssistantDao;
	
	@Override
	public ResponseEntity<?> saveStudentByLibUserId(Student theStudent, Long libUserId) {
		try {
			LibUser tempLibUser = libUserDao.getReferenceById(libUserId);
			theStudent.setLibUser(tempLibUser);
			tempLibUser.setStudent(theStudent);
			Student savedStudent = studentDao.save(theStudent);
			return ResponseEntity.ok().body(savedStudent);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Error Occured - " + e.toString());
		}
	}

	@Override
	public ResponseEntity<?> getStudentById(Long studentId) {
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
//			System.out.println(tempStudent.getLibUser().getName());
			return ResponseEntity.ok().body(tempStudent);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Error Occured - " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getDoubtList(Long studentId) {
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
			return ResponseEntity.ok().body(tempStudent.getDoubtList());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error Occured - " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getCourseList(Long studentId) {
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
			return ResponseEntity.ok().body(tempStudent.getCourseList());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error Occured - " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getReviewList(Long studentId) {
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
			return ResponseEntity.ok().body(tempStudent.getReviewList());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error Occured - " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getCommentList(Long studentId) {
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
			return ResponseEntity.ok().body(tempStudent.getLibUser().getCommentList());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error Occured - " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getTeachingAssistantList(Long studentId) {
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
			return ResponseEntity.ok().body(tempStudent.getLibUser().getTeachingAssistant());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error Occured - " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> assignCourse(Long courseId, Long studentId) {
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
			Course tempCourse = courseDao.getReferenceById(courseId);
			
			for(Course course:tempStudent.getCourseList()) 
				if(course.getId() == courseId)  return ResponseEntity.ok().body("You have already Enrolled In this Course");
			
			
			tempCourse.addStudentToList(tempStudent);
			courseDao.save(tempCourse);
			tempStudent.addCourseToList(tempCourse);
			studentDao.save(tempStudent);
			return ResponseEntity.ok().body(tempStudent);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error occured - " + e.getMessage());
		}
	}



}
