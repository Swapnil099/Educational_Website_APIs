package com.example.demo.entitiy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Table(name = "student")
@JsonIdentityInfo(generator=IntSequenceGenerator.class, property="json_id")
public class Student {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="first_name",nullable=false)
	private String firstName;
	@Column(name="last_name",nullable=false)
	private String lastName;
	@Column(name="email",nullable=false)
	private String email;
	private Date Dob;

	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="libUser_id",nullable = false)
//	@JsonBackReference
	@JsonIgnore
	private LibUser libUser;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

	public void addDoubtToList(Doubt theDoubt) {
		if(doubtList == null) doubtList = new ArrayList<>();
		doubtList.add(theDoubt);
	}
	
	public void addCourseToList(Course theCourse) {
		if(courseList == null) courseList = new ArrayList<>();
		courseList.add(theCourse);
	}
	
	public void addTeachingAssistantToList(TeachingAssistant theTeachingAssistant) {
		if(teachingAssistantList == null) teachingAssistantList = new ArrayList<>();
		teachingAssistantList.add(theTeachingAssistant);
	}
	
	public void addReviewToList(CourseReview theCourseReview) {
		if(reviewList == null) reviewList = new ArrayList<>();
		reviewList.add(theCourseReview);
	}
	
	
	
}
