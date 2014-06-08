package com.example.lifehelper.self_define;


import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;


public class MyPopupWindow extends PopupWindow {
	private PopupWindow pop;
	//private Context context;
	






	public MyPopupWindow(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}




	public MyPopupWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}




	public MyPopupWindow(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyPopupWindow(View contentView, int width, int height,
			boolean focusable) {
		super(contentView, width, height, focusable);
		// TODO Auto-generated constructor stub
	}




	public MyPopupWindow(View contentView, int width, int height) {
		super(contentView, width, height);
		// TODO Auto-generated constructor stub
	}




	public MyPopupWindow(View contentView) {
		super(contentView);
		// TODO Auto-generated constructor stub
	}




	@Override
	public void showAsDropDown(View anchor) {
		
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		LinearLayout ll = new LinearLayout(getContentView().getContext());
		ll.setBackgroundColor(0x7f000000);
		ll.setLayoutParams(lp);
		pop = new PopupWindow(ll,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT, true);
		pop.setAnimationStyle(android.R.style.Animation_Toast);
		pop.showAtLocation(anchor, Gravity.CENTER, 0, 0);
		super.showAsDropDown(anchor);
		
	}
	
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		pop.dismiss();
		super.dismiss();
	}

}
