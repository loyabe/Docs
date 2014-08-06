package com.abay.aml.handler;

import java.lang.ref.WeakReference;

import com.abay.aml.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 
 * 实现的主要功能。
 * 
 * @version 1.0.0
 * @author Abay Zhuang <br/>
 *         Create at 2014-7-28
 */
public class HandlerActivity3 extends Activity {
	private static final String TAG = HandlerActivity3.class.getSimpleName();
	private static final int MESSAGE_1 = 1;
	private static final int MESSAGE_2 = 2;
	private static final int MESSAGE_3 = 3;
	private Handler mHandler ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mHandler = new MyHandler(this);
		mHandler.sendMessageDelayed(Message.obtain(), 60000);
		Message m = Message.obtain();
		m.what = MESSAGE_1;
		mHandler.sendMessageDelayed(m, 60000);
		// just finish this activity
		finish();
	}

	public void todo() {
		//while(true);
	};

	private static class MyHandler extends Handler {
		private final WeakReference<HandlerActivity3> mActivity;

		public MyHandler(HandlerActivity3 activity) {
			mActivity = new WeakReference<HandlerActivity3>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			Log.e(TAG, "handleMessage");
			System.out.println(msg);
			if (mActivity == null || mActivity.get() == null) {
				return;
			}
			mActivity.get().todo();
		}
	}

	private Runnable mRunnable = new Runnable() {

		@Override
		public void run() {

			System.out.println("hello everyone ");
		}

	};

//	/**
//	 * 一切都是为了不要让mHandler拖泥带水
//	 */
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		mHandler.removeMessages(MESSAGE_1);
//		mHandler.removeMessages(MESSAGE_2);
//		mHandler.removeMessages(MESSAGE_3);
//
//		// ... ...
//
//		mHandler.removeCallbacks(mRunnable);
//
//		// ... ...
//	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	    //  If null, all callbacks and messages will be removed.
		Log.e(TAG, "onDestroy");
	    mHandler.removeCallbacksAndMessages(null);
	}
	
}
