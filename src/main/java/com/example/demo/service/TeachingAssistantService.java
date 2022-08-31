 package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.entitiy.TeachingAssistant;

public interface TeachingAssistantService {
	public ResponseEntity<?> saveTeachingAssistant(TeachingAssistant theTeachingAssistant);
	
	public ResponseEntity<?> AssignTeachingAssistantToStudent(Long teachingAssistantId,Long studentId);
}
