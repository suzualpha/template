package com.suzualpha.animation;

import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.suzualpha.templateapk.R;
import com.suzualpha.util.AnimationUtil;
import com.suzualpha.util.CreateBitMapImage;

public class LayoutAnimation2 extends Activity {

	private final static int WC = RelativeLayout.LayoutParams.WRAP_CONTENT;

	View view1;
	RelativeLayout layout;
	RelativeLayout alfaLayout;
	int viewId = 1;
	ImageView iv;
	int width;
	int height;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout_animation2);

		Display display = getWindowManager().getDefaultDisplay();
		Point point = new Point();
		overrideGetSize(display, point);
		width = point.x;
		height = point.y;

		iv = (ImageView) findViewById(R.id.layout_animation2_imageview);
//		int takasa = iv.getHeight();
//		int yoko = iv.getWidth();
		// Toast.makeText(this, "taka:" + String.valueOf(takasa) + " yoko:"+
		// String.valueOf(yoko), Toast.LENGTH_SHORT).show();
//		alfaLayout = (RelativeLayout) findViewById(R.id.layout_animation2_alpha);
		// alfaLayout.setVisibility(View.VISIBLE);
//		layout = (RelativeLayout) getWindow().getDecorView().findViewById(
//				R.id.layout_animation2_root);

		// AnimationUtil.minAnimation(layout);
		// AnimationUtil.animationSet(layout, width / 2, height / 2,
		// alfaLayout);
		// alfaLayout.setVisibility(View.VISIBLE);
		// AnimationUtil.alfaAnimation(alfaLayout);

		// iv.setDrawingCacheEnabled(false);
		// iv.setDrawingCacheEnabled(true);

		// iv.setDrawingCacheEnabled(true); // キャッシュを取得する設定にする
		// iv.destroyDrawingCache(); // 既存のキャッシュをクリアする

		// iv.setImageDrawable(getResources().getDrawable(R.drawable.icon04));
		// Bitmap bmp = iv.getDrawingCache();
		// Bitmap bitmap1 = Bitmap.createBitmap(iv.getDrawingCache());
		// BitmapDrawable bmp = (BitmapDrawable)iv.getDrawable();
		// Bitmap bitmap2 =CreateBitMapImage.getRoundedCornerBitmap(bmp);
		// iv.setImageBitmap(bmp);
		// Drawable drowa = new BitmapDrawable(getResources(),bitmap2);
		// layout.setBackgroundDrawable(drowa);
		//

		// Bitmap bmp = Bitmap.createBitmap(width/3, height/3,
		// Config.ARGB_8888);
		// view のサイズで Bitmap を作成
		// Canvas canvas = new Canvas(bmp); // bmp をターゲットにした Canvas を作成

		// iv.draw(canvas);
		// layout.setBackground(null);
		// iv.setImageBitmap(getViewBitmap(iv));

		/*
		 * Bitmap bb =
		 * CreateBitMapImage.getRoundedCornerBitmap(loadBitmapFromView(iv),
		 * 100);
		 *
		 * iv.setImageBitmap(bb);
		 *
		 * AnimationUtil.minAnimation(iv); AnimationUtil.animationSet(iv, width
		 * / 2, height / 2, alfaLayout);
		 */

		/*
		 *
		 *
		 * iv.setDrawingCacheEnabled(true);
		 *
		 * // this is the important code :) // Without it the view will have a
		 * dimension of 0,0 and the bitmap will be null
		 * iv.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
		 * MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		 * iv.layout(0, 0, iv.getMeasuredWidth(), iv.getMeasuredHeight());
		 *
		 * iv.buildDrawingCache(true); Bitmap b =
		 * Bitmap.createBitmap(iv.getDrawingCache());
		 * iv.setDrawingCacheEnabled(false); // clear drawing cache Bitmap bb
		 * =CreateBitMapImage.getRoundedCornerBitmap(b,50);
		 * iv.setImageBitmap(bb);
		 */

		// alfaLayout.setBackgroundDrawable(getR);

		// View layout = layout.getRootView();
		// layout.setDrawingCacheEnabled(true);
		// layout . setDrawingCacheBackgroundColor ( Color . WHITE );
		// Bitmap bitmap = layout.getDrawingCache();
		// iv.setImageBitmap(bitmap);
		// MediaStore.Images.Media.insertImage(getContentResolver(), bitmap,
		// null, null);

		// Bitmap bitmap3 = getScreenBitmap(iv);
		// Bitmap bitmap4 = CreateBitMapImage.getRoundedCornerBitmap(bitmap3);
		// iv.setImageBitmap(bitmap4);
	}

	/*
	 * public boolean onKeyDown(int keyCode, KeyEvent event) {
	 *
	 * //==== キーコード判定 ====//
	 *
	 * if (keyCode == KeyEvent.KEYCODE_BACK) {
	 *
	 * Bitmap bb =
	 * CreateBitMapImage.getRoundedCornerBitmap(loadBitmapFromView(iv), 200);
	 *
	 * iv.setImageBitmap(bb);
	 *
	 * AnimationUtil.minAnimation(iv); AnimationUtil.animationSet(iv, width / 2,
	 * height / 2, alfaLayout); return false;
	 *
	 * }
	 *
	 *
	 *
	 * return super.onKeyDown(keyCode, event);
	 *
	 * }
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		String action = "";
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			action = "ACTION_DOWN";

			Bitmap bb = CreateBitMapImage.getRoundedCornerBitmap(
					loadBitmapFromView(iv), 200);

			iv.setImageBitmap(bb);

//			AnimationUtil.minAnimation(iv);
			AnimationUtil.animationSet(iv, width / 2, height / 2);

			break;
		case MotionEvent.ACTION_UP:
			action = "ACTION_UP";
			break;
		case MotionEvent.ACTION_MOVE:
			action = "ACTION_MOVE";
			break;
		case MotionEvent.ACTION_CANCEL:
			action = "ACTION_CANCEL";
			break;
		}

		return super.onTouchEvent(event);
	}

	public static Bitmap loadBitmapFromView(View v) {

		int yoko = 300; // 50
		int tate = 510; // 80
		Bitmap b = Bitmap.createBitmap(yoko, tate, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		v.layout(0, 0, yoko, tate);
		v.draw(c);
		return b;
	}

	public Bitmap getViewBitmap(View view) {
		view.setDrawingCacheEnabled(true);
		Bitmap cache = view.getDrawingCache();
		if (cache == null) {
			return null;
		}
		Bitmap bitmap = Bitmap.createBitmap(cache);
		view.setDrawingCacheEnabled(false);
		return bitmap;
	}

	public Bitmap getScreenBitmap(View view) {
		return getViewBitmap(view.getRootView());
	}

	public void ImageCreate() {

		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(WC,
				WC);
		param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, viewId);
		param.addRule(RelativeLayout.CENTER_HORIZONTAL, viewId);

		// ImageView iv = new ImageView(this);

		ImageView iv = (ImageView) getLayoutInflater().inflate(
				R.layout.balloon1, null);
		layout.addView(iv, param);

		AnimationUtil.migiueAnimation(iv, 0, 50, 100, 0);
		viewId++;
	}

	void overrideGetSize(Display display, Point outSize) {
		try {
			Class pointClass = Class.forName("android.graphics.Point");
			Method newGetSize = Display.class.getMethod("getSize",
					new Class[] { pointClass });
			newGetSize.invoke(display, outSize);
		} catch (Exception ex) {
			outSize.x = display.getWidth();
			outSize.y = display.getHeight();
		}
	}
}
