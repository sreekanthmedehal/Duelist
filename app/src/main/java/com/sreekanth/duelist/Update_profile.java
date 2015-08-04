package com.sreekanth.duelist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class Update_profile extends Activity implements OnClickListener {
	

	EditText uagcode;
	EditText uagname;
	Spinner  Designation;
	EditText umobile;
	EditText ubrcode;
	EditText uemail;
	EditText uaddress;
	EditText RegCode;
	Button   register;
	Button   back;
	
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
	
	String rdate;
	String result = "";
	String ragcode;
	String ragname;
	String rmobile;
	String remail;
	String rdesg;  
	String rprodkey;
	String rbrcode;
	String rdealercode;
	String radd;	
	String Portalpwd;
	String activated;
	
	ContentValues values;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_profile);
		uagcode = (EditText)findViewById(R.id.editText_enter_code);
		uagname = (EditText)findViewById(R.id.editText_enter_name);
		Designation = (Spinner)findViewById(R.id.spinner1);
		umobile = (EditText)findViewById(R.id.editText_enter_username);
		ubrcode = (EditText)findViewById(R.id.editText_enter_brcode); 
		uemail = (EditText)findViewById(R.id.editText_enter_email);
		uaddress = (EditText)findViewById(R.id.editText_enter_add);
		RegCode = (EditText)findViewById(R.id.editText_enter_rpassword);
		register = (Button)findViewById(R.id.button_register);
		back = (Button)findViewById(R.id.button_back);
		
		RegCode.setFocusable(false);
		umobile.setFocusable(false);
        uagcode.setFocusable(false);
		
		 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(Update_profile.this);
		 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
		 String sql = "";
		 sql = "SELECT * from registration";
		 Cursor c = database.rawQuery(sql, null);
			
			if ((c.getCount()>0)){
				c.moveToFirst();
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
			    	 
			    	  
			    	 
			    	 
			    	 
					uagcode.setText(REG_AGCODE);
					uagname.setText(REG_NAME);
					String [] Desg = {"","Agent","CLIA","DevOff","SBA","Empowered Agent"};
				    Designation.setAdapter(new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, Desg, REG_REGFLAG));
					umobile.setText(REG_MOBILE);
					ubrcode.setText(REG_BRCODE);
					uemail.setText(REG_EMAIL);
					uaddress.setText(REG_ADD);
					RegCode.setText(REG_REGCODE);
				
					  database.close();
					  stockSQLHelper.close();
				}else{
					Toast.makeText(Update_profile.this, "Activate Product and try again...", Toast.LENGTH_LONG).show();
				  database.close();
				  stockSQLHelper.close();
				  finish();
				  }
			}else{
			Toast.makeText(Update_profile.this, "First Register Yourself and try again...", Toast.LENGTH_LONG).show();
		    database.close();
		    stockSQLHelper.close();
		    finish();
			}	
		register.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_profile, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch( ( v.getId())){
		   case R.id.button_register:
			   if ((RegCode.getText().toString().trim().length()>0)){
				   DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(Update_profile.this);
					 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
					 database.delete("registration", null, null);
					   values = new ContentValues();
					   values.put(Registration.REG_AGCODE, uagcode.getText().toString().trim());
					   values.put(Registration.REG_COUPONNO, REG_COUPONNO);
		               values.put(Registration.REG_REGFLAG, Designation.getSelectedItem().toString());
		               values.put(Registration.REG_MODEL, REG_MODEL);
		               values.put(Registration.REG_ACTIVATED, REG_ACTIVATED);
		               values.put(Registration.REG_SDATE, REG_SDATE);
		               values.put(Registration.REG_EDATE, REG_EDATE);
		               values.put(Registration.REG_EMAIL, uemail.getText().toString());
		               values.put(Registration.REG_MOBILE, umobile.getText().toString());
		               values.put(Registration.REG_REGCODE,RegCode.getText().toString());
		               values.put(Registration.REG_PRODKEY,REG_PRODKEY);
		               values.put(Registration.REG_NAME, uagname.getText().toString().trim());
		               values.put(Registration.REG_BRCODE, ubrcode.getText().toString());
		               values.put(Registration.REG_DEALERCODE, REG_DEALERCODE);
		               values.put(Registration.REG_ADD,uaddress.getText().toString().trim());
		               
		               
		               
		               
		               
		               
		               
					   database.insert(Registration.TABLE_STOCK, null, values);
	  
					   new task().execute(uagcode.getText().toString().trim());
			     database.close();
			     stockSQLHelper.close();			 
			   }
	 break;
	case R.id.button_back:
	finish();
	break;		   
	}	   	
	}
	class task extends AsyncTask<String, String, Void>
	{
	 private ProgressDialog progressDialog = new ProgressDialog(Update_profile.this);
	    protected void onPreExecute() {
	       progressDialog.setMessage("Updating Profile...");
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
	       	Connection con = null;
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

	             try {
	                 

	                 String insertTableSQL = "REPLACE INTO access_registration"
	                			+ "(AgentCode, AgentName, BranchCode,MobileNumber,Email,Address,DealerCode,Upddate,UserCode,ProductKey,Designation) VALUES"
	                			+ "(?,?,?,?,?,?,?,?,?,?,?)";
	                	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(insertTableSQL);
	                	preparedStatement.setString(1, agcode);
	                  	preparedStatement.setString(2, uagname.getText().toString().trim());
	                	preparedStatement.setString(3, ubrcode.getText().toString());
	                	preparedStatement.setString(4, REG_MOBILE);
	                	preparedStatement.setString(5, uemail.getText().toString());
	                	preparedStatement.setString(6, uaddress.getText().toString().trim());
	                	preparedStatement.setString(7, REG_DEALERCODE);
	                	String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	                	preparedStatement.setString(8, date);
	                	preparedStatement.setString(9, REG_REGCODE);
	                	preparedStatement.setString(10, REG_PRODKEY); 
	                	preparedStatement.setString(11, Designation.getSelectedItem().toString().trim());
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
	  /*********************************************************************************************/          		
	        
		               try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		      
		//	DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(F3Activity.this);
		//	SQLiteDatabase database = stockSQLHelper.getReadableDatabase(); 
		//	values = new ContentValues();
		//   	values.put("regmodel", result);
		//   	values.put("startdate", rdate);
//		int rowsUpdate =     database.update("registration", values, null,null);
	 //       database.close();
	 //       stockSQLHelper.close();
	            }
	         return null;
	        }
	       protected void onPostExecute(Void v) {   
	       this.progressDialog.dismiss();
	       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					Update_profile.this);

				// set title
				alertDialogBuilder.setTitle("Profile Updated");

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
	       }
	  

