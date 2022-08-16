package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.LibUser;
import com.example.demo.entities.Role;

public interface LibUserService {
	LibUser saveUser(LibUser newLibUser);
	Role saveRole(Role role);
	void addRoleToLibUser(String username,String rolename);
	LibUser getUser(String username);
	List<LibUser>getUsers();
	void deleteUserByUsername(String username);
}
