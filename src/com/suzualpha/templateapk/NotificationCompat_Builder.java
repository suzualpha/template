package com.suzualpha.templateapk;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * Notification.Builderクラスをnewし、build()メソッドを使用する (APIレベル11以上 android3.0↑)
 * NotificationCompat.Builderクラスをnewし、build()メソッドを使用する (APIレベル4以上 android1.6↑)
 * サンプルではNotificationCompat.Builderを使用
 * 
 */

public class NotificationCompat_Builder extends Activity implements
		OnClickListener {

	Button BroadCastStartbutton;
	Button notiBtn2;
	Button notiBtn3;
	Button notiBtn4;
	Button notiBtn5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_notification_compat__builder);
		BroadCastStartbutton = (Button) findViewById(R.id.notification_nowpush_btn);
		BroadCastStartbutton.setOnClickListener(this);
		
		notiBtn2 = (Button) findViewById(R.id.notification_nowpush_btn2);
		notiBtn2.setOnClickListener(this);
		
		notiBtn3 = (Button) findViewById(R.id.notification_nowpush_btn3);
		notiBtn3.setOnClickListener(this);
		
		notiBtn4 = (Button) findViewById(R.id.notification_nowpush_btn4);
		notiBtn4.setOnClickListener(this);
		
		notiBtn5 = (Button) findViewById(R.id.notification_nowpush_btn5);
		notiBtn5.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v == BroadCastStartbutton) {
			// NotificationManager notificationManager = (NotificationManager)
			// getSystemService(NOTIFICATION_SERVICE);
			// Intent notificationIntent = new Intent(this,
			// NotificationCompat_Builder.class);
			Intent intent = new Intent(this, NotificationReceiver.class);
			intent.putExtra("notinumber", 1);

			/**
			 * いつIntentを飛とばすか時刻の設定 getActivity(Context, int, Intent, int)
			 * 新たなActivityを開始するためのPendingIntentを取得する getBroadcast(Context, int,
			 * Intent, int) 新たなBroadcastReceiverを開始するためのPendingIntentを取得する
			 * getService(Context, int, Intent, int)
			 * 新たなService開始するためのPendingIntentを取得する
			 * 
			 * PendingIntent.FLAG_UPDATE_CURRENT 新たに通知した場合に通知内容をアップデート
			 */
			PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent,
					0);
			// PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
			// notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			// PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
			// notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			// PendingIntent contentIntent = PendingIntent.getActivity(context,
			// requestCode, intent, flags)

			/*
			 * Notification notification = new NotificationCompat.Builder(this)
			 * 
			 * 
			 * .setSmallIcon(R.drawable.ic_launcher) // アイコン .setTicker("Hello")
			 * // 通知バーに表示する簡易メッセージ .setWhen(System.currentTimeMillis()) //
			 * ノティフィケーション時に表示される時間 .setContentTitle("My notification") //
			 * 展開メッセージのタイトル .setContentText("Hello Notification!!") //
			 * 展開メッセージの詳細メッセージ .setContentIntent(contentIntent) // PendingIntent
			 * .setSound(RingtoneManager.getDefaultUri(RingtoneManager.
			 * TYPE_NOTIFICATION)) // 音の設定 .setVibrate(new long[]{1000, 1000,
			 * 1000, 1000, 1000}) // バイブレーションの設定 // .setLights(Color.RED, 0, 1);
			 * // LEDの設定 Notification.Builder使用のためAPI11以上 .setLights(0xff00ff00,
			 * 300, 100) // .setAutoCancel(true) // クリックした時に通知を消す .build();
			 */

			/**
			 * 通知の際にバイブさせるには上記のようにミリ秒単位でlong配列に、 {"ディレイ時間", "ON時間", "OFF時間",
			 * "ON時間", "OFF時間", …}というように記述 ↓の例は0.1秒間振動するのを3回繰り返す
			 * 
			 * 振動だけさせたい場合は Vibrator で実装 Vibrator vibrator =
			 * (Vibrator)getSystemService(VIBRATOR_SERVICE);
			 * 
			 * どちらの場合でも AndroidManifest.xml に以下のパーミッションが必要です。 <uses-permission
			 * android:name="android.permission.VIBRATE" />
			 */
			// notification.vibrate = new long[]{0, 100, 100, 100, 100, 100}; //
			// バイブレーションの設定

			/**
			 * 第1引数にはアプリケーション内で一意となるNotification用のIDを、
			 * 第2引数にはNotificationオブジェクトを指定します。 このIDはNotificationの更新が必要な場合、
			 * または通知メッセージをクリックすることでユーザーがアプリケーションに戻ってきたときに、
			 * どのNotificationがクリックされたかを識別するために使用することができます。
			 */
			// notificationManager.notify(1, notification);

			// アラームマネージャの用意（初回は5秒後,そのあとは20秒毎に実行）
			long firstTime = SystemClock.elapsedRealtime();
			firstTime += 10 * 1000;
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			// am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
			// 20 * 1000, sender);
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, sender);
			
			
		}
		
		if (v == notiBtn2) {
			Intent intent = new Intent(this, NotificationReceiver.class);
			intent.putExtra("notinumber", 2);
			PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
			long firstTime = SystemClock.elapsedRealtime();
			firstTime += 10 * 1000;
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, sender);
		}
		
		if (v == notiBtn3) {
			Intent intent = new Intent(this, NotificationReceiver.class);
			intent.putExtra("notinumber", 3);
			PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
			long firstTime = SystemClock.elapsedRealtime();
			firstTime += 10 * 1000;
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, sender);
		}
		
		if (v == notiBtn4) {
			Intent intent = new Intent(this, NotificationReceiver.class);
			intent.putExtra("notinumber", 4);
			PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
			long firstTime = SystemClock.elapsedRealtime();
			firstTime += 10 * 1000;
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, sender);
		}
		
		if (v == notiBtn5) {
			Intent intent = new Intent(this, NotificationReceiver.class);
			intent.putExtra("notinumber", 5);
			PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
			long firstTime = SystemClock.elapsedRealtime();
			firstTime += 10 * 1000;
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, sender);
		}
	}
}

