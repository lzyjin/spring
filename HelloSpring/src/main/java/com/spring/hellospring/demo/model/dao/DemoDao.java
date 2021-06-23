package com.spring.hellospring.demo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.spring.hellospring.demo.model.vo.Dev;

public interface DemoDao {
	
	int insertDev(SqlSessionTemplate sqlSession, Dev dev);
	
	List<Dev> selectDevList(SqlSessionTemplate sqlSession);

}
