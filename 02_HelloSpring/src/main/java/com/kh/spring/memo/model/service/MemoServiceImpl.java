package com.kh.spring.memo.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.memo.model.dao.MemoDao;
import com.kh.spring.memo.model.vo.Memo;

@Service
public class MemoServiceImpl implements MemoService{
	
	@Autowired
	private MemoDao dao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int memoInsert(Memo param) {
		
		return dao.memoInsert(param, sqlSession);
	}

	@Override
	public List<Memo> memoList() {
		
		return dao.memoList(sqlSession);
	}

	@Override
	public int memoDelete(int memoNo) {
		
		return dao.memoDelete(sqlSession, memoNo);
	}

}
