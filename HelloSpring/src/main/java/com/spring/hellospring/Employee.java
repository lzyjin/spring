package com.spring.hellospring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
	
	private int empNo;
	private String empName;
	private String email;
	private Department dept;
	
}
