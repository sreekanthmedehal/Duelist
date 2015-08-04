package com.sreekanth.duelist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class Feedback extends Activity {
	Button btnOK,btnCancel;
    EditText txtAgcode,txtAgName,txtMobile;
    Spinner txtSubject;
    EditText txtMessage;
    String agcode;
	String agname;
	String mobile;
    String subject;
    String message;
    String ragname,ragcode,rmobileno;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		 btnOK = (Button) findViewById(R.id.btnOK);
		 btnCancel = (Button)findViewById(R.id.btnCancel);
	        txtAgcode= (EditText) findViewById(R.id.fbagcode);
	        txtAgcode.requestFocus();
	        txtAgName = (EditText)findViewById(R.id.fbagname);
	        txtMobile = (EditText)findViewById(R.id.fbmobile);
	        
	        DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(Feedback.this);
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
	        
	           txtAgcode.setText(ragcode);
	           txtAgName.setText(ragname);
	           txtMobile.setText(rmobileno);
	        
	        
	        
	        
	        
	        
	        
	        txtSubject = (Spinner) findViewById(R.id.fbtopic);
	        final String[] str4={"Policy Manager","ASKLIC","Registration","Premium Calculator","Others"};
	        ArrayAdapter<String> adp5=new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item,str4);
	        adp5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        txtSubject.setAdapter(adp5);
	        txtMessage = (EditText) findViewById(R.id.fbmessage);
 btnCancel.setOnClickListener(new OnClickListener() {
	        	
	            public void onClick(View v) {
	            	finish();
	            }
	            });	
	        btnOK.setOnClickListener(new OnClickListener() {
	        	
	            public void onClick(View v) {
	            	
	            	 agcode = txtAgcode.getText().toString();
	            	 agname = txtAgName.getText().toString();
	            	 mobile = txtMobile.getText().toString();
	                 subject = txtSubject.getSelectedItem().toString();
	                 message = txtMessage.getText().toString();
	            	
	            	
	            	
	            	if ( agcode.length()==0){
	            		txtAgcode.setError("Enter Agcode");
	            	}
	            	else if ( agname.length()==0){
	            		txtAgName.setError("Enter Name");
	            	}
	            	
	            	else if ( mobile.length()==0){
	            		txtMobile.setError("Enter Mobile Number");
	            	}
	            	else if (message.length()==0){
	            		txtMessage.setError("feedback is blank");
	            	}
	                else{
	            	new task().execute();
	
	            }
	            }    
	            });}	
	        
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feedback, menu);
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
	
	
	 class task extends AsyncTask<String, String, Void>
	    {
	     private ProgressDialog progressDialog = new ProgressDialog(Feedback.this);
	           protected void onPreExecute() {
	           progressDialog.setMessage("Submitting  Please wait...");
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
	                 String insertTableSQL = "INSERT INTO feedback"
	                			+ "(AgCode, AgName, Mobile,Topic,FeedbackMessage) VALUES"
	                			+ "(?,?,?,?,?)";
	                	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(insertTableSQL);
	                	preparedStatement.setString(1, agcode);
	                	preparedStatement.setString(2, agname);
	                	preparedStatement.setString(3, mobile);
	                	preparedStatement.setString(4, subject);
	                	preparedStatement.setString(5, message);
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
				return null;
	                }
	           protected void onPostExecute(Void v) {   
	           this.progressDialog.dismiss();
	           AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	    				Feedback.this);

	    			// set title
	    			alertDialogBuilder.setTitle("Job Over");

	    			// set dialog message
	    			alertDialogBuilder
	    				.setMessage("Click OK to Continue...!")
	    				.setCancelable(false)
	    				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    				   dialog.cancel();
	    				   finish();
	    					}
	    				  });
	    				// create alert dialog
	    				AlertDialog alertDialog = alertDialogBuilder.create();

	    				// show it
	    				alertDialog.show();
	      	   }
	           }
               }
