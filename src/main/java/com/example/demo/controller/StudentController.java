package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entitiy.Course;
import com.example.demo.entitiy.CourseReview;
import com.example.demo.entitiy.Doubt;
import com.example.demo.entitiy.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping(path = "/")
	public ResponseEntity<?> addStudentByLibUserId(@RequestBody Student theStudent,@RequestParam String libUserId){
		return studentService.saveStudentByLibUserId(theStudent, Long.parseLong(libUserId));
	}
	
	@GetMapping(path = "/")
	public ResponseEntity<?> getStudentByStudentId(@RequestParam String studentId){
		return studentService.getStudentById(Long.parseLong(studentId));
	}
	
	@GetMapping(path = "/doubt")
	public ResponseEntity<?> getDoubtList(@RequestParam String studentId){
		return studentService.getDoubtList(Long.parseLong(studentId));
	}
	
	
	@PostMapping(path = "/course")
	public ResponseEntity<?> assignCourse(@RequestParam String courseId,@RequestParam String studentId){
		return studentService.assignCourse(Long.parseLong(courseId),Long.parseLong(studentId));
	}
	
	@GetMapping(path = "/course")
	public ResponseEntity<?> getCourseList(@RequestParam String studentId){
		return studentService.getCourseList(Long.parseLong(studentId));
	}

	
	@GetMapping(path = "/course-review")
	public ResponseEntity<?> getReviewList(@RequestParam String studentId){
		return studentService.getReviewList(Long.parseLong(studentId));
	}
	
	@GetMapping(path = "/comment")
	public ResponseEntity<?> getCommentList(@RequestParam String studentId){
		return studentService.getCommentList(Long.parseLong(studentId));
	}

	
	@GetMapping(path = "/teaching-assistant")
	public ResponseEntity<?> assignTeachingAssistant(@RequestParam String studentId){
		return studentService.getTeachingAssistantList(Long.parseLong(studentId));
	}
	
}
