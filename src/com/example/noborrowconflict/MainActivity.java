package com.example.noborrowconflict;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends Activity {

	RadioGroup rg;
	RadioButton rb;
	Button b;
	String usr;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		                                       
		
		 Bundle b1= getIntent().getExtras();
    	  usr = b1.getString("usrnme");
		
	
		 
		b=(Button)findViewById(R.id.button1);
		rg=(RadioGroup)findViewById(R.id.radiogroup);
		b.setOnClickListener(new View.OnClickListener(){
		
		
			public void onClick(View arg0) {
				
			if(rg.getCheckedRadioButtonId()!=-1)       
			{
				
				int selectedId = rg.getCheckedRadioButtonId();
				rb=(RadioButton)findViewById(selectedId);
			     if(rb.getText().toString().equals("Check Status"))
				{     
			    	
			    	Intent  intent = new Intent(getApplicationContext(),Status.class);
						intent.putExtra("usrnme",usr);
						startActivity(intent);
			    	// startActivity(new Intent(getApplicationContext(),Status.class));
					finish();
				}
				
				else if(rb.getText().toString().equals("Add Friend"))
				{
					
					
					Intent  intent = new Intent(getApplicationContext(),AddFriend.class);
					intent.putExtra("usrnme",usr);
					startActivity(intent);
					
					//startActivity(new Intent(getApplicationContext(),AddFriend.class));
					finish();
				}
			     
				else if(rb.getText().toString().equals("Delete Ur Friend"))
				{
					Intent  intent = new Intent(getApplicationContext(),delete.class);
					intent.putExtra("usrnme",usr);
					startActivity(intent);
				//	startActivity(new Intent(getApplicationContext(),delete.class));
					
				}
			     
				else if(rb.getText().toString().equals("Clear History"))
				{
					Intent  intent = new Intent(getApplicationContext(),clearhistory.class);
					intent.putExtra("usrnme",usr);
					startActivity(intent);
				//	startActivity(new Intent(getApplicationContext(),clearhistory.class));
					
				}
			     
				else if(rb.getText().toString().equals("NetMoney"))
				{
					
					Intent  intent = new Intent(getApplicationContext(),netmoney.class);
					intent.putExtra("usrnme",usr);
					startActivity(intent);
					
				//	startActivity(new Intent(getApplicationContext(),netmoney.class));
					
				}
					
					
			     
				else
				{
				
					
				
		
					Intent i = new Intent(getApplicationContext(),GivingBorrow.class);
					i.putExtra("option", rb.getText().toString());
					i.putExtra("usrnme",usr);
					startActivity(i);	
					finish();
				}
				
				Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
			}
			else
			{
				Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE) ;
				vib.vibrate(300);
					Toast.makeText(getApplicationContext(), "Please Select an Option", Toast.LENGTH_SHORT).show();
			}
				
				
				
			}
		});

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
