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



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/**
 * This is a simple activity that demonstrates the dashboard user interface pattern.
 *
 */

public class HomeActivity extends DashboardActivity 
{
	 String ipadd;
	//Button yourButton;
	 String ragcode;
	 String result = "";
	 String rdate;
	 ContentValues values;
	 Button yourButton;
	 String activated;
	  String agcode;
		String agname;
		String mobile;
		EditText fagcode;
		EditText fagname;
		EditText fmobile; 
		String nagcode;
	  	String nagname;
	   	String nmobile;
		
	   	GridView gridView;	
	   	static final String[] MOBILE_OS = new String[] { 
			"Add Policy", "info","Policy Status", "Policy List","Revival Calculation","Settings","Submit Feedback","Surrender","Utilities","Ask Lic","Downloads","Buy Online","Doctor Locator","Updates","Add Person","Agency Portal","CLIA Portal","Office Locator","NAV","Developer Site" };
	   	private ToggleButton toggleButtongv;
	    private ExpandListAdapter ExpAdapter;
	    private ArrayList<Group> ExpListItems;
	    private ExpandableListView ExpandList;
	  
/**
 * onCreate - called when the activity is first created.
 * Called when the activity is first created. 
 * This is where you should do all of your normal static set up: create views, bind data to lists, etc. 
 * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
 * 
 * Always followed by onStart().
 *
 */

protected void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    
    PackageInfo pInfo = null;
	try {
		pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
	} catch (NameNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		String version = pInfo.versionName;
    ((TextView)findViewById(R.id.verno)).setText("Version : " +version); 
    
    
    
    
    if (getIntent().getBooleanExtra("EXIT", false)) {
        finish();
    }
     DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(HomeActivity.this);
    SQLiteDatabase database = stockSQLHelper.getReadableDatabase(); 
		 String sql = "select * from registration";
	         final Cursor a = database.rawQuery(sql, null);
	         a.moveToFirst();
	         if (a.getCount()>0){
	   ipadd = a.getString(a.getColumnIndex("regmodel")).trim();
	   ragcode = a.getString(a.getColumnIndex("regagcode")).trim();
	   activated = a.getString(a.getColumnIndexOrThrow("regactivated"));
       database.close();
      a.close();
       stockSQLHelper.close();
	 }
//	         	 switch (a.getCount()){
//	 case 1:
		 if (ipadd.equals("Pro") && activated.equals("Y") && !(ipadd.equals("x"))){
			
			 Button feature6 = (Button) findViewById(R.id.home_club);
			   
			   feature6.setVisibility(View.VISIBLE);
			   
//		        Button yourButton = new Button(this);
  yourButton = (Button)findViewById(R.id.home_getpro);
//   ViewGroup linearLayout = (ViewGroup) findViewById(R.id.GetPro);
//   yourButton.setText("Get Pro");
//   yourButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 
//                                       LayoutParams.WRAP_CONTENT));
//   linearLayout.addView(yourButton);
   yourButton.setOnClickListener(new View.OnClickListener() {

	    public void onClick(View v) {
	    	
	    
   Toast.makeText(HomeActivity.this, "Already Pro Version.....", Toast.LENGTH_LONG).show();    	
	  
	    }
	});	   		   
		 }
		 else {

			 Button feature6 = (Button) findViewById(R.id.home_club);
	

			    feature6.setVisibility(View.GONE); 
			    yourButton = (Button)findViewById(R.id.home_getpro);
			    yourButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
				    	
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

				    //     String result = "Database connection success\n";
				         Statement st = (Statement) con.createStatement();
				            ResultSet rs = st.executeQuery("select updtype,startdate,activated from subscription WHERE sagcode = '" + ragcode + "';");
				         
				         //      ResultSetMetaData rsmd = rs.getMetaData();

				               while(rs.next()) {
				               	result = rs.getString("updtype");
				               	rdate = rs.getString("startdate");
			                    activated = rs.getString("activated");
				               }
				      }
				     catch(Exception e) {
				         e.printStackTrace();
				     }   
					DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(HomeActivity.this);
					SQLiteDatabase database = stockSQLHelper.getReadableDatabase(); 
					values = new ContentValues();
				   	values.put("regmodel", result);
				   	values.put("startdate", rdate);
				int rowsUpdate =     database.update("registration", values, null,null);
				
				
				
				
				 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							HomeActivity.this);
				if (result.equals("Pro")){
							// set title
							alertDialogBuilder.setTitle("Successful!");}
							else {
								alertDialogBuilder.setTitle("You are not entitled to Pro,contact developer!");    	
									    }
							// set dialog message
							alertDialogBuilder
								.setMessage("Click OK to exit!")
								.setCancelable(false)
								.setPositiveButton("OK",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, close
										// current activity
										Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
										intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
										intent.putExtra("EXIT", true);
										startActivity(intent);
									
									}
								  });
								

								// create alert dialog
								AlertDialog alertDialog = alertDialogBuilder.create();

								// show it
								alertDialog.show();
				    }
				});	   

		 }
		 

			 
				
			 
		  if (ragcode.trim().length()==0 && activated.trim().length()==0){ 			  
		//  Toast.makeText(HomeActivity.this, "First Step  Register .........", Toast.LENGTH_LONG).show();
		//  startActivity (new Intent(getApplicationContext(), Feedback.class));	
		  final Dialog dialog = new Dialog(HomeActivity.this);
			dialog.setContentView(R.layout.activity_feedback);
			dialog.setTitle("Please submit your details...");
			 fagcode = (EditText) dialog.findViewById(R.id.fbagcode);
			 fagname = (EditText)dialog.findViewById(R.id.fbagname);
			 fmobile = (EditText)dialog.findViewById(R.id.fbmobile);
			Spinner ftopic = (Spinner)dialog.findViewById(R.id.fbtopic);
			EditText fmessage = (EditText)dialog.findViewById(R.id.fbmessage);
			ftopic.setVisibility(View.GONE);
			fmessage.setVisibility(View.GONE);
			TextView fyq = (TextView)dialog.findViewById(R.id.yq);
			TextView fst = (TextView)dialog.findViewById(R.id.st);
			fyq.setVisibility(View.GONE);
			fst.setVisibility(View.GONE);
			
			
			
			
			Button OKButton = (Button) dialog.findViewById(R.id.btnOK);
			Button CancelButton = (Button) dialog.findViewById(R.id.btnCancel);
			CancelButton.setVisibility(View.GONE);
			OKButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					agcode = fagcode.getText().toString();
		        	agname = fagname.getText().toString();
		        	mobile = fmobile.getText().toString();
					 
					if ( mobile.length()==0){
	            		fmobile.setError("Mobile Number required");
	            	}
					 if ( agname.length()==0){
	            		fagname.setError("Name required");
	            	}
					 if ( agcode.length()==0){
	            		fagcode.setError("Code No required");
	            	}
					else {
						
						
						
						
						
						
						
						
						
						
						 new task().execute(fagcode.getText().toString().trim());		
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					dialog.dismiss();
					}
				}
			});
			dialog.show();

		  }
		  toggleButtongv = (ToggleButton) findViewById(R.id.toggleButton1);
			//	toggleButtonmix.setChecked(false);
				toggleButtongv.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					   @Override
					   public void onCheckedChanged(CompoundButton buttonView,
					     boolean isChecked) {
					    
					    if(isChecked){
					    ((LinearLayout)findViewById(R.id.llv)).setVisibility(View.GONE);
					    ((LinearLayout)findViewById(R.id.llv1)).setVisibility(View.GONE);
					    ((LinearLayout)findViewById(R.id.llv2)).setVisibility(View.GONE);
					    ((LinearLayout)findViewById(R.id.lgv)).setVisibility(View.VISIBLE);
					    }else{
					    	 ((LinearLayout)findViewById(R.id.llv)).setVisibility(View.VISIBLE);
					    	 ((LinearLayout)findViewById(R.id.llv1)).setVisibility(View.VISIBLE);
					    	 ((LinearLayout)findViewById(R.id.llv2)).setVisibility(View.VISIBLE);
							    ((LinearLayout)findViewById(R.id.lgv)).setVisibility(View.GONE);
					    }

					   }
					  });
		  gridView = (GridView) findViewById(R.id.gridView1);
		  gridView.setAdapter(new ImageAdapter1(this, MOBILE_OS));
		  
			gridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {
			String gvpressed = 		 ((TextView) v.findViewById(R.id.grid_item_label)).getText().toString().trim();
					/*Toast.makeText(
					   getApplicationContext(),
					   ((TextView) v.findViewById(R.id.grid_item_label))
					   .getText(), Toast.LENGTH_SHORT).show();*/
					if (gvpressed.equals("Add Policy")) {
	            		 startActivity (new Intent(getApplicationContext(), AboutAdd.class));
	    			}        	
					if (gvpressed.equals("info")) {
						startActivity (new Intent(getApplicationContext(), AboutActivity.class));
	    			} 
					
					/*"Add Policy", "info","Policy Status", "Policy List","Revival Calculation",
					"Settings","Submit Feedback","Surrender","Utilities","Ask Lic","Downloads","Buy Online",
					"Doctor Locator","Updates","Agency Portal",
					"CLIA Portal","Office Locator","NAV","Developer Site"*/					
					if (gvpressed.equals("Policy Status")) {
						startActivity (new Intent(getApplicationContext(), FrmActivity.class));
	    			} 
					if (gvpressed.equals("Policy List")) {
						startActivity (new Intent(getApplicationContext(), PmActivity.class));
	    			} 
					if (gvpressed.equals("Revival Calculation")) {
						startActivity (new Intent(getApplicationContext(), RevActivity.class));
	    			} 
					if (gvpressed.equals("Settings")) {
						Intent intent8 = new Intent(getApplicationContext(),SettingsTheme.class);
				    	startActivity(intent8);
	    			} 
					if (gvpressed.equals("Submit Feedback")) {
						startActivity (new Intent(getApplicationContext(), Feedback.class));
	    			} 
					if (gvpressed.equals("Surrender")) {

						 AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this); 
						  alert.setTitle("Surrender Quotation");

						 
						  

						  alert.setMessage("Coming Soon");
						  alert.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
						      @Override
						      public void onClick(DialogInterface dialog, int id) {
						      dialog.dismiss();
						      }
						  });
						  alert.show();	
	    			} 
					if (gvpressed.equals("Utilities")) {
						startActivity (new Intent(getApplicationContext(), F7Activity.class));
	    			} 
					if (gvpressed.equals("Ask Lic")) {
						startActivity (new Intent(getApplicationContext(), ASKLICSMS.class));
	    			} 
					if (gvpressed.equals("Downloads")) {
						 Intent intent = new Intent();
						   intent.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
						   startActivity(intent);
	    			} 
					if (gvpressed.equals("Buy Online")) {
				//		startActivity (new Intent(getApplicationContext(), AboutActivity.class));
	    			} 
					if (gvpressed.equals("Doctor Locator")) {
						startActivity (new Intent(getApplicationContext(), DoctorLocator.class));
	    			} 
					
					if (gvpressed.equals("Updates")) {
						Intent uIntent = new Intent(getApplicationContext(),Update.class);  	  
					  	startActivity(uIntent); 
	    			} 
					if (gvpressed.equals("Agency Portal")) {
						startActivity (new Intent(getApplicationContext(), F3Activity.class));
	    			} 
					if (gvpressed.equals("CLIA Portal")) {
	//					startActivity (new Intent(getApplicationContext(), AboutActivity.class));
	    			} 
					if (gvpressed.equals("Office Locator")) {
						startActivity (new Intent(getApplicationContext(), branch.class));
	    			} 
					if (gvpressed.equals("NAV")) {
						  Intent browserIntent = new Intent(getApplicationContext(),nav.class);  	  
						  	startActivity(browserIntent);
	    			} 
					if (gvpressed.equals("Developer Site")) {
						startActivity (new Intent(getApplicationContext(), Devwebsite.class)); 
	    			} 
					if (gvpressed.equals("Add Person")) {
			//			startActivity (new Intent(getApplicationContext(), Devwebsite.class)); 
	    			} 
					
					
					
					
					
					
					
				}
			});
			 // get the listview
			ExpandList = (ExpandableListView) findViewById(R.id.lvExp);
	        ExpListItems = SetStandardGroups();
	        ExpAdapter = new ExpandListAdapter(HomeActivity.this, ExpListItems);
	        ExpandList.setAdapter(ExpAdapter);
	        ExpandList.expandGroup(0);
	        setListViewHeight(ExpandList);
	        ExpandList.setOnGroupClickListener(new OnGroupClickListener() {

	        @Override
	        public boolean onGroupClick(ExpandableListView parent, View v,
	        		int groupPosition, long id) {
	        setListViewHeight(parent, groupPosition);
	        return false;
	        }
	        });

	     // Listview on child click listener
	        ExpandList.setOnChildClickListener(new OnChildClickListener() {
	 
	            @Override
	            public boolean onChildClick(ExpandableListView parent, View v,
	                    int groupPosition, int childPosition, long id) {
	            	TextView tv = (TextView)v.findViewById(R.id.country_name);
	            	
	            	
	                Toast.makeText(
	                        getApplicationContext(),
	                        tv.getText().toString().trim(), Toast.LENGTH_SHORT)
	                        .show();
	            	
	            	
	            	
	                return false;
	            }
	        });

		}
		  
		  




