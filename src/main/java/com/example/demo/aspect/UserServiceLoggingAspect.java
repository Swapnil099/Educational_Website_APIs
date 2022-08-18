package com.example.demo.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.entitiy.LibUser;
import com.example.demo.entitiy.Role;

@Aspect
@Component
public class UserServiceLoggingAspect {

	final static Logger logger = LoggerFactory.getLogger(UserServiceLoggingAspect.class);

	@Before("com.example.demo.aspect.UserServicePointcutDeclaration.libUserServiceSaveLogging()")
	public void loggingBeforeSave(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		for (Object tempArg : args) {
			if (tempArg instanceof LibUser) {
				LibUser tempLibUser = (LibUser) tempArg;
				logger.info("Request For Creating New User with Username = " + tempLibUser.getUsername());
			}

			if (tempArg instanceof Role) {
				Role tempRole = (Role) tempArg;
				logger.info("Request For Creating New Role = " + tempRole.getName());
			}
		}
	}

	@AfterReturning("com.example.demo.aspect.UserServicePointcutDeclaration.libUserServiceSaveLogging()")
	public void loggingAfterSave(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		for (Object tempArg : args) {
			if (tempArg instanceof LibUser) {
				LibUser tempLibUser = (LibUser) tempArg;
				logger.info("New User with Username = " + tempLibUser.getUsername() + " Saved Successfully");
			}

			if (tempArg instanceof Role) {
				Role tempRole = (Role) tempArg;
				logger.info("New Role Of  " + tempRole.getName() + " is Saved Successfully");
			}
		}
	}

	@Before("com.example.demo.aspect.UserServicePointcutDeclaration.libUserServiceDeleteLogging()")
	public void loggingBeforeDelete(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		String username = (String) args[0];
		logger.info("Delete Request for Username " + username + " Recieved");
	}

	@AfterReturning("com.example.demo.aspect.UserServicePointcutDeclaration.libUserServiceDeleteLogging()")
	public void loggingAfterDelete(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		String username = (String) args[0];
		logger.info("Delete Request for Username " + username + " is Successfull");
	}

	@AfterThrowing(pointcut = "com.example.demo.aspect.UserServicePointcutDeclaration.libUserServiceDeleteLogging()", throwing = "usernameNotFoundException")
	public void loggingAfterDeleteFailed(JoinPoint joinPoint, Throwable usernameNotFoundException) {
		Object[] args = joinPoint.getArgs();
		String username = (String) args[0];
		logger.error("Delete Request for Username " + username + " is Failed");
		logger.error(usernameNotFoundException.getMessage());
	}

}
