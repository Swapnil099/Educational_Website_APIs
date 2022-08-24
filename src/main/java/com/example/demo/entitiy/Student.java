package com.example.demo.entitiy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "student")
public class Student {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String email;
	private String city;
	private Date dob;
	private String collegeName;

	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="libUser_id")
	private LibUser libUser;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Doubt> doubtList;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="student_id")
	private List<CourseReview> reviewList;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(
			name="student_course",
			joinColumns =@JoinColumn(name="student_id"),
			inverseJoinColumns =@JoinColumn(name = "course_id"))
	private List<Course> courseList;
	
	@ManyToMany(cascade =  {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(
			name="student_teaching_assistant",
			joinColumns =@JoinColumn(name="student_id"),
			inverseJoinColumns =@JoinColumn(name = "teaching_assistant_id"))
	private List<TeachingAssistant> teachingAssistantList;
	
	
}
