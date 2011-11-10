package kirk.android.phonegap.plugin;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Environment;
import android.util.Log;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;
import com.phonegap.api.PluginResult.Status;
/**
 * 使在html頁面上可以取得SD-Card的檔案路徑
 * @author kirk
 *
 */
public class FileSelectorPlugin extends Plugin {
	private static final String TAG = "FileSelectorPlugin";
	static String LIST = "list";
	static String UP = "up";

	@Override
	public PluginResult execute(String action, JSONArray data, String callbackId) {
		Log.d(TAG, "FileSelectorPlugin.execute...");
		Log.d(TAG, "action=" + action);
		
		PluginResult result = null;
		// 參數得有兩個，一個是代表動作，一個代表路徑
		//String[] params = action.split(",");
		File root = null;
		try{
			if(LIST.equals(action)){
				if(data!=null){
					JSONObject jObj = data.getJSONObject(0);
					String isFirst = String.valueOf(jObj.get("isFirst"));
					if("true".equals(isFirst)){
						root = Environment.getExternalStorageDirectory();
						
						Log.d(TAG, "ExternalStorageDirectory="+root.getPath());
					}else{
						String path = String.valueOf(jObj.get("path"));
						root = new File(path);
						
						Log.d(TAG, "path=" + path);
					}
				}
			}else if(UP.equals(action)){
				// 上一層
				JSONObject jObj = data.getJSONObject(0);
				String path = String.valueOf(jObj.get("path"));
				root = new File(path).getParentFile();
			}
			
			JSONObject fileInfo = getOneDirectoryListing(root);
			result = new PluginResult(Status.OK, fileInfo);
		} catch (JSONException jsonEx) {
			Log.d(TAG, "Got JSON Exception "+ jsonEx.getMessage());
			result = new PluginResult(Status.JSON_EXCEPTION);
		}
		
		return result;
	}
	
	private JSONObject getDirectoryListing(File file) throws JSONException {
		JSONObject fileInfo = new JSONObject();
		fileInfo.put("filename", file.getName());
		fileInfo.put("isdir", file.isDirectory());
		if (file.isDirectory()) {
			JSONArray children = new JSONArray();
			fileInfo.put("children", children);
			if (null != file.listFiles()) {
				for (File child : file.listFiles()) {
					children.put(getDirectoryListing(child));
				}
			}
		}
		return fileInfo;
	}
	
	/**
	 * 取得第一層的檔案或目錄
	 * @param file
	 * @return
	 * @throws JSONException
	 */
	private JSONObject getOneDirectoryListing(File file) throws JSONException {
		Log.d(TAG, "AbsolutePath="+file.getAbsolutePath());
		try {
			Log.d(TAG, "CanonicalPath="+file.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.d(TAG, "Path="+file.getPath());
		
		JSONObject fileInfo = new JSONObject();
		fileInfo.put("filename", file.getName());
		fileInfo.put("isdir", file.isDirectory());
		fileInfo.put("path", file.getAbsolutePath());
		if (file.isDirectory()) {
			Log.d(TAG, "is directory");
			
			if (null != file.listFiles()) {
				JSONArray children = new JSONArray();
				fileInfo.put("children", children);
				
				for (File child : file.listFiles()) {
					if(child.getName().indexOf(".")!=0){
					
						JSONObject childFileInfo = new JSONObject();
						
						Log.d(TAG, "filename=" + child.getName());
						Log.d(TAG, "path=" + child.getAbsolutePath());
						
						childFileInfo.put("filename", child.getName());
						childFileInfo.put("isdir", child.isDirectory());
						childFileInfo.put("path", child.getAbsolutePath());
						
						children.put(childFileInfo);
					}
				}
			}
		}
		return fileInfo;
	}
	
	private String removeMnt(String input){
		if(input.indexOf("/mnt")==0){
			return input.substring(4);
		}else{
			return input;
		}
	}

}
