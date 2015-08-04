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
import android.os.Looper;
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
import android.widget.Toast;


public class PortalPolList extends Activity implements View.OnClickListener {

	private WebView myWebView;
	private MyWebChromeClient myWebChromeClient;
	private RelativeLayout childLayout;
	private RelativeLayout browserLayout;
	private Button mainCloseButton;
	private Button mainCloseButton1;
	private Button DownloadButton;
	private TextView titleText;
	 private Handler mHandler = new Handler();
	  final Context myApp = this; 
	  ProgressDialog pd;
	  String j2;
	String PUser;
	String PPwd;
	 private boolean mCanceled;
	 JIFace iface = new JIFace();
	 JIFace1 iface1 = new JIFace1();
	 int noofpages;
	 String html;
	 String ind;
	 String ht;
	 int i =1;int j = 1;
	 String html1=" ";
	 int value= 0;
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
	    	 final DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(PortalPolList.this);
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
			setupBrowser("https://customer.onlinelic.in/LICEPS/Login/begin.do");
		}
		

	/**
	 * Does all of the grunt work of setting up the app's main webview
	 */
	private void setupBrowser(String url) {
		myWebView = (WebView) findViewById(R.id.webview);
		myWebView.setVisibility(View.INVISIBLE);
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
		                PortalPolList.this.finish();
		            }
		        });
         pd.show();
		
		WebSettings settings = myWebView.getSettings();
		settings.setAllowUniversalAccessFromFileURLs(true);
		settings.setJavaScriptEnabled(true);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setGeolocationEnabled(false);  // normally set true
		settings.setSupportMultipleWindows(true);
		//These database/cache/storage calls might not be needed, but just in case
		settings.setAppCacheEnabled(true);
		myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		settings.setDatabaseEnabled(true);
		settings.setDomStorageEnabled(true);
		settings.setLoadsImagesAutomatically(false);
		settings.setAppCachePath(getApplicationContext().getDatabasePath("myAppCache").getAbsolutePath());
		settings.setDatabasePath(getApplicationContext().getDatabasePath("myDatabase").getAbsolutePath()); //deprecated in Android 4.4 KitKat (API level 19)
		myWebView.addJavascriptInterface(iface, "droid");
		myWebView.addJavascriptInterface(iface1, "pages");
		
		myWebChromeClient = new MyWebChromeClient(PortalPolList.this, childLayout, browserLayout, titleText);
		myWebView.setWebChromeClient(myWebChromeClient);

		myWebView.setWebViewClient(new WebViewClient() {	 
			 public void onPageStarted(WebView view, String url){
				super.onPageStarted(view, url, null);
				 Log.d("url", "onPage started the url: " + url);
             }
		    public boolean shouldOverrideUrlLoading (final WebView view, String url){
   	    	 Log.d("url", "onPage shouldOverrideUrlLoading the url: " + url);

                    return false;
              }
				 

			//If no internet, redirect to error page
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Log.v(Constants.LOG_TAG, "Error: " + failingUrl);
			}

			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				Log.v(Constants.LOG_TAG, "Finished: " + url);

				 if (url.equals("https://customer.onlinelic.in/LICEPS/appmanager/Agent/AgentHome")) {
                     view.loadUrl("javascript:{" + "var lastClickTime =   window.lastEventTimestamp || 1;var current = new Date().getTime();var event = document.createEvent('MouseEvents');var link = document.getElementsByTagName('a')[26];var delta=1;" + "};");
                     view.loadUrl("javascript: (function() { " +  "event.initEvent('click', {'view': window,'bubbles': true,'cancelable': true});      if (delta >1 ) {event.stopImmediatePropagation();}else {link.dispatchEvent(event); };lastClickTime = current;" + "})()");
							 pd.dismiss(); 
				 }
				 else if (url.contains("AgtPolSearch")) {
		   	//
				 	 view.loadUrl("javascript: {" + 
		   		"document.getElementsByName(\"Agnet_Pol_Search_portlet_43_2wlw-radio_button_group_key:{actionForm.option}\")[2].click();" +
	            "document.getElementsByName(\"Agnet_Pol_Search_portlet_43_2{actionForm.fromDOC}\")[0].value = '01/04/2012';" +
	            "document.getElementsByName(\"Agnet_Pol_Search_portlet_43_2{actionForm.toDOC}\")[0].value = '31/03/2015';" +		   		         
	  		    "document.getElementsByName(\"getAgencyCodeForm1\")[0].submit();" +

		" " +	"};"); 	
				 	 pd.dismiss();
					  view.setVisibility(View.VISIBLE);
				 }
                else if (url.contains("FgetAgencyCode")) {
                    //		 mHandler.post(new Runnable() {
                    //             public void run() {
                    String ht1 = "javascript:window.pages.print1(document.getElementsByClassName(\"pagingLabel\")[1].innerHTML);";
                    view.loadUrl(ht1);





                    //	  view.setVisibility(View.VISIBLE);

                }
				 else if (url.contains("Login")){
   					 pd.dismiss();
					 view.loadUrl("javascript: function waitForjQuery() {"+
							 " if (typeof jQuery != 'undefined') {" +
                             // do some stuff

                             "} else {" +
                             " window.setTimeout(function () { waitForjQuery(); }, 100);" +
                             "}" +
                             " " + "};");


                     view.loadUrl("javascript: { "

                             + "var script=document.createElement('script'); "
                             + " script.setAttribute('type','text/javascript'); "
							 + " script.setAttribute('src', '//code.jquery.com/jquery-1.11.3.js'); "
							 + "document.getElementsByTagName(\"head\")[0].appendChild(script);"
							 +  "waitForjQuery();" +

							 " " +	"};");
	     	     String user=PUser;
			     String pwd=PPwd;
			 
		  	     view.loadUrl("javascript: {" +
		                "document.getElementsByName(\"{actionForm.userName}\")[0].value = '"+user +"';" +
		                "document.getElementsByName(\"{actionForm.password}\")[0].value = '"+pwd+"';" +
		                "var msg = document.getElementsByClassName(\"inputFieldLabel\")[2].innerHTML;" +
		               // "alert(msg);" +
		                "var fy_string = prompt(msg,\"\");" +
		        
		              "document.getElementsByName(\"{actionForm.qreply}\")[0].value = fy_string;" +
		              "document.getElementsByClassName(\"button\")[0].click();" +
		             " " +	"};");
			   		  	    
		}
		}
		});
		 
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
							myWebView.loadUrl("https://customer.onlinelic.in/LICEPS/Login/logout.do");
							new Thread(new Task()).start();
							new Thread(new Task()).start();
							myWebChromeClient.closeChild();
							myWebView.clearCache(true);
							myWebView.clearHistory();
							myWebView.stopLoading();
						    this.finish();
						} else if (myWebView.canGoBack()) {
							myWebView.loadUrl("https://customer.onlinelic.in/LICEPS/Login/logout.do");
							new Thread(new Task()).start();
							new Thread(new Task()).start();
							myWebView.goBack();
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
			case R.id.mainCloseButton1:
	              displayalert(html1);
			break;
            case R.id.download:
                DownloadButton.setText(j + "/" + String.valueOf(noofpages));
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    public void run() {
/*                   "var dataTable = $('table.commonTableBorder');"+
					 "$(dataTable).each(function(){" +
					 "var $td= $(this).closest('tr').children('td');" +
					 "var sr= $td.eq(0).text();" +
					 "alert(sr);"+
							 "});" +*/
                        myWebView.loadUrl("javascript: {" +
                                "document.getElementById(\"Agnet_Pol_Search_portlet_43_2{actionForm.goToPage}\").value ='" + j + "';" +
                           //     "document.getElementById(\"Agnet_Pol_Search_portlet_43_2go\").click();" +
                                        "document.getElementsByTagName('input')[5].click();" +
                                "  " + "};");
                        ht = "javascript:window.droid.print(document.getElementsByTagName('html')[0].innerHTML);";
                        myWebView.loadUrl(ht);
                        j++;
                        if (j == noofpages+1) {
                            DownloadButton.setEnabled(false);
                        }
                    }
                });
                break;
				}
		}

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
		DownloadButton = (Button) findViewById(R.id.download);
        DownloadButton.setOnClickListener(this);
   //     DownloadButton.setVisibility(View.GONE);

	}
	class JIFace {
		@JavascriptInterface
		public void print(String data) {
	//		data =        Normalizer.normalize(data, Normalizer.Form.NFD).replaceAll("[^a-zA-Z0-9+.-/]", " ");
			data ="<html>"+data+"</html>";
		//	 System.out.println(data);
			 //DO the stuff
			 //int position = data.indexOf("<span style=\"functionalHeader\">Lapsed/Revival Details</span>"); 
			 int position = data.indexOf("Agent Policy Search");
			 int position1 = data.indexOf("Go back to Input Page");
 //System.out.println(j2 + "------------------------------------------------------->" + String.valueOf(position));
 html =  data.substring(position+200,position1-120);
 html = html.replace("commonTableBorder", "1");
 html = html.replace("class", "border");
 html1 = html1 + html;


/* StringBuilder sb= new StringBuilder();
 Document     doc = Jsoup.parse(html, "", Parser.xmlParser());
 Elements element=doc.select("td");
 for(Element ele:element){
String normal =        Normalizer.normalize(ele.text(), Normalizer.Form.NFD).replaceAll("[^a-zA-Z0-9+.-/]", " ");
sb.append(normal);
sb.append(":");//text() to print in between the text //text() to print in between the text 
 }
String html = sb.toString();
  String [] items = html.split(":");
  List<String> container = Arrays.asList(items);
  for (int i = 0; i < container.size(); i++) {
		System.out.println(container.get(i));
	}*/
	}
	}
