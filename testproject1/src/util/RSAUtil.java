package util;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

/**
 * RSA 비대칭키 암호화 알고리즘 사용을 위한 클래스<br/>
 * - 공개키(Public Key)로 암호화를 하고,<br/>
 * - 개인키(Private Key)로 복호화를 한다.
 * @author Park Sang Jun
 *
 */
public class RSAUtil {
	private static final String RSA = "RSA";
	private static final int KEY_SIZE = 1024;
	
	public static KeyPair generatorKeyPair(){
		KeyPair keyPair = null;
		
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA);
			generator.initialize(KEY_SIZE);
			keyPair = generator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return keyPair;
	}
	
	/**
	 * Public Key로 암호화한 후,
	 * 결과로 출력된 byte 배열을 BASE64로 인코딩하여 String으로 변환하여 전송
	 * 
	 * @param text 암호화할 텍스트
	 * @param publicKey	RSA 공개키
	 * @return	Base64로 인코딩된 암호화 문자열
	 */
	public static String encrypt(String text, PublicKey publicKey){
		String encryptedText = null;
		byte[] bytes = text.getBytes();
		try {
			Cipher cipher = Cipher.getInstance(RSA);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			encryptedText = new String(Base64.encodeBase64(cipher.doFinal(bytes)));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return encryptedText;
	}
	
	/**
	 * Base64로 인코딩된 문자열을 받아 decode 시킨 후,<br/>
	 * RSA 개인키(Private Key)를 이용하여 암호화된 문자열을 복호화하여 반환
	 * @param encryptedBase64Text	Base64로 인코딩된 암호화 문자열
	 * @param privateKey	RSA 개인키
	 * @return	복호화된 텍스트
	 */
	public static String decrypt(String encryptedBase64Text, PrivateKey privateKey){
		String decryptedText = null;
		byte[] bytes = Base64.decodeBase64(encryptedBase64Text.getBytes());
		try {
			Cipher cipher = Cipher.getInstance(RSA);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			decryptedText = new String(cipher.doFinal(bytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return decryptedText;
	}
	
	/**
	 * RSA 공개키로부터 RSAPublicKeySpec 객체를 생성
	 * 
	 * @param publicKey	공개키
	 * @return	RSAPublicKeySpec
	 */
	public static RSAPublicKeySpec getRSAPublicKeySpec(PublicKey publicKey){
		RSAPublicKeySpec spec = null;
		try {
			spec = KeyFactory.getInstance(RSA).getKeySpec(publicKey, RSAPublicKeySpec.class);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return spec;
	}
	
	/**
	 * RSA 개인키로부터 RSAPrivateKeySpec 객체를 생성
	 * 
	 * @param privateKey	개인키(비밀키)
	 * @return	RSAPrivateKeySpec
	 */
	public static RSAPrivateKeySpec getRSAPrivateKeySpec(PrivateKey privateKey){
		RSAPrivateKeySpec spec = null;
		try {
			spec = KeyFactory.getInstance(RSA).getKeySpec(privateKey, RSAPrivateKeySpec.class);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return spec;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
