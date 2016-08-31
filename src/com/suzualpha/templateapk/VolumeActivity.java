package com.suzualpha.templateapk;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.TextView;

public class VolumeActivity extends Activity {
	TextView int1;
	TextView int2;
	TextView int3;
	TextView int4;
	TextView int5;
	TextView int6;
	TextView int7;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volume);
		
		int1 = (TextView)findViewById(R.id.vol1);
		int2 = (TextView)findViewById(R.id.vol2);
		int3 = (TextView)findViewById(R.id.vol3);
		int4 = (TextView)findViewById(R.id.vol4);
		int5 = (TextView)findViewById(R.id.vol5);
		int6 = (TextView)findViewById(R.id.vol6);
		int7 = (TextView)findViewById(R.id.vol7);
	}

	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		int1.setText(String.valueOf(am.getStreamVolume(AudioManager.STREAM_ALARM)));
		int2.setText(String.valueOf(am.getStreamVolume(AudioManager.STREAM_DTMF)));
		int3.setText(String.valueOf(am.getStreamVolume(AudioManager.STREAM_MUSIC)));
		int4.setText(String.valueOf(am.getStreamVolume(AudioManager.STREAM_NOTIFICATION)));
		int5.setText(String.valueOf(am.getStreamVolume(AudioManager.STREAM_RING)));
		int6.setText(String.valueOf(am.getStreamVolume(AudioManager.STREAM_SYSTEM)));
		int7.setText(String.valueOf(am.getStreamVolume(AudioManager.STREAM_VOICE_CALL)));
		
		
		
		
		
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}


}
