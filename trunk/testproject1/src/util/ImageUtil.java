package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ImageUtil {

	private Log log = LogFactory.getLog(getClass());
	
	public static final int RATIO = 0;
    public static final int SAME = -1;
	
	public ImageUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 썸네일 이미지 생성.
	 * 
	 * @param srcPath 원본 이미지 파일 경로
	 * @param width 리사이징 너비 ( 0: 비율에 맞춰, -1: 원본대로)
	 * @param height 리사이징 높이 ( 0: 비율에 맞춰, -1: 원본대로)
	 * @return true, false
	 * @throws IOException 
	 */
	public boolean createThumbnail(String srcPath, int width, int height) throws IOException{
	
		File srcFile = new File(srcPath);
		
//		System.out.println("getAbsolutePath : " + srcFile.getAbsolutePath());
//		System.out.println("getAbsoluteFile : " + srcFile.getAbsoluteFile());
//		System.out.println("getCanonicalPath : " + srcFile.getCanonicalPath());
//		System.out.println("getCanonicalFile : " + srcFile.getCanonicalFile());
//		System.out.println("getName : " + srcFile.getName());
//		System.out.println("getPath : " + srcFile.getPath());
		
		String srcFileName = srcFile.getAbsolutePath();
		int extIndex = srcFileName.lastIndexOf(".");
		String fileName = srcFileName.substring(0, extIndex);	// . 전까지
		String suffix = srcFileName.substring(extIndex + 1).toLowerCase();	// . 이후로
//		System.out.println("fileName : " + fileName);
//		System.out.println("file ext : " + fileExt);
		
		if(srcFile.isFile() && !(suffix.equals("jpg") || suffix.equals("jpeg") || 
			suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif")) ){
			
			log.debug("이미지 파일이 아닙니다.");
			return false;
		}
		File destFile = new File(fileName + "_thumb." + suffix);
		
		Image srcImg = null;
		
		if(suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif")){
			srcImg = ImageIO.read(srcFile);
		} else {
			// BMP가 아닌 경우 ImageIcon을 활용해서 Image 생성.
			// 이렇게 하는 이유는 getScaleInstance를 통해 구한 이미지를
			// PixelGrabber.grabPixel()로 리사이즈할때
			// 빠르게 처리하기 위함이다.
			srcImg = new ImageIcon(srcFile.toURI().toURL()).getImage();
		}
		
		int srcWidth = srcImg.getWidth(null);
		int srcHeight = srcImg.getHeight(null);
		int destWidth = -1, destHeight = -1;

		// 입력값이 -1 이면 원본사이즈 적용
		if(width == SAME){
			destWidth = srcWidth;
		} else if(width > 0) {
			destWidth = width;
		}
		
		if(height == SAME){
			destHeight = srcHeight;
		} else if(height > 0) {
			destHeight = height;
		}
		
		if(width == RATIO && height == RATIO){
			destWidth = srcWidth;
			destHeight = srcHeight;
		} else if(width == RATIO){		// 너비/높이 둘중 하나가 0 값이면 한쪽 비율에 맞춰 리사이징.
			double ratio = (double)destHeight / (double)srcHeight;
			destWidth = (int)((double)srcWidth * ratio);
		} else if(height == RATIO){
			double ratio = (double)destWidth / (double)srcWidth;
			destHeight = (int)((double)srcHeight * ratio);
		}
		
		Image targetImg = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH);
		int pixels[] = new int[destWidth * destHeight];
		PixelGrabber pg = new PixelGrabber(targetImg, 0, 0, destWidth, destHeight, pixels, 0, destWidth);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
		
		BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
		destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);
		ImageIO.write(destImg, suffix, destFile);
		
		log.debug("create thumbnail image!");
		return true;
	}
}
