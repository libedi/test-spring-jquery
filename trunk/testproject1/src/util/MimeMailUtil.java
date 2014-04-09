package util;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import vo.MailVO;

public class MimeMailUtil {

	private JavaMailSender mailSender;
//	private SimpleMailMessage simpleMailMessage;
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
//	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
//		this.simpleMailMessage = simpleMailMessage;
//	}
	
	public void sendMail(MailVO mailVO){
		MimeMessage msg = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
			
			helper.setFrom(mailVO.getFromEmail(), "밥상");		// 수취인에게 보내는 사람의 이름을 표시할 수 있다.
//			helper.setTo(mailVO.getToEmail());
			helper.setTo(new InternetAddress(mailVO.getToEmail(), "단무지", "UTF-8"));		// 보낸편지함에 표시될 수취인의 이름을 정할 수 있다.
			helper.setSubject(mailVO.getSubject());
			String htmlContent = "<table border><tr><td>테스트입니다.</td></tr></table><br/>반갑습니다.";
			helper.setText(htmlContent, true);
			// javax.activation.DataSource
//			DataSource dataSource = new FileDataSource("d:/테스트.txt");		// FileDataSource를 이용하여 첨부할 수도 있다.
			FileSystemResource file = new FileSystemResource("d:/테스트.txt");
			helper.addAttachment(MimeUtility.encodeText(file.getFilename(), "UTF-8", "B"), file);
			// MimeUtility를 이용하여 ASCII가 아닌 값의 파일이름을 인코딩한다. ASCII인 경우는 "Q"로 인코딩.
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mailSender.send(msg);
		
	}
}
