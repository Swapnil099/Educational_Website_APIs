package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.entitiy.Comment;

public interface CommentService {
	ResponseEntity<?> addComment(Comment theComment, Long libUserId, Long doubtId);
	
	ResponseEntity<?> deleteComment(Long commentId, Long libUserId);
}
