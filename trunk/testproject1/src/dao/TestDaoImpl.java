package dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import vo.FileVO;

public class TestDaoImpl extends SqlMapClientDaoSupport implements TestDao {

	private Log log = LogFactory.getLog(getClass());
	
	@Override
	public void fileUpload(Map param) throws SQLException {
		// TODO Auto-generated method stub
		
		this.getSqlMapClientTemplate().insert("test.fileUpload", param);
		this.getSqlMapClientTemplate().update("test.updateId");
	}

	@Override
	public Map getUploadParam(String filePath) throws SQLException {
		// TODO Auto-generated method stub
		HashMap param = new HashMap();
		int seq = ((Integer)this.getSqlMapClientTemplate().queryForObject("test.getId")).intValue();
		param.put("seq", seq);
		param.put("filePath", filePath);
		
		return param;
	}

	@Override
	public Map getFileInfo(int fileId) throws SQLException {
		// TODO Auto-generated method stub
		Map result = new HashMap();
		result = (Map)this.getSqlMapClientTemplate().queryForObject("test.getFileInfo", fileId);
		return result;
	}

	@Override
	public List<FileVO> getFileList(int page) throws SQLException {
		// TODO Auto-generated method stub
		return (List<FileVO>)this.getSqlMapClientTemplate().queryForList("test.getFileList");
	}

}
