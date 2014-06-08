package com.example.lifehelper;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifehelper.adapter.PopupAdapter;
import com.example.lifehelper.net.DownloadWeatherJson;
import com.example.lifehelper.self_define.MyPopupWindow;

public class WeatherMainAct extends Activity implements OnDismissListener, OnClickListener, OnItemClickListener {

	private TextView tempTv, detailTv, weatherTv,cityTv;
	private ImageView bg,weatherIv;
	private final String url = "http://m.weather.com.cn/data/";
	private String cityId="101010100";
	private MyPopupWindow popwindow;
	private View popV;
	private ListView popList;
	private String date,temp,wind,weather,sugest;
	private String cityStr="北京";
	private String cityIds[] = new String[]{"101010100",
			"101020100",
			"101280101",
			"101280601",
			"101100101",
			"101220101",
			"101310201",
			"101140101",
			"101290101",
			"101190101",
			"101210101",
			"101220601"};
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			tempTv.setText(temp);
			detailTv.setText(wind+" \n"+date+"\n"+sugest);
			weatherTv.setText(weather);
			
			SharedPreferences weatheSp = getSharedPreferences("weatherLast", MODE_PRIVATE);
			
			Editor editor = weatheSp.edit();
			editor.putString("temp", temp);
			editor.putString("wind", wind+"\n"+date+"\n"+sugest);
			editor.putString("weather", weather);
			editor.commit();
			
			if(weather.contains("晴")){
				
				bg.setBackgroundResource(R.drawable.bg0_fine_day);
				weatherIv.setImageResource(R.drawable.w0);
			}else if(weather.contains("雷")){
				bg.setBackgroundResource(R.drawable.bg_thunder_storm);
				weatherIv.setImageResource(R.drawable.w4);
			}else if(weather.contains("雨")){
				bg.setBackgroundResource(R.drawable.bg_rain);
				weatherIv.setImageResource(R.drawable.w10);
			}else if(weather.contains("雪")){
				bg.setBackgroundResource(R.drawable.bg14_day_snow);
				weatherIv.setImageResource(R.drawable.w17);
			}else{
				bg.setBackgroundResource(R.drawable.bg1_cloudy_day);
				weatherIv.setImageResource(R.drawable.w2);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.weather_main);

		tempTv = (TextView) findViewById(R.id.tv_tempr);
		detailTv = (TextView) findViewById(R.id.detail_tv);
		weatherIv = (ImageView) findViewById(R.id.weather_iv);
		weatherTv = (TextView) findViewById(R.id.weather_tv);
		cityTv = (TextView) findViewById(R.id.city);
		bg = (ImageView) findViewById(R.id.iv1);
		
		cityTv.setOnClickListener(this);
		//city = cityTv.getText().toString();
		
		init();
		

	}
	
	/**
	 * 
	 * 
	 * 
	 */
	public void initWeatherInfo(){
		SharedPreferences sp = getSharedPreferences("weatherLast", MODE_PRIVATE);
		Editor editor = sp.edit();
		
		temp = sp.getString("temp", "20°~12°");
		wind = sp.getString("wind", "2013年8月22日\n哈哈哈");
		weather = sp.getString("weather", "雨");
		
		tempTv.setText(temp);
		detailTv.setText(wind);
		weatherTv.setText(weather);
		cityTv.setText(cityStr);
		
		if(weather.contains("晴")){
			
			bg.setBackgroundResource(R.drawable.bg0_fine_day);
			weatherIv.setImageResource(R.drawable.w0);
		}else if(weather.contains("雷")){
			bg.setBackgroundResource(R.drawable.bg_thunder_storm);
			weatherIv.setImageResource(R.drawable.w4);
		}else if(weather.contains("雨")){
			bg.setBackgroundResource(R.drawable.bg_rain);
			weatherIv.setImageResource(R.drawable.w10);
		}else if(weather.contains("雪")){
			bg.setBackgroundResource(R.drawable.bg14_day_snow);
			weatherIv.setImageResource(R.drawable.w17);
		}else{
			bg.setBackgroundResource(R.drawable.bg1_cloudy_day);
			weatherIv.setImageResource(R.drawable.w2);
		}
	}
	
	/**
	 * 
	 * init poupwindow 
	 * @author wuyanbao
	 */
	public void init(){
		popV = LayoutInflater.from(this).inflate(R.layout.first_pop, null);
		
		popList = (ListView) popV.findViewById(R.id.pop1_list);
		PopupAdapter ada = new PopupAdapter(this);
		popList.setAdapter(ada);
		//popList.setOnItemSelectedListener(this);
		popList.setOnItemClickListener(this);
		popwindow = new MyPopupWindow(popV, 75, 300);
		popwindow.setBackgroundDrawable(new ColorDrawable(0));
		popwindow.setAnimationStyle(R.style.popAnim);
		popwindow.setOutsideTouchable(true);
		popwindow.setOnDismissListener(this);
		popwindow.setFocusable(true);
		
		initWeatherInfo();

	}

	@Override
	protected void onResume() {

		super.onResume();

		if (isNetWorkOpen()) {
			System.out.println("---->可用");
			getWeatherJson();
			
			
		} else {
			initWeatherInfo();

			Toast.makeText(this, "当前没有可用网络哦！", Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * get weather's json data
	 * @author wuyanbao
	 */
	public void getWeatherJson(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				JSONObject jsObj;
				try {
					
					jsObj = new JSONObject((new DownloadWeatherJson()).loadMessage(url+cityId+".html"));
					jsObj = jsObj.getJSONObject("weatherinfo");
					
					date = jsObj.getString("date")+" "+jsObj.getString("date_y");
					temp = jsObj.getString("temp1");
					wind = jsObj.getString("wind1");
					weather = jsObj.getString("weather1");
					sugest = jsObj.getString("index_d");
					
					handler.sendEmptyMessage(0);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}

	/**
	 * 
	 * is useable network
	 * @author wuyanbao
	 * @return
	 */
	public boolean isNetWorkOpen() {

		ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity == null) {
			System.out.println("--->con==null");
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {

						System.out.println("---->use" + info[i].getTypeName());
						return true;
					}
				}
			}
		}

		return false;

	}

	@Override
	public void onDismiss() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//popwindow.showAsDropDown(v);
		
		startActivity(new Intent(WeatherMainAct.this,ChooseCityActivity.class));
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		popwindow.dismiss();
		System.out.println("----->dismiss");
		cityTv.setText(PopupAdapter.choose[arg2]);
		cityStr = PopupAdapter.choose[arg2];
		cityId = cityIds[arg2];
		//city = cityTv.getText().toString();
		
		if (isNetWorkOpen()) {
			System.out.println("---->可用");
			getWeatherJson();
		} else {
			Toast.makeText(this, "当前没有可用网络哦！", Toast.LENGTH_SHORT).show();
		}
	}

}
