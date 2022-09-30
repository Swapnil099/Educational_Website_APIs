package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

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
	public Student getStudentById(Long studentId) {
		Student tempStudent = null;
		try {
			tempStudent = studentDao.getReferenceById(studentId);
//			System.out.println(tempStudent.getLibUser().getName());
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return tempStudent;
	}

	@Override
	public List<Doubt> getDoubtList(Long studentId) {
		List<Doubt> doubt = new ArrayList<Doubt>();
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
			doubt = tempStudent.getDoubtList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return doubt;
	}

	@Override
	public List<Course> getCourseList(Long studentId) {
		List<Course> courses = new ArrayList<Course>();
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
			courses = tempStudent.getCourseList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return courses;
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
	public List<TeachingAssistant> getTeachingAssistantList(Long studentId) {
		List<TeachingAssistant> teachingAssistantList = new ArrayList<>();
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
			teachingAssistantList = tempStudent.getTeachingAssistantList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return teachingAssistantList;
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
