package com.example.lifehelper;

import com.tencent.tauth.AuthActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Login extends Activity implements OnClickListener, IUiListener{
	
	private ImageButton QQLogin;
	private ImageView SinaLogin;
	private static final String APP_ID = "101130124";
	private Tencent mTeccent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);
		initView();
		//mTeccent = Tencent.createInstance(APP_ID, getApplicationContext());
	}
	
	public void initView(){
		
		QQLogin = (ImageButton) findViewById(R.id.qq_login);
		SinaLogin = (ImageView) findViewById(R.id.weibo_login);
		
		QQLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.qq_login:
			//startActivity(new Intent(Login.this,AuthActivity.class));
			mTeccent = Tencent.createInstance(APP_ID, getApplicationContext());
			mTeccent.login(Login.this, APP_ID, this);
			break;
			
		case R.id.weibo_login:
			
			break;
			
		default :
			break;
		}
	}

	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(UiError arg0) {
		// TODO Auto-generated method stub
		
	}

}
