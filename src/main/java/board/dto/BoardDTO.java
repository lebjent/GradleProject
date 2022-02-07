package board.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;
	@Alias("BoardDTO")
	@Data
	public class BoardDTO{
	    private int boardIdx;
	    private String title;
	    private String contents;
	    private int hitCnt;
	    private String creatorId;
	    private LocalDateTime createdDatetime;
	    private String updaterId;
	    private LocalDateTime updatedDatetime;
	    
	    private List<BoardFileDTO> fileList;
	
}
