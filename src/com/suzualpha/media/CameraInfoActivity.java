package com.suzualpha.media;

import java.util.List;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.suzualpha.templateapk.R;

public class CameraInfoActivity extends Activity {
	
	TextView cameraCont, cameraFlont, cameraRia, cameraOther, cameraFlashText;
	Camera camera;
	private Camera.Parameters cameraParams;
	private String[] mSupportedFlashModes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camerainfo);
		
		cameraCont = (TextView) findViewById(R.id.camerainfo_cameracount_text);
		cameraFlont = (TextView) findViewById(R.id.camerainfo_camerafront_text);
		cameraRia = (TextView) findViewById(R.id.camera_info_cameraria_text);
		cameraOther = (TextView) findViewById(R.id.camera_info_cameraother_text);
		cameraFlashText = (TextView)findViewById(R.id.camerainfo_flash_text);
		
		// 端末に搭載されているカメラの数を取得　
		int numberOfCameras = Camera.getNumberOfCameras();
		cameraCont.setText(String.valueOf(numberOfCameras));
		
		// 各カメラの情報を取得
		for (int i = 0; i < numberOfCameras; i++) {
			CameraInfo caminfo = new CameraInfo();
			Camera.getCameraInfo(i, caminfo);
			
			// カメラの向きを取得
			int facing = caminfo.facing;
			
			if (facing == CameraInfo.CAMERA_FACING_BACK) {
				cameraFlont.setText("id:" +String.valueOf(i)+" あり");
				// 後部についているカメラの場合
				Log.d("MultiCameraTest", "cameraId=" + Integer.toString(i)
						+ ", this is a back-facing camera");
				
			} else if (facing == CameraInfo.CAMERA_FACING_FRONT) {
				cameraRia.setText("id:" +String.valueOf(i)+"あり");
				// フロントカメラの場合
				Log.d("MultiCameraTest", "cameraId=" + Integer.toString(i)
						+ ", this is a front-facing camera");
			} else {
				cameraOther.setText("あり");
				Log.d("MultiCameraTest", "cameraId=" + Integer.toString(i)
						+ ", unknown camera?");
			}
			
			// カメラのOrientation(角度) を取得
			int orient = caminfo.orientation;
			Log.d("MultiCameraTest", "cameraId=" + Integer.toString(i)
					+ ", orientation=" + Integer.toString(orient));
		}
		
		camera = Camera.open(0);
		cameraParams = camera.getParameters();
		try {
			String currentFlashMode = cameraParams.getFlashMode();
			List<String> supportedFlashModes = cameraParams.getSupportedFlashModes();
			
			mSupportedFlashModes = new String[supportedFlashModes.size()];
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mSupportedFlashModes.length; i++) {
				String flashMode = supportedFlashModes.get(i);
				sb.append(flashMode);
				sb.append("\n");
				mSupportedFlashModes[i] = flashMode;
				if (currentFlashMode.equals(flashMode)) {
//					mCurrentFlashModeIndex = i;
				}
			}
			String str = new String(sb);
			cameraFlashText.setText(str);
		} catch (Exception e) {
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		camera.release();
	}
	
}
