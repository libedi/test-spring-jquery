package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EcryptUtil {

	/**
	 * SHA1 암호화
	 * 
	 * @param pw
	 * @return
	 */
	public String encryptToSha1(String pw){
		byte[] ba = pw.getBytes();
		MessageDigest md = null;
		
		try{
			md = MessageDigest.getInstance("SHA-256");
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
		System.out.println(enc.equals("e9cee71ab932fde863338d8be4de9dfe39ea049bdafb342ce659ec545b69ae"));
	}
}
