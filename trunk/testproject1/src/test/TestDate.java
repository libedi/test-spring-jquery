/**
 * 
 */
package test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 박상준
 *
 */
public class TestDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("current time mil : " + System.currentTimeMillis());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = sdf.format(new Date());
		
		System.out.println(time);
	}

}
