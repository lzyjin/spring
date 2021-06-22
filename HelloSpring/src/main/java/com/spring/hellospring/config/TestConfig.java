package com.spring.hellospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.hellospring.Department;
import com.spring.hellospring.Employee;

@Configuration
public class TestConfig {
// 이 클래스는 servlet-context.xml의 역할을 하는 클래스 	

	@Bean // spring contxt에서 활용하는 bean이 등록된다 
	public Employee getEmp() { // 메소드명이 bean의 id값이 된다 
		
		return new Employee();
	}
	
	@Bean
	public Department getDept() {
		Department d = new Department();
		d.setDeptName("자바부");
		d.setDeptCount(20);
		return d;
	}
}
