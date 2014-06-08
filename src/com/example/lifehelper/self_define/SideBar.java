package com.example.lifehelper.self_define;

import com.example.lifehelper.ChooseCityActivity;
import com.example.lifehelper.R;

import android.app.Notification.Action;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SideBar extends View {
	
	private OnTouchingLetterChangedListener mOnTouchingLetterChangedListener;
	private TextView mCenterDialog;
	private int mChoose = -1;
	private Paint paint = new Paint();
	private String [] mAlp = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };

	public SideBar(Context context) {
		super(context);
	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	public void setCenterDialog(TextView centerDialog){
		this.mCenterDialog = centerDialog;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int height = getHeight();
		int width = getWidth();
		double singleHeight = height/mAlp.length;
		
		for (int i=0; i<mAlp.length; i++) {
			paint.setColor(Color.GRAY);
			paint.setTypeface(Typeface.DEFAULT);
			paint.setAntiAlias(true);
			paint.setTextSize(20);
			
			if (i == mChoose) {
				paint.setColor(Color.BLUE);
				paint.setFakeBoldText(true);
			}
			
			float xPoint = width/2 - paint.measureText(mAlp[i])/2;
			float yPoint = (float) (singleHeight*i + singleHeight);
			canvas.drawText(mAlp[i], xPoint, yPoint, paint);
			paint.reset();
		}
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		int action = event.getAction();
		float y = event.getY();
		int num = (int) (y/getHeight() * mAlp.length);
		int oldChoose = mChoose;
		OnTouchingLetterChangedListener listener = mOnTouchingLetterChangedListener;
		
		switch (action) {
		case MotionEvent.ACTION_UP:
			setBackgroundDrawable(new ColorDrawable(0x00000000));
			mChoose = -1;
			//再次触发绘制onDraw()方法
			invalidate();
			if (null != mCenterDialog) {
				mCenterDialog.setVisibility(INVISIBLE);
			}
			break;

		default:
			setBackgroundResource(R.drawable.sidebar_background);
			if (num != oldChoose) {
				if (num >= 0 && num < mAlp.length) {
					if (null != listener) {
						listener.onTouchingLetterChanged(mAlp[num]);
					}
					if (null != mCenterDialog) {
						mCenterDialog.setText(mAlp[num]);
						mCenterDialog.setVisibility(VISIBLE);
					}
				}
				mChoose = num;
				invalidate();
			}
			
			break;
		}
		
		return super.dispatchTouchEvent(event);
	}
	
	public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener){
		mOnTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}
	
	public interface OnTouchingLetterChangedListener{
		public void onTouchingLetterChanged(String s);
	}

}
