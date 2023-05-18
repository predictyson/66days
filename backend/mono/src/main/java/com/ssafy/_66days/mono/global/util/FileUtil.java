package com.ssafy._66days.mono.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileUtil {

	/***
	 * File upload logic
	 * @param image : 실제 업로드할 MultipartFile 객체
	 * @param path : application.yml 등록된 path를 @Value로 받아와서 넣어주시면 됩니다.
	 * @return String : db에 저장될 이름을 return합니다. 받아서 db에 저장해주시면 됩니다.
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String fileUpload(MultipartFile image, String path) throws IllegalStateException, IOException {
		// InetAddress : 부팅시 한번만 static으로 사용하지 않으면 성능이슈가 있다고 합니다.
		// String hostname = InetAddress.getLocalHost().getHostName();
		if (image != null) {
			String originalFileName = image.getOriginalFilename();
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String saveFolder = path + File.separator + today;
			
			File folder = new File(saveFolder);
			if (!folder.exists())
				folder.mkdirs();

			if (originalFileName != null && !originalFileName.isEmpty()) {
				String saveFileName = UUID.randomUUID().toString()
						+ originalFileName.substring(originalFileName.lastIndexOf('.'));
				log.debug("image save path : {}", saveFolder + "/" + saveFileName);
				image.transferTo(new File(folder, saveFileName));
				return today + File.separator + saveFileName;
			} else {
				throw new InputMismatchException("비어있는 파일입니다.");
			}
		} else {
			throw new InputMismatchException("비어있는 파일입니다.");
		}
	}
	
	/***
	 * File delete logic
	 * @param dbSaveImage : DB에 저장되어있는 image 경로
	 * @param path : application.yml 등록된 path를 @Value로 받아와서 넣어주시면 됩니다.
	 * @return
	 */
	public boolean fileDelete(String dbSaveImage, String path) {
		File file = new File(path + File.separator + dbSaveImage);
		return file.delete();
	}
}
