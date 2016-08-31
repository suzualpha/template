package com.suzualpha.templateapk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.suzualpha.templateapk.R.color;

public class Web extends Activity {

	private TextView agentPrintText;
	private TextView agentMobileBeforeText;
	private TextView agentMobileAfterText;
	private WebView webView = null;
	private Spinner userAgentSpinner;

	private final String url = "http://kaizoku.tv";

	// Spinnerは配列の順番で中身を選択している為、userAgentArrayへ追加の順番を考慮すること
	private final String ISW11F = "Mozilla/5.0 (Linux; U; Android 4.0.3; ja-jp; ISW11F Build/FIK700) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
	private final String ISW11F_delMobile = "Mozilla/5.0 (Linux; U; Android 4.0.3; ja-jp; ISW11F Build/FIK700) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30";
	private final String ISW11F_DeviceChange_NEXUS5 = "Mozilla/5.0 (Linux; U; Android 4.0.3; ja-jp; Nexus 5 Build/FIK700) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30";
	private final String NEXUS5 = "Mozilla/5.0 (Linux; Android 4.4.2; Nexus 5 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36";
	private final String NEXUS5_DeviceChange_ISW11F = "Mozilla/5.0 (Linux; Android 4.4.2; ISW11F Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36";
	private final String NEXUS7_2012wifi = "Mozilla/5.0 (Linux; Android 4.4.2; Nexus 7 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Safari/537.36";
	private final String NEXUS7_2012wifi_DeviceChange_ISW11F = "Mozilla/5.0 (Linux; Android 4.4.2; ISW11F Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Safari/537.36";
	private final String[] userAgentArray = { ISW11F, ISW11F_delMobile,
			ISW11F_DeviceChange_NEXUS5, NEXUS5, NEXUS5_DeviceChange_ISW11F,
			NEXUS7_2012wifi, NEXUS7_2012wifi_DeviceChange_ISW11F };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);

		webView = new WebView(this);

		agentPrintText = (TextView) findViewById(R.id.web_useragent_mobile_text);
		agentMobileBeforeText = (TextView) findViewById(R.id.web_befor_useragentprint_text);
		agentMobileAfterText = (TextView) findViewById(R.id.web_after_useragentprint_text);
		webView = (WebView) findViewById(R.id.web_webview);
		userAgentSpinner = (Spinner) findViewById(R.id.web_useragent_spinner);
		userAgentSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						Spinner spinner = (Spinner) parent;
						// 選択されたアイテムを取得します
						String item = (String) spinner.getSelectedItem();
						Toast.makeText(Web.this, item, Toast.LENGTH_LONG)
								.show();
						WebLoad(userAgentArray[position]);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});

		String userAgentBeforeString = webView.getSettings()
				.getUserAgentString();
		Log.d("before_userAgent", userAgentBeforeString);
		agentMobileBeforeText.setText(userAgentBeforeString);

		int mobileCheck = userAgentBeforeString.indexOf("Mobile");

		if (mobileCheck != -1) {
			agentPrintText
					.setText(getResources().getString(R.string.mobile_on));
		} else {
			agentPrintText.setTextColor(color.red);
			agentPrintText.setText(getResources()
					.getString(R.string.mobile_off));
		}

		webView.loadUrl(url);

	}

	// ユーザエージェントを変更してURLを開く
	private void WebLoad(String serectAgentName) {
		webView.getSettings().setUserAgentString(serectAgentName);
		String userAgentAfterString = webView.getSettings()
				.getUserAgentString();
		Log.d("after_userAgent", userAgentAfterString);

		agentMobileAfterText.setText(userAgentAfterString);

		int mobileCheck = userAgentAfterString.indexOf("Mobile");

		if (mobileCheck != -1) {
			agentPrintText
					.setText(getResources().getString(R.string.mobile_on));
		} else {
			agentPrintText.setTextColor(color.red);
			agentPrintText.setText(getResources()
					.getString(R.string.mobile_off));
		}

		webView.loadUrl(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.web, menu);
		return true;
	}

}