/**
 * onDestroy
 * The final call you receive before your activity is destroyed. 
 * This can happen either because the activity is finishing (someone called finish() on it, 
 * or because the system is temporarily destroying this instance of the activity to save space. 
 * You can distinguish between these two scenarios with the isFinishing() method.
 *
 */

protected void onDestroy ()
{
   super.onDestroy ();
}

/**
 * onPause
 * Called when the system is about to start resuming a previous activity. 
 * This is typically used to commit unsaved changes to persistent data, stop animations 
 * and other things that may be consuming CPU, etc. 
 * Implementations of this method must be very quick because the next activity will not be resumed 
 * until this method returns.
 * Followed by either onResume() if the activity returns back to the front, 
 * or onStop() if it becomes invisible to the user.
 *
 */

protected void onPause ()
{
   super.onPause ();
}

/**
 * onRestart
 * Called after your activity has been stopped, prior to it being started again.
 * Always followed by onStart().
 *
 */

protected void onRestart ()
{
   super.onRestart ();
}

/**
 * onResume
 * Called when the activity will start interacting with the user. 
 * At this point your activity is at the top of the activity stack, with user input going to it.
 * Always followed by onPause().
 *
 */

protected void onResume ()
{
   super.onResume ();
}

/**
 * onStart
 * Called when the activity is becoming visible to the user.
 * Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes hidden.
 *
 */

