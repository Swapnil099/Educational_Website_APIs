package com.example.demo.entitiy;

import javax.persistence.Entity;
import javax.persistence.Id;

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
	
	
	public Course() {
		super();
	}

	public Course(long id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Courses [id=" + id + ", title=" + title + ", description=" + description + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}