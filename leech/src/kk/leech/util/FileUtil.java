package kk.leech.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
/**
 * 
 * @author Kirk Hsu
 *
 */
public class FileUtil {
	/**
	 * 建立檔案
	 * @param content 內容
	 * @param fileName 檔名
	 * @param encode 編碼方式
	 * @return
	 */
	static public boolean createFile(String content, String fileName, String encode){
		String resultFileTotalPath = null;
		resultFileTotalPath = fileName;
		try {

            BufferedReader inputStream = new BufferedReader(new StringReader(content.toString()));
			
			FileOutputStream fileOutputStream = new FileOutputStream(resultFileTotalPath);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, encode);
            PrintWriter outputStream = new PrintWriter(outputStreamWriter); 
            
            String inLine = null;

            while ((inLine = inputStream.readLine()) != null) {
            	outputStream.println(inLine);
            }
            
            outputStream.close();
            inputStream.close();
            
            return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
