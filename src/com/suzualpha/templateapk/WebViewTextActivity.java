package com.suzualpha.templateapk;

import java.net.URI;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

public class WebViewTextActivity extends Activity {
	
	WebView web1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view_text);
		
		web1 = (WebView)findViewById(R.id.webviewtext1);
		web1.setWebViewClient(new WebViewClientTest());
		
		web1.loadUrl("file:///android_asset/NewFile.html");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web_view_text, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("onKeyDown", event.toString());
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		Log.d("onKeyLongPress", event.toString());
		return super.onKeyLongPress(keyCode, event);
	}

	@Override
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		Log.d("onKeyMultiple", event.toString());
		return super.onKeyMultiple(keyCode, repeatCount, event);
	}

	@Override
	public boolean onKeyShortcut(int keyCode, KeyEvent event) {
		Log.d("onKeyShortcut", event.toString());
		return super.onKeyShortcut(keyCode, event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		Log.d("dispatchKeyEvent", event.toString());
		return super.dispatchKeyEvent(event);
	}

	@Override
	public boolean dispatchKeyShortcutEvent(KeyEvent event) {
		Log.d("dispatchKeyShortcutEvent", event.toString());
		return super.dispatchKeyShortcutEvent(event);
	}
}
	class WebViewClientTest extends WebViewClient {
        @Override
		public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        	Log.d("shouldOverrideKeyEvent", event.toString());
			return super.shouldOverrideKeyEvent(view, event);
		}

		@Override
		public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
			Log.d("onUnhandledKeyEvent", event.toString());
			super.onUnhandledKeyEvent(view, event);
		}

	
}
