package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class TestProperty {
	
	private static final Properties props = new Properties();
	
	private String FILE = "test.properties";
	
	public String getProperty(String keyName){
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		
		if(cl == null){
			cl = ClassLoader.getSystemClassLoader();
		}
		URL url = cl.getResource("");
		
//		System.out.println(url.getPath());
		
		String value = null;

		Properties props = new Properties();
		FileInputStream fis = null;
		try {
			
//			fis = new FileInputStream(FILE);
			fis = new FileInputStream(url.getPath() + File.separator + FILE);
			
			props.load(new BufferedInputStream(fis));
			value = props.getProperty(keyName).trim();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(fis != null){
					fis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return value;
	}
	
	public String loadProperty(String keyName){
		
		String value = "";
		try {		
			if( getClass().getClassLoader().getResourceAsStream(FILE) != null ) {
				props.load( getClass().getClassLoader().getResourceAsStream(FILE) );
			} else {
				throw new Exception("Property 파일의 위치를 확인할 수 없습니다");			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		value = props.getProperty(keyName).trim();
		
		return value;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestProperty test = new TestProperty();
//		System.out.println(test.getProperty("TEST_IP"));
		System.out.println(test.loadProperty("TEST_IP"));
	}

}
