package com.abay.aml.resource;

import com.abay.aml.R;

import android.app.ListActivity;
import android.os.Bundle;
/**
 * 
 * 实现的主要功能。
 * @version 1.0.0 
 * @author Abay Zhuang <br/>
 *		   Create at 2014-8-7
 */
public class TestListActivity extends ListActivity {
	private BadAdapter mAdapter;

	private String[] mArrData;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_res_list);
		mArrData = new String[1000];
		for (int i = 0; i < 1000; i++) {
			mArrData[i] = "Abay test Adapter";
		}
		mAdapter = new BadAdapter(this, mArrData);
		setListAdapter(mAdapter);
	}

}
