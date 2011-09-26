package personal.kirk.tools.db;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * 匯出資料庫表格及表格內容註解成EXCEL
 * For Oracle
 * 
 * @author KIRK HSU
 * @since 2008/11/5下午 6:20:55
 * @version 1.0
 */
public class DataBaseTableToExcel{
	public Connection conn = null;
	
	final int ONE_WORD_WIDTH = 550; 
	
	/**
	 * 匯出所有表格到一個Excel
	 * 
	 * @author KIRK HSU
	 * @since 2008/11/5
	 *
	 * @param fileName
	 */
	public void createOneFile(String outputPath, String fileName) {
		Statement stmt = null;
		ResultSet set = null;
		PreparedStatement pstmt = null;
		try {
			stmt = conn.createStatement();
			set = stmt.executeQuery("SELECT count(*) FROM USER_TABLES");
			set.next();
			int count = set.getInt(1);

			set = stmt.executeQuery("SELECT t1.TABLE_NAME,t2.COMMENTS FROM USER_TABLES t1, USER_TAB_COMMENTS t2 WHERE t1.TABLE_NAME=t2.TABLE_NAME ORDER BY TABLE_NAME");
			String[] tableNames = new String[count];
			String[] tableComments = new String[count];
			int index = 0;
			
			HSSFWorkbook wb = new HSSFWorkbook();
			
			HSSFSheet indexSheet = wb.createSheet("INDEX");
			indexSheet.setColumnWidth(0, ONE_WORD_WIDTH*50);
			
			// Create a new font and alter it.
		    HSSFFont font = wb.createFont();
		    font.setFontHeightInPoints((short)14);
		    font.setFontName("Courier New");
		    font.setItalic(true);
//		    font.setStrikeout(true);
//		    font.setUnderline(HSSFFont.U_DOUBLE);
			
			// Link Style
			HSSFHyperlink link = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
			HSSFCellStyle hlink_style = wb.createCellStyle();
		    HSSFFont hlink_font = wb.createFont();
		    hlink_font.setUnderline(HSSFFont.U_SINGLE);
		    hlink_font.setColor(HSSFColor.BLUE.index);
		    hlink_style.setFont(hlink_font);
		    hlink_style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		    hlink_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    
		    // Comment style
		    HSSFCellStyle comment_style = wb.createCellStyle();
		    HSSFFont comment_font = wb.createFont();
		    comment_font.setColor(HSSFColor.BLUE.index);
		    comment_style.setFont(comment_font);
		    comment_style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		    comment_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    
		    // Header style
			HSSFCellStyle header_style = wb.createCellStyle();
			header_style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
			header_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    
		    // Data style
		    HSSFCellStyle data_style = wb.createCellStyle();
		    data_style.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		    data_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    
		    HSSFCellStyle data_number_style = wb.createCellStyle();
//		    data_number_style.setDataFormat(HSSFDataFormat.getBuiltinFormat( "###,#" ));
		    data_number_style.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		    data_number_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		    data_number_style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		    
		    HSSFCellStyle data_style_center = wb.createCellStyle();
		    data_style_center.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		    data_style_center.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    data_style_center.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    
		    HSSFCellStyle pk_style = wb.createCellStyle();
		    pk_style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		    pk_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    
		    HSSFCellStyle pk_number_style = wb.createCellStyle();
		    pk_number_style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		    pk_number_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    pk_number_style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		    
		    // Title style
		    HSSFCellStyle title_style = wb.createCellStyle();
//		    titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    title_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    title_style.setFont(font);
		    
		    setBorderToStyle1(header_style);
			setBorderToStyle1(data_style);
			setBorderToStyle1(data_number_style);
			setBorderToStyle1(data_style_center);
			setBorderToStyle1(pk_style);
			
			while (set.next()) {
				String ss = set.getString(1);
				tableNames[index] = ss;
				String sss = set.getString(2);
				tableComments[index] = sss;
				
				HSSFRow row = indexSheet.createRow(index);
				HSSFCell cell = row.createCell(0);
				
				String comm = "";
				if(sss!=null){
					comm = "  - ["+sss+"]";
				}
				
				HSSFRichTextString str = new HSSFRichTextString(ss+comm);
			    cell.setCellValue(str);
			    link = new HSSFHyperlink(HSSFHyperlink.LINK_DOCUMENT);
			    link.setAddress("'"+ss+"'!A1");
			    cell.setHyperlink(link);
			    cell.setCellStyle(hlink_style);
			    
//			    HSSFCell cell1 = row0.createCell(1);
//			    HSSFRichTextString str1 = new HSSFRichTextString(sss);
//			    cell1.setCellValue(str1);
//			    cell1.setCellStyle(comment_style);
			    
			    index++;
			}
			
			set.close();
			stmt.close();
			
			int nameSize = tableNames.length;
			for(int i=0; i<nameSize; i++){
				String name = tableNames[i];
				StringBuffer sql = new StringBuffer();
				
				List pkList = getPKList(name);
				
				// 取得table的info及comments
				sql = new StringBuffer();
				sql.append("select t.table_name,t.column_name,t.data_type,t.data_length,t.nullable,c.comments ")
				   .append("from user_tab_cols t, user_col_comments c ")
				   .append("where t.table_name=c.table_name ")
				   .append("and t.column_name=c.column_name ")
				   .append("and t.table_name= ? order by t.column_id ");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, name);
				set = pstmt.executeQuery();
				
				HSSFSheet sheet = wb.createSheet(name);
				
				HSSFRow row = sheet.createRow(0);
				HSSFCell cell = row.createCell(0);
				
				row.setHeightInPoints(25);
				String comm = "";
				if(tableComments[i]!=null){
					comm = " - [" + tableComments[i] + "]";
				}
				
				cell.setCellValue(new HSSFRichTextString(name + comm));
				cell.setCellStyle(title_style);
				
				// 合併儲存格
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
				
				row = sheet.createRow(1);
				
				cell = row.createCell(0);
				HSSFRichTextString columnName = new HSSFRichTextString("欄位名稱");
				cell.setCellValue(columnName);
				cell.setCellStyle(header_style);
				
				cell = row.createCell(1);
				HSSFRichTextString dataType = new HSSFRichTextString("資料型態");
				cell.setCellValue(dataType);
				cell.setCellStyle(header_style);
				
				cell = row.createCell(2);
				HSSFRichTextString dataLength = new HSSFRichTextString("欄位長度");
				cell.setCellValue(dataLength);
				cell.setCellStyle(header_style);
				
				cell = row.createCell(3);
				HSSFRichTextString nullable = new HSSFRichTextString("NULLABLE");
				cell.setCellValue(nullable);
				cell.setCellStyle(header_style);
				
				cell = row.createCell(4);
				HSSFRichTextString comment = new HSSFRichTextString("註解");
				cell.setCellValue(comment);
				cell.setCellStyle(header_style);
				
				int rowIndex = 2;
				
				int cSize0 = 10;
				int cSize1 = 10;
//				int cSize2 = 10;
				int cSize3 = 10;
			    
				while (set.next()) {
					String columnNameString = set.getString("COLUMN_NAME");
					String dataTypeString = set.getString("DATA_TYPE");
					String dataLengthString = set.getString("DATA_LENGTH");
					String nullableString = set.getString("NULLABLE");
					String commentString = set.getString("COMMENTS");
					
					boolean isPK = pkList.contains(columnNameString);
					
					if(columnNameString!=null && columnNameString.length()>cSize0){
						cSize0 = columnNameString.length();
					}
					if(dataTypeString!=null && dataTypeString.length()>cSize1){
						cSize1 = dataTypeString.length();
					}
					if(commentString!=null && commentString.length()>cSize3){
						cSize3 = commentString.length();
					}
					
					row = sheet.createRow(rowIndex);
					
					cell = row.createCell(0);
					HSSFRichTextString _columnName = new HSSFRichTextString(columnNameString);
					cell.setCellValue(_columnName);
					if(isPK){
						cell.setCellStyle(pk_style);
					}else{
						cell.setCellStyle(data_style);
					}
					
					cell = row.createCell(1);
					HSSFRichTextString _dataType = new HSSFRichTextString(dataTypeString);
					cell.setCellValue(_dataType);
					if(isPK){
						cell.setCellStyle(pk_style);
					}else{
						cell.setCellStyle(data_style);
					}
					
					cell = row.createCell(2);
//					HSSFRichTextString _dataLength = new HSSFRichTextString(dataLengthString);
//					cell.setCellValue(_dataLength);
					cell.setCellValue(Long.parseLong(dataLengthString));
//					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					if(isPK){
						cell.setCellStyle(pk_number_style);
					}else{
						cell.setCellStyle(data_number_style);
					}
					
					cell = row.createCell(3);
					if("N".equals(nullableString)){
						nullableString = "";
					}
					HSSFRichTextString _nullable = new HSSFRichTextString(nullableString);
					cell.setCellValue(_nullable);
					if(isPK){
						cell.setCellStyle(pk_style);
					}else{
						cell.setCellStyle(data_style_center);
					}
					
					cell = row.createCell(4);
					HSSFRichTextString _comment = new HSSFRichTextString(commentString);
					cell.setCellValue(_comment);
					if(isPK){
						cell.setCellStyle(pk_style);
					}else{
						cell.setCellStyle(data_style);
					}
					
					rowIndex++;
					
				}
				
				// return index
				HSSFRow returnIndexRow = sheet.createRow(rowIndex);
				HSSFCell returnindexCell = returnIndexRow.createCell(0);
				returnindexCell.setCellValue(new HSSFRichTextString("GOTO INDEX..."));
			    link = new HSSFHyperlink(HSSFHyperlink.LINK_DOCUMENT);
			    link.setAddress("'INDEX'!A" + (i+1));
			    returnindexCell.setHyperlink(link);
			    returnindexCell.setCellStyle(hlink_style);
				
				sheet.setColumnWidth(0, ONE_WORD_WIDTH*(cSize0*3/4));
				sheet.setColumnWidth(1, ONE_WORD_WIDTH*7);
				sheet.setColumnWidth(2, ONE_WORD_WIDTH*5); // data_length
				sheet.setColumnWidth(3, ONE_WORD_WIDTH*5);
				sheet.setColumnWidth(4, ONE_WORD_WIDTH*cSize3);
				
				set.close();
				pstmt.close();
			}
			
//			FileOutputStream fileOut = new FileOutputStream("D:/"+fileName+"-"+getDatePrefix()+".xls");
			FileOutputStream fileOut = new FileOutputStream(outputPath+fileName+"-"+getDatePrefix()+".xls");
			wb.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 匯出單一表格的Excel
	 * 
	 * @author KIRK HSU
	 * @since 2008/11/5
	 *
	 * @param tableNames
	 */
	public void create(String tableNames) {
		PreparedStatement pstmt = null;
		ResultSet set = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql
					.append(
							"select t.table_name,t.column_name,t.data_type,t.nullable,c.comments ")
					.append("from user_tab_cols t, user_col_comments c ")
					.append("where t.table_name=c.table_name ").append(
							"and t.column_name=c.column_name ").append(
							"and t.table_name= ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, tableNames);
			set = pstmt.executeQuery();
			
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(tableNames);
			
			// adjust width of the first column
//			sheet.autoSizeColumn((short)0); 
			
			int cSize0 = 10;
			int cSize1 = 10;
//			int cSize2 = 10;
			int cSize3 = 10;
			
			HSSFCellStyle style = wb.createCellStyle();
			style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
		    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    
		    HSSFCellStyle style2 = wb.createCellStyle();
			style2.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		    style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    
		    HSSFCellStyle style3 = wb.createCellStyle();
		    style3.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		    style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			
			String comm = "";
//			if(tableComments[i]!=null){
//				comm = " - [" + tableComments[i] + "]";
//			}
			cell.setCellValue(new HSSFRichTextString(tableNames + comm));
			// 合併儲存格
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
			
			
			row = sheet.createRow(1);
			
			cell = row.createCell(0);
			HSSFRichTextString _columnName0 = new HSSFRichTextString("欄位名稱");
			cell.setCellValue(_columnName0);
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			HSSFRichTextString _dataType0 = new HSSFRichTextString("資料型態");
			cell.setCellValue(_dataType0);
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			HSSFRichTextString _nullable0 = new HSSFRichTextString("NULLABLE");
			cell.setCellValue(_nullable0);
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			HSSFRichTextString _comment0 = new HSSFRichTextString("註解");
			cell.setCellValue(_comment0);
			cell.setCellStyle(style);
			
			setBorderToStyle1(style);
			setBorderToStyle1(style2);
			setBorderToStyle1(style3);
			
			int rowIndex = 2;
		    
			while (set.next()) {
				String dataType = set.getString("DATA_TYPE");
				String columnName = set.getString("COLUMN_NAME");
				String nullable = set.getString("NULLABLE");
				String comment = set.getString("COMMENTS");
				
				if(columnName!=null && columnName.length()>cSize0){
					cSize0 = columnName.length();
				}
				if(dataType!=null && dataType.length()>cSize1){
					cSize1 = dataType.length();
				}
				if(comment!=null && comment.length()>cSize3){
					cSize3 = comment.length();
				}

				
				row = sheet.createRow(rowIndex);
				
				cell = row.createCell(0);
				HSSFRichTextString _columnName = new HSSFRichTextString(columnName);
				cell.setCellValue(_columnName);
				cell.setCellStyle(style2);
				
				cell = row.createCell(1);
				HSSFRichTextString _dataType = new HSSFRichTextString(dataType);
				cell.setCellValue(_dataType);
				cell.setCellStyle(style2);
				
				cell = row.createCell(2);
				if("N".equals(nullable)){
					nullable = "";
				}
				HSSFRichTextString _nullable = new HSSFRichTextString(nullable);
				cell.setCellValue(_nullable);
				cell.setCellStyle(style3);
				
				cell = row.createCell(3);
				HSSFRichTextString _comment = new HSSFRichTextString(comment);
				cell.setCellValue(_comment);
				cell.setCellStyle(style2);
				
				rowIndex++;
			}
			
			sheet.setColumnWidth(0, ONE_WORD_WIDTH*(cSize0*1/2));
			sheet.setColumnWidth(1, ONE_WORD_WIDTH*7);
			sheet.setColumnWidth(2, ONE_WORD_WIDTH*5);
			sheet.setColumnWidth(3, ONE_WORD_WIDTH*cSize3);
			
			FileOutputStream fileOut = new FileOutputStream("c:/excel/"+tableNames+".xls");
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setBorderToStyle1(HSSFCellStyle style){
//	    style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM_DASHED);
//	    style.setTopBorderColor(HSSFColor.BLACK.index);
	    
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setTopBorderColor(HSSFColor.BLACK.index);
		
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBottomBorderColor(HSSFColor.BLACK.index);
	    
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setLeftBorderColor(HSSFColor.BLACK.index);
	    
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setRightBorderColor(HSSFColor.BLACK.index);
	}

	/**
	 * 取得所有表格名稱
	 * 
	 * @author KIRK HSU
	 * @since 2008/11/5
	 *
	 * @return
	 */
	public String[] getAllDbTableNames() {
		Statement stmt = null;
		ResultSet set = null;
		try {
			stmt = conn.createStatement();
			set = stmt.executeQuery("SELECT count(*) FROM USER_TABLES");
			set.next();
			int count = set.getInt(1);

			set = stmt.executeQuery("SELECT * FROM USER_TABLES");
			String[] tableNames = new String[count];
			int index = 0;
			while (set.next()) {
				tableNames[index] = set.getString(1);
				index++;
			}
			return tableNames;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				stmt.close();
				// conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 設定並取得Oracle的Connection
	 * 
	 * @author KIRK HSU
	 * @since 2008/11/5
	 *
	 * @param ip
	 * @param port
	 * @param dbName
	 * @param username
	 * @param password
	 */
	public void setOracleConnection(String ip, String port, String dbName, String username, String password){
		String driver = "oracle.jdbc.driver.OracleDriver";
	    String url = "jdbc:oracle:thin:@"+ip+":"+port+":"+dbName;
	    try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	}
	
	private List getPKList(String tableName){
		System.out.println(tableName);
		ResultSet set = null;
		PreparedStatement pstmt = null;
		List pkList = new ArrayList();
		try{
			// get pk start
			StringBuffer sql = new StringBuffer();
			sql.append("select i.column_name ").append(
					"from user_ind_columns i, user_constraints c ").append(
					"where i.index_name=c.index_name ").append(
					"and i.table_name=c.table_name ").append(
					"and c.constraint_type='P' ")
					.append("and c.table_name= ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, tableName);
			set = pstmt.executeQuery();
			
			while (set.next()) {
				pkList.add(set.getString("COLUMN_NAME"));
			}
		}catch(Exception e){
			
		}finally{
			if(set!=null){
				try {
					set.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return pkList;
	}
	
	private String getDatePrefix(){
		SimpleDateFormat pattern = new SimpleDateFormat("yyyyMMddHHmmss",Locale.TAIWAN);
		return pattern.format(new Date());
	}
	
	static public void main(String[] args) throws Exception{
		DataBaseTableToExcel cei = new DataBaseTableToExcel();
		
//		cei.setOracleConnection("localhost", "1521", "tmtrmst", "RMS", "RMS123");
//		String[] tableNames = cei.getAllDbTableNames();
//		for(int i=0; i<tableNames.length; i++){
//			cei.create(tableNames[i]);
//		}
//		cei.conn.close();
		
//		cei.setOracleConnection("localhost", "1521", "tmtrmst", "RMS", "RMS123");
//		cei.createOneFile("TAIKOO_TABLES");
		
//		cei.setOracleConnection("172.30.12.223", "1521", "CMSDB", "ecuser1", "twm0919");
//		cei.setOracleConnection("172.30.12.223", "1521", "CMSDBS", "ap_ecuser", "twmec2ap");
		cei.setOracleConnection("10.76.134.3", "1533", "APPSS", "ebookstg", "ebookstg123");
		cei.createOneFile("D:/", "FET_EBOOK_TABLES");
		
		
	}
}
