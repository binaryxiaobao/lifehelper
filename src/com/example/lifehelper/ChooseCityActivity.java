package com.example.lifehelper;

import com.example.lifehelper.adapter.CityListAdapter;
import com.example.lifehelper.self_define.SideBar;
import com.example.lifehelper.self_define.SideBar.OnTouchingLetterChangedListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseCityActivity extends Activity implements OnTouchingLetterChangedListener {
	
	private SideBar sidebar;
	private ListView cityList;
	private CityListAdapter adapter;
	private TextView dialog;
	private TextView mPinnedHeader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_city_main);
		initViews();
	}
	
	public void initViews(){
		mPinnedHeader = (TextView) findViewById(R.id.pinned_header);
		sidebar = (SideBar) findViewById(R.id.sidebar);
		dialog = (TextView) findViewById(R.id.text_choose);
		sidebar.setCenterDialog(dialog);
		cityList = (ListView) findViewById(R.id.city_list);
		adapter = new CityListAdapter(this);
		cityList.setAdapter(adapter);
		
		sidebar.setOnTouchingLetterChangedListener(this);
		
	}

	@Override
	public void onTouchingLetterChanged(String s) {
		int position = s.toCharArray()[0] - 'A';
		cityList.setSelection(position);
	}

}
