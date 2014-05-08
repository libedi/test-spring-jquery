package org.ricetable.testexcel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
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
	public ArrayList<ArrayList<String>> readExcelFile(File file) throws Exception {
		if(file == null || !file.isFile()){
			throw new FileNotFoundException();
		}
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		Workbook workbook = getWorkbook(file);
		result = getExcelData(workbook);
		
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
	private Workbook getWorkbook(File file) throws FileNotFoundException, IOException {
		Workbook workbook = null;
		
		String filePath = file.getCanonicalPath();
		String fileExt = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
		if(fileExt != null && !fileExt.isEmpty()){
			if(fileExt.equals("xls")){
				workbook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(file)));
			} else if(fileExt.equals("xlsx")){
				workbook = new XSSFWorkbook(new FileInputStream(file));
			}
		}
		
		return workbook;
	}

	/**
	 * Read workbook<br/>
	 * - read only single sheet
	 * @param workbook	Workbook
	 * @return	2-dimension array list
	 */
	private ArrayList<ArrayList<String>> getExcelData(Workbook workbook) {
		ArrayList<ArrayList<String>> resultRow = null;
		ArrayList<String> resultCell = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		int rows = 0;
		int cells = 0;
		
		// read one sheet
//		int sheetNum = workbook.getNumberOfSheets();
		sheet = workbook.getSheetAt(0);
		rows = (sheet.getPhysicalNumberOfRows() < 5) ? sheet.getPhysicalNumberOfRows() : 5;
		
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
	
	/**
	 * Transfer Excel file to Text file
	 * 
	 * @param path
	 * @return if success is true, but false
	 * @throws Exception
	 */
	public boolean transferExcelToText(String src, String dest) throws Exception{
		if(src == null || src.equals("")){
			throw new FileNotFoundException();
		}
		
		String fileExt = src.substring(src.lastIndexOf(".") + 1);
		if(fileExt != null && !src.isEmpty()){
			Workbook workbook = null;
			if(fileExt.toLowerCase().equals("xls")){				// xls file
				workbook = getWorkbookXls(src);
			} else if(fileExt.toLowerCase().equals("xlsx")){		// xlsx file
				workbook = getWorkbookXlsx(src);
			} else {
				throw new Exception("invalid file type.(Not excel file)");
			}
			
			transferExcelToText(dest, workbook);
		}
		
		return true;
	}

	/**
	 * get Workbook object for xls file
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private Workbook getWorkbookXls(String path) throws FileNotFoundException, IOException {
		POIFSFileSystem excel = new POIFSFileSystem(new FileInputStream(path));
		return new HSSFWorkbook(excel);
	}

	/**
	 * get Workbook object for xlsx file
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private Workbook getWorkbookXlsx(String path) throws FileNotFoundException, IOException {
		return new XSSFWorkbook(new FileInputStream(path));
	}

	/**
	 * read Excel file data, and write Text file.
	 * 
	 * @param workbook
	 * @throws IOException
	 */
	private void transferExcelToText(String dest, Workbook workbook) throws IOException {
//		FileWriter fw = new FileWriter("c:/sujiewon/test/test_trans.txt");
//		BufferedWriter bw = new BufferedWriter(fw);
		
//		String tgPath = "c:/sujiewon/test/test_trans.txt";
		// encoding UTF-8
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest), "UTF-8"));
		
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
					bw.write("|");
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

	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public boolean readText(String path) throws IOException {
		if(path == null || path.isEmpty()){
			throw new FileNotFoundException();
		}
		String dest = "c:/sujiewon/test/test_trans_copy.txt";
		boolean result = copyText(path, dest);
		
		return result;
	}

	/**
	 * 
	 * @param src
	 * @param dest
	 * @return
	 * @throws IOException
	 */
	private boolean copyText(String src, String dest) throws IOException {
		FileReader fr = null;
		BufferedReader br = null;
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try{
			fr = new FileReader(src);
			br = new BufferedReader(fr);
			
			fw = new FileWriter(dest, false);	// append false.
			bw = new BufferedWriter(fw);
//			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest), "UTF-8"));
			
			String tempStr  = null;
			
			while((tempStr = br.readLine()) != null){
				String[] spStr = tempStr.split("[|]");
				for(int i=0; i<spStr.length; i++){
					bw.write(spStr[i]);
//					System.out.println(spStr[i]);
					if(i < (spStr.length - 1)){
						bw.write("|");
					}
				}
				bw.newLine();
			}
		} catch(IOException e){
			throw e;
		} finally {
			if(br != null) try{br.close();} catch(IOException e){throw e;}
			if(fr != null) try{fr.close();} catch(IOException e){throw e;}
			if(bw != null) try{bw.close();} catch(IOException e){throw e;}
			if(fw != null) try{fw.close();} catch(IOException e){throw e;}
		}
		
		return true;
	}
	
	/**
	 * Create Workbook object
	 * 
	 * @param fileExt
	 * @return
	 * @throws Exception
	 */
	private Workbook createWorkbook(String fileExt) throws Exception {
		Workbook workbook = null;
		if(fileExt == null || fileExt.equals("")){
			throw new Exception("invalid file type.(Not Excel file)");
		}
		if(fileExt.equals("xls")){
			workbook = new HSSFWorkbook();
		} else if(fileExt.equals("xlsx")) {
			workbook = new XSSFWorkbook();
		}
		return workbook;
	}

	/**
	 * Transfer text file to excel file
	 * 
	 * @param src	Text file path
	 * @param dest	Excel file path
	 * @return
	 * @throws Exception
	 */
	public boolean transferTextToExcel(String src, String dest) throws Exception {
		if(src == null || src.equals("")){
			throw new FileNotFoundException();
		}
		boolean result = true;
		String fileExt = dest.substring(src.lastIndexOf(".") + 1).toLowerCase();
		if(fileExt != null && !dest.isEmpty()){
			Workbook workbook = null;
			if(fileExt.equals("xls") || fileExt.equals("xlsx")){
				workbook = createWorkbook(fileExt);
			} else {
				throw new Exception("invalid file type.(Not excel file)");
			}
			
			result = transferTextToExcel(src, dest, workbook);
		}
		return result;
	}

	/**
	 * Transfer text file to excel file
	 * 
	 * @param src	Text file path
	 * @param dest	Excel file path
	 * @param workbook	Workbook Object
	 * @return
	 * @throws IOException
	 */
	public boolean transferTextToExcel(String src, String dest, Workbook workbook) throws IOException {
		
		FileReader fr = null;
		BufferedReader br = null;
		FileOutputStream fs = null;
		
		// create sheet
		Sheet sheet = workbook.createSheet();
		Row row = null;
		Cell cell = null;
		
		// config cell style
		CellStyle style = workbook.createCellStyle();
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		
		int rowCount = 0;
		
		try {
			fr = new FileReader(src);
			br = new BufferedReader(fr);
			fs = new FileOutputStream(dest);
			
			String tempStr = "";
			while((tempStr = br.readLine()) != null){
				row = sheet.createRow(rowCount++);
				String[] spStr = tempStr.split("[|]");
				for(int i=0; i<spStr.length; i++){
					cell = row.createCell(i);
					cell.setCellStyle(style);
					cell.setCellValue(spStr[i]);
				}
			}
			workbook.write(fs);
			
		} catch (IOException e) {
			throw e;
		} finally {
			if(fs != null) try{fs.close();}catch(IOException e){throw e;}
		}
		
		return true;
	}

	
}
