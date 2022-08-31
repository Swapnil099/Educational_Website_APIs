package com.example.demo.entitiy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "doubt")
@JsonIdentityInfo(generator=IntSequenceGenerator.class, property="json_id")	
public class Doubt {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne  (cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "course_id", nullable = false)
	@JsonIgnore
	private Course course;
	
	@ManyToOne  (cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "student_id",nullable = false)
	@JsonIgnore
	private Student student;
	
	@Column( nullable = false)
	private String question;
	
	@Column(nullable = true)
	private Long upvote;
	
	@Column(name = "resolved_status", nullable = true)
	private Boolean resolvedStatus;
	
	@ManyToOne (cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="doubt_solver_id",nullable = true)
	@JsonIgnore
	private TeachingAssistant doubtSolvedByTA;
	
	@OneToMany(mappedBy = "doubt", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Comment> comments = new ArrayList<>();
	
}
