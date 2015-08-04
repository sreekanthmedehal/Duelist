package com.sreekanth.duelist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PortalCLIA extends Activity implements View.OnClickListener {

	private WebView myWebView;
	private MyWebChromeClient myWebChromeClient;
	private RelativeLayout childLayout;
	private RelativeLayout browserLayout;
	private Button mainCloseButton;
	private TextView titleText;
	 private Handler mHandler = new Handler();
	  final Context myApp = this; 
	  ProgressDialog pd;
	  String j2;
	String PUser;
	String PPwd;
	 private boolean mCanceled;
	/**
	 * @param savedInstanceState - saved data bundle from the system
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_portal);
		   Intent iin= getIntent();
	         Bundle b = iin.getExtras();
	         if(b!=null)
	         {
	              j2 =(String) b.get("PolicyNo");
	         }
	    	 final DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(PortalCLIA.this);
			 final SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
			 
			    String sql = "";
			    sql = "SELECT * FROM  registration";
			    Cursor a = database.rawQuery(sql, null);
			    a.moveToFirst();
			PPwd = a.getString(a.getColumnIndex("regcouponno")).trim();
		    PUser = a.getString(a.getColumnIndex("regagcode")).trim();
		    a.close();
		    database.close();
		    stockSQLHelper.close();	
			setUpWidgets();
			setupBrowser("https://customer.onlinelic.in/epslogin.htm");
		}
		

	/**
	 * Does all of the grunt work of setting up the app's main webview
	 */
	private void setupBrowser(String url) {
		myWebView = (WebView) findViewById(R.id.webview);
		 myWebView.setVisibility(View.INVISIBLE);
	//	 pd = ProgressDialog.show(Portal.this, "", "Please wait, your transaction is being processed...", true);
		 pd = new ProgressDialog(this);
			pd.setMessage("Please wait, your transaction is being processed...");
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
		                PortalCLIA.this.finish();
		//                mErrorMsg = "Canceled";
		                
		      //         if (mFos != null)
		      //          {
		    //            	try
		     //           	{
		     //                   mFos.close();
		      //              }
		    //            	catch (IOException e)
		  //              	{
		  //                  }
		   //             }
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

		myWebChromeClient = new MyWebChromeClient(PortalCLIA.this, childLayout, browserLayout, titleText);
		myWebView.setWebChromeClient(myWebChromeClient);

		myWebView.setWebViewClient(new WebViewClient() {
			 
			 public void onPageStarted(WebView view, String url)
             {
				
             }
		    public boolean shouldOverrideUrlLoading (final WebView view, String url){
   	    	 Log.d("url", "onPage shouldOverrideUrlLoading the url: "+url);
   	         if (url.contains("CLIAHome")) {
   	       // 				 mHandler.post(new Runnable() {
   	       //		             public void run() {
   	        		            
   	        	//  	   view.loadUrl("javascript:{" + "document.getElementsByTagName(\"a\")[26].href.click();" +	"};");
view.loadUrl("https://customer.onlinelic.in/LICEPS/appmanager/CLIA/CLIAHome?_nfpb=true&_pageLabel=cliaPolicyLookupPage");

//view.loadUrl("javascript: {" +
 //                    "document.getElementByName(\"PolicyLookupportlet_51_1{actionForm.policyNo}\")[0].value = 656868268;" +
 //                    " " +	"};");
   		    		
   	     //    }});
   	        				 
   	        
   	        				 
   	        				 
				return false;  }
				return false; } 

			//If no internet, redirect to error page
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Log.v(Constants.LOG_TAG, "Error: " + failingUrl);
			}

			public void onPageFinished(WebView view, String url) {
				Log.v(Constants.LOG_TAG, "Finished: " + url);
				 if (url.contains("cliaPolicyLookupPage")) {
			    		//		 mHandler.post(new Runnable() {
			    		//             public void run() {
			    		//    String polno = "656868268";        
//			      		 			

			view.loadUrl("javascript: {" +
			          "document.getElementById(\"cliaPolicyLookupPortlet_1{actionForm.policyNo}\").value = '"+j2 +"';" +
					  "document.getElementsByTagName(\"input\")[2].click();" +
			          " " +	"};");
                              
			    // }});	        
			 pd.dismiss();
	//		  view.setVisibility(View.VISIBLE);
							 } 
			       				 else if (url.contains("eps")){
			    	     	   String user=PUser;
			   			       String pwd=PPwd;
			   			 
			   		  	    view.loadUrl("javascript: {" +
			   		                "document.getElementsByName(\"portlet_5_6{actionForm.userName}\")[0].value = '"+user +"';" +
			   		                "document.getElementsByName(\"portlet_5_6{actionForm.password}\")[0].value = '"+pwd+"';" +
			   		                "document.getElementsByName(\"B1\")[0].click();"+
			   		  		
			   		          
			" " +	"};");
			   		  	    
			   		  	    
			
		}
			
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
						if (myWebChromeClient.isChildOpen()) {
							myWebChromeClient.closeChild();
						} else if (myWebView.canGoBack()) {
							myWebView.goBack();
						} else if(!myWebView.canGoBack()){
							myWebView.clearCache(true);
							myWebView.clearHistory();
							myWebView.stopLoading();
						    this.finish();
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
			case R.id.mainCloseButton:
				AlertDialog.Builder alert = new AlertDialog.Builder(this);

				alert.setTitle("Policy Status,OK to Continue,Cancel To Exit!!");
				alert.setMessage("Enter Policy Number");

				// Set an EditText view to get user input 
				final EditText input = new EditText(this);
				alert.setView(input);

				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
				  String value = input.getText().toString();
				  // Do something with value!
				  myWebView.loadUrl("javascript: {" +
				          "document.getElementById(\"PolicyLookupportlet_51_1{actionForm.policyNo}\").value = '"+value +"';" +
						  "document.getElementsByTagName(\"input\")[2].click();" +
				          " " +	"};");		  
			
				  }
				});

				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int whichButton) {
				    // Canceled.
					  myWebView.loadUrl("https://customer.onlinelic.in/LICEPS/Login/logout.do");
						myWebChromeClient.closeChild();
						myWebView.clearCache(true);
						myWebView.clearHistory();
						myWebView.stopLoading();
						PortalCLIA.this.finish();
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
}