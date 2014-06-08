package com.example.lifehelper.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*import android.graphics.Bitmap;*/

public class DownloadWeatherJson {
	
	public String loadMessage(String path){
		HttpURLConnection conn = null;
		InputStream stream = null;
		BufferedReader reader = null;
		//Bitmap b = null;
		String line = "";
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(path);
			System.out.println("--->"+path);
			conn = (HttpURLConnection) url.openConnection();
			stream = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(stream));
			//b = BitmapFactory.decodeStream(stream);
			while((line = reader.readLine())!=null){
				sb.append(line);
				System.out.println("--->line"+line);
			}
			System.out.println("----->"+sb.toString());
			return sb.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				conn.disconnect();
			}
			if(sb!=null){
				try {
					if(stream!=null){
						stream.close();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	

}
