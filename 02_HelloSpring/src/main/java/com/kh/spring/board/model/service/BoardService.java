package com.kh.spring.board.model.service;

import java.util.List;

import com.kh.spring.board.model.vo.Board;

public interface BoardService {

//	List<Board> boardList();

	int countBoard();

	List<Board> selectBoardList(int cPage, int numPerPage);

	Board selectBoard(int boardNo);
	
	// 210701
	// 파일 업로드
	int insertBoard(Board b);
}
