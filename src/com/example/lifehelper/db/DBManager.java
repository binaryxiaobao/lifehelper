package com.example.lifehelper.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.example.lifehelper.R;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Environment;

public class DBManager {
	private String PACKAGE_NAME = "com.example.lifehelper";
	private String DBPATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME + "/databases";
	private Context context;
	public static String DB_NAME = "city.db";

	public DBManager(Context context) {
		this.context = context;
	}
	
	public void copyDatabase() {
		
		File file = new File(DBPATH);
		if (!file.isDirectory()) {
			file.mkdir();
		}
		String dbFile = file + "/" + "city.db";
		
		if ((new File(dbFile)).length() == 0) {
			try {
				//FileInputStream fis = new FileInputStream(this.context.getResources().openRawResource(R.raw.citychina).toString());
				FileOutputStream fos = new FileOutputStream(dbFile);
				byte[] buffer = new byte[400000];
				InputStream is =  this.context.getResources().openRawResource(R.raw.citychina);
				int count = 0;
				while((count = is.read(buffer)) > 0){
					fos.write(buffer);
				}
				fos.close();
				is.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
