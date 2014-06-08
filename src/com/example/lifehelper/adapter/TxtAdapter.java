package com.example.lifehelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
/*import android.view.View.OnClickListener;*/
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/*import android.widget.ImageView;
import android.widget.LinearLayout;*/
import android.widget.TextView;

import com.example.lifehelper.R;
import com.example.lifehelper.data.StaticVar;

public class TxtAdapter extends BaseAdapter {
	
	private Context context;
	/*private boolean isOpen = false;*/
	
	public void update(){
		
	}
	
	public TxtAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return StaticVar.fileSub.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return StaticVar.fileSub.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.txt_list_item, null);
		}
		
		TextView tt = (TextView) convertView.findViewById(R.id.txtTv);
		//ImageView iv = (ImageView) convertView.findViewById(R.id.txtMore);
	//	final LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.txtLl);
		
		/*iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(isOpen){
					ll.setVisibility(View.GONE);
					isOpen = false;
				}else{
					ll.setVisibility(View.VISIBLE);
					isOpen = true;
				}
			}
		});*/
		tt.setText(StaticVar.fileSub.get(position).toString());
		
		return convertView;
	}

}
