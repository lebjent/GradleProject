package board.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import board.dto.BoardDTO;
import board.mapper.BoardMapper;
@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardDTO> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }
    
    //게시판 작성
	@Override
	public void insertBoard(BoardDTO bDTO) throws Exception {
	
		boardMapper.insertBoard(bDTO);
		
	}
	
	//게시판 상세보기
	@Override
	public BoardDTO boardDetail(int boardIdx) throws Exception {
		
		//게시글 상세보기시 조회수 업데이트
		boardMapper.hitCount(boardIdx);
		
		return boardMapper.boardDetail(boardIdx);
	}
	
	//게시글 업데이트
	@Override
	public void updateBoard(BoardDTO bDTO) throws Exception {
		
		boardMapper.updateBoard(bDTO);
	}
	
	//게시글 삭제
	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
	}
	
	
	
}
