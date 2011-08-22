package kirk.android.lottery;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String TAG = "DB";
	
	private static final String DATABASE_NAME = "moke_config.db";	//資料庫名稱
	private static final int DATABASE_VERSION = 1;	//資料庫版本
	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private SQLiteDatabase db;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		db = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 創建數據庫後，對數據庫的操作
		
//		String DATABASE_CREATE_TABLE =
//		    "create table config ("
//		        + "_ID INTEGER PRIMARY KEY,"
//		        + "name TEXT,"
//		        + "value INTEGER"
//		    + ");";
		
		// 建立config資料表，詳情請參考SQL語法
//		db.execSQL(DATABASE_CREATE_TABLE);
		
		/**
		 * data type
		 * INTEGER
		 * TEXT
		 */
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE ").append("lottery").append(" ( ");
		sql.append("id INTEGER DEFAULT '1' NOT NULL PRIMARY KEY AUTOINCREMENT").append(", ");
		sql.append("buy_date DATE NOT NULL").append(", ");
		sql.append("numbers TEXT NOT NULL").append(", ");
		sql.append("status TEXT NOT NULL");
		sql.append(" ) "); 
		
		Log.i(TAG, "Create DB.");
		db.execSQL(sql.toString()); 
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 更改數據庫版本的操作
		Log.i(TAG, "onUpgrade");
		//oldVersion=舊的資料庫版本；newVersion=新的資料庫版本
		db.execSQL("DROP TABLE IF EXISTS lottery");	//刪除舊有的資料表
		onCreate(db);
	}
	
	@Override    
	public void onOpen(SQLiteDatabase db) {     
		super.onOpen(db);       
		// TODO 每次成功打開數據庫後首先被執行     
	}
	
	public long insert(Date buyDate, String numbers, String status){
		db= getWritableDatabase();//獲取可寫SQLiteDatabase對象
		//ContentValues類似map，存入的是鍵值對
		ContentValues contentValues = new ContentValues();
//		contentValues.put("buy_date", "(DATETIME('now'))");
		contentValues.put("buy_date", dateTimeFormat.format(new Date()));
		contentValues.put("numbers", numbers);
		contentValues.put("status", status);
		
		long result = db.insert("lottery", null, contentValues);
		
		db.close();
		
		return result;
	}
	
	public long deleteAll(){
		db = getWritableDatabase();
		long result = db.delete("lottery", null, null);
		db.close();
		return result;
	}
	
	public Cursor query(){
		db = getReadableDatabase();
		Cursor result = db.query("lottery", new String[]{"id","buy_date","numbers","status"} , null, null, null, null, "buy_date");
//		db.close();
		return result;
	}
}
