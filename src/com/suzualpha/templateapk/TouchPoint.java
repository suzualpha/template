package com.suzualpha.templateapk;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.TextView;

public class TouchPoint extends Activity {

	TextView downXText;
	TextView downYText;
	TextView upXText;
	TextView upYText;
	TextView moveXText;
	TextView moveYText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch_point);

		downXText = (TextView) findViewById(R.id.touchdown_x_text);
		downYText = (TextView) findViewById(R.id.touchdown_y_text);
		upXText = (TextView) findViewById(R.id.touchup_x_text);
		upYText = (TextView) findViewById(R.id.touchup_y_text);
		moveXText = (TextView) findViewById(R.id.touchmove_x_text);
		moveYText = (TextView) findViewById(R.id.touchmove_y_text);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		String action = "";
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downXText.setText(String.valueOf(event.getX()));
			downYText.setText(String.valueOf(event.getY()));
			break;
		case MotionEvent.ACTION_UP:
			upXText.setText(String.valueOf(event.getX()));
			upYText.setText(String.valueOf(event.getY()));
			break;

		case MotionEvent.ACTION_MOVE:
			moveXText.setText(String.valueOf(event.getX()));
			moveYText.setText(String.valueOf(event.getY()));
			break;
		case MotionEvent.ACTION_CANCEL:
			action = "ACTION_CANCEL";
			break;
		}

		return super.onTouchEvent(event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.touch_point, menu);
		return true;
	}

}
