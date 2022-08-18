package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitiy.LibUser;

public interface LibUserDao extends JpaRepository<LibUser, Long>{
	LibUser findByUsername(String userName);

}
