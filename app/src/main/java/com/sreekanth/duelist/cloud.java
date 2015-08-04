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
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * This is the activity for feature 5 in the dashboard application.
 * It displays some text and provides a way to get back to the home activity.
 *
 */

public class cloud extends DashboardActivity 
{
SQLiteDatabase database;
String	GFile;
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
int   rowsUpdated = 0 ;
String j2;
protected void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
    setContentView (R.layout.activity_f5);
    setTitleFromActivityLabel (R.id.title_text);
    
    Intent iin= getIntent();
    Bundle b = iin.getExtras();
    if(b!=null)
    {
        j2 =(String) b.get("source");
        
    }
    
    
 //   Button btnNb = (Button)findViewById(R.id.button1);
 //   Button btnPols = (Button)findViewById(R.id.button1a);
 //   Button btnDues = (Button)findViewById(R.id.button1b);
    Button btnAg = (Button)findViewById(R.id.button1c);
    Button btnCNb = (Button)findViewById(R.id.button2);
    Button btnCDues = (Button)findViewById(R.id.button2b);
    Button btnCAg = (Button)findViewById(R.id.button2c);
    
    Button btnCPols = (Button)findViewById(R.id.button2a);
    Button btnBackup = (Button)findViewById(R.id.button1d);
    Button btnRestore = (Button)findViewById(R.id.button2d);
    Button btnGBackup = (Button)findViewById(R.id.button1);
    if (j2.equals("abar")){
    btnCNb.setVisibility(View.GONE); btnCDues.setVisibility(View.GONE);btnCAg.setVisibility(View.GONE);
    btnAg.setVisibility(View.GONE);
    }
    /*******************************************************************/
    
       btnBackup.setOnClickListener(new View.OnClickListener() {
 	  @Override
	  public void onClick(View v) 
	  {  
 			if (exportDatabase()){
 	  			Toast.makeText(cloud.this, "Export OVer,press Back Button", Toast.LENGTH_LONG).show();
 	  		};
		  }
	});
    

       btnRestore.setOnClickListener(new View.OnClickListener() {
 	  @Override
	  public void onClick(View v) 
	  {  
 		 startActivity (new Intent(getApplicationContext(), FEActivity.class));
 		
		  }
	});
    
    
       btnGBackup.setOnClickListener(new View.OnClickListener() {
    	 	  @Override
    		  public void onClick(View v) 
    		  {  
    	 		 startActivity (new Intent(getApplicationContext(), Gmail.class));	
    			  }
    		});
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*   btnNb.setOnClickListener(new View.OnClickListener() {
       	  @Override
    	  public void onClick(View v) 
    	  {  
  			 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
    			 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
    		     String sql = "";
    		     sql = "SELECT * FROM  agentdata";  
    			 Cursor a = database.rawQuery(sql, null);
    			 a.moveToFirst();
    		 	 String dagcode = a.getString(a.getColumnIndex("agcode")).trim();
    	         a.close();
    	         database.delete("nbcr", null, null);
    	         database.close();
    	         a.close();
    	         stockSQLHelper.close();
    			 String ipadd= "abcd";
    		     new taskNB().execute(ipadd,dagcode); 
    		  }
    	});*/
    btnCNb.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
			 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
			 
		     String sql = "";
		     sql = "SELECT count(*) as cnb FROM  nbcr";
			    
