package com.example.lifehelper;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.lifehelper.adapter.ExpressSpinnerAdapter;
import com.example.lifehelper.net.DownloadWeatherJson;

public class ExpressSearchMainAct extends Activity implements OnItemSelectedListener, OnClickListener {
	
	private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
	private LinearLayout ll;
	private Button cancel;
	private EditText et,con;
	private Spinner mySpin;
	private ExpressSpinnerAdapter ada;
	//private String expressType = "Ô²Í¨";
	private String uri = "http://api.kuaidi100.com/api?id=d3d26f3c59bd5a9b&com=company&nu=post_id&order=asc";
	private String [] comCode = new String[]{
			"yuantong",
			"jingguangsudikuaijian",
			"jinyuekuaidi",
			"jietekuaidi",
			"jindawuliu"
	};
	
	@SuppressLint("HandlerLeak")
	//private String number="";
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			String contend = "";
			for(int i=0;i<list.size();i++){
				contend += list.get(i).get("time")+"\n "+list.get(i).get("context")+"\n\n";
			}
			System.out.println(contend);
			con.setText(contend);
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_express_main);
		
		ll = (LinearLayout) findViewById(R.id.ex_ll);
		con = (EditText) findViewById(R.id.display_express);
		et = (EditText) findViewById(R.id.search);
		cancel = (Button) findViewById(R.id.search_cancel);
		mySpin = (Spinner) findViewById(R.id.expressSpin);
		ada = new ExpressSpinnerAdapter(this);
		
		et.setOnClickListener(this);
		cancel.setOnClickListener(this);
		
		mySpin.setAdapter(ada);
		mySpin.setOnItemSelectedListener(this);
	}
	

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		//expressType = (String) ada.getItem(arg2);
		uri = "http://api.kuaidi100.com/api?id=d3d26f3c59bd5a9b&com="+comCode[arg2]+"&nu="+et.getText()+"&order=asc";
		System.out.println("-->"+uri);
		
		if(isNetWorkOpen()&&!et.getText().equals("")){
			getExpressFromJson();
		}
		
	}
	
	/**
	 * get the detail from json data
	 * @author wuyanbao
	 */
	public void getExpressFromJson(){
		

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				JSONObject jsObj;
				JSONArray jArray;
				//ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
				try {
					
					jsObj = new JSONObject((new DownloadWeatherJson()).loadMessage(uri));
					String json = jsObj.getString("data");
					jArray = new JSONArray(json);
					
					//Çå¿Õ¼ÇÂ¼
					list.removeAll(list);
					
					for(int i = 0; i<jArray.length();i++){
						JSONObject jj = jArray.getJSONObject(i);
						HashMap<String, String> h = new HashMap<String, String>();
						String date = jj.getString("time");
						String context = jj.getString("context");
						System.out.println("------->"+date);
						h.put("time", date);
						h.put("context", context);
						
						list.add(h);
					}
					
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
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.search:
			ll.setVisibility(View.VISIBLE);
			break;
			
		case R.id.search_cancel:
			ll.setVisibility(View.GONE);
			et.setText("");

		default:
			break;
		}
	}

}
