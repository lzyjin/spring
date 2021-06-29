package com.kh.spring.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

import static com.kh.spring.common.PagebarTemplate.getPagebar;

@Controller
@Slf4j
public class BoardController {

	@Autowired
	private BoardService service;
	
//	@RequestMapping("/board/board.do")
//	public String board(HttpServletRequest request) {
//		
//		int totalData = service.countBoard();
//		
//		String pagebar = getPagebar(1, 10, totalData, 5, "/");
//		
//		List<Board> list = service.boardList();
//		
//		request.setAttribute("list", list);
//		
//		return "board/board";
//	}
	
	// 선생님풀이 
	@RequestMapping("/board/boardList.do")
	public ModelAndView boardList(@RequestParam(value="cPage", defaultValue = "1") int cPage,
						@RequestParam(value="numPerPage", defaultValue = "5") int numPerPage,
						ModelAndView mv) {
		
		List<Board> list = service.selectBoardList(cPage, numPerPage);
		
		mv.addObject("list", list);
		
		mv.setViewName("board/boardList");
		
		int totalData = service.countBoard();
		
		int pageBarSize = 5;
		
		String pagebar = getPagebar(cPage, numPerPage, totalData, pageBarSize, "boardList.do");
																				// 이렇게 쓰면 상대경로 
		mv.addObject("pagebar", pagebar);
		
				log.debug("{}", mv.getModel().get("list"));
				log.debug("{}", mv.getModel().get("pagebar"));
		
		return mv;
		
	}
	
	
	
	
	@RequestMapping("/board/boardView.do")
	public String boardView(@RequestParam(value="no") String no, Model model) {
		
		int boardNo = Integer.parseInt(no);
		
		Board b = service.selectBoard(boardNo);
		
				// System.out.println(" 게시글 상세보기, b : " + b);
		
		model.addAttribute("b", b);
		
		return "board/boardView";
		
	}
	
	@RequestMapping("/board/boardInsert.do")
	public String boardInsert() {
		return null;
		
	}
}
