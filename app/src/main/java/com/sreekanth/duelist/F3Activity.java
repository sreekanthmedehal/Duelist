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
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * This is the activity for feature 3 in the dashboard application.
 * It displays some text and provides a way to get back to the home activity.
 *
 */

public class F3Activity extends DashboardActivity implements OnClickListener
{
	    String ipadd;
	    ContentValues values;
	    String vtype;
	    String result = "";
		String rdate;
	    String ltype;
	    
	    
	     String REG_AGCODE;
	     String REG_COUPONNO;
	     String REG_ACTIVATED;
	     String REG_REGFLAG;
	     String REG_MODEL;  
	    
	    
	     String REG_SDATE;
	     String REG_EDATE;
	     String REG_EMAIL;
	     String REG_MOBILE;
	     String REG_REGCODE;
	     String REG_PRODKEY;
	     String REG_NAME;
	     String REG_BRCODE;
	     String REG_DEALERCODE;
	     String REG_ADD;
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
    setContentView (R.layout.activity_f3);
    setTitleFromActivityLabel (R.id.title_text);
    
      Button Psbutton = (Button)findViewById(R.id.button0);
    Psbutton.setOnClickListener(this);
    
     Button lapbutton = (Button)findViewById(R.id.button1);
    lapbutton.setOnClickListener(this);
    
    Button blbutton = (Button)findViewById(R.id.button2);
    blbutton.setOnClickListener(this);
    
    Button mlbutton = (Button)findViewById(R.id.button3);
    mlbutton.setOnClickListener(this);
    
    Button pcbutton = (Button)findViewById(R.id.button4);
    pcbutton.setOnClickListener(this);
    
   
    
    Button Portalbutton = (Button)findViewById(R.id.button6);
    Portalbutton.setOnClickListener(this);
    
    Button surbtn = (Button)findViewById(R.id.button8);
    surbtn.setOnClickListener(this);
    
    Button pollistbtn = (Button)findViewById(R.id.button5);
    pollistbtn.setOnClickListener(this);
    
    Button revbtn = (Button)findViewById(R.id.button7);
    revbtn.setOnClickListener(this); 
    
   	DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(F3Activity.this);
	SQLiteDatabase database = stockSQLHelper.getReadableDatabase(); 
	
	 	String sql = "";
	sql = "SELECT regactivated,regcouponno from registration";
	Cursor c = database.rawQuery(sql, null);
	c.moveToFirst();
		if ((c.getCount()>0)){
		
				String active = c.getString(c.getColumnIndexOrThrow("regactivated"));
				String portalpwd = c.getString(c.getColumnIndexOrThrow("regcouponno"));
					if (!active.equals("Y") || portalpwd.length()==0){
	
		Psbutton.setEnabled(false);
		lapbutton.setEnabled(false);
		blbutton.setEnabled(false);
		mlbutton.setEnabled(false);
		pcbutton.setEnabled(false);
		pollistbtn.setEnabled(false);
	
		surbtn.setEnabled(false);
		revbtn.setEnabled(false);
		}
	
					database.close();
				    stockSQLHelper.close();
	
	}
	
}

