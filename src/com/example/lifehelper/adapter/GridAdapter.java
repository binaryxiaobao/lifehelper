package com.example.lifehelper.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.lifehelper.R;
/*import com.example.lifehelper.self_define.Rotate3DAnimation;*/

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/*import android.view.animation.AnimationUtils;*/
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
	
	@SuppressWarnings("unused")
	private int[] pics = new int[]{R.drawable.weather,
			R.drawable.user,
			R.drawable.jishi,
			R.drawable.kuaidi,
			R.drawable.jisuan,
			R.drawable.calender,
			};
	
	@SuppressWarnings("unused")
	private String[] text = new String[]{
			"天气预报",
			"常用服务",
			"记事札记",
			"快递查询",
			"基本计算",
			"中国日历",
	};
	
	private List<MyItemEntry> entries= new ArrayList<MyItemEntry>();
	
	private Context context;
	
	public GridAdapter(Context context,List<MyItemEntry> entries) {

		this.context = context;
		this.entries.addAll(entries);
		
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return entries.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressWarnings("unused")
	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, null);
		}
		float centerX = convertView.getX();
		float centerY = convertView.getY();
		
		ImageView iv = (ImageView) convertView.findViewById(R.id.myiv);
		TextView tv = (TextView) convertView.findViewById(R.id.mytv);
		
		iv.setImageResource(entries.get(position).getDrawable());
		tv.setText(entries.get(position).getName());
		
		
		
		return convertView;
	}

}
