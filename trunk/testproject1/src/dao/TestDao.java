package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import vo.FileVO;

public interface TestDao {

	/**
	 * 파일 업로드
	 * @param java.util.Map
	 * @throws SQLException
	 */
	public void fileUpload(Map param) throws SQLException;
	
	/**
	 * 업로드할 파일 파라미터 셋팅
	 * @param filePath
	 * @return
	 * @throws SQLException
	 */
	public Map getUploadParam(String filePath) throws SQLException;
	
	/**
	 * 파일 정보 가져오기
	 * @param fileId
	 * @return
	 * @throws SQLException
	 */
	public Map getFileInfo(int fileId) throws SQLException;

	/**
	 * 파일 리스트 가져오기
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public List<FileVO> getFileList(int page) throws SQLException;

}
