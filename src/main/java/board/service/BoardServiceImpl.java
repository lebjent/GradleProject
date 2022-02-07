package board.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.common.FileUtils;
import board.dto.BoardDTO;
import board.dto.BoardFileDTO;
import board.mapper.BoardMapper;
@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
    @Autowired
    private BoardMapper boardMapper;
    
    @Autowired
    private FileUtils fileUtils;
    

    @Override
    public List<BoardDTO> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }
    
    //게시판 작성
	@Override
	public void insertBoard(BoardDTO bDTO, MultipartHttpServletRequest mhsr) throws Exception {
	
		boardMapper.insertBoard(bDTO);
		List<BoardFileDTO> list = fileUtils.parseFileInfo(bDTO.getBoardIdx(), mhsr);
		if(CollectionUtils.isEmpty(list) == false) {
			boardMapper.insertBoardFileList(list);
		}
	}
	
	//게시판 상세보기
	@Override
	public BoardDTO boardDetail(int boardIdx) throws Exception {
		
		//게시글 상세보기시 조회수 업데이트
		boardMapper.hitCount(boardIdx);
		
		BoardDTO bDTO = boardMapper.boardDetail(boardIdx);
		
		List<BoardFileDTO> fileList = boardMapper.boardFileList(boardIdx);
		
		bDTO.setFileList(fileList);
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
	
	//다운로드할 파일정보 불러오기
	@Override
	public BoardFileDTO boardFileInformation(int idx, int boardIdx) throws Exception {
		
		return boardMapper.boardFileInformation(idx, boardIdx);
	}
	
	
	
	
}
