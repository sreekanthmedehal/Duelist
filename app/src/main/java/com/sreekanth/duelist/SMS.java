package com.sreekanth.duelist;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SMS extends Activity 
{
	Button btnSendSMS,btnCancelSMS;
	ImageButton btnContactsList;
	EditText txtPhoneNo;
	EditText txtMessage;
    String mobno,smsbody;
    public static final int PICK_CONTACT    = 1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smsmain); 
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        btnCancelSMS = (Button)findViewById(R.id.btnCancelSMS);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
        txtMessage = (EditText) findViewById(R.id.txtMessage);
        btnContactsList = (ImageButton)findViewById(R.id.btnContacts);
        Bundle extras = getIntent().getExtras(); 
            if (extras != null) {
            mobno = extras.getString("smsmobno");
            smsbody = extras.getString("smsbody");
            txtPhoneNo.setText(mobno);
            txtMessage.setText(smsbody);
        } 
          
            btnContactsList.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                	Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);      	
             //       Intent intent = new Intent(Intent.ACTION_PICK, People.CONTENT_URI);
                    startActivityForResult(intent, PICK_CONTACT);	
                }
            });
             
        
          
            btnCancelSMS.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        
       
       
        /*
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("sms_body", "Content of the SMS goes here..."); 
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivity(sendIntent);
        */
                
        btnSendSMS.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {            	
            	String phoneNo = txtPhoneNo.getText().toString();
            	String message = txtMessage.getText().toString();             	
                if (phoneNo.length()>0 && message.length()>0)                
                    sendSMS(phoneNo, message);                
                else
                	Toast.makeText(getBaseContext(), 
                        "Please enter both phone number and message.", 
                        Toast.LENGTH_SHORT).show();
            }
        });        
    }
    
    //---sends a SMS message to another device---
    private void sendSMS(String phoneNumber, String message)
    {      
    	/*
        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, test.class), 0);                
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, pi, null);        
        */
    	
    	String SENT = "SMS_SENT";
    	String DELIVERED = "SMS_DELIVERED";
    	
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
            new Intent(SENT), 0);
        
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
            new Intent(DELIVERED), 0);
    	
        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode())
				{
				    case Activity.RESULT_OK:
					    Toast.makeText(getBaseContext(), "SMS sent", 
					    		Toast.LENGTH_SHORT).show();
					    break;
				    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					    Toast.makeText(getBaseContext(), "Generic failure", 
					    		Toast.LENGTH_SHORT).show();
					    break;
				    case SmsManager.RESULT_ERROR_NO_SERVICE:
					    Toast.makeText(getBaseContext(), "No service", 
					    		Toast.LENGTH_SHORT).show();
					    break;
				    case SmsManager.RESULT_ERROR_NULL_PDU:
					    Toast.makeText(getBaseContext(), "Null PDU", 
					    		Toast.LENGTH_SHORT).show();
					    break;
				    case SmsManager.RESULT_ERROR_RADIO_OFF:
					    Toast.makeText(getBaseContext(), "Radio off", 
					    		Toast.LENGTH_SHORT).show();
					    break;
				}
			}
        }, new IntentFilter(SENT));
        
        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode())
				{
				    case Activity.RESULT_OK:
					    Toast.makeText(getBaseContext(), "SMS delivered", 
					    		Toast.LENGTH_SHORT).show();
					    break;
				    case Activity.RESULT_CANCELED:
					    Toast.makeText(getBaseContext(), "SMS not delivered", 
					    		Toast.LENGTH_SHORT).show();
					    break;					    
				}
			}
        }, new IntentFilter(DELIVERED));        
    
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);  }             
        @Override
        public void onActivityResult(int reqCode, int resultCode, Intent data) {
                super.onActivityResult(reqCode, resultCode, data);
                switch (reqCode) {
                        case (PICK_CONTACT):
                        	  if (resultCode == Activity.RESULT_OK)
                              {
                        		  Uri contactData = data.getData();
                                  Cursor contactCursor = getContentResolver().query(contactData,
                                                  new String[] { ContactsContract.Contacts._ID }, null, null,
                                                  null);
                                  String id = null;
                                  if (contactCursor.moveToFirst()) {
                                          id = contactCursor.getString(contactCursor
                                                          .getColumnIndex(ContactsContract.Contacts._ID));
                                  }
                                  contactCursor.close();
                                  String phoneNumber = null;
                                  Cursor phoneCursor = getContentResolver().query(
                                                  ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                                  new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER },
                                                  ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ? ",
                                                  new String[] { id }, null);
                                  if (phoneCursor.moveToFirst()) {
                                          phoneNumber = phoneCursor
                                                          .getString(phoneCursor
                                                                          .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                  }
                                  phoneCursor.close();
txtPhoneNo.setText(phoneNumber);
                                break;
                }
        }
        }
}