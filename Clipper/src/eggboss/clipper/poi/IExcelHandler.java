package eggboss.clipper.poi;

import org.apache.poi.ss.usermodel.Workbook;
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
	public int[] action(Workbook myExcel) throws Exception;
}
