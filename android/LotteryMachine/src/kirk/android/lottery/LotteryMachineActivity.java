package kirk.android.lottery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * 彩券應用
 * 設定一組或多組號碼，提供每次購買時選號，提醒購買彩券，提醒得獎
 * 
 * 功能：
 * 1.儲存彩券號碼
 * 2.對獎(取得當期的得獎號碼)
 * 3.得獎通知、
 * 
 * 應用：
 * 1.排程
 * 2.Android資料庫(SQLite)
 * Android 開發中使用 SQLite 數據庫 
 * http://www.ibm.com/developerworks/cn/opensource/os-cn-sqlite/index.html
 * Android開發筆記－建立SQLite實作類別
 * http://www.moke.tw/wordpress/computer/advanced/238
 *
 * @author kirk
 * @version 1.0
 */
public class LotteryMachineActivity extends Activity {
	private static final String TAG = "DB";
	private DatabaseHelper databaseHelper = null;
	Button btnInsert = null;
	Button btnQuery = null;
	Button btnDeleteAll = null;
	TextView resultView = null;
	Spinner spinner = null;
	
	ArrayAdapter adapter = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        databaseHelper = new DatabaseHelper(this);
        
        resultView = (TextView)findViewById(R.id.resultTextView);
        btnInsert = (Button)findViewById(R.id.insert);
        btnQuery = (Button)findViewById(R.id.query);
        btnDeleteAll = (Button)findViewById(R.id.btn_deleteall);
        spinner = (Spinner)findViewById(R.id.spinner1);
        
        btnInsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				databaseHelper.insert(new Date(), "1,2,3,4,5,6", "Y");
				
//				databaseHelper.close();
			}
		});
        
        btnQuery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Cursor requltCursor = databaseHelper.query();
				int row_count = requltCursor.getCount();
				StringBuffer result = new StringBuffer();
				
				requltCursor.moveToFirst();			//將指標移至第一筆資料
				for(int i=0; i<row_count; i++) {
					
					int id = requltCursor.getInt(0);	//取得第0欄的資料，根據欄位type使用適當語法
					String buyDate = requltCursor.getString(1);
					String numbers = requltCursor.getString(2);
					String status = requltCursor.getString(3);
					
					result.append(id+","+buyDate+","+numbers+","+status+"\n");
					
					requltCursor.moveToNext();		//將指標移至下一筆資料
				}
				requltCursor.close();		//關閉資料庫，釋放記憶體
				
				resultView.setText(result.toString());
			}
		});
        
        btnDeleteAll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				databaseHelper.deleteAll();
				resultView.setText("");
//				databaseHelper.close();
			}
		});
        
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getListData());
        spinner.setAdapter(adapter);
    }
    
    
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.import_option, menu);
    	return true;
	}



	private String[] getListData(){
    	List<String> dataList = new ArrayList<String>();
    	for(int i=1; i<=49; i++){
    		dataList.add("" + i);
    	}
    	return (String[])dataList.toArray(new String[dataList.size()]);
    }
    
}