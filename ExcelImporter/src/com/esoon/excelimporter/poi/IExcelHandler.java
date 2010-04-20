package com.esoon.excelimporter.poi;

import org.apache.poi.ss.usermodel.Workbook;

import com.esoon.excelimporter.exception.ImportException;
/**
 * Excel處理介面
 * 實作其處理方式或DB
 * @author kirk
 *
 */
public interface IExcelHandler {
	
	/**
	 * Excel檔案處理實作
	 * @param myExcel Workbook物件
	 * @return
	 * @throws Exception
	 */
	public void action(Workbook myExcel) throws Exception;
	
	/**
	 * 檢查輸入檔案格式
	 * @return
	 * @throws ImportException
	 */
	public boolean check(Workbook myExcel) throws ImportException;
}
