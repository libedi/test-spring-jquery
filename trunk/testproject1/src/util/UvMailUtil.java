package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 파 일 명 : UvMailUtil.java<BR>
 * 기 능 : 메일발송 <BR>
 * 최초 작성일 : 2012/07/03 <BR>
 * Comments : <BR>
 * 수정내역 : <BR>
 * @version 1
 * @author 윤재오
 */
/**
 * @author Yunjo
 *
 */

public class UvMailUtil {
	public static boolean SendMail(String[] toMail, String Subject, String Contents) {
		boolean rtn = false;
		
		try {
			JavaMailSenderImpl mailImpl = new JavaMailSenderImpl();
			Properties javaMailProperties = new Properties();
			
			//MailSender mailSender = new DummyMailSender();
			
			MimeMessage mailMessage = mailImpl.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mailMessage, true /* multipart */, "UTF-8"); 
			message.setTo(toMail);
			message.setFrom("수지원넷<newjuso@sujiewon.com>");
			message.setSubject(Subject); 
			message.setText(Contents, true);
			
			javaMailProperties.put("mail.transport.protocol", "smtps");
			javaMailProperties.put("mail.smtp.host", "mail.sujiewon.com");		
			//javaMailProperties.put("mail.smtp.host", "smtp.sujiewon.com");
			//javaMailProperties.put("mail.smtp.starttls.enable", "true");
			//javaMailProperties.put("mail.smtp.auth", "true");
				
			//javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			javaMailProperties.put("mail.smtp.socketFactory.fallback", "false");
			javaMailProperties.put("mail.smtp.socketFactory.port", "25");
			//javaMailProperties.put("mail.smtp.socketFactory.port", "587");
			
			Authenticator auth = new SMTPAuthenticator();
	/*		Authenticator auth1 =new Authenticator() {
				   protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				        return new javax.mail.PasswordAuthentication("sm85choi@gmail.com", "24845654aa");
				    }
			};*/
			Session mailSession = Session.getInstance(javaMailProperties, auth);
			   
			
			mailImpl.setJavaMailProperties(javaMailProperties);
			mailImpl.setSession(mailSession);
			mailImpl.send(mailMessage);
			//mailSender.send(mailMessage);
			
			rtn = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return rtn;
	}
	
	public static boolean SendMail(String toMail, String Subject, String Contents) {
		boolean rtn = false;
		
		try {
			JavaMailSenderImpl mailImpl = new JavaMailSenderImpl();
			Properties javaMailProperties = new Properties();
			
			//MailSender mailSender = new DummyMailSender();
			
			MimeMessage mailMessage = mailImpl.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mailMessage, true /* multipart */, "UTF-8"); 
			message.setTo(toMail);
			message.setFrom("수지원넷<newjuso@sujiewon.com>");
			message.setSubject(Subject); 
			message.setText(Contents, true);
			
			javaMailProperties.put("mail.transport.protocol", "smtps");
			javaMailProperties.put("mail.smtp.host", "mail.sujiewon.com");		
			//javaMailProperties.put("mail.smtp.host", "smtp.sujiewon.com");
			//javaMailProperties.put("mail.smtp.starttls.enable", "true");
			//javaMailProperties.put("mail.smtp.auth", "true");
				
			//javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			javaMailProperties.put("mail.smtp.socketFactory.fallback", "false");
			javaMailProperties.put("mail.smtp.socketFactory.port", "25");
			//javaMailProperties.put("mail.smtp.socketFactory.port", "587");
			
			Authenticator auth = new SMTPAuthenticator();
	/*		Authenticator auth1 =new Authenticator() {
				   protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				        return new javax.mail.PasswordAuthentication("sm85choi@gmail.com", "24845654aa");
				    }
			};*/
			Session mailSession = Session.getInstance(javaMailProperties, auth);
			   
			
			mailImpl.setJavaMailProperties(javaMailProperties);
			mailImpl.setSession(mailSession);
			mailImpl.send(mailMessage);
			//mailSender.send(mailMessage);
			
			rtn = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return rtn;
	}
	
	public static boolean OrganizationCDReqSendMail(String reqLibraryMobile, String toMail, String Subject, String Contents) {
		boolean rtn = false;
		
		try {
			JavaMailSenderImpl mailImpl = new JavaMailSenderImpl();
			Properties javaMailProperties = new Properties();

			MimeMessage mailMessage = mailImpl.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mailMessage, true /* multipart */, "UTF-8"); 
			message.setTo(toMail);
			message.setFrom(reqLibraryMobile+"고객<prtmail@phose.co.kr>");
			message.setSubject(Subject);
			message.setText(Contents, true);

			javaMailProperties.put("mail.transport.protocol", "smtps");
			javaMailProperties.put("mail.smtp.host", "mail.phose.co.kr");
			javaMailProperties.put("mail.smtp.auth", "true");
			javaMailProperties.put("mail.smtp.socketFactory.fallback", "false");
			javaMailProperties.put("mail.smtp.socketFactory.port", "25");

			Authenticator auth = new SMTPAuthenticator();
			Session mailSession = Session.getInstance(javaMailProperties, auth);

			mailImpl.setJavaMailProperties(javaMailProperties);
			mailImpl.setSession(mailSession);
			mailImpl.send(mailMessage);

			rtn = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return rtn;
	}
}