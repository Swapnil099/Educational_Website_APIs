package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.entitiy.Doubt;

public interface DoubtService {
	public ResponseEntity<?> addDoubt(Doubt theDoubt,Long studentId);
	
	public ResponseEntity<?> deleteDoubt(Long doubtId);
	
	public ResponseEntity<?> ChangeDoubtStatus(Long doubtId,Long teachingAssistantId);
	
	public ResponseEntity<?> getDoubtByCourseId(Long courseId);
}
