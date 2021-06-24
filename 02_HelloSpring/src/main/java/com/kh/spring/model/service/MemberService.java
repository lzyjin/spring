package com.kh.spring.model.service;

import com.kh.spring.model.vo.Member;

public interface MemberService {

	int insertMember(Member member);

	Member memberLogin(Member member);
	
	

}
