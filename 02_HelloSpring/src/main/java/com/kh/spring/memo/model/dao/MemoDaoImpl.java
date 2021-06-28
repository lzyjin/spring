package com.kh.spring.memo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.memo.model.vo.Memo;

@Repository
public class MemoDaoImpl implements MemoDao{

	@Override
	public int memoInsert(Memo param, SqlSessionTemplate sqlSession) {
		
		return sqlSession.insert("memo.memoInsert", param);
	}

	@Override
	public List<Memo> memoList(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectList("memo.memoList");
	}

	
}
