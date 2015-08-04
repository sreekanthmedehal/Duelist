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

public class F7Activity extends DashboardActivity implements OnClickListener
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
    setContentView (R.layout.activity_f7);
//    setTitleFromActivityLabel (R.id.title_text);
   
 
Button acalcbutton = (Button)findViewById(R.id.header);
acalcbutton.setOnClickListener(this);

Button medcbutton = (Button)findViewById(R.id.header4);
medcbutton.setOnClickListener(this);
Button paypbutton = (Button)findViewById(R.id.header5);
paypbutton.setOnClickListener(this);
Button clubcbutton = (Button)findViewById(R.id.header6);
clubcbutton.setOnClickListener(this);

}
public void onClick(View v) {
    
    
 	
	// TODO Auto-generated method stub
	  switch( ( v.getId())){
   case R.id.header:
	   Intent intenta=new Intent(this,Agecalc.class);
	   startActivity(intenta);   
	   break;
   case R.id.header4:
	   Intent intentm=new Intent(this,medchart.class);
	   startActivity(intentm);
   break;  
   case R.id.header5:
	   Intent intentp=new Intent(this,Paypremium.class);
	   startActivity(intentp);
   break; 
   case R.id.header6:
	   Intent intentc=new Intent(this,clubchart.class);
	   startActivity(intentc);
   break;   
	   
  
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
   default:
	   break;
  
}

} 
} // end class
