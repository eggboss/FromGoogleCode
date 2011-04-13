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
import android.view.Window;
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
 * 4.點兩下才拍 OK
 * android.view.GestureDetector.OnDoubleTapListener
 * ==>http://developer.android.com/reference/android/view/GestureDetector.html
 * http://hi.baidu.com/baoj3/blog/item/cba37926eaf6c93ec8955915.html
 * 
 * 改進
 * 1.預覽可以再小一點(目前再小會有問題)
 * 2.設定畫面
 * 3.zoom 
 * startSmoothZoom()
 * 4.Drag拖動效果
 * ==>fling
 * http://developer.51cto.com/art/201001/181289.htm
 * 
 * 
 * @author Kirk Hsu
 * @see http://androidbiancheng.blogspot.com/2010/12/androidcamera-preview.html
 * @see http://androidbiancheng.blogspot.com/2010/12/androidcameratakepicture.html
 */
public class FaceSpy extends Activity{; //implements SurfaceHolder.Callback {
	private static final String TAG = "FaceSpy";
	
	GestureDetector gestureDetector;
	Camera myCamera;
	SurfaceView previewSurfaceView;
	SurfaceHolder previewSurfaceHolder;
	boolean previewing = false;
	String SDPath = null;
	private AudioManager audioManager;
	private int maxZoom = 0;
	private Parameters parameters;
	
//	private static final int SWIPE_MIN_DISTANCE = 120;   
//	private static final int SWIPE_MAX_OFF_PATH = 250;   
//	private static final int SWIPE_THRESHOLD_VELOCITY = 200; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 讓程式的標題列消失
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.main);
		
		// http://www.hlovey.cn/2009/09/10/android-screen-longitudinal-horizontal.html
		// 設死程式為直的！
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
		
//		NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//		Notification notification = new Notification();
//		notification.defaults=Notification.DEFAULT_VIBRATE;
//		mNotificationManager.notify(0,notification);
		
		// 設定拍照聲音
		audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		// 設定成震動
//		audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		// 設定靜音
		audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		
		// 設定圖壓儲存路徑
		SDPath = Environment.getExternalStorageDirectory().toString();
		File targetFolder = new File(SDPath + "\\FaceSpy\\");
		if(!targetFolder.exists()){
			targetFolder.mkdir();
			targetFolder = null;
		}
		
		getWindow().setFormat(PixelFormat.UNKNOWN);
		
		// 設定預覽
		bundleSurfaceHolderCallback();
		
		// 設定Touch Event(螢幕點擊或滑動)
		bundleMultiTouchEvent();
		
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

//				Log.i(TAG, "format=" + format);
//				Log.i(TAG, "width=" + width);
//				Log.i(TAG, "height=" + height);
				
				try {
					
					// 解決preview時會轉90度的問題
//					Camera.Parameters parameters = myCamera.getParameters();
//			        List<Size> sizes = parameters.getSupportedPreviewSizes();
//			        Size optimalSize = getOptimalPreviewSize(sizes, width, height);
//			        parameters.setPreviewSize(optimalSize.width, optimalSize.height);
			        myCamera.setDisplayOrientation(90);
			        parameters.setRotation(90);
			        myCamera.setParameters(parameters);
					
//					surfaceHolder.setFixedSize(192, 175);
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
				parameters = myCamera.getParameters();
				maxZoom = parameters.getMaxZoom();
				
				myCamera.setZoomChangeListener(new MySmoothZoomListener());
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
//				Log.d(TAG, "onDown");
				return super.onDown(e);
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//				Log.d(TAG, "onFling");
//				myCamera.stopSmoothZoom();
//				Log.d(TAG, "isSmoothZoomSupported="+parameters.isSmoothZoomSupported());
				try {
					if(Math.abs(e1.getY() - e2.getY()) < 100){
						float moveSize = e2.getX()-e1.getX();
//						Log.d(TAG, "Move size = " + moveSize);
						
//						float fZoom = (moveSize/400) * maxZoom;
//						Log.d(TAG, "Float zoom = " + fZoom);
						
//						int zoom = (int)fZoom;
//						Log.d(TAG, "add zoom: " + zoom);
						
						int cZoom = parameters.getZoom(); // 目前的zooom
//						cZoom += zoom;
						
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
//							Log.d(TAG, "over zoom!");
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
			// 解決問題：OutOfMemoryError: bitmap size exceeds VM budget
			// http://blog.csdn.net/wen0006/archive/2010/11/15/6009634.aspx
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inSampleSize = 2;
			
			Bitmap bitmapPicture = BitmapFactory.decodeByteArray(arg0, 0, arg0.length, opts);
			saveImage(bitmapPicture);
		}
	};
	
	private void saveImage(Bitmap bm){
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
	public boolean onTouchEvent(MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
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