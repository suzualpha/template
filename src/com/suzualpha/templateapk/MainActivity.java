package com.suzualpha.templateapk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.suzualpha.animation.LayoutAnimation1;
import com.suzualpha.animation.LayoutAnimation2;
import com.suzualpha.ber.FullScreenMainActivity;
import com.suzualpha.billing.AppActivity;
import com.suzualpha.fragment.FragmentActivityTest;
import com.suzualpha.media.CameraInfoActivity;
import com.suzualpha.media.ContentProviderActivity;
import com.suzualpha.media.MediaRecorderTest;

public class MainActivity extends Activity {
	
	ListView listView;
	ArrayAdapter<String> adapter;
	String[] stringArray; // String型の配列stringArrayを宣言
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
		// レイアウトと関連付ける
		listView = (ListView) findViewById(R.id.mainmenu_listview);
		
		// res/values/string.xml内で定義されているアイテムを呼び出す
		stringArray = getResources().getStringArray(R.array.meinmenu_list);
		
		/**
		 * リストビューにセットするアダプターを作成
		 *
		 * @param 第1引数
		 *            コンストラクタ
		 * @param 第2引数
		 *            レイアウト
		 * @param 第3引数
		 *            表示したい配列（今回はStringの配列）
		 */
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, stringArray);
		listView.setAdapter(adapter);
		// リスト項目がクリックされた時の処理
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ListView listView = (ListView) parent;
				String item = (String) listView.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), item + " clicked", Toast.LENGTH_SHORT).show();
				
				Intent intent;
				switch (position) {
				case 0:
					intent = new Intent(MainActivity.this,
							ActivityLifeCycle.class);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(MainActivity.this, Web.class);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(MainActivity.this,
							NotificationCompat_Builder.class);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent(MainActivity.this, FaceBookLink.class);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(MainActivity.this,
							DateFormatActivity.class);
					startActivity(intent);
					break;
				case 5:
					intent = new Intent(MainActivity.this,
							HashEncodeActivity.class);
					startActivity(intent);
					break;
				case 6:
					intent = new Intent(MainActivity.this, PacageManeger.class);
					startActivity(intent);
					break;
				case 7:
					intent = new Intent(MainActivity.this, DeviceInfo.class);
					startActivity(intent);
					break;
				case 8:
					intent = new Intent(MainActivity.this, ImeiToHash.class);
					startActivity(intent);
					break;
				case 9:
					intent = new Intent(MainActivity.this, TouchPoint.class);
					startActivity(intent);
					break;
				case 10:
					intent = new Intent(MainActivity.this,
							ActivityAnimation.class);
					startActivity(intent);
					break;
				case 11:
					intent = new Intent(MainActivity.this,
							LayoutAnimation1.class);
					startActivity(intent);
					break;
				case 12:
					intent = new Intent(MainActivity.this,
							LayoutAnimation2.class);
					startActivity(intent);
					break;
				case 13:
					intent = new Intent(MainActivity.this, IntentActivity.class);
					startActivity(intent);
					break;
				case 14:
					intent = new Intent(MainActivity.this, VideoPlayActivity.class);
					startActivity(intent);
					break;
				case 15:
					intent = new Intent(MainActivity.this, CameraInfoActivity.class);
					startActivity(intent);
					break;
				case 16:
					intent = new Intent(MainActivity.this, MediaRecorderTest.class);
					startActivity(intent);
					break;
				case 17:
					intent = new Intent(MainActivity.this, ContentProviderActivity.class);
					startActivity(intent);
					break;
				case 18:
					intent = new Intent(MainActivity.this, FragmentActivityTest.class);
					startActivity(intent);
					break;
				case 19:
//					intent = new Intent(MainActivity.this, AdActivity.class);
//					startActivity(intent);
					break;
				case  20:
					intent = new Intent(MainActivity.this, FullScreenMainActivity.class);
					startActivity(intent);
					break;
				case  21:
					intent = new Intent(MainActivity.this, AppActivity.class);
					startActivity(intent);
					break;
				case  22:
					intent = new Intent(MainActivity.this, VolumeActivity.class);
					startActivity(intent);
					break;
				case  23:
					intent = new Intent(MainActivity.this, KeyEventActivity.class);
					startActivity(intent);
					break;
				case  24:
					intent = new Intent(MainActivity.this, WebViewTextActivity.class);
					startActivity(intent);
					break;
				case  25:
					intent = new Intent(MainActivity.this, NavigationbarCustum.class);
					startActivity(intent);
					break;
				case  26:
					intent = new Intent(MainActivity.this, SpaceToSS.class);
					startActivity(intent);
					break;
				}
			}
		});
		
		listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			// リスト項目が選択された時の処理
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ListView listView = (ListView) parent;
				String item = (String) listView.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), item + " selected",
						Toast.LENGTH_SHORT).show();
			}
			
			// リスト項目がなにも選択されていない時の処理
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getApplicationContext(), "no item selected",
						Toast.LENGTH_SHORT).show();
			}
		});
		
		// リスト項目が長押しされた時の処理
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListView listView = (ListView) parent;
				String item = (String) listView.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), item + " long clicked",
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onPowerManager(View v) {
		Intent intent = new Intent(this, ActivityLifeCycle.class);
		startActivity(intent);
	}
	
}
