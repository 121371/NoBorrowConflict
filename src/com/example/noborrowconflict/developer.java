package com.example.noborrowconflict;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class developer extends Activity {

	ImageView imgv;
	ScrollView sv1;
	TextView tv;
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.developer);
		
		sv1 = (ScrollView)findViewById(R.id.sv);
		imgv=(ImageView)findViewById(R.id.image);
		tv= (TextView)findViewById(R.id.textView1);
		
		tv.append("\nAMIT PATHAK    \n\nEMAIL: amit.pathak321@gmail.com\n\nContact No:+91-8965084777\n\nB.TECH : CSE\n\nJAYPEE UNIVERSITY OF ENGINEERING AND TECHNOLOGY \n\nDEVELOPMENT START DATE : JUNE 2015");
		
	//	imgv.setImageResource(R.drawable.amitpathak);
		
	
	}
	
	public void back(View v)
	
	{
		
		startActivity(new Intent(getApplicationContext(),Front_database.class));
	}
	


}
