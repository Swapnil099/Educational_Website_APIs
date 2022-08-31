package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entitiy.Course;
import com.example.demo.entitiy.CourseReview;
import com.example.demo.entitiy.Student;
import com.example.demo.repository.CourseDao;
import com.example.demo.repository.CourseReviewDao;
import com.example.demo.repository.StudentDao;

@Service
public class CourseReviewServiceImpl implements CourseReviewService {
	
	@Autowired
	private  StudentDao studentDao;
	
	@Autowired
	private  CourseDao courseDao;
	
	@Autowired
	private  CourseReviewDao courseReviewDao;
	
	@Override
	public ResponseEntity<?> addCourseReview(CourseReview theCourseReview, Long studentId,Long courseId) {
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
			Course tempCourse = courseDao.getReferenceById(courseId);
			
			for(CourseReview tempCourseReview:tempStudent.getReviewList()) 
				if(tempCourseReview.getCourseId() == courseId)  return ResponseEntity.ok().body("You have already submitted review for this Course " + tempCourseReview);
			
			theCourseReview.setCourseId(courseId);
			theCourseReview.setStudentId(studentId);
			
			tempCourse.addReviewToList(theCourseReview);
			tempStudent.addReviewToList(theCourseReview);
			
			courseReviewDao.save(theCourseReview);
			return ResponseEntity.ok().body(tempStudent);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error occured - " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> upvoteCourseReview(Long courseReviewId) {
		return null;
	}

	@Override
	public ResponseEntity<?> deleteCourseReview(Long courseReviewId, Long studentId) {
		try {
			CourseReview tempCourseReview = courseReviewDao.getReferenceById(courseReviewId);
			
			if(tempCourseReview.getStudentId() != studentId) return ResponseEntity.badRequest().body("Action can not be performed");
			
			courseReviewDao.delete(tempCourseReview);
			return ResponseEntity.ok().body(tempCourseReview);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error occured - " + e.getMessage());
		}
	}

}
