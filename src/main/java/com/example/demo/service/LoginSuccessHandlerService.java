package com.example.demo.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.entitiy.LibUser;
import com.example.demo.entitiy.Role;

@Component
public class LoginSuccessHandlerService extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	LibUserService libUserService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		User userDetails = (User) authentication.getPrincipal();

		String redirectURL = request.getContextPath();
			
		Collection<GrantedAuthority> listOfRoles = userDetails.getAuthorities();
		
		String role = getRole(listOfRoles);
		
		LibUser libUser = libUserService.getUser(userDetails.getUsername());
		
		System.out.println("list of roles are --- "+role);
		
		if (role.equalsIgnoreCase("ROLE_STUDENT")) {
			Long studentId = libUser.getStudent().getId();
			redirectURL = ("/student/course" + "?studentId=" + studentId);
		} else if (role.equalsIgnoreCase("ROLE_ADMIN")) {			
			Long adminId = libUser.getId();
			redirectURL = ("/admin/assignRole" + "?adminId=" + adminId);			
		} else if (role.equalsIgnoreCase("ROLE_TA")) {			
			Long TaId = libUser.getTeachingAssistant().getId();
			redirectURL = ("/ta" + "?TaId=" + TaId);				
		}
		else redirectURL = "/wait";

		response.sendRedirect(redirectURL);
	}
	
    public String getRole(Collection<GrantedAuthority> listOfRoles) {
        Iterator<GrantedAuthority> iterator = listOfRoles.iterator();
        while (iterator.hasNext()) {
        	GrantedAuthority role = iterator.next();
        	return role.toString();
        }
        return "null";
    }
}
