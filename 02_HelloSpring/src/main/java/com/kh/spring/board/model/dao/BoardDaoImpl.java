package com.kh.spring.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BoardDaoImpl implements BoardDao{

//	@Override
//	public List<Board> boardList(SqlSessionTemplate session) {
//		
//		return session.selectList("board.boardList");
//	}

	@Override
	public int countBoard(SqlSessionTemplate session) {
		
		return session.selectOne("board.countBoard");
	}

	@Override
	public List<Board> selectBoardList(int cPage, int numPerPage, SqlSessionTemplate session) {
		
		RowBounds row = new RowBounds( ((cPage-1)*numPerPage), numPerPage );
		
		return session.selectList("board.selectBoardList", null, row);
	}

	@Override
	public Board selectBoard(int boardNo, SqlSessionTemplate session) {
		
		return session.selectOne("board.selectBoard", boardNo);
	}

	@Override
	public int insertBoard(Board b, SqlSessionTemplate session) {
		
		return session.insert("board.insertBoard", b);
	}

	@Override
	public int insertAttachment(SqlSessionTemplate session, Attachment a) {
		
		log.info("{}",a);
		return session.insert("board.insertAttachment",a);
	}

}
