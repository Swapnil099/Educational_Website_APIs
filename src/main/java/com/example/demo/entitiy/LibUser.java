package com.example.demo.entitiy;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class LibUser {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<>();
	
	@OneToMany(mappedBy = "libUser", cascade = CascadeType.ALL)
	private List<Comment> commentList;
	
	@OneToOne(mappedBy = "libUser", cascade = CascadeType.ALL)
	private Student student;
	
	@OneToOne(mappedBy = "libUser", cascade = CascadeType.ALL)
	private TeachingAssistant teachingAssistant;
	

}
