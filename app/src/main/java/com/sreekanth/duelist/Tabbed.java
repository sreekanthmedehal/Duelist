																																																																																																									package com.sreekanth.duelist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.LocalActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class Tabbed extends ActivityGroup {
	private static int tabIndex = 0;
    private TabHost tabHost;
    private static int getsumy = 0;
    private static int getsumh = 0;
    private static int getsumq = 0;
    private static int getsumm = 0;
    private static int getsums = 0;
    private static int getsumsa = 0;
    private static int getmixing =0;
    private static int getmatsa = 0;
    String html;
    String strFile, address, subject, emailtext;
    ArrayList<TabHost.TabSpec> list = new ArrayList<TabHost.TabSpec>();
    ImageButton AddTab;
    Intent tabIntent;
    SharedPreferences preferences;
	SharedPreferences.Editor editor;
	 int maxTabs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tabbed);

	        tabHost =  (TabHost) findViewById(android.R.id.tabhost);
            tabHost.setup(this.getLocalActivityManager());
	      //  addTab();
            AddTab = (ImageButton)findViewById(R.id.add_tab);
	        AddTab.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	        String[] DayOfWeek = {"814", "815", "816","817", "818", "819", "820","821","822","823","825","826","827"};
	            	        LayoutInflater layoutInflater = 
	            		    (LayoutInflater)getBaseContext()
	            		    .getSystemService(LAYOUT_INFLATER_SERVICE);
	            		    View popupView = layoutInflater.inflate(R.layout.pop, null);
	            		    final PopupWindow popupWindow = new PopupWindow(
	            		    popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	            		    Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
	            		    Button btnSelect = (Button)popupView.findViewById(R.id.select);
	            		    final Spinner popupSpinner = (Spinner)popupView.findViewById(R.id.popupspinner);
	            		    ArrayAdapter<String> adapter = 
	            		    new ArrayAdapter<String>(Tabbed.this, 
	            		        android.R.layout.simple_spinner_item, DayOfWeek);
	            		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	            		    popupSpinner.setAdapter(adapter);
	            		    btnSelect.setOnClickListener(new Button.OnClickListener(){
		            		@Override
		            		public void onClick(View v) {
		            		tabIndex++;
		            	    addTab(popupSpinner.getSelectedItem().toString());
		            		popupWindow.dismiss();
		            		     }});
	            		    btnDismiss.setOnClickListener(new Button.OnClickListener(){
	            		    @Override
	            		    public void onClick(View v) {
	            		    popupWindow.dismiss();
	            		    }});
	            		    popupWindow.showAsDropDown(AddTab, 50, -30);
	                        }});	 
	           ((ImageButton)findViewById(R.id.btntotal)).setOnClickListener(new OnClickListener() {
	           public void onClick(View v) {
	//         String currentActivityId = getLocalActivityManager().getCurrentId();
	        	  
	  			   
	  			  
	        	
               getsumy=0; int j=0;getsumh=0;getsumq=0;getsumm=0;getsums=0;getsumsa=0;getmixing=0;maxTabs=0;getmatsa=0;
       //        WebView wv =  (WebView)findViewById(R.id.webView1);
 			    
	      //     TextView txttotal = (TextView)findViewById(R.id.txttotal);   	
            maxTabs = tabHost.getTabContentView().getChildCount();
// 	           for( j=1;j<maxTabs+1;j++){
	           
	           for(TabHost.TabSpec spec : list) {
	           Activity currentActivity = getLocalActivityManager().getActivity(spec.getTag());  
	           String mixing = ((BlankActvity) currentActivity).mixing();
	           if (mixing.equals("MIXING ON")){
	           getmixing++;
	           getsumy += ((BlankActvity) currentActivity).refreshcontenty();
	           getsumh += ((BlankActvity) currentActivity).refreshcontenth();
	           getsumq += ((BlankActvity) currentActivity).refreshcontentq();
	           getsumm += ((BlankActvity) currentActivity).refreshcontentm();
	           getsums += ((BlankActvity) currentActivity).refreshcontents();
	           getsumsa += ((BlankActvity) currentActivity).refreshcontentsa();
	     //      getmatsa += ((BlankActvity) currentActivity).refreshcontentmatsa();
	           }  
 	           }
	            String htmltot = "<html><body><table bgcolor=\"white\" border=1 style=\"width:30%;display:inline-block\">"
			    
	          + "<tr><td>" + "Total Plans" +"</td>" + "<td>" + String.valueOf(maxTabs) +"</td></tr>"
	           
	           + "<tr><td>" + "Mixing" +"</td>" + "<td>" + String.valueOf(getmixing) +"</td></tr>"
	           + "<tr><td>" + "Sum Assured" +"</td>" + "<td>" + String.valueOf(getsumsa) +"</td></tr>"
	           + "<tr><td>" + "Yly" +"</td>" + "<td>" + String.valueOf(getsumy) +"</td></tr>"
	           + "<tr><td>" + "Hly" +"</td>" + "<td>" + String.valueOf(getsumh) +"</td></tr>"
	           + "<tr><td>" + "Qly" +"</td>" + "<td>" + String.valueOf(getsumq) +"</td></tr>"
	           + "<tr><td>" + "Mly/ECS" +"</td>" + "<td>" + String.valueOf(getsumm) +"</td></tr>"
	           + "<tr><td>" + "SSS" +"</td>" + "<td>" + String.valueOf(getsums) +"</td></tr>"
	       //    + "<tr><td>" + "Mat Sa" +"</td>" + "<td>" + String.valueOf(getmatsa) +"</td></tr>"
	            + "</body></html>";
	            
	            
	            
	            final Dialog dialog = new Dialog(Tabbed.this);
                // Include dialog.xml file
	            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
       	        Window window = dialog.getWindow();
       	        window.setGravity(Gravity.BOTTOM|Gravity.RIGHT);
       	        window.setLayout(LayoutParams.WRAP_CONTENT ,LayoutParams.WRAP_CONTENT);
                dialog.setContentView(R.layout.web_dailog);
                // Set dialog title
	            WebView wb = (WebView) dialog.findViewById(R.id.webview);
	            wb.loadData(htmltot, "text/html", "UTF-8");
  			    
  			    dialog.setCancelable(true);
  			    Button close  = (Button)dialog.findViewById(R.id.button1);
  			    close.setOnClickListener(new OnClickListener() {
                  @Override
                  public void onClick(View v) {
			      dialog.cancel();     
				 }
  			     });
        	     dialog.show();  	            
                 }
	             });
	           ((ImageButton)findViewById(R.id.btnloaddata)).setOnClickListener(new OnClickListener() {
	        		public void onClick(View v) {   
	           startActivity (new Intent(getApplicationContext(), Calcstore.class));    
	        		 }
	             });
	((ImageButton)findViewById(R.id.btnquot)).setOnClickListener(new OnClickListener() {
	public void onClick(View v) {           
	int j=0;   html=""; maxTabs=0;  
	 maxTabs = tabHost.getTabContentView().getChildCount();
 //   for( j=1;j<maxTabs+1;j++){
    	 for(TabHost.TabSpec spec : list) {
    Activity currentActivity = getLocalActivityManager().getActivity(spec.getTag());  
    String mixing = ((BlankActvity) currentActivity).mixing();
    if (mixing.equals("MIXING ON")){
html += "<html><body><table bgcolor=\"white\" border=1 style=\"width:30%;display:inline-block\">";
html+= "<tr><td colspan=\"2\">"+  spec.getTag() +"</td></tr>"; 
html +="<tr><td>" + "Name" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontentname() +"</td></tr>" 
+ "<tr><td>" + "Plan" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontentplan() +"</td></tr>"    			 
+ "<tr><td>" + "Term" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontentterm() +"</td></tr>"    			 
+ "<tr><td>" + "SA" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontentsa() +"</td></tr>"	
+ "<tr><td>" + "DAB" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontentsa() +"</td></tr>"	
+ "<tr><td>" + "Ylyprem" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontenty() +"</td></tr>"
+ "<tr><td>" + "Hlyprem" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontenth() +"</td></tr>"
+ "<tr><td>" + "Qlyprem" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontentq() +"</td></tr>"
+ "<tr><td>" + "Mlyprem" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontentm() +"</td></tr>"
+ "<tr><td>" + "SSSprem" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontents() +"</td></tr>"
+ "<tr><td>" + "Maturity Benefit" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontentmatsa() +"</td></tr>"
+ "<tr><td>" + "Bonus" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontentbonus() +"</td></tr>"
+ "<tr><td>" + "FAB" +"</td>" + "<td>" + " " +"</td></tr>"
+ "<tr><td>" + "Total" +"</td>" + "<td>" + ((BlankActvity) currentActivity).refreshcontenttotal() +"</td></tr>"
+ "</body></html>";
      }   
      }
		           AlertDialog.Builder builder1 = new AlertDialog.Builder(Tabbed.this);
		           WebView wv = new WebView(getApplicationContext());		           
		           wv.loadData(html, "text/html", "UTF-8");
		           builder1.setView(wv);        
		           builder1.setTitle("Summary - Report - Plans");
		           builder1.setCancelable(true);		           
		           builder1.setNeutralButton("Continue....",
		           new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                   dialog.cancel();
		                   gmail();     
		               }
		               });
		           AlertDialog alert11 = builder1.create();
		           alert11.show();
		              }
		              });   
	        ((ImageButton)findViewById(R.id.btndelete)).setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	               removeTab();
	            }
	            });     
	            }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tabbed, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}else if (id == R.id.album_loaddata){
	//		int maxTabs = tabHost.getTabContentView().getChildCount();
		    startActivity (new Intent(getApplicationContext(), Calcstore.class));
		    
		 return true;
		}
		return super.onOptionsItemSelected(item);
	    }
	@SuppressLint("InflateParams")
	public void addTab(String plan){
        LayoutInflater layoutInflate = LayoutInflater.from(Tabbed.this);
        Button tabBtn = (Button)layoutInflate.inflate(R.layout.tab_event, null);
       
        if (plan.equals("814")){
         tabIntent = new Intent(Tabbed.this, BlankActvity.class);
        }else{
        	 tabIntent = new Intent(Tabbed.this, BlankActvity.class);	
        }
       preferences = PreferenceManager.getDefaultSharedPreferences(this);
       editor = preferences.edit();
       editor.putString("TPlan",plan);
       editor.apply();
       tabBtn.setText("PLAN "+plan);
       setupTab(tabBtn, tabIntent,tabIndex + "- PLAN "+plan);
    }
    protected void setupTab(View tabBtn, Intent setClass,String tag) {
        TabSpec setContent = tabHost.newTabSpec(tag).setIndicator(tabBtn).setContent(setClass);
        tabHost.addTab(setContent);
        tabHost.setCurrentTab(tabIndex-1);
        list.add(setContent);
    }
    protected void removeTab(){
    	  int currentindex= tabHost.getCurrentTab();
  //  tabHost.getTabWidget().removeView(tabHost.getTabWidget().getChildTabViewAt(tabHost.getCurrentTab()));
    	  tabHost.setCurrentTab(0);
    	  tabHost.getTabWidget().removeView(tabHost.getTabWidget().getChildTabViewAt(currentindex));
    	  list.remove(currentindex); // remove it from memory
    //destroy("PLAN "+currentindex);
    	  
   //   tabHost.clearAllTabs();  // clear all tabs from the tabhost
    	 int nTabIndex = 1;
    	 for(TabHost.TabSpec spec : list) {// add all that you remember back
    		 tabHost.getTabWidget().getChildTabViewAt(nTabIndex);
    	 spec.setIndicator("PLAN "+nTabIndex);
    	 tabHost = new TabHost(Tabbed.this);
    	 tabHost.setup(this.getLocalActivityManager());
    	 tabHost.addTab(spec);
    	 tabHost.setCurrentTab(nTabIndex++);}
    	 tabIndex=nTabIndex-1;
    }
    public boolean destroy(String id) {
		final LocalActivityManager activityManager = getLocalActivityManager();
		if(activityManager != null){
			activityManager.destroyActivity(id, false);
			// https://code.google.com/p/android/issues/detail?id=12359
			// http://www.netmite.com/android/mydroid/frameworks/base/core/java/android/app/LocalActivityManager.java
			try {
				final Field mActivitiesField = LocalActivityManager.class.getDeclaredField("mActivities");
				if(mActivitiesField != null){
					mActivitiesField.setAccessible(true);
					@SuppressWarnings("unchecked")
					final Map<String, Object> mActivities = (Map<String, Object>)mActivitiesField.get(activityManager);
					if(mActivities != null){
						mActivities.remove(id);
					}
					final Field mActivityArrayField = LocalActivityManager.class.getDeclaredField("mActivityArray");
					if(mActivityArrayField != null){
						mActivityArrayField.setAccessible(true);
						@SuppressWarnings("unchecked")
						final ArrayList<Object> mActivityArray = (ArrayList<Object>)mActivityArrayField.get(activityManager);
						if(mActivityArray != null){
							for(Object record : mActivityArray){
								final Field idField = record.getClass().getDeclaredField("id");
								if(idField != null){
									idField.setAccessible(true);
									final String _id = (String)idField.get(record);
									if(id.equals(_id)){
										mActivityArray.remove(record);
										break;
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		    }
		return false;
	        }

    private void gmail(){
//888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    			try {
    				strFile = Environment.getExternalStorageDirectory()
    				.getAbsolutePath() + "/temp";
    				File file = new File(strFile);
    				if (!file.exists())
    				file.mkdirs();
    				strFile = strFile + "/report.html";
    				createFile();
    			//	Log.i(getClass().getSimpleName(), "send  task - start");
    				final Intent emailIntent = new Intent(
    				android.content.Intent.ACTION_SEND);
    				address = "";
    				subject = "Premium Quotation";
    				emailtext =  "Please download the attached html file";
    				emailIntent.setType("text/html");
    				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
    				new String[] { address });
    				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
    				emailIntent.putExtra(Intent.EXTRA_STREAM,
    				Uri.parse("file:" + strFile)); 
    				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,  emailtext);
    				emailIntent.setType("message/rfc822");
    				startActivity(Intent
    				.createChooser(emailIntent, "Send mail..."));
    				 
    				} catch (Throwable t) {
    				Toast.makeText(Tabbed.this, "Request failed: " + t.toString(),
    				Toast.LENGTH_LONG).show();
    				}		
    			    }
//888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888    
    	private void createFile() {
    	try {
    	String data = "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/><html><body style='tab-interval: .5in'><div class=\"Section1\"><div>";
    	data += "<p class=\"MsoNormal\" dir=\"LTR\" style=\"text-align: left; unicode-bidi: embed\"><span lang=\"AR-EG\">";
    	FileOutputStream fos = new FileOutputStream(strFile);

    	Writer out = new OutputStreamWriter(fos, "UTF-8");

    	/*data += "Email data" + "<br />";
    	data += "bla bla bla" + "<br />";
    	data += "Footer" + "<br />";

    	data += "</span></p></div></body></html>";*/
    	data = html;
    	out.write(data);
    	out.flush();
    	out.close();

    	} catch (Throwable t) {
    	Toast.makeText(this, "Request failed: " + t.toString(),
    	Toast.LENGTH_LONG).show();
    	}
    	}
    	
    

}

