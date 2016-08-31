package com.suzualpha.templateapk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

/**
 * 
 * @author ogawa アクティビティのライフサイクルとScreenオン・オフをチェックするクラス
 */
public class ActivityLifeCycle extends Activity {

	PowerManager pawerManager;

	// Activity開始時に呼ばれる
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activitylifecycle);
		pawerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
		Log.d("onCreate", "SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
	}

	// onCreate、onRestartの次に呼ばれる
	@Override
	protected void onStart() {
		super.onStart();
		Log.d("onStart", "SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
		Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
	}

	// onStartの次、画面復帰時に呼ばれる
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("onResume", "SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
		Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
	}

	// 他画面への遷移時に最初に呼ばれる
	@Override
	protected void onPause() {
		super.onPause();
		Log.d("onPause", "SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
		Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
	}

	// 全画面を覆う画面遷移時 onPauseの次に呼ばれる データセーブ処理などは基本ここ
	@Override
	protected void onStop() {
		super.onStop();
		Log.d("onStop", "SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
		Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
	}

	// 全画面を覆う画面遷移から復帰時に呼ばれる
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("onRestart",
				"SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
		Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
	}

	// アクティビティ終了時に呼ばれる
	// finish()メソッドが呼ばれた時に onPause→onStop→onDestroyの順番で呼ばれる
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("onDestroy",
				"SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
		Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
	}

	// バッテリーが少なくなった時に呼ばれる30%?
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		Log.d("onLowMemory",
				"SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
		Toast.makeText(this, "onLowMemory", Toast.LENGTH_SHORT).show();
	}

	// システムのバックキーが押された時に呼ばれる
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Log.d("onBackPressed",
				"SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
		Toast.makeText(this, "onBackPressed", Toast.LENGTH_SHORT).show();
	}

	// ホームボタンor他画面へ遷移時に呼ばれる
	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		Log.d("onUserLeaveHint",
				"SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
		Toast.makeText(this, "onUserLeaveHint", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.d("onNewIntent",
				"SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
		Toast.makeText(this, "onNewIntent", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.powermanager, menu);
		return true;
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d("onRestoreInstanceState",
				"SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
		Toast.makeText(this, "onRestoreInstanceState", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d("onSaveInstanceState",
				"SCREEN:" + String.valueOf(pawerManager.isScreenOn()));
		Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show();
	}

	// layout.xmlのview内定義onClickに定義されているメソッドが呼ばれる（今回の場合はonDialogがlayout内で定義されている）
	public void onDialog(View v) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// アラートダイアログのタイトルを設定します
		alertDialogBuilder.setTitle("タイトル");
		// アラートダイアログのメッセージを設定します
		alertDialogBuilder.setMessage("メッセージ");
		// アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
		alertDialogBuilder.setPositiveButton("肯定",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		// アラートダイアログの中立ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
		alertDialogBuilder.setNeutralButton("中立",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		// アラートダイアログの否定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
		alertDialogBuilder.setNegativeButton("否定",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		// アラートダイアログのキャンセルが可能かどうかを設定します
		alertDialogBuilder.setCancelable(true);
		AlertDialog alertDialog = alertDialogBuilder.create();
		// アラートダイアログを表示します
		alertDialog.show();
	}

	public void onStringIntent(View v) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "テストテキスト");
		startActivity(intent);
	}

}
