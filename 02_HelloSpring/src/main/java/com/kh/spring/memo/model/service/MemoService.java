package com.kh.spring.memo.model.service;

import java.util.List;

import com.kh.spring.memo.model.vo.Memo;


public interface MemoService {

	int memoInsert(Memo param);

	List<Memo> memoList();

	int memoDelete(int memoNo);

}
