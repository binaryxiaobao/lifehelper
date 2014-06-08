package com.example.lifehelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lifehelper.data.StaticVar;

public class TxtAddAct extends Activity implements OnClickListener {

	private EditText content;
	private Button cancel, save;
	private String txts = "";
	private FileOutputStream fos;
	private boolean isExit = false;
	private int position;
	private AlertDialog saveDialog;
	private String fileN;
	private EditText nameEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		if (getIntent() != null) {
			if (getIntent().getStringExtra("txts") != null) {

				txts = getIntent().getStringExtra("txts");
				position = getIntent().getIntExtra("position", 0);
				isExit = true;
			}
		}

		setContentView(R.layout.add_txt);

		cancel = (Button) findViewById(R.id.cancel);
		save = (Button) findViewById(R.id.save);
		content = (EditText) findViewById(R.id.content);
		content.setText(txts);

		cancel.setOnClickListener(this);
		save.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.cancel:
			finish();
			break;
		case R.id.save:
			save();
			break;
		}

	}

	/**
	 * 
	 * save txt's file to sdcard
	 * 
	 * @author wuyanbao
	 */
	public void save() {

		saveDialog = new AlertDialog.Builder(this).create();
		saveDialog.setView(((Activity) this).getLayoutInflater().inflate(
				R.layout.save_dialog_name, null));
		saveDialog.show();
		saveDialog.setContentView(R.layout.save_dialog_name);
		Button ok = (Button) saveDialog.findViewById(R.id.dia_ok);
		Button ca = (Button) saveDialog.findViewById(R.id.dia_cancel);
		nameEdit = (EditText) saveDialog.findViewById(R.id.putName);
		
		ca.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveDialog.cancel();
			}
		});

		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				fileN = nameEdit.getText().toString();

				if ("".equals(fileN)) {
					Toast.makeText(TxtAddAct.this, "文件名不能为空！/(ㄒoㄒ)/", Toast.LENGTH_LONG)
							.show();
					saveDialog.cancel();
				} else {

					txts = content.getText().toString();
					// if null return to father activity
					if (null == txts || "".equals(txts)) {
						Toast.makeText(TxtAddAct.this, "您没有输入任何东西喔！/(ㄒoㄒ)/~~",
								Toast.LENGTH_LONG).show();
						finish();
					} else {
						// if no sdcard mounted
						if (Environment.getExternalStorageState().equals(
								Environment.MEDIA_UNMOUNTED)) {
							Toast.makeText(TxtAddAct.this, "请插入sdcard！o(︶︿︶)o",
									Toast.LENGTH_LONG).show();
						} else {

							String path = Environment
									.getExternalStorageDirectory().toString();
							fileN += "_ilife_xiaobao.wyb";

							if (isExit) {
								fileN = StaticVar.fileName.get(position)
										.toString();

								StaticVar.fileAll.set(position, txts);
								StaticVar.fileName.set(position, fileN);

								if (txts.length() > 10) {
									StaticVar.fileSub.set(position,
											txts.substring(0, 6) + "...");
								} else {
									StaticVar.fileSub.set(position, txts);
								}
							} else {

								StaticVar.fileAll.add(txts);
								StaticVar.fileName.add(fileN);

								if (txts.length() > 10) {
									StaticVar.fileSub.add(txts.substring(0, 6)
											+ "...");
								} else {
									StaticVar.fileSub.add(txts);
								}
							}

							File f = new File(path + File.separator + fileN);
							System.out.println("path---->" + path
									+ File.separator + fileN);

							try {
								System.out.println("---->3" + txts.getBytes());
								fos = new FileOutputStream(f);
								fos.write(txts.getBytes());
								fos.flush();
								Toast.makeText(TxtAddAct.this, "O(∩_∩)O 保存成功",
										Toast.LENGTH_LONG).show();

							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								try {
									if (fos != null) {
										fos.close();
									}
									finish();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

						}
					}

				}
			}
		});

	}

}
