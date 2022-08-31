package com.example.demo.entitiy;

import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(generator=IntSequenceGenerator.class, property="json_id")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String description;
	private int rating;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="course_id")
	@JsonIgnore
	private List<CourseReview> reviewList;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Doubt> doubtList;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(
			name="student_course",
			joinColumns =@JoinColumn(name="course_id"),
			inverseJoinColumns =@JoinColumn(name = "student_id"))
	@JsonIgnore
	private List<Student> studentList;
	
	public void addStudentToList(Student theStudent) {
		if(studentList == null) studentList = new ArrayList<>();
		studentList.add(theStudent);
	}
	
	public void addReviewToList(CourseReview theReview) {
		if(reviewList == null) reviewList = new ArrayList<>();
		reviewList.add(theReview);
	}
	
}
