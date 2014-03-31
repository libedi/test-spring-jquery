package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import vo.BoardVO;

public class BoardDaoImpl extends SqlMapClientDaoSupport implements BoardDao {

	@Override
	public ArrayList<BoardVO> getBoardList() throws SQLException {
		// TODO Auto-generated method stub
		return (ArrayList<BoardVO>) this.getSqlMapClientTemplate().queryForList("board.getList");
	}

	@Override
	public void register(BoardVO bvo) throws SQLException {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("board.register", bvo);
	}

}
