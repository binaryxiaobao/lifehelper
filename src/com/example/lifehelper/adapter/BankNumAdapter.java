package com.example.lifehelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lifehelper.R;

public class BankNumAdapter extends BaseAdapter {
	
	String name[] = new String[]{"北京农商银行",
			"北京银行",
			"工商银行",
			"工商银行贵宾服务",
			"光大银行",
			"光大银行信用卡",
			"广发客服中心",
			"广发银行信用卡专线",
			"国家开发银行",
			"华夏银行",
			"建设银行",
			"北京银行",
			"工商银行",
			"工商银行贵宾服务",
			"光大银行",
			"光大银行信用卡",
			"广发客服中心",
			"广发银行信用卡专线",
			"国家开发银行",
			"华夏银行",
			"建设银行",
			"北京银行",
			"工商银行",
			"工商银行贵宾服务",
			"光大银行",
			"光大银行信用卡",
			"广发客服中心",
			"广发银行信用卡专线",
			"国家开发银行",
			"华夏银行",
			"建设银行",
			"北京银行",
			"工商银行",
			"工商银行贵宾服务",
			"光大银行",
			"光大银行信用卡",
			"广发客服中心",
			"广发银行信用卡专线",
			"国家开发银行",
			"华夏银行",
			"建设银行",
			"北京银行",
			"工商银行",
			"工商银行贵宾服务",
			"光大银行",
			"光大银行信用卡",
			"广发客服中心",
			"广发银行信用卡专线",
			"国家开发银行",
			"华夏银行",
			"建设银行",
			"北京银行",
			"工商银行",
			"工商银行贵宾服务",
			"光大银行",
			"光大银行信用卡",
			"广发客服中心",
			"广发银行信用卡专线",
			"国家开发银行",
			"华夏银行",
			"建设银行",
			"北京银行",
			"工商银行",
			"工商银行贵宾服务",
			"光大银行",
			"光大银行信用卡",
			"广发客服中心",
			"广发银行信用卡专线",
			"国家开发银行",
			"华夏银行",
			"建设银行"
			};
	
	public String num[] = new String[]{"96198",
			"95526",
			"95588",
			"4006695588",
			"95595",
			"4007888888",
			"4008308003",
			"95508",
			"95593",
			"95577",
			"95533",
			"95526",
			"95588",
			"4006695588",
			"95595",
			"4007888888",
			"4008308003",
			"95508",
			"95593",
			"95577",
			"95533",
			"95526",
			"95588",
			"4006695588",
			"95595",
			"4007888888",
			"4008308003",
			"95508",
			"95593",
			"95577",
			"95533",
			"95526",
			"95588",
			"4006695588",
			"95595",
			"4007888888",
			"4008308003",
			"95508",
			"95593",
			"95577",
			"95533",
			"95526",
			"95588",
			"4006695588",
			"95595",
			"4007888888",
			"4008308003",
			"95508",
			"95593",
			"95577",
			"95533",
			"95526",
			"95588",
			"4006695588",
			"95595",
			"4007888888",
			"4008308003",
			"95508",
			"95593",
			"95577",
			"95533",
			"95526",
			"95588",
			"4006695588",
			"95595",
			"4007888888",
			"4008308003",
			"95508",
			"95593",
			"95577",
			"95533",};
	private Context context;
	
	public BankNumAdapter(Context context){
		this.context = context;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.bank_num_item, null);
		}
		
		TextView nameTv = (TextView) convertView.findViewById(R.id.nameTv3);
		TextView numTv = (TextView) convertView.findViewById(R.id.numTv3);
		
		nameTv.setText(name[position]);
		numTv.setText(num[position]);
		
		return convertView;
	}

}
