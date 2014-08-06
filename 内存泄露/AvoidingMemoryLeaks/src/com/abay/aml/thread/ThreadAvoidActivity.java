package com.abay.aml.thread;

import java.lang.ref.WeakReference;

import com.abay.aml.R;
import android.app.Activity;
import android.os.Bundle;

/**
 * 
 * @version 1.0.0
 * @author Abay Zhuang <br/>
 *         Create at 2014-7-17
 */

public class ThreadAvoidActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new MyThread(this).start();
	}

	private void dosomthing() {

	}

	private static class MyThread extends Thread {
		WeakReference<ThreadAvoidActivity> mThreadActivityRef;

		public MyThread(ThreadAvoidActivity activity) {
			mThreadActivityRef = new WeakReference<ThreadAvoidActivity>(
					activity);
		}

		@Override
		public void run() {
			super.run();
			if (mThreadActivityRef == null)
				return;
			if (mThreadActivityRef.get() != null)
				mThreadActivityRef.get().dosomthing();
			// dosomthing
		}
	}
}