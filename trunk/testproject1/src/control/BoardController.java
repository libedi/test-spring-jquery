package control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import service.BoardService;
import vo.BoardVO;

public class BoardController extends MultiActionController {

	private BoardService boardService;
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	/**
	 * 게시판 리스트 가져오기
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView getBoardList(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			list = this.boardService.getBoardList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject("list", list);
		mv.setViewName("board_list");
		
		return mv;
	}
	
	/**
	 * 게시판 등록
	 * @param req
	 * @param res
	 * @param bvo
	 * @return
	 */
	public ModelAndView boardRegister(HttpServletRequest req, HttpServletResponse res, BoardVO bvo){
		ModelAndView mv = null;
		
		try {
			this.boardService.register(bvo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv = new ModelAndView("register_ok", "result", "ok");
		
		return mv;
	}
}
