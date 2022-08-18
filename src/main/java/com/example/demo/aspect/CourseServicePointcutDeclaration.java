package com.example.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CourseServicePointcutDeclaration {
	
	@Pointcut("execution(* com.example.demo.service.CourseService.addCourse(..))")
	public void addCourseLog() {};
	
	@Pointcut("execution(* com.example.demo.service.CourseService.getCourseByID(..))")
	public void getCourseByIDLog() {};
	
	@Pointcut("execution(* com.example.demo.service.CourseService.updateCourse(..))")
	public void updateCourseLog() {};
	
	@Pointcut("execution(* com.example.demo.service.CourseService.deleteCourseById(..))")
	public void deleteCourseByIDLog() {};
	
}
