package com.example.noborrowconflict;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Front_database extends Activity{

	SQLiteDatabase db;
	Button signin,signup;
	
	Cursor c1;
	EditText usr_name,password;
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.front);
		signin=(Button)findViewById(R.id.button1);
		
	    
		signup=(Button)findViewById(R.id.button2);
		usr_name=(EditText)findViewById(R.id.editText1);
		password=(EditText)findViewById(R.id.editText2);
	
		try{
			
			db=openOrCreateDatabase("SignUP", 0, null);
			db.execSQL("create table if not exists pathak3(Full_name varchar,Email varchar,Address varchar,gender varchar,Occupation varchar,Password varchar(100))");
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		signin.setOnClickListener(new View.OnClickListener() {		
			public void onClick(View arg0) {
				String usr = usr_name.getText().toString();
				String psw = password.getText().toString();
				c1 = db.rawQuery("select Password from pathak3 where Email='"+usr+"' ",null);
				int x=0;
				do
				{
					
					if(c1.moveToFirst())
					{
				      String psw1=c1.getString(c1.getColumnIndex("Password"));
				   
				      if(psw.equals(psw1))
				      {
					  x=1;
					  break;
				      }
				    }
					else
					{
						 Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
						    vib.vibrate(300);
						Toast.makeText(getApplicationContext(), "Please Check ur Email Or Password",Toast.LENGTH_LONG).show();		
					}
				}while(c1.moveToNext());
			
				if(x==1)
				{
					Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
					i1.putExtra("usrnme", usr);
					startActivity(i1);
				//startActivity(new Intent(getApplicationContext(),MainActivity.class));
				finish();
				}
				else
				{
					 Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
					    vib.vibrate(300);
					Toast.makeText(getApplicationContext(), "Please Check ur Email Or Password",Toast.LENGTH_LONG).show();	
				}
				
				
				
			}
		});
		
signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(),signup.class));
				finish();
				
			}
		});


  
		
	}
	
	public void developerpanel(View v)
	  {
		  
		startActivity(new Intent(getApplicationContext(),developer.class));  
	  }
	
	
	

}
