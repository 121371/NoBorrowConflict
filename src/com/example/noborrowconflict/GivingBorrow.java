package com.example.noborrowconflict;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class GivingBorrow extends Activity {

	SQLiteDatabase db;
	String s1,s2,s3,dte,s4,choice,usr,nameoffriend;
	// String[] names;
	int x=0,x1,x2,xx;
	Cursor c1,c2;
	EditText et2,et3,et4;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.givingborrow);
	//	et1 = (EditText)findViewById(R.id.editText1);
		et2 = (EditText)findViewById(R.id.editText2);
		et3 = (EditText)findViewById(R.id.editText3);
		et4 = (EditText)findViewById(R.id.editText4);
		
		
	    
	    Bundle b1= getIntent().getExtras();
	    usr = b1.getString("usrnme");
	    choice = b1.getString("option");
		
		dte = DateFormat.getDateTimeInstance().format(new Date());
		et3.setText(dte);
		
		
		
		
		
		db = openOrCreateDatabase(usr, 0, null);	
	//	int k=5;
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
	
	
 public void submit(View v)
 {
	 
	 //s1=et1.getText().toString();
	 s1=nameoffriend;
		try
		{
		x = Integer.parseInt(et2.getText().toString());
		x1=x;
		}catch(Exception e)
		{
		}
		
		
		s2=et3.getText().toString();
		s3=et4.getText().toString();
		
		
	if(s1.equals(""))
	{
		Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE) ;
		vib.vibrate(300);
		Toast.makeText(getApplicationContext(), "Please Choose Ur friend Name ", Toast.LENGTH_LONG).show();
	}
	else if(et2.getText().toString().equals(""))
	{
		Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE) ;
		vib.vibrate(300);
		Toast.makeText(getApplicationContext(), "Please Enter The Amount ", Toast.LENGTH_LONG).show();
	}
		
	else
	{
		
		c1 = db.rawQuery(" select name from sqlite_master WHERE type='table' AND name='"+s1+"' ", null);
		
		if(c1.moveToFirst())
			
		{
			
			c1 = db.rawQuery("select due_amount from  '"+s1+"'  ", null);
		
		    
			c1.moveToFirst();
			 do{
				   x2=c1.getInt(c1.getColumnIndex("due_amount"));
				     }while(c1.moveToNext());
			if(choice.equals("Lending"))
			{
				
				xx = x2 + x;
				s4="taken";
				db.execSQL("insert into '"+s1+"' values('"+s1+"' ,"+x1+" , '"+dte+"' , '"+s3+"' , "+xx+" , 'given') ");
			}
			
			else
				
			{
				x=-x;
			
				xx = x2 + x;
				
				
				db.execSQL("insert into '"+s1+"' values('"+s1+"' ,"+x1+" , '"+dte+"' , '"+s3+"' , "+xx+" , 'taken') ");
			}
			
			
			Toast.makeText(getApplicationContext(), "before insert due amnt "+x2+"   ", Toast.LENGTH_SHORT).show();
			
			try
			{
				
				
			
			c1 = db.rawQuery("select due_amount from '"+s1+"' ORDER BY due_amount DESC LIMIT 1 ", null);
			}catch(Exception e)
			{}
			if(c1.moveToFirst())
			{x1=c1.getInt(c1.getColumnIndex("due_amount"));
			Toast.makeText(getApplicationContext(), "after insert due amnt "+xx+" ", Toast.LENGTH_SHORT).show();
		}
		//	Toast.makeText(getApplicationContext(), " "+x1+"  ", Toast.LENGTH_LONG).show();
			Toast.makeText(getApplicationContext(), "amount updated successfully", Toast.LENGTH_SHORT).show();
			
		}
		
		else
		{
			
		Toast.makeText(getApplicationContext(), "Please Choose Ur friend Name ", Toast.LENGTH_SHORT).show();
		
		}
		
	}
	
	
}
 public void reset(View v)
 {
	
	 et2.setText("");
	 et4.setText("");
 }
 
 
 public void back(View v)
 {
	 Intent  intent = new Intent(getApplicationContext(),MainActivity.class);
		intent.putExtra("usrnme",usr);
		startActivity(intent);
	 //startActivity(new Intent(getApplicationContext(),MainActivity.class));
 }
 
 
 
}
	
 
