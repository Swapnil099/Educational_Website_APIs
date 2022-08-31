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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "teaching_assistant")
public class TeachingAssistant {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Long libUser_id;
	@Column(name="first_name",nullable=false)
	private String firstName;
	@Column(name="last_name",nullable=false)
	private String lastName;
	private String email;
	private Date Dob;
	private Integer rating;
	private Integer usersRated;
	private Integer doubtSolved;

	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name="libUser_id", insertable = false, updatable = false)
	@JsonIgnore
	private LibUser libUser;
	
	@OneToMany(mappedBy = "doubtSolvedByTA", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	@JsonIgnore
	private List<Doubt> resolvedDoubtList;
	
	@ManyToMany(cascade =  {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(
			name="student_teaching_assistant",
			joinColumns =@JoinColumn(name="teaching_assistant_id"),
			inverseJoinColumns =@JoinColumn(name = "student_id"))
	@JsonIgnore
	private List<Student> studentList;
	
	public void addStudentToList(Student theStudent) {
		if(studentList == null) studentList = new ArrayList<>();
		studentList.add(theStudent);
	}
	
	public void addDoubtToResolvedList(Doubt theDoubt) {
		if(resolvedDoubtList == null) studentList = new ArrayList<>();
		resolvedDoubtList.add(theDoubt);
	}
	
}
