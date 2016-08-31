package com.suzualpha.templateapk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DateFormatActivity extends Activity {

	TextView answer1, answer2, answer3, answer4, answer5, answer6, answer7,
			answer8, answer9, answer10, answer11, answer12, answer13;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_format);

		answer1 = (TextView) findViewById(R.id.dataformat_answer1);
		answer2 = (TextView) findViewById(R.id.dataformat_answer2);
		answer3 = (TextView) findViewById(R.id.dataformat_answer3);
		answer4 = (TextView) findViewById(R.id.dataformat_answer4);
		answer5 = (TextView) findViewById(R.id.dataformat_answer5);
		answer6 = (TextView) findViewById(R.id.dataformat_answer6);
		answer7 = (TextView) findViewById(R.id.dataformat_answer7);
		answer8 = (TextView) findViewById(R.id.dataformat_answer8);
		answer9 = (TextView) findViewById(R.id.dataformat_answer9);
		answer10 = (TextView) findViewById(R.id.dataformat_answer10);
		answer11 = (TextView) findViewById(R.id.dataformat_answer11);

	}

	@Override
	protected void onStart() {
		super.onStart();

		// UNIXタイムスタンプ
		Long l = System.currentTimeMillis() / 1000L;
		answer1.setText(String.valueOf(l));
	}
}
