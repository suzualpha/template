package com.suzualpha.ber;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.suzualpha.templateapk.R;

public class FullScreenMainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		Window window = getWindow();
//		View view = window.getDecorView();
		//ナビゲーションバーを隠す場合はSYSTEM_UI_FLAG_HIDE_NAVIGATIONを使う
//		int visibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//		view.setSystemUiVisibility(visibility);
		setContentView(R.layout.activity_full_screen_main);
		ViewGroup root = (ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content);
//		LinearLayout rootLayout = (LinearLayout) findViewById(R.id.full_root);
		root.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);    // ナビゲーションを隠す;
		
	}
	
}
