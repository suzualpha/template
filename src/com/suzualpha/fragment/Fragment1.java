package com.suzualpha.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.suzualpha.templateapk.R;

public class Fragment1 extends Fragment {
	
	public Fragment1() {
	}
	
	// 初期化専用のメソッド。Fragment に対する初期化用の引数はここで管理する
	public static Fragment createInstance(int hoge) {
		Fragment fragment = new Fragment1();
		// Fragment に渡す引数を詰めこむオブジェクト
		Bundle args = new Bundle();
		args.putInt("fragment1", hoge);
		
		// 詰め込んだオブジェクトを Fragment に渡す
		fragment.setArguments(args);
		
		// 新しいインスタンスを返す
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment1, container, false);
		
		WebView wv = (WebView) root.findViewById(R.id.fragment1_webview);
		wv.setWebViewClient(new WebViewClient());
		
		wv.loadUrl("http://www.google.co.jp/");
		
		/** 戻り値としてインフレートした View を返す */
		return root;
	}
/*	*//** Fragmentで保持しておくデータ */
	/*
	private int mData;
	
	public static Fragment1 newInstance(int index) {
	Fragment1 fragment = new Fragment1();
	
	// 引数を設定
	Bundle args = new Bundle();
	args.putInt("index", index);
	fragment.setArguments(args);
	
	return fragment;
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// FragmentのViewを返却
	return inflater.inflate(R.layout.fragment1, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);
	
	if (savedInstanceState != null) {
	// onSaveInstanceStateで保存されたデータを復元
	mData = savedInstanceState.getInt("data");
	}
	
	// 引数を取得
	Bundle args = getArguments();
	int index = args.getInt("index");
	
	// Viewの初期化、イベントハンドラ設定など
	//	        Button button = (Button) getView().findViewById(R.id.btn);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	// Fragment内で残しておきたいデータを保存
	outState.putInt("data", mData);
	}*/
}
