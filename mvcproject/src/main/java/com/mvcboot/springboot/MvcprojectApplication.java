package com.mvcboot.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value="com.mvcboot.springboot.model.mapper")
public class MvcprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcprojectApplication.class, args);
	}

}
