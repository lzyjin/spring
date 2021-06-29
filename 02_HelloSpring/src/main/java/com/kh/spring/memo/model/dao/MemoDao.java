package com.kh.spring.memo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.spring.memo.model.vo.Memo;

public interface MemoDao {

	int memoInsert(Memo param, SqlSessionTemplate sqlSession);

	List<Memo> memoList(SqlSessionTemplate sqlSession);

	int memoDelete(SqlSessionTemplate sqlSession, int memoNo);

}
