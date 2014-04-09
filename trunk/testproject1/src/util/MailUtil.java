package util;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailUtil implements SendMailService {

	private MailSender mailSender;
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(String email) {
		// TODO Auto-generated method stub
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setSubject("테스트 메일");
		msg.setText("테스트 메일입니다.");
		msg.setFrom("libedi@gmail.com");
		msg.setTo("libedi@gmail.com");
		
		try{
			mailSender.send(msg);
		} catch(MailException e){
			e.printStackTrace();
		}
	}

}
