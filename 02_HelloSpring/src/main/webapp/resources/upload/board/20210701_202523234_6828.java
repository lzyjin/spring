package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

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
			@RequestParam(value="upFile", required=false) MultipartFile[] upFile,
			HttpServletRequest req) {
		log.info("파일명 : "+upFile[0].getOriginalFilename());
		log.info("파일크기{} : ",upFile[0].getSize());
		log.info("파일명 : "+upFile[1].getOriginalFilename());
		log.info("파일크기{} : ",upFile[1].getSize());
		
		//저장경로설정하기 -> 실제경로를 가져와야함.
		String path=req.getServletContext().getRealPath("/resources/upload/board/");
		File dir=new File(path);
		//폴더가 없다면 생성
		if(!dir.exists()) dir.mkdirs();
		
		//업로드처리하기 다중
		for(MultipartFile f : upFile) {
			if(!f.isEmpty()) {
				String originalFilename=f.getOriginalFilename();
				String ext=originalFilename.substring(originalFilename.lastIndexOf("."));
				//.jpg .pdf
				
				//리네임규칙설정
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rndNum=(int)(Math.random()*10000);
				String reName=sdf.format(System.currentTimeMillis())+"_"+rndNum+ext;
				
				//리네임으로 파일업로드하기
				try {
					f.transferTo(new File(path+reName));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}//for문끝
		
		
		mv.setViewName("redirect:/board/boardList.do");
		return mv;
	}
	
}







