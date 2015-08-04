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
import android.widget.Button;

/**
 * This is the activity for feature 4 in the dashboard application.
 * It displays some text and provides a way to get back to the home activity.
 *
 */

public class F10Activity extends DashboardActivity implements View.OnClickListener
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
    setContentView (R.layout.activity_f10);
    setTitleFromActivityLabel (R.id.title_text);
    Button  clubbtn = (Button) findViewById(R.id.home_club);
    clubbtn.setOnClickListener(this);
    Button  pdfbtn = (Button) findViewById(R.id.home_reports);
    pdfbtn.setOnClickListener(this);
    Button agdetails = (Button)findViewById(R.id.home_agdetails);
    agdetails.setOnClickListener(this);
    Button agclub = (Button)findViewById(R.id.home_agclub);
    agclub.setOnClickListener(this);
}
public void onClick(View v) {
	switch (v.getId()) {
		case R.id.home_club:
			Intent clubintent = new Intent(F10Activity.this,F9Activity.class);
			startActivity(clubintent);
		break;
		case R.id.home_reports:	
	Intent pdfintent = new Intent(F10Activity.this,FirstPdf.class);
		//	Intent pdfintent = new Intent(F10Activity.this,ParsePDF.class);
			startActivity(pdfintent);
		break;
		case R.id.home_agdetails:	
			Intent agdintent = new Intent(F10Activity.this,AgFrmActivity.class);
				//	Intent pdfintent = new Intent(F10Activity.this,ParsePDF.class);
					startActivity(agdintent);
				break;
		case R.id.home_agclub:	
			Intent agcintent = new Intent(F10Activity.this,GridViewActivity1.class);
				//	Intent pdfintent = new Intent(F10Activity.this,ParsePDF.class);
					startActivity(agcintent);
				break;
	}
}
} // end class
