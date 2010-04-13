package kirksample.jdbc.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerConnectUtil {
	public static void main(String[] srg) {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加載JDBC驅動
		String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=eggboss;"; // 連接服務器和數據庫sample
		String userName = "sa"; // 默認用戶名
		String userPwd = "sa"; // 密碼
		Connection dbConn;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("Connection Successful!"); // 如果連接成功
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
