package com.suzualpha.templateapk;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.microedition.khronos.opengles.GL10;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.opengl.GLES10;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DeviceInfo extends Activity {
	
	LinearLayout scrollLinear;
	ArrayList<String> strList;
//	コピペ用	strList.add();
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_info);
		
		strList = new ArrayList<String>();		
		scrollLinear = (LinearLayout) findViewById(R.id.devide_info_scrolllinear);
		
		getAdId();
		
		strList.add("OpenGLES2.0Renderer: " + GLES10.glGetString(GL10.GL_RENDERER));
//		GLES10.glGetString(GLES10.GL_RENDERER); //どっちも一緒
		
		WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		Point size = new Point();
		disp.getSize(size);
		strList.add("画面横: "+ size.x);
		strList.add("画面縦: " + size.y);

		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		strList.add("デバイスId: " + telephonyManager.getDeviceId());
		strList.add("言語設定: " + Locale.getDefault().toString());
		
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		String macAddress = wifiInfo.getMacAddress();
		strList.add("macAddress: " + macAddress);
		
		// 指定のパッケージ名のverを取得する
		int versionCode = 0;
		String versionName = "";
		PackageManager packageManager = this.getPackageManager();
		
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.webview", PackageManager.GET_ACTIVITIES);
			versionCode = packageInfo.versionCode;
			versionName = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		strList.add("WebVeiw VersionCode = " + versionCode + "  :  versionName = " + versionName);

/* デバイス情報　*/
		strList.add("BOARD: " + android.os.Build.BOARD);
		strList.add("BOOTLOADER: " + android.os.Build.BOOTLOADER);
		strList.add("BRAND: " + android.os.Build.BRAND);
		strList.add("CPU_ABI: " + android.os.Build.CPU_ABI);
		strList.add("CPU_ABI2: " + android.os.Build.CPU_ABI2);
		strList.add("DEVICE: " + android.os.Build.DEVICE);
		strList.add("DISPLAY: " + android.os.Build.DISPLAY);
		strList.add("FINGERPRINT: " + android.os.Build.FINGERPRINT);
		strList.add("HARDWARE: " + android.os.Build.HARDWARE);
		strList.add("HOST: " + android.os.Build.HOST);
		strList.add("ID: " + android.os.Build.ID);
		strList.add("MANUFACTURER: " + android.os.Build.MANUFACTURER);
		strList.add("MODEL: " + android.os.Build.MODEL);
		strList.add("PRODUCT: " + android.os.Build.PRODUCT);
		strList.add("RADIO: " + android.os.Build.RADIO);
		strList.add("SERIAL: " + android.os.Build.SERIAL);
		strList.add("TAGS: " + android.os.Build.TAGS);
		strList.add("TYPE: " + android.os.Build.TYPE);
		strList.add("UNKNOWN: " + android.os.Build.UNKNOWN);
		strList.add("USER: " + android.os.Build.USER);
		strList.add("getRadioVersion: " + android.os.Build.getRadioVersion());
		strList.add("SDK_INT: " + String.valueOf(android.os.Build.VERSION.SDK_INT));
		strList.add("CODENAME: " + android.os.Build.VERSION.CODENAME);
		strList.add("INCREMENTAL: " + android.os.Build.VERSION.INCREMENTAL);
		strList.add("RELEASE: " + android.os.Build.VERSION.RELEASE);
		strList.add("SDK: " + android.os.Build.VERSION.SDK);
		strList.add("SDK_INT: " + String.valueOf(android.os.Build.VERSION.SDK_INT));
		
