package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan()
public class AppConfig{

//   @Bean 
//   PasswordEncoder passwordEncoder() {
//	   return new BCryptPasswordEncoder();
//   }
   
}
