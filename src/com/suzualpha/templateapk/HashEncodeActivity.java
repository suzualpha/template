package com.suzualpha.templateapk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class HashEncodeActivity extends Activity {
	private static final String KEY_SHA = "SHA";
	String hashString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hash_encode);

		try {
			hashString = encrypt("KDV608EzRBLRaaomKK8e1e2Xxo6qVulg,1391397196,/status/1,545e1f3a-a77b-4f3f-bce9-4466c0d21d85"
					.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		Log.d("hash", hashString);
	}

	// Unixタイムスタンプ
	private String UnixTimeStmp() {
		Long l = System.currentTimeMillis() / 1000L;
		return String.valueOf(l);
	}

	public static String encrypt(byte[] value) throws NoSuchAlgorithmException {

		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(value);

		return hashByte2String(sha.digest());
	}

	// 16進数に変換
	private static String hashByte2String(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {// < 16（<=F）の場合、1桁になるので、頭に"0"を追加
				hexString.append("0" + Integer.toHexString((0xFF & hash[i]))); // HEX
			} else {
				hexString.append(Integer.toHexString(0xFF & hash[i])); // HEX
			}
		}

		return hexString.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hash_encode, menu);
		return true;
	}

}
