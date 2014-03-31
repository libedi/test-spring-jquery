package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.BoardDao;
import vo.BoardVO;

public class BoardServiceImpl implements BoardService {

	private BoardDao boardDao;
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public ArrayList<BoardVO> getBoardList() throws SQLException {
		// TODO Auto-generated method stub
		return this.boardDao.getBoardList();
	}

	@Override
	public void register(BoardVO bvo) throws SQLException {
		// TODO Auto-generated method stub
		this.boardDao.register(bvo);
	}

}
