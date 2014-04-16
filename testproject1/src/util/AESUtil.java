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
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtil {
	private static final int KEY_SIZE_128 = 128;
	// AES-CBC-256 암호화를 위한
	private static final int KEY_SIZE_256 = 256;
	private static final String SECRET_KEY_256 = "a2b4c6d8e0f2948b3840f5e7d6c8b0a1";
	private static final String IV_256 = "9c8476a8b0c2645a719fe2045d7a90ea";
	private static final String IV_128 = "2d9587b0c1d37a6e";

	/**
	 * generatorSecretKey()
	 * 
	 * @return Key 객체
	 * @throws NoSuchAlgorithmException
	 */
	public static Key generatorSecretKey() throws NoSuchAlgorithmException{
		Key secretKey = null;
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");	// Pseudo-Random Number Generator
		generator.init(KEY_SIZE_128, random);
		secretKey = generator.generateKey();
		return secretKey;
	}
	
	/**
	 * generatorSecretKey256()
	 * 
	 * @return	Key 객체
	 * @throws NoSuchAlgorithmException
	 */
	public static Key generatorSecretKey256() throws NoSuchAlgorithmException{
//		Key secretKey = new SecretKeySpec(SECRET_KEY_256.getBytes(), "AES");
		Key secretKey = null;
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");	// Pseudo-Random Number Generator
		/* 
		 * 32byte (256bit)를 사용하면 java.security.InvalidKeyException: Illegal key size 예외발생.
		 * Unlimited JCE Policy 를 다운받아 $JAVA_HOME/jre/lib/security 에 복사해 덮어쓰기.
		 */
//		generator.init(KEY_SIZE_256, random);
		generator.init(KEY_SIZE_128, random);
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
	public static String encryptByAES(String text, Key secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		String encryptedText = null;
		byte[] bytes = text.getBytes("UTF-8");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		encryptedText = new String(Base64.encodeBase64(cipher.doFinal(bytes)));
		return encryptedText;
	}
	
	/**
	 * 
	 * @param text
	 * @param secretKey
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String encryptByAES256(String text, Key secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
		String encryptedText = null;
		byte[] bytes = text.getBytes("UTF-8");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		/*
		 * Initialization Vector 값이 32byte (256bit) 사용하면
		 * java.security.InvalidAlgorithmParameterException: Wrong IV length: must be 16 bytes long 예외발생.
		 * Unlimited JCE Policy 를 다운받아 $JAVA_HOME/jre/lib/security 에 복사해 덮어쓰기.
		 */
//		cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(IV_256.getBytes()));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(IV_128.getBytes()));
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
	public static String decryptByAES(String encryptedBase64Text, Key secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
		String decryptedText = null;
		byte[] bytes = Base64.decodeBase64(encryptedBase64Text.getBytes());
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		decryptedText = new String(cipher.doFinal(bytes), "UTF-8");
		return decryptedText;
	}
	
	/**
	 * 
	 * @param encryptedBase64Text
	 * @param secretKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String decryptByAES256(String encryptedBase64Text, Key secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
		String decryptedText = null;
		byte[] bytes = Base64.decodeBase64(encryptedBase64Text.getBytes());
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		/*
		 * Initialization Vector 값이 32byte (256bit) 사용하면
		 * java.security.InvalidAlgorithmParameterException: Wrong IV length: must be 16 bytes long 예외발생.
		 * Unlimited JCE Policy 를 다운받아 $JAVA_HOME/jre/lib/security 에 복사해 덮어쓰기.
		 */
//		cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(IV_256.getBytes()));
		cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(IV_128.getBytes()));
		decryptedText = new String(cipher.doFinal(bytes), "UTF-8");
		return decryptedText;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TEST
		String id = "libedi";
		String pw = "libedi02";
		
		try {
			Key secretKey128 = AESUtil.generatorSecretKey();
			Key secretKey256 = AESUtil.generatorSecretKey256();
			
			String enPw = AESUtil.encryptByAES(pw, secretKey128);
			System.out.println("Encoded Data : " + enPw);
			String dePw = AESUtil.decryptByAES(enPw, secretKey128);
			System.out.println("Decoded Data : " + dePw);
			
			String enPwCBC = AESUtil.encryptByAES256(pw, secretKey256);
			System.out.println("Encoded CBC Data : " + enPwCBC);
			String dePwCBC = AESUtil.decryptByAES256(enPwCBC, secretKey256);
			System.out.println("Decoded CBC Data : " + dePwCBC);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	}

}
