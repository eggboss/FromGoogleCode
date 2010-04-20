package com.esoon.excelimporter.poi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.esoon.excelimporter.exception.ImportException;
import com.esoon.jdbc.SQLServerConnector;
import com.esoon.resource.ResourceBundleAdapter;
/**
 * Excel檔案匯入處理實作
 * @author kirk
 *
 */
public class ExcelHandlerImpl implements IExcelHandler{
	
	/**
	 * 取得JDBC Connection
	 * 在resource.properties設定
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected Connection getConnection() throws ClassNotFoundException, SQLException{
		String driverName = ResourceBundleAdapter.getResource("driverName");
		String dbURL = ResourceBundleAdapter.getResource("dbURL");
		String userName = ResourceBundleAdapter.getResource("userName");
		String userPwd = ResourceBundleAdapter.getResource("userPwd");
		return new SQLServerConnector(driverName, dbURL, userName, userPwd).getConnection();
	}
	
	public void action(Workbook myExcel) throws ClassNotFoundException, SQLException{
		String sql = "";
		List values = null;
		// TODO
		
		// get connection
		Connection conn = getConnection();
		
		
/*
		conn.setAutoCommit(false);
		
		// 應該是個批次寫入
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for(int i=0; i<values.size(); i++){
			pstmt.setObject(i+1, values.get(i));
			pstmt.addBatch();
		}
		
		int[] counts = pstmt.executeBatch();
		System.out.println("成功寫入" + counts + "筆資料！");
		
		conn.commit();
		
		pstmt.close();*/
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean check(Workbook myExcel) throws ImportException {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
/*
	public static void main(String[] args) throws Exception{
		// read Excel file
		String filePath = "d:/WorkingSpace/workspaces/TWM/Clipper/sample/3月底的特休補休時數.XLS";
		Workbook excel = new ExcelReader().readFile(filePath);
		
		
		// generate prepareStatement sql and Object set.
		String sql = "";
		List values = null;
		// TODO
		
		// get connection
		String driverName = ResourceBundleAdapter.getResource("driverName");
		String dbURL = ResourceBundleAdapter.getResource("dbURL");
		String userName = ResourceBundleAdapter.getResource("userName");
		String userPwd = ResourceBundleAdapter.getResource("userPwd");
		Connection conn = new SQLServerConnector(driverName, dbURL, userName, userPwd).getConnection();
		System.out.println("get connection success!");
//		conn.setAutoCommit(false);
		
		// 應該是個批次寫入
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		for(int i=0; i<values.size(); i++){
//			pstmt.setObject(i+1, values.get(i));
//			pstmt.addBatch();
//		}
		
		
//		int [] counts = pstmt.executeBatch();
//		System.out.println("成功寫入" + counts + "筆資料！");
//		conn.commit();
//		
//		pstmt.close();
		conn.close();
	}
*/
	
	
	
/* batch sample
 * http://publib.boulder.ibm.com/infocenter/iseries/v5r3/index.jsp?topic=/rzaha/batchpre.htm
 * 
connection.setAutoCommit(false);
PreparedStatement statement = connection.prepareStatement("INSERT INTO TABLEX VALUES(?, ?)");

statement.setInt(1, 1);
statement.setString(2, "Cujo");
statement.addBatch();

statement.setInt(1, 2);
statement.setString(2, "Fred");
statement.addBatch();

statement.setInt(1, 3);
statement.setString(2, "Mark");
statement.addBatch();

int [] counts = statement.executeBatch();
connection.commit();
*/
}
