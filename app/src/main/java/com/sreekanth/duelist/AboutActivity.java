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

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * This is the About activity in the dashboard application.
 * It displays some text and provides a way to get back to the home activity.
 *
 */

public class AboutActivity extends DashboardActivity implements View.OnClickListener 
{
	
	private WebView myWebView;
	private MyWebChromeClient myWebChromeClient;
	private RelativeLayout childLayout;
	private RelativeLayout browserLayout;
	private Button mainCloseButton;
	private TextView titleText;
	 private Handler mHandler = new Handler();
	  final Context myApp = this; 
	  ProgressDialog pd;
	String PAgcode;
	 private boolean mCanceled;
	 JIFace iface = new JIFace();
	 private Button mainCloseButton1;
	 private List<String> urls = new ArrayList<String>();
	/**
	 * @param savedInstanceState - saved data bundle from the system
	 */
		@Override
protected void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);

    setContentView (R.layout.activity_about);
    setTitleFromActivityLabel (R.id.title_text);
    final DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(AboutActivity.this);
	 final SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
	 
	    String sql = "";
	    sql = "SELECT * FROM  agentdata";
	    Cursor a = database.rawQuery(sql, null);
	    a.moveToFirst();
	PAgcode = a.getString(a.getColumnIndex("agcode")).trim();
   
   a.close();
   database.close();
   stockSQLHelper.close();	
    setUpWidgets();
//    wv.loadUrl("file:///android_asset/cssbutton.html");
//	setupBrowser("http://storage.googleapis.com/sunitha/csvertical.html");
    setupBrowser("file:///android_asset/csvertical.html");
}

		private void setupBrowser(String url) {
			myWebView = (WebView) findViewById(R.id.webview);
			 myWebView.setVisibility(View.INVISIBLE);
		//	 pd = ProgressDialog.show(Portal.this, "", "Please wait, your transaction is being processed...", true);
			 pd = new ProgressDialog(this);
				pd.setMessage("Please wait, your request is being processed...");
				pd.setIndeterminate(false);
			
			
			
				pd.setCancelable(true);
				 pd.setButton("Cancel", new OnClickListener()
			        {
			        	public void onClick(DialogInterface dialog, int which)
			        	{
			                mCanceled = true;
			                new Thread(new Task()).start();
			                new Thread(new Task()).start();
			            	myWebView.clearCache(true);
							myWebView.clearHistory();
							myWebView.stopLoading();
			                AboutActivity.this.finish();
		
			            }
			        });
	         pd.show();
			
			WebSettings settings = myWebView.getSettings();

			settings.setJavaScriptEnabled(true);
			settings.setJavaScriptCanOpenWindowsAutomatically(true);
			settings.setGeolocationEnabled(false);  // normally set true

			settings.setSupportMultipleWindows(true);

			//These database/cache/storage calls might not be needed, but just in case
			settings.setAppCacheEnabled(true);
			settings.setDatabaseEnabled(true);
			settings.setDomStorageEnabled(true);
			settings.setAppCachePath(getApplicationContext().getDatabasePath("myAppCache").getAbsolutePath());
			settings.setDatabasePath(getApplicationContext().getDatabasePath("myDatabase").getAbsolutePath()); //deprecated in Android 4.4 KitKat (API level 19)
			myWebView.addJavascriptInterface(iface, "droid");
			myWebChromeClient = new MyWebChromeClient(AboutActivity.this, childLayout, browserLayout, titleText);
			myWebView.setWebChromeClient(myWebChromeClient);

			myWebView.setWebViewClient(new WebViewClient() {
				 
				 public void onPageStarted(WebView view, String url)
	             {
					
	             }
			    public boolean shouldOverrideUrlLoading (final WebView view, String url){
	   	    	 Log.d("url", "onPage shouldOverrideUrlLoading the url: "+url);
	   	        
	   	        	if(url.contains("about")){
	   	        		AlertDialog.Builder alert = new AlertDialog.Builder(AboutActivity.this);
	   	        		PackageInfo pInfo = null;
						try {
							pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
						} catch (NameNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	   	        		String version = pInfo.versionName;
						alert.setTitle("Press OK  To Exit!!");
						alert.setMessage("Version :"+version);

						// Set an EditText view to get user input 
				//		final EditText input = new EditText(AboutActivity.this);
				//		alert.setView(input);

						alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
					//	  String value = input.getText().toString();
													
							//	myWebChromeClient.closeChild();
								myWebView.clearCache(true);
								myWebView.clearHistory();
								myWebView.stopLoading();
						
						  }
						});
						alert.show();
	   	        		return true;
	   	      
	   	        		}
	   	        	if(url.contains("Release")){
	   	        		myWebView.loadUrl("http://www.mssoftwaresolutions.in/portal/about-us/2-uncategorised/7-release-notes");
	   	        		myWebView.clearCache(true);
						myWebView.clearHistory();
						myWebView.stopLoading();
	   	        		return true;
	   	        	}
	   	        		return super.shouldOverrideUrlLoading(view, url);		 
	   	         }	 
	   	        				 

				//If no internet, redirect to error page
				public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
					Log.v(Constants.LOG_TAG, "Error: " + failingUrl);
				}

				public void onPageFinished(WebView view, String url) {
					Log.v(Constants.LOG_TAG, "Finished: " + url);
//					 if (url.contains("AgtPolStatusView")) {
				    		//		 mHandler.post(new Runnable() {
				    		//             public void run() {
				    		//    String polno = "656868268";        
//				      		 			

//				view.loadUrl("javascript: {" +
//				          "document.getElementById(\"PolicyLookupportlet_51_1{actionForm.policyNo}\").value = '"+j2 +"';" +
//						  "document.getElementsByTagName(\"input\")[2].click();" +
//				          " " +	"};");
	                              
				    // }});	
					 urls.add(0, url);
				if (url.contains("csvertical")){
	   	        		String ht = "javascript:window.droid.print(document.getElementsByTagName('html')[0].innerHTML);";
						view.loadUrl(ht);	   	        		
	   	        		
	   	        	}
				 pd.dismiss();
				  view.setVisibility(View.VISIBLE);
								 } 

			
	     
			});
		//    myWebView.reload();
			myWebView.loadUrl(url);
			
		}

		/**
		 * Overrides the back key handler
		 *
		 * @param keyCode - the pressed key identifier
		 * @param event   - the key event type
		 * @return - true if we handled the key pressed
		 */
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (myWebView != null) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					switch (keyCode) {
						case KeyEvent.KEYCODE_BACK:
						     if (urls.size() == 2) {
						    	 AboutActivity.this.finish();
						            }
						     else if (myWebChromeClient.isChildOpen()) {
							//	myWebChromeClient.closeChild();
								myWebView.clearCache(true);
								myWebView.clearHistory();
								myWebView.stopLoading();
								AboutActivity.this.finish();
							} else if (myWebView.canGoBack()) {
								myWebView.goBack();
								myWebView.clearCache(true);
								myWebView.clearHistory();
					//			myWebView.stopLoading();
					//		    AboutActivity.this.finish();
							} 
							else if(!myWebView.canGoBack()){
								myWebView.clearCache(true);
								myWebView.clearHistory();
								myWebView.stopLoading();
								AlertDialog.Builder alert = new AlertDialog.Builder(this);

								alert.setTitle("Press OK  To Exit!!");
						
								alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
							    AboutActivity.this.finish();
								  }
								});

								alert.show();
					
						
							}
							return true;
					}
				}
			}
			return super.onKeyDown(keyCode, event);
		}

		/**
		 * Handles the pressing of all buttons on the activity
		 *
		 * @param v - the view (button) which triggered the click
		 */
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.mainCloseButton1:
					AlertDialog.Builder alert = new AlertDialog.Builder(this);

					alert.setTitle("Press OK  To Exit!!");
			//		alert.setMessage("Enter");

					// Set an EditText view to get user input 
			//		final EditText input = new EditText(this);
			//		alert.setView(input);

					alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
			//		  String value = input.getText().toString();
					  // Do something with value!