public void displayalert(String hhhhh){
  AlertDialog.Builder alert = new AlertDialog.Builder(this); 
  alert.setTitle("Title here");
  WebView wv = new WebView(this);
  wv.loadData(hhhhh, "text/html", "UTF-8");	
  alert.setView(wv);
  alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
      dialog.dismiss();
      }
  });
  alert.show();
 }
 
 
class JIFace1 {
    Context mContext;

    void JavaScriptInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public int print1(String data) {
        noofpages = Integer.parseInt(data);
        Toast.makeText(PortalPolList.this, "No.Of Pages " + String.valueOf(noofpages), Toast.LENGTH_SHORT).show();

        DownloadButton.setText(String.valueOf(noofpages));
        DownloadButton.setVisibility(View.VISIBLE);

       /* DownloadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //DO SOMETHING! {RUN SOME FUNCTION ... DO CHECKS... ETC}

                DownloadButton.setText(j + "/" + String.valueOf(noofpages));

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    public void run() {
*//*                   "var dataTable = $('table.commonTableBorder');"+
					 "$(dataTable).each(function(){" +
					 "var $td= $(this).closest('tr').children('td');" +
					 "var sr= $td.eq(0).text();" +
					 "alert(sr);"+
							 "});" +*//*
                        myWebView.loadUrl("javascript: {" +
                                "document.getElementById(\"Agnet_Pol_Search_portlet_43_2{actionForm.goToPage}\").value ='" + j + "';" +
                                "document.getElementById(\"Agnet_Pol_Search_portlet_43_2go\").click();" +
                                "  " + "};");
                        ht = "javascript:window.droid.print(document.getElementsByTagName('html')[0].innerHTML);";
                        myWebView.loadUrl(ht);
                        j++;
                        if (j == noofpages) {
                            DownloadButton.setEnabled(false);
                        }
                    }
                });
            }
        });*/

        return noofpages;
    }


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
		         }
		     }
	
}