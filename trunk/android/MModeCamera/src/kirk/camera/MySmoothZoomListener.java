package kirk.camera;

import android.hardware.Camera;
import android.hardware.Camera.OnZoomChangeListener;
import android.util.Log;

public class MySmoothZoomListener implements OnZoomChangeListener {
	private static final String TAG = "FaceSpy";

	@Override
	public void onZoomChange(int zoomValue, boolean stopped, Camera camera) {
		// TODO Auto-generated method stub
		Log.d(TAG, "MySmoothZoomListener.onZoomChange, zoomValue="+zoomValue+",stopped="+stopped);
		camera.startSmoothZoom(zoomValue);
//		camera.stopSmoothZoom();
	}

}
