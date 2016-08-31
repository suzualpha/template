package com.suzualpha.templateapk;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SpaceToSS extends Activity {
	private static final int KB = 1024;
	private static final int MB = 1024 * KB;
	 
	private long available; // 空き容量
	private long used; // 使用中
	private long size; // 最大容量
	
	TextView nokoriSpace;
	TextView nokoriAtai;
	EditText Kaisuu;
	EditText spaceFileName;
	Button JikkouBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_space_to_ss);

		nokoriAtai = (TextView) findViewById(R.id.spaceText2);
		Kaisuu = (EditText) findViewById(R.id.spaceText4);
		spaceFileName = (EditText) findViewById(R.id.spaceFileName);
		spaceFileName.setText("dummny");
		Kaisuu.setText("10");

		SpaceReload();

	}
	
	public void SpaceReload(){
		DecimalFormat format_mem = new DecimalFormat("#,###KB");

		String path5 = Environment.getDataDirectory().getPath();
		StatFs statFs5 = new StatFs(path5);
		long total5;
		if (Build.VERSION.SDK_INT < 18) {
			total5 = ((long) statFs5.getBlockSize() * (long) statFs5.getAvailableBlocks()) / 1024;
		} else {
			total5 = (statFs5.getBlockSizeLong() * statFs5.getAvailableBlocksLong()) / 1024;
		}
		nokoriAtai.setText(format_mem.format(total5));
		
		
	}

	public void OnSSsave(View v) {
		int counts = Integer.valueOf(Kaisuu.getText().toString());
//		for (int i = 0; i < counts; i++) {
//			try {
//				savePngLocalStorage("test"+ String.valueOf(i)+ ".png", getScreenBitmap(this.getWindow().getDecorView()), this);
//			} catch (IOException e) {
//				// TODO 自動生成された catch ブロック
//				e.printStackTrace();
//			}
//		}
		
		saveFileToInternalStorage(counts);
	}

	public void onCopy(View v) {

	}

	public Bitmap getScreenBitmap(View view) {
		return getViewBitmap(view.getRootView());
	}

	public Bitmap getViewBitmap(View view) {
		view.setDrawingCacheEnabled(true);
		Bitmap cache = view.getDrawingCache();
		if (cache == null) {
			return null;
		}
		Bitmap bitmap = Bitmap.createBitmap(cache);
		view.setDrawingCacheEnabled(false);
		return bitmap;
	}

	// 内部ストレージに、画像ファイルを保存する(png) (Android 用)
	public static final boolean savePngLocalStorage(String fileName, Bitmap bitmap, Context context)
			throws IOException {
		BufferedOutputStream bos = null;
		Bitmap tmp = null;
		try {
			bos = new BufferedOutputStream(context.openFileOutput(fileName, Context.MODE_PRIVATE)); // 他アプリアクセス不可
			tmp = bitmap.copy(Config.ARGB_8888, true);
			return tmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
		} finally {
			if (tmp != null) {
				tmp.recycle();
				tmp = null;
			}
			try {
				bos.close();
			} catch (Exception e) {
				// IOException, NullPointerException
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void getInternalStorageStatus() {
	    File path = Environment.getDataDirectory();
	    StatFs stat = new StatFs(path.getPath());
	    available = stat.getAvailableBlocks() * stat.getBlockSize();
//	    size = stat.getBlockCount() * stat.getBlockSize();
//	    used = maxSize - availableInternalStorage;
	}
	 
	/** 
	 * 指定サイズ(MB単位)のファイルを内部ストレージへ保存する
	 * @param long MBでファイルサイズを指定(1MBのファイルなら1と指定)
	 * @return true 保存成功
	 */
	public boolean saveFileToInternalStorage(long fileSizeMB) {
//	    File path = Environment.getDataDirectory();
//	    StatFs stat = new StatFs(path.getPath());
//	    available = stat.getAvailableBlocks() * stat.getBlockSize();
	    
	    String path5 = Environment.getDataDirectory().getPath();
		StatFs statFs5 = new StatFs(path5);
		long total5;
		if (Build.VERSION.SDK_INT < 18) {
			total5 = ((long) statFs5.getBlockSize() * (long) statFs5.getAvailableBlocks()) ;
		} else {
			total5 = (statFs5.getBlockSizeLong() * statFs5.getAvailableBlocksLong()) ;
		}
	    
	    String test = String.valueOf(total5 / MB);
	    Toast.makeText(this, "空き容量:" + test + "MB", Toast.LENGTH_SHORT).show();
	    // 保存可能領域が足りない
	    if(fileSizeMB > total5 / MB) {
	    	Toast.makeText(this, "領域がたりない 入力:" + String.valueOf(fileSizeMB) + "MB　空き容量:" + test, Toast.LENGTH_SHORT).show();
	        return false;
	    }
	    // 1MB分の文字列生成
	    char[] chars = new char[1 * MB];
	    Arrays.fill(chars, 'x');
	    String mbString = new String(chars);
	 
	    // ファイル保存開始
	    try {
	        FileOutputStream fos = openFileOutput(spaceFileName.getText().toString() + ".txt", MODE_PRIVATE);
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	        for(int i = 0; i < fileSizeMB; i++) {
	            // 文字列の書き込み
	            bw.append(mbString);
	            bw.flush();
	        }
	        bw.close();
	        fos.flush();
	        fos.close();
	        Toast.makeText(this, "保存したよ", Toast.LENGTH_SHORT).show();
	        SpaceReload();
	    } catch(Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	    return true;
	}

}
