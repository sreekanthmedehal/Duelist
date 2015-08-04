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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Days;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
/**
 * This is the activity for feature 2 in the dashboard application.
 * It displays some text and provides a way to get back to the home activity.
 *
 */

public class F2Activity extends DashboardActivity implements OnClickListener 
{
	String fyyear;
	Date fdate;  
	Date tdate;
	Date  sdate;
	 Button pmrfrbutton;
	 Button duerfrbutton;
	 Button agrfrbutton;
	 AnimationDrawable d;
	 AnimationDrawable d1;
	 AnimationDrawable d2;
	 int   rowsUpdated = 0 ;
	 Button dueslbutton;
	 Spinner duesspinner; 
	 String datetime;
	 String datetimedd;
	 Date date;
	 String FUP1;
	 String datetimetoday;
	 DateTime FUP1cdate;
	 int mode_days; int no_of_inst;
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
    setContentView (R.layout.activity_f2);
    setTitleFromActivityLabel (R.id.title_text);
    Button Agbutton = (Button)findViewById(R.id.button6);
    Agbutton.setOnClickListener(this);
 //   Button nbbutton = (Button)findViewById(R.id.button10);
 //   nbbutton.setOnClickListener(this);
    pmrfrbutton =(Button)findViewById(R.id.button12);
    pmrfrbutton.setOnClickListener(this);
    duerfrbutton =(Button)findViewById(R.id.button13);
    duerfrbutton.setOnClickListener(this);
    agrfrbutton = (Button)findViewById(R.id.button11);
    agrfrbutton.setOnClickListener(this);
  
     d=(AnimationDrawable)pmrfrbutton.getCompoundDrawables()[0];
     d1=(AnimationDrawable)duerfrbutton.getCompoundDrawables()[0];
     d2=(AnimationDrawable)agrfrbutton.getCompoundDrawables()[0];
    Button pmbutton = (Button)findViewById(R.id.button1);
    pmbutton.setOnClickListener(this);
    Button duesbtn = (Button)findViewById(R.id.button2);
    duesbtn.setOnClickListener(this);
    Button alphabtn = (Button)findViewById(R.id.button4);
    alphabtn.setOnClickListener(this);
    Button glrybtn = (Button)findViewById(R.id.button5);
    glrybtn.setOnClickListener(this);
    final Spinner fybtn = (Spinner)findViewById(R.id.spinner1);
    List<String> list = new ArrayList<String>();
    list.add("Plan Wise");
    
    
   	list.add("1994-1995");
   	list.add("1995-1996");
   	list.add("1996-1997");
   	list.add("1997-1998");
    
    
    
    list.add("1998-1999");
   	list.add("1999-2000");
   	list.add("2000-2001");
   	list.add("2001-2002");
   	list.add("2002-2003");
    
    
    
    
    list.add("2003-2004");
	list.add("2004-2005");
	list.add("2005-2006");
	list.add("2006-2007");
	list.add("2007-2008");
	list.add("2008-2009");
	
    
    
    
    
    
	list.add("2009-2010");
	list.add("2010-2011");
	list.add("2011-2012");
	list.add("2012-2013");
	list.add("2013-2014");
	list.add("2014-2015");
	
	
	
	
	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(F2Activity.this,android.R.layout.simple_spinner_item, list);
	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	fybtn.setAdapter(dataAdapter);
	fybtn.setOnItemSelectedListener(new OnItemSelectedListener() {
	    @Override
	    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	    //	 ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
	         ((TextView) parentView.getChildAt(0)).setTextSize(25);
	         ((TextView) parentView.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL);
	    }

	    @Override
	    public void onNothingSelected(AdapterView<?> parentView) {
	        // your code here
	    }

	});
	//---------------------------------------------------
	Button btnSubmit = (Button) findViewById(R.id.button9); 
	btnSubmit.setOnClickListener(new OnClickListener() {
	  @Override
	  public void onClick(View v) {
	if (String.valueOf(fybtn.getSelectedItem()).equalsIgnoreCase("Plan Wise")) {
		AlertDialog.Builder builder1 = new AlertDialog.Builder(F2Activity.this);
        builder1.setMessage("Select Fin Year..");
        builder1.setCancelable(true);
      
        builder1.setNegativeButton("Continue......",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert11 = builder1.create();
        alert11.show();
	} else{
	    Intent theintent = new Intent(F2Activity.this,ActivityExpList.class);
	    theintent.putExtra("ftyear",String.valueOf(fybtn.getSelectedItem()));
	    startActivity(theintent);
	  }
	  }
	});
	
	
	
	//-------------------------------------------------

	
	
	
	 duesspinner = (Spinner)findViewById(R.id.spinner3);
	    List<String> list1 = new ArrayList<String>();
	    list1.add("Month Wise");
	    
	    
	   	list1.add("Jan");
	   	list1.add("Feb");
	   	list1.add("Mar");
	   	list1.add("Apr");
	    list1.add("May");
	   	list1.add("Jun");
	   	list1.add("Jul");
	   	list1.add("Aug");
	   	list1.add("Sep");
	    list1.add("Oct");
		list1.add("Nov");
		list1.add("Dece");
		
		
		
		
		
		ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(F2Activity.this,android.R.layout.simple_spinner_item, list1);
		dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		duesspinner.setAdapter(dataAdapter3);
		duesspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	// ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
		         ((TextView) parentView.getChildAt(0)).setTextSize(25);
		         ((TextView) parentView.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL);
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
	
	
	
	
	
	
	
		  dueslbutton =(Button)findViewById(R.id.button14);
		  dueslbutton.setOnClickListener(new OnClickListener() {

	   @Override
		  public void onClick(View v) {
		if (String.valueOf(duesspinner.getSelectedItem()).equalsIgnoreCase("Month Wise")) {
			AlertDialog.Builder builder1 = new AlertDialog.Builder(F2Activity.this);
	        builder1.setMessage("Select Month..");
	        builder1.setCancelable(true);
	      
	        builder1.setNegativeButton("Continue......",
	                new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	            }
	        });

	        AlertDialog alert11 = builder1.create();
	        alert11.show();
		} else{
			   Intent duesslintent = new Intent(F2Activity.this,BmExpList.class);
			   duesslintent.putExtra("ftyear",String.valueOf(duesspinner.getSelectedItem()));
			   startActivity(duesslintent);
		  }
		  }
		});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//-------------------------------------------------

