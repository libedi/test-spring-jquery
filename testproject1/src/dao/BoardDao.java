package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import vo.BoardVO;

public interface BoardDao {

	public ArrayList<BoardVO> getBoardList() throws SQLException;

	public void register(BoardVO bvo) throws SQLException;

}
