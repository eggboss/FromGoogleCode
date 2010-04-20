package eggboss.clipper.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnector {
	
	private String driverName = null;
	private String dbURL = null;
	private String userName = null;
	private String userPwd = null;
	
	public SQLServerConnector(String driverName, String dbURL, String userName, String userPwd){
		this.driverName = driverName;
		this.dbURL = dbURL;
		this.userName = userName;
		this.userPwd = userPwd;
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
//		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加載JDBC驅動
//		// 一定得加DatabaseName
//		String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=AdventureWorks;"; // 連接服務器和數據庫sample
//		String userName = "sa"; // 默認用戶名
//		String userPwd = "s1234a"; // 密碼
		Connection dbConn = null;
		Class.forName(driverName);
		dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
		System.out.println("Connection Successful!"); // 如果連接成功
		return dbConn;
	}
	
}