/* TelephonyManager */
		strList.add("isNetworkRoaming: " + String.valueOf(telephonyManager.isNetworkRoaming()));
		strList.add("isSmsCapable: " + String.valueOf(telephonyManager.isSmsCapable()));
		strList.add("isVoiceCapable: " + String.valueOf(telephonyManager.isVoiceCapable()));
		strList.add("getCallState: " + String.valueOf(telephonyManager.getCallState()));
		strList.add("getDataActivity: " + String.valueOf(telephonyManager.getDataActivity()));
		strList.add("getDataState: " + String.valueOf(telephonyManager.getDataState()));
		strList.add("getNetworkType: " + String.valueOf(telephonyManager.getNetworkType()));
		strList.add("getPhoneType: " + String.valueOf(telephonyManager.getPhoneType()));
		strList.add("getSimState: " + String.valueOf(telephonyManager.getSimState()));
		strList.add("getDeviceSoftwareVersion: " + String.valueOf(telephonyManager.getDeviceSoftwareVersion()));
//		strList.add("getGroupIdLevel1:" + String.valueOf(telephonyManager.getGroupIdLevel1()));
		strList.add("getLine1Number:" + String.valueOf(telephonyManager.getLine1Number()));
//		strList.add("getMmsUAProfUrl:" + String.valueOf(telephonyManager.getMmsUAProfUrl()));
//		strList.add("getMmsUserAgent:" + String.valueOf(telephonyManager.getMmsUserAgent()));
		strList.add("getNetworkCountryIso:" + String.valueOf(telephonyManager.getNetworkCountryIso()));
		strList.add("getNetworkOperator:" + String.valueOf(telephonyManager.getNetworkOperator()));
		strList.add("getNetworkOperatorName:" + String.valueOf(telephonyManager.getNetworkOperatorName()));
		strList.add("getSimCountryIso:" + String.valueOf(telephonyManager.getSimCountryIso()));
		strList.add("getSimOperator:" + String.valueOf(telephonyManager.getSimOperator()));
		strList.add("getSimOperatorName:" + String.valueOf(telephonyManager.getSimOperatorName()));
		strList.add("getSimSerialNumber:" + String.valueOf(telephonyManager.getSimSerialNumber()));
		strList.add("getSubscriberId:" + String.valueOf(telephonyManager.getSubscriberId()));
		strList.add("getVoiceMailAlphaTag:" + String.valueOf(telephonyManager.getVoiceMailAlphaTag()));
		strList.add("getVoiceMailNumber:" + String.valueOf(telephonyManager.getVoiceMailNumber()));
//		List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
//		for(CellInfo info: cellInfoList){
//			strList.add("CellInfo:" + info.toString());
//		}
//		List<NeighboringCellInfo> neighboringCellInfo = telephonyManager.getNeighboringCellInfo();
//		for(NeighboringCellInfo info: neighboringCellInfo){
//			strList.add("NeighboringCellInfo:" + info.toString());
//		}

//		deviceinfo28.setText("INCREMENTAL:" + (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE).);
//		deviceinfo29.setText("INCREMENTAL:" + android.os.Build.VERSION.INCREMENTAL);
		
		strList.add("getVoiceMailNumber:" + String.valueOf(telephonyManager.getVoiceMailNumber()));
		
		
