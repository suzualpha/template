package com.suzualpha.templateapk;

import android.R.color;
import android.R.style;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
	Intent notificationIntent;

	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context ctx, Intent intent) {

		NotificationManager notificationManager = (NotificationManager) ctx
				.getSystemService(Context.NOTIFICATION_SERVICE);

		// if (android.os.Build.VERSION.SDK_INT <
		// android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {

		// 通知をクリックされたときのintent設定

		try {
			PackageManager pm = ctx.getPackageManager();
			notificationIntent = pm.getLaunchIntentForPackage("jp.gungho.pad");
		} catch (Exception e) {

			Toast.makeText(ctx, "対象のアプリがありません", Toast.LENGTH_SHORT).show();

		}

		int btnNumber = intent.getIntExtra("notinumber", 0);

		if (btnNumber == 1) {

			Intent notificationIntent = new Intent(ctx, MainActivity.class);
			// Intent notificationIntent = new Intent(ctx,
			// "jp.gungho.pad/jp.gungho.pad.AppDelegate");
			PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, notificationIntent,
					PendingIntent.FLAG_UPDATE_CURRENT);

			PowerManager.WakeLock wakelock = ((PowerManager) ctx.getSystemService(Context.POWER_SERVICE))
					.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP
							| PowerManager.ON_AFTER_RELEASE, "disableLock");
			wakelock.acquire(15000);// 15秒後にスリープ
			Vibrator vibrator = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(1000);
			wakelock.release();

			Resources r = ctx.getResources();
			Bitmap bmp = BitmapFactory.decodeResource(r, R.drawable.icon04);

			Builder builder = new NotificationCompat.Builder(ctx);
			builder.setTicker("A");
			builder.setContentTitle("B");
			builder.setContentText("C");
			builder.setContentInfo("D");
			builder.setWhen(System.currentTimeMillis());
			builder.setContentIntent(contentIntent);
			builder.setSmallIcon(R.drawable.ic_launcher);
			builder.setLights(0xffff0000, 1000, 1000);
			builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
			builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bmp));
			builder.setSubText("E");
			builder.setLargeIcon(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.icon02));
			// builder.setStyle(new
			// Notification.BigPictureStyle().bigPicture(b))
			Notification notification = builder.build();

			notificationManager.notify(0, notification);
		}
		
		if (btnNumber == 2) {
			Intent notificationIntent = new Intent(ctx, MainActivity.class);
			
	        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
	                .addLine("First line")
	                .addLine("Second line")
	                .setBigContentTitle("Multiple Content")
	                .setSummaryText("+2 Messages");
			
		        PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, notificationIntent, 0);
		        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx);
		        builder.setPriority(NotificationCompat.PRIORITY_MAX);
//		        builder.setCategory(NotificationCompat.CATEGORY_MESSAGE);
//		        builder.setStyle(inboxStyle);
		        builder.setSmallIcon(R.drawable.ic_launcher);
//		        builder.setContentIntent(pendingIntent);
//		        builder.setColor(color.white);
//		        builder.setAutoCancel(true);
//		        builder.setLargeIcon(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.ic_launcher));
		        builder.setContentTitle("BasicNotifications Sample");
		        builder.setContentText("Time to learn about notifications!");
//		        builder.setSubText("Tap to view documentation about notifications.");
		        notificationManager.notify(2, builder.build());
		}

		// Intent intent = new
		// Intent(this,jp.tuyano.sample.MySampleActivity.class);

		/*
		 * // ISW11F でライト、バイブ共に動作 Nexus5は動作しない。 Notification notify = new
		 * Notification(); notify.flags = Notification.FLAG_AUTO_CANCEL;
		 * notify.flags = Notification.FLAG_SHOW_LIGHTS; notify.icon =
		 * R.drawable.ic_launcher; notify.ledARGB = 0xff0000ff; notify.ledOnMS =
		 * 500; notify.ledOffMS = 500; notify.vibrate = new long[]{0, 100, 300,
		 * 1000}; notify.setLatestEventInfo(ctx, "TEST INFO",
		 * "テストのノーティフィケーションです。", contentIntent);
		 * 
		 * notificationManager.notify(0, notify);
		 */

		/*
		 * Notification notification = new
		 * NotificationCompat.Builder(ctx).setSmallIcon(R.drawable.ic_launcher)
		 * // アイコン // 大きいアイコンの設定 .setTicker("Hello") // 通知バーに表示する簡易メッセージ
		 * .setWhen(System.currentTimeMillis()) // ノティフィケーション時に表示される時間
		 * .setContentTitle("My notification") // 展開メッセージのタイトル .setContentText(
		 * "Hello Notification!!") // 展開メッセージの詳細メッセージ
		 * .setContentIntent(contentIntent) // PendingIntent
		 * .setSound(RingtoneManager
		 * .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) // 音の設定
		 * .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000}) // バイブレーションの設定
		 * // .setLights(Color.RED, 0, 1); // LEDの設定
		 * Notification.Builder使用のためAPI11以上？ // .setLights(0xffff0000, 3000,
		 * 1000) // // .setNumber(5); // API11以上？ .setAutoCancel(true) //
		 * クリックした時に通知を消す .build();
		 * 
		 * notification.flags = Notification.FLAG_SHOW_LIGHTS;
		 * notification.ledARGB = 0xff00ff00; notification.ledOnMS = 500;
		 * notification.ledOffMS = 500;
		 * 
		 * notification.number = 5; // ステータスバー上のアイコンに重ねて表示される文字列;
		 * 
		 * // notificationManager.cancelAll(); // 古い通知をクリアし、最新の情報を通知する
		 * 
		 * notificationManager.notify(1, notification);
		 */
		/*
		 * } else {
		 * 
		 * Notification notification; Intent intent2 = new Intent(ctx,
		 * MainActivity.class);
		 * 
		 * PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0,
		 * intent2, PendingIntent.FLAG_UPDATE_CURRENT); //
		 * ActionButtonをタッチした時に呼び出されるPendingIntent
		 * 
		 * notification = new Notification.BigPictureStyle(//
		 * BigPictureStyleを利用したNotificationの作成 //従来の手法でNotificationを作成 new
		 * Notification .Builder(ctx).setContentTitle("Notify TItle"
		 * ).setContentText ("sub title") //
		 * .setSmallIcon(R.drawable.ic_launcher) //
		 * .setLargeIcon(BitmapFactory.decodeResource(ctx.getResources(),
		 * R.drawable.ic_launcher)) // .addAction(R.drawable.ic_launcher,
		 * "ticker title", contentIntent) // .addAction(R.drawable.ic_launcher,
		 * "ticker title2", contentIntent)) //
		 * .bigPicture(BitmapFactory.decodeResource(ctx.getResources(),
		 * R.drawable.ic_launcher)) // BigPictureの設定を追加 .build();
		 * notificationManager.notify(0x01, notification);//
		 * NotificationManagerからnotificationの呼び出し
		 * 
		 * }
		 */

	}
}

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
