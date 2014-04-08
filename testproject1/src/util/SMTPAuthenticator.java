package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator
{
	public PasswordAuthentication getPasswordAuthentication()
	{
		String username = "prtmail";
		String password = "wjdtkddl";
		return new PasswordAuthentication(username, password); 
	}
}