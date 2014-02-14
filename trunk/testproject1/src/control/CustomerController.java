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
	 * ȸ������
	 */
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, CustomerVO cvo){
		ModelAndView mv = new ModelAndView();
		String msg = "";
		try{
			this.customerService.register(cvo);
			msg = "ȸ�������� �Ϸ�Ǿ����ϴ�.";
		} catch(SQLException e){
			e.printStackTrace();
			msg = "ȸ������ ��ϵ��� ���Ͽ����ϴ�.";
		}
		mv.addObject("msg", msg);
		mv.setViewName("register_ok");
		
		return mv;
	}
	
	/**
	 * ������Ȯ��
	 */
	public ModelAndView customerInfo(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
	
	/**
	 * ����������
	 */
	public ModelAndView customerModify(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
	
	/**
	 * ��Ż��
	 */
	public ModelAndView customerDelete(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
	
}