final Spinner nbbtn = (Spinner)findViewById(R.id.spinner2);
List<String> listnb = new ArrayList<String>();
listnb.add("NB - Fin Year Wise");


	listnb.add("1994-1995");
	listnb.add("1995-1996");
	listnb.add("1996-1997");
	listnb.add("1997-1998");



listnb.add("1998-1999");
	listnb.add("1999-2000");
	listnb.add("2000-2001");
	listnb.add("2001-2002");
	listnb.add("2002-2003");




listnb.add("2003-2004");
listnb.add("2004-2005");
listnb.add("2005-2006");
listnb.add("2006-2007");
listnb.add("2008-2009");






listnb.add("2009-2010");
listnb.add("2010-2011");
listnb.add("2011-2012");
listnb.add("2012-2013");
listnb.add("2013-2014");
listnb.add("2014-2015");




ArrayAdapter<String> dataAdapternb = new ArrayAdapter<String>(F2Activity.this,
	android.R.layout.simple_spinner_item, listnb);
dataAdapternb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
nbbtn.setAdapter(dataAdapternb);
nbbtn.setOnItemSelectedListener(new OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
    	// ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
         ((TextView) parentView.getChildAt(0)).setTextSize(25);
         ((TextView) parentView.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL);
         
         if (!String.valueOf(nbbtn.getSelectedItem()).equalsIgnoreCase("NB - Fin Year Wise")) {     
         
         fyyear = String.valueOf(nbbtn.getSelectedItem());
         String [] items = fyyear.split("-");
         String fs = items[0]+"/04/01";
         String ts = items[1]+"/03/31";
         SimpleDateFormat  format = new SimpleDateFormat("yyyy/MM/dd");  
//       Toast.makeText(getBaseContext(), "From : " + format.format(fdate) +"To : "+format.format(tdate)  , Toast.LENGTH_LONG).show();
   
         try {  
              fdate = format.parse(fs);  
              tdate = format.parse(ts);
         } catch (ParseException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         }      
         
         DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(F2Activity.this);
     	SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
     	
     	database.delete("nbcr", null, null);
     	
     	 
     	String sql1 = "SELECT  _id,policyno,polname,prem,fup,doc,sa FROM bmpolmast  where doc >= '"+format.format(fdate)+ "' and doc <= '"+ format.format(tdate) +"'  ";
     	
 		
     	Cursor ds = database.rawQuery(sql1, null);
     	
     		 Toast.makeText(getBaseContext(), String.valueOf(ds.getCount())  , Toast.LENGTH_LONG).show();
     	for (ds.moveToFirst(); !ds.isAfterLast(); ds.moveToNext()) {
     	    // do what you need with the cursor here
     		ContentValues values = new ContentValues();
     	 values.put("nbpolno", ds.getString(ds.getColumnIndex("policyno")));
     	 try {
			Date newdoc = format.parse(ds.getString(ds.getColumnIndex("doc")));
			String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(newdoc);
			 values.put("nbcommdt", dateString2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	
     	
     	 values.put("nbname", ds.getString(ds.getColumnIndex("polname")));
     	 
     	 
     	 
     	 
     	 database.insert("nbcr", null, values);
     	}	
     	database.close();    
         
         
         
         
         
         
         
         
         }   
         
    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        // your code here
    }

});
//---------------------------------------------------
Button btnSubmitnb = (Button) findViewById(R.id.button10); 
btnSubmitnb.setOnClickListener(new OnClickListener() {
  @Override
  public void onClick(View v) {
if (String.valueOf(nbbtn.getSelectedItem()).equalsIgnoreCase("NB - Fin Year Wise")) {
	AlertDialog.Builder builder1 = new AlertDialog.Builder(F2Activity.this);
    builder1.setMessage("Select Fin Year..");
    builder1.setCancelable(true);
  
    builder1.setNegativeButton("Continue......",
            new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
        }
    });

    AlertDialog alert11 = builder1.create();
    alert11.show();
} else{
    Intent theintent = new Intent(F2Activity.this,MainActivitynb.class);
   // theintent.putExtra("ftyear",String.valueOf(nbbtn.getSelectedItem()));
    startActivity(theintent);
  }
  }
});
//-------------------------------------------------
}

    public void onClick(View v) {	
			// TODO Auto-generated method stub
			  switch( ( v.getId())){
           case R.id.button6:
                Intent agintent = new Intent(F2Activity.this,welcome.class);
                startActivity(agintent);
           break;
           case R.id.button1:
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
   //        case R.id.button10:
  //      	   Intent intent1=new Intent(this,MainActivitynb.class);
	//           startActivity(intent1);
  //         break;
           case R.id.button2:
        	   Intent intentd=new Intent(this,MainActivity.class);
	           startActivity(intentd);
           break;
           case R.id.button4:
        	   Intent intenta=new Intent(this,AlphabetListDemo.class);
        	   startActivity(intenta);
           break;
           case R.id.button5:
        	   Intent intent3 = new Intent(this,GridViewActivity.class);
	        	startActivity(intent3);
           break;
           case R.id.button12:
        	    DataViewerSQLiteHelper stockSQLHelper1 = new DataViewerSQLiteHelper(this);
  			  SQLiteDatabase database1 = stockSQLHelper1.getReadableDatabase();  
  			 
  			    String sql = "";
  			    sql = "SELECT * FROM  registration";
  			    Cursor a = database1.rawQuery(sql, null);
  			    a.moveToFirst();
  			String PPwd = a.getString(a.getColumnIndex("regcouponno")).trim();
  		    String PUser = a.getString(a.getColumnIndex("regagcode")).trim();
  		    a.close();
  		    database1.close();
  		    stockSQLHelper1.close();
  		  String ipadd= "abcd";
  		
  		d.start();

  		if (exportDatabase()){
  			Toast.makeText(F2Activity.this, "Export OVer", Toast.LENGTH_LONG).show();
  		};
  		
       	  new taskPols().execute(ipadd,PUser);
  		

    
        	break;
           case R.id.button11:  	
        		d2.start();

     			final DataViewerSQLiteHelper stockSQLHelpera = new DataViewerSQLiteHelper(F2Activity.this);
     			final SQLiteDatabase databasea = stockSQLHelpera.getReadableDatabase();  
     			 String sqla = "";
     			sqla = "select regagcode from registration";
     	      Cursor aa = databasea.rawQuery(sqla, null);
     	      aa.moveToFirst();
     	
     	//     database.delete("agentdata", null, null);
            String ipadda = aa.getString(aa.getColumnIndex("regagcode")).trim();
            databasea.close();
            aa.close();
            stockSQLHelpera.close();
            new taskAg().execute(ipadda);
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	break;
        	
        	
        	
           case R.id.button13:
        	   d1.start();
      	   DataViewerSQLiteHelper stockSQLHelper2 = new DataViewerSQLiteHelper(this);
   			  SQLiteDatabase database2 = stockSQLHelper2.getWritableDatabase();  
   			
   			 	    String sql1 = "";
   			 	
        	   database2.delete(bmbillmast.TABLE_STOCK, null,null);
        	   sql1 = "SELECT * FROM  bmpolmast where status = '" +  "inforce" + "'";
        	      Cursor a1 = database2.rawQuery(sql1, null);
 			   for (a1.moveToFirst(); !a1.isAfterLast(); a1.moveToNext()) {
 				  ContentValues values1 = new ContentValues();
        	   String duepolicyno =  a1.getString(a1.getColumnIndexOrThrow("policyno"));
        	   String duepremium = a1.getString(a1.getColumnIndexOrThrow("prem"));
        	   String duemode= a1.getString(a1.getColumnIndexOrThrow("mode"));
        	   String duedate =  a1.getString(a1.getColumnIndexOrThrow("doc"));
        	   String duefup = a1.getString(a1.getColumnIndexOrThrow("fup"));
        	   String duepolname = a1.getString(a1.getColumnIndexOrThrow("polname"));
        	   String dueadd1= a1.getString(a1.getColumnIndexOrThrow("add1"));
        	   String dueadd2= a1.getString(a1.getColumnIndexOrThrow("add2"));
        	   String dueadd3= a1.getString(a1.getColumnIndexOrThrow("add3"));
        	   String duepin= a1.getString(a1.getColumnIndexOrThrow("pin"));
        	   String duemobile = a1.getString(a1.getColumnIndexOrThrow("mobile"));
        	   String dueagcode = a1.getString(a1.getColumnIndexOrThrow("agcode"));
        	      values1.put(bmbillmast.BM_POLNO, duepolicyno);
        	      values1.put(bmbillmast.BM_DOC, duedate);
        	      values1.put(bmbillmast.BM_MOD, duemode);
        	      values1.put(bmbillmast.BM_PREM, duepremium);
        	      values1.put(bmbillmast.BM_DUEFROM, duefup);
        	      values1.put(bmbillmast.BM_NAME,duepolname);
        	      values1.put(bmbillmast.BM_ADD1,dueadd1);
        	      values1.put(bmbillmast.BM_ADD2,dueadd2);
        	      values1.put(bmbillmast.BM_ADD3,dueadd3);
        	      values1.put(bmbillmast.BM_PIN,duepin);
        	      values1.put(bmbillmast.BM_MOBILE, duemobile);
        	      values1.put(bmbillmast.BM_AGCODE, dueagcode);
        	      
        	      
        	      String dtStart = duedate;  
       		   SimpleDateFormat  format = new SimpleDateFormat("yyyy/MM/dd");  
       		   try {  
       		        date = format.parse(dtStart);  
       		       System.out.println(date);  
       		   } catch (ParseException e) {  
       		       // TODO Auto-generated catch block  
       		       e.printStackTrace();  
       		   }      
        	      
       		   
    		   SimpleDateFormat  dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    		   Date date1 = date;  
			 datetime = dateformat.format(date1);
			 datetimedd = datetime.substring(0,2);
			 datetimetoday = dateformat.format(new Date());
			System.out.println("Current Date Time : " + datetime);
    		   FUP1 = datetimedd+"/"+duefup+" 00:00:00";
    		
                         SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                         try {
             				FUP1cdate = new DateTime(format1.parse(FUP1));
             			} catch (java.text.ParseException e) {
             				// TODO Auto-generated catch block
             				e.printStackTrace();
             			}  
                         DateTime test = new DateTime();
           String no_of_days =  String.valueOf(Days.daysBetween(FUP1cdate,test).getDays());  
           if (duemode.equals("Y")){
       	    mode_days = 365;
          }else if (duemode.equals("H")){
       	    mode_days = 180;
          }else if (duemode.equals("Q")){
       	    mode_days = 90;
          }else if (duemode.equals("M")){
       	    mode_days = 30;
          }
          
          
          
          
          
//          System.out.println(Integer.parseInt(no_of_days));
          no_of_inst = (Integer.parseInt(no_of_days)/(mode_days))+1;        
        	      
        	     values1.put("nfdues", no_of_inst); 
        	      
        	      
        	      
        	      
        	      String duemonth = duedate.substring(5,7).trim();
        	      System.out.println(duemonth);
        	      if (duemonth.equals("01")){values1.put(bmbillmast.BM_JAN, "Y");}
        	      if (duemonth.equals("02")){values1.put(bmbillmast.BM_FEB, "Y");}
        	      if (duemonth.equals("03")){values1.put(bmbillmast.BM_MAR, "Y");}
        	      if (duemonth.equals("04")){values1.put(bmbillmast.BM_APR, "Y");}
        	      if (duemonth.equals("05")){values1.put(bmbillmast.BM_MAY, "Y");}
        	      if (duemonth.equals("06")){values1.put(bmbillmast.BM_JUN, "Y");}
        	      if (duemonth.equals("07")){values1.put(bmbillmast.BM_JUL, "Y");}
        	      if (duemonth.equals("08")){values1.put(bmbillmast.BM_AUG, "Y");}
        	      if (duemonth.equals("09")){values1.put(bmbillmast.BM_SEP, "Y");}
        	      if (duemonth.equals("10")){values1.put(bmbillmast.BM_OCT, "Y");}
        	      if (duemonth.equals("11")){values1.put(bmbillmast.BM_NOV, "Y");}
        	      if (duemonth.equals("12")){values1.put(bmbillmast.BM_DECE, "Y");}
        	    
        	      
        	      
        	      if (duemode.equals("H")){
        	    	int  duemonth1=Integer.parseInt((duemonth));
        	    	duemonth1=duemonth1+6;
        	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
        	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
            	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
            	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
            	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
            	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
            	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
            	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
            	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
            	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
            	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
            	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
            	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");}
        	      }
        	      
        	      
        	      if (duemode.equals("Q")){
          	    	int  duemonth1=Integer.parseInt((duemonth));
          	    	duemonth1=duemonth1+3;
          	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
          	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
              	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
              	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
              	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
              	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
              	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
              	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
              	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
              	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
              	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
              	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
              	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");}
              	      
              	    duemonth1=duemonth1+3;
        	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
        	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
            	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
            	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
            	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
            	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
            	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
            	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
            	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
            	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
            	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
            	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
            	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");} 
              	      
            	      duemonth1=duemonth1+3;
          	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
          	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
              	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
              	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
              	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
              	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
              	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
              	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
              	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
              	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
              	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
              	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
              	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");}   
              	      
              	      
              	      
              	      
              	      
              	      
              	      
              	      
              	      
              	      
          	      } 
        	      
        	      
        	      if (duemode.equals("M")){
          	    	int  duemonth1=Integer.parseInt((duemonth));
          	    	duemonth1=duemonth1+1;
          	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
          	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
              	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
              	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
              	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
              	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
              	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
              	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
              	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
              	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
              	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
              	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
              	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");}
              	      
              	    duemonth1=duemonth1+1;
        	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
        	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
            	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
            	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
            	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
            	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
            	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
            	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
            	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
            	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
            	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
            	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
            	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");} 
              	      
            	      duemonth1=duemonth1+1;
          	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
          	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
              	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
              	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
              	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
              	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
              	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
              	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
              	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
              	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
              	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
              	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
              	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");}   
              	      
              		duemonth1=duemonth1+1;
        	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
        	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
            	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
            	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
            	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
            	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
            	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
            	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
            	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
            	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
            	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
            	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
            	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");}
            	      
            	    duemonth1=duemonth1+1;
      	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
      	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
          	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
          	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
          	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
          	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
          	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
          	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
          	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
          	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
          	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
          	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
          	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");} 
            	      
          	      duemonth1=duemonth1+1;
        	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
        	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
            	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
            	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
            	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
            	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
            	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
            	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
            	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
            	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
            	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
            	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
            	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");} 
            	  	duemonth1=duemonth1+1;
        	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
        	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
            	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
            	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
            	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
            	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
            	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
            	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
            	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
            	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
            	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
            	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
            	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");}
            	      
            	    duemonth1=duemonth1+1;
      	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
      	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
          	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
          	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
          	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
          	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
          	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
          	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
          	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
          	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
          	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
          	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
          	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");} 
            	      
          	      duemonth1=duemonth1+1;
        	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
        	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
            	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
            	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
            	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
            	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
            	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
            	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
            	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
            	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
            	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
            	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
            	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");} 
              	      
            	  	duemonth1=duemonth1+1;
        	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
        	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
            	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
            	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
            	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
            	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
            	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
            	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
            	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
            	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
            	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
            	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
            	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");}
            	      
            	    duemonth1=duemonth1+1;
      	    	  if (duemonth1 > 12){duemonth1 =  duemonth1 -12;}
      	    	  if (duemonth1 == 1){values1.put(bmbillmast.BM_JAN, "Y");}
          	      if (duemonth1 ==2){values1.put(bmbillmast.BM_FEB, "Y");}
          	      if (duemonth1 ==3){values1.put(bmbillmast.BM_MAR, "Y");}
          	      if (duemonth1 ==4){values1.put(bmbillmast.BM_APR, "Y");}
          	      if (duemonth1 ==5){values1.put(bmbillmast.BM_MAY, "Y");}
          	      if (duemonth1 ==6){values1.put(bmbillmast.BM_JUN, "Y");}
          	      if (duemonth1 ==7){values1.put(bmbillmast.BM_JUL, "Y");}
          	      if (duemonth1 == 8){values1.put(bmbillmast.BM_AUG, "Y");}
          	      if (duemonth1 == 9){values1.put(bmbillmast.BM_SEP, "Y");}
          	      if (duemonth1 ==10){values1.put(bmbillmast.BM_OCT, "Y");}
          	      if (duemonth1 ==11){values1.put(bmbillmast.BM_NOV, "Y");}
          	      if (duemonth1 ==12){values1.put(bmbillmast.BM_DECE, "Y");} 
            	      
          	    
              	      
              	      
              	      
              	      
          	      }    
        	      
        	      
        	      
        	      
        	      
        	      
        	   database2.insert(bmbillmast.TABLE_STOCK, null, values1); 
 			   }
        	   
        	   database2.close();
        	   stockSQLHelper2.close(); 
        	   
        	   d1.stop();
        	   
        	   break;
          
       }

}
    class taskPols extends AsyncTask<String, String, Void>
	{
 // ProgressBar progressbar =(ProgressBar)findViewById(R.id.progressBar1a);	
  URL stockURL = null;
  int em = 0;
	  ContentValues values;
	String getagcode;
	    protected void onPreExecute() {
	    	super.onPreExecute();
//	        progressbar.setProgress(ProgressDialog.STYLE_HORIZONTAL);
	//    	progressbar.setVisibility(View.VISIBLE);
	    }

	    @Override
	    protected Void doInBackground(String... params) {	
	    	String input = (String)params[0];
	    	getagcode = (String)params[1];
	    	String url_select;
	  
	     	//url_select = "http://storage.googleapis.com/sunitha/"+getagcode+"pm.csv";
	     	url_select = "http://sreekanth.azurewebsites.net/data/"+getagcode+"pm.csv";
	     	System.out.println(url_select);
	     	
	    	URL url = null;
			try {
				url = new URL(url_select);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		HttpURLConnection urlConnection = null;
			try {
				urlConnection = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
					 
				// TODO Auto-generated catch block
				e.printStackTrace();
				  Log.w("Log", "Foo didn't work: "+ e.getMessage());
			}
    		try {
				urlConnection.setRequestMethod("GET");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		urlConnection.setDoOutput(true);
    		//connect
    		try {
				urlConnection.connect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				  Log.w("Log", "Foo didn't work: "+ e.getMessage());
				e.printStackTrace();
			}
    		//set the path where we want to save the file    		
    		File SDCardRoot = Environment.getExternalStorageDirectory(); 
    		File file = new File(SDCardRoot, "Download/" + getagcode+"pm.csv");
 
    		FileOutputStream fileOutput = null;
			try {
				fileOutput = new FileOutputStream(file);
			}   catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		InputStream inputStream = null;
			try {
				inputStream = new BufferedInputStream(url.openStream(), 8192);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 Log.w("Log", "Foo didn't work: "+ e.getMessage());		
				em =1;
				
				e.printStackTrace();
			}
    		//this is the total size of the file which we are downloading
			if (em != 1){
    		int totalSize = urlConnection.getContentLength();
    		//create a buffer...
    		byte[] buffer = new byte[1024];
    		int bufferLength = 0;
    		try {
				while ( (bufferLength = inputStream.read(buffer)) != -1 ) {
					fileOutput.write(buffer, 0, bufferLength);
					int downloadedSize = (bufferLength/1024);
					// update the progressbar //
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		//close the output stream when complete //
    		try {
				fileOutput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	      		 return null;
	    }
			 protected void onPostExecute(Void v) {	
				 
				 
				 
				 
				 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(F2Activity.this);
	     	     SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
	     	       
	     	    	File dir = Environment.getExternalStorageDirectory();
	     	    	File yourFile = new File(dir, "download/" + getagcode+"pm.csv");
	     	    	long length = yourFile.length();
	            if (length > 0){
	                database.delete(bmpolmast.TABLE_STOCK, null, null);
	                database.execSQL("delete from sqlite_sequence where name= '" + bmpolmast.TABLE_STOCK + "'");
	     	    	
	     	        database.beginTransaction();
	     		try {
	     			FileReader file = new FileReader(yourFile);
	     	        BufferedReader buffer = new BufferedReader(file);
	     		String line = "";
	     	    ContentValues values;

	     		while ((line = buffer.readLine()) != null) {

	     		    String[] str = line.split("#");
	     		   if (str.length == 21) {
	     		       
		                values = new ContentValues();
		                values.put(bmpolmast.PM_TYPE,  str[1]);
		                values.put(bmpolmast.PM_BRANCH, str[2]);
		                values.put(bmpolmast.PM_POLNO, str[3]);
		                values.put(bmpolmast.PM_UNITS, str[4]);
		                values.put(bmpolmast.PM_PLAN, str[5]);
		                values.put(bmpolmast.PM_AGCODE, str[6]);
		                values.put(bmpolmast.PM_DOCODE, str[7]);
		                values.put(bmpolmast.PM_SA, str[8].replace(",",""));
		                values.put(bmpolmast.PM_PREM, str[9].replace(",",""));
		                values.put(bmpolmast.PM_TERM, str[10]);
		                values.put(bmpolmast.PM_FUP, str[11]);
		                values.put(bmpolmast.PM_NAME, str[12]);
		                values.put(bmpolmast.PM_STATUS, str[13]);
		                values.put(bmpolmast.PM_DOC, str[14]);
		                values.put(bmpolmast.PM_MOD, str[15]);
		                values.put(bmpolmast.PM_ADD1, str[16]);
		                values.put(bmpolmast.PM_ADD2, str[17]);
		                values.put(bmpolmast.PM_ADD3, str[18]);
		                values.put(bmpolmast.PM_PIN, str[19]);
		                values.put(bmpolmast.PM_DOB, str[20]);
		                
		                database.insert(bmpolmast.TABLE_STOCK, null, values);
		            }
	               }
	     		buffer.close();
	     		} catch (FileNotFoundException e) {
	     			// TODO Auto-generated catch block
	     			e.printStackTrace();
	     		} catch (SQLException e) {
	     			// TODO Auto-generated catch block
	     			e.printStackTrace();
	     		} catch (IOException e) {
	     			// TODO Auto-generated catch block
	     			e.printStackTrace();
	     		}
	     		   String sql = "select count(*) as mskcount from bmpolmast";
	     	       Cursor navcount = database.rawQuery(sql, null);
	     	       navcount.moveToFirst();

	               String ncount =  navcount.getString(navcount.getColumnIndexOrThrow("mskcount"));
	               Toast.makeText(getApplicationContext(), "Job Over,PM Records Inserted = "  + ncount,
	             		   Toast.LENGTH_LONG).show();
	               database.setTransactionSuccessful();// marks a commit
	               database.endTransaction();
	   //            progressbar.setVisibility(View.GONE);
	               if (importDatabase()){
	         			Toast.makeText(F2Activity.this, "Mobile No(s) Imported", Toast.LENGTH_LONG).show();
	         		};
	              	  d.stop();
	            }
	            else {
	        //    	 progressbar.setVisibility(View.GONE);	 
	Toast.makeText(F2Activity.this, "No Data found ....", Toast.LENGTH_LONG).show();	
	  	  d.stop();
	            }   
			
			 }
	            }  
    
    class taskAg extends AsyncTask<String, String, Void>
    {
    //	 ProgressBar progressbar =(ProgressBar) findViewById(R.id.progressBar1c);
    	 URL stockURL = null;
     	  ContentValues values;
     	String getagcode;
     	 int em = 0;
      	    protected void onPreExecute() {
      	    	super.onPreExecute();
      //	        progressbar.setProgress(ProgressDialog.STYLE_HORIZONTAL);
      	//    	progressbar.setVisibility(View.VISIBLE);
      	    }
      
      	    @Override
      	    protected Void doInBackground(String... params) {	
      //	    	String input = (String)params[0];
      	    	getagcode = (String)params[0];
      	    	String url_select;
      	  
      	     	url_select = "http://sreekanth.azurewebsites.net/data/"+getagcode+"ag.csv";
      	     	System.out.println(url_select);
      	     	
      	    	URL url = null;
    				try {
    					url = new URL(url_select);
    				} catch (MalformedURLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
          		HttpURLConnection urlConnection = null;
    				try {
    					urlConnection = (HttpURLConnection) url.openConnection();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
          		try {
    					urlConnection.setRequestMethod("GET");
    				} catch (ProtocolException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
          		urlConnection.setDoOutput(true);
          		//connect
          		try {
    					urlConnection.connect();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
          		//set the path where we want to save the file    		
          		File SDCardRoot = Environment.getExternalStorageDirectory(); 
          		File file = new File(SDCardRoot, "Download/" + getagcode+"ag.csv");
       
          		FileOutputStream fileOutput = null;
    				try {
    					fileOutput = new FileOutputStream(file);
    				}   catch (FileNotFoundException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
          		InputStream inputStream = null;
    				try {
    					inputStream = new BufferedInputStream(url.openStream(), 8192);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    					em=1;
    				}
    				if (em != 1){
          		//this is the total size of the file which we are downloading
          		int totalSize = urlConnection.getContentLength();
          		//create a buffer...
          		byte[] buffer = new byte[1024];
          		int bufferLength = 0;
          		try {
    					while ( (bufferLength = inputStream.read(buffer)) != -1 ) {
    						fileOutput.write(buffer, 0, bufferLength);
    						int downloadedSize = (bufferLength/1024);
    						// update the progressbar //
    					}
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
          		//close the output stream when complete //
          		try {
    					fileOutput.close();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}}
    		      		 return null;
      	    }
    				 protected void onPostExecute(Void v) {	
    					 
    					 
    					 
    					 
    					 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(F2Activity.this);
    		     	     SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
    		     	       
    		     	    	File dir = Environment.getExternalStorageDirectory();
    		     	    	File yourFile = new File(dir, "download/" + getagcode+"ag.csv");
    		     	    	long length = yourFile.length();
    			            if (length > 0){
    			            	database.delete("agentdata", null, null);
    		     	        database.beginTransaction();
    		     		try {
    		     			FileReader file = new FileReader(yourFile);
    		     	        BufferedReader buffer = new BufferedReader(file);
    		     		String line = "";
    		     	    ContentValues values;

    		     		while ((line = buffer.readLine()) != null) {

    		     		    String[] str = line.split("#");
    		     		   if (str.length == 27) {
    		     		       
    			                values = new ContentValues();
    			                values.put(AgentData.AG_CODE, str[1]);
    			                values.put(AgentData.AG_NAME, str[2]);
    			                values.put(AgentData.AG_ELGCODE, str[3]);
    			                values.put(AgentData.AG_DOCODE, str[4]);
    			                values.put(AgentData.AG_SEX, str[5]);
    			                values.put(AgentData.AG_CLUB, str[6]);
    			                values.put(AgentData.AG_PAN, str[7]);
    			                values.put(AgentData.AG_DOA, str[8]);
    			                values.put(AgentData.AG_DOB, str[9]);
    			                values.put(AgentData.AG_LICENNO, str[10]);
    			                values.put(AgentData.AG_LICEXPDATE, str[11]);
    			                values.put(AgentData.AG_ACCOUNTNO, str[12]);
    			                values.put(AgentData.AG_BANKNAME, str[13]);
    			                values.put(AgentData.AG_DOE, str[14]);
    			                values.put(AgentData.AG_DOR, str[15]);
    			                values.put(AgentData.AG_ADR1, str[16]);
    			                values.put(AgentData.AG_ADR2, str[17]);
    			                values.put(AgentData.AG_ADR3, str[18]);
    			                values.put(AgentData.AG_PIN, str[19]);
    			                values.put(AgentData.AG_TEL, str[20]);
    			                values.put(AgentData.AG_ENTRYMD, str[21]);
    			                values.put(AgentData.AG_EXITMD, str[22]);
    			                values.put(AgentData.AG_NOMINEE, str[23]);
    			                values.put(AgentData.AG_NOMINEEREL, str[24]);
    			                values.put(AgentData.AG_CLIACD, str[25]);
    			                values.put(AgentData.AG_EMAIL, str[26]);
    			                database.insert(AgentData.TABLE_STOCK, null, values);
    		                 
    		               }
    		             
    		     		}
    		     		buffer.close();
    		     		} catch (FileNotFoundException e) {
    		     			// TODO Auto-generated catch block
    		     			e.printStackTrace();
    		     		} catch (SQLException e) {
    		     			// TODO Auto-generated catch block
    		     			e.printStackTrace();
    		     		} catch (IOException e) {
    		     			// TODO Auto-generated catch block
    		     			e.printStackTrace();
    		     		}
    		     		   String sql = "select count(*) as mskcount from agentdata";
    		     	       Cursor navcount = database.rawQuery(sql, null);
    		     	       navcount.moveToFirst();

    		               String ncount =  navcount.getString(navcount.getColumnIndexOrThrow("mskcount"));
    		               Toast.makeText(F2Activity.this, "Job Over,Agency Records Inserted = "  + ncount,
    		             		   Toast.LENGTH_LONG).show();
    		               database.setTransactionSuccessful();// marks a commit
    		               database.endTransaction();
    		               testDB(getagcode);
    		               d2.stop();
    		  //             progressbar.setVisibility(View.GONE);
    		            } 
    			            else {
    			    //        	 progressbar.setVisibility(View.GONE);	 
    			Toast.makeText(F2Activity.this, "No Data found ....", Toast.LENGTH_LONG).show();
    			 d2.stop();
    			            }  } 
    }   
    
    
    
    
    public void testDB(String agcode) {
  	  if (android.os.Build.VERSION.SDK_INT > 9) {
  		  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
  	      StrictMode.setThreadPolicy(policy);
        }
  	final String url = "jdbc:mysql://ap-cdbr-azure-southeast-a.cloudapp.net/sreekanAcCySHknL";
    final String user = "b2bc3b54a57106";
	  final String pass = "cbbcf92d6038bb6";
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, pass);
         System.out.println("Database connection success"); 
         String insertTableSQL = "REPLACE INTO updation"
        			+ "(uagcode, upddate) VALUES"
        			+ "(?,?)";
        	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(insertTableSQL);
        	preparedStatement.setString(1, agcode);
        	String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        	preparedStatement.setString(2, date);
        	// execute insert SQL stetement
        	preparedStatement .executeUpdate();
        	con.close();
    }
    catch(Exception e) {
        e.printStackTrace();
//        tv.setText(e.toString());
    }   

  }
  

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public boolean exportDatabase() {
    	DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
    	
    	/**First of all we check if the external storage of the device is available for writing.
    	 * Remember that the external storage is not necessarily the sd card. Very often it is
    	 * the device storage.
    	 */
    	String state = Environment.getExternalStorageState();
    	if (!Environment.MEDIA_MOUNTED.equals(state)) { 
    		return false;
    	}
    	else {
    		//We use the Download directory for saving our .csv file.
    	    File exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);      
    	    if (!exportDir.exists()) 
    	    {
    	        exportDir.mkdirs();
    	    }
    	    
    	    File file;
    	    PrintWriter printWriter = null;
    	    try 
    	    {
    	    	file = new File(exportDir, "MyCSVFile.csv");
    	        file.createNewFile();                
    	        printWriter = new PrintWriter(new FileWriter(file));
    	                   
    	        /**This is our database connector class that reads the data from the database.
    	         * The code of this class is omitted for brevity.
    	         */
    	        
				 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(F2Activity.this);
	     	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
    	    	
    	    	/**Let's read the first table of the database.
    	    	 * getFirstTable() is a method in our DBCOurDatabaseConnector class which retrieves a Cursor
    	    	 * containing all records of the table (all fields).
    	    	 * The code of this class is omitted for brevity.
    	    	 */
    	    	
    	    	   String sql = "";
     			    sql = "SELECT * FROM  bmpolmast";
     			    Cursor curCSV = database.rawQuery(sql, null);
     			//   curCSV.moveToFirst();
    	    	//Write the name of the table and the name of the columns (comma separated values) in the .csv file.
    	//    	printWriter.println("FIRST TABLE OF THE DATABASE");
    	//    	printWriter.println("DATE,ITEM,AMOUNT,CURRENCY");
     			  for (curCSV.moveToFirst(); !curCSV.isAfterLast(); curCSV.moveToNext()) {
    	        
    	        	Long date = curCSV.getLong(curCSV.getColumnIndex("policyno"));
    	        	String item = curCSV.getString(curCSV.getColumnIndex("mobile"));
    	   //     	Double amount = curCSV.getDouble(curCSV.getColumnIndex("amount"));
    	   //     	String currency = curCSV.getString(curCSV.getColumnIndex("currency"));
    	        	
    	        	/**Create the line to write in the .csv file. 
    	        	 * We need a String where values are comma separated.
    	        	 * The field date (Long) is formatted in a readable text. The amount field
    	        	 * is converted into String.
    	        	 */
    	        	String record = date + "," + item ;
    			    printWriter.println(record); //write the record in the .csv file
    			}
    	 
    			curCSV.close();
    			database.close();
    			stockSQLHelper.close();curCSV.close();
    	    }
    	   
    	   catch(Exception exc) {
    		   //if there are any exceptions, return false
    		   return false;
    	   }
    	   finally {
    		   if(printWriter != null) printWriter.close();
    	   }	
    	   
    	   //If there are no errors, return true.
    	   return true;
    	}
    }
    
    
    
    public boolean importDatabase()
    {
    	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(F2Activity.this);
 	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
    	String state = Environment.getExternalStorageState();
    	if (!Environment.MEDIA_MOUNTED.equals(state)) { 
    		return false;
    	}
    	else {
    		//We use the Download directory for saving our .csv file.
    	    File exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    	    
    	    File yourFile = new File(exportDir, "MyCSVFile.csv");
    	    
    	    
	  
	        database.beginTransaction();
		try {
			FileReader file = new FileReader(yourFile);
	        BufferedReader buffer = new BufferedReader(file);
		String line = "";
	    ContentValues values;

		while ((line = buffer.readLine()) != null) {

		    String[] str = line.split(",");
		   if (str.length == 2) {
		       
           values = new ContentValues();
           int SChCode = (int) Integer.parseInt(str[0]);
     
   //    values.put(bmpolmast.PM_POLNO, SChCode);
     
       values.put(bmpolmast.PM_MOBILE, str[1]);
       
     
       System.out.println(SChCode+ "    " + str[1]);
         rowsUpdated =   database.update(bmpolmast.TABLE_STOCK,  values, "policyno  = " + SChCode ,null);
     //  database.insert(bmpolmast.TABLE_STOCK, null, values);
     //  int   rowsUpdated = database.update("products_web", values, "TRIM(inenumber) = '" + SChCode + "'", null);
       }
     
		}
		buffer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	}
    	  database.setTransactionSuccessful();
		  database.endTransaction();
//    	Toast.makeText(F2Activity.this, rowsUpdated, Toast.LENGTH_LONG).show();
		return true;	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
} // end class
