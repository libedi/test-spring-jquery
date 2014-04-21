package org.ricetable.testexcel;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel file handling class by POI library
 * @author Park Sang Jun
 *
 */
public class ExcelUtil {

	private static ExcelUtil instance = null;
	
	private ExcelUtil(){
	}
	
	public static ExcelUtil getInstance(){
		if(instance == null){
			synchronized(ExcelUtil.class){
				if(instance == null){
					instance = new ExcelUtil();
				}
			}
		}
		return instance;
	}

	/**
	 * Read excel file data
	 * 
	 * @param path	file path
	 * @return	2-dimension array list
	 * @throws Exception 
	 */
	public ArrayList<ArrayList<String>> readExcel(String path) throws Exception {
		if(path == null || path.equals("")){
			throw new FileNotFoundException();
		}
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		String fileExt = path.substring(path.lastIndexOf(".") + 1);
		if(fileExt != null && !path.isEmpty()){
			if(fileExt.toLowerCase().equals("xls")){				// xls file
				result = readXlsFile(path);
			} else if(fileExt.toLowerCase().equals("xlsx")){		// xlsx file
				result = readXlsxFile(path);
			} else {
				throw new Exception("invalid file type.(Not excel file)");
			}
		}
		return result;
	}

	/**
	 * Read xls file<br/>
	 * - before Office 2007
	 * 
	 * @param path	file path
	 * @return	2-dimension array list
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private ArrayList<ArrayList<String>> readXlsFile(String path) throws FileNotFoundException, IOException {
		POIFSFileSystem excel = new POIFSFileSystem(new FileInputStream(path));
		HSSFWorkbook workbook = new HSSFWorkbook(excel);
		return readWorkbook(workbook);
	}

	/**
	 * Read xlsx file
	 * 
	 * @param path	file path
	 * @return	2-dimension array list
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private ArrayList<ArrayList<String>> readXlsxFile(String path) throws FileNotFoundException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(path));
		return readWorkbook(workbook);
	}
	
	/**
	 * Read workbook<br/>
	 * - read only single sheet
	 * @param workbook	Workbook
	 * @return	2-dimension array list
	 */
	private ArrayList<ArrayList<String>> readWorkbook(Workbook workbook) {
		ArrayList<ArrayList<String>> resultRow = null;
		ArrayList<String> resultCell = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		int rows = 0;
		int cells = 0;
		
//		int sheetNum = workbook.getNumberOfSheets();
		sheet = workbook.getSheetAt(0);
		rows = sheet.getPhysicalNumberOfRows();
		
		resultRow = new ArrayList<ArrayList<String>>();
		for(int r=0; r < rows; r++){
			row = sheet.getRow(r);
			cells = row.getPhysicalNumberOfCells();
			
			resultCell = new ArrayList<String>();
			for(int c=0; c < cells; c++){
				cell = row.getCell(c);
				if(cell != null){
					switch(cell.getCellType()){
					case XSSFCell.CELL_TYPE_FORMULA :
						resultCell.add(cell.getCellFormula());
						break;
					case XSSFCell.CELL_TYPE_NUMERIC :
						resultCell.add((int)cell.getNumericCellValue() + "");
						break;
					case XSSFCell.CELL_TYPE_STRING :
						resultCell.add(cell.getStringCellValue());
						break;
					case XSSFCell.CELL_TYPE_BLANK :
						resultCell.add("[blank]");
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN :
						resultCell.add(cell.getBooleanCellValue() + "");
						break;
					}
				}
			}
			resultRow.add(resultCell);
		}
		
		return resultRow;
	}
	
	public boolean transferExcelToText(String path) throws Exception{
		long startTime = System.currentTimeMillis();
		if(path == null || path.equals("")){
			throw new FileNotFoundException();
		}
		
		String fileExt = path.substring(path.lastIndexOf(".") + 1);
		if(fileExt != null && !path.isEmpty()){
			Workbook workbook = null;
			if(fileExt.toLowerCase().equals("xls")){				// xls file
				workbook = getWorkbookXls(path);
			} else if(fileExt.toLowerCase().equals("xlsx")){		// xlsx file
				workbook = getWorkbookXlsx(path);
			} else {
				throw new Exception("invalid file type.(Not excel file)");
			}
			
			transferExcelFile(workbook);
		}
		long endTime = System.currentTimeMillis();
		
		double time = ((double)(endTime - startTime)) / 1000;
		System.out.println(time + " sec");
		
		return true;
	}

	private Workbook getWorkbookXls(String path) throws FileNotFoundException, IOException {
		POIFSFileSystem excel = new POIFSFileSystem(new FileInputStream(path));
		return new HSSFWorkbook(excel);
	}

	private Workbook getWorkbookXlsx(String path) throws FileNotFoundException, IOException {
		return new XSSFWorkbook(new FileInputStream(path));
	}

	private void transferExcelFile(Workbook workbook) throws IOException {
//		FileWriter fw = new FileWriter("c:/sujiewon/test/test_trans.txt");
//		BufferedWriter bw = new BufferedWriter(fw);
		
		String tgPath = "c:/sujiewon/test/test_trans.txt";
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tgPath), "UTF-8"));
		
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		
		sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int cells = 0;
		
		for(int r=0; r < rows; r++){
			row = sheet.getRow(r);
			cells = row.getPhysicalNumberOfCells();
			
			for(int c=0; c < cells; c++){
				cell = row.getCell(c);
				
				if(cell != null){
					switch(cell.getCellType()){
					case Cell.CELL_TYPE_STRING :
						bw.write(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC :
						bw.write((int)cell.getNumericCellValue() + "");
						break;
					case Cell.CELL_TYPE_FORMULA :
						bw.write(cell.getCellFormula());
						break;
					case Cell.CELL_TYPE_BLANK :
						bw.write("[blank]");
						break;
					case Cell.CELL_TYPE_BOOLEAN :
						bw.write(cell.getBooleanCellValue() + "");
						break;
					}
				}
				if(c < cells-1){
					bw.write("\t");
				}
				cell = null;
			}
			bw.newLine();
			row = null;
		}
		if(bw != null){
			bw.close();
		}
	}
	
}
