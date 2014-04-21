package org.ricetable.testexcel;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

public class TestExcel {

	ExcelUtil util = null;
	
	@Test
	public void testMakeObject(){
		util = ExcelUtil.getInstance();
		assertEquals(ExcelUtil.getInstance(), util);
	}
	
	public void testReadExcel(){
		testMakeObject();
		String path = "c:/sujiewon/test/test.xlsx";
		try {
			ArrayList<ArrayList<String>> list = util.readExcel(path);
			for(ArrayList<String> row : list){
				for(String cell : row){
					System.out.print(cell + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testTransFile(){
		testMakeObject();
		String path1 = "c:/sujiewon/test/test_100k.xlsx";
		String path2 = "c:/sujiewon/test/test_540k.xlsx";
		try {
			if(util.transferExcelToText(path2)){
				assertTrue("파일생성완료", true);
			}
//			if(util.transferExcelToText(path2)){
//				assertTrue("파일생성완료", true);
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
}
