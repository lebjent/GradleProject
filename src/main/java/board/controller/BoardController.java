package board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import board.dto.BoardDTO;
import board.dto.BoardFileDTO;
import board.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//게시글 리스트로 가기
	@GetMapping("/board/openBoardList")
	public ModelAndView openBoardList()throws Exception{
		
		log.info("게시글 리스트로 이동");
		
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		List<BoardDTO> list =boardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	//게시판 작성 뷰 이동
	@GetMapping("/board/openBoardWrite")
	public String openBoradWrite()throws Exception{
		
		log.info("게시글 작성");
		
		return "board/boardWrite";
	}
	
	//게시글 테이블에 저장 + 첨부파일 저장
	@PostMapping("/board/insertBoard")
	public String insertBoard(BoardDTO bDTO,MultipartHttpServletRequest mhsr)throws Exception{
		
		boardService.insertBoard(bDTO,mhsr);
		
		return "redirect:/board/openBoardList";
	}
	
	
	//게시글 상세보기
	@GetMapping("/board/openBoardDetail")
	public ModelAndView openBoardDetail(@RequestParam("boardIdx") int boardIdx)throws Exception{
		
		log.info("게시글 상세보기");
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("board",boardService.boardDetail(boardIdx));
		
		mv.setViewName("/board/boardDetail");
		
		return mv;
		
	}
	
	//게시판 삭제하기
	@PostMapping("/board/deleteBoard")
	public String deleteBoard(@RequestParam("boardIdx")int boardIdx)throws Exception{
		
		boardService.deleteBoard(boardIdx);
		
		return "redirect:/board/openBoardList";
	}
	
	//게시판 업데이트
	@PostMapping("/board/updateBoard")
	public String updateBoard(BoardDTO bDTO)throws Exception{
		
		boardService.updateBoard(bDTO);
		
		return "redirect:/board/openBoardList";
	}
	
	//첨부파일 다운로드
	@GetMapping("/board/downloadBoardFile")
	public void downloadBoardFile(@RequestParam int idx,@RequestParam int boardIdx, HttpServletResponse response)throws Exception{
		
		BoardFileDTO boardFile = boardService.boardFileInformation(idx, boardIdx);
		
		if(ObjectUtils.isEmpty(boardFile) == false) {
			
			String fileName = boardFile.getOriginalFileName();
			byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8")+"\";");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			
		}
	}
	
}
