package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EcryptUtil {
	
	public String encryptToSha1(String pw){
		byte[] ba = pw.getBytes();
		MessageDigest md = null;
		
		try{
			md = MessageDigest.getInstance("SHA1");
		} catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		if(md == null){
			System.out.println("null");
		}
		md.reset();
		md.update(ba);
		
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<digest.length; i++){
			sb.append(Integer.toHexString(0xFF & digest[i]));
		}
		System.out.println("PW : " + pw);
		
		return sb.toString();
	}
	
	public static void main(String args[]){
		EcryptUtil eu = new EcryptUtil();
		String password = "abcd1234";
		String enc = eu.encryptToSha1(password);
		System.out.println(enc);
		System.out.println(enc.length());
		System.out.println(enc.equals("7ce0359f12857f2a90c7de465f40a95f1cb5da9"));
	}
	
}
