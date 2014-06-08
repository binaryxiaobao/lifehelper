package com.example.lifehelper.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.lifehelper.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DateAdapter extends BaseAdapter {

	private static final String TAG = "TAG";
	private Context context;
	private List<MyItemEntry> lstData;
	private TextView txtAge;
	private ImageView imgViewItem;
	private int holdPosition;
	private boolean isChanged = false;
	private boolean ShowItem = false;

	/**
	 * 构造函数
	 * 
	 * @param mContext
	 * @param listData
	 */
	public DateAdapter(Context mContext,
			List<MyItemEntry> listData) {
		this.context = mContext;
		this.lstData = listData;
	}

	/**
	 * 重写获得数量
	 */
	@Override
	public int getCount() {
		return lstData.size();
	}

	/**
	 * 重写活动Item
	 */
	@Override
	public Object getItem(int position) {
		return lstData.get(position);
	}

	/**
	 * 重写获得ItemID
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	public void exchange(int startPosition, int endPosition) {
		Log.d(TAG, startPosition+"--->"+endPosition);
		holdPosition = endPosition;
		//通过重写的getItem方法获得一个移动前的Item
		Object startObject = getItem(startPosition);
		if (startPosition < endPosition) {
			//改变数据源的顺序
			//下标为结束位置+1，值为拖动的Item本身
			lstData.add(endPosition + 1, (MyItemEntry)startObject);
			//通过下标走原来的原理的数据源中的元素
			lstData.remove(startPosition);
		} else {
			lstData.add(endPosition, (MyItemEntry)startObject);
			lstData.remove(startPosition + 1);
		}
		isChanged = true;
		notifyDataSetChanged();
	}

	public void showDropItem(boolean showItem) {
		this.ShowItem = showItem;
	}

	/**
	 * 重写getView
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 填充每一个Item
		convertView = LayoutInflater.from(context).inflate(R.layout.grid_item,
				null);
		imgViewItem = (ImageView) convertView
				.findViewById(R.id.myiv);
		txtAge = (TextView) convertView.findViewById(R.id.mytv);
		imgViewItem.setImageResource((Integer) lstData.get(position).getDrawable());
		txtAge.setText(lstData.get(position).getName());
		// 如果位置改变
		if (isChanged) {
			//原来的位置 == 移动结束的位置
			if (position == holdPosition) {
				if (!ShowItem) {
					//不可见，但这个View仍然会占用在xml文件中所分配的布局空间
					convertView.setVisibility(View.INVISIBLE);
				}
			}
		}

		return convertView;
	}

}
