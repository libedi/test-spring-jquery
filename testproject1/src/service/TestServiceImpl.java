package service;

import java.sql.SQLException;
import java.util.Map;

import dao.TestDao;

public class TestServiceImpl implements TestService {

	private TestDao testDao;
	
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public void fileUpload(String filePath) throws SQLException {
		// TODO Auto-generated method stub
		Map param = this.testDao.getUploadParam(filePath);
		this.testDao.fileUpload(param);
	}

}
