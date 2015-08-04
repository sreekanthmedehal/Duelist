package com.sreekanth.duelist;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


public class RegisterActivity extends Activity implements View.OnClickListener{
	EditText AgentCode;
	EditText FullName;
	Button Register;
	Button Back;
	EditText MobileNo;
	Spinner Designation;
	EditText BranchCode;
	EditText Email;
	EditText Address;
	EditText DealerCode;
	EditText Password;
	String rannum;
  	String nagcode;
  	String nagname;
  	String nbrcode;
   	String nmobile;
   	String nemail;
   	String nadd;
   	String ndealercode;
    String ragname,ragcode,rmobileno;
   	String nusercode;
   	String nproductkey;
   	String ndesignation;
   	TelephonyManager tel;
   	
   	WebView webview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);
        tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
   //     TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        
        // Listening to Login Screen link
  //      loginScreen.setOnClickListener(new View.OnClickListener() {
			
	//		public void onClick(View arg0) {
				// Switching to Login Screen/closing register screen
	//			finish();
	//		}
	//	});
        //Compulsory Fields
     Register = (Button)findViewById(R.id.btnRegister);
     Register.setOnClickListener(this);
     Back = (Button)findViewById(R.id.btnback);
     Back.setOnClickListener(this);
     AgentCode = (EditText)findViewById(R.id.reg_agcode);
     FullName = (EditText)findViewById(R.id.reg_fullname);
     MobileNo = (EditText)findViewById(R.id.reg_mobile);
     
     DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(RegisterActivity.this);
     SQLiteDatabase database = stockSQLHelper.getReadableDatabase(); 
 		 String sql = "select * from registration";
 	         final Cursor a = database.rawQuery(sql, null);
 	         a.moveToFirst();
 	         if (a.getCount()>0){
 	   ragname = a.getString(a.getColumnIndex("agname")).trim();
 	   ragcode = a.getString(a.getColumnIndex("regagcode")).trim();
 	   rmobileno = a.getString(a.getColumnIndexOrThrow("regmobile"));
 	         }
        database.close();
        a.close();
        stockSQLHelper.close();
     
        AgentCode.setText(ragcode);
        FullName.setText(ragname);
        MobileNo.setText(rmobileno);
     
     
     
     
     Designation = (Spinner)findViewById(R.id.spinner1);
     webview = (WebView)findViewById(R.id.webView1);
    String [] Desg = {"","Agent","CLIA","DevOff","SBA","Empowered Agent"};
    
    ArrayAdapter<String> Desgadapter = new ArrayAdapter<String>(RegisterActivity.this,
	android.R.layout.simple_spinner_item, Desg);
    Desgadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	Designation.setAdapter(Desgadapter);
	
    
    
     BranchCode = (EditText)findViewById(R.id.reg_branchcode);
     Email = (EditText)findViewById(R.id.reg_email);
     Address = (EditText)findViewById(R.id.reg_address);
 	 DealerCode = (EditText)findViewById(R.id.reg_dealercode);
 	 Password = (EditText)findViewById(R.id.reg_password); 
    }
    @Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnRegister:
    if (AgentCode.getText().toString().trim().length()==0||FullName.getText().toString().trim().length()==0||MobileNo.getText().toString().trim().length()==0){
				  AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create(); //Read Update
			        alertDialog.setTitle("Please Fill all the Fields");
			        alertDialog.setMessage("Code/Name/Mobile No. Blank");

			        alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int which) {
			              // here you can add functions
			           }
			        });

			        alertDialog.show();  //<-- See This!
  }
    else if (Designation.getSelectedItem().toString().trim().length()==0){
		  AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create(); //Read Update
	        alertDialog.setTitle("Please Fill all the Fields");
	        alertDialog.setMessage("Designation Blank");

	        alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int which) {
	              // here you can add functions
	           }
	        });

	        alertDialog.show();  //<-- See This!
}
    else if (Address.getText().toString().trim().length()==0){
    //	||DealerCode.getText().toString().trim().length()==0||Password.getText().toString().trim().length()==0
		  AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create(); //Read Update
	        alertDialog.setTitle("Please Fill all the Fields");
	        alertDialog.setMessage("Address/DealerCode/Password Blank");

	        alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int which) {
	              // here you can add functions
	           }
	        });

	        alertDialog.show();  //<-- See This!
}   
    else  if (BranchCode.getText().toString().trim().length()==0||Email.getText().toString().trim().length()==0){
		  AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create(); //Read Update
	        alertDialog.setTitle("Please Fill all the Fields");
	        alertDialog.setMessage("Branch Code/Email Blank");

	        alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int which) {
	              // here you can add functions
	           }
	        });

	        alertDialog.show();  //<-- See This!
}      
    
    else {
    rannum = GenerateRandomNumber(10);
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(Password.getWindowToken(), 0);
   new task().execute(AgentCode.getText().toString().trim());

    
    
    
    
    
    }
    
    
    break;
			case R.id.btnback:
				finish();
			break;	
}
}   
    String GenerateRandomNumber(int charLength) {
        return String.valueOf(charLength < 1 ? 0 : new Random()
                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
                + (int) Math.pow(10, charLength - 1));
    }
    class task extends AsyncTask<String, String, Void>
    {
     private ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
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
				rs = st.executeQuery("select count(*) as acount from access_registration WHERE AgentCode = '" + AgentCode.getText().toString() + "';");
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
               

                 String insertTableSQL = "INSERT INTO access_registration"
                			+ "(AgentCode, AgentName, BranchCode,MobileNumber,Email,Address,DealerCode,Upddate,UserCode,ProductKey,Designation) VALUES"
                			+ "(?,?,?,?,?,?,?,?,?,?,?)";
                	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(insertTableSQL);
                	preparedStatement.setString(1, AgentCode.getText().toString().trim());
                	preparedStatement.setString(2, FullName.getText().toString().trim());
                	preparedStatement.setString(3, BranchCode.getText().toString().trim());
                	preparedStatement.setString(4, MobileNo.getText().toString().trim());
                	preparedStatement.setString(5, Email.getText().toString().trim());
                	preparedStatement.setString(6, Address.getText().toString().trim());
                	preparedStatement.setString(7, DealerCode.getText().toString().trim());
                	
                	String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                	preparedStatement.setString(8, date);
                
                	preparedStatement.setString(9, rannum);
                	
                	preparedStatement.setString(10, tel.getSimSerialNumber().toString());
                	preparedStatement.setString(11, Designation.getSelectedItem().toString());
                	
                	
                	// execute insert SQL stetement
                	preparedStatement .executeUpdate();  
            }
   
            catch (SQLException ex) {
            	   try {
					throw new MySQLIntegrityConstraintViolationException();
				} catch (MySQLIntegrityConstraintViolationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					 AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create(); //Read Update
				        alertDialog.setTitle("Not Successful");
				        alertDialog.setMessage("Server Busy,Try Again");

				        alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int which) {
				              // here you can add functions
				           }
				        });

				        alertDialog.show();  //<-- See This!				
					
					
					
					
					
				}
            	}
            final String html = "<html><table border=1><tr><td colspan=2>Registration Successful....</td></tr><tr><td>UserCode</td><td>" +rannum + " </td></tr><tr><td colspan=2>Next Step  Activation...</td></tr></body></html>";
           
               
                    

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                 public void run() {
                      // UI code goes here
                	 webview.loadData(html, "text/html", "UTF-8");
                 }
            });
                 	
                 
       
            
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
				rs = st.executeQuery("select AgentCode, AgentName, BranchCode,MobileNumber,Email,Address,DealerCode,Upddate,UserCode,ProductKey,Designation from access_registration WHERE AgentCode = '" + AgentCode.getText().toString() + "';");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	         
         //      ResultSetMetaData rsmd = rs.getMetaData();
               try {
				while(rs.next()) {
					   
					   
				   	nagcode = rs.getString("AgentCode");
				  	nagname = rs.getString("AgentName");
				  	nbrcode = rs.getString("BranchCode");
				   	nmobile = rs.getString("MobileNumber");
				   	nemail = rs.getString("Email");
				   	nadd = rs.getString("Address");
				   	ndealercode = rs.getString("DealerCode");
      //       	nupddate = rs.getString("Upddate");
				   	nusercode = rs.getString("UserCode");
				   	nproductkey = rs.getString("ProductKey");
				   	ndesignation = rs.getString("Designation");
				   	
				   	
				   	
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
       //        Toast.makeText(RegisterActivity.this, rannum, Toast.LENGTH_LONG).show();
               DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(RegisterActivity.this);
               SQLiteDatabase database = stockSQLHelper.getWritableDatabase();
               ContentValues values = new ContentValues();
              
               
               database.delete("registration", null, null);
              
               values.put(Registration.REG_AGCODE, nagcode);
               values.put(Registration.REG_COUPONNO, "     ");
               values.put(Registration.REG_ACTIVATED, "  ");
               values.put(Registration.REG_REGFLAG, ndesignation);
               values.put(Registration.REG_MODEL, "x");
               values.put(Registration.REG_ADD, nadd);
               values.put(Registration.REG_BRCODE, nbrcode);
               values.put(Registration.REG_MOBILE, nmobile);
               values.put(Registration.REG_NAME, nagname);
               values.put(Registration.REG_REGCODE, nusercode);
               values.put(Registration.REG_DEALERCODE, ndealercode);
               values.put(Registration.REG_EMAIL, nemail);
               values.put(Registration.REG_PRODKEY, nproductkey);
              database.insert(Registration.TABLE_STOCK, null, values);
              database.close();
              stockSQLHelper.close();
           }
           else
           {
        	   final String html = "<html><table border=1 ><tr><td>Already Registered...</td></tr></body></html>";
        	   Handler handler = new Handler(Looper.getMainLooper());
               handler.post(new Runnable() {
                    public void run() {
                         // UI code goes here
                   	 webview.loadData(html, "text/html", "UTF-8");
                    }
               });
               
           }
             return null;
            }
           protected void onPostExecute(Void v) {   
           this.progressDialog.dismiss();
           AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
    				RegisterActivity.this);

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
       
   
    }