protected void onStart ()
{
   super.onStart ();
}

/**
 * onStop
 * Called when the activity is no longer visible to the user
 * because another activity has been resumed and is covering this one. 
 * This may happen either because a new activity is being started, an existing one 
 * is being brought in front of this one, or this one is being destroyed.
 *
 * Followed by either onRestart() if this activity is coming back to interact with the user, 
 * or onDestroy() if this activity is going away.
 */

protected void onStop ()
{
   super.onStop ();
}




/**
 */
// Click Methods


/**
 */
// More Methods
class task extends AsyncTask<String, String, Void>
{
 private ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
    protected void onPreExecute() {
       progressDialog.setMessage("Registering  Please wait...");
       progressDialog.show();
       progressDialog.setOnCancelListener(new OnCancelListener() {
 @Override
  public void onCancel(DialogInterface arg0) {
  task.this.cancel(false);
    }

 });
    }
       @Override
       protected Void doInBackground(String... params) {
    	   Connection con = null;
    	   String	cagcode = null;
       	String agcode = (String)params[0];
       	final String url = "jdbc:mysql://ap-cdbr-azure-southeast-a.cloudapp.net/sreekanAcCySHknL";
        final String user = "b2bc3b54a57106";
		  final String pass = "cbbcf92d6038bb6";
        try {
			Class.forName("com.mysql.jdbc.Driver");
			   try {
				con = DriverManager.getConnection(url, user, pass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	             System.out.println("Database connection success"); 	
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Statement st = null;
   		try {
   			st = (Statement) con.createStatement();
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
          ResultSet rs = null;
       
     	try {
			rs = st.executeQuery("select count(*) as acount from access_registration WHERE AgentCode = '" + fagcode.getText().toString() + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	         
     //      ResultSetMetaData rsmd = rs.getMetaData();
           try {
			while(rs.next()) {	   
				cagcode = rs.getString("acount");  	
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
           /*****************************************************************************************************************************************/
        
      if (Integer.parseInt(cagcode)==0){
        
        try {
             String insertTableSQL = "INSERT INTO access_registration "
            			+ "(AgentCode, AgentName, MobileNumber,Upddate) VALUES"
            			+ "(?,?,?,?)";
            	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(insertTableSQL);
            	preparedStatement.setString(1, fagcode.getText().toString().trim());
            	preparedStatement.setString(2, fagname.getText().toString().trim());         	
            	preparedStatement.setString(3, fmobile.getText().toString().trim());
            	
            	String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            	preparedStatement.setString(4, date);
            	
            	// execute insert SQL stetement
            	preparedStatement .executeUpdate();  
        }

        catch (SQLException ex) {
        	   try {
				throw new MySQLIntegrityConstraintViolationException();
			} catch (MySQLIntegrityConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	} 
      }
  /*********************************************************************************************/   
         st = null;
		try {
			st = (Statement) con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         rs = null;
        	 
		try {
			rs = st.executeQuery("select AgentCode, AgentName,MobileNumber from access_registration WHERE AgentCode = '" + fagcode.getText().toString().trim() + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	         
     
           try {
			while(rs.next()) {
				   
				   
			   	nagcode = rs.getString("AgentCode");
			  	nagname = rs.getString("AgentName");
			   	nmobile = rs.getString("MobileNumber");
			   
		  	
			   	
			   	
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
           System.out.println("nagcode   "+nagcode); 	
           DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(HomeActivity.this);
           SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
           ContentValues values = new ContentValues();
          
           
           database.delete("registration", null, null);
          
           values.put(Registration.REG_AGCODE, nagcode);
           values.put(Registration.REG_COUPONNO, "     ");
           values.put(Registration.REG_ACTIVATED, "  ");
           values.put(Registration.REG_REGFLAG, " ");
           values.put(Registration.REG_MODEL, "x");
           values.put(Registration.REG_ADD, " ");
           values.put(Registration.REG_BRCODE, "  ");
           values.put(Registration.REG_MOBILE, nmobile);
           values.put(Registration.REG_NAME, nagname);
           values.put(Registration.REG_REGCODE, "  ");
           values.put(Registration.REG_DEALERCODE, "   ");
           values.put(Registration.REG_EMAIL, "  " );
           values.put(Registration.REG_PRODKEY,"   ");
          database.insert(Registration.TABLE_STOCK, null, values);
          database.close();
          stockSQLHelper.close();
       
      
         return null;
        }
       protected void onPostExecute(Void v) {   
       this.progressDialog.dismiss();
       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				HomeActivity.this);

			// set title
			alertDialogBuilder.setTitle("Job Over");

			// set dialog message
			alertDialogBuilder
				.setMessage("Click OK to Continue...!")
				.setCancelable(false)
				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
				//		Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
				//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//		intent.putExtra("EXIT", false);
				//		startActivity(intent);
					}
				  });
				

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
			}

       
       
       
       
       }
   
public ArrayList<Group> SetStandardGroups() {

    String group_names[] = { "Top 6 Policies" };

    String country_names[] = { "Brazil", "Mexico" };

    int Images[] = { R.drawable.ic_fa_plus, R.drawable.ic_action_action_settings
             };
    DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
    SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
    String sql = "";
    sql = "SELECT * FROM bmpolmast order by prem desc limit 6";
    Cursor c = database.rawQuery(sql, null);
    
    ArrayList<Group> list = new ArrayList<Group>();

    ArrayList<Child> ch_list = null;
    c.moveToFirst();
    for (String group_name : group_names) {
        Group gru = new Group();
        gru.setName(group_name);
        

    if (!c.isAfterLast()){
    	ch_list = new ArrayList<Child>();
  		do{
  			
  			
  			 String compcode = c.getString(c.getColumnIndexOrThrow("policyno"));
  			 String prem = c.getString(c.getColumnIndexOrThrow("prem"));
  			 Child ch = new Child();
  			ch.setName(compcode);
  			ch.SetPrem(prem);
            ch.setImage(Images[0]);
            ch_list.add(ch);
            
           
  		} while (c.moveToNext());
  		 gru.setItems(ch_list);
  		}
    
    list.add(gru);
    }
    /*int size = 2;
    int j = 0;

    for (String group_name : group_names) {
        Group gru = new Group();
        gru.setName(group_name);

        ch_list = new ArrayList<Child>();
        for (; j < size; j++) {
            Child ch = new Child();
            ch.setName(country_names[j]);
            ch.setImage(Images[j]);
            ch_list.add(ch);
        }
        gru.setItems(ch_list);
        list.add(gru);

        size = size + 2;
    }*/

    return list;
}
private void setListViewHeight(ListView listView) {
ListAdapter listAdapter = listView.getAdapter();
int totalHeight = 0;
for (int i = 0; i < listAdapter.getCount(); i++) {
View listItem = listAdapter.getView(i, null, listView);
listItem.measure(0, 0);
totalHeight += listItem.getMeasuredHeight();
}

ViewGroup.LayoutParams params = listView.getLayoutParams();
params.height = totalHeight
+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
listView.setLayoutParams(params);
listView.requestLayout();
}


private void setListViewHeight(ExpandableListView listView,
int group) {
ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
int totalHeight = 0;
int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(),
MeasureSpec.AT_MOST);
for (int i = 0; i < listAdapter.getGroupCount(); i++) {
View groupItem = listAdapter.getGroupView(i, false, null, listView);
groupItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);

totalHeight += groupItem.getMeasuredHeight();

if (((listView.isGroupExpanded(i)) && (i != group))
|| ((!listView.isGroupExpanded(i)) && (i == group))) {
for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
View listItem = listAdapter.getChildView(i, j, false, null,
listView);
listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);

totalHeight += listItem.getMeasuredHeight();

}
}
}

ViewGroup.LayoutParams params = listView.getLayoutParams();
int height = totalHeight
+ (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
if (height < 10)
height = 200;
params.height = height;
listView.setLayoutParams(params);
listView.requestLayout();
}

}
