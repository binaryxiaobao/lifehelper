package com.example.lifehelper;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class AboutMainAct extends Activity implements OnPageChangeListener {

	private ArrayList<View> allViewForPager = new ArrayList<View>();
	private ViewPager myPager;
	private MyPagerAdapter ada;
	private ImageView g1,g2,g3,g4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View view = LayoutInflater.from(this).inflate(R.layout.about_main, null);
		setContentView(view);

		myPager = (ViewPager) findViewById(R.id.aboutViewPager);
		

		init();
	}

	/**
	 * ≥ı ºªØ
	 * 
	 * @author wuyanbao
	 */
	public void init() {
		
		g1 = (ImageView) findViewById(R.id.g1);
		g2 = (ImageView) findViewById(R.id.g2);
		g3 = (ImageView) findViewById(R.id.g3);
		g4 = (ImageView) findViewById(R.id.g4);

		for (int i = 0; i < 4; i++) {
			
			ImageView iv = new ImageView(this);
			iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			
			if (0 == i) {
				iv.setBackgroundResource(R.drawable.pic_a);
			} else if (1 == i) {
				iv.setBackgroundResource(R.drawable.pic_b);
			} else if (2 == i) {
				iv.setBackgroundResource(R.drawable.pic_c);
			} else if (3 == i) {
				iv.setBackgroundResource(R.drawable.pic_d);
			}

			allViewForPager.add(iv);
		}
		
		ada = new MyPagerAdapter();
		myPager.setAdapter(ada);
		myPager.setCurrentItem(0);
		myPager.setOnPageChangeListener(this);
	}
	
	
	class MyPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(allViewForPager.get(position));
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(allViewForPager.get(position));
			return allViewForPager.get(position);
		}
		
		@Override
		public int getCount() {
			return allViewForPager.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {


		// TODO Auto-generated method stub
		//currentitem = arg0;
		if(arg0 == 0){
			g1.setBackgroundResource(R.drawable.point02);
			for(int i=0; i<3; i++){
				g2.setBackgroundResource(R.drawable.point01);
				g3.setBackgroundResource(R.drawable.point01);
				g4.setBackgroundResource(R.drawable.point01);
			}
		}else if(arg0 == 1){
			g2.setBackgroundResource(R.drawable.point02);
			for(int i=0; i<3; i++){
				g1.setBackgroundResource(R.drawable.point01);
				g3.setBackgroundResource(R.drawable.point01);
				g4.setBackgroundResource(R.drawable.point01);
				
			}
		}else if(arg0 == 2){
			g3.setBackgroundResource(R.drawable.point02);
			for(int i=0; i<3; i++){
				g1.setBackgroundResource(R.drawable.point01);
				g2.setBackgroundResource(R.drawable.point01);
				g4.setBackgroundResource(R.drawable.point01);
				
			}
		}else if(arg0 == 3){
			g4.setBackgroundResource(R.drawable.point02);
			for(int i=0; i<3; i++){
				g1.setBackgroundResource(R.drawable.point01);
				g3.setBackgroundResource(R.drawable.point01);
				g2.setBackgroundResource(R.drawable.point01);
				
			}
		}
	}

}


