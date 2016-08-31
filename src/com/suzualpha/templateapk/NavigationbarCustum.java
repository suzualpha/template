package com.suzualpha.templateapk;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class NavigationbarCustum extends Activity {
	
	CheckBox checkBox0;
	CheckBox checkBox1;
	CheckBox checkBox2;
	CheckBox checkBox3;
	CheckBox checkBox4;
	CheckBox checkBox5;
	CheckBox checkBox6;
	CheckBox checkBox7;
	CheckBox checkBox8;
	CheckBox checkBox9;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigationbar_custum);
		
		checkBox0 = (CheckBox)findViewById(R.id.checkBoxUIFlag0);
		checkBox1 = (CheckBox)findViewById(R.id.checkBoxUIFlag1);
		checkBox2 = (CheckBox)findViewById(R.id.checkBoxUIFlag2);
		checkBox3 = (CheckBox)findViewById(R.id.checkBoxUIFlag3);
		checkBox4 = (CheckBox)findViewById(R.id.checkBoxUIFlag4);
		checkBox5 = (CheckBox)findViewById(R.id.checkBoxUIFlag5);
		checkBox6 = (CheckBox)findViewById(R.id.checkBoxUIFlag6);
		checkBox7 = (CheckBox)findViewById(R.id.checkBoxUIFlag7);
		checkBox8 = (CheckBox)findViewById(R.id.checkBoxUIFlag8);
		checkBox9 = (CheckBox)findViewById(R.id.checkBoxUIFlag9);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.navigationbar_custum, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onBavigationEnter(View v){
		int check0 = 0;
		if(checkBox0.isChecked()){ check0 = View.SYSTEM_UI_FLAG_FULLSCREEN;} else { check0 = 0;}
		int check1 = 0;
		if(checkBox1.isChecked()){ check1 = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;} else { check1 = 0;}
		int check2 = 0;
		if(checkBox2.isChecked()){ check2 = View.SYSTEM_UI_FLAG_IMMERSIVE;} else { check2 = 0;}
		int check3 = 0;
		if(checkBox3.isChecked()){ check3 = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;} else { check3 = 0;}
		int check4 = 0;
		if(checkBox4.isChecked()){ check4 = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;} else { check4 = 0;}
		int check5 = 0;
		if(checkBox5.isChecked()){ check5 = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;} else { check5 = 0;}
		int check6 = 0;
		if(checkBox6.isChecked()){ check6 = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;} else { check6 = 0;}
		int check7 = 0;
//		if(checkBox7.isChecked()){ check7 = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;} else { check0 = 0;}
		int check8 = 0;
		if(checkBox8.isChecked()){ check8 = View.SYSTEM_UI_FLAG_LOW_PROFILE;} else { check8 = 0;}
		int check9 = 0;
		if(checkBox9.isChecked()){ check9 = View.SYSTEM_UI_FLAG_VISIBLE;} else { check9 = 0;}
		

		this.getWindow().getDecorView().setSystemUiVisibility(check0 | check1 |  check2 | check3 | check4 | check5 | check6 | check7 | check8 | check9);
		
		
	}
}
