package com.esoon.excelimporter.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * 使用POI讀入Excel檔案
 * @author Kirk Hsu
 * 
 * @see POI : http://poi.apache.org/
 * @see file:///D:/resource/POI/poi-3.6/docs/spreadsheet/quick-guide.html#ReadWriteWorkbook
 * @see Sample : http://onjava.com/pub/a/onjava/2003/04/16/poi_excel.html
 * 
 */
public class ExcelReader{

	/**
	 * 讀入檔案(2003 or 2007)
	 * @param filePath 檔案路徑
	 * @return
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	public Workbook readFile(String filePath) throws InvalidFormatException, IOException{
		InputStream inp = null;;
		Workbook wb = null;
		inp = new FileInputStream(filePath);
		wb = WorkbookFactory.create(inp);
		return wb;
	}
	
//	public HSSFWorkbook readFile(String filePath) throws IOException {
//	InputStream myxls = new FileInputStream(filePath);
//	HSSFWorkbook hSSFWorkbook = new HSSFWorkbook(myxls);
//	return hSSFWorkbook;
//}

	
/*
	public static void main(String[] args) throws Exception{
		String filePath = "d:/WorkingSpace/workspaces/TWM/Clipper/sample/3月底的特休補休時數.XLS";
//		HSSFWorkbook excel = new ExcelReader().readFile(filePath);
		Workbook wb = new ExcelReader().readFile(filePath);
		
//		System.out.println("第一個頁籤名稱：" + excel.getSheetName(0));
		// 取得第一個頁籤
//		HSSFSheet sheet = excel.getSheetAt(0);
		// 取得列
//		HSSFRow row = sheet.getRow(4);
		// 取得cell
//		HSSFCell cell   = row.getCell(0);
		
		System.out.println("第一個頁籤名稱：" + wb.getSheetName(0));
		Sheet sheet = wb.getSheetAt(0);
	    Row row = sheet.getRow(4);
	    Cell cell = row.getCell(0);
		
		System.out.println(row.getRowNum()+" : "+ cell.getColumnIndex() +" = "+   cell.getStringCellValue());
	}
*/
	
}
