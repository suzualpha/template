package com.suzualpha.templateapk;


import java.lang.reflect.Method;

import android.app.Activity;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;

import com.suzualpha.util.FitSizeVideoView;

public class VideoPlayActivity  extends Activity {
	FitSizeVideoView videov;
	int width;
	int height;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_videoplay);
		
		Display display = getWindowManager().getDefaultDisplay();
		Point point = new Point();
		overrideGetSize(display, point);
		width = point.x;
		height = point.y;
		
		
		
		 videov = (FitSizeVideoView)findViewById(R.id.playviedeo_videoview);
		String filePath = "android.resource://com.suzualpha.templateapk/"+ R.raw.ipone314;
		videov.setVideoURI(Uri.parse(filePath));
		videov.start();
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
