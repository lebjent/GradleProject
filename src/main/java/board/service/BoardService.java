package board.service;

import board.dto.BoardDTO;

import java.util.List;

public interface BoardService {
	
	 List<BoardDTO> selectBoardList() throws Exception;
	
}
