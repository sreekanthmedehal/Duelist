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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * This is the activity for feature 4 in the dashboard application.
 * It displays some text and provides a way to get back to the home activity.
 *
 */

public class F8Activity extends DashboardActivity implements OnClickListener
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
    setContentView (R.layout.activity_f8);
    setTitleFromActivityLabel (R.id.title_text);
    
    
    Button regbutton = (Button)findViewById(R.id.header);
    regbutton.setOnClickListener(this);
    Button updprofbutton = (Button)findViewById(R.id.header1);
    updprofbutton.setOnClickListener(this);
    Button prokeybutton = (Button)findViewById(R.id.header2);
    prokeybutton.setOnClickListener(this);
    Button profilebutton = (Button)findViewById(R.id.header3);
    profilebutton.setOnClickListener(this);
    Button forgotregcode = (Button)findViewById(R.id.header4);
    forgotregcode.setOnClickListener(this);
    Button changetheme = (Button)findViewById(R.id.header5);
    changetheme.setOnClickListener(this);
}
public void onClick(View v) {
    
    
 	
	// TODO Auto-generated method stub
	  switch( ( v.getId())){
  
  /* case R.id.button1:
   DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	   SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
      int numRows = (int) DatabaseUtils.longForQuery(database, "SELECT COUNT(*) FROM bmpolmast", null);
   Toast.makeText(this.getBaseContext(), "No.of Records :: "+ numRows, 
	   Toast.LENGTH_SHORT).show();
   database.close();
   stockSQLHelper.close();
	   Intent intent=new Intent(this,PmActivity.class);
	   startActivity(intent); 
   break;
   case R.id.button3:
	   Intent intent1=new Intent(this,MainActivitynb.class);
       startActivity(intent1);
   break;
   case R.id.button2:
	   Intent intentd=new Intent(this,MainActivity.class);
       startActivity(intentd);
   break;
   case R.id.button4:
	   Intent intenta=new Intent(this,AlphabetListDemo.class);
	   startActivity(intenta);
   break;*/
   case R.id.header:
	   Intent intent3 = new Intent(this,RegisterActivity.class);
    	startActivity(intent3);
   break;
   case R.id.header1:
	   Intent intent4 = new Intent(this,Update_profile.class);
    	startActivity(intent4);
   break;
   case R.id.header2:
	   Intent intent5 = new Intent(this,ActivateProduct.class);
    	startActivity(intent5);
   break;
   case R.id.header3:
	   Intent intent6 = new Intent(this,Profile.class);
    	startActivity(intent6);
   break;
   case R.id.header4:
	   Intent intent7 = new Intent(this,Forgot_regcode.class);
    	startActivity(intent7);
   break;
   case R.id.header5:
	   Intent intent8 = new Intent(this,SettingsTheme.class);
    	startActivity(intent8);
   break;
}

}
} // end class
