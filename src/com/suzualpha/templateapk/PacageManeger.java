package com.suzualpha.templateapk;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class PacageManeger extends Activity {

	TextView handan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pacage_maneger);

		handan = (TextView) findViewById(R.id.paagemaneger_hanndan);
		handan.setText(String.valueOf(checkFaceBook()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pacage_maneger, menu);
		return true;
	}

	public boolean checkFaceBook() {
		try {
			PackageManager pm = getPackageManager();
			@SuppressWarnings("unused")
			ApplicationInfo appInfo = pm.getApplicationInfo(
					"com.facebook.katana", PackageManager.GET_META_DATA);
			return true;
		} catch (NameNotFoundException e) {

			return false;

		}
	}
}
