package eggboss.clipper.poi;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelTransform {
	
	private Workbook myExcel;
	private IExcelHandler excelHandler;
	
	/**
	 * 建構子
	 * @param excel Workbook物件
	 * @param excelHandler Excel檔案處理實作
	 * @throws IOException
	 */
	public ExcelTransform(Workbook excel, IExcelHandler excelHandler) throws IOException{
		this.excelHandler = excelHandler;
		this.myExcel = excel;
	}
	
	/**
	 * 建構子
	 * @param filePath 檔案路徑 
	 * @param excelHandler Excel檔案處理實作
	 * @throws IOException
	 */
	public ExcelTransform(String filePath, IExcelHandler excelHandler) throws IOException, InvalidFormatException{
		this.excelHandler = excelHandler;
		this.myExcel = new ExcelReader().readFile(filePath);
	}
	
	/**
	 * Workbook物件套用Excel檔案處理實作
	 * @return
	 * @throws Exception
	 */
	public int[] transfer() throws Exception{
		return excelHandler.action(myExcel);
	}

}
