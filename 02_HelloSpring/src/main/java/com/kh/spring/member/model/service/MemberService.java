package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {

	int insertMember(Member member);

	Member selectMember(Member member);
	
	

}
