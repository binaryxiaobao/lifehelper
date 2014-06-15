package com.example.lifehelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MoreSetAct extends Activity implements OnClickListener {
	
	private Button mNavigation;
	private Button mSearchBus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.more_set_main);
		
		mNavigation = (Button) findViewById(R.id.navigation_btn);
		mSearchBus = (Button) findViewById(R.id.bus_search_btn);
		mNavigation.setOnClickListener(this);
		mSearchBus.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.navigation_btn:
			startActivity(new Intent(this, MyMap.class));
			break;

		default:
			startActivity(new Intent(this, RouteActivity.class));
			break;
		}
		
	}

}
