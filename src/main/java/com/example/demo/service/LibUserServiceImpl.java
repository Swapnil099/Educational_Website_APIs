package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entitiy.LibUser;
import com.example.demo.entitiy.Role;
import com.example.demo.repository.LibUserDao;
import com.example.demo.repository.RoleDao;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Transactional 
public class LibUserServiceImpl implements LibUserService, UserDetailsService{
	
	private final LibUserDao libUserDao;
	private final RoleDao roleDao;
	private  final BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();
	
	public LibUserServiceImpl(LibUserDao libUserDao, RoleDao roleDao) {
		super();
		this.libUserDao = libUserDao;
		this.roleDao = roleDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LibUser user = libUserDao.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("username not found: " + username);
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role->{
				authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		
		return new User(user.getUsername(),user.getPassword(),authorities);
	}

	@Override
	public LibUser saveUser(LibUser newLibUser) {
		newLibUser.setPassword(bCryptPasswordEncoder.encode(newLibUser.getPassword()));
		return libUserDao.save(newLibUser);
	}

	@Override
	public Role saveRole(Role role) {
		return roleDao.save(role);
	}

	@Override
	public void addRoleToLibUser(String username, String rolename) {
		LibUser tempLibUser = libUserDao.findByUsername(username);
		Role tempRole = roleDao.findByName(rolename);
		tempLibUser.getRoles().add(tempRole);
		
	}

	@Override
	public LibUser getUser(String username) {
		return libUserDao.findByUsername(username);
	}

	@Override
	public List<LibUser> getUsers() {
		return libUserDao.findAll();
	}

	@Override
	public void deleteUserByUsername(String username) throws UsernameNotFoundException {
		LibUser tempLibUser = libUserDao.findByUsername(username);
		try {
			 libUserDao.delete(tempLibUser);
		}
		catch(UsernameNotFoundException e) {
			e.printStackTrace();
		}
		return;
	}

}
