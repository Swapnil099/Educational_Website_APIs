package com.example.demo.aspect;

import javax.persistence.EntityNotFoundException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CourseServiceLoggingAspect {

	final static Logger logger = LoggerFactory.getLogger(CourseServiceLoggingAspect.class);

	@AfterThrowing(
				pointcut = "com.example.demo.aspect.CourseServicePointcutDeclaration.addCourseLog()",
				throwing = "exception")
		public void afterThrowingAddCourseLog(JoinPoint joinPoint,Exception exception) {
			logger.error("Invalid Course : "+ exception.getMessage());
		}
	
	@AfterThrowing(
			pointcut = "com.example.demo.aspect.CourseServicePointcutDeclaration.getCourseByIDLog()",
			throwing = "exception")
	public void afterThrowingGetCourseByIDLog(JoinPoint joinPoint,Exception exception) {
		logger.error("Enter Valid Course ID : "+ exception.getMessage());
	}
	
	@AfterThrowing(
			pointcut = "com.example.demo.aspect.CourseServicePointcutDeclaration.updateCourseLog()",
			throwing = "exception")
	public void afterThrowingUpdateCourseLog(JoinPoint joinPoint,Exception exception) {
		logger.error("Course Updation Failed: "+ exception.getMessage());
	}
	

}
