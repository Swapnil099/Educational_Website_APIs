package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entitiy.Comment;
import com.example.demo.entitiy.LibUser;
import com.example.demo.entitiy.Role;

public interface LibUserService extends UserDetailsService  {
	LibUser saveUser(LibUser newLibUser);
	Role saveRole(Role role);
	void addRoleToLibUser(String username,String rolename);
	LibUser getUser(String username);
	List<LibUser>getUsers();
	void deleteUserByUsername(String username) throws UsernameNotFoundException;
//	public boolean hasRole(String role);
}
