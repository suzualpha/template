package com.suzualpha.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.suzualpha.templateapk.R;

public class Fragment2 extends Fragment {
	
	/** Fragmentで保持しておくデータ */
	private int mData;

	
	public static Fragment2 newInstance(int index) {
		Fragment2 fragment = new Fragment2();
		
		// 引数を設定
		Bundle args = new Bundle();
		args.putInt("index", index);
		fragment.setArguments(args);
		
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// FragmentのViewを返却
//		container.removeAllViews();
		View root = null ;
		try{
		
		 root = inflater.inflate(R.layout.fragment2, container, false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		WebView  wv = (WebView) root.findViewById(R.id.fragment2_webview);
		wv.setWebViewClient(new WebViewClient());
		wv.loadUrl("http://www.yahoo.co.jp/");
		return root;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		if (savedInstanceState != null) {
			// onSaveInstanceStateで保存されたデータを復元
//			mData = savedInstanceState.getInt("data");
		}
		
		// 引数を取得
//		Bundle args = getArguments();
//		int index = args.getInt("index");
		
		// Viewの初期化、イベントハンドラ設定など
//	        Button button = (Button) getView().findViewById(R.id.btn);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// Fragment内で残しておきたいデータを保存
//		outState.putInt("data", mData);
	}
}
