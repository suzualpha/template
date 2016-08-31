package com.suzualpha.templateapk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ImeiToHash extends Activity {

	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> hashList = new ArrayList<String>();

	TextView hashText;

	ListView listView;

	boolean doubleFlag = false;

	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imei_to_hash);

		hashText = (TextView) findViewById(R.id.imeitohash_text);

		listView = (ListView) findViewById(R.id.imeitohash_listview);

	}

	public void createList(View v) {
		for (int i = 0; i < 10000; i++) {
			int no1 = 0;
			no1 = (int) (Math.random() * 8999999 + 1000000);

			int no2 = 0;
			no2 = (int) (Math.random() * 89999999 + 10000000);

			String noStr = (String.valueOf(no1) + String.valueOf(no2));
			list.add(noStr);
		}

		list.get(0);
		HashSet<String> hs = new HashSet<String>();
		for (int i = 0; i < list.size() - 1;) {
			if (!hs.contains(list.get(i))) {
				hs.add(list.get(i));
				++i;
			} else {
				list.remove(i);
			}
		}
		Toast.makeText(this, "リスト生成完了", Toast.LENGTH_SHORT).show();

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);
	}

	public void createHash(View v) {
		for (int i = 0; i < list.size(); i++) {
			hashList.add(StringToMD5(list.get(i)));
		}
		Toast.makeText(this, "ハッシュリスト生成完了", Toast.LENGTH_SHORT).show();

		adapter.clear();
		adapter.notifyDataSetChanged();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, hashList);
		listView.setAdapter(adapter);
	}

	public void hashCheck(View v) {
		HashSet<String> hs = new HashSet<String>();
		for (int i = 0; i < hashList.size() - 1;) {
			if (!hs.contains(hashList.get(i))) {
				hs.add(hashList.get(i));
				++i;
			} else {
				hashList.remove(i);
				Toast.makeText(this, "重複あり", Toast.LENGTH_SHORT).show();
				doubleFlag = true;
			}
		}
		Toast.makeText(this, "チェック終わり", Toast.LENGTH_SHORT).show();
		if (doubleFlag) {
			hashText.setTextColor(Color.RED);
			hashText.setText("重複あり");
		} else {
			hashText.setTextColor(Color.BLUE);
			hashText.setText("重複なし");
		}
	}

	private String StringToMD5(String key) {
		byte[] hash = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(key.getBytes());
			hash = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashByteToMD5(hash);
	}

	private String hashByteToMD5(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {
				hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
			} else {
				hexString.append(Integer.toHexString(0xFF & hash[i]));
			}
		}
		return hexString.toString();
	}
}
