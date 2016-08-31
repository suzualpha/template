package com.suzualpha.templateapk;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;

public class KeyEventActivity extends Activity {
	
	TextView KeyEvent1;
	TextView KeyEvent2;
	TextView KeyEvent3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_key_event);
		
		KeyEvent1 = (TextView)findViewById(R.id.key_event1);
		KeyEvent2 = (TextView)findViewById(R.id.key_event2);
		KeyEvent3 = (TextView)findViewById(R.id.key_event3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.key_event, menu);
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
		// TODO 自動生成されたメソッド・スタブ
//		KeyEvent1.setText("[onKeyDonwn]¥n keyCode:" + String.valueOf(keyCode) + "¥n event:" + event.toString() );
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onKeyLongPress(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onKeyMultiple(keyCode, repeatCount, event);
	}

	@Override
	public boolean onKeyShortcut(int keyCode, KeyEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onKeyShortcut(keyCode, event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		KeyEvent3.setText("[onTouchEvent]¥n event:" + event.toString() );
		return super.onTouchEvent(event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		KeyEvent2.setText("[dispatchKeyEvent]¥n event:" + event.toString() );
		return super.dispatchKeyEvent(event);
	}

	@Override
	public boolean dispatchKeyShortcutEvent(KeyEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		return super.dispatchKeyShortcutEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		KeyEvent1.setText("[dispatchTouchEvent]¥n event:" + ev.toString() );
		return super.dispatchTouchEvent(ev);
	}
}
