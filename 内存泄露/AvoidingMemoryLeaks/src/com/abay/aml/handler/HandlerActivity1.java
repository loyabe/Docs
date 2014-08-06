package com.abay.aml.handler;

import com.abay.aml.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 
 * 实现的主要功能。
 * @version 1.0.0 
 * @author Abay Zhuang <br/>
 *		   Create at 2014-7-28
 */
public class HandlerActivity1 extends Activity {

	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// ...
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler.sendMessageDelayed(Message.obtain(), 60000);
 
        //just finish this activity
        finish();
    }


}
