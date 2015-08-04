package com.sreekanth.duelist;


import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MetroStatus extends Activity implements OnClickListener{
	
	EditText mEdit;
	String magcode;
	String mpolno;


	private WebView webview;
	String url_select;	

	ProgressDialog pd;
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_metro_status);
		mEdit = (EditText)findViewById(R.id.editText1);
		 webview = (WebView)findViewById(R.id.webView1);
		
		Button submit = (Button)findViewById(R.id.button1);
		submit.setOnClickListener(this);
		submit.setText("Submit");
		Toast.makeText(MetroStatus.this, mpolno, Toast.LENGTH_LONG).show();
		DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(MetroStatus.this);
		SQLiteDatabase database = stockSQLHelper.getReadableDatabase(); 
		
		String sql = "";
		sql = "SELECT * from agentdata";
		Cursor c = database.rawQuery(sql, null);
		c.moveToFirst();
		if ((c.getCount()>0)){	
			 magcode = c.getString(c.getColumnIndexOrThrow("agcode"));
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.metro_status, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		  switch( ( v.getId())){
		  case R.id.button1: 
			  mpolno = mEdit.getText().toString();
			  url_select = "http://sreekanth.azurewebsites.net/policystat.php?qa="+magcode+"&qp="+mpolno;
			  setupBrowser(url_select);
	      break;
   default:
   break;
	}

}
/**************************************************************************************************/
	private void setupBrowser(String url) {
		
		
		
			pd = new ProgressDialog(this);
			pd.setMessage("Please wait, your transaction is being processed...");
			pd.setIndeterminate(false);
			pd.setCancelable(true);
			
			           
			       
	         pd.show();
			
			WebSettings settings = webview.getSettings();

			settings.setJavaScriptEnabled(true);
			settings.setJavaScriptCanOpenWindowsAutomatically(true);
			settings.setGeolocationEnabled(false);  // normally set true

			settings.setSupportMultipleWindows(true);

			//These database/cache/storage calls might not be needed, but just in case
			settings.setAppCacheEnabled(true);
			settings.setDatabaseEnabled(true);
			settings.setDomStorageEnabled(true);
			webview.setWebViewClient(new WebViewClient() {
				 
				 public void onPageStarted(WebView view, String url)
	             {
					
	             }
			    public boolean shouldOverrideUrlLoading (final WebView view, String url){
	   	    	 Log.d("url", "onPage shouldOverrideUrlLoading the url: "+url);
	 
					return false; } 

				//If no internet, redirect to error page
				public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
					Log.v(Constants.LOG_TAG, "Error: " + failingUrl);
				}

				public void onPageFinished(WebView view, String url) {
					super.onPageFinished(view, url);
					Log.v(Constants.LOG_TAG, "Finished: " + url);
					
				        pd.dismiss();
		
				
			}
			});
					webview.loadUrl(url);
		}
/**********************************************************************************************/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (webview != null) {
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				switch (keyCode) {
					case KeyEvent.KEYCODE_BACK:
						
						 if (webview.canGoBack()) {				
							webview.goBack();
						
						} else {
							webview.clearCache(true);
							webview.clearHistory();
							webview.stopLoading();
						    this.finish();
							
							
						}
						return true;
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}		
}
