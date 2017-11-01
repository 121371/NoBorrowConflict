package com.example.noborrowconflict;

import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddFriend extends Activity {

  SQLiteDatabase db;
  String s,dte,s2,dbname,usr;
  EditText et;
  TextView tv;
  Cursor c1;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addfriend);
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
		et = (EditText)findViewById(R.id.editText1);
		tv= (TextView)findViewById(R.id.textView1);
		
		 Bundle b1= getIntent().getExtras();
		    usr = b1.getString("usrnme");
		
		dte = DateFormat.getDateTimeInstance().format(new Date());
	//	Bundle b1 = getIntent().getExtras();
		//dbname = b1.getString("usrnme");
		
		db = openOrCreateDatabase(usr , 0 ,null);
		
		
		
		
	}
	
	
  public void addfriend(View v)
	{
		
	  s =et.getText().toString();
	  
  if(s.equals(""))
  {
	 Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
	 vib.vibrate(300);
	Toast.makeText(getApplicationContext(), "Please Enter Ur Friend Name", Toast.LENGTH_LONG).show();
  }
  else
  {
	  
	  c1 = db.rawQuery("select name from sqlite_master WHERE type='table' AND name='"+s+"' ", null);
	  if(c1.moveToFirst())
		{
			//do
			//{
		
				//if(c1.getString(0).equals("+s+") )
				//{
		 // tv.setText("hello exists");
		  Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE) ;
			vib.vibrate(300);
		  Toast.makeText(getApplicationContext()," '"+s+"' Already exists in ur List",Toast.LENGTH_SHORT).show();
				//	break;
			    //}
				
			//}while(c1.moveToNext());
		 }
		else
		{
			 
		db.execSQL("create table if not exists  '"+s+"'(name varchar(100), amount integer(100) , date varchar(100) , remark varchar(100) , due_amount integer(100) , mode varchar(50) ) ");
		
		db.execSQL("insert into '"+s+"' values('"+s+"' , 0 ,'"+dte+"','No dues' , 0 , 'Account_Created')");
		Toast.makeText(getApplicationContext()," '"+s+"' Added in ur list ", Toast.LENGTH_LONG).show();
		
		}
  }
}
  
  public void back(View v)
  {
	  
	  Intent  intent = new Intent(getApplicationContext(),MainActivity.class);
		intent.putExtra("usrnme",usr);
		startActivity(intent);
	//  startActivity(new Intent(getApplicationContext(),MainActivity.class));
  }
	
	
}
