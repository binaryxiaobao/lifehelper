package com.example.lifehelper.adapter;

import com.example.lifehelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OperatorNumAdapter extends BaseAdapter {
	
	String name[] = new String[]{"北京联通VIP客服",
			"北京联通电话营销热线",
			"联通秘书",
			"中国电信充值付费",
			"中国电信号码百事通",
			"中国电信客服",
			"中国电信自助服务热线",
			"中国联通充值专线",
			"中国联通电话导航",
			"中国联通集团客服",
			"中国联通客服",
			"中国联通投诉中心"
			};
	
	public String num[] = new String[]{"10018",
			"10016",
			"10198",
			"11888",
			"118114",
			"10000",
			"10001",
			"10011",
			"116114",
			"10019",
			"10010",
			"10015",};
	private Context context;
	
	public OperatorNumAdapter(Context context){
		this.context = context;
	}

	@Override
	public int getCount() {
		return name.length;
	}

	@Override
	public Object getItem(int position) {
		return name[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.operator_num_item, null);
		}
		
		TextView nameTv = (TextView) convertView.findViewById(R.id.nameTv5);
		TextView numTv = (TextView) convertView.findViewById(R.id.numTv5);
		
		nameTv.setText(name[position]);
		numTv.setText(num[position]);
		
		return convertView;
	}

}
