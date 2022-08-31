package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entitiy.LibUser;
import com.example.demo.entitiy.Student;
import com.example.demo.entitiy.TeachingAssistant;
import com.example.demo.repository.LibUserDao;
import com.example.demo.repository.StudentDao;
import com.example.demo.repository.TeachingAssistantDao;

@Service
public class TeachingAssistantServiceImpl implements TeachingAssistantService {

	@Autowired
	private TeachingAssistantDao teachingAssistantDao;
	
	@Autowired
	private LibUserDao libUserDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public ResponseEntity<?> saveTeachingAssistant(TeachingAssistant theTeachingAssistant) {
		try {
			LibUser tempLibUser = libUserDao.getReferenceById((Long) theTeachingAssistant.getLibUser_id());
			theTeachingAssistant.setRating(0);
			theTeachingAssistant.setUsersRated(0);
			theTeachingAssistant.setDoubtSolved(0);
			tempLibUser.setTeachingAssistant(theTeachingAssistant);
			
			libUserDao.save(tempLibUser);
			return ResponseEntity.ok().body(theTeachingAssistant);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error occured - " + e.getMessage());
		}
	}
	
	@Override
	public ResponseEntity<?> AssignTeachingAssistantToStudent(Long teachingAssistantId, Long studentId) {
		try {
			Student tempStudent = studentDao.getReferenceById(studentId);
			TeachingAssistant tempTeachingAssistant = teachingAssistantDao.getReferenceById(teachingAssistantId);
			
			for(TeachingAssistant TA:tempStudent.getTeachingAssistantList())
				if(TA.getId() == teachingAssistantId) return ResponseEntity.ok().body("Already Assigned");
			
			tempStudent.addTeachingAssistantToList(tempTeachingAssistant);
			tempTeachingAssistant.addStudentToList(tempStudent);
			
			studentDao.save(tempStudent);
			return ResponseEntity.ok().body(tempStudent.getTeachingAssistantList());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error occured - " + e.getMessage());
		}
	}

	
}
