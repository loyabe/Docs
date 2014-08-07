package com.abay.aml.resource;

import com.abay.aml.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * 实现的主要功能。
 * @version 1.0.0 
 * @author Abay Zhuang <br/>
 *		   Create at 2014-8-7
 */
public class BadAdapter extends BaseAdapter {
	
    private Context mContext;

    private String[] mData;

    public BadAdapter(Context mContext, String[] mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public Object getItem(int position) {
        return mData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("MyAdapter", "Position:" + position + "---"
                + String.valueOf(System.currentTimeMillis()));
        final LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_item_icon_text, null);
        ((ImageView) v.findViewById(R.id.icon)).setImageResource(R.drawable.ic_launcher);
        ((TextView) v.findViewById(R.id.text)).setText(mData[position]);
        return v;
    }

}
