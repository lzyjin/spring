package com.spring.hellospring.config;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Student {
	
	private String name;
	private int grade;
	private int classNum;


}
