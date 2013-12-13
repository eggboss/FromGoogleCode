package personal.kirk.tools.db;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

public class TestPOI {
	static public void main(String[] args) throws Exception{
/*
	    HSSFWorkbook wb = new HSSFWorkbook();
	    
	    // Tab
	    HSSFSheet sheet = wb.createSheet("new sheet");

	    // Create a row and put some cells in it. Rows are 0 based.
	    HSSFRow row = sheet.createRow(0);
	    
	    // Create a cell and put a value in it.
	    HSSFCell cell = row.createCell(0);
	    cell.setCellValue(1);

	    // Or do it on one line.
	    row.createCell(1).setCellValue(1.2);
	    
	    HSSFRichTextString str = new HSSFRichTextString("This is a string");
	    row.createCell(2).setCellValue(str);
	    
	    row.createCell(3).setCellValue(true);

	    // Write the output to a file
	    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
	    wb.write(fileOut);
	    fileOut.close();
*/
		
		
/*
	    HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("new sheet");

	    // Create a row and put some cells in it. Rows are 0 based.
	    HSSFRow row = sheet.createRow(0);

	    // Create a cell and put a date value in it.  The first cell is not styled
	    // as a date.
	    HSSFCell cell = row.createCell(0);
	    cell.setCellValue(new Date());

	    // we style the second cell as a date (and time).  It is important to
	    // create a new cell style from the workbook otherwise you can end up
	    // modifying the built in style and effecting not only this cell but other cells.
	    HSSFCellStyle cellStyle = wb.createCellStyle();
	    cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
	    cell = row.createCell(1);
	    cell.setCellValue(new Date());
	    cell.setCellStyle(cellStyle);

	    // Write the output to a file
	    FileOutputStream fileOut = new FileOutputStream("c:/workbook.xls");
	    wb.write(fileOut);
	    fileOut.close();
*/
/*
	    HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("new sheet");

	    // Create a row and put some cells in it. Rows are 0 based.
	    HSSFRow row = sheet.createRow((short) 1);

	    // Aqua background
	    HSSFCellStyle style = wb.createCellStyle();
	    style.setFillBackgroundColor(HSSFColor.AQUA.index);
	    style.setFillPattern(HSSFCellStyle.BIG_SPOTS);
	    HSSFCell cell = row.createCell(1);
	    cell.setCellValue("X");
	    cell.setCellStyle(style);

	    // Orange "foreground", foreground being the fill foreground not the font color.
	    style = wb.createCellStyle();
	    style.setFillForegroundColor(HSSFColor.ORANGE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    cell = row.createCell(2);
	    cell.setCellValue("X");
	    cell.setCellStyle(style);

	    // Write the output to a file
	    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
	    wb.write(fileOut);
	    fileOut.close();
*/
/*
	    HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("new sheet");

	    // Create a row and put some cells in it. Rows are 0 based.
	    HSSFRow row = sheet.createRow((short) 1);

	    // Create a cell and put a value in it.
	    HSSFCell cell = row.createCell((short) 1);
	    cell.setCellValue(4);

	    // Style the cell with borders all around.
	    HSSFCellStyle style = wb.createCellStyle();
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBottomBorderColor(HSSFColor.BLACK.index);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setLeftBorderColor(HSSFColor.GREEN.index);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setRightBorderColor(HSSFColor.BLUE.index);
	    style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM_DASHED);
	    style.setTopBorderColor(HSSFColor.BLACK.index);
	    cell.setCellStyle(style);

	    // Write the output to a file
	    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
	    wb.write(fileOut);
	    fileOut.close();
*/
		
/*
	    HSSFWorkbook wb = new HSSFWorkbook();

	    //cell style for hyperlinks
	    //by default hypelrinks are blue and underlined
	    HSSFCellStyle hlink_style = wb.createCellStyle();
	    HSSFFont hlink_font = wb.createFont();
	    hlink_font.setUnderline(HSSFFont.U_SINGLE);
	    hlink_font.setColor(HSSFColor.BLUE.index);
	    hlink_style.setFont(hlink_font);

	    HSSFCell cell;
	    HSSFSheet sheet = wb.createSheet("Hyperlinks");

	    //URL
	    cell = sheet.createRow(0).createCell((short)0);
	    cell.setCellValue("URL Link");
	    HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
	    link.setAddress("http://poi.apache.org/");
	    cell.setHyperlink(link);
	    cell.setCellStyle(hlink_style);

	    //link to a file in the current directory
	    cell = sheet.createRow(1).createCell((short)0);
	    cell.setCellValue("File Link");
	    link = new HSSFHyperlink(HSSFHyperlink.LINK_FILE);
	    link.setAddress("link1.xls");
	    cell.setHyperlink(link);
	    cell.setCellStyle(hlink_style);

	    //e-mail link
	    cell = sheet.createRow(2).createCell((short)0);
	    cell.setCellValue("Email Link");
	    link = new HSSFHyperlink(HSSFHyperlink.LINK_EMAIL);
	    //note, if subject contains white spaces, make sure they are url-encoded
	    link.setAddress("mailto:poi@apache.org?subject=Hyperlinks");
	    cell.setHyperlink(link);
	    cell.setCellStyle(hlink_style);

	    //link to a place in this workbook

	    //create a target sheet and cell
	    HSSFSheet sheet2 = wb.createSheet("Target Sheet");
	    sheet2.createRow(0).createCell((short)0).setCellValue("Target Cell");

	    cell = sheet.createRow(3).createCell((short)0);
	    cell.setCellValue("Worksheet Link");
	    link = new HSSFHyperlink(HSSFHyperlink.LINK_DOCUMENT);
	    link.setAddress("'Target Sheet'!A1");
	    cell.setHyperlink(link);
	    cell.setCellStyle(hlink_style);

	    FileOutputStream out = new FileOutputStream("hssf-links.xls");
	    wb.write(out);
	    out.close();
*/
		
		
		
		// Merging cells
	    HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("new sheet");

	    HSSFRow row = sheet.createRow((short) 1);
	    HSSFCell cell = row.createCell( 1);
	    cell.setCellValue(new HSSFRichTextString("This is a test of merging"));

//	    sheet.addMergedRegion(new Region(1,(short)1,1,(short)2));
	    
	    sheet.addMergedRegion(new CellRangeAddress(1,1,1,2));

	    // Write the output to a file
	    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
	    wb.write(fileOut);
	    fileOut.close();
	}
}
