package com.kh.spring.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.PageFactory;

import lombok.extern.slf4j.Slf4j;

@Controller	
@Slf4j
public class BoardController {
	@Autowired
	private BoardService service;
	
	@RequestMapping("/board/boardList.do")
	public ModelAndView boardList(
			@RequestParam(value="cPage", defaultValue="1") int cPage, 
			@RequestParam(value="numPerpage", defaultValue="5") int numPerpage, 
			ModelAndView mv) {
		mv.addObject("list",service.selectBoardList(cPage,numPerpage));
		int totalData=service.selectBoardCount();
		mv.addObject("pageBar",PageFactory.getPageBar(totalData,cPage,numPerpage,"boardList.do"));
		mv.addObject("totalData", totalData);
		log.debug("{}",mv.getModel().get("list"));
		
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@RequestMapping("/board/boardForm.do")
	public String boardForm() {
		return "board/boardForm";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public ModelAndView insertBoard(Board board,ModelAndView mv, 
			MultipartFile upfile ) {
		log.info("파일명 : "+upfile.getName());
		log.info("파일크기{} : ",upfile.getSize());
		
		mv.setViewName("board/boardList.do");
		return mv;
	}
	
}







