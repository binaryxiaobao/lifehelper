package com.example.lifehelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalculatorMainAct extends Activity implements OnClickListener{

    private int flag = 0;
    private int dian = 0;
    
    private int f1 = 0;
/*    private int f2 = 0;
    private int tmp_1 = 0;
    private int tmp_2 = 0;
    private int tmp_3 = 0;*/

    private EditText editText = null;
    private Button clear = null;
    private Button next = null;
    private Button num_9 = null;
    private Button num_8 = null;
    private Button num_7 = null;
    private Button num_6 = null;
    private Button num_5 = null;
    private Button num_4 = null;
    private Button num_3 = null;
    private Button num_2 = null;
    private Button num_1 = null;
    private Button num_0 = null;
    private Button op_add = null;
    private Button op_min = null;
    private Button op_chen = null;
    private Button op_chu = null;
    private Button op_dian = null;
    private Button op_deng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.compete_main);
	//R.layout.
	editText = (EditText) findViewById(R.id.editTest);
	clear = (Button) findViewById(R.id.clear);
	next = (Button) findViewById(R.id.next);
	num_9 = (Button) findViewById(R.id.num_9);
	num_8 = (Button) findViewById(R.id.num_8);
	num_7 = (Button) findViewById(R.id.num_7);
	num_6 = (Button) findViewById(R.id.num_6);
	num_5 = (Button) findViewById(R.id.num_5);
	num_4 = (Button) findViewById(R.id.num_4);
	num_3 = (Button) findViewById(R.id.num_3);
	num_2 = (Button) findViewById(R.id.num_2);
	num_1 = (Button) findViewById(R.id.num_1);
	num_0 = (Button) findViewById(R.id.num_0);
	op_add = (Button) findViewById(R.id.num_add);
	op_min = (Button) findViewById(R.id.num_jian);
	op_chen = (Button) findViewById(R.id.num_chen);
	op_chu = (Button) findViewById(R.id.num_chu);
	op_deng = (Button) findViewById(R.id.num_deng);
	op_dian = (Button) findViewById(R.id.num_dian);

	editText.setOnClickListener(this);
	clear.setOnClickListener(this);
	next.setOnClickListener(this);
	num_9.setOnClickListener(this);
	num_8.setOnClickListener(this);
	num_7.setOnClickListener(this);
	num_6.setOnClickListener(this);
	num_5.setOnClickListener(this);
	num_4.setOnClickListener(this);
	num_3.setOnClickListener(this);
	num_2.setOnClickListener(this);
	num_1.setOnClickListener(this);
	num_0.setOnClickListener(this);
	op_add.setOnClickListener(this);
	op_min.setOnClickListener(this);
	op_chen.setOnClickListener(this);
	op_chu.setOnClickListener(this);
	op_deng.setOnClickListener(this);
	op_dian.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
	// TODO Auto-generated method stub
	super.onStart();
    }

    @Override
    protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
    }

    @Override
    protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
    }

    @Override
    protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
    }

    @Override
    protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.activity_main, menu);
	return true;
    }

    @Override
    public void onClick(View v) {

	switch(v.getId()){
	case R.id.clear:
	    dian = 0;
	    editText.setText("");
	    break;
	case R.id.next:
	    String text = editText.getText().toString();
	    if(!text.equals("")){
		text = text.substring(0, text.length()-1);
		editText.setText(text);
	    }

	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_9:
	    caculate(num_9.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_8:
	    caculate(num_8.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_7:
	    caculate(num_7.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_6:
	    caculate(num_6.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_5:
	    caculate(num_5.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_4:
	    caculate(num_4.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_3:
	    caculate(num_3.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_2:
	    caculate(num_2.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_1:
	    caculate(num_1.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_0:
	    caculate(num_0.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_add:
	    
	    if(f1 == 0){
		f1 = 1;
	    }else{
		//f2 = 1;
	    }
	    
	    caculate(op_add.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_chen:
	    
	    if(f1 == 0){
		f1 = 3;
	    }else{
		//f2 = 3;
	    }
	    
	    caculate(op_chen.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_chu:
	    caculate(op_chu.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_deng:
	    caculate(op_deng.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_dian:
	    caculate(op_dian.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	case R.id.num_jian:
	    caculate(op_min.getText().toString());
	    if(!editText.getText().equals("")){
		editText.setSelection(editText.getText().toString().length());
	    }
	    break;
	}

    }

    public void display(){
	
	Toast.makeText(CalculatorMainAct.this, "请改变计算方式，不支持负数运算！", Toast.LENGTH_SHORT).show();
	editText.setText("");
    }

    public void caculate(String num){

	String text = editText.getText().toString();
	String num1="";
	String num2="";
	double num_1;
	double num_2;

	if(editText.getText().toString().length() == 0){
	    if(num.equals(".")){
		editText.setText("0.");
	    }else if(num.equals("+")||num.equals("-")||num.equals("×")||num.equals("÷")
		    ||num.equals("=")){

		editText.setText("");
	    }else{
		editText.setText(num);
	    }
	}else {
	    if(num.equals(".")){
		if(text.indexOf("+")==-1 && text.indexOf("-")==-1 && text.indexOf("×")==-1 
			&& text.indexOf("÷")==-1 && dian==0 ){

		    if(text.indexOf(".")!=-1){
			return;
		    }else{
			dian = 1;
			text += ".";
			editText.setText(text);
		    }
		}else if(text.indexOf("+")!=text.length()-1&&text.indexOf("-")!=text.length()-1
			&&text.indexOf("×")!=text.length()-1&&text.indexOf("÷")!=text.length()-1 
			&& dian==0 &&text.indexOf("-")!=0){
		    dian = 1;
		    text += ".";
		    editText.setText(text);
		}
	    }

	    if(num.equals("+")){
		if(text.indexOf("+")==-1 && text.indexOf("-")==-1 && text.indexOf("×")==-1 
			&& text.indexOf("÷")==-1){
		    dian = 0;
		    text += "+";
		    editText.setText(text);
		}else if(text.indexOf("-")==0){
		    display();
		    return;
		}else{
		    return;
		}
	    }

	    if(num.equals("-")){
		if(text.indexOf("+")==-1 && text.indexOf("-")==-1 && text.indexOf("×")==-1 
			&& text.indexOf("÷")==-1){
		    dian = 0;
		    text += "-";
		    editText.setText(text);
		}else if(text.indexOf("-")==0){
		    display();
		    return;
		}
	    }

	    if(num.equals("×")){
		if(text.indexOf("+")==-1 && text.indexOf("-")==-1 && text.indexOf("×")==-1 
			&& text.indexOf("÷")==-1){
		    dian = 0;
		    text += "×";
		    editText.setText(text);
		}else if(text.indexOf("-")==0){
		    display();
		    return;
		}
	    }

	    if(num.equals("÷")){
		if(text.indexOf("+")==-1 && text.indexOf("-")==-1 && text.indexOf("×")==-1 
			&& text.indexOf("÷")==-1){
		    dian = 0;
		    text += "÷";
		    editText.setText(text);
		}else if(text.indexOf("-")==0){
		    display();
		    return;
		}
	    }

	    if(num.equals("=")){

		flag = 1;
		dian = 0;

		if(text.indexOf("+")==-1 && text.indexOf("-")==-1 && text.indexOf("×")==-1 
			&& text.indexOf("÷")==-1){
		    return;
		}else{
		    if(text.indexOf("+")==text.length()-1||text.indexOf("-")==text.length()-1
			    ||text.indexOf("×")==text.length()-1||text.indexOf("÷")==text.length()-1
			    ||text.indexOf("+")==0||text.indexOf("-")==0||text.indexOf("×")==0
			    ||text.indexOf("÷")==0){

			return;
		    }else{
			if(text.indexOf("+")!=-1){
			    num1 = text.substring(0, text.indexOf("+"));
			    num_1 = Double.parseDouble(num1);

			    num2 = text.substring(text.indexOf("+")+1, text.length());
			    num_2 = Double.parseDouble(num2);

			    num_1 += num_2;

			    editText.setText(num_1+"");
			}else if(text.indexOf("-")!=-1){
			    num1 = text.substring(0, text.indexOf("-"));
			    num_1 = Double.parseDouble(num1);

			    num2 = text.substring(text.indexOf("-")+1, text.length());
			    num_2 = Double.parseDouble(num2);

			    num_1 -= num_2;

			    editText.setText(num_1+"");
			}else if(text.indexOf("×")!=-1){
			    num1 = text.substring(0, text.indexOf("×"));
			    num_1 = Double.parseDouble(num1);

			    num2 = text.substring(text.indexOf("×")+1, text.length());
			    num_2 = Double.parseDouble(num2);

			    num_1 *= num_2;

			    editText.setText(num_1+"");
			}else if(text.indexOf("÷")!=-1){
			    num1 = text.substring(0, text.indexOf("÷"));
			    num_1 = Double.parseDouble(num1);

			    num2 = text.substring(text.indexOf("÷")+1, text.length());
			    num_2 = Double.parseDouble(num2);

			    num_1 /= num_2;

			    editText.setText(num_1+"");
			}
		    }
		}
	    }

	    if(num.equals("1")||num.equals("2")||num.equals("3")||num.equals("4")||num.equals("5")
		    ||num.equals("6")||num.equals("7")||num.equals("8")||num.equals("9")
		    ||num.equals("9")||num.equals("0")){

		if(flag == 1 && (text.indexOf("+")==-1&&text.indexOf("-")==-1)&&text.indexOf("×")==-1
			&&text.indexOf("÷")==-1){
		    text = "";
		    flag = 0;
		}
		text += num;
		editText.setText(text);
	    }
	}

    }
}