/*
 * class DoActionReceiver extends BroadcastReceiver {
 * 
 * @Override public void onReceive(Context ctx, Intent intent) {
 * NotificationManager notificationManager = (NotificationManager)
 * ctx.getSystemService(Context.NOTIFICATION_SERVICE);
 * 
 * // 通知をクリックされたときのintent設定 Intent notificationIntent = new Intent(ctx,
 * MainActivity.class); PendingIntent contentIntent =
 * PendingIntent.getActivity(ctx, 0, notificationIntent,
 * PendingIntent.FLAG_UPDATE_CURRENT);
 * 
 * Notification notification = new
 * NotificationCompat.Builder(ctx).setSmallIcon(R.drawable.ic_launcher) // アイコン
 * .setTicker("Hello") // 通知バーに表示する簡易メッセージ .setWhen(System.currentTimeMillis())
 * // ノティフィケーション時に表示される時間 .setContentTitle("My notification") // 展開メッセージのタイトル
 * .setContentText("Hello Notification!!") // 展開メッセージの詳細メッセージ
 * .setContentIntent(contentIntent) // PendingIntent
 * .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
 * // 音の設定 .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}) // バイブレーションの設定
 * // .setLights(Color.RED, 0, 1); // LEDの設定 Notification.Builder使用のためAPI11以上
 * .setLights(0xff00ff00, 300, 100) // .setAutoCancel(true) // クリックした時に通知を消す
 * .build();
 * 
 * // 古い通知をクリアし、最新の情報を通知する // notificationManager.cancelAll();
 * notificationManager.notify(1, notification); }
 * 
 * }
 */
/*
 * LEDに表示できる色 Notification notif = new Notification(); Spinner spinner =
 * (Spinner)findViewById(R.id.spinner); int index =
 * spinner.getSelectedItemPosition() + 1; switch (index) { case 1: //赤
 * notif.ledARGB = 0xffff0000; break; case 2: //緑 notif.ledARGB = 0xff00ff00;
 * break; case 3: //青 notif.ledARGB = 0xff0000ff; break; case 4: //点滅しない
 * notif.ledARGB = 0xff000000; break; case 5: //本来は黄色のはずだが・・・朱色？ notif.ledARGB =
 * 0xffffff00; break; case 6: //紫 notif.ledARGB = 0xffff00ff; break; case 7:
 * //水色 notif.ledARGB = 0xff00ffff; break; case 8: //白のはずだけど、青紫。 notif.ledARGB =
 * 0xffffffff; break; case 9: //case 1の赤と変わらず。 notif.ledARGB = 0x99ff0000;
 * break; case 10: //case 1の赤と変わらず。 notif.ledARGB = 0x33ff0000; break; default:
 * break; } notif.ledOnMS = 300; notif.ledOffMS = 1000; notif.flags |=
 * Notification.FLAG_SHOW_LIGHTS; notif.defaults |= Notification.DEFAULT_LIGHTS;
 * NotificationManager nm =
 * (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
 * nm.notify(R.string.app_name, notif);
 */
