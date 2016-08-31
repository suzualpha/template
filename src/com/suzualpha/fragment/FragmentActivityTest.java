package com.suzualpha.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

import com.suzualpha.templateapk.R;

public class FragmentActivityTest extends FragmentActivity {
	
	LinearLayout menuList;
	LinearLayout adLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			return;
		}
		setContentView(R.layout.activity_fragment);
		
		menuList = (LinearLayout) findViewById(R.id.menu_list);
		menuList.setVisibility(View.INVISIBLE);
		adLayout = (LinearLayout) findViewById(R.id.fragment_activity_adroot);
		
		/*	  FragmentManager manager = getSupportFragmentManager();
		        // FragmentTransaction を開始
		        FragmentTransaction transaction = manager.beginTransaction();

		        // FragmentContainer のレイアウトに、MyFragment を割当てる
		        transaction.add(R.id.fragment_change_layout, Fragment1.createInstance(1));

		        // FragmentContainer のレイアウトの中身を、MyFragment に置き換える
		        transaction.replace(R.id.fragment_change_layout, Fragment1.createInstance(1));

		        // Fragment を削除する
		        transaction.remove(mFragment);

		        // 変更を確定して FragmentTransaction を終える
		        transaction.commit();
		        */
		
	}
	
	public void onChangeLayout(View v) {
		try {
//			View fg = (View) findViewById(R.id.fragment_change_layout);
//			ViewGroup linearLayout1 = (ViewGroup) this.findViewById(R.id.fragment_activity_root);
//			ViewGroup viewG2 = (ViewGroup) linearLayout1.getChildAt(0);
//		View view = viewG2.getChildAt(0);
//			viewG2.removeView(fg);
			FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
			Fragment next = new Fragment2();
			ft.replace(R.id.fragment_change_layout, next);
			ft.addToBackStack(null);
			ft.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onHome(View v) {
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		Fragment next = new Fragment1();
		ft.replace(R.id.fragment_change_layout, next);
		ft.addToBackStack(null);
		ft.commit();
//		this.menuList.setVisibility(View.INVISIBLE);
		onCloseMenu(v);
	}
	
	public void onInfo(View v) {
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		Fragment next = new Fragment2();
		ft.replace(R.id.fragment_change_layout, next);
		ft.addToBackStack(null);
		ft.commit();
//		this.menuList.setVisibility(View.INVISIBLE);
		onCloseMenu(v);
	}
	
	public void onChatList(View v) {
		this.adLayout.setVisibility(View.GONE);
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		Fragment next = new Fragment3();
		ft.replace(R.id.fragment_change_layout, next);
		ft.addToBackStack(null);
		ft.commit();
//		this.menuList.setVisibility(View.INVISIBLE);
		onCloseMenu(v);
		
	}
	
	public void onOpenMenu(View v) {
		
		AlphaAnimation alpha = new AlphaAnimation(0, 1);
		alpha.setDuration(500);
//		alpha.setFillAfter(true);
//		alpha.setFillEnabled(true);
		alpha.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				menuList.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				
			}
		});
		menuList.startAnimation(alpha);
	}
	
	public void onCloseMenu(View v) {
		AlphaAnimation alpha = new AlphaAnimation(1, 0);
		alpha.setDuration(200);
//		alpha.setFillAfter(true);
//		alpha.setFillEnabled(true);
		alpha.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				menuList.setVisibility(View.INVISIBLE);
			}
		});
		menuList.startAnimation(alpha);
	}
	
}
