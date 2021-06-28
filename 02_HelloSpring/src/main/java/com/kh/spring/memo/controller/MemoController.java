package com.kh.spring.memo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemoController {
	
	@Autowired
	private MemoService service;

//	@RequestMapping("/memo/memo.do")
//	public String memo(HttpServletRequest request) {
//		
//		List<Memo> memoList = service.memoList();
//		
//		request.setAttribute("list", memoList);
//		
//		return "memo/memo";
//	}
	
	@RequestMapping("/memo/memo.do")
	public ModelAndView memo(ModelAndView mv) {
		
				log.debug("memo메소드 실행중...");
		
		List<Memo> memoList = service.memoList();
		
		mv.addObject("list", memoList); // model에 데이터 저장 ( 키값을 중복되지않게 할것 )  
		mv.setViewName("memo/memo"); // view 세팅
		
		return mv;
	}
	
	
	
	
	@RequestMapping("/memo/memoInsert.do")
	public String memoInsert(Memo param, HttpServletRequest request) {
		
			// System.out.println("MemoController, param : " + param);
		
		int result = service.memoInsert(param);
		
		request.setAttribute("msg", result > 0 ? "메모등록성공" : "메모등록실패");
		request.setAttribute("loc", "/memo/memo.do");
		
		return "common/msg";
		
	}
	
	
//	@RequestMapping("memo/memoDelete.do")
//	public String memoDelete(@RequestParam()) {
//		
//	}
}
