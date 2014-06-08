package com.example.lifehelper;

import java.lang.reflect.Field;

import com.example.lifehelper.adapter.BankNumAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BankNumAct extends Activity implements OnItemClickListener {
	
	private ListView list;
	private BankNumAdapter ada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bank_list);
		
		list = (ListView) findViewById(R.id.my_list3);
		
		//更改listview快读滑动块的图片
		try {
			//java反射机制
			Field f = AbsListView.class.getDeclaredField("mFastScroller");
			
			if(!f.isAccessible()){
				f.setAccessible(true);
			}
			
			Object o = f.get(list);
			f = f.getType().getDeclaredField("mThumbDrawable");
			f.setAccessible(true);
			Drawable dra = (Drawable) f.get(o);
			dra = getResources().getDrawable(R.drawable.protection_v2_log_empty);
			f.set(o, dra);
			
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
		ada = new BankNumAdapter(this);
		list.setAdapter(ada);
		
		list.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		System.out.println("----->item");
		String uri = "tel://";
		Intent i = new Intent(Intent.ACTION_DIAL,Uri.parse(uri+ada.num[arg2]));
		startActivity(i);
	}

}
