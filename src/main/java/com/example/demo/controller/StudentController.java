package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entitiy.Comment;
import com.example.demo.entitiy.Course;
import com.example.demo.entitiy.CourseReview;
import com.example.demo.entitiy.Doubt;
import com.example.demo.entitiy.Student;
import com.example.demo.service.DoubtService;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	DoubtService doubtService;
	
//	@PostMapping(path = "/")
//	public ResponseEntity<?> addStudentByLibUserId(@RequestBody Student theStudent,@RequestParam String libUserId){
//		return studentService.saveStudentByLibUserId(theStudent, Long.parseLong(libUserId));
//	}
//	
//	@GetMapping(path = "/")
//	public ResponseEntity<?> getStudentByStudentId(@RequestParam String studentId){
//		return studentService.getStudentById(Long.parseLong(studentId));
//	}
//	
//	@GetMapping(path = "/doubt")
//	public ResponseEntity<?> getDoubtList(@RequestParam String studentId){
//		return studentService.getDoubtList(Long.parseLong(studentId));
//	}
//	
//	
//	@PostMapping(path = "/course")
//	public ResponseEntity<?> assignCourse(@RequestParam String courseId,@RequestParam String studentId){
//		return studentService.assignCourse(Long.parseLong(courseId),Long.parseLong(studentId));
//	}
//	
//	@GetMapping(path = "/course")
//	public ResponseEntity<?> getCourseList(@RequestParam String studentId){
//		return studentService.getCourseList(Long.parseLong(studentId));
//	}
//
//	
//	@GetMapping(path = "/course-review")
//	public ResponseEntity<?> getReviewList(@RequestParam String studentId){
//		return studentService.getReviewList(Long.parseLong(studentId));
//	}
//	
//	@GetMapping(path = "/comment")
//	public ResponseEntity<?> getCommentList(@RequestParam String studentId){
//		return studentService.getCommentList(Long.parseLong(studentId));
//	}
//
//	
//	@GetMapping(path = "/teaching-assistant")
//	public ResponseEntity<?> assignTeachingAssistant(@RequestParam String studentId){
//		return studentService.getTeachingAssistantList(Long.parseLong(studentId));
//	}
	
	// mapping for html pages
	
	@GetMapping(path = "/course")
	public String coursePage(@RequestParam String studentId,Model model) {
		List<Course> courses = studentService.getCourseList(Long.parseLong(studentId));
		model.addAttribute("courses", courses);
		model.addAttribute("studentId", studentId);
		return "/STUDENT/course";
	}
	
	@GetMapping(path = "/doubt")
	public String doubtPage(@RequestParam String studentId,Model model) {		
		List<Doubt> doubt = studentService.getDoubtList(Long.parseLong(studentId));
		model.addAttribute("doubts", doubt);
		model.addAttribute("studentId", studentId);
		return "/STUDENT/Doubt";
	}
	
	@GetMapping(path = "/doubt/comment")
	public String doubtCommentPage(@RequestParam String studentId,@RequestParam String doubtId,Model model) {
		Doubt doubt = doubtService.getDoubtById(Long.parseLong(doubtId));
		List<Comment> comments = doubtService.getCommentByDoubtId(Long.parseLong(doubtId));
		model.addAttribute("comments", comments);
		model.addAttribute("doubt", doubt);
		model.addAttribute("studentId", studentId);
		model.addAttribute("doubtId", doubtId);
		return "/STUDENT/doubtComment";
	}
	
	@GetMapping(path = "/explore")
	public String explorePage(@RequestParam String studentId,Model model) {
		model.addAttribute("studentId", studentId);
		return "/STUDENT/explore";
	}
	
	@GetMapping(path = "/profile")
	public String profilePage(@RequestParam String studentId,Model model) {
		Student student = studentService.getStudentById(Long.parseLong(studentId));
		Integer totalCourses = student.getCourseList().size();
		Integer totalDoubts= student.getDoubtList().size();
		Integer totalTA= student.getTeachingAssistantList().size();
		
		model.addAttribute("student", student);
		model.addAttribute("studentId", studentId);
		
		model.addAttribute("totalCourses", totalCourses);
		model.addAttribute("totalDoubts", totalDoubts);
		model.addAttribute("totalTA", totalTA);
		return "/STUDENT/profile";
	}
	
	@GetMapping(path = "/search")
	public String searchPage(@RequestParam String studentId,Model model) {
		model.addAttribute("studentId", studentId);
		return "/STUDENT/search";
	}
	
	@GetMapping(path = "/ta")
	public String taPage(@RequestParam String studentId,Model model) {
		model.addAttribute("studentId", studentId);
		return "/STUDENT/teachingAssistant";
	}
}
