package com.suzualpha.mensetu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SampleC extends Activity {
	
	int intZ = 50;
	int intY = 20;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		methodC();
		Log.d("intZ", String.valueOf(intZ));
	}
	
	public void methodA() {
		intZ = intZ + 50;
	}
	
	public void methodB() {
		int intZ = 50;
		intZ = intZ + 50;
	}
	
	public void methodC() {
		int intX = 10;
		intZ = (intY + intX);
		
	}
}