package com.suzualpha.templateapk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;
import com.facebook.widget.ProfilePictureView;

public class FaceBookLink extends Activity {

	// private GraphUser user;
	private LoginButton facebook_link;
	TextView username;
	ProfilePictureView userpic;
	UiLifecycleHelper uihelper;
	private Session.StatusCallback callback = new Session.StatusCallback() {

		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onsessionstatechange(session, state, exception);
		}

	};

	private void onsessionstatechange(Session session, SessionState state,
			Exception exception) {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_face_book);
		uihelper = new UiLifecycleHelper(this, callback);
		uihelper.onCreate(savedInstanceState);

		facebook_link = (LoginButton) findViewById(R.id.facebook_link_switch);
		facebook_link.setPublishPermissions(Arrays.asList("publish_actions"));

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Toast.makeText(
				getApplicationContext(),
				"requestCode: " + String.valueOf(requestCode) + " reaultCode: "
						+ String.valueOf(resultCode), Toast.LENGTH_SHORT)
				.show();
		uihelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		uihelper.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		uihelper.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		uihelper.onResume();
	}

	// ハッシュコードを生成
	public void printHash(View v) {
		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					getPackageName(), PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md;

				md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				String something = new String(Base64.encode(md.digest(), 0));
				Log.e("hash key", something);
				Toast.makeText(this, something, Toast.LENGTH_SHORT).show();
			}
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			Log.e("name not found", e1.toString());
		}

		catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			Log.e("no such an algorithm", e.toString());
		} catch (Exception e) {
			Log.e("exception", e.toString());
		}
	}
}
