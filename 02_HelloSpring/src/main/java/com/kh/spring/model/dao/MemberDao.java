package com.kh.spring.model.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.spring.model.vo.Member;

public interface MemberDao {

	int insertMember(SqlSessionTemplate sqlSession, Member member);

	Member memberLogin(SqlSessionTemplate sqlSession, Member member);

}
