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

import com.example.demo.entitiy.LibUser;
import com.example.demo.filter.CustomAuthenticationFilter;
import com.example.demo.filter.CustomAuthorizationFiler;
import com.example.demo.repository.LibUserDao;
import com.example.demo.repository.RoleDao;
import com.example.demo.service.LibUserServiceImpl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@Configuration @EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private  LibUserDao libUserDao;
	
	public SecurityConfig() {
	}

	public SecurityConfig(LibUserDao libUserDao) {
		super();
		this.libUserDao = libUserDao;
	}
	

	private final UserDetailsService userDetailsService = new UserDetailsService() {
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			LibUser user = libUserDao.findByUsername(username);
			if(user == null) {
				System.out.println("--------------1");
				throw new UsernameNotFoundException("username not found: " + username);
			}
			
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			user.getRoles().forEach(role->{
					authorities.add(new SimpleGrantedAuthority(role.getName()));
			});
			
			return new User(user.getUsername(),user.getPassword(),authorities);
		}
	};
	
	private  final BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("--------------2");
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/course").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/course").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/course").hasAnyAuthority("ROLE_ADMIN");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/user").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/user").hasAnyAuthority("ROLE_ADMIN");
		
		http.authorizeRequests().anyRequest().authenticated();

		http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
		http.addFilterBefore(new CustomAuthorizationFiler(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean(name="AuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
}
