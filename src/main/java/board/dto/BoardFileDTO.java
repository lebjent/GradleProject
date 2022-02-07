package board.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Alias("BoardFileDTO")
@Data
public class BoardFileDTO {

	private int idx;
	private int boardIdx;
	private String originalFileName;
	private String storedFilePath;
	private long fileSize;
	
}
