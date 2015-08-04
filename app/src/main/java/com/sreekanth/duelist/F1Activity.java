/*
 * Copyright (C) 2011 Wglxy.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sreekanth.duelist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * This is the activity for feature 1 in the dashboard application.
 * It displays some text and provides a way to get back to the home activity.
 *
 */

public class F1Activity extends DashboardActivity implements OnClickListener
{

/**
 * onCreate
 *
 * Called when the activity is first created. 
 * This is where you should do all of your normal static set up: create views, bind data to lists, etc. 
 * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
 * 
 * Always followed by onStart().
 *
 * @param savedInstanceState Bundle
 */

protected void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView (R.layout.activity_f1);
    setTitleFromActivityLabel (R.id.title_text);
   /* Button  navbtn = (Button) findViewById(R.id.button6);
    navbtn.setOnClickListener(this);*/
    Button enqbtn = (Button)findViewById(R.id.button1);
    enqbtn.setOnClickListener(this);
    Button surbtn = (Button)findViewById(R.id.button2);
    surbtn.setOnClickListener(this);
    Button revbtn = (Button)findViewById(R.id.button3);
    revbtn.setOnClickListener(this);
}
public void onClick(View v) {
	// TODO Auto-generated method stub
	  switch( ( v.getId())){
  /* case R.id.button6:
	   Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.licindia.in/plannav/newplannav.htm"));
  	startActivity(browserIntent);
	     break;*/
   case R.id.button1:
       Intent enqintent = new Intent(this,FrmActivity.class);
       startActivity(enqintent);
   break;
   case R.id.button2:
		 AlertDialog.Builder alert = new AlertDialog.Builder(F1Activity.this); 
		  alert.setTitle("Surrender Quotation");

		 
		  

		  alert.setMessage("Coming Soon");
		  alert.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
		      @Override
		      public void onClick(DialogInterface dialog, int id) {
		      dialog.dismiss();
		      }
		  });
		  alert.show();
   break;
   case R.id.button3:
	   Intent revintent = new Intent(this,RevActivity.class);
	   startActivity(revintent);
   break;
}
}
} // end class
