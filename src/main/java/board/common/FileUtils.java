package board.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.dto.BoardFileDTO;
@Component
public class FileUtils {
	
	//첨부파일 정보를 리스트에 담는 메서드
	public List<BoardFileDTO> parseFileInfo(int boardIdx,MultipartHttpServletRequest mhsr)throws Exception{
		
		//첨부파일이 없는경우 null값을 리턴
		if(ObjectUtils.isEmpty(mhsr)) {
			return null;
		}
		
		List<BoardFileDTO> fileList = new ArrayList<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/"+current.format(format);
		File file = new File(path);
		
		//폴더가 존재하지않으면 폴더를 생성해주는 메서드 
		if(file.exists()==false) {
			file.mkdirs();
		}
		
		Iterator<String> iterator = mhsr.getFileNames();
		
		String newFileName;//새로운 파일이름명
		String originalFileExtension;
		String contentType;
		
		while (iterator.hasNext()) {
			List<MultipartFile> list = mhsr.getFiles(iterator.next());
			for(MultipartFile multipartFile : list) {
				
				if(multipartFile.isEmpty() == false) {
					contentType = multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)) {
						break;
					}else {
						if(contentType.contains("image/jpeg")) {
							originalFileExtension=".jpg";
						}else if(contentType.contains("image/png")) {
							originalFileExtension=".png";
						}else if(contentType.contains("image/gif")) {
							originalFileExtension=".gif";
						}else {
							break;
						}
					}
					//UUID 적용
					UUID uuid = UUID.randomUUID();
					newFileName = uuid.toString() + originalFileExtension;
					BoardFileDTO boardFile = new BoardFileDTO();
					boardFile.setBoardIdx(boardIdx);
					boardFile.setFileSize(multipartFile.getSize());
					boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
					boardFile.setStoredFilePath(path+"/"+newFileName);
					fileList.add(boardFile);
					
					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
					
				}
			}
		}
		return fileList;
	}
}
