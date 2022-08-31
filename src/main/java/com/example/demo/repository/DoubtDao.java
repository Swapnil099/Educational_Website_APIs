package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitiy.Doubt;

public interface DoubtDao  extends JpaRepository<Doubt, Long> {

}
