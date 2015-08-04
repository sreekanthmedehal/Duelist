package com.sreekanth.duelist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mysql.jdbc.Statement;

public class ActivateProduct extends Activity implements OnClickListener {
Button activate;
String result = "";
String rdate;
String activated;
String agcode;
String usercode;
EditText RegCode;
String Portalpwd;
String ragcode;
String ragname;
String rmobile;
String remail;
String rdesg;  
String rprodkey;
String rbrcode;
String rdealercode;
String radd;
TextView Prodkey1;

TelephonyManager tel;
String Prodkey;
//String active;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activate_product);
		 tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		activate = (Button)findViewById(R.id.button2);
		activate.setOnClickListener(this);
		Button getregcode = (Button)findViewById(R.id.button1);
		getregcode.setOnClickListener(this);
		
	//	activate.setEnabled(false);
		
		RegCode = (EditText)findViewById(R.id.textView7);
	    RegCode.setText("");
		Button backbtn = (Button)findViewById(R.id.button3);
		backbtn.setOnClickListener(this);
	    Prodkey1=(TextView)findViewById(R.id.textView1);
	    try{
	    	Prodkey = tel.getSimSerialNumber().toString().trim();
	    }catch (Exception e) {
		     Log.e("your app", e.toString());
		    }
	    if (Prodkey != null && Prodkey.length() > 0) {
	    	Prodkey1.setText(tel.getSimSerialNumber().toString());
	    	   
	      }else {
	    	  Prodkey1.setText("No Sim");
	      }
	 
	
	  
	 
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activate_product, menu);
		return true;
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch( ( v.getId())){
		case R.id.button1:
			
			 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(ActivateProduct.this);
			 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
			 String sql = "";
				sql = "SELECT regagcode,regactivated,regcode from registration";
				Cursor c = database.rawQuery(sql, null);
				
				if ((c.getCount()>0)){
					c.moveToFirst();
					agcode = c.getString(c.getColumnIndexOrThrow("regagcode"));
			//		active = c.getString(c.getColumnIndexOrThrow("regactivated"));
					usercode = c.getString(c.getColumnIndexOrThrow("regcode"));
					if (agcode.length() > 0 ){
						RegCode.setText(usercode);
					//	activate.setEnabled(true);
						  database.close();
						  stockSQLHelper.close();
					}else{
						Toast.makeText(ActivateProduct.this, "Activate Product and try again...", Toast.LENGTH_LONG).show();
					  database.close();
					  stockSQLHelper.close();
					  }
				}else{
				Toast.makeText(ActivateProduct.this, "First Register Yourself and try again...", Toast.LENGTH_LONG).show();
				
				
			    database.close();
			    stockSQLHelper.close();
			    
				}	
			
	
			
			break;
		   case R.id.button2:
			   if ((RegCode.getText().toString().trim().length()>0)){
					
				
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
			    Statement st = (Statement) con.createStatement();
	            ResultSet rs = st.executeQuery("select sagcode,updtype,startdate,activated from subscription WHERE UserCode = '" + RegCode.getText().toString().trim() + "';");	         
	         //      ResultSetMetaData rsmd = rs.getMetaData();
	               while(rs.next()) {
	            	ragcode = rs.getString("sagcode");
	               	result = rs.getString("updtype");
	               	rdate = rs.getString("startdate");
	               	activated = rs.getString("activated");
	               }
	          ResultSet rs1 = st.executeQuery("select passwd from portal where pagcode = '" + ragcode + "';");     
	               while(rs1.next()){
	            	   Portalpwd = rs1.getString("passwd");  
	               }
	     
	               ResultSet        rs2 = st.executeQuery("select  AgentName, BranchCode,MobileNumber,Email,Address,DealerCode,Upddate,ProductKey,Designation from access_registration WHERE UserCode = '" + RegCode.getText().toString() + "';");
                   while(rs2.next()){
                	ragname = rs2.getString("AgentName");
                	rmobile = rs2.getString("MobileNumber");
                	remail = rs2.getString("Email");
                	rdesg = rs2.getString("Designation");  
                	rprodkey = rs2.getString("ProductKey");
                	rbrcode = rs2.getString("BranchCode");
                	rdealercode = rs2.getString("DealerCode");
                	radd = rs2.getString("Address");
                   }
	               con.close();
	      }
	     catch(Exception e) {
	         e.printStackTrace();
	     }  
	            if (!(ragcode == null)){
		DataViewerSQLiteHelper stockSQLHelper1 = new DataViewerSQLiteHelper(ActivateProduct.this);
		SQLiteDatabase database1 = stockSQLHelper1.getReadableDatabase(); 
	  ContentValues	values = new ContentValues();
	    values.put("regagcode", ragcode);
	   	values.put("regmodel", result);
	   	values.put("startdate", rdate);
	   	values.put("regactivated", activated);
	   	if (Portalpwd == null){ Portalpwd = "   ";}
	   	values.put("regcouponno",Portalpwd);
	   	values.put("regcode", RegCode.getText().toString());
	   	values.put("regmobile", rmobile);
	   	values.put("agname", ragname);
 	   	values.put("regemail", remail);
 	   	values.put("regflag", rdesg);
 	   	values.put("prodkey", rprodkey);
 	   	values.put("brcode", rbrcode);
 	   	values.put("dealercode", rdealercode);
 	   	values.put("address", radd);
	   
	 
	int rowsUpdate =     database1.update("registration", values, null,null);
        database1.close();
        stockSQLHelper1.close();   
			   
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				ActivateProduct.this);

			// set title
			alertDialogBuilder.setTitle("Product Activated...");

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
			   }else
			   {
				   AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							ActivateProduct.this);

						// set title
						alertDialogBuilder.setTitle("Registataion Code Wrong,Enter Correct One ...");

						// set dialog message
						alertDialogBuilder
							.setMessage("Click OK to exit!")
							.setCancelable(false)
							.setPositiveButton("OK",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									// if this button is clicked, close
									// current activity
								dialog.cancel();
								}
							  });
							

							// create alert dialog
							AlertDialog alertDialog = alertDialogBuilder.create();

							// show it
							alertDialog.show();
				   
				   
			   }}
				 break;
			 
	case R.id.button3:
		finish();
	break;	
			   
			   
		}	   
			   
		   
	}
	}