@Override
public void onClick(View v) {
    
    
 	
	// TODO Auto-generated method stub
		  switch( ( v.getId())){
  case R.id.button6:
	   DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(F3Activity.this);
		 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
		 String sql = "";
			sql = "SELECT * from registration";
			Cursor c = database.rawQuery(sql, null);
			c.moveToFirst();
			if ((c.getCount()>0)){

				     REG_AGCODE = c.getString(c.getColumnIndexOrThrow("regagcode"));
				     REG_COUPONNO = c.getString(c.getColumnIndexOrThrow("regcouponno"));
				     REG_ACTIVATED = c.getString(c.getColumnIndexOrThrow("regactivated"));
				     REG_REGFLAG = c.getString(c.getColumnIndexOrThrow("regflag"));
				     REG_MODEL = c.getString(c.getColumnIndexOrThrow("regmodel"));  				    
				     REG_SDATE = c.getString(c.getColumnIndexOrThrow("startdate"));
				     REG_EDATE = c.getString(c.getColumnIndexOrThrow("enddate"));
				     REG_EMAIL  = c.getString(c.getColumnIndexOrThrow("regemail"));
				     REG_MOBILE = c.getString(c.getColumnIndexOrThrow("regmobile"));
				     REG_REGCODE = c.getString(c.getColumnIndexOrThrow("regcode"));
				     REG_PRODKEY = c.getString(c.getColumnIndexOrThrow("prodkey"));
				     REG_NAME = c.getString(c.getColumnIndexOrThrow("agname"));
				     REG_BRCODE = c.getString(c.getColumnIndexOrThrow("brcode"));
				     REG_DEALERCODE = c.getString(c.getColumnIndexOrThrow("dealercode"));
				     REG_ADD = c.getString(c.getColumnIndexOrThrow("address"));

				if ((REG_ACTIVATED.equals("Y"))){
					final Dialog dialog = new Dialog(this);

					  dialog.setContentView(R.layout.block_by_keyword);
					  dialog.setTitle("Enter Password");
			          final EditText editText1 = (EditText)dialog.findViewById(R.id.editText1);
			          editText1.setText(REG_AGCODE);
					  final EditText editTextKeywordToBlock=(EditText)dialog.findViewById(R.id.editTextKeywordsToBlock);
					  final RadioGroup radioSexGroup = (RadioGroup)dialog.findViewById(R.id.radioGroup1);
					  final RadioGroup radioVerGroup = (RadioGroup)dialog.findViewById(R.id.radioGroup2);
					  Button btnBlock=(Button)dialog.findViewById(R.id.buttonBlockByKeyword);
					  Button btnCancel=(Button)dialog.findViewById(R.id.buttonCancelBlockKeyword);
					  dialog.show();

					//Get The DATA:
					  btnCancel.setOnClickListener(new View.OnClickListener() {
						  
						  @Override
						  public void onClick(View v) 
						  {
							  dialog.cancel();
						       //  Your Code
							
						  }
						});


					//We can set ClickListiner on Buttons As Well

					btnBlock.setOnClickListener(new View.OnClickListener() {
					 
					  @Override
					  public void onClick(View v) 
					  {
						  dialog.cancel();
					       //  Your Code
						  String ragcode = editText1.getText().toString().trim();
						  ipadd =  editTextKeywordToBlock.getText().toString().trim();
						  int selectedId = radioSexGroup.getCheckedRadioButtonId();
						  int verId = radioVerGroup.getCheckedRadioButtonId();
						// find the radiobutton by returned id
						  RadioButton       radioSexButton = (RadioButton)radioSexGroup.findViewById(selectedId);			
						  ltype = radioSexButton.getText().toString();
						  RadioButton radioVerButton = (RadioButton)radioVerGroup.findViewById(verId);
						  vtype = radioVerButton.getText().toString();
						   
						   
						   
							 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(F3Activity.this);
							 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
							 database.delete("registration", null, null);
							   values = new ContentValues();
							   values.put(Registration.REG_AGCODE, REG_AGCODE);
							   values.put(Registration.REG_COUPONNO, ipadd);
							   
							   
							   
				               values.put(Registration.REG_REGFLAG, REG_REGFLAG);
				               values.put(Registration.REG_MODEL, REG_MODEL);
				               values.put(Registration.REG_ACTIVATED, REG_ACTIVATED);
				               
				               values.put(Registration.REG_SDATE, REG_SDATE);
				               values.put(Registration.REG_EDATE, REG_EDATE);
				               values.put(Registration.REG_EMAIL, REG_EMAIL);
				               values.put(Registration.REG_MOBILE, REG_MOBILE);
				               values.put(Registration.REG_REGCODE,REG_REGCODE);
				               values.put(Registration.REG_PRODKEY,REG_PRODKEY);
				               values.put(Registration.REG_NAME, REG_NAME);
				               values.put(Registration.REG_BRCODE, REG_BRCODE);
				               values.put(Registration.REG_DEALERCODE, REG_DEALERCODE);
				               values.put(Registration.REG_ADD,REG_ADD);
				               
				               
				               
				               
				               
				               
				               
							   database.insert(Registration.TABLE_STOCK, null, values);
			   //              testDB(ragcode);
							   new task().execute(ragcode);
					     database.close();
					     stockSQLHelper.close();			 
			}
			});
			}else
			{
			Toast.makeText(F3Activity.this, "Activate Product and try again...", Toast.LENGTH_LONG).show();
			database.close();
			stockSQLHelper.close();
			}
			}
			else
			{
			Toast.makeText(F3Activity.this, "First Register Yourself and try again...", Toast.LENGTH_LONG).show();
			database.close();
			stockSQLHelper.close();
			}
			
	   break;	
		


		//We can set ClickListiner on Buttons As Well

   case R.id.button1:
        //DO something
	   Intent intent1=new Intent(this,PortalrevList.class);
       startActivity(intent1);
   break;
   case R.id.button0:
	   Intent intentps=new Intent(this,PortalStatus.class);
       startActivity(intentps);
   break;
   case R.id.button2:
	   Intent intentbl=new Intent(this,PortalbdList.class);
       startActivity(intentbl);
   break;
   case R.id.button8:
   Intent surintent = new Intent(this,PortalSurr.class);
   startActivity(surintent);
   break;
   case R.id.button7:
	   Intent revintent = new Intent(this,Portalrev.class);
	   startActivity(revintent);
   break;
   case R.id.button5:
	   Intent pollistintent = new Intent(this,PortalPolList.class);
	   startActivity(pollistintent);
   break;
   default:
   break;
}

} 

