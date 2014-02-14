package dao;

import java.sql.SQLException;

import vo.CustomerVO;

public interface CustomerDao {

	public void register(CustomerVO cvo) throws SQLException;

}
