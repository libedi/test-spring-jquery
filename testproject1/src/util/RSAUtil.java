package util;

import java.io.UnsupportedEncodingException;
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
	
	/**
	 * generatorKeyPair()
	 * 
	 * @return	KeyPair 객체
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair generatorKeyPair() throws NoSuchAlgorithmException{
		KeyPair keyPair = null;
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA);
		generator.initialize(KEY_SIZE);
		keyPair = generator.generateKeyPair();
		return keyPair;
	}
	
	/**
	 * Public Key로 암호화한 후,
	 * 결과로 출력된 byte 배열을 BASE64로 인코딩하여 String으로 변환하여 전송
	 * 
	 * @param text 암호화할 텍스트
	 * @param publicKey	RSA 공개키
	 * @return	Base64로 인코딩된 암호화 문자열
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String encrypt(String text, PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
		String encryptedText = null;
		byte[] bytes = text.getBytes("UTF-8");
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		encryptedText = new String(Base64.encodeBase64(cipher.doFinal(bytes)));
		
		return encryptedText;
	}
	
	/**
	 * Base64로 인코딩된 문자열을 받아 decode 시킨 후,<br/>
	 * RSA 개인키(Private Key)를 이용하여 암호화된 문자열을 복호화하여 반환
	 * @param encryptedBase64Text	Base64로 인코딩된 암호화 문자열
	 * @param privateKey	RSA 개인키
	 * @return	복호화된 텍스트
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String decrypt(String encryptedBase64Text, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
		String decryptedText = null;
		byte[] bytes = Base64.decodeBase64(encryptedBase64Text.getBytes());
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		decryptedText = new String(cipher.doFinal(bytes), "UTF-8");
		return decryptedText;
	}
	
	/**
	 * RSA 공개키로부터 RSAPublicKeySpec 객체를 생성
	 * 
	 * @param publicKey	공개키
	 * @return	RSAPublicKeySpec
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public static RSAPublicKeySpec getRSAPublicKeySpec(PublicKey publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
		RSAPublicKeySpec spec = null;
		spec = KeyFactory.getInstance(RSA).getKeySpec(publicKey, RSAPublicKeySpec.class);
		return spec;
	}
	
	/**
	 * RSA 개인키로부터 RSAPrivateKeySpec 객체를 생성
	 * 
	 * @param privateKey	개인키(비밀키)
	 * @return	RSAPrivateKeySpec
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public static RSAPrivateKeySpec getRSAPrivateKeySpec(PrivateKey privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException{
		RSAPrivateKeySpec spec = null;
		spec = KeyFactory.getInstance(RSA).getKeySpec(privateKey, RSAPrivateKeySpec.class);
		return spec;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
