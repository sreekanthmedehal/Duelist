package com.sreekanth.duelist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mysql.jdbc.Statement;

public class Forgot_regcode extends Activity implements OnClickListener {
     TextView tagcode;
     EditText eagcode;
     EditText emobileno;
    TextView sagcode;
    TextView sresult;
    
 	String result = "";
 	String rdate;
 	String activated;
 	String agcode;
 	String mobile;
 	
 	
 	String usercode;
 	String regcode;
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
 	
 	Button regbutton;
 	Button backbutton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_regcode);
		
		tagcode = (TextView)findViewById(R.id.textView1);
		eagcode = (EditText)findViewById(R.id.editText2);
		emobileno = (EditText)findViewById(R.id.editText1);
		
		sagcode = (TextView)findViewById(R.id.textView4);
		sresult = (TextView)findViewById(R.id.textView5);
		regbutton = (Button)findViewById(R.id.button1);
		regbutton.setOnClickListener(this);
		backbutton = (Button)findViewById(R.id.button2);
		backbutton.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forgot_regcode, menu);
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
		}
		return super.onOptionsItemSelected(item);
	}
	class gettask extends AsyncTask<String, String, Void>
	{
	 private ProgressDialog progressDialog = new ProgressDialog(Forgot_regcode.this);
	    protected void onPreExecute() {
	       progressDialog.setMessage("Retrieving Registration Code...");
	       progressDialog.show();
	       progressDialog.setOnCancelListener(new OnCancelListener() {
	 @Override
	  public void onCancel(DialogInterface arg0) {
	  gettask.this.cancel(false);
	    }

	 });
	    }
	       @Override
	  protected Void doInBackground(String... params) {
	       	 agcode = (String)params[0].trim();
	       	 mobile = (String)params[1].trim();
	       	
	       
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
          ResultSet rs = st.executeQuery("select sagcode,updtype,startdate,activated from subscription WHERE sagcode = '" + agcode + "' and TRIM(smobile) = '" + mobile + "';");	         
       //      ResultSetMetaData rsmd = rs.getMetaData();
             while(rs.next()) {
          	ragcode = rs.getString("sagcode");
             	result = rs.getString("updtype");
             	rdate = rs.getString("startdate");
             	activated = rs.getString("activated");
             }
        ResultSet rs1 = st.executeQuery("select passwd from portal where TRIM(pagcode) = '" + agcode + "';");     
             while(rs1.next()){
          	   Portalpwd = rs1.getString("passwd");  
             }
   
             ResultSet        rs2 = st.executeQuery("select  AgentName, BranchCode,MobileNumber,Email,Address,DealerCode,Upddate,ProductKey,Designation,UserCode from access_registration WHERE AgentCode = '" + agcode + "';");
             while(rs2.next()){
          	ragname = rs2.getString("AgentName");
          	rmobile = rs2.getString("MobileNumber");
          	remail = rs2.getString("Email");
          	rdesg = rs2.getString("Designation");  
          	rprodkey = rs2.getString("ProductKey");
          	rbrcode = rs2.getString("BranchCode");
          	rdealercode = rs2.getString("DealerCode");
          	radd = rs2.getString("Address");
          	regcode = rs2.getString("UserCode");
             }
             con.close();
    }
   catch(Exception e) {
       e.printStackTrace();
   }     	

	        return null;
	        }
	       protected void onPostExecute(Void v) {   
	       this.progressDialog.dismiss();
	       
	       
	       sagcode.setText(agcode.trim() + "  "+ mobile);
	       
           if (!(ragcode == null)){
		DataViewerSQLiteHelper stockSQLHelper1 = new DataViewerSQLiteHelper(Forgot_regcode.this);
		SQLiteDatabase database1 = stockSQLHelper1.getReadableDatabase(); 
	  ContentValues	values = new ContentValues();
	    values.put("regagcode", ragcode);
	   	values.put("regmodel", result);
	   	values.put("startdate", rdate);
	   	values.put("regactivated", activated);
	   	if (Portalpwd == null){ Portalpwd = "   ";}
	   	values.put("regcouponno",Portalpwd);
	   	values.put("regcode", regcode);
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
       sagcode.setText(regcode.trim());
       
       sresult.setText("Successful...." +"\n"+ "Next Step Activation...");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				Forgot_regcode.this);

			// set title
			alertDialogBuilder.setTitle("Successful...  "
					+ "\n" + "Next Step Activation");

			// set dialog message
			alertDialogBuilder
				.setMessage("Click OK to exit!")
				.setCancelable(false)
				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
				//		Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
				//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			//			intent.putExtra("EXIT", false);
				//		startActivity(intent);
					}
				  });
				

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
			   }else
			   {
				   AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							Forgot_regcode.this);

						// set title
						alertDialogBuilder.setTitle("Not Registered ...");

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
				   
				   
			   }
           }
				
	       
	       
	       
	       
	       
	       
	       
	       }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch( ( v.getId())){
		case R.id.button1:
			hideKeyboard();
			if (eagcode.getText().toString().length() ==0 ){
				eagcode.setError("Agency Code required");
				
			}
			else if (emobileno.getText().toString().length() == 0){
				emobileno.setError("mobile Number required");
				
			} else {
		new gettask().execute(eagcode.getText().toString().trim(),emobileno.getText().toString().trim());}
			
		break;
		
		case R.id.button2:
			finish();
			break;
		}
	}
	 private void hideKeyboard() {
         if (emobileno != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(emobileno.getWindowToken(), 0);
         }

     }
}
