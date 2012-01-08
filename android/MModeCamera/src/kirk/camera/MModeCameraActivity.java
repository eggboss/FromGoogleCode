package kirk.camera;

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
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

/**
 * M模式的照相機
 * 可以自行設定光圈、快門及ISO
 * 檢視圖片時會顯示exif
 * 
 * 1. 列出可以設定的光圈、快門、ISO
 * ==>Exposure曝光
 * 
 * 
 * [GUIDE] Desire HD Camera guide - all issues resolved
 * http://forum.xda-developers.com/showthread.php?t=875230
 * 
 * @author kirk
 * 
 * 
 * 
 * 
 * 
 * 		<SeekBar android:id="@+id/zoomRate"
		android:layout_width="280dip"
		android:layout_height="wrap_content"/>
		
		
		
		<SeekBar android:id="@+id/isoSeekBar"
		android:layout_width="280dip"
		android:layout_height="wrap_content"
		android:paddingTop="200px"
		/>
 *
 */
public class MModeCameraActivity extends Activity {
	private static final String TAG = "MModeCamera";
		
	GestureDetector gestureDetector;
	Camera myCamera;
	SurfaceView previewSurfaceView;
	SurfaceHolder previewSurfaceHolder;
	boolean previewing = false;
	String SDPath = null;
	private AudioManager audioManager;
	private int maxZoom = 0;
	private Parameters parameters;
	private String saveDir = "";
	
	private SeekBar seekBar = null;
	private SeekBar exposureSeekBar = null;
	
	//private static final int SWIPE_MIN_DISTANCE = 120;   
	//private static final int SWIPE_MAX_OFF_PATH = 250;   
	//private static final int SWIPE_THRESHOLD_VELOCITY = 200; 
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 讓程式的標題列消失
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	//	getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.main);
		
		// http://www.hlovey.cn/2009/09/10/android-screen-longitudinal-horizontal.html
		// 設死程式為直的！
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
		
	//	NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
	//	Notification notification = new Notification();
	//	notification.defaults=Notification.DEFAULT_VIBRATE;
	//	mNotificationManager.notify(0,notification);
		
		saveDir = getString(R.string.save_dir);
		Log.i(TAG, "saveDir=" + saveDir);
		
		int alpha = Integer.parseInt(getString(R.string.alpha_value));
		Log.i(TAG, "alpha=" + alpha);
		View view=(View)findViewById(R.id.tview);
		view.getBackground().setAlpha(alpha);//0-255透明度
		
		// 設定拍照聲音
		audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		// 設定成震動
	//	audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		// 設定靜音
		audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		
		// 設定圖壓儲存路徑
		SDPath = Environment.getExternalStorageDirectory().toString();
		try{
			File targetFolder = new File(SDPath + File.separator + saveDir + File.separator);
			if(!targetFolder.exists()){
				Log.i(TAG, "Create DIR :" + targetFolder.getAbsolutePath());
				targetFolder.mkdir();
				targetFolder = null;
			}else{
				Log.i(TAG, "Exist : " + targetFolder.getAbsolutePath());
			}
		}catch(Exception e){
			Log.e(TAG, e.getMessage());
		}
		
		getWindow().setFormat(PixelFormat.UNKNOWN);
		
		// 設定預覽
		bundleSurfaceHolderCallback();
		
		// 設定Touch Event(螢幕點擊或滑動)
		bundleMultiTouchEvent();
		
		
	}
	
