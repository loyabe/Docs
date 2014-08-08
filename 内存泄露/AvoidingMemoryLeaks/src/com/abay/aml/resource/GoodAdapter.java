package com.abay.aml.resource;

import com.abay.aml.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 
 * @version 1.0.0 
 * @author Abay Zhuang <br/>
 *		   Create at 2014-8-7
 */
public class GoodAdapter extends BaseAdapter {
	private static final String TAG = GoodAdapter.class.getSimpleName();
	private Context mContext;
	
    private String[] mData;
    private Drawable mDefaultDrawable;

    public GoodAdapter(Context mContext, String[] mData) {
        this.mContext = mContext;
        this.mData = mData;
        mDefaultDrawable = mContext.getResources().getDrawable(R.drawable.ic_launcher);
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
        Log.d(TAG, "Position:" + position + "---"
                + String.valueOf(System.currentTimeMillis()));
        ViewHolder holder;
        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_icon_text, null);
            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.icon.setImageDrawable(mDefaultDrawable);
        holder.text.setText(mData[position]);
        return convertView;
    }

    static class ViewHolder {
        ImageView icon;
        TextView text;
    }

}