			 Cursor a = database.rawQuery(sql, null);
			 a.moveToFirst();
		 	 String cnb = a.getString(a.getColumnIndex("cnb")).trim();
	         a.close();
	         Toast.makeText(cloud.this, cnb, Toast.LENGTH_LONG).show();	
		}
	});
    /*  btnPols.setOnClickListener(new View.OnClickListener() {
   	  @Override
  	  public void onClick(View v) 
  	  {
		 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
		 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
		 
	     String sql = "";
	     sql = "SELECT * FROM  agentdata";
		    
		 Cursor a = database.rawQuery(sql, null);
		 a.moveToFirst();
	 	 String dagcode = a.getString(a.getColumnIndex("agcode")).trim();
        a.close();
   //     database.delete("bmpolmast", null, null);
        database.close();
        a.close();
        stockSQLHelper.close();
		String ipadd= "abcd";
		
	  new taskPols().execute(ipadd,dagcode); 
	
  		  }
  	});*/
 btnCPols.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 
			 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
			 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
			 
		     String sql = "";
		     sql = "SELECT count(*) as cpols FROM  bmpolmast";
			    
			 Cursor a = database.rawQuery(sql, null);
			 a.moveToFirst();
		 	 String cpols = a.getString(a.getColumnIndex("cpols")).trim();
	         a.close();
	         Toast.makeText(cloud.this, cpols, Toast.LENGTH_LONG).show();	
		}
	});
 /*btnDues.setOnClickListener(new View.OnClickListener() {
  	  @Override
 	  public void onClick(View v) 
 	  {
		 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
		 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
		 
	     String sql = "";
	     sql = "SELECT * FROM  agentdata";
		    
	   Cursor a = database.rawQuery(sql, null);
	   a.moveToFirst();
	   String dagcode = a.getString(a.getColumnIndex("agcode")).trim();
       a.close();
       database.delete("bmbillmast", null, null);
       database.close();
       a.close();
       stockSQLHelper.close();
	   String ipadd= "abcd";
	   new taskDues().execute(ipadd,dagcode); 
 		  }
 	});*/
  btnCDues.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
			 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
			 
		     String sql = "";
		     sql = "SELECT count(*) as cdues FROM  bmbillmast";
			    
			 Cursor a = database.rawQuery(sql, null);
			 a.moveToFirst();
		 	 String cdues = a.getString(a.getColumnIndex("cdues")).trim();
	         a.close();
	         Toast.makeText(cloud.this, cdues, Toast.LENGTH_LONG).show();	
		}
	});
  btnAg.setOnClickListener(new View.OnClickListener() {
  	  @Override
 	  public void onClick(View v) 
 	  {
 
  	/*		final DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
  			final SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
  			 String sql = "";
  			sql = "select regagcode from registration";
  	      Cursor a = database.rawQuery(sql, null);
  	      a.moveToFirst();
  	
  	//     database.delete("agentdata", null, null);
         String ipadd = a.getString(a.getColumnIndex("regagcode")).trim();
         database.close();
         a.close();
         stockSQLHelper.close();
         new taskAg().execute(ipadd);*/
  		  
  		  
  		  
  		  
  		  
  		  
  		  
  		  
  		Intent ImpComm = new Intent(cloud.this,FileExplore.class);
  		startActivity(ImpComm);
  		  
  		  }
 	      });
  
 btnCAg.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 
			 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
			 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
			 
		     String sql = "";
		     sql = "SELECT count(*) as cag FROM  agentdata";
			    
			 Cursor a = database.rawQuery(sql, null);
			 a.moveToFirst();
		 	 String cag = a.getString(a.getColumnIndex("cag")).trim();
	         a.close();
	         Toast.makeText(cloud.this, cag, Toast.LENGTH_LONG).show();	
		}
	});
  	 }
/********************************************************************************************/
class taskNB extends AsyncTask<String, String, Void>
{
//	 ProgressBar progressbar =(ProgressBar)findViewById(R.id.progressBar1);
	 URL stockURL = null;
  	  ContentValues values;
  	String getagcode;
   	    protected void onPreExecute() {
   	    	super.onPreExecute();
   //	        progressbar.setProgress(ProgressDialog.STYLE_HORIZONTAL);
  // 	    	progressbar.setVisibility(View.VISIBLE);
   	    }
   
