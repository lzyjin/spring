package com.mvcboot.springboot.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvcboot.springboot.model.dao.MemberDao;
import com.mvcboot.springboot.model.mapper.MemberMapper;
import com.mvcboot.springboot.model.vo.Member;

@Service
public class MemberService {
	
	// @Autowired
	// SqlSessionTemplate session;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	MemberDao dao;
	
	
	
	public List<Member> selectMemberAll() {
		
		return dao.selectMemberAll(memberMapper);
	}



	public Member selectOneMember(String userId) {
		
		return dao.selectOneMember(memberMapper, userId);
	}

}
