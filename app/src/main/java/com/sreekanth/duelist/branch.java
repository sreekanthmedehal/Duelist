package com.sreekanth.duelist;





import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class branch extends Activity implements View.OnClickListener {
	  WebView myWebView;
	  ProgressDialog pd;
	  String j2;
	  private Button mainCloseButton;
	   boolean mCanceled;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_locator);
		myWebView = (WebView) findViewById(R.id.webView1);
		  AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Branch Locator\nOK to Continue,Cancel To Exit!!");
			alert.setMessage("Enter Place(AHM - AHMEDABAD");

			// Set an EditText view to get user input 
			final EditText input = new EditText(this);
			
			alert.setView(input);

			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			j2 = input.getText().toString();         
		
				setUpWidgets();
				setupBrowser("http://www.licindia.in/LICEPS/portlets/visitor/OfficeLocator/OfficeLocatorController.jpf");          
			}
			});
			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int whichButton) {
				    // Canceled.
					  
						branch.this.finish();
				  }
				});
				alert.show();	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doctor_locator, menu);
	
		
		
		
		return true;
	}

	
	
	private void setUpWidgets() {
		
		mainCloseButton = (Button) findViewById(R.id.button1);	
		mainCloseButton.setOnClickListener(this);
		
	}	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			 // Canceled.
			
			myWebView.clearCache(true);
			myWebView.clearHistory();
			myWebView.stopLoading();
			branch.this.finish();
			break;
			
			
		}		
		}	
	
	private void setupBrowser(String url) {
	
	   // myWebView.setVisibility(View.INVISIBLE);
		pd = new ProgressDialog(this);
		pd.setMessage("Please wait, your request is being processed...");
		pd.setIndeterminate(false);
		pd.setCancelable(true);
		pd.setButton("Cancel", new OnClickListener()
		        {
		        	public void onClick(DialogInterface dialog, int which)
		        	{
		                mCanceled = true;
		            	myWebView.clearCache(true);
						myWebView.clearHistory();
						myWebView.stopLoading();
		                branch.this.finish();
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
    //    myWebView.setVisibility(View.INVISIBLE);
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
		
		myWebView.setWebViewClient(new WebViewClient() {
			 
			 public void onPageStarted(WebView view, String url)
            {
            }
		    public boolean shouldOverrideUrlLoading (final WebView view, String url){
  	    	 Log.d("url", "onPage shouldOverrideUrlLoading the url: "+url);
			return false;
  	     } 

			//If no internet, redirect to error page
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Log.v(Constants.LOG_TAG, "Error: " + failingUrl);
			}
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				Log.v(Constants.LOG_TAG, "Finished: " + url);
				if (url.contains("OfficeLocatorController")) {
	//Toast.makeText(DoctorLocator.this, url, Toast.LENGTH_LONG).show();
		view.loadUrl("javascript: {" +
				 "document.getElementsByName(\"{actionForm.city}\")[0].value = '"+ j2 +"';" +
								  "document.getElementById(\"GetBranchForm\").submit();" +
			          " " +	"};");
		 
		
				 }
			
				if (url.contains("getBranchesByCity")) {
					  pd.dismiss();
					    view.setVisibility(View.VISIBLE);
				}
			 
			       			 
		}
		});
		myWebView.loadUrl(url);
	}	
	
	
}
