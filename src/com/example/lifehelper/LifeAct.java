package com.example.lifehelper;

import com.example.lifehelper.adapter.LifeNumAdapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class LifeAct extends Activity implements OnItemClickListener {
	
	private ListView list;
	private LifeNumAdapter ada;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.life_num_list);
		
		list = (ListView) findViewById(R.id.my_list4);
		ada = new LifeNumAdapter(this);
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
