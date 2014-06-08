package com.example.lifehelper;

import com.example.lifehelper.adapter.HotelNumAdapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class HotelNum extends Activity implements OnItemClickListener {
	
	private ListView list;
	private HotelNumAdapter ada;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotel_num_list);
		
		list = (ListView) findViewById(R.id.my_list2);
		ada = new HotelNumAdapter(this);
		
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
