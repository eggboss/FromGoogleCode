package eggboss.clipper.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * 使用POI讀入Excel檔案
 * @author Kirk Hsu
 * 
 * @see POI : http://poi.apache.org/
 * @see Sample : http://onjava.com/pub/a/onjava/2003/04/16/poi_excel.html
 */
public class ExcelReader{

	/**
	 * 讀入檔案
	 * @param filePath 檔案路徑
	 * @return
	 * @throws IOException
	 */
	public HSSFWorkbook readFile(String filePath) throws IOException {
		InputStream myxls = new FileInputStream(filePath);
		HSSFWorkbook hSSFWorkbook = new HSSFWorkbook(myxls);
		return hSSFWorkbook;
	}

	public static void main(String[] args) throws Exception{
		String filePath = "d:/WorkingSpace/workspaces/TWM/Clipper/sample/3月底的特休補休時數.XLS";
		HSSFWorkbook excel = new ExcelReader().readFile(filePath);
		
		System.out.println("第一個頁籤名稱：" + excel.getSheetName(0));
		
		// 取得第一個頁籤
		HSSFSheet sheet = excel.getSheetAt(0);
		
		// 取得列
		HSSFRow row = sheet.getRow(4);
		
		// 取得cell
		HSSFCell cell   = row.getCell(0);
		
		System.out.println(row.getRowNum()+" : "+ cell.getColumnIndex() +" = "+   cell.getStringCellValue());
		
	}
	
}
