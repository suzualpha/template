package com.suzualpha.util;

import java.util.Random;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimationUtil {
	static int startTime = 800;

	public static void animationSet(final View view, int pivotX, int pivotY) {
		AnimationSet set = new AnimationSet(true);

		ScaleAnimation scale = new ScaleAnimation(1, 0, 1, 0, pivotX,
				pivotY);
		scale.setDuration(startTime);
		scale.setStartOffset(0);
		scale.setFillAfter(true);
		scale.setFillEnabled(true);
		scale.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				view.setVisibility(View.GONE);
			}
		});
//		TranslateAnimation translate = new TranslateAnimation(0, 50, 0, -5000);
//		translate.setDuration(1500);
//		translate.setStartOffset(startTime);
//		translate.setFillAfter(true);
//		translate.setFillEnabled(true);
		set.addAnimation(scale);
//		set.addAnimation(translate);
		set.setFillBefore(true);
		// set.setDuration(10000);
		view.startAnimation(set);
		// AlphaAnimation alpha = new AlphaAnimation(0.1f, 1);
		// alpha.setDuration(startTime);
		// view2.startAnimation(alpha);

	}

	public static void alfaAnimation(View view) {
		AlphaAnimation alpha = new AlphaAnimation(0, 1);
		alpha.setDuration(1500);
		alpha.setFillAfter(true);
		alpha.setFillEnabled(true);
		view.startAnimation(alpha);
	}

	public static void minAnimation(final View view) {
		ScaleAnimation scale = new ScaleAnimation(1, 0.3f, 1, 0.3f, 50, 50);
		scale.setFillAfter(true);
		scale.setDuration(3000);
		scale.setStartOffset(0);
		scale.setFillEnabled(true);
		scale.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				migiueAnimation(view, 0, 50, 0, -100);
			}
		});
		view.startAnimation(scale);

	}

	public static void migiueAnimation(final View view, final int defX,
			final int aftX, final int defY, final int aftY) {
		final int moveX = animationXRandom();
		TranslateAnimation translate = new TranslateAnimation(defX, moveX,
				defY, aftY);
		// translate.setFillBefore(false); // アニメーション後、初期状態に戻す
		translate.setFillAfter(true); // アニメーション後、状態を維持
		translate.setFillEnabled(true); // アニメーション後の状態を有効化（↑2つ）
		translate.setDuration(animationTimeRadum()); // アニメーション時間
		translate.setStartOffset(0); // 開始時間
		// translate.setRepeatCount(Animation.INFINITE); // 繰り返し回数
		// Animation.INFINITE で無限
		// translate.setRepeatMode(Animation.REVERSE); // 繰り返しの動き
		// Animation.RESTART同じ動作 Animation.REVERSE 反転
		translate.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				migiueAnimation(view, moveX, 0, aftY, aftY - animationYRandom());
			}
		});
		view.startAnimation(translate);
	}

	public static void hidariueAnimation(final View view, final int defX,
			final int aftX, int defY, final int aftY) {
		final int moveX = animationXRandom();
		TranslateAnimation translate = new TranslateAnimation(defX, moveX,
				defY, aftY);
		// translate.setFillBefore(false); // アニメーション後、初期状態に戻す
		translate.setFillAfter(true); // アニメーション後、状態を維持
		translate.setFillEnabled(true); // アニメーション後の状態を有効化（↑2つ）
		translate.setDuration(animationTimeRadum()); // アニメーション時間
		translate.setStartOffset(0); // 開始時間
		// translate.setRepeatCount(0); // 繰り返し回数 Animation.INFINITE で無限
		// translate.setRepeatMode(Animation.RESTART); // 繰り返しの動き
		// Animation.RESTART同じ動作 Animation.REVERSE 反転
		translate.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				migiueAnimation(view, moveX, 0, aftY, aftY - animationYRandom());
			}
		});
		view.startAnimation(translate);
	}

	// アニメーションの時間をランダムにする（500～1500）
	public static int animationTimeRadum() {
		Random rand = new Random();

		return rand.nextInt(1300) + 700;
	}

	public static int animationXRandom() {
		Random rand = new Random();
		int test = rand.nextInt(100);

		int flag = rand.nextInt(100);
		if (50 < flag) {
			return test;
		} else {
			return test - test - test;
		}
		// return rand.nextInt(500)+20;
	}

	public static int animationYRandom() {
		Random rand = new Random();
		return rand.nextInt(300) + 100;
	}

	/*
	 * public static int animationXRandom(){ Random rand = new Random(); return
	 * rand.nextInt(200)+50; }
	 */

}
