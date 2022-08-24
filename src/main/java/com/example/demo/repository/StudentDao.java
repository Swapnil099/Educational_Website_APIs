package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitiy.Student;

public interface StudentDao  extends JpaRepository<Student, Long> {

}
