package com.kh.spring.board.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Board;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao dao;
	
	@Autowired
	private SqlSessionTemplate session;

//	@Override
//	public List<Board> boardList() {
//		
//		return dao.boardList(session);
//	}

	@Override
	public int countBoard() {
		
		return dao.countBoard(session);
	}

	@Override
	public List<Board> selectBoardList(int cPage, int numPerPage) {
		
		return dao.selectBoardList(cPage, numPerPage, session);
	}

	@Override
	public Board selectBoard(int boardNo) {
		
		return dao.selectBoard(boardNo, session);
	}

}
