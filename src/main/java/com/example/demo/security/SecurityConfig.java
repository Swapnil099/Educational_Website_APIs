package com.example.demo.security;

import java.awt.PageAttributes.MediaType;
import java.lang.invoke.VarHandle.AccessMode;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.entitiy.LibUser;
import com.example.demo.filter.CustomAuthenticationFilter;
import com.example.demo.filter.CustomAuthorizationFiler;
import com.example.demo.repository.LibUserDao;
import com.example.demo.repository.RoleDao;
import com.example.demo.service.LibUserService;
import com.example.demo.service.LibUserServiceImpl;
import com.example.demo.service.LoginSuccessHandlerService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@Configuration @EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LibUserService libUserService;
	
	@Autowired 
	private LoginSuccessHandlerService loginSuccessHandlerService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(libUserService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests().antMatchers("/student").hasAnyAuthority("ROLE_STUDENT").and()
		.authorizeRequests().antMatchers("/admin").hasAnyAuthority("ROLE_ADMIN").and()
		.authorizeRequests().antMatchers("/ta").hasAnyAuthority("ROLE_TA");
		
		http.authorizeRequests().antMatchers(
				"/user/register",
				"/js/**",
				"/css/**",
				"/img/**",
				"/user/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/user/login").usernameParameter("username")
        .successHandler(loginSuccessHandlerService)
        .permitAll()
		.and()
		.logout().invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		.logoutSuccessUrl("/user/login?logout")
		.permitAll();
		
	}
	
	
}
