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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Status extends Activity {

	
	SQLiteDatabase db;
	Cursor c1;
	String s1,usr,nameoffriend;
	ScrollView Sv;
	TextView tv;
	int x;
	//EditText et;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);
		Sv=(ScrollView)findViewById(R.id.sv);
	//	et = (EditText)findViewById(R.id.editText1);
		tv = (TextView)findViewById(R.id.textView1);
		
		
		 Bundle b1= getIntent().getExtras();
		  usr = b1.getString("usrnme");
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
			
			Toast.makeText(getApplicationContext(), "table doesn't exist", Toast.LENGTH_SHORT).show();
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
	
	
	
	
	
public void Every_Transaction_Status(View v)
	
{
	//	int x;
		//String ss;
		
		s1 = nameoffriend;
  if(s1.equals(""))
  {
	Toast.makeText(getApplicationContext(), "Please Enter Ur Friend Name", Toast.LENGTH_SHORT).show();
  }
  else
  {
		c1 =db.rawQuery(" select name from sqlite_master WHERE type='table' AND name='"+s1+"'  ", null);
		
		
		if(c1.moveToFirst())
		{
			
			c1 =  db.rawQuery(" select * from '"+s1+"' ", null);
			
	          c1.moveToFirst();
	          tv.setText("");
				do
				{
					
					if(c1.getString(c1.getColumnIndex("mode")).equals("given"))
					{
					tv.append("\nname : "+c1.getString(c1.getColumnIndex("name"))+"\nAmount Given : "+c1.getString(c1.getColumnIndex("amount"))+"\nDate : "+c1.getString(c1.getColumnIndex("date"))+"\nRemark : "+c1.getString(c1.getColumnIndex("remark"))+"\nDue Amount : "+c1.getString(c1.getColumnIndex("due_amount"))+"\n");
					}
					
					else if(c1.getString(c1.getColumnIndex("mode")).equals("taken"))
					{
						tv.append("\nname : "+c1.getString(c1.getColumnIndex("name"))+"\nAmount Taken : "+c1.getString(c1.getColumnIndex("amount"))+"\nDate : "+c1.getString(c1.getColumnIndex("date"))+"\nRemark : "+c1.getString(c1.getColumnIndex("remark"))+"\nDue Amount : "+c1.getString(c1.getColumnIndex("due_amount"))+"\n");
					}
					
					else
					{
						
						tv.append("\nname : "+c1.getString(c1.getColumnIndex("name"))+"\nAmount  : "+c1.getString(c1.getColumnIndex("amount"))+"\nDate : "+c1.getString(c1.getColumnIndex("date"))+"\nRemark : "+c1.getString(c1.getColumnIndex("remark"))+"\nDue Amount : "+c1.getString(c1.getColumnIndex("due_amount"))+"\n");
					}
					
						
				}while(c1.moveToNext());
		}
		
		else 
		{
          
			Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE) ;
			vib.vibrate(300);	
			Toast.makeText(getApplicationContext(), "Please choose Ur friend name", Toast.LENGTH_SHORT).show();
		}
		
    }
}
	
	
 public void Final_Status(View v)
 {
		long x1;
		String ss;
	
		s1 = nameoffriend;
		
	if(s1.equals(""))
	{
		Toast.makeText(getApplicationContext(), "Please Enter Ur Friend Name", Toast.LENGTH_SHORT).show();
	}
    else
	{	
		c1 =db.rawQuery("select name from sqlite_master WHERE type='table' AND name='"+s1+"'  ", null);
	 
	  
		if(c1.moveToFirst())
		{
			c1 = db.rawQuery("select due_amount from  '"+s1+"'  ", null);
		
		     c1.moveToFirst();
		     do{
		   x=c1.getInt(c1.getColumnIndex("due_amount"));
		     }while(c1.moveToNext());
		   Toast.makeText(getApplicationContext(), " "+x+" ", Toast.LENGTH_SHORT).show();
		   tv.setText("");
			  if( x > 0)
			     {
				 
				  ss = String.valueOf(x);
			      tv.append("You have to take "+ss+" Rupees from "+s1+"\n");
		         }
		
		
	             else if( x < 0 )
		        {
	            	 x=x*-1;
			     ss = String.valueOf(x);
			     tv.append("You have to Give "+ss+" Rupees To "+s1+"\n");
		        }
		
		        else
			
		        {
			   
			    tv.setText(" Your Account with  "+s1+" Is Balanced \n");
			
		        }
		        
		}
		
		else 
		{
          
			Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE) ;
			vib.vibrate(300);
					
			Toast.makeText(getApplicationContext(), "Please choose Ur friend name ", Toast.LENGTH_SHORT).show();
		}
		
	  }
   
 }
 
 public void back(View v)
 {
	
	Intent  intent = new Intent(getApplicationContext(),MainActivity.class);
		intent.putExtra("usrnme",usr);
		startActivity(intent);
	//startActivity(new Intent(getApplicationContext(),MainActivity.class)); 
 }
}
	  
