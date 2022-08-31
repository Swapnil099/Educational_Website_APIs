package com.example.demo.entitiy;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Course {
	
	@Id
	private long id;
	private String title;
	private String description;
	private int price;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="course_id")
	private List<CourseReview> reviewList;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(
			name="student_course",
			joinColumns =@JoinColumn(name="course_id"),
			inverseJoinColumns =@JoinColumn(name = "student_id"))
	private List<Course> courseList;
	
		
	
}