//					  myWebView.loadUrl("javascript: {" +
//					          "document.getElementById(\"PolicyLookupportlet_51_1{actionForm.policyNo}\").value = '"+value +"';" +
//							  "document.getElementsByTagName(\"input\")[2].click();" +
//					          " " +	"};");		  
					  
					  
					  
					  
					  
					  
					  
					  
					  
	//				  }
//					});

//					alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//					  public void onClick(DialogInterface dialog, int whichButton) {
					    // Canceled.
						
					//		myWebChromeClient.closeChild();
							myWebView.clearCache(true);
							myWebView.clearHistory();
							myWebView.stopLoading();
							AboutActivity.this.finish();
					  }
					});

					alert.show();
		
			}}
		/**
		 * Button setup method
		 */
		private void setUpWidgets() {
			browserLayout = (RelativeLayout) findViewById(R.id.mainBrowserLayout);
			childLayout = (RelativeLayout) findViewById(R.id.mainAdChildLayout);
			titleText = (TextView) findViewById(R.id.mainTitleText);
			mainCloseButton = (Button) findViewById(R.id.mainCloseButton);
			mainCloseButton.setOnClickListener(this);
			mainCloseButton1 = (Button) findViewById(R.id.mainCloseButton1);	
			mainCloseButton1.setOnClickListener(this);
		}
		 class Task implements Runnable {
				
	         @Override

	         public void run() {

	             for (int i = 0; i <= 10; i++) {

	                 final int value = i;

	                 try {

	                     Thread.sleep(1000);

	                 } catch (InterruptedException e) {

	                     e.printStackTrace();

	                 }

	                

	  

	             }

	         }}
		 class JIFace {
				@JavascriptInterface
				public void print(String data) {
					data ="<html>"+data+"</html>";
				//	 System.out.println(data);
					 //DO the stuff
		
					 data = data.replaceAll("Agcode", PAgcode);
		 myWebView.loadData(data, "text/html", "UTF-8");
				}
		 }
	}