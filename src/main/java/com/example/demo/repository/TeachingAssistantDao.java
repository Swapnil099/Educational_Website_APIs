package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitiy.TeachingAssistant;

public interface TeachingAssistantDao  extends JpaRepository<TeachingAssistant, Long> {

}
