package com.example.noborrowconflict;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class netmoney extends Activity {

	SQLiteDatabase db;
	Cursor c1;
	String usr;
	TextView  tv;
	String[] str = new String[100];
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.netmoney);
		tv = (TextView)findViewById(R.id.tv1);
		 Bundle b1= getIntent().getExtras();
		    usr = b1.getString("usrnme");
		db = openOrCreateDatabase(usr, 0, null);
		
		
		
	}
	
	
	
	public static void appendColoredText(TextView tv,String text,int color)
	
	{
		
		int start = tv.getText().length();
		tv.append(text);
		int end = tv.getText().length();
		Spannable s = (Spannable)tv.getText();
		s.setSpan(new ForegroundColorSpan(color), start, end, 0);
	}
	
	public void final_status(View v)
	
	{
		tv.setText("");
		int i=0;
		c1 = db.rawQuery(" select name from sqlite_master WHERE type='table' ", null);
		if(c1.moveToFirst())
		{
			do
			{
				
				str[i] = c1.getString(c1.getColumnIndex("name"));
				i++;
			}while(c1.moveToNext());
			
			str[i]="";
		}
		
		else
			
		{
			
			Toast.makeText(getApplicationContext(), "table doesn't exist", Toast.LENGTH_LONG).show();
		}
		
		
	
		int x;
		appendColoredText(tv, "NAME OF FRIEND          DUE AMOUNT\n", Color.RED);
		for(int j=0;str[j]!="";j++)
		{
			
			if(str[j].equals("android_metadata"))
				continue;
			c1 = db.rawQuery(" select due_amount from "+str[j]+"  ", null);
			
			if(c1.moveToFirst())
				
			{
				
				do{
					
					x = c1.getInt(c1.getColumnIndex("due_amount"));
					
				}while(c1.moveToNext());
				
				tv.append("\n"+str[j]+"  \t\t\t\t\t\t\t\t\t\t\t\t\t      "+x+"\n");
			}
			
			
			
		}
		
	}
	
	
	public void netmoney(View v)
	
	{
		tv.setText("");
		int i=0,j;
		c1 = db.rawQuery(" select name from sqlite_master WHERE type='table' ", null);
		if(c1.moveToFirst())
		{
			do
			{
				
				str[i] = c1.getString(c1.getColumnIndex("name"));
				i++;
			}while(c1.moveToNext());
			
			str[i]="";
		}
		
		else
			
		{
			
			Toast.makeText(getApplicationContext(), "table doesn't exist", Toast.LENGTH_LONG).show();
		}
		
		int x,x1=0;
		for(j=0;str[j]!="";j++)
		{
			
			if(str[j].equals("android_metadata"))
				continue;
			c1 = db.rawQuery(" select due_amount from "+str[j]+"  ", null);
			
			if(c1.moveToFirst())
				
			{
				
				do{
					
					x = c1.getInt(c1.getColumnIndex("due_amount"));
					
				}while(c1.moveToNext());
				
				x1 = x1+x;
				
			}
			
			
			
		}
		j--;
		if(x1>0)
		{
			appendColoredText(tv, "You have to take total : "+x1+"  Rupees After Including All UR Friends\n", Color.RED);
		}
		else if(x1<0)
		{
			x1=-x1;
			appendColoredText(tv, "You have to Give total : "+x1+" Rupees After Including All UR Friends\n", Color.RED);
		}
		else
		{
			if(j==0)
			{
			appendColoredText(tv, "Sorry!!!  Still you haven't  add any Friend\n", Color.RED);
			}
			else if(j==1)
			{
			appendColoredText(tv, "Wow!!!  Your Account is Balanced with UR single Friend "+str[1]+"\n", Color.RED);
			}
			else
			{
			appendColoredText(tv, "Wow!!!  Your Account is Balanced  After Including All UR "+j+" Friends\n", Color.RED);
			}
		}
		
	}
	
	public void back(View v)
	{
	
		 Intent  intent = new Intent(getApplicationContext(),MainActivity.class); 
			intent.putExtra("usrnme",usr);
			startActivity(intent);
	//	startActivity(new Intent(getApplicationContext(),MainActivity.class));
		
	}
	
	
  
	
	
}
