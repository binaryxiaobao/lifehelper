package com.example.lifehelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

import com.example.lifehelper.adapter.DateAdapter;
import com.example.lifehelper.adapter.MyItemEntry;
import com.example.lifehelper.adapter.MyItemEntry.MyItemClickListener;
import com.example.lifehelper.data.StaticVar;
import com.example.lifehelper.self_define.DragGrid;
import com.example.lifehelper.self_define.Rotate3DAnimation;

public class MainActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener, OnTouchListener {

	private DragGrid gv;
	private DateAdapter adapter;
	private List<MyItemEntry> entries;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gv = (DragGrid) findViewById(R.id.myGrid);

		initGrid();

		initAllData();
	
		gv.setOnItemClickListener(this);
		gv.setOnItemLongClickListener(this);
		gv.setOnTouchListener(this);

		//
		Rotate3DAnimation rotate3d = new Rotate3DAnimation(-90, 0, 0, 0, 0,
				true);
		rotate3d.setDuration(300);
		rotate3d.setFillAfter(true);
		LayoutAnimationController lac = new LayoutAnimationController(rotate3d);
		lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
		lac.setDelay(1f);
		
		
		gv.setLayoutAnimation(lac);
		gv.setAdapter(adapter);
	}
	
	/**
	 * 
	 * init txt's data
	 * @author wuyanbao
	 */
	public void initAllData(){
		
		StaticVar.fileName.removeAll(StaticVar.fileName);
		StaticVar.fileAll.removeAll(StaticVar.fileAll);
		StaticVar.fileSub.removeAll(StaticVar.fileSub);
		
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)){
			Toast.makeText(this, "sdcard没有挂在！", Toast.LENGTH_LONG).show();
		}else{
			File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
			File list[] = f.listFiles();
			
			for(int i=0;i<list.length;i++){
				
				//是否为我的应用存储的文件
				if(list[i].getName().contains("_ilife_xiaobao")){
					StaticVar.fileName.add(list[i].getName());
				}
			}
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					for(int j=0;j<StaticVar.fileName.size();j++){
						try {
							/*byte [] buffer = new byte[1024];
							int offset = 0;*/
							StringBuffer sb = new StringBuffer();
							String line = "";
							FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+StaticVar.fileName.get(j));
							BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
							
							while((line=reader.readLine())!=null){
								sb.append(line);
								System.out.println("----->init"+sb.toString());
							}
							
							StaticVar.fileAll.add(sb.toString());
							
							if(sb.length()>10){
								StaticVar.fileSub.add(sb.substring(0, 6)+"...");
							}else{
								StaticVar.fileSub.add(sb.toString());
							}
							
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			}).start();
		}
		
	}

	/**
	 * 
	 * init main view's gridview
	 * @author wuyanbao
	 */
	public void initGrid() {
		entries = new ArrayList<MyItemEntry>();
		entries.add(new MyItemEntry("天气预报", R.drawable.weather,
				new MyItemClickListener() {

					@Override
					public void onPerfom() {
						startActivity(new Intent(MainActivity.this,WeatherMainAct.class));
					}
				}));
		entries.add(new MyItemEntry("常用服务", R.drawable.user,
				new MyItemClickListener() {

					@Override
					public void onPerfom() {
						startActivity(new Intent(MainActivity.this,RegularNumberAct.class));
					}
				}));
		entries.add(new MyItemEntry("记事札记", R.drawable.jishi,
				new MyItemClickListener() {

					@Override
					public void onPerfom() {
						startActivity(new Intent(MainActivity.this,TxtAct.class));
					}
				}));
		entries.add(new MyItemEntry("快递查询", R.drawable.kuaidi,
				new MyItemClickListener() {

					@Override
					public void onPerfom() {
						startActivity(new Intent(MainActivity.this,ExpressSearchMainAct.class));
					}
				}));
		entries.add(new MyItemEntry("基本计算", R.drawable.jisuan,
				new MyItemClickListener() {

					@Override
					public void onPerfom() {
						startActivity(new Intent(MainActivity.this,CalculatorMainAct.class));
					}
				}));
		entries.add(new MyItemEntry("中国日历", R.drawable.calender,
				new MyItemClickListener() {

					@Override
					public void onPerfom() {
						startActivity(new Intent(MainActivity.this,CalendarMainAct.class));
					}
				}));

		entries.add(new MyItemEntry("地图导航", R.drawable.map_btn_location_up_boy,
				new MyItemClickListener() {

					@Override
					public void onPerfom() {
						startActivity(new Intent(MainActivity.this,MoreSetAct.class));
					}
				}));

		entries.add(new MyItemEntry("社交互联", R.drawable.about,
				new MyItemClickListener() {

					@Override
					public void onPerfom() {
						startActivity(new Intent(MainActivity.this,Login.class));
					}
				}));

		adapter = new DateAdapter(this, entries);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		System.out.println("-->position"+arg2);
		System.out.println("--->name:"+((MyItemEntry)(((DateAdapter)(arg0.getAdapter())).getItem(arg2))).getName());
		((MyItemEntry)(((DateAdapter)(arg0.getAdapter())).getItem(arg2))).getMyItemClickListener().onPerfom();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		return false;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	}

}
