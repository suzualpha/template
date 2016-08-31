package com.suzualpha.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.suzualpha.templateapk.R;
import com.suzualpha.util.AnimationUtil;

public class LayoutAnimation1 extends Activity {

	private final static int WC = RelativeLayout.LayoutParams.WRAP_CONTENT;

	View view1;
	RelativeLayout layout;
	int viewId = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout_animation1);

		view1 = findViewById(R.id.layout_animation_view1);

		AnimationUtil.migiueAnimation(view1, 0, 50, 100, 0);

		// ImageCreate();
		layout = (RelativeLayout) findViewById(R.id.layout_animation_root);
		for (int i = 0; i < 100; i++) {
			ImageCreate();
		}
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
}
