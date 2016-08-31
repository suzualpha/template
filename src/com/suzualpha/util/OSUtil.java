package com.suzualpha.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

/**
 *	android   API
 * 		1.0 = 1		BASE
 *		1.1 = 2		BASE_1_1
 *		1.5 = 3		CUPCAKE
 *		1.6 = 4		DONUT
 *		2.0 = 5		ECLAIR
 *	  2.0.1 = 6		ECLAIR_0_1
 *		2.1 = 7		ECLAIR_MR1
 *		2.2 = 8		FROYO
 *		2.3 = 9		GINGERBREAD
 *	  2.3.3 = 10	GINGERBREAD_MR1
 *		3.0 = 11	HONEYCOMB
 * 		3.1 = 12	HONEYCOMB_MR1
 * 		3.2 = 13	HONEYCOMB_MR2
 * 		4.0 = 14	ICE_CREAM_SANDWICH
 * 	  4.0.3 = 15	ICE_CREAM_SANDWICH_MR1
 * 		4.1 = 16	JELLY_BEAN
 * 		4.2 = 17	JELLY_BEAN_MR1
 * 		4.3 = 18	JELLY_BEAN_MR2
 * 		4.4 = 19	KITKAT
 */

/**
 * androidシステムに関する補助クラス
 * @author ogawa
 */
public class OSUtil {
	
	/**
	 * APIが13以下ならfalseを返す（android4.0↑がtrue）
	 * @param OSver
	 * @return
	 */
	public static boolean switchAPI14(int OSver) {
		
		if (Build.VERSION_CODES.ICE_CREAM_SANDWICH <= OSver) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void backShowToast(final Activity activity, final String text){
		activity.runOnUiThread(new Runnable() {
		     @Override
		     public void run() {
		          Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
		     }
		});
		}
	
	/**
	 * APIが10以下ならfalseを返すandroid3.0↑がtrue）
	 * @param OSver
	 * @return
	 */
	public static boolean switchAPI11(int OSver) {
		if (Build.VERSION_CODES.HONEYCOMB <= OSver) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 指定されたKEYに含まれているメタデータを取得
	 * @param context
	 * @param key
	 * @return
	 */
	public static String getMetaData(Context context, String key) {
		ApplicationInfo appliInfo = null;
		try {
			appliInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
		}
		Log.d("MetaData", appliInfo.metaData.getString(key));
		return appliInfo.metaData.getString(key);
		
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void clipBoardCopy(String text, Context context) {
		if (!(text.equals(""))) {
			
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
				android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
				clipboard.setText(text);
				
			} else {
				
				ClipData.Item item = new ClipData.Item(text);
				String[] mimeType = new String[1];
				mimeType[0] = ClipDescription.MIMETYPE_TEXT_PLAIN;
				
				// クリップボードに格納するClipDataオブジェクトの作成
				ClipData cd = new ClipData(new ClipDescription("text_data",
						mimeType), item);
				// クリップボードにデータを格納
				ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
				cm.setPrimaryClip(cd);
			}
			
		} else {
			Toast.makeText(context, "コピー出来ませんでした", Toast.LENGTH_SHORT).show();
		}
	}
}
