package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entitiy.Comment;
import com.example.demo.service.CommentService;

@Controller
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("")
	public ResponseEntity<?> addCommentToDoubt(@RequestBody Comment theComment,@RequestParam String doubtId,@RequestParam String libUserId){
		return commentService.addComment(theComment, Long.parseLong(libUserId),Long.parseLong(doubtId));
	}
	
	@DeleteMapping("")
	public ResponseEntity<?> addCommentToDoubt(@RequestParam String commentId,@RequestParam String libUserId){
		return commentService.deleteComment(Long.parseLong(commentId),Long.parseLong(libUserId));
	}
	
}
