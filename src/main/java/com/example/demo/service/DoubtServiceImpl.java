package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entitiy.Course;
import com.example.demo.entitiy.Doubt;
import com.example.demo.entitiy.Student;
import com.example.demo.entitiy.TeachingAssistant;
import com.example.demo.repository.CourseDao;
import com.example.demo.repository.DoubtDao;
import com.example.demo.repository.StudentDao;
import com.example.demo.repository.TeachingAssistantDao;

@Service
public class DoubtServiceImpl implements DoubtService {
	
	@Autowired
	private DoubtDao doubtDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private TeachingAssistantDao teachingAssistantDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Override
	public ResponseEntity<?> deleteDoubt(Long doubtId) {
		try {
			Doubt tempDoubt = doubtDao.getReferenceById(doubtId);
			doubtDao.delete(tempDoubt);
			return ResponseEntity.ok().body(tempDoubt);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error Occured - " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> addDoubt(Doubt theDoubt, Long studentId) {
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
			theDoubt.setStudent(tempStudent);
			theDoubt.setResolvedStatus(false);
			theDoubt.setUpvote(Long.parseLong("0"));
			tempStudent.addDoubtToList(theDoubt);
			
			studentDao.save(tempStudent);
			doubtDao.save(theDoubt);
			return ResponseEntity.ok().body(theDoubt);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error occured - " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> ChangeDoubtStatus(Long doubtId, Long teachingAssistantId) {
		try {
			Doubt tempDoubt = doubtDao.getReferenceById(doubtId);
			TeachingAssistant tempTeachingAssistant = teachingAssistantDao.getReferenceById(teachingAssistantId);
			
			if(tempDoubt.getResolvedStatus() == true)  return ResponseEntity.ok().body("Doubt is Already Resolved");
			
			tempDoubt.setResolvedStatus(true);
			tempDoubt.setDoubtSolvedByTA(tempTeachingAssistant);
			tempTeachingAssistant.addDoubtToResolvedList(tempDoubt);
			doubtDao.save(tempDoubt);
			return ResponseEntity.ok().body(tempDoubt);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error occured - " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getDoubtByCourseId(Long courseId) {
		try {
			Course tempCourse = courseDao.getReferenceById(courseId);
			return ResponseEntity.ok().body(tempCourse.getDoubtList());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error occured - " + e.getMessage());
		}
	}

}
