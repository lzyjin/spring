package com.mvcboot.springboot.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mvcboot.springboot.model.vo.Member;

@Mapper
public interface MemberMapper {
	
	// 메소드를 하나의 쿼리문으로 사용한다 
	// @Select, @Update, @Delete, @Insert
	@Select("SELECT * FROM MEMBER")
	public List<Member> selectMemberAll(); // WHERE절에 들어갈 매개변수가 있다면 여기에 

	@Select("SELECT * FROM MEMBER WHERE USERID = #{userId}")
	public Member selectOneMember(String userId);
	

	

}
