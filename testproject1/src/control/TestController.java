package control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import util.MailUtil;
import util.MimeMailUtil;
import util.RSAUtil;
import util.TestProperty;
import vo.FileVO;
import vo.MailVO;


public class TestController extends MultiActionController {
	
	private Log log = LogFactory.getLog(getClass());
	private TestService testService;
	private MailUtil mailUtil;
	private MimeMailUtil mimeMailUtil;
	private static int KEY_SIZE = 1024;
	
	public void setTestService(TestService testService) {
		this.testService = testService;
	}
	public void setMailUtil(MailUtil mailUtil) {
		this.mailUtil = mailUtil;
	}
	public void setMimeMailUtil(MimeMailUtil mimeMailUtil) {
		this.mimeMailUtil = mimeMailUtil;
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
	
	
	/**
	 * 파일 리스트 가져오기
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView getFileList(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		
		List<FileVO> resultList = new ArrayList<FileVO>();
		
		int page = Integer.parseInt( req.getParameter("page") );
		
		try {
			resultList = this.testService.getFileList(page);
			mv.addObject("list", resultList);
			mv.setViewName("file_download");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}
	
	public ModelAndView getProps(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		
		TestProperty test = new TestProperty();
		
		String p = test.getProperty("TEST_IP");
		System.out.println(p);
		
		mv.addObject("ip", p);
		mv.setViewName("view_ip");
		
		return mv;
	}
	
	/**
	 * 메일 발송 테스트
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView sendMail(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		
		MailVO paramMail = new MailVO();
		paramMail.setSubject("테스트 메일");
		paramMail.setText("테스트로 보내는 메일입니다.");
		paramMail.setFromEmail("libedi@gmail.com");
		paramMail.setToEmail("libedi@gmail.com");
		
		mailUtil.sendEmail(paramMail);
		
		mv.setViewName("mail_ok");
		
		return mv;
	}
	
	/**
	 * 메일 발송 테스트
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView sendMimeMail(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		
		MailVO paramMail = new MailVO();
		paramMail.setSubject("테스트 메일");
		paramMail.setText("테스트로 보내는 메일입니다.");
		paramMail.setFromEmail("libedi@gmail.com");
		paramMail.setToEmail("libedi@nate.com");
		
		mimeMailUtil.sendMail(paramMail);
		
		mv.setViewName("mail_ok");
		
		return mv;
	}
	
	/**
	 * loginForm
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView loginForm(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		
		String publicKeyModulus = null;
		String publicKeyExponent = null;
		
		KeyPair keyPair = null;
		
		try {
			keyPair = RSAUtil.generatorKeyPair();
			
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
			
			// 세션에 복호화에 필요한 비밀키를 넣어서 전송한다.
			HttpSession session = req.getSession();
			session.setAttribute("__rsaPrivateKey__", privateKey);
			
			// 공개키를 문자열로 변환하여 Modulus와 Exponent 값을 전달한다.
			RSAPublicKeySpec publicSpec = RSAUtil.getRSAPublicKeySpec(publicKey);
			
			publicKeyModulus = publicSpec.getModulus().toString(16);
			publicKeyExponent = publicSpec.getPublicExponent().toString(16);
			
			log.debug("Public Key Modulus : " + publicKeyModulus);
			log.debug("Public Key Exponent : " + publicKeyExponent);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		mv.addObject("publicKeyModulus", publicKeyModulus);
		mv.addObject("publicKeyExponent", publicKeyExponent);
		mv.setViewName("loginForm_rsa");
		
		return mv;
	}
	
	/**
	 * login
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();

		String result = "";
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession(true);
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		// 세션에서 개인키값을 제거 - 개인키 값이 유지되는 것을 방지
		session.removeAttribute("__rsaPrivateKey__");
		log.debug("username : "+ req.getParameter("securedUsername"));
		
		String decryptedUsername = null;
		String decryptedPassword = null;
		
		try {
			decryptedUsername = RSAUtil.decrypt(req.getParameter("securedUsername"), privateKey);
			decryptedPassword = RSAUtil.decrypt(req.getParameter("securedPassword"), privateKey);
			
			log.debug("Decrypted Username : " + decryptedUsername);
			log.debug("Decrypted Password : " + decryptedPassword);
		
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		if(username.equals(decryptedUsername) && password.equals(decryptedPassword)){
			result = "match";
		} else {
			result = "dismatch";
		}
		
		mv.addObject("result", result);
		mv.setViewName("login_rsa");
		
		return mv;
	}
	
}
