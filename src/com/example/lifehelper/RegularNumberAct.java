package com.example.lifehelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class RegularNumberAct extends Activity implements OnClickListener {
	
	private LinearLayout ll1,ll2,ll3,ll4,ll5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.regular_number_main);
		
		ll1 = (LinearLayout) findViewById(R.id.book);
		ll2 = (LinearLayout) findViewById(R.id.jiudian);
		ll3 = (LinearLayout) findViewById(R.id.finance);
		ll4 = (LinearLayout) findViewById(R.id.life);
		ll5 = (LinearLayout) findViewById(R.id.operator);
		
		ll1.setOnClickListener(this);
		ll2.setOnClickListener(this);
		ll3.setOnClickListener(this);
		ll4.setOnClickListener(this);
		ll5.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {

		switch(v.getId()){
		case R.id.book:
			startActivity(new Intent(RegularNumberAct.this,TicketNum.class));
			break;
			
		case R.id.jiudian:
			startActivity(new Intent(RegularNumberAct.this,HotelNum.class));
			break;
			
		case R.id.finance:
			startActivity(new Intent(RegularNumberAct.this,BankNumAct.class));
			break;
		
		case R.id.life:
			startActivity(new Intent(RegularNumberAct.this,LifeAct.class));
			break;
			
		case R.id.operator:
			startActivity(new Intent(RegularNumberAct.this,OperatorNumAct.class));
			break;
		}
		
	}

}
