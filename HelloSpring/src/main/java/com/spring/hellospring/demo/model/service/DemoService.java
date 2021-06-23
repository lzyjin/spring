package com.spring.hellospring.demo.model.service;

import java.util.List;

import com.spring.hellospring.demo.model.vo.Dev;

public interface DemoService {
	
	int insertDev(Dev d);
	
	List<Dev> selectDevList();


}
