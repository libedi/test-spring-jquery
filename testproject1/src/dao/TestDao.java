package dao;

import java.sql.SQLException;
import java.util.Map;

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

}
