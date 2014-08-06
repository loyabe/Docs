package com.abay.aml.main;

import com.abay.aml.R;
import com.abay.aml.handler.HandlerActivity2;
import com.abay.aml.handler.HandlerActivity1;
import com.abay.aml.handler.HandlerActivity3;
import com.abay.aml.thread.ThreadActivity;
import com.abay.aml.thread.ThreadAvoidActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 测试列表
 * @version 1.0.0 
 * @author Abay Zhuang <br/>
 *		   Create at 2014-8-6
 * 修改者，修改日期，修改内容。
 */
public class MainActivity extends Activity implements OnClickListener{
	
	private Button mHandler;
	private Button mHandler1;
	private Button mHandler2;
	private Button mThread;
	private Button mThreadAvoidLeak;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mHandler = (Button)findViewById(R.id.handler);
        mHandler1 = (Button)findViewById(R.id.handler1);
        mHandler2 = (Button)findViewById(R.id.handler2);
        mThread = (Button)findViewById(R.id.ThreadLeak);
        mThreadAvoidLeak = (Button)findViewById(R.id.ThreadAvoidLeak);
        mHandler.setOnClickListener(this);
        mHandler1.setOnClickListener(this); 
        mHandler2.setOnClickListener(this);
        mThread.setOnClickListener(this);
        mThreadAvoidLeak.setOnClickListener(this);
    }


	@Override
	public void onClick(View v) {
		Class clazz = null;
		switch(v.getId()){
		case R.id.handler:
			clazz = HandlerActivity1.class;
			
			break;
		case R.id.handler1:
			clazz = HandlerActivity2.class;
			break;
		case R.id.handler2:
			clazz = HandlerActivity3.class;
			break;
		case R.id.ThreadLeak:
			clazz = ThreadActivity.class;
			break;
		case R.id.ThreadAvoidLeak:
			clazz = ThreadAvoidActivity.class;
			break;
		}
		
		
		Intent i = new Intent(MainActivity.this, clazz);
		startActivity(i);
	}
}
