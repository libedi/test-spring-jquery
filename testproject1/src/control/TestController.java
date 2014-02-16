package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import service.TestService;
import util.FileUtil;
import util.ImageUtil;


public class TestController extends MultiActionController {
	
	private Log log = LogFactory.getLog(getClass());
	private TestService testService;
	
	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	public ModelAndView test(HttpServletRequest request, HttpServletResponse response){
//		System.out.println("start");
		log.debug("test class start");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("test");
		log.debug("test class end");
		return mv;
	}
	
	/**
	 * ajax test
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView ajax(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		
		String param = req.getParameter("id");
		
		log.debug("Parameter : " + param);
		
		param += "_ajaxResult";
		
		mv.addObject("resultValue", param);
		mv.setViewName("result");
		return mv;
	}
	
	/**
	 * json test
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView json(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		
		String param = req.getParameter("id");

		log.debug("parameter : " + param);
		
		param += "_jsonTest";
		
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		
		try {
			jsonObj.put("obj", param);
			
			for(int i=0; i<5; i++){
				jsonArr.put(param + i);
			}
			jsonObj.put("arr", jsonArr);
			
			log.debug("json object : " + jsonObj.get("obj"));
			log.debug("json array : " + jsonObj.get("arr"));
			log.debug("json : " + jsonObj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.addObject("resultValue", jsonObj);
		mv.setViewName("result");
		return mv;
	}
	
	
	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView fileUpload(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		
		log.debug("file Upload Start");
		
		String filePath = FileUtil.fileUploadController(req, "image", "upload");
		
		try {
			new ImageUtil().createThumbnail(filePath, 500, 0);
			this.testService.fileUpload(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("upload path : " + filePath);
		
		mv.addObject("msg", filePath);
		mv.setViewName("upload_ok");
		
		return mv;
	}
	
	/**
	 * Ajax 파일 업로드
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView ajaxFileUpload(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		
		log.debug("ajax file upload start");
		
		String filePath = FileUtil.fileUploadController(req, "image", "upload");
		
		try {
			this.testService.fileUpload(filePath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("upload path : " + filePath);
		
		mv.addObject("resultValue", filePath);
		mv.setViewName("result");
		
		return mv;
	}
	
	
}
