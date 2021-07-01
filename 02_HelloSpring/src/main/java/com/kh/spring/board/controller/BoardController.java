package com.kh.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
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
		mv.addObject("totalData", totalData);
		
				log.debug("{}", mv.getModel().get("list"));
				log.debug("{}", mv.getModel().get("pagebar"));
		
		return mv;
		
	}
	
	
	
	
	@RequestMapping("/board/boardView.do")
//	public String boardView(@RequestParam(value="no") String no, Model model) {
	public ModelAndView boardView(int no, ModelAndView mv) {
		
//		int boardNo = Integer.parseInt(no);
		
//		Board b = service.selectBoard(boardNo);
		Board b = service.selectBoard(no);
		
				// System.out.println(" 게시글 상세보기, b : " + b);
		
//		model.addAttribute("b", b);
		mv.addObject("b", b);
		mv.setViewName("board/boardView");
		
//		return "board/boardView";
		return mv;
		
	}
	
	
	// 210701
	
	@RequestMapping("/board/boardForm.do")
	public String boardForm() {
		
		return "board/boardForm";
	}
	

	// 내가 다시 쓴 부분
	@RequestMapping("/board/insertBoard.do")							
	public ModelAndView boardInsert(Board board, ModelAndView mv, 
									@RequestParam(value="upFile", required = false) MultipartFile[] upFile,
									HttpServletRequest req) {
															// MultipartFile 객체는 commonsio에 의존하기 때문에 의존하는 객체를 bean으로 등록해줘야 정상적으로 작동한다
															// servlet-context.xml에서 bean등록
															// 파일을 여러개 등록했기 때문에 배열로 받는다
		log.info("파일명 : " + upFile[0].getOriginalFilename());
		log.info("파일크기 : {}", upFile[0].getSize()); 
		log.info("파일명 : " + upFile[1].getOriginalFilename());
		log.info("파일크기 : {}", upFile[1].getSize()); 
		
		
		// 넘어온 파일들을 폴더에 저장
		// 저장할 실제 경로를 받아야한다
		// request를 통해 가져오기 위해 매개변수로 HttpServletRequest를 받는다
		String path = req.getServletContext().getRealPath("/resources/upload/board/"); // session에 저장된 아이디를 이용하면 아이디별 파일을 만들어서 저장할 수 있다
		
		// 디렉토리가 없으면 자동으로 만들어지도록
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		// 파일 업로드 처리하기 ( 다중 파일 )
		for(MultipartFile f : upFile) {
			
			// 파일이 있다면
			if(!f.isEmpty()) {
				
				// 오리지날 파일명
				String originalFilename = f.getOriginalFilename();
				// 확장자 ( .jpg ) 
				String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
				// rename규칙설정
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int randomNum = (int)(Math.random()*10000);
				String reName = sdf.format(System.currentTimeMillis()) + "_" + randomNum + ext;
				
				// rename된 파일로 업로드
				try {
					f.transferTo(new File(path+reName));
					// 여기까지 실행된다는건 파일이 저장되었다는 뜻 
					// -> board객체와 attachment 객체에 original파일명과 rename된 파일명을 저장 
					// board객체의 attachments 필드는 이미 객체는 생성되어있지만 값은 없는 상태이므로 getter로 접근 가능 
					board.getAttachments().add(Attachment.builder().originalFileName(originalFilename).renameFileName(reName).build());
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
			
		} // f객체가 바뀌면서 반복
		
		
//		log.info("{}", board);
//		
//		int result = service.insertBoard(board);
//		
//		mv.setViewName("redirect:/board/boardList.do");
//		return mv;
		
		String msg = "등록성공";
		
		try {
			service.insertBoard(board);
		} catch (RuntimeException e) {
			msg = "등록실패";
			// 등록 실패시 이미 저장되어버린 파일 삭제시키는 로직
		}
		
		mv.addObject("msg", msg);
		mv.addObject("loc", "/board/boardList.do");
		
		mv.setViewName("common/msg");
		return mv;
		
	}
	
	
	
	
	
	
	
	
	@RequestMapping("/board/fileDownload.do")
	public void fileDownload(@RequestParam(value="oriname") String oriname,
											@RequestParam(value="rename") String rename,
											HttpServletRequest req,
											HttpServletResponse resp,
											@RequestHeader(value="user-agent") String header) {
		
		String path = req.getServletContext().getRealPath("/resources/upload/board/");
		File saveFile = new File(path + rename);
		
		BufferedInputStream bis = null;
		ServletOutputStream sos = null;
		
		try {
			bis = new BufferedInputStream(new FileInputStream(saveFile)); // 파일을 byte단위로 읽어옴 
			sos = resp.getOutputStream();
			
			
			boolean isMS = header.indexOf("Trident") != -1 || header.indexOf("MSIE") != -1;
			String encodeStr = "";
			if(isMS) {
				encodeStr = URLEncoder.encode(oriname, "UTF-8");
				encodeStr = encodeStr.replaceAll("\\", "%20");
			} else {
				encodeStr = new String(oriname.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			
			resp.setContentType("application/octet-stream;charset=utf-8");
			resp.setHeader("Content-Disposition", "attachment;filename=\"" + encodeStr + "\"");
				
				int read = 1;
				while((read=bis.read())!= -1) {
					
					sos.write(read);
				}
				
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			try {
				bis.close();
				sos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
}
