package eggboss.clipper.poi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import eggboss.clipper.jdbc.SQLServerConnector;
import eggboss.clipper.resource.ResourceBundleAdapter;

public class ExcelTransform {
	public static void main(String[] args) throws Exception{
		// read Excel file
		String filePath = "d:/WorkingSpace/workspaces/TWM/Clipper/sample/3月底的特休補休時數.XLS";
		HSSFWorkbook excel = new ExcelReader().readFile(filePath);
		
		
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
		
		conn.setAutoCommit(false);
		
		// 應該是個批次寫入
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for(int i=0; i<values.size(); i++){
			pstmt.setObject(i+1, values.get(i));
			pstmt.addBatch();
		}
		
		
		int [] counts = pstmt.executeBatch();
		System.out.println("成功寫入" + counts + "筆資料！");
		conn.commit();
		
		pstmt.close();
		conn.close();
	}
	
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
