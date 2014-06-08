package com.example.lifehelper.adapter;

public class MyItemEntry {
	private String name;
	private int drawable;
	private MyItemClickListener myItemClickListener;
	
	
	
	public MyItemEntry(String name, int drawable,
			MyItemClickListener myItemClickListener) {
		super();
		this.name = name;
		this.drawable = drawable;
		this.myItemClickListener = myItemClickListener;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getDrawable() {
		return drawable;
	}



	public void setDrawable(int drawable) {
		this.drawable = drawable;
	}



	public MyItemClickListener getMyItemClickListener() {
		return myItemClickListener;
	}



	public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
		this.myItemClickListener = myItemClickListener;
	}



	public interface MyItemClickListener{
		public void onPerfom();
	}
}
