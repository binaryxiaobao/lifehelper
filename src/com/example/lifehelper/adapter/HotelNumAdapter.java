package com.example.lifehelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lifehelper.R;

public class HotelNumAdapter extends BaseAdapter {
	
	String name[] = new String[]{"7Ìì¾Æµê",
			"°²ÒÝ158",
			"¸ñÁÖºÀÌ©",
			"ººÍ¥¾Æµê",
			"½õ½­Ö®ÐÇ",
			"½Û×Ó¾Æµê",
			"ÁëÄÏ¼ÑÔ°¾Æµê",
			"Èç¼Ò¾Æµê",
			"ËÙ8¾Æµê",
			"ÍòºÀ¾Æµê",
			"Ï²´ïÎÝ¾Æµê",
			"Ïã¸ñÀïÀ­¾Æµê"
			};
	
	public String num[] = new String[]{"4008740087",
			"4006028158",
			"4006998998",
			"4008121121",
			"4008209999",
			"4008190099",
			"4008308331",
			"4008203333",
			"4001840018",
			"4008300251",
			"4008688688",
			"4001205900",};
	private Context context;
	
	public HotelNumAdapter(Context context){
		this.context =context;
		System.out.println("---->");
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return name.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return name[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.hotel_num_item, null);
		}
		
		TextView nameTv = (TextView) convertView.findViewById(R.id.nameTv2);
		TextView numTv = (TextView) convertView.findViewById(R.id.numTv2);
		
		nameTv.setText(name[position]);
		numTv.setText(num[position]);
		
		return convertView;
	}

}
