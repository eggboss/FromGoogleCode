package kirk.android.music;

import android.os.Bundle;
import android.util.Log;

import com.phonegap.DroidGap;

public class MyMusicPlayerActivity extends DroidGap {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
        
        appView.addJavascriptInterface(new JsBridge(), "JsBridge");
    }
}

class JsBridge {
	public void log(String msg){
		Log.d("JsBridge", msg);
	}
}