package com.mvcboot.springboot.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvcboot.springboot.model.mapper.MemberMapper;
import com.mvcboot.springboot.model.vo.Member;

import oracle.net.aso.u;

@Repository
public class MemberDao {

	public List<Member> selectMemberAll(MemberMapper memberMapper) {
		return memberMapper.selectMemberAll();
	}

	public Member selectOneMember(MemberMapper memberMapper, String userId) {
		
		return memberMapper.selectOneMember(userId);
	}



}
