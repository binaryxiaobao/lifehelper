package com.example.lifehelper.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CityListAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<String> City;
	private String [] mIndex = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };
	public CityListAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return mIndex.length;
	}

	@Override
	public Object getItem(int position) {
		return mIndex[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		
		if (convertView == null) {
			//convertView = LayoutInflater.from(mContext).inflate(resource, null);
		}
		return null;
	}

}
