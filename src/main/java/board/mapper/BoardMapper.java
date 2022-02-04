package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	
	public List<BoardDTO> selectBoardList() throws Exception;
	
}
