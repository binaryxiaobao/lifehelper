package com.example.lifehelper;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lifehelper.adapter.TxtAdapter;
import com.example.lifehelper.data.StaticVar;

public class TxtAct extends Activity implements OnItemClickListener,
		OnClickListener, OnItemLongClickListener {

	private ListView list;
	private TxtAdapter ada;
	private LinearLayout ll2;
	private AlertDialog deletDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.txt_main);

		ll2 = (LinearLayout) findViewById(R.id.txtLl2);
		list = (ListView) findViewById(R.id.txtList);
		ada = new TxtAdapter(this);

		list.setAdapter(ada);
		list.setOnItemClickListener(this);
		ll2.setOnClickListener(this);
		list.setOnItemLongClickListener(this);

	}

	@Override
	protected void onResume() {
		super.onResume();

		// update the listview's ui
		ada.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Intent i = new Intent(TxtAct.this, TxtAddAct.class);
		i.putExtra("txts", StaticVar.fileAll.get(arg2).toString());
		i.putExtra("position", arg2);
		startActivity(i);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(TxtAct.this, TxtAddAct.class));
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
			final int arg2, long arg3) {

		deletDialog = new AlertDialog.Builder(this).create();
		deletDialog.show();
		deletDialog.setContentView(R.layout.delete_txt_dialog);
		Button ok = (Button) deletDialog.findViewById(R.id.dia_ok_dele);
		Button can = (Button) deletDialog.findViewById(R.id.dia_cancel_dele);

		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// delete
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					String path = Environment.getExternalStorageDirectory()
							.getAbsolutePath();
					String name = StaticVar.fileName.get(arg2);

					File f = new File(path + File.separator + name);

					f.delete();
					StaticVar.fileName.remove(arg2);
					StaticVar.fileAll.remove(arg2);
					StaticVar.fileSub.remove(arg2);

					ada.notifyDataSetChanged();
				} else {
					Toast.makeText(TxtAct.this, "sdcardƒæ”–π“‘ÿ", Toast.LENGTH_LONG).show();
				}
				
				deletDialog.cancel();

			}
		});

		can.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				deletDialog.cancel();

			}
		});

		return false;
	}

}
