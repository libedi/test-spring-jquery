package dao;

import java.sql.SQLException;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import vo.CustomerVO;

public class CustomerDaoImpl extends SqlMapClientDaoSupport implements CustomerDao {

//	private SqlMapClient sqlMapClient = this.getSqlMapClient();
	
	@Override
	public void register(CustomerVO cvo) throws SQLException {
		// TODO Auto-generated method stub
		this.getSqlMapClient().insert("customer.insert", cvo);
	}

}
