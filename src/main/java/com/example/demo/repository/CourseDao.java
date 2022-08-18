package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitiy.Course;

public interface CourseDao extends JpaRepository<Course, Long>{

} 
