package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitiy.CourseReview;

public interface CourseReviewDao  extends JpaRepository<CourseReview, Long> {

}