class task extends AsyncTask<String, String, Void>
{
 private ProgressDialog progressDialog = new ProgressDialog(F3Activity.this);
    protected void onPreExecute() {
       progressDialog.setMessage("Saving Password...");
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
       	String agcode = (String)params[0];
    	final String url = "jdbc:mysql://ap-cdbr-azure-southeast-a.cloudapp.net/sreekanAcCySHknL";
        final String user = "b2bc3b54a57106";
		  final String pass = "cbbcf92d6038bb6";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
             System.out.println("Database connection success"); 

             String insertTableSQL = "REPLACE INTO portal"
            			+ "(pagcode, updtype, upddate,packagetype,passwd,branch,pregcode) VALUES"
            			+ "(?,?,?,?,?,?,?)";
            	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(insertTableSQL);
            	preparedStatement.setString(1, agcode);
            	preparedStatement.setString(2, REG_REGFLAG);
            	String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            	preparedStatement.setString(3, date);
            	preparedStatement.setString(4, REG_MODEL);
            	preparedStatement.setString(5, ipadd);
            	preparedStatement.setString(6, REG_BRCODE);
            	preparedStatement.setString(7, REG_REGCODE);
            	
            	// execute insert SQL stetement
            	preparedStatement .executeUpdate();   	
  /*********************************************************************************************/          		
        //        Statement st = (Statement) con.createStatement();
	    //        ResultSet rs = st.executeQuery("select updtype,startdate from subscription WHERE sagcode = '" + agcode + "';");	         
	         //      ResultSetMetaData rsmd = rs.getMetaData();
	     //          while(rs.next()) {
	      //         	result = rs.getString("updtype");
	      //         	rdate = rs.getString("startdate");
	       //        }
	               con.close();
	      }
	     catch(Exception e) {
	         e.printStackTrace();
	     }   
	//	DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(F3Activity.this);
	//	SQLiteDatabase database = stockSQLHelper.getReadableDatabase(); 
	//	values = new ContentValues();
	//   	values.put("regmodel", result);
	//   	values.put("startdate", rdate);
//	int rowsUpdate =     database.update("registration", values, null,null);
 //       database.close();
 //       stockSQLHelper.close();
         return null;
        }
       protected void onPostExecute(Void v) {   
       this.progressDialog.dismiss();
       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				F3Activity.this);

			// set title
			alertDialogBuilder.setTitle("Password Saved");

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
						intent.putExtra("EXIT", false);
						startActivity(intent);
					}
				  });
				

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
			}

       
       
      
       
       }
   
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

         String insertTableSQL = "REPLACE INTO portal"
        			+ "(pagcode, updtype, upddate,packagetype) VALUES"
        			+ "(?,?,?,?)";
        	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(insertTableSQL);
        	preparedStatement.setString(1, agcode);
        	preparedStatement.setString(2, " ");
        	String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        	preparedStatement.setString(3, date);
        	preparedStatement.setString(4, vtype);
        	// execute insert SQL stetement
        	preparedStatement .executeUpdate();
  con.close();
    }
    catch(Exception e) {
        e.printStackTrace();
  //      tv.setText(e.toString());
    }   

}
} // end class
