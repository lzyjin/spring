package com.kh.spring.board.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

	@Override
//	@Transactional
	public int insertBoard(Board b) throws RuntimeException{
		
		int result = dao.insertBoard(b, session);
		
				log.info("{} b.getBoardNo() : ", b.getBoardNo());
		
		if(result > 0) {
			
			if(b.getAttachments().size() > 0) { // b.getAttachments()는 vo에서 이미 객체를 생성해놔서 null이 될수 없으므로 size()로 비교 
				
				for(Attachment a : b.getAttachments()) {
						
						// Attachment 테이블에 boardNo라는 컬럼이 있기때문에 vo에 boardNo 필드를 추가하고, 
						// board를 insert할 때 Attachment의 boardNo에 값을 대입해준다 (같은 트랜잭션에서 처리)
						// mybatis에서 제공하는 기능 : insert하고 바로 select할 수 있는 기능 
					
						try {
							a.setBoardNo(b.getBoardNo());
							result = dao.insertAttachment(session, a);
						} catch (Exception e) {
							// 이렇게하면 출력하고끝나기 때문에 트랜잭션은 에러난것을 모르고 끝나버린다
							//log.debug("test");
							
							throw new RuntimeException("작성실패");
						}
					
				}
			}
		}
		
		return result;
	}
	
	

}
