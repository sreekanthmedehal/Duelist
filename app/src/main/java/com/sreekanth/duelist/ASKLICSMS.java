package com.sreekanth.duelist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class ASKLICSMS extends Activity 
{
	Button btnSendSMS,btnViewSMS;
	EditText txtPolicyNo;
	EditText txtMessage;
	private Spinner spinner1, spinner2, spinner3;
	String sms_part1,sms_part2;
	private BroadcastReceiver sendBroadcastReceiver;
	private BroadcastReceiver deliveryBroadcastReceiver;
	String SENT = "SMS_SENT";
	String DELIVERED = "SMS_DELIVERED";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asklic);        
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        btnViewSMS = (Button)findViewById(R.id.btnViewSMS);
        txtPolicyNo = (EditText) findViewById(R.id.txtPolicyNo);
        txtMessage = (EditText) findViewById(R.id.txtMessage);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
    	spinner2 = (Spinner) findViewById(R.id.spinner2);
    	spinner3 = (Spinner) findViewById(R.id.spinner3);
        /*
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("sms_body", "Content of the SMS goes here..."); 
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivity(sendIntent);
        */
             
    	List<String> list = new ArrayList<String>();
    	list.add("Individual policy");
    	list.add("Pension policy");
    	
    	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
    		android.R.layout.simple_spinner_item, list);
    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner1.setAdapter(dataAdapter);
    	spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        
    	List<String> list2 = new ArrayList<String>();
    	list2.add("9222492224");
    	list2.add("56767877");
    	
    	ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
    		android.R.layout.simple_spinner_item, list2);
    	dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner3.setAdapter(dataAdapter2);
        
           
        
        
        
        
        
        
    	 btnViewSMS.setOnClickListener(new View.OnClickListener() 
         {
             public void onClick(View v) 
             {            	
            	 startActivity (new Intent(getApplicationContext(),SmsActivity.class));
             }
         });        
     
        
        
        
        
        
        btnSendSMS.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {      
            	String phoneNo = spinner3.getSelectedItem().toString();
            	String policyno = txtPolicyNo.getText().toString().trim();
            	 txtMessage.setText(sms_part1.concat(" ").concat(txtPolicyNo.getText().toString().trim()).concat(" ").concat(sms_part2));
      		   String message = txtMessage.getText().toString(); 
            	   
            	   if (policyno.length()==0){
            	    txtPolicyNo.setError("Policy Number Required...");
            	   }else {      	        	
            		  
                    sendSMS(phoneNo, message);
                    
            	   }
            }
        });        
   
    
   
    	/*
        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, test.class), 0);                
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, pi, null);        
        */
    	
    
   
    	
        //---when the SMS has been sent---
        sendBroadcastReceiver = new BroadcastReceiver()
        {
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
        };//, new IntentFilter(SENT));
        
        //---when the SMS has been delivered---
			 deliveryBroadcastReceiver = new BroadcastReceiver()
			    {
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
        };// new IntentFilter(DELIVERED));        
        registerReceiver(deliveryBroadcastReceiver, new IntentFilter(DELIVERED));
        registerReceiver(sendBroadcastReceiver , new IntentFilter(SENT));}
        //---sends a SMS message to another device---
        private void sendSMS(String phoneNumber, String message)
        {  
        	String SENT = "SMS_SENT";
            String DELIVERED = "SMS_DELIVERED";
            PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);
            
            PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);               
    }  
    
    public class CustomOnItemSelectedListener implements OnItemSelectedListener {
    	 
    	  public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
    	/*	Toast.makeText(parent.getContext(), 
    			"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
    			Toast.LENGTH_SHORT).show();*/
    		List<String> list1 = new ArrayList<String>();
    		if(String.valueOf(parent.getItemAtPosition(pos).toString()).equals("Individual policy")){
    			
   
    	    	list1.add("premium due date");
    	    	list1.add("If policy is lapsed, Revival amount payable");
    	    	list1.add("Amount of Bonus vested");
    	    	list1.add("Amount available as Loan");
    	    	list1.add("Details of Nomination");
    	    	sms_part1="ASKLIC";
    			
    			
    			
    		}else{
    	
    		    //			 PREMIUM/REVIVAL/BONUS/LOAN/NOM
    		    	    	list1.add("IPP Policy Status");
    		    	    	list1.add("Existence Certificate Due");
    		    	    	list1.add("Last Annuity Released Date");
    		    	    	list1.add("Annuity Payment thru (CHQ/ECS/NEFT)");
    		    	    	list1.add("Annuity Amount");
    		    	    	list1.add("Cheque Return Information");
    		    	    	sms_part1="LICPension";
    		}
    		
    		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(ASKLICSMS.this,
    	    		android.R.layout.simple_spinner_item, list1);
    	    	dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	    	spinner2.setAdapter(dataAdapter1);
    	    	spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener1());	
    		
    		
    	  }
    	 
    	  @Override
    	  public void onNothingSelected(AdapterView<?> arg0) {
    		// TODO Auto-generated method stub
    	  }
    	 
    	}
    public class CustomOnItemSelectedListener1 implements OnItemSelectedListener {
   	 
  	  public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
  		/*Toast.makeText(parent.getContext(), 
  			"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
  			Toast.LENGTH_SHORT).show();*/
  		 //			 PREMIUM/REVIVAL/BONUS/LOAN/NOM
  		if (parent.getItemAtPosition(pos).toString().equals("premium due date")){sms_part2="PREMIUM";}
  		if (parent.getItemAtPosition(pos).toString().equals("If policy is lapsed, Revival amount payable")){sms_part2="REVIVAL";}
  		if (parent.getItemAtPosition(pos).toString().equals("Amount of Bonus vested")){sms_part2="BONUS";}
  		if (parent.getItemAtPosition(pos).toString().equals("Amount available as Loan")){sms_part2="LOAN";}
  		if (parent.getItemAtPosition(pos).toString().equals("Details of Nomination")){sms_part2="NOM";}
  		
//		[STAT /ECDUE/ANNPD/PDTHRU/AMOUNT/CHQRET]		
  		if (parent.getItemAtPosition(pos).toString().equals("IPP Policy Status")){sms_part2="STAT";}
  		if (parent.getItemAtPosition(pos).toString().equals("Existence Certificate Due")){sms_part2="ECDUE";}
  		if (parent.getItemAtPosition(pos).toString().equals("Last Annuity Released Date")){sms_part2="ANNPD";}
  		if (parent.getItemAtPosition(pos).toString().equals("Annuity Payment thru (CHQ/ECS/NEFT)")){sms_part2="PDTHRU";}
  		if (parent.getItemAtPosition(pos).toString().equals("Annuity Amount")){sms_part2="AMOUNT";}
  		if (parent.getItemAtPosition(pos).toString().equals("Cheque Return Information")){sms_part2="CHQRET";}
  		
  		
  		
  		
  		
  		
    txtMessage.setText(sms_part1.concat(" ").concat(txtPolicyNo.getText().toString().trim()).concat(" ").concat(sms_part2));

  	  }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
  	  
    }
    @Override
    protected void onStop()
    {
    	super.onStop();
    	try { unregisterReceiver(sendBroadcastReceiver);
        unregisterReceiver(deliveryBroadcastReceiver);} catch(IllegalArgumentException e) { e.printStackTrace();}
       
        
    }
}