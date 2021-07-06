package com.inittest.springboot.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
	
	private String userId;
	private String password;
	private int age;
	private String email;

}
