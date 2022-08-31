package com.example.demo.entitiy;

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
@Table(name = "teaching_assistant")
public class TeachingAssistant {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String email;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="libUser_id")
	private LibUser libUser;
	
	@OneToMany(mappedBy = "doubtSolvedByTA", cascade = CascadeType.ALL)
	private List<Doubt> resolvedDoubtList;
	
	@ManyToMany(cascade =  {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(
			name="student_teaching_assistant",
			joinColumns =@JoinColumn(name="teaching_assistant_id"),
			inverseJoinColumns =@JoinColumn(name = "student_id"))
	private List<Student> studentList;
	
}