   	    @Override
   	    protected Void doInBackground(String... params) {	
   	    	String input = (String)params[0];
   	    	getagcode = (String)params[1];
   	    	String url_select;
   	  
   	     	url_select = "http://storage.googleapis.com/sunitha/"+getagcode+"nb.csv";
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
       		File file = new File(SDCardRoot, "download/" + getagcode+"nb.csv");
    
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
				}
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
				}
		      		 return null;
   	    }
				 protected void onPostExecute(Void v) {	
					 
					 
					 
					 
					 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
		     	     SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
		     	       
		     	    	File dir = Environment.getExternalStorageDirectory();
		     	    	File yourFile = new File(dir, "download/" + getagcode+"nb.csv");
		     	        database.beginTransaction();
		     		try {
		     			FileReader file = new FileReader(yourFile);
		     	        BufferedReader buffer = new BufferedReader(file);
		     		String line = "";
		     	    ContentValues values;

		     		while ((line = buffer.readLine()) != null) {

		     		    String[] RowData = line.split("#");
		     		   if (RowData.length == 10) {
		     		       
		                   values = new ContentValues();
		               System.out.println(RowData[0]);
		                   values.put(NBData.NB_POLNO, RowData[0]);
		                  values.put(NBData.NB_COMMDT, RowData[1]);
		                   values.put(NBData.NB_SA, RowData[2]);
		                   values.put(NBData.NB_PREM, RowData[3]);
		                   values.put(NBData.NB_PLAN, RowData[4]);
		                   values.put(NBData.NB_TERM, RowData[5]);
		                   values.put(NBData.NB_AGCODE, RowData[6]);
		                   values.put(NBData.NB_DOCODE, RowData[7]);
		                   values.put(NBData.NB_MODE, RowData[8]);
		                   values.put(NBData.NB_NAME, RowData[9]);
		                 
		                 
		                   database.insert(NBData.TABLE_STOCK, null, values);
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
		     		   String sql = "select count(*) as mskcount from nbcr";
		     	       Cursor navcount = database.rawQuery(sql, null);
		     	       navcount.moveToFirst();

		               String ncount =  navcount.getString(navcount.getColumnIndexOrThrow("mskcount"));
		               Toast.makeText(getApplicationContext(), "Job Over,NB Records Inserted = "  + ncount,
		             		   Toast.LENGTH_LONG).show();
		               database.setTransactionSuccessful();// marks a commit
		               database.endTransaction();
	//	               progressbar.setVisibility(View.GONE);
		            }   
  }
/*****************************************************************************/
class taskPols extends AsyncTask<String, String, Void>
    	{
	  //ProgressBar progressbar =(ProgressBar)findViewById(R.id.progressBar1a);	
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
    	  
    	     	url_select = "http://storage.googleapis.com/sunitha/"+getagcode+"pm.csv";
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
        		File file = new File(SDCardRoot, "download/" + getagcode+"pm.csv");
     
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
					 
					 
					 
					 
					 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
		     	     SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
		     	       
		     	    	File dir = Environment.getExternalStorageDirectory();
		     	    	File yourFile = new File(dir, "download/" + getagcode+"pm.csv");
		     	    	long length = yourFile.length();
		            if (length > 0){
		                database.delete("bmpolmast", null, null);
		     	    	
		     	    	
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
		  //             progressbar.setVisibility(View.GONE);
		            }
		            else {
		    //        	 progressbar.setVisibility(View.GONE);	 
		Toast.makeText(cloud.this, "No Data found ....", Toast.LENGTH_LONG).show();			 
		            }   
				
				 }
		            }      	   
/***************************************************************************************************/
class taskDues extends AsyncTask<String, String, Void>
{
	  //   ProgressBar progressbar =(ProgressBar)findViewById(R.id.progressBar1b);
	     URL stockURL = null;
	   	  ContentValues values;
	   	String getagcode;
	    	    protected void onPreExecute() {
	    	    	super.onPreExecute();
	    //	        progressbar.setProgress(ProgressDialog.STYLE_HORIZONTAL);
	    //	    	progressbar.setVisibility(View.VISIBLE);
	    	    }
	    
	    	    @Override
	    	    protected Void doInBackground(String... params) {	
	    	    	String input = (String)params[0];
	    	    	getagcode = (String)params[1];
	    	    	String url_select;
	    	  
	    	     	url_select = "http://storage.googleapis.com/sunitha/"+getagcode+"bm.csv";
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
	        		File file = new File(SDCardRoot, "download/" + getagcode+"bm.csv");
	     
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
					}
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
					}
			      		 return null;
	    	    }
					 protected void onPostExecute(Void v) {	
						 
						 
						 
						 
						 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
			     	     SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
			     	       
			     	    	File dir = Environment.getExternalStorageDirectory();
			     	    	File yourFile = new File(dir, "download/" + getagcode+"bm.csv");
			     	        database.beginTransaction();
			     		try {
			     			FileReader file = new FileReader(yourFile);
			     	        BufferedReader buffer = new BufferedReader(file);
			     		String line = "";
			     	    ContentValues values;

			     		while ((line = buffer.readLine()) != null) {

			     		    String[] RowData = line.split("#");
			     		   if (RowData.length == 28) {
			     		       
			                   values = new ContentValues();
		//	               System.out.println(RowData[0]);
			                   values.put(bmbillmast.BM_POLNO, RowData[0]);
			                  values.put(bmbillmast.BM_DOC, RowData[1]);
			                   values.put(bmbillmast.BM_PLAN, RowData[2]);
			                   values.put(bmbillmast.BM_TERM, RowData[3]);
			                   values.put(bmbillmast.BM_MOD, RowData[4]);
			                   values.put(bmbillmast.BM_SA, RowData[5]);
			                   values.put(bmbillmast.BM_PREM, RowData[6]);
			                   values.put(bmbillmast.BM_AGCODE, RowData[7]);
			                   values.put(bmbillmast.BM_DEVOFF, RowData[8]);
			                   values.put(bmbillmast.BM_DUEFROM, RowData[9]);
			                   values.put(bmbillmast.BM_DUETO, RowData[10]);
			                   values.put(bmbillmast.BM_NAME, RowData[11]);
			                   values.put(bmbillmast.BM_ADD1, RowData[12]);
			                   values.put(bmbillmast.BM_ADD2, RowData[13]);
			                   values.put(bmbillmast.BM_ADD3, RowData[14]);
			                   values.put(bmbillmast.BM_PIN, RowData[15]);
			                   values.put(bmbillmast.BM_APR, RowData[16]);
			                   values.put(bmbillmast.BM_MAY, RowData[17]);
			                   values.put(bmbillmast.BM_JUN, RowData[18]);
			                   values.put(bmbillmast.BM_JUL, RowData[19]);
			                   values.put(bmbillmast.BM_AUG, RowData[20]);
			                   values.put(bmbillmast.BM_SEP, RowData[21]);
			                   values.put(bmbillmast.BM_OCT, RowData[22]);
			                   values.put(bmbillmast.BM_NOV, RowData[23]);
			                   values.put(bmbillmast.BM_DECE, RowData[24]);
			                   values.put(bmbillmast.BM_JAN, RowData[25]);
			                   values.put(bmbillmast.BM_FEB, RowData[26]);
			                   values.put(bmbillmast.BM_MAR, RowData[27]);
			              
			                   database.insert(bmbillmast.TABLE_STOCK, null, values);
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
			     		   String sql = "select count(*) as mskcount from bmbillmast";
			     	       Cursor navcount = database.rawQuery(sql, null);
			     	       navcount.moveToFirst();

			               String ncount =  navcount.getString(navcount.getColumnIndexOrThrow("mskcount"));
			               Toast.makeText(getApplicationContext(), "Job Over,Dues Records Inserted = "  + ncount,
			             		   Toast.LENGTH_LONG).show();
			               database.setTransactionSuccessful();// marks a commit
			               database.endTransaction();
			//               progressbar.setVisibility(View.GONE);
			            }   
}
/*****************************************************************************/
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
  //	    	progressbar.setVisibility(View.VISIBLE);
  	    }
  
  	    @Override
  	    protected Void doInBackground(String... params) {	
  //	    	String input = (String)params[0];
  	    	getagcode = (String)params[0];
  	    	String url_select;
  	  
  	     	url_select = "http://storage.googleapis.com/sunitha/"+getagcode+"ag.csv";
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
      		File file = new File(SDCardRoot, "download/" + getagcode+"ag.csv");
   
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
					 
					 
					 
					 
					 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
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
		               Toast.makeText(getApplicationContext(), "Job Over,Agency Records Inserted = "  + ncount,
		             		   Toast.LENGTH_LONG).show();
		               database.setTransactionSuccessful();// marks a commit
		               database.endTransaction();
		               testDB(getagcode);
	//	               progressbar.setVisibility(View.GONE);
		            } 
			            else {
		//	            	 progressbar.setVisibility(View.GONE);	 
			Toast.makeText(cloud.this, "No Data found ....", Toast.LENGTH_LONG).show();			 
			            }  } 
}
/*****************************************************************************/
public void testDB(String agcode) {
	  if (android.os.Build.VERSION.SDK_INT > 9) {
		  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	      StrictMode.setThreadPolicy(policy);
      }
	final String url = "jdbc:mysql://ap-cdbr-azure-east-b.cloudapp.net/sreekanAcCySHknL";
  final String user = "b4d182987fb4e6";
  final String pass = "02061a38";
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
//      tv.setText(e.toString());
  }   

}
public boolean exportDatabase() {
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
   		
   	    File exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/ms/");      
   	    if (!exportDir.exists()) 
   	    {
   	        exportDir.mkdirs();
   	    }
   	    
   	    File file;
   	    PrintWriter printWriter = null;
   	    try 
   	    {
   	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
   	    	Date now = new Date();
   	    	String fileName = "Policy_"+formatter.format(now) + ".csv";
   	    	
   	    	file = new File(exportDir, fileName );
   	        file.createNewFile();                
   	        printWriter = new PrintWriter(new FileWriter(file));
   	                   
   	        /**This is our database connector class that reads the data from the database.
   	         * The code of this class is omitted for brevity.
   	         */
   	        
				 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(cloud.this);
	     	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
   	    	
   	    	/**Let's read the first table of the database.
   	    	 * getFirstTable() is a method in our DBCOurDatabaseConnector class which retrieves a Cursor
   	    	 * containing all records of the table (all fields).
   	    	 * The code of this class is omitted for brevity.
   	    	 */
   	    	
   	    	String sql = "";
    		sql = "SELECT * FROM  bmpolmast";
    		Cursor curCSV = database.rawQuery(sql, null);
    		
   	    	//Write the name of the table and the name of the columns (comma separated values) in the .csv file.
   	//    	printWriter.println("FIRST TABLE OF THE DATABASE");
   	//    	printWriter.println("DATE,ITEM,AMOUNT,CURRENCY");
    		for (curCSV.moveToFirst(); !curCSV.isAfterLast(); curCSV.moveToNext()) {
	   //  	Long polno = curCSV.getLong(curCSV.getColumnIndex("policyno"));
	   //   Long mobileno = curCSV.getLong(curCSV.getColumnIndex("mobile"));
   	   //  	Double amount = curCSV.getDouble(curCSV.getColumnIndex("amount"));

		   String PolicyNo = curCSV.getString(curCSV.getColumnIndexOrThrow("policyno"));
		   String polname = curCSV.getString(curCSV.getColumnIndex("polname"));
		   String FUP = curCSV.getString(curCSV.getColumnIndexOrThrow("fup"));
		   String Mode = curCSV.getString(curCSV.getColumnIndexOrThrow("mode"));
		   String DOC = curCSV.getString(curCSV.getColumnIndexOrThrow("doc"));
		   String Status = curCSV.getString(curCSV.getColumnIndexOrThrow("status"));
		   String Units = curCSV.getString(curCSV.getColumnIndexOrThrow("units"));
		   String PlanNo = curCSV.getString(curCSV.getColumnIndexOrThrow("plan"));
		   String TermNo = curCSV.getString(curCSV.getColumnIndexOrThrow("term"));
		   String AgCode = curCSV.getString(curCSV.getColumnIndexOrThrow("agcode"));
		   String SA = curCSV.getString(curCSV.getColumnIndexOrThrow("sa"));
		   String DOB = curCSV.getString(curCSV.getColumnIndexOrThrow("dob"));
		   String Prem = curCSV.getString(curCSV.getColumnIndexOrThrow("prem"));
		   String Add1 = 
				   curCSV.getString(curCSV.getColumnIndexOrThrow("add1"));
		   String Add2 = 
				   curCSV.getString(curCSV.getColumnIndexOrThrow("add2"));
		   String Add3 = 
				   curCSV.getString(curCSV.getColumnIndexOrThrow("add3"));
		   String Pin = curCSV.getString(curCSV.getColumnIndexOrThrow("pin"));
		   String Mobile = curCSV.getString(curCSV.getColumnIndexOrThrow("mobile"));
		   String Email = curCSV.getString(curCSV.getColumnIndexOrThrow("email"));
		   String Type = curCSV.getString(curCSV.getColumnIndexOrThrow("type"));
		   String Branch = curCSV.getString(curCSV.getColumnIndexOrThrow("branch"));
   	        	/**Create the line to write in the .csv file. 
   	        	 * We need a String where values are comma separated.
   	        	 * The field date (Long) is formatted in a readable text. The amount field
   	        	 * is converted into String.
   	        	 */
   	       String record = PolicyNo + "#" +
   	        			polname + "#" +
   	        			FUP + "#" +
   	        			Mode + "#" +
   	        			DOC + "#" +
   	        			Status + "#" +
   	        			Units + "#" +
   	        			PlanNo + "#" +
   	        			TermNo + "#" +
   	        			AgCode + "#" +
   	        			SA + "#" +
   	        			DOB + "#" +
   	        			Prem + "#" +
   	        	        Add1 + "#" +
   	        	        Add2 + "#" +
   	        	        Add3 + "#" +
   	        	        Pin + "#" +
   	        	        Mobile + "#" +
   	        	        Email + "#" +
   	        	        Type   + "#" +
   	        	        Branch;
   	        	        
   	        	       
   	 //      Log.d("Output",record);
   			printWriter.println(record); //write the record in the .csv file
   			}
   			curCSV.close();
   			database.close();
   			stockSQLHelper.close();curCSV.close();
   	        }
   	   catch(Exception exc) {
   		   //if there are any exceptions, return false
   		Toast.makeText(cloud.this, exc.toString(), Toast.LENGTH_LONG).show();
   		   return false;
   	       }
   	   finally {
   		   if(printWriter != null) printWriter.close();
   	       }	
   	   //If there are no errors, return true.
   	   return true;
   	       }
           }
   

   
  
} // end class
