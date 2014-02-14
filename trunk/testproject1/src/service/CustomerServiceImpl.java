package service;

import java.sql.SQLException;

import vo.CustomerVO;
import dao.CustomerDao;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void register(CustomerVO cvo) throws SQLException {
		// TODO Auto-generated method stub
		this.customerDao.register(cvo);
	}
	
	
}
