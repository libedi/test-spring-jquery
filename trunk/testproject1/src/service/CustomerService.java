package service;

import java.sql.SQLException;

import vo.CustomerVO;

public interface CustomerService {

	public void register(CustomerVO cvo) throws SQLException;

}
