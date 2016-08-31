package com.suzualpha.media;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;

import com.suzualpha.templateapk.R;

public class ContentProviderActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content_provider);
		
		ContentResolver resolver = this.getContentResolver();
		Cursor cursor = resolver.query(
				MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
				new String[] {
						BaseColumns._ID,
						
						MediaColumns.TITLE
//						,
//						MediaStore.Video.Media._COUNT,
//						MediaStore.Video.Media.ALBUM,
//						MediaStore.Video.Media.ARTIST,
//						MediaStore.Video.Media.CATEGORY,
//						MediaStore.Video.Media.DISPLAY_NAME,
//						MediaStore.Video.Media.WIDTH
				},    // keys for select. null means all
				null,
				null,
				null
				);
		
		while (cursor.moveToNext()) {
			Log.d("TEST", "====================================");
			Log.d("TEST", cursor.getString(cursor.getColumnIndex(BaseColumns._ID))); //アルバム名の取得
			Log.d("TEST", cursor.getString(cursor.getColumnIndex(MediaColumns.TITLE))); //アーティスト名の取得
//			Log.d("TEST", cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media._COUNT))); //タイトルの取得
//			Log.d("TEST", cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.ALBUM))); //タイトルの取得
//			Log.d("TEST", cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.ARTIST))); //タイトルの取得
//			Log.d("TEST", cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.CATEGORY))); //タイトルの取得
//			Log.d("TEST", cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME))); //タイトルの取得
//			Log.d("TEST", cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.WIDTH))); //タイトルの取得
		}
		
	}
	
}
