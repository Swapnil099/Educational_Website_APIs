package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitiy.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
