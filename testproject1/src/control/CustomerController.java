package control;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import service.CustomerService;
import vo.CustomerVO;

public class CustomerController extends MultiActionController {

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public ModelAndView login(HttpSession session, CustomerVO cvo){
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
	
	/**
	 * 회원가입
	 */
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, CustomerVO cvo){
		ModelAndView mv = new ModelAndView();
		String msg = "";
		try{
			this.customerService.register(cvo);
			msg = "회원가입이 완료되었습니다.";
		} catch(SQLException e){
			e.printStackTrace();
			msg = "회원으로 등록되지 못하였습니다.";
		}
		mv.addObject("msg", msg);
		mv.setViewName("register_ok");
		
		return mv;
	}
	
	/**
	 * 고객정보확인
	 */
	public ModelAndView customerInfo(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
	
	/**
	 * 고객정보수정
	 */
	public ModelAndView customerModify(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
	
	/**
	 * 고객탈퇴
	 */
	public ModelAndView customerDelete(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
	
}
