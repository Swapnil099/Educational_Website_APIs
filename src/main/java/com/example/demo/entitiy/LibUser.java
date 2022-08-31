package com.example.demo.entitiy;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
@JsonIdentityInfo(generator=IntSequenceGenerator.class, property="json_id")	
public class LibUser {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="first_name",nullable=false)
	private String firstName;
	@Column(name="last_name",nullable=false)
	private String lastName;
	@Column(name="email",nullable=false)
	private String email;
	private Date Dob;
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<>();
	
	@OneToMany(mappedBy = "libUser", cascade = CascadeType.ALL)
	private List<Comment> commentList;
	
	@OneToOne(mappedBy = "libUser", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JsonManagedReference
	private Student student;
	
	@OneToOne(mappedBy = "libUser", cascade = CascadeType.ALL)
	private TeachingAssistant teachingAssistant;
	

}
