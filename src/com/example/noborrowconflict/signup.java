package com.example.noborrowconflict;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends Activity {

	SQLiteDatabase db;
	String name,mail,adrs,gndr,ocpsn,pass;
	TextView tv,tv1,tv2,tv3,tv4,tv5,tv6;
	Cursor c1;
	Button b1,b2;
	EditText Full_Name,Email,Address,Gender,Occupation,password;
	protected void onCreate(Bundle savedInstanceState)   {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
	    Full_Name=(EditText)findViewById(R.id.editText1);
	    tv1=(TextView)findViewById(R.id.TV1);
	    tv2=(TextView)findViewById(R.id.TV2);
	    tv3=(TextView)findViewById(R.id.TV3);
	    tv4=(TextView)findViewById(R.id.TV4);
	    tv5=(TextView)findViewById(R.id.TV5);
	    tv6=(TextView)findViewById(R.id.TV6);
	    tv=(TextView)findViewById(R.id.textView1);
		Email=(EditText)findViewById(R.id.editText2);
		password=(EditText)findViewById(R.id.editText6);
		Address=(EditText)findViewById(R.id.editText3);
		Gender=(EditText)findViewById(R.id.editText4);
		Occupation=(EditText)findViewById(R.id.editText5);
		
		try
		{
		try{
		
		db=openOrCreateDatabase("SignUP", 0, null);
		db.execSQL("create table if not exists pathak3(Full_name varchar,Email varchar,Address varchar,gender varchar,Occupation varchar,Password varchar(100))");
		}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
		
		
	
		b1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0)
			{
			name=Full_Name.getText().toString();
			mail=Email.getText().toString();
			adrs=Address.getText().toString();
			gndr=Gender.getText().toString();
			ocpsn=Occupation.getText().toString();
			pass=password.getText().toString();
			//tv.setText("name = : "+name+"\n");	
			
			
			if(name.equals(""))
			{
				 Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
				    vib.vibrate(300);
			Toast.makeText(getApplicationContext(),"Please!! insert Ur Name",Toast.LENGTH_SHORT).show();	
			}
			
			
			else if(mail.equals(""))
			{
			    Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
			    vib.vibrate(300);
				Toast.makeText(getApplicationContext(),"Please!! insert Ur Email",Toast.LENGTH_SHORT).show();	
			}	
			
			
			else if(pass.equals(""))
			{
				 Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
				    vib.vibrate(300);
				Toast.makeText(getApplicationContext(),"Please!! insert Ur Password",Toast.LENGTH_SHORT).show();	
			}	
			
			
			
			else
			{
				
				
			 String s;
			 c1=db.rawQuery("select Email from pathak3 where Email='"+mail+"' ", null);//checking Existing user 
			 
			 
		
			if(c1.moveToFirst())
		    {
			 //do
			 //{
				 s=c1.getString(c1.getColumnIndex("Email"));
				// tv.append("1: "+s+" ");
			if(s.equals(mail))
			{
				 Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
				    vib.vibrate(300);
				Toast.makeText(getApplicationContext(),"Entered Email Id is already Registered,Try with different Email Id",Toast.LENGTH_LONG).show();
				
			}
		}
		else
		{
			
		
			db.execSQL("insert into pathak3 values('"+name+"','"+mail+"','"+adrs+"','"+gndr+"','"+ocpsn+"','"+pass+"') ");
			Full_Name.setText("");
			Email.setText("");
			password.setText("");
			Address.setText("");
			Gender.setText("");
			Occupation.setText("");
			password.setText("");
			Toast.makeText(getApplicationContext(),"values inserted successfully",Toast.LENGTH_LONG).show();
			//break;
		}
		
			 
			}
			 }
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(),Front_database.class));
				
			}
		});
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
}
