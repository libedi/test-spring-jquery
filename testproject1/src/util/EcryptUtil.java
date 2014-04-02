package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EcryptUtil {
	
	public String encryptToSha(String pw){
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
		System.out.print("Byte : ");
		for(int i=0; i<ba.length; i++){
			System.out.print(ba[i]);
		}
		
		return sb.toString();
	}
	
	
	
}
