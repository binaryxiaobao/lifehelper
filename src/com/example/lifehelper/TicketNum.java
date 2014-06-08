package com.example.lifehelper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.lifehelper.adapter.TicketNumAdapter;

public class TicketNum extends Activity implements OnItemClickListener {
	
	private ListView list ;
	private TicketNumAdapter ada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ticket_num_list);
		ada = new TicketNumAdapter(this);
		list = (ListView) findViewById(R.id.my_list);
		
		list.setAdapter(ada);
		list.setOnItemClickListener(this);
		
		
		System.out.println("----->ticket");
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		
		System.out.println("----->item");
		String uri = "tel://";
		Intent i = new Intent(Intent.ACTION_DIAL,Uri.parse(uri+ada.num[arg2]));
		startActivity(i);
		
		
	}

	

}
