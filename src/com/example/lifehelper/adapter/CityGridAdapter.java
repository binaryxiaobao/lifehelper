package com.example.lifehelper.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CityGridAdapter extends BaseAdapter {
	
	private Context mContext;
	private String[] mCityName;

	public CityGridAdapter(Context context, String[] cityName) {
		this.mContext = context;
		this.mCityName = cityName;
	}

	@Override
	public int getCount() {
		return mCityName.length;
	}

	@Override
	public Object getItem(int position) {
		return mCityName[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		
		TextView tv = new TextView(mContext);
		tv.setText(mCityName[position]);
		tv.setGravity(Gravity.CENTER);
		tv.setPadding(0, 10, 0, 10);
		return tv;
	}
	

}
