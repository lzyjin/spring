package com.kh.spring.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.spring.board.model.vo.Board;

public interface BoardDao {

//	List<Board> boardList(SqlSessionTemplate session);

	int countBoard(SqlSessionTemplate session);

	List<Board> selectBoardList(int cPage, int numPerPage, SqlSessionTemplate session);

	Board selectBoard(int boardNo, SqlSessionTemplate session);

}
