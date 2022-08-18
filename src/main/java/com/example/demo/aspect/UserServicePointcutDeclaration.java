package com.example.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class UserServicePointcutDeclaration {
	
	@Pointcut("execution(* com.example.demo.service.LibUserService.save*(..))")
	public void libUserServiceSaveLogging() {};
	
	
	@Pointcut("execution(* com.example.demo.service.LibUserService.deleteUserByUsername(..))")
	public void libUserServiceDeleteLogging() {};
}
