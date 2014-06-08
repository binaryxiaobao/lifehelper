package com.example.lifehelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lifehelper.R;

public class ExpressSpinnerAdapter extends BaseAdapter {
	
	private Context context;
	
	String [] name = new String []{
			"圆通快递",
			"京广速递",
			"晋越快递",
			"捷特快递",
			"金大物流"
	};
	
	public ExpressSpinnerAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return name.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return name[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {

		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.express_item, null);
		}
		
		TextView tv = (TextView) convertView.findViewById(R.id.expressTv);
		tv.setText(name[arg0]);
		
		return convertView;
	}

}
