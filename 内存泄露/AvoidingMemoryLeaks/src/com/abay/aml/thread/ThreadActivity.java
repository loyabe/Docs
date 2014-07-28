package com.abay.aml.thread;

import java.lang.ref.WeakReference;

import com.abay.avoidingmemoryleaks.R;

import android.app.Activity;
import android.os.Bundle;
/**
 * 
 * @version 1.0.0 
 * @author Abay Zhuang <br/>
 *		   Create at 2014-7-17
 */
public class ThreadActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new MyThread().start();
	}

	private class MyThread extends Thread {
		@Override
		public void run() {
			super.run();
			dosomthing();
		}
	}
	private void dosomthing(){
	
	}
}


