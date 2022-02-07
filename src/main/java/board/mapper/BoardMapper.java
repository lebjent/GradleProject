package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;

import board.dto.BoardDTO;
import board.dto.BoardFileDTO;

@Mapper
public interface BoardMapper {
	
	//게시판 리스트
	public List<BoardDTO> selectBoardList() throws Exception;
	
	//게시판 작성
	public void insertBoard(BoardDTO bDTO) throws Exception;
	
	//게시글 상세보기
	public BoardDTO boardDetail(int boardIdx)throws Exception;
	
	//게시판 조회수 업데이트
	public void hitCount(int boardIdx)throws Exception;
	
	//게시판 업데이트
	public void updateBoard(BoardDTO bDTO) throws Exception;
	
	//게시판 삭제 처리
	public void deleteBoard(int boardIdx) throws Exception;
	
	//첨부파일 데이터 저장
	public void insertBoardFileList(List<BoardFileDTO> list)throws Exception;
	
	//첨부파일 리스트 불러오기
	public List<BoardFileDTO> boardFileList(int boardIdx) throws Exception;
	
	//다운로드한 파일정보 불러오기
	BoardFileDTO boardFileInformation(@Param("idx") int idx,@Param("boardIdx") int boardIdx) throws Exception;

}
