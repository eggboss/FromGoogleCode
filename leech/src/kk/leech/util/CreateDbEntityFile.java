package kk.leech.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kk.leech.db.JNDIConnection;

/**
 * 建立DB中所有Table的Entity
 * 
 * @author Kirk Hsu
 * @since 2008.10.13
 * 
 */
public class CreateDbEntityFile {
	static public final String FILE_ENCODE_UTF8 = "UTF-8";
	static public final String FILE_ENCODE_BIG5 = "BIG5";

	private final String COLUMN_TYPE_VARCHAR2 = "VARCHAR2";
	private final String COLUMN_TYPE_CHAR = "CHAR";
	private final String COLUMN_TYPE_NUMBER = "NUMBER";
	private final String COLUMN_TYPE_DATE = "DATE";

	private final String TAB = "    ";

	private boolean genComments = false;
	private String outPath = null;
	private String packageString = null;
	private String[] importStrings = null;
	private String extend = null;
	private String fileEncode = FILE_ENCODE_UTF8;
	Connection conn = null;

	public CreateDbEntityFile() throws Exception {
		conn = JNDIConnection.getConnection();
	}
	
	public CreateDbEntityFile(Connection conn) throws Exception {
		this.conn = conn;
	}

	/**
	 * 取得Java對應的資料型態
	 * 
	 * @param dbDataType
	 * @return
	 */
	private String getJavaDataType(String dbDataType) {
		if (COLUMN_TYPE_VARCHAR2.equals(dbDataType)
				|| COLUMN_TYPE_CHAR.equals(dbDataType)) {
			return "String ";
		} else if (COLUMN_TYPE_NUMBER.equals(dbDataType)) {
			return "Integer ";
		} else if (COLUMN_TYPE_DATE.equals(dbDataType)) {
			return "Date ";
		} else {
			return "String ";
		}
	}

