package com.kh.spring.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.model.dao.MemberDao;
import com.kh.spring.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao dao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertMember(Member member) {
		
		return dao.insertMember(sqlSession, member);
	}

	@Override
	public Member memberLogin(Member member) {
		
		return dao.memberLogin(sqlSession, member);
	}

}
