package board.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.dto.BoardDTO;
import board.mapper.BoardMapper;

public class BoardServiceImpl implements BoardService {
	
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardDTO> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }
	
}
