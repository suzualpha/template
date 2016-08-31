package com.suzualpha.mensetu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SampleB extends Activity {
	
	String b2 = "う";
	String c2 = "え";
	int d2 = 2;
	
	public void main() {
		SampleA samA = new SampleA(b2, c2, d2);
//			     ↑この状態のsamAのフィールド
		Log.d("b1", samA.b1);
		Log.d("c1", samA.c1);
		Log.d("d1", String.valueOf(samA.d1));
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		SampleA samA = new SampleA(b2, c2, d2);
//	     ↑この状態のsamAのフィールド
		Log.d("b1", samA.b1);
		Log.d("c1", samA.c1);
		Log.d("d1", String.valueOf(samA.d1));
	}
}