/* 容量 */
		strList.add("システム：getBlockCount() 総ブロック数");
		String path1 = Environment.getExternalStorageDirectory().getAbsolutePath();
		StatFs statFs1 = new StatFs(path1);
		long total1;
		if(Build.VERSION.SDK_INT < 18){
			 total1 = ((long)statFs1.getBlockSize() * (long)statFs1.getBlockCount()) / 1024;
        } else {
        	 total1 = (statFs1.getBlockSizeLong() * statFs1.getBlockCountLong()) / 1024;
        }
		DecimalFormat format_mem =   new DecimalFormat("#,###KB");
		strList.add(format_mem.format(total1));
		
		strList.add("システム：getAvailable() 空ブロック数");
		String path2 = Environment.getExternalStorageDirectory().getAbsolutePath();
		StatFs statFs2 = new StatFs(path2);
		long total2;
		if(Build.VERSION.SDK_INT < 18){
			 total2 = ((long)statFs2.getBlockSize() * (long)statFs2.getAvailableBlocks()) / 1024;
        }else{
        	 total2 = (statFs2.getBlockSizeLong() * statFs2.getAvailableBlocksLong()) / 1024;
        }
		strList.add(format_mem.format(total2));
		
		strList.add("システム：getFreeBlocks() スーパーユーザーが使用可能なブロック数");
		String path3 = Environment.getExternalStorageDirectory().getAbsolutePath();
		StatFs statFs3 = new StatFs(path3);
		long total3;
		if(Build.VERSION.SDK_INT < 18){
			 total3 = ((long)statFs3.getBlockSize() * (long)statFs3.getFreeBlocks()) / 1024;
        }else{
        	 total3 = (statFs3.getBlockSizeLong() * statFs3.getFreeBlocksLong()) / 1024;
        }
		strList.add(format_mem.format(total3));
		
		
		strList.add("保存領域：getBlockCount() 総ブロック数");
		String path4 = Environment.getDataDirectory().getPath();
		StatFs statFs4 = new StatFs(path4);
		long total4;
		if(Build.VERSION.SDK_INT < 18){
			 total4 = ((long)statFs4.getBlockSize() * (long)statFs4.getBlockCount()) / 1024;
        } else {
        	 total4 = (statFs4.getBlockSizeLong() * statFs4.getBlockCountLong()) / 1024;
        }
		strList.add(format_mem.format(total4));
		
		strList.add("保存領域：getAvailable() 空ブロック数");
		String path5 = Environment.getDataDirectory().getPath();
		StatFs statFs5 = new StatFs(path5);
		long total5;
		if(Build.VERSION.SDK_INT < 18){
			 total5 = ((long)statFs5.getBlockSize() * (long)statFs5.getAvailableBlocks()) / 1024;
        }else{
        	 total5 = (statFs5.getBlockSizeLong() * statFs5.getAvailableBlocksLong()) / 1024;
        }
		strList.add(format_mem.format(total5));
		
		strList.add("保存領域：getFreeBlocks() スーパーユーザーが使用可能なブロック数");
		String path6 = Environment.getDataDirectory().getPath();
		StatFs statFs6 = new StatFs(path6);
		long total6;
		if(Build.VERSION.SDK_INT < 18){
			 total6 = ((long)statFs6.getBlockSize() * (long)statFs6.getFreeBlocks()) / 1024;
        }else{
        	 total6 = (statFs6.getBlockSizeLong() * statFs6.getFreeBlocksLong()) / 1024;
        }
		strList.add(format_mem.format(total6));
		
		// 描画
		for(final String text: strList){
			TextView addText = new TextView(this);
			addText.setText(text);
			addText.setOnLongClickListener(new OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View v) {
					StringCopy(text);
					return false;
				}
			});
			
			scrollLinear.addView(addText);
		}
		
	}
	
	public void getAdId() {
	    Thread adIdThread = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            Info adInfo = null;
	            try {
	                adInfo = AdvertisingIdClient
	                        .getAdvertisingIdInfo(getApplicationContext());
	                final String id = adInfo.getId();
	                final boolean isLAT = adInfo.isLimitAdTrackingEnabled();
	                strList.add("AndroidAdID: " + id);
	                strList.add("OptoutFlag: " + String.valueOf(isLAT));
	            } catch (Exception e) {
	            }
	        }
	    });
	    adIdThread.start();
	}

	public void StringCopy(String copystr) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			clipboard.setText(copystr);
			// メッセージ
		} else {
			ClipData.Item item = new ClipData.Item(copystr);
			String[] mimeType = new String[1];
			/**
			 * MIMETYPE_TEXT_PLAIN テキストを表す定数 MIMETYPE_TEXT_INTENT Intentを表す定数
			 * MIMETYPE_TEXT_URILIST Uriを表す定数
			 */
			mimeType[0] = ClipDescription.MIMETYPE_TEXT_PLAIN;

			// クリップボードに格納するClipDataオブジェクトの作成
			ClipData cd = new ClipData(new ClipDescription("text_data", mimeType), item);
			// クリップボードにデータを格納
			ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			cm.setPrimaryClip(cd);

		}
		showToast("コピー："+ copystr); // URLをクリップボードにコピーしました
	}
	private void showToast(String text) {
		Toast.makeText(this.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
	}
}
