package com.spring.hellospring.demo.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hellospring.demo.model.dao.DemoDao;
import com.spring.hellospring.demo.model.vo.Dev;

@Service
public class DemoServiceImpl implements DemoService{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// SqlSessionTemplate는 bean으로 등록해놨기때문에 사용가능 
	
	@Autowired
	private DemoDao dao;

	@Override
	public int insertDev(Dev d) {
		
		return dao.insertDev(sqlSession, d);
	}

	
	
	@Override
	public List<Dev> selectDevList() {
		
		return dao.selectDevList(sqlSession);
	}

}
