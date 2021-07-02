package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Override
	public int insertMember(SqlSessionTemplate sqlSession, Member member) {
		
		return sqlSession.insert("member.insertMember", member);
	}

	@Override
	public Member selectMember(SqlSessionTemplate sqlSession, Member member) {
		
		return sqlSession.selectOne("member.selectMember", member);
	}

}
