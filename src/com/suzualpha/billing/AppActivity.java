/****************************************************************************
Copyright (c) 2008-2010 Ricardo Quesada
Copyright (c) 2010-2012 cocos2d-x.org
Copyright (c) 2011      Zynga Inc.
Copyright (c) 2013-2014 Chukong Technologies Inc.
 
http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
****************************************************************************/
package com.suzualpha.billing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.vending.billing.IabHelper;
import com.android.vending.billing.IabResult;
import com.android.vending.billing.Inventory;
import com.android.vending.billing.Purchase;
import com.suzualpha.templateapk.R;

public class AppActivity extends Activity {
	
	static AppActivity me = null;
	static final int RC_REQUEST = 10000;
	IabHelper mHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_billing);
		me = this;
		
//		String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnSwpmoVZc3MtUby2cMJqp7Q+hERZDS7hNXTZE60XhSFV76c6bO23TV+H1ympkrvfupYRK9aCRufblyxmStaJXchfuuIY2ARF1XbqwbNoTiB1wqNs34nbGfPmAllGZBAW1Yr9cgIlF1jQzgkwTG1rrpaD73Pvhni9iB79V5Zm9WmV+mtUgUj5M7ue7BDwPH/iJgntkPwwcQ1L72NI1IBClOj7Mp89SS9svYBRYHqT9ERdsbllCCBDN3IXYmxrkaEbjFhidFsQ/HyI260uwxXvKTsY4L0iEfy/ZtvBsLGjgYSoDukToAZWjDrvPhJ6GMTmENmiJ4/uyLEjkPHYcUyP/wIDAQAB";
		mHelper = new IabHelper(this);
		mHelper.enableDebugLogging(true); // リリース時には消す
		
		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
			@Override
			public void onIabSetupFinished(IabResult result) {
				if (mHelper == null)
					return;
				if (result.isFailure())
					return;
				mHelper.queryInventoryAsync(mGotInventoryListener);
			}
		});
		
	}
	
	private IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
		@Override
		public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
			if (mHelper == null)
				return;
			if (result.isFailure())
				return;
			
			// ここで購入情報をチェック
			// 購入済みのアイテムがあればそれに応じた処理
			//Log.e("TITLE",inventory.getSkuDetails("yoko1").getTitle());
			
			if (inventory.hasPurchase("tenplateappkakin01")) {
				mHelper.consumeAsync(inventory.getPurchase("tenplateappkakin01"), mConsumeFinishedListener);
				Log.d("itemPurchase", "item is Consume!!!!!!");
			}
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (mHelper == null)
			return;
		if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
	
	private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
		@Override
		public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
			if (mHelper == null)
				return;
			if (result.isFailure())
				return;
			
			// 購入成功時の処理（アイテムの効果をゲームに適用）
		}
	};
	
	private IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
		
		@Override
		public void onConsumeFinished(Purchase purchase, IabResult result) {
			if (mHelper == null)
				return;
			if (result.isFailure())
				return;
			if (result.isSuccess())
				Log.d("onConsumeFinished", "SUCCESS!");
			
		}
	};
	
	public void buyItem1(View v){
		requestBilling("tenplateappkakin01");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if (mHelper != null) {
			mHelper.dispose();
			mHelper = null;
		}
	}
	
	protected void requestBilling(String productID) {
		mHelper.launchPurchaseFlow(this, productID, RC_REQUEST, mPurchaseFinishedListener);
	}
	
	public static void requestPurchasing(String productID) {
		me.requestBilling(productID);
	}
	
}
