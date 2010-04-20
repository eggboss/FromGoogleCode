package com.esoon.excelimporter.exception;

public class ImportException extends Exception {

	@Override
	public String getMessage() {
		return "輸入檔案格式錯誤！";
	}
	
}