/*	
	private void bundleSeekBar(){
		// SeekBar
		seekBar = (SeekBar)findViewById(R.id.zoomRate);
		seekBar.setMax(parameters.getMaxZoom());
		seekBar.setKeyProgressIncrement(1);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				Log.i(TAG, "progress=" + progress);
				if(progress >= 1){
					if(parameters.isSmoothZoomSupported()){
						myCamera.startSmoothZoom(progress);
						Log.d(TAG, "StartSmoothZoom !");
					}else{
						parameters.setZoom(progress);
						myCamera.setParameters(parameters);
						Log.d(TAG, "Set Zoom !");
					}
				}
			}
		});
	}
*/
	
	private void bundleExposureSeekBar(){

		int maxExposureCompensation = parameters.getMaxExposureCompensation();
		Log.d(TAG, "MaxExposureCompensation=" + maxExposureCompensation);
		
		int minExposureCompensation = parameters.getMinExposureCompensation();
		Log.d(TAG, "MinExposureCompensation=" + minExposureCompensation);
		
		// SeekBar
		exposureSeekBar = (SeekBar)findViewById(R.id.exposureSeekBar);
		exposureSeekBar.setMax(maxExposureCompensation-minExposureCompensation); // 8
		exposureSeekBar.setKeyProgressIncrement(1);
		exposureSeekBar.setProgress(4);
		exposureSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				Log.i(TAG, "progress=" + progress); // 0~8
				if(progress >= 1){
					int exposureCompensation = progress-4;
					Log.d(TAG, "exposureCompensation=" + exposureCompensation);
					parameters.setExposureCompensation(exposureCompensation); // -4~4
					myCamera.setParameters(parameters);
				}
			}
		});
	}
	
	
	private void bundleSurfaceHolderCallback(){
		previewSurfaceView = (SurfaceView) findViewById(R.id.previewsurface);
		previewSurfaceHolder = previewSurfaceView.getHolder();
		previewSurfaceHolder.addCallback(new SurfaceHolder.Callback(){
	
			@Override
			public void surfaceChanged(SurfaceHolder surfaceHolder, int format , int width , int height ) {
				if (previewing) {
					myCamera.stopPreview();
					previewing = false;
				}
				try {
					// 解決preview時會轉90度的問題
	//				Camera.Parameters parameters = myCamera.getParameters();
	//		        List<Size> sizes = parameters.getSupportedPreviewSizes();
	//		        Size optimalSize = getOptimalPreviewSize(sizes, width, height);
	//		        parameters.setPreviewSize(optimalSize.width, optimalSize.height);
			        myCamera.setDisplayOrientation(90);
			        parameters.setRotation(90);
			        myCamera.setParameters(parameters);
			        
			        parameters.set("preview-size", "800x480");
			        parameters.set("iso", "false");
					
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
				myCamera = Camera.open();
				Log.d(TAG, "Open Camera !");
				
				parameters = myCamera.getParameters();
				Log.d(TAG, "Get Parameters !");
				
				maxZoom = parameters.getMaxZoom();
				myCamera.setZoomChangeListener(new MySmoothZoomListener());
				
//				Log.d(TAG, "Run bundleSeekBar !");
//				bundleSeekBar();
				
				bundleExposureSeekBar();
			}
	
			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) {
				myCamera.stopPreview();
				myCamera.release();
				myCamera = null;
				previewing = false;
				
				// 音量設定成正常模式
				audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			}
			
		});
		previewSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	private void bundleMultiTouchEvent(){
		gestureDetector = new GestureDetector(new SimpleOnGestureListener(){
			
			@Override
			public boolean onDown(MotionEvent e) {
				return super.onDown(e);
			}
	
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					if(Math.abs(e1.getY() - e2.getY()) < 100){
						float moveSize = e2.getX()-e1.getX();
						int cZoom = parameters.getZoom(); // 目前的zooom
						
						if(moveSize>0 && cZoom<maxZoom){
							cZoom++;
						}else if(moveSize<0 && cZoom>0){
							cZoom--;
						}
						
						if(cZoom<=maxZoom && cZoom>=0){
							parameters.setZoom(cZoom);
							myCamera.setParameters(parameters);
						}else if(cZoom<0){
							parameters.setZoom(0);
							myCamera.setParameters(parameters);
						}else{
							parameters.setZoom(maxZoom);
							myCamera.setParameters(parameters);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return super.onFling(e1, e2, velocityX, velocityY);
			}
	
			@Override
			public void onLongPress(MotionEvent e) {
				super.onLongPress(e);
			}
	
			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,	float distanceX, float distanceY) {
				return super.onScroll(e1, e2, distanceX, distanceY);
			}
	
			@Override
			public void onShowPress(MotionEvent e) {
				super.onShowPress(e);
			}
	
			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				if(!previewing){
					myCamera.startPreview();
					previewing = true;
				}
				return super.onSingleTapConfirmed(e);
			}
	
			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				return super.onSingleTapUp(e);
			}
	
			@Override
			public boolean onDoubleTap(MotionEvent event) {
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
				return super.onDoubleTap(event);
			}
	
			@Override
			public boolean onDoubleTapEvent(MotionEvent e) {
				return super.onDoubleTapEvent(e);
			}
			
		});
	}
	
	ShutterCallback shutterCallback = new ShutterCallback() {
		@Override
		public void onShutter() {
		}
	};
	
	PictureCallback rawPictureCallback = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] arg0, Camera arg1) {
		}
	};
	
	PictureCallback jpegPictureCallback = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] arg0, Camera arg1) {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inSampleSize = 2;
			
			Bitmap bitmapPicture = BitmapFactory.decodeByteArray(arg0, 0, arg0.length, opts);
			saveImage(bitmapPicture);
		}
	};
	
	private void saveImage(Bitmap bm){
		String barcodeNumber = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		FileOutputStream out = null;
		try {
			String imgPath = SDPath + File.separator + saveDir + File.separator + barcodeNumber + ".JPG";
			out = new FileOutputStream(imgPath);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "Save Image Error!! Error Message: " + e.getMessage());
		}
		Toast.makeText(this, barcodeNumber + ".JPG Saved.", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}
	
}