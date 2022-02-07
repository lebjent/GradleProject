package board.service;

import board.dto.BoardDTO;
import board.dto.BoardFileDTO;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {
	 
	//게시판 리스트
	public List<BoardDTO> selectBoardList() throws Exception;
	
	//게시판 작성
	public void insertBoard(BoardDTO bDTO,MultipartHttpServletRequest mhsr)throws Exception;
	
	//게시글 상세보기
	public BoardDTO boardDetail(int boardIdx)throws Exception;
	
	//게시판 업데이트
	public void updateBoard(BoardDTO bDTO) throws Exception;
	
	//게시판 삭제 처리
	public void deleteBoard(int boardIdx) throws Exception;
	
	//다운로드한 파일정보 불러오기
	BoardFileDTO boardFileInformation(int idx, int boardIdx) throws Exception;
	
}
