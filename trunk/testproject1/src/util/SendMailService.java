package util;

import java.util.HashMap;

import vo.MailVO;

public interface SendMailService {
	public void sendEmail(MailVO mailVO);
	public void sendEmail(String email);
}
