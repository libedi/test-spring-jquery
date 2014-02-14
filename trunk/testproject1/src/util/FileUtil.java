package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtil {
	
	private static final String UPLOAD_PATH = "c:/javawork/file";
	
//	private static FileUtil instance;
	public FileUtil() {
		super();
		// TODO Auto-generated constructor stub
	}
//	public static FileUtil getInstance(){
//		if(instance == null){
//			instance = new FileUtil();
//		}
//		return instance;
//	}

	/**
	 * 단일 파일 업로드
	 * 
	 * @param req : request
	 * @param destinationDir : 업로드 디렉토리
	 * @param fileTag : 화면단 태그명
	 * @return
	 */
	public static String fileUploadController(HttpServletRequest req, String destinationDir, String fileTag){
		String filePath = "";
		
		// request 를 MultipartHttpServletRequset 로 바인딩.
		MultipartHttpServletRequest multipartReq = (MultipartHttpServletRequest) req;
		
		MultipartFile file = multipartReq.getFile(fileTag);
		
		String fileName = file.getOriginalFilename();
		
		filePath = UPLOAD_PATH + System.getProperty("file.separator") + destinationDir +
				System.getProperty("file.separator") + fileName;
		
		File destFile = new File(filePath);
		destFile.getParentFile().mkdirs();
		
		try {
			destFile.createNewFile();
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filePath;
	}
}
