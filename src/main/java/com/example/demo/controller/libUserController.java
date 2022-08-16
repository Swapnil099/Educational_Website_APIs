package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.LibUser;
import com.example.demo.entities.Role;
import com.example.demo.services.LibUserService;

@RestController
@RequestMapping("/api")
public class libUserController {
	private final LibUserService libUserService;
	
	public libUserController(LibUserService libUserService) {
		super();
		this.libUserService = libUserService;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<LibUser>> getusers(){
		return ResponseEntity.ok().body(libUserService.getUsers());
	}
	
	@PostMapping("/user/save")
	public ResponseEntity<LibUser> saveLibUser(@RequestBody LibUser newLibUser){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toString());
		return ResponseEntity.created(uri).body(libUserService.saveUser(newLibUser));
	}
	
	@PostMapping("/role/save")
	public ResponseEntity<Role> saveRole(@RequestBody Role newRole){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toString());
		return ResponseEntity.created(uri).body(libUserService.saveRole(newRole));
	}
	
	@PostMapping("/role/addtouser")
	public ResponseEntity<String> addRoleToUser(@RequestBody RoleToUserForm form){
		libUserService.addRoleToLibUser(form.getUsername(), form.getRolename());
		return ResponseEntity.ok().body("User Role Added");
	}
	
	@DeleteMapping("/user/delete")
	public ResponseEntity<?> deleteUserByUsername(@RequestParam String username){
		libUserService.deleteUserByUsername(username);
		return ResponseEntity.ok().body(username + " is deleted");
	}
	
}

class RoleToUserForm{
	private String username;
	private String rolename;
	
	public RoleToUserForm(String username, String rolename) {
		super();
		this.username = username;
		this.rolename = rolename;
	}
	
	public RoleToUserForm() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
