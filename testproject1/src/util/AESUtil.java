package util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtil {
	private static final int KEY_SIZE = 128;

	/**
	 * generatorSecureKey()
	 * 
	 * @return SecureKey 객체
	 * @throws NoSuchAlgorithmException
	 */
	public static Key generatorSecureKey() throws NoSuchAlgorithmException{
		Key secretKey = null;
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");	// Pseudo-Random Number Generator
		generator.init(KEY_SIZE, random);
		secretKey = generator.generateKey();
		return secretKey;
	}
	
	/**
	 * encryptByAES()
	 * 
	 * @param text
	 * @param secretKey
	 * @return	Base64로 인코딩된 AES로 암호화된 텍스트
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String encryptByAES(String text, SecretKey secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		String encryptedText = null;
		byte[] bytes = text.getBytes("UTF-8");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		encryptedText = new String(Base64.encodeBase64(cipher.doFinal(bytes)));
		return encryptedText;
	}
	
	public String encryptByAESCBC(String text, SecretKey secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
		String encryptedText = null;
		byte[] bytes = text.getBytes("UTF-8");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] iv = cipher.getIV();		// Initial Vector
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
		encryptedText = new String(Base64.encodeBase64(cipher.doFinal(bytes)));
		return encryptedText;
	}
	
	/**
	 * decryptByAES()
	 * 
	 * @param encryptedBase64Text
	 * @param secretKey
	 * @return	복호화된 텍스트
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String decryptByAES(String encryptedBase64Text, SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
		String decryptedText = null;
		byte[] bytes = Base64.decodeBase64(encryptedBase64Text.getBytes());
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		decryptedText = new String(cipher.doFinal(bytes), "UTF-8");
		return decryptedText;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
