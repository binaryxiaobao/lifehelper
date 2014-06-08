package com.example.lifehelper;

import com.example.lifehelper.self_define.SideBar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ChooseCityActivity extends Activity {
	
	private SideBar sidebar;
	private ListView cityList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_city_main);
	}
	
	public void initViews(){
		sidebar = (SideBar) findViewById(R.id.sidebar);
		cityList = (ListView) findViewById(R.id.city_list);
	}

}
