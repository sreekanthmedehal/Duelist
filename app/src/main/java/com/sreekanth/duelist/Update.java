package com.sreekanth.duelist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

public class Update extends Activity {
	HttpClient httpclient;
	HttpGet request;
	HttpResponse response;
	String url;
	String line;
	String result = " ";
	  private Handler mHandler;
	//  long    lastUpdateTime;
	  Boolean isInternetPresent = false;
	   
	   // Connection detector class
	   ConnectionDetector cd;
	   Context context;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_update);
	        mHandler = new Handler();
	        cd = new ConnectionDetector(getApplicationContext());
	    	  
	    	  isInternetPresent = cd.isConnectingToInternet();
	    	  
	    	  // check for Internet status
	    	  
	    		     if (isInternetPresent) {
	    		    	 new   taskNB().execute();

	    				} else {
	    	
	    		  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	    					Update.this);
	    	 
	    				// set title
	    				alertDialogBuilder.setTitle("No InterNet Connection");
	    	 
	    				// set dialog message
	    				alertDialogBuilder
	    					.setMessage("Want to Open Internet Connection?")
	    					.setCancelable(false)
	    					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
	    						public void onClick(DialogInterface dialog,int id) {
	    							// if this button is clicked, close
	    							// current activity
	    							ConnectivityManager dataManager;
	    					        dataManager  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	    					        Method dataMtd = null;
	    					        try {
	    					            dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
	    					        } catch (NoSuchMethodException e) {
	    					            // TODO Auto-generated catch block
	    					            e.printStackTrace();
	    					        }
	    					        dataMtd.setAccessible(true);
	    					        try {
	    					            dataMtd.invoke(dataManager, true);
	    					        } catch (IllegalArgumentException e) {
	    					            // TODO Auto-generated catch block
	    					            e.printStackTrace();
	    					        } catch (IllegalAccessException e) {
	    					            // TODO Auto-generated catch block
	    					            e.printStackTrace();
	    					        } catch (InvocationTargetException e) {
	    					            // TODO Auto-generated catch block
	    					            e.printStackTrace();
	    					        }   							
	    					        finish();  
	    						}
	    						
	    					  })
	    					.setNegativeButton("No",new DialogInterface.OnClickListener() {
	    						public void onClick(DialogInterface dialog,int id) {
	    							// if this button is clicked, just close
	    							// the dialog box and do nothing
	    							dialog.cancel();
	    							finish();
	    						}
	    					});
	    	 
	    					// create alert dialog
	    					AlertDialog alertDialog = alertDialogBuilder.create();
	    	 
	    					// show it
	    					alertDialog.show();
	    					
	    	  }     
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	    
	  //      checkUpdate.start();
	 //       mHandler.post(showUpdate);
	        /* Get Last Update Time from Preferences */
	//        SharedPreferences prefs = getPreferences(0);
//	     lastUpdateTime =  prefs.getLong("lastUpdateTime", 0);
	        
	        /* Should Activity Check for Updates Now? */
//	        if ((lastUpdateTime + (24 * 60 * 60 * 1000)) < System.currentTimeMillis()) {

	            /* Save current timestamp for next Check*/
//	            lastUpdateTime = System.currentTimeMillis();            
//	            SharedPreferences.Editor editor = getPreferences(0).edit();
//	            editor.putLong("lastUpdateTime", lastUpdateTime);
//	            editor.commit();        
	     
	
//	            /* Start Update */            
//	            checkUpdate.start();
//	        }
	    }
	    
		class taskNB extends AsyncTask<String, String, Void>
		{
			ProgressDialog progressDialog = new ProgressDialog(Update.this);
			
		    protected void onPreExecute() {	
		    	 progressDialog.setMessage("Checking for Updates...");
	   	         progressDialog.show();
		    }
		    protected Void doInBackground(String... params) {    
		   //  	url = "http://10.0.2.2:8080/openag.php";
		    	url = "http://storage.googleapis.com/sunitha/update.txt";
		     	try {
					httpclient = new DefaultHttpClient();
					request = new HttpGet(url);
					response = httpclient.execute(request);
				}
				catch (Exception e) {
					// Code to handle exception
				}
		     
				// response code
				try {
					BufferedReader rd = new BufferedReader(new InputStreamReader(
							response.getEntity().getContent()));
					while ((line = rd.readLine()) != null) {
					result += line;
					}
				} catch (Exception e) {
					// Code to handle exception
				}
		      return null;
		     }
		    protected void onPostExecute(Void v) {	
		    progressDialog.dismiss();
	//	    Toast.makeText(Update.this, result, Toast.LENGTH_LONG).show();
		    int curVersion = 0;
			try {
				curVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            int newVersion = Integer.valueOf(result.trim());
       
            /* Is a higher version than the current already out? */
            if (newVersion > curVersion) {
                /* Post a Handler for the UI to pick up and open the Dialog */
           	  
                mHandler.post(showUpdate);
            }  else 
            {
            	new AlertDialog.Builder(Update.this)
	            .setIcon(R.drawable.icon)
	            .setTitle("Congrats!!!")
	            .setMessage("Already Latest version!")
	          
	            .setNegativeButton("Continue....", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                            /* User clicked Cancel */
	                    	finish();
	                    }
	            })
	            .show();
	           }   	
            	
            	
            	
            	
            	
            }
       
		    }

	    
	    
	    
	    
	    
	    /* This Thread checks for Updates in the Background */
	    
	    /* This Runnable creates a Dialog and asks the user to open the Market */ 
	    private Runnable showUpdate = new Runnable(){
	           public void run(){
	            new AlertDialog.Builder(Update.this)
	            .setIcon(R.drawable.icon)
	            .setTitle("Update Available")
	            .setMessage("An update for is available!\\n\\nOpen Android Market and see the details?")
	            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                            /* User clicked OK so do some stuff */
	                           Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pname:com.sreekanth.duelist"));
	                            startActivity(intent);
	                    }
	            })
	            .setNegativeButton("No", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                            /* User clicked Cancel */
	                    	finish();
	                    }
	            })
	            .show();
	           }
	    };    
}
