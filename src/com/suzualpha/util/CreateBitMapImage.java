package com.suzualpha.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * プロフィール画像用のBitmapデータを作成するクラス。
 */

public class CreateBitMapImage {

	// ビットマップに変換。
	/*
	 * public static Bitmap getBitmapFromURL(String src) { try { URL url = new
	 * URL(src); HttpURLConnection connection = (HttpURLConnection)
	 * url.openConnection(); connection.setDoInput(true); connection.connect();
	 * InputStream input = connection.getInputStream(); Bitmap myBitmap =
	 * BitmapFactory.decodeStream(input); return myBitmap; } catch (IOException
	 * e) { e.printStackTrace(); return null; } }
	 */

	// ビットマップ画像を丸くする。
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {

		// 画像が取れない場合はnullを返す。
		if (bitmap == null) {
			return null;
		}

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		// 縦横比の小さい方へ合わせる
		if (width > height) {
			width = height;
		}
		if (height > width) {
			height = width;
		}

		// 正方形になった縦横比のサイズでViewを作る
		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, width, height);
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {

		// 画像が取れない場合はnullを返す。
		if (bitmap == null) {
			return null;
		}

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		// 縦横比の小さい方へ合わせる
		if (width > height) {
			width = height;
		}
		if (height > width) {
			height = width;
		}

		// 正方形になった縦横比のサイズでViewを作る
		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, width, height);
		final RectF rectF = new RectF(rect);
		final float roundPx = 200;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}
}
