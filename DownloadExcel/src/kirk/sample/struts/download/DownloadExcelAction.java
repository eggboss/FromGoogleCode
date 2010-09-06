package kirk.sample.struts.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
/**
 * Action for download excel file
 * 需實作getExcelData(...) method
 * 
 * @author Kirk Hsu
 *
 */
public abstract class DownloadExcelAction extends DispatchAction {
    
    protected static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    
    /**
     * 將要報表資料組合成POI物件
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    protected abstract HSSFWorkbook getExcelData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

    protected InputStream getStreamData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	HSSFWorkbook workbook = getExcelData(mapping, form, request, response);
    	return poiToInputStream(workbook);
    }
    
    protected void poiToOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HSSFWorkbook workbook = getExcelData(mapping, form, request, response);
		outputPoiExcel(workbook, response);
	}

    protected int getBufferSize() {
        return DEFAULT_BUFFER_SIZE;
    }

    /**
     * 下載Excel檔案
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward downloadExcel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

//    	InputStream stream = getStreamData(mapping, form, request, response);
        String fileName = request.getParameter("downloadFileName");
        try {
        	response.setContentType("application/octet-stream; charset=UTF-8");
        	response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
//            copy(stream, response.getOutputStream());
        	
        	poiToOutput(mapping, form, request, response);
        } finally {
//            if (stream != null) {
//                stream.close();
//            }
        }

        // Tell Struts that we are done with the response.
        return null;
    }
    
    /*
     * 將InputStream導到OutputStream
     */
    private int copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[getBufferSize()];
        int count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
    
    /*
     * 先將HSSFWorkbook寫到暫存檔案中，
     * 再將暫存檔放入InputStream中回傳
     */
    private InputStream poiToInputStream(HSSFWorkbook workbook){
    	File tempFile = new File("temp.xls");
		InputStream is = null;
		try {
			OutputStream os = new FileOutputStream(tempFile);
			workbook.write(os);
			is = new FileInputStream(tempFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
    }
    
    /*
     * 將HSSFWorkbook直接寫入到OutputStream
     * 不會產生暫存檔案
     */
    private void outputPoiExcel(HSSFWorkbook workbook, HttpServletResponse response){
		try {
			workbook.write(response.getOutputStream());
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 加入一個一般的Cell
     * @param row
     * @param index
     * @param value
     * @return
     */
    protected void createCell(HSSFRow row, short index, String value){
		HSSFCell cell1 = row.createCell(index);
		cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell1.setCellValue(value);
	}
    
    /**
     * 加入一個金額的Cell (前置$並靠右)
     * @param row
     * @param index
     * @param value
     * @return
     */
    protected void createAmountCell(HSSFRow row, short index, String value, String prefix, HSSFWorkbook wk){
    	HSSFCellStyle style = wk.createCellStyle();
    	style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
    	
		HSSFCell cell1 = row.createCell(index);
		cell1.setCellStyle(style);
		cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
		if(prefix!=null){
			cell1.setCellValue(prefix + value);
		}else{
			cell1.setCellValue(value);
		}
	}
}
