package com.sreekanth.duelist;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class SendSMSActivity extends Activity {
private String extraData;
	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;
	 int myNum = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
	  ActionBar actionBar = getActionBar();
  	  actionBar.setDisplayHomeAsUpEnabled(true);
		//	setContentView(R.layout.sms_item);
			
			
		 Intent sender=getIntent();
		  extraData=sender.getExtras().getString("ID");
          extraData = extraData.trim();
         

          try {
              myNum = Integer.parseInt(extraData);
          } catch(NumberFormatException nfe) {
            // Handle parse error.
          }
          Cursor ddetails = getDatab(myNum);
          Cursor agdetails = getDataag();
          
          String smspolno =  ddetails.getString(ddetails.getColumnIndexOrThrow("bmpolno"));
          String smsfup = ddetails.getString(ddetails.getColumnIndexOrThrow("bmfup"));
          String smsprem = ddetails.getString(ddetails.getColumnIndexOrThrow("bmprem"));
          String smsagname = agdetails.getString(agdetails.getColumnIndexOrThrow("agname"));
          String smsagtel = agdetails.getString(agdetails.getColumnIndexOrThrow("tel"));
          String smsbody = "Policy No : " + smspolno + "\n" + "Due : " + smsfup + "\n" + "Instl Prem : " + smsprem
        		  + "\n" + "Please Pay Premiums at LIC Office" + "\n" + smsagname + "\n";
	//	buttonSend = (Button) findViewById(R.id.buttonSend);
	//	textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
	//	textSMS = (EditText) findViewById(R.id.editTextSMS);
    //     textSMS.setText(smsbody);
	//	buttonSend.setOnClickListener(new OnClickListener() {
 
		//	@Override
		//	public void onClick(View v) {
 
		//	  String phoneNo = textPhoneNo.getText().toString();
		//	  String sms = textSMS.getText().toString();
      
			//  try {
			//	SmsManager smsManager = SmsManager.getDefault();
			//	smsManager.sendTextMessage(phoneNo, null, sms, null, null);
			//	Toast.makeText(getApplicationContext(), "SMS Sent!",
			//				Toast.LENGTH_LONG).show();
			//  } catch (Exception e) {
			//	Toast.makeText(getApplicationContext(),
			//		"SMS faild, please try again later!",
			//		Toast.LENGTH_LONG).show();
			//	e.printStackTrace();
			//  }
 
			  
				try {
					 
				     Intent sendIntent = new Intent(Intent.ACTION_VIEW);
				     sendIntent.putExtra("sms_body", smsbody); 
				     sendIntent.setType("vnd.android-dir/mms-sms");
				     startActivity(sendIntent);

				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
						"SMS failed, please try again later!",
						Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			  
		
			}
	//	});
//	}
	 private Cursor getDataag() {
		// TODO Auto-generated method stub
			DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
		     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
		     
		        String sql = "";
		        sql = "SELECT agname,tel FROM agentdata";
		      
		     
		        
		        Cursor agetails = database.rawQuery(sql, null);
		        agetails.moveToFirst();
		        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
		        return agetails;
		 
		 
		 
		
	}
	public Cursor getDatab(int smsid) {
	    	DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
	     
	        String sql = "";
	        sql = "SELECT * FROM bmbillmast WHERE _id = " + smsid;
	      
	     
	        
	        Cursor ddetails = database.rawQuery(sql, null);
	        ddetails.moveToFirst();
	        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
	        return ddetails;
	   
	    }
	 public void close() {
		 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
   database.close();
} 	
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        // Respond to the action bar's Up/Home button
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(this);
	            return true;
	     
	     
	            
	        }
	        return super.onOptionsItemSelected(item);
	    }
}
