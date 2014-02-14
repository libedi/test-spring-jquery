/**
 * 
 */
package test;

import java.io.IOException;

import util.ImageUtil;


/**
 * @author �ڻ���
 *
 */
public class TestImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImageUtil img = new ImageUtil();
		
		try {
			if(img.createThumbnail("c:/javawork/image/7.jpg", 0, 500)){
				System.out.println("create thumbnail image!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
