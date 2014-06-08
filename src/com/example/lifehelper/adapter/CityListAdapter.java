package com.example.lifehelper.adapter;

import java.util.ArrayList;

import com.example.lifehelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class CityListAdapter extends BaseAdapter implements SectionIndexer{
	private Context mContext;
	private ArrayList<String> City;
	private String [] mIndex = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };
	//private ViewHolder mHolder;
	
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
		ViewHolder mHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.city_list, null);
		}
		if (null == mHolder) {
			mHolder = new ViewHolder();
			mHolder.indexLetter = (TextView) convertView.findViewById(R.id.index_letter);
			//mHolder.gridView = (GridView) convertView.findViewById(R.id.city);
			convertView.setTag(mHolder);
		}else {
			mHolder = (ViewHolder) convertView.getTag();
		}System.out.println("------->mHolder"+mHolder);
		System.out.println("------->indexLetter"+mHolder.indexLetter);
		mHolder.indexLetter.setText(mIndex[position]);
		
		return convertView;
	}
	
	private static class ViewHolder{
		TextView indexLetter;
		GridView gridView;
	}

	@Override
	public int getPositionForSection(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSectionForPosition(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

}
