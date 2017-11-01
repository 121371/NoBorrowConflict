package com.example.noborrowconflict;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class clearhistory extends Activity {

	SQLiteDatabase db;
	Cursor c1;
	//EditText et;
	String s,usr,nameoffriend;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clearhistory);
		
		  Bundle b1= getIntent().getExtras();
		    usr = b1.getString("usrnme");
	//	et = (EditText)findViewById(R.id.editText1);
		
		db = openOrCreateDatabase(usr, 0, null);
		
		
		final Spinner sp = (Spinner)findViewById(R.id.editText1);
		final String[] names1 = new String[200];
	   String[] names=null;
		
		
		int i=0;
		c1 = db.rawQuery(" select name from sqlite_master WHERE type='table' ", null);
		if(c1.moveToFirst())
		{
			
			do
			{
				names1[i]=c1.getString(c1.getColumnIndex("name"));
				
				if(! (names1[i].equals("android_metadata") ) )
				{
				//names1[i] = c1.getString(c1.getColumnIndex("name"));
				i++;
				}
			}while(c1.moveToNext());
			
			
			if(i>0)
			{
				 names = new String[i+1];
				 names[0]="Choose Ur Friend Name";
			  for(int j=1;j<=i;j++)
			   {
				
				names[j]=names1[j-1];
			   }
			}
			else
			{
				names = new String[1];
				names[0]="Please Add Ur Friend First";
			}
			
		}
		
		else
			
		{
			
			Toast.makeText(getApplicationContext(), "table doesn't exist", Toast.LENGTH_LONG).show();
		}
		
		//String[] names = { "monday" , "tuesday" , "wednesday" , "thursday" , "friday" ,"saturday" , "sunday" ,"a","b","c","d","e","f","g","h","i" };
		
		sp.setPrompt("Choose Ur Name");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>
		(getApplicationContext(),
				android.R.layout.simple_spinner_dropdown_item, names);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(new  OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				
				nameoffriend = sp.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(), nameoffriend, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
			
			
			
		});
		
		
	}
	
	
	
	public void clear(View v)
	
	{
		String nme,dte,rmrk,mde;
		int amnt,due_amnt;
		s=nameoffriend;
		
		c1 = db.rawQuery(" select name from sqlite_master WHERE type='table'  AND name='"+s+"'  ", null);
		
		if(c1.moveToFirst())
			
		{
			
			c1 = db.rawQuery(" select * from "+s+"  ", null);    //clearing history
			
			if(c1.moveToFirst())
			{
			
			    do
			     {
			       nme =       c1.getString(c1.getColumnIndex("name"));
			       amnt =      c1.getInt(c1.getColumnIndex("amount"));
			       dte =       c1.getString(c1.getColumnIndex("date"));
			       rmrk =      c1.getString(c1.getColumnIndex("remark"));
			       due_amnt =  c1.getInt(c1.getColumnIndex("due_amount"));
			       mde =       c1.getString(c1.getColumnIndex("mode"));
			
				
			     }while(c1.moveToNext());
			    
			    
			    db.execSQL(" delete  from "+s+" WHERE name='"+s+"'  ");
			    
			    db.execSQL(" insert into "+s+" values('"+nme+"',"+amnt+",'"+dte+"','"+rmrk+"',"+due_amnt+",'"+mde+"') ");
			    
			    Toast.makeText(getApplicationContext(), " History is Cleared Successfully!! ", Toast.LENGTH_LONG).show();
		     }
			
			else
				
			{
				
				Toast.makeText(getApplicationContext(), " No data is existing ", Toast.LENGTH_LONG).show();
				
			}
	
		
		
	  }
		
		else
			
		{
			Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
			vib.vibrate(300);
			Toast.makeText(getApplicationContext(), " Please Choose Ur Friend name ", Toast.LENGTH_SHORT).show();
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
