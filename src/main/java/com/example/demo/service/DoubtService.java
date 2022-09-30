package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entitiy.Comment;
import com.example.demo.entitiy.Doubt;

public interface DoubtService {
	public ResponseEntity<?> addDoubt(Doubt theDoubt,Long studentId);
	
	public ResponseEntity<?> deleteDoubt(Long doubtId);
	
	public ResponseEntity<?> ChangeDoubtStatus(Long doubtId,Long teachingAssistantId);
	
	public ResponseEntity<?> getDoubtByCourseId(Long courseId);
	
	public List<Comment> getCommentByDoubtId(Long doubtId);
	
	public Doubt getDoubtById(Long doubtId);
}
