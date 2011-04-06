package kirk.FaceSpyAndroid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;
/**
 * FaceSpy Android版
 * http://sparksolar.blogspot.com/2010/10/facespy-very-discreet-spy-cam.html
 * 未解問題：
 * 1.轉90度問題 OK 
 * =>http://stackoverflow.com/questions/4998205/htc-desire-hd-not-accepts-setparameter-with-hardware-camera
 * 2.快門聲問題 OK (把系統的音量設定成震動或靜音)
 * =>http://cooking-java.blogspot.com/2010/07/android-audiomanager.html
 * 3.對焦問題 OK
 * 
 * 改進
 * 1.預覽可以再小一點(目前再小會有問題)
 * 2.設定畫面
 * 3.zoom 
 * startSmoothZoom()
 * 4.點兩下才拍
 * android.view.GestureDetector.OnDoubleTapListener
 * 
 * @author Kirk Hsu
 * @see http://androidbiancheng.blogspot.com/2010/12/androidcamera-preview.html
 * @see http://androidbiancheng.blogspot.com/2010/12/androidcameratakepicture.html
 */
public class FaceSpy extends Activity implements SurfaceHolder.Callback {
private static final String TAG = "FaceSpy";
	
	Camera myCamera;
	SurfaceView previewSurfaceView;
	SurfaceHolder previewSurfaceHolder;
	boolean previewing = false;
	String SDPath = null;
	private AudioManager audioManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// http://www.hlovey.cn/2009/09/10/android-screen-longitudinal-horizontal.html
		// 設死程式為直的！
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
		
//		NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//		Notification notification = new Notification();
//		notification.defaults=Notification.DEFAULT_VIBRATE;
//		mNotificationManager.notify(0,notification);
		
		audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		// 設定成震動
//		audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		// 設定靜音
		audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		
		SDPath = Environment.getExternalStorageDirectory().toString();
		File targetFolder = new File(SDPath + "\\FaceSpy\\");
		if(!targetFolder.exists()){
			targetFolder.mkdir();
			targetFolder = null;
		}
		
		getWindow().setFormat(PixelFormat.UNKNOWN);
		previewSurfaceView = (SurfaceView) findViewById(R.id.previewsurface);
		previewSurfaceHolder = previewSurfaceView.getHolder();
		previewSurfaceHolder.addCallback(this);
		previewSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

	}

	ShutterCallback shutterCallback = new ShutterCallback() {

		@Override
		public void onShutter() {
			Log.d(TAG, "shutterCallback！");
		}
	};

	PictureCallback rawPictureCallback = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] arg0, Camera arg1) {
			Log.d(TAG, "rawPictureCallback！");
		}
	};

	PictureCallback jpegPictureCallback = new PictureCallback() {
		
		@Override
		public void onPictureTaken(byte[] arg0, Camera arg1) {
			Log.d(TAG, "jpegPictureCallback！onPictureTaken！");
			Log.d(TAG, "Date=" + arg0);
			
			// 解決問題：OutOfMemoryError: bitmap size exceeds VM budget
			// http://blog.csdn.net/wen0006/archive/2010/11/15/6009634.aspx
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inSampleSize = 2;
			
			Bitmap bitmapPicture = BitmapFactory.decodeByteArray(arg0, 0, arg0.length, opts);
			saveImage(bitmapPicture);
		}
	};
	
	private void saveImage(Bitmap bm){
		Log.d(TAG, "saveImage！");
		String barcodeNumber = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//		MediaStore.Images.Media.insertImage(getContentResolver(), bm, barcodeNumber + ".jpg Card Image", barcodeNumber + ".jpg Card Image");
		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(SDPath + "\\FaceSpy\\" + barcodeNumber + ".JPG");
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Toast.makeText(this, barcodeNumber + ".JPG Saved.", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void surfaceChanged(SurfaceHolder surfaceHolder, int format , int width , int height ) {
		Log.d(TAG,"surfaceChanged()");
		
		if (previewing) {
			myCamera.stopPreview();
			previewing = false;
		}

		Log.i(TAG, "format=" + format);
		Log.i(TAG, "width=" + width);
		Log.i(TAG, "height=" + height);
		
		try {
			
			// 解決preview時會轉90度的問題
			Camera.Parameters parameters = myCamera.getParameters();
//	        List<Size> sizes = parameters.getSupportedPreviewSizes();
//	        Size optimalSize = getOptimalPreviewSize(sizes, width, height);
//	        parameters.setPreviewSize(optimalSize.width, optimalSize.height);
	        myCamera.setDisplayOrientation(90);
	        parameters.setRotation(90);
	        myCamera.setParameters(parameters);
			
//			surfaceHolder.setFixedSize(192, 175);
			myCamera.setPreviewDisplay(surfaceHolder);
			myCamera.startPreview();
			previewing = true;
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		}

	}

	@Override
	public void surfaceCreated(SurfaceHolder surfaceHolder) {
		Log.d(TAG,"surfaceCreated()");
		myCamera = Camera.open();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		Log.d(TAG,"surfaceDestroyed()");
		myCamera.stopPreview();
		myCamera.release();
		myCamera = null;
		previewing = false;
		
		// 音量設定成正常模式
		audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i(TAG, "onTouchEvent!! action=" + event.getAction() + ", previewing=" + previewing);
		
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			
			if(!previewing){
				myCamera.startPreview();
				previewing = true;
			}else{
				// 對焦問題
				AutoFocusCallback autoFocusCallback = new AutoFocusCallback() {
					@Override
					public void onAutoFocus(boolean success, Camera camera) {

						Log.v("AutoFocusCallback", "AutoFocusCallback" + success);
						Camera.Parameters parameters = camera.getParameters();
						parameters.setPictureFormat(PixelFormat.JPEG);// 設置圖片格式
						camera.setParameters(parameters);
						
						camera.takePicture(shutterCallback, rawPictureCallback, jpegPictureCallback);
						myCamera.stopPreview();
					}
				};
				
				myCamera.autoFocus(autoFocusCallback);
				
				previewing = false;
			}
		}
		
		return super.onTouchEvent(event);
	}
	
	

	
/*
	private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
	    final double ASPECT_TOLERANCE = 0.05;
	    double targetRatio = (double) w / h;
	    if (sizes == null) return null;

	    Size optimalSize = null;
	    double minDiff = Double.MAX_VALUE;

	    int targetHeight = h;

	    // Try to find an size match aspect ratio and size
	    for (Size size : sizes) {
	        double ratio = (double) size.width / size.height;
	        if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
	        if (Math.abs(size.height - targetHeight) < minDiff) {
	            optimalSize = size;
	            minDiff = Math.abs(size.height - targetHeight);
	        }
	    }

	    // Cannot find the one match the aspect ratio, ignore the requirement
	    if (optimalSize == null) {
	        minDiff = Double.MAX_VALUE;
	        for (Size size : sizes) {
	            if (Math.abs(size.height - targetHeight) < minDiff) {
	                optimalSize = size;
	                minDiff = Math.abs(size.height - targetHeight);
	            }
	        }
	    }
	    return optimalSize;
	}
*/
	
}