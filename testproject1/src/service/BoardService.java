package service;

import java.sql.SQLException;
import java.util.ArrayList;

import vo.BoardVO;

public interface BoardService {

	public ArrayList<BoardVO> getBoardList() throws SQLException;

	public void register(BoardVO bvo) throws SQLException;

}