	/**
	 * 取得DataBase裡所有表格的名稱
	 * 
	 * @return
	 * @throws Exception
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
	 * 建立Table相對應的Java Entity
	 * 
	 * @param tableNames
	 *            表格名稱
	 * @throws Exception
	 */
	public void createClassFile(String tableNames) {
		PreparedStatement pstmt = null;
		ResultSet set = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql
					.append(
							"select t.table_name,t.column_name,t.data_type,c.comments ")
					.append("from user_tab_cols t, user_col_comments c ")
					.append("where t.table_name=c.table_name ").append(
							"and t.column_name=c.column_name ").append(
							"and t.table_name= ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, tableNames);
			set = pstmt.executeQuery();

			// 用來存Class的內容
			StringBuffer bf = new StringBuffer();

			// package字串
			if (packageString != null) {
				bf.append("package " + packageString).append(";\n\n");
			}
			// import字串
			if (importStrings != null) {
				for (int i = 0; i < importStrings.length; i++) {
					bf.append("import " + importStrings[i]).append(";\n\n");
				}
			}
			String extendString = "";
			if (extend != null) {
				extendString = " extends " + extend;
			}
			// 取得Class內容
			String ss = genClassContent(set);
			bf.append("public class " + tableNames + extendString + "{\n\n");
			bf.append(ss);

			// set pk
			sql = new StringBuffer();
			sql.append("select i.column_name ").append(
					"from user_ind_columns i, user_constraints c ").append(
					"where i.index_name=c.index_name ").append(
					"and i.table_name=c.table_name ").append(
					"and c.constraint_type='P' ")
					.append("and c.table_name= ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, tableNames);
			set = pstmt.executeQuery();
			StringBuffer keys = new StringBuffer();
			int index = 1;
			while (set.next()) {
				if (index > 1) {
					keys.append(",");
				}
				keys.append("\"").append(set.getString(1).toLowerCase()).append("\"");
				index++;
			}

			bf.append("\n" + TAB + "public String[] getKeys() {\n").append(
					TAB + TAB + "String[] keys = {" + keys + "};\n").append(
					TAB + TAB + "return keys;\n").append(TAB + "}\n");

			bf.append("}");

			createFile(bf.toString(), getOutPath() + "\\" + tableNames
					+ ".java", getFileEncode());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				// conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 由ResultSet中抓出Table所有欄位名稱，並組成所需字串
	 * 
	 * @param set
	 *            ResultSet
	 * @return
	 * @throws Exception
	 */
	public String genClassContent(ResultSet set) throws Exception {
		StringBuffer bf = new StringBuffer();
		StringBuffer bf2 = new StringBuffer();
		while (set.next()) {
			String dataType = getJavaDataType(set.getString("DATA_TYPE"));
			String columnName = set.getString("COLUMN_NAME").toLowerCase();
			String comment = set.getString("COMMENTS");
			if (this.isGenComments()) {
				bf.append(TAB + "/**\n").append(TAB + " *  ").append(comment)
						.append("\n").append(TAB + " */").append("\n");
			}
			bf.append(TAB + "private ").append(dataType).append(columnName)
					.append(";\n\n");

			if (this.isGenComments()) {
				bf2.append(TAB + "/**\n").append(TAB + " * 取得").append(comment)
						.append("\n").append(TAB + " * @return " + comment)
						.append("\n").append(TAB + " */").append("\n");
			}
			bf2.append(TAB + "public ").append(dataType).append(
					"get" + getNameString(columnName)).append("(){\n");
			bf2.append(TAB + TAB + "return this." + columnName).append(";\n");
			bf2.append(TAB + "}\n");

			if (this.isGenComments()) {
				bf2.append(TAB + "/**\n").append(TAB + " * 設定").append(comment)
						.append("\n").append(TAB + " * @param " + columnName)
						.append(" " + comment).append("\n").append(TAB + " */")
						.append("\n");
			}
			bf2.append(TAB + "public ").append("void ").append(
					"set" + getNameString(columnName)).append(
					"(" + dataType + columnName + "){\n");
			bf2.append(TAB + TAB + "this." + columnName + " = " + columnName)
					.append(";\n");
			bf2.append(TAB + "}\n");
		}

		return bf.toString() + bf2.toString();
	}

	/**
	 * 取得method名稱
	 * 
	 * @param name
	 * @return
	 */
	private String getNameString(String name) {
		return name.substring(0, 1).toUpperCase()
				+ name.substring(1).toLowerCase();
	}

	/**
	 * 取得檔案編碼
	 * 
	 * @return
	 */
	public String getFileEncode() {
		return fileEncode;
	}

	/**
	 * 設定檔案編碼
	 * 
	 * @param fileEncode
	 */
	public void setFileEncode(String fileEncode) {
		this.fileEncode = fileEncode;
	}

	/**
	 * 建立檔案
	 * 
	 * @param content
	 *            檔案內容
	 * @param fileName
	 *            檔案完整路徑
	 * @param encode
	 *            檔案編碼
	 */
	private void createFile(String content, String fileName, String encode) {
		FileUtil.createFile(content, fileName, encode);
	}

	// public void search(int pagenum) throws Exception{
	// int changeUnit = 10;
	// Statement stmt = conn.createStatement();
	// ResultSet set = null;
	// set = stmt.executeQuery("SELECT count(*) FROM USER_TABLES");
	// set.next();
	// int count = set.getInt(1);
	// System.out.println(count);
	// int totalPageCount =
	// count%changeUnit==0?(count/changeUnit):((count/changeUnit)+1);
	// String linkString = "*";
	// for(int i=1; i<=totalPageCount; i++){
	// if(i==pagenum){
	// System.out.print(i+" ");
	// }else{
	// System.out.print(i+linkString+" ");
	// }
	// }
	// }

	/**
	 * 取得輸出路徑
	 */
	public String getOutPath() {
		return outPath;
	}

	/**
	 * 設定輸出路徑
	 * 
	 * @param outPath
	 */
	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}

	/**
	 * 取得package
	 * 
	 * @return
	 */
	public String getPackageString() {
		return packageString;
	}

	/**
	 * 設定package
	 * 
	 * @param packageString
	 */
	public void setPackageString(String packageString) {
		this.packageString = packageString;
	}

	/**
	 * 取得import陣列
	 * 
	 * @return
	 */
	public String[] getImportStrings() {
		return importStrings;
	}

	/**
	 * 設定import陣列
	 * 
	 * @param importStrings
	 */
	public void setImportStrings(String[] importStrings) {
		this.importStrings = importStrings;
	}

	/**
	 * 是否要產生注解
	 * 
	 * @return
	 */
	public boolean isGenComments() {
		return genComments;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	/**
	 * 設定是否要產生注解
	 * 
	 * @param genComments
	 */
	public void setGenComments(boolean genComments) {
		this.genComments = genComments;
	}

	static public void main(String[] args) throws Exception {
		CreateDbEntityFile cf = new CreateDbEntityFile();

		// 設定的絕對路徑
		cf.setOutPath("C:\\workspace\\leech\\src\\kk\\leech\\db\\vo");
		// 設定package
		cf.setPackageString("kk.leech.db.vo");
		// 設定import
		String[] inportStrings = { "java.util.Date", "kk.leech.db.DBEntities" };
		cf.setImportStrings(inportStrings);
		// 設定是否要有注解
//		cf.setGenComments(true);
		// 設定檔案編碼
		cf.setFileEncode(CreateDbEntityFile.FILE_ENCODE_UTF8);
		// 繼承
		cf.setExtend("DBEntities");

		// 取得所有table的名稱
		String[] tableNames = cf.getAllDbTableNames();
		for (int i = 0; i < tableNames.length; i++) {
			// 由table名稱建立CLASS
			cf.createClassFile(tableNames[i]);
		}

	}

}
