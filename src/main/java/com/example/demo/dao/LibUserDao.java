package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.LibUser;

public interface LibUserDao extends JpaRepository<LibUser, Long>{
	LibUser findByUsername(String userName);

}
