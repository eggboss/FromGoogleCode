package personal.kirk.tools.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JNDIConnection {

	private static DataSource ds = null;
	private static Connection con = null;
/*
	private static final String strDBDriver =
		"com.microsoft.jdbc.sqlserver.SQLServerDriver";
	private static final String strDBUrl =
		"jdbc:microsoft:sqlserver://localhost:1433;DataBaseName=struts";
	private static final String strDBUser = "sa";
	private static final String strDBPwd = "1234";
*/

	//private static 
	static public Connection getConnection() throws SQLException {
		try {
			
			String driver = "oracle.jdbc.driver.OracleDriver";
		    String url = "jdbc:oracle:thin:@172.30.12.223:1521:CMSDBS";
//		    String url = "jdbc:oracle:thin:@localhost:1521:tmtrmst";
		    String username = "ap_ecuser2";
		    String password = "twmtwm0935";
//		    String username = "RMS";
//		    String password = "RMS123";

		    Class.forName(driver); // load Oracle driver
		    con = DriverManager.getConnection(url, username, password);

	/**************************************************************************************
	 * Connecttion to MS SQL 2000																														   *
	 *                                                                                                                                                                        *
	 * Class.forName("com.mysql.jdbc.Driver");                                                                                             	       *
	 * Connection con = DriverManager.getConnection(                                                                                 	       *
	 *                          "jdbc:microsoft:sqlserver://localhost:1433;DataBaseName=struts","user","password"); 	   *
	 *                                                                                                                                                                        *
	 **************************************************************************************/
	/*
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			con =
				DriverManager.getConnection(
					"jdbc:microsoft:sqlserver://localhost:1433;DataBaseName=struts",
					"sa",
					"1234");
	*/
	/**************************************************************************************
	 * Connecttion to MySQL 5																												               *
	 *                                                                                                                                                                        *
	 * Class.forName("com.mysql.jdbc.Driver");                                                                                             	       *
	 * Connection con = DriverManager.getConnection("jdbc:mysql://host:port/database","user","password"); 	   *
	 * in order to solve the  chinese problem ,add "?useUnicode=true&characterEncoding=big5"                           *
	 **************************************************************************************/
/*
			Class.forName("com.mysql.jdbc.Driver");
			con =
				DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=big5",
					"kirk",
					"94043");
*/
			return con;
		} catch (ClassNotFoundException cne) {
			System.out.println(
				"[JNDIConnection.getDirectConnection()]ClassNotFoundException�G"
					+ cne.getMessage());
			throw new SQLException(cne.getMessage());
		} catch (SQLException se) {
			System.out.println(
				"[JNDIConnection.getDirectConnection()]SQLException�G"
					+ se.getMessage());
			throw new SQLException(se.getMessage());
		}
	}
}
