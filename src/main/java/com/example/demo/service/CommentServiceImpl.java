package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entitiy.Comment;
import com.example.demo.entitiy.Doubt;
import com.example.demo.entitiy.LibUser;
import com.example.demo.repository.CommentDao;
import com.example.demo.repository.DoubtDao;
import com.example.demo.repository.LibUserDao;


@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private  LibUserDao libUserDao;
	
	@Autowired
	private  DoubtDao doubtDao;
	
	@Autowired
	private  CommentDao commentDao;
	
	@Override
	public ResponseEntity<?> addComment(Comment theComment, Long libUserId, Long doubtId) {
		try {
			LibUser tempLibUser = libUserDao.getReferenceById(libUserId);
			Doubt tempDoubt = doubtDao.getReferenceById(doubtId);
			
			theComment.setDoubt(tempDoubt);
			theComment.setLibUser(tempLibUser);
			
			commentDao.save(theComment);
			return ResponseEntity.ok().body(theComment);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error Occured - " + e.toString());
		}
	}

	@Override
	public ResponseEntity<?> deleteComment(Long commentId, Long libUserId) {
		try {
			Comment tempComment = commentDao.getReferenceById(commentId);
			
			if(tempComment.getLibUser().getId() != libUserId)return ResponseEntity.badRequest().body("Can not be performed");
						
			commentDao.delete(tempComment);
			return ResponseEntity.ok().body(tempComment);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Error Occured - " + e.toString());
		}
	}
}
