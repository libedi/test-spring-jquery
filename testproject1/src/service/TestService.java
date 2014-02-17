package service;

import java.sql.SQLException;
import java.util.List;

import vo.FileVO;

public interface TestService {

	public void fileUpload(String filePath) throws SQLException;

	public List<FileVO> getFileList(int page) throws SQLException;

}
