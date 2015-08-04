package com.sreekanth.duelist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class BlankActvity extends Activity {
	EditText ename,emobileno,emailid;
	TextView eplan,ematsa;
	EditText eage;
	EditText eterm;
	EditText esa;
	EditText ebonrat;
	TextView ebonus;
	TextView etotal;
	Spinner emode;
	TextView etbpremy;
	TextView etbpremh;
	TextView etbpremq;
	TextView etbpremm;
	TextView etbprems;
	TextView mixStateofToggleButton;
	Button calc;
	Button benefits;
	Button elegibility;
	int plan;
	int age;
	int term;
	private ToggleButton toggleButtonmix, toggleButtonyly, toggleButtonhly, toggleButtonqly, toggleButtonmly, toggleButtonsss;
	long tbprem;
	long sa;
	int yearlyprem;
    int hlyprem;
    int qlyprem;
    int mlyprem;
    int sssprem;
    String GPlan,GName,GEmail,GMobile,GAge,GTerm,GSa;
    ImageButton button1;
    String strFile, address, subject, emailtext;
    LinearLayout llyly,llhly,llqly,llmly,llsss;
    EditText sms;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blank_actvity);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
  	  String tplan = preferences.getString("TPlan","");
  	  String tname = preferences.getString("TName","");
  	  String temail = preferences.getString("TEmail","");
     String tmobile = preferences.getString("TMobile","");
     String tage = preferences.getString("TAge","");
     String tterm = preferences.getString("TTerm","");
     String tsa = preferences.getString("TSa","");
  	  if(!tplan.equalsIgnoreCase(""))
  	  {
  		GPlan = tplan;
  		GName = tname;
  		GEmail = temail;
  		GMobile = tmobile;
  		GAge = tage;
  		GTerm = tterm;
  		GSa = tsa;
  	  }	
  	  
  	llsss = (LinearLayout)findViewById(R.id.ssslayout);
  	toggleButtonsss = (ToggleButton) findViewById(R.id.toggleButton9);
  	toggleButtonsss.setChecked(true);
  	toggleButtonsss.setOnCheckedChangeListener(new OnCheckedChangeListener() {
  		
  		   @Override
  		   public void onCheckedChanged(CompoundButton buttonView,
  		     boolean isChecked) {
  		    
  		    if(isChecked){
  		     llsss.setVisibility(View.VISIBLE);
  		    }else{
  		    	llsss.setVisibility(View.GONE);
  		    }

  		   }
  		  });
  	  
  	  
  	llmly = (LinearLayout)findViewById(R.id.mlylayout);
  	toggleButtonmly = (ToggleButton) findViewById(R.id.toggleButton8);
  	toggleButtonmly.setChecked(true);
  	toggleButtonmly.setOnCheckedChangeListener(new OnCheckedChangeListener() {
  		
  		   @Override
  		   public void onCheckedChanged(CompoundButton buttonView,
  		     boolean isChecked) {
  		    
  		    if(isChecked){
  		     llmly.setVisibility(View.VISIBLE);
  		    }else{
  		    	llmly.setVisibility(View.GONE);
  		    }

  		   }
  		  });
  	  
  	  
  	  
  	llqly = (LinearLayout)findViewById(R.id.qlylayout);
	toggleButtonqly = (ToggleButton) findViewById(R.id.toggleButton7);
	toggleButtonqly.setChecked(true);
	toggleButtonqly.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		   @Override
		   public void onCheckedChanged(CompoundButton buttonView,
		     boolean isChecked) {
		    
		    if(isChecked){
		     llqly.setVisibility(View.VISIBLE);
		    }else{
		    	llqly.setVisibility(View.GONE);
		    }

		   }
		  });
  	  
  	  
  	  
  	 llhly = (LinearLayout)findViewById(R.id.hlylayout);
		toggleButtonhly = (ToggleButton) findViewById(R.id.toggleButton6);
		toggleButtonhly.setChecked(true);
		toggleButtonhly.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			   @Override
			   public void onCheckedChanged(CompoundButton buttonView,
			     boolean isChecked) {
			    
			    if(isChecked){
			     llhly.setVisibility(View.VISIBLE);
			    }else{
			    	llhly.setVisibility(View.GONE);
			    }

			   }
			  });
  	  
  	  
  	    llyly = (LinearLayout)findViewById(R.id.ylylayout);
		toggleButtonyly = (ToggleButton) findViewById(R.id.toggleButton5);
		toggleButtonyly.setChecked(true);
		toggleButtonyly.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			   @Override
			   public void onCheckedChanged(CompoundButton buttonView,
			     boolean isChecked) {
			    
			    if(isChecked){
			     llyly.setVisibility(View.VISIBLE);
			    }else{
			    	llyly.setVisibility(View.GONE);
			    }

			   }
			  });
  	    mixStateofToggleButton = (TextView)findViewById(R.id.mixingtext);
		toggleButtonmix = (ToggleButton) findViewById(R.id.toggleButton4);
	//	toggleButtonmix.setChecked(false);
		toggleButtonmix.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			   @Override
			   public void onCheckedChanged(CompoundButton buttonView,
			     boolean isChecked) {
			    
			    if(isChecked){
			     mixStateofToggleButton.setText("MIXING ON");
			    }else{
			     mixStateofToggleButton.setText("MIXING OFF");
			    }

			   }
			  });
        ename = (EditText)findViewById(R.id.editText3); 
        ename.setText(GName);
        emobileno = (EditText)findViewById(R.id.editText3a);
        emobileno.setText(GMobile);
        emailid = (EditText)findViewById(R.id.editText3b);
        emailid.setText(GEmail);      
		eplan = (TextView)findViewById(R.id.txtplan);
		eplan.setText(GPlan);
		etbpremy = (TextView)findViewById(R.id.textView1);
		etbpremh= (TextView)findViewById(R.id.textView2);
		etbpremq= (TextView)findViewById(R.id.textView3);
		etbpremm= (TextView)findViewById(R.id.textView4);
		etbprems= (TextView)findViewById(R.id.textView5);
	    
	    eage = (EditText)findViewById(R.id.editText2);
	    eage.setText(GAge);
	     
	    eterm = (EditText)findViewById(R.id.spinner2);
	    eterm.setText(GTerm);
	     
        esa = (EditText)findViewById(R.id.editText1);
        esa.setText(GSa);
        ematsa = (TextView)findViewById(R.id.matsa);
         emode = (Spinner)findViewById(R.id.spinner3);
         final String[] str2={"Yearly","HalfYearly","Quarterly","MOnthly"};
         emode.setAdapter(new CustomSpinnerAdapter(this,android.R.layout.simple_spinner_dropdown_item,str2,"Yearly"));
         emode.setVisibility(View.GONE);
     	 ebonrat = (EditText)findViewById(R.id.bonrat);
    	 ebonus= (TextView)findViewById(R.id.bonus);
    	 etotal= (TextView)findViewById(R.id.total);
         
         
         
	     calc = (Button)findViewById(R.id.button1);
	     calc.setOnClickListener(new OnClickListener(){
	    	 
	    	
			@Override
			public void onClick(View v) {
				
			     
			     if (eage.getText().length()==0){
			    	 eage.setError("Invalid Age"); 
			     }
			     else if(eterm.getText().length()==0  )  {
			    	 eterm.setError("Invalid Term");
			     } else if(esa.getText().length()==0) {
			    	 esa.setError("Invalid SA");
			     } else {
			    	 plan = (int) Integer.parseInt(eplan.getText().toString());
				     age = (int)  Integer.parseInt(eage.getText().toString());
				     term = (int) Integer.parseInt(eterm.getText().toString());	
				     sa = (int) Integer.parseInt(esa.getText().toString().trim());
			     }
			     
			     if (age < 8 || age > 55){
			    		AlertDialog.Builder builder1 = new AlertDialog.Builder(BlankActvity.this);
		  			  
		  			    builder1.setTitle("Inavlid Age!");
		  			    builder1.setCancelable(true);
		  			    builder1.setNeutralButton("Continue....",
		  			            new DialogInterface.OnClickListener() {
		  			        public void onClick(DialogInterface dialog, int id) {
		  			            dialog.cancel();
		  			            
		  			        }
		  			    });

		  			    AlertDialog alert11 = builder1.create();
		  			    alert11.show();
			    	 
			     } else if(term <9 || term > 35   )  {
			    	 AlertDialog.Builder builder1 = new AlertDialog.Builder(BlankActvity.this);
		  			  
		  			    builder1.setTitle("Inavlid Term!");
		  			    builder1.setCancelable(true);
		  			    builder1.setNeutralButton("Continue....",
		  			            new DialogInterface.OnClickListener() {
		  			        public void onClick(DialogInterface dialog, int id) {
		  			            dialog.cancel();
		  			            
		  			        }
		  			    });

		  			    AlertDialog alert11 = builder1.create();
		  			    alert11.show();
			     } else if(sa < 100000) {
			    	 AlertDialog.Builder builder1 = new AlertDialog.Builder(BlankActvity.this);
		  			  
		  			    builder1.setTitle("Inavlid Sa!");
		  			    builder1.setCancelable(true);
		  			    builder1.setNeutralButton("Continue....",
		  			            new DialogInterface.OnClickListener() {
		  			        public void onClick(DialogInterface dialog, int id) {
		  			            dialog.cancel();
		  			            
		  			        }
		  			    });

		  			    AlertDialog alert11 = builder1.create();
		  			    alert11.show();
			     }
			    	 else {
			     
				// TODO Auto-generated method stub
	        DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(BlankActvity.this);
	        SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
	        String sql = "";
	        sql = "SELECT prem FROM tbprem WHERE (plan = " + plan + " and age = "+ age + " and term = "+ term +");";
	        Cursor ddetails = database.rawQuery(sql, null);
	        ddetails.moveToFirst();
	        tbprem = ddetails.getLong(ddetails.getColumnIndexOrThrow("prem")); 
	        
	        
	        
	        ddetails.close();
	        database.close();
	        stockSQLHelper.close();
	       
	         yearlyprem = (int) (tbprem*sa/1000);
	         yearlyprem = (int) ((int) yearlyprem + (yearlyprem*3.09/100))- ModeYRebate()-HSaRebate();
	         hlyprem = (int) ((tbprem*sa/1000) - ModeHRebate() - HSaRebate())/2;
	         qlyprem = (int)((tbprem*sa/1000) -HSaRebate())/4;
	         mlyprem = (int) ((tbprem*sa/1000)- HSaRebate())/12;
	         sssprem = (int) ((tbprem*sa/1000)- HSaRebate())/12;
	        
	        etbpremy.setText(String.valueOf(yearlyprem));
	        etbpremh.setText(String.valueOf(hlyprem + (hlyprem*3.09/100)));
	        etbpremq.setText(String.valueOf(qlyprem + (qlyprem*3.09/100)));
	        etbpremm.setText(String.valueOf(mlyprem + (mlyprem*3.09/100)));
	        etbprems.setText(String.valueOf(sssprem + (sssprem*3.09/100)));
	        ematsa.setText(esa.getText().toString());
	        ebonus.setText(String.valueOf(Integer.parseInt(ebonrat.getText().toString())*sa/1000*term));
	        etotal.setText(String.valueOf(Integer.parseInt(ematsa.getText().toString())+Integer.parseInt(ebonus.getText().toString())));
			}
			}
			
	     });
	     button1 = (ImageButton) findViewById(R.id.album_overflow);
	     
	     button1.setOnClickListener(new OnClickListener() {  

	           @Override  
	           public void onClick(View v) {  
	            //Creating the instance of PopupMenu  
	            PopupMenu popup = new PopupMenu(BlankActvity.this, button1);  
	            //Inflating the Popup using xml file  
	            popup.getMenuInflater().inflate(R.menu.blank_actvity, popup.getMenu());  

	            //registering popup with OnMenuItemClickListener  
	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
	            public boolean onMenuItemClick(MenuItem item) {  
	         //     Toast.makeText(BlankActvity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
	              if (item.getTitle().toString().equals("Commission")){
	            	AlertDialog.Builder builder1 = new AlertDialog.Builder(BlankActvity.this);
	  			    WebView wv = new WebView(getApplicationContext());
	  			    String html = LoadData("Commission");
	  			    wv.loadData(html, "text/html", "UTF-8");
	  			    builder1.setView(wv);
	  			   // builder1.setView(s_view);
	  			    builder1.setTitle("Commission - 814 Plan!");
	  			    builder1.setCancelable(true);
	  			    builder1.setNeutralButton("Continue....",
	  			            new DialogInterface.OnClickListener() {
	  			        public void onClick(DialogInterface dialog, int id) {
	  			            dialog.cancel();
	  			            
	  			        }
	  			    });

	  			    AlertDialog alert11 = builder1.create();
	  			    alert11.show();			  
	              }
	              if (item.getTitle().toString().equals("Share")){ 
	            	  final Dialog d = new Dialog(BlankActvity.this);
	            	  d.requestWindowFeature(Window.FEATURE_NO_TITLE);
	                  d.setContentView(R.layout.dialog);
	                  d.show();           
	                  Button smsButton = (Button) d.findViewById(R.id.button1);
//	                  // if decline button is clicked, close the custom dialog
	                  smsButton.setOnClickListener(new OnClickListener() {
	                      @Override
	                      public void onClick(View v) {
	                          // Close dialog
	                          d.dismiss();  
	            	  
	            	  final Dialog dialog = new Dialog(BlankActvity.this);
	                  // Include dialog.xml file
	                  dialog.setContentView(R.layout.smsmanager);
	                  // Set dialog title
	                  dialog.setTitle("SMS");
	                  // set values for custom dialog components - text, image and button
	                  final TextView text = (TextView) dialog.findViewById(R.id.editTextPhoneNo);
	              //    text.setText("9849163851");
	                  sms = (EditText) dialog.findViewById(R.id.editTextSMS);
	                  sms.setText( "Name : " + ename.getText().toString()+ "\n" +
	                		  "Plan : " +eplan.getText().toString()+ "\n" +
	                		  "Term : " +eterm.getText().toString()+ "\n" +
	                		  "Sum Assu : " +esa.getText().toString()+ "\n" +
	                		  "Yly Prem : " +etbpremy.getText().toString()+ "\n" +
	                		  "Hly Prem : " +etbpremh.getText().toString()+ "\n" +
	                		  "Qly Prem : " +etbpremq.getText().toString()+ "\n" +
	                		  "Monthly/ECS  : " +etbpremm.getText().toString()+ "\n" +
	                		  "SSS : " + etbprems.getText().toString()+ "\n" +
	                		  "Mat Sa : " + ematsa.getText().toString() + "\n"+ 
	                		  "Bonus : " + ebonus.getText().toString() + "\n"+     
	                		  "Total : " + etotal.getText().toString() + "\n"+ 
	                		"\n"  );
	                  dialog.show();
	                   
	                  Button declineButton = (Button) dialog.findViewById(R.id.buttonSend);
	                  // if decline button is clicked, close the custom dialog
	                  declineButton.setOnClickListener(new OnClickListener() {
	                      @Override
	                      public void onClick(View v) {
	                          // Close dialog
	                          dialog.dismiss();
	                          try {
	              				SmsManager smsManager = SmsManager.getDefault();
	              				smsManager.sendTextMessage(text.getText().toString(), null, sms.getText().toString(), null, null);
	              				Toast.makeText(getApplicationContext(), "SMS Sent!",
	              							Toast.LENGTH_LONG).show();
	              			  } catch (Exception e) {
	              				Toast.makeText(getApplicationContext(),
	              					"SMS faild, please try again later!",
	              					Toast.LENGTH_LONG).show();
	              				e.printStackTrace();
	              			  }
	                          }
	                          });  
	                          }
	                          });  
	                  Button gmailButton = (Button) d.findViewById(R.id.button2);
//	                  // if decline button is clicked, close the custom dialog
	                  gmailButton.setOnClickListener(new OnClickListener() {
	                      @Override
	                      public void onClick(View v) {
	                          // Close dialog
	                          d.dismiss();  
	  	                      gmail();
	                      }
	                      }); 
	                      }
	              if (item.getTitle().toString().equals("Save")){   
	            	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(BlankActvity.this);
	     			 SQLiteDatabase database = stockSQLHelper.getWritableDatabase();  		
	     			 ContentValues values;
	     			 values = new ContentValues();              
	     		     values.put(PremCalc.REG_NAME, ename.getText().toString());
	     		     values.put(PremCalc.REG_PLAN,eplan.getText().toString());
	     		     values.put(PremCalc.REG_AGE, eage.getText().toString());
	     		     values.put(PremCalc.REG_SA, esa.getText().toString());
	     		     values.put(PremCalc.REG_TERM, eterm.getText().toString());
	     		     values.put(PremCalc.REG_MOBILE, emobileno.getText().toString());
	     		     values.put(PremCalc.REG_EMAIL, emailid.getText().toString());
	     		    
	                 database.insert(PremCalc.TABLE_STOCK, null, values);
	                 database.close();
	                 stockSQLHelper.close();
	               	 AlertDialog.Builder builder1 = new AlertDialog.Builder(BlankActvity.this);
	                 builder1.setMessage(ename.getText().toString()+" Record Saved successfully ...");
	                 builder1.setCancelable(true);
	                 builder1.setPositiveButton("Continue ...",
	                         new DialogInterface.OnClickListener() {
	                         public void onClick(DialogInterface dialog, int id) {
	                         dialog.cancel();
	            //           BlankActivity.this.finish();      
	                     }
	                     });
	                 AlertDialog alert11 = builder1.create();
	                 alert11.show();
	              }
	              
	              
	              
	              
	              
	              
	              
	              
	              
	              
	              
	              
	              return true;  
	             }  
	            });  

	            popup.show();//showing popup menu  
	           }  
	          });
	     
	    benefits = (Button)findViewById(R.id.button4); 
	    benefits.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder1 = new AlertDialog.Builder(BlankActvity.this);
			    WebView wv = new WebView(getApplicationContext());
			    String html = LoadData("stripes.html");
			    wv.loadData(html, "text/html", "UTF-8");
			    builder1.setView(wv);
			   // builder1.setView(s_view);
			    builder1.setTitle("Benefits - 814 Plan!");
			    builder1.setCancelable(true);
			    builder1.setNeutralButton("Continue....",
			            new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int id) {
			            dialog.cancel();
			            
			        }
			    });

			    AlertDialog alert11 = builder1.create();
			    alert11.show();		
			}
			});
	    	
	    
	    elegibility = (Button)findViewById(R.id.button3); 
	    elegibility.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder1 = new AlertDialog.Builder(BlankActvity.this);
			    WebView wv = new WebView(getApplicationContext());
			    String html = LoadData("stripes.html");
			    wv.loadData(html, "text/html", "UTF-8");
			    builder1.setView(wv);
			   // builder1.setView(s_view);
			    builder1.setTitle("Benefits - 814 Plan!");
			    builder1.setCancelable(true);
			    builder1.setNeutralButton("Continue....",
			        new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int id) {
			        dialog.cancel();
			        }
			    });
			    AlertDialog alert11 = builder1.create();
			    alert11.show();		
			}
			});
	  
	  
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	   }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tabbed, menu);
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
		
//		else if (id == R.id.album_loaddata){
//		startActivity (new Intent(getApplicationContext(), Calcstore.class));
//		return true;
//		}
		return super.onOptionsItemSelected(item);
	}
	public String refreshcontentname(){
		String totname = ename.getText().toString();
		return totname;
	}
	public String refreshcontentplan(){
		String totplan = eplan.getText().toString();
		return totplan;
	}
	public String refreshcontentterm(){
		String totterm = eterm.getText().toString();
		return totterm;
	}
	public int refreshcontenty(){
		int totpremy = (int) Double.parseDouble(etbpremy.getText().toString());
		return totpremy;
	}
	public int refreshcontenth(){
		int totpremh = (int) Double.parseDouble(etbpremh.getText().toString());
		return totpremh;
	}
	public int refreshcontentq(){
		int totpremq = (int) Double.parseDouble(etbpremq.getText().toString());
		return totpremq;
	}
	public int refreshcontentm(){
		int totpremm = (int) Double.parseDouble(etbpremm.getText().toString());
		return totpremm;
	}
	public int refreshcontents(){
		int totprems = (int) Double.parseDouble(etbprems.getText().toString());
		return totprems;
	}
	public int refreshcontentsa(){
		int totpremsa = (int) Integer.parseInt(esa.getText().toString());
		return totpremsa;
	}
	public int ModeYRebate(){
		int intYRebate = 0;	
		intYRebate = (int) (tbprem*2/100*sa/1000);
		return intYRebate;
	}
	public int ModeHRebate(){
		int intHRebate = 0;	
		intHRebate = (int) (tbprem*1/100*sa/1000);
		return intHRebate;
	}
	public int HSaRebate(){
		int intSRebate = 0;
		if (Integer.parseInt(esa.getText().toString()) >= 500000 ){
			intSRebate = Integer.parseInt(esa.getText().toString())/1000 * 3;
		} else if (Integer.parseInt(esa.getText().toString()) >= 200000 && Integer.parseInt(esa.getText().toString()) <= 495000)
		{
		   intSRebate = Integer.parseInt(esa.getText().toString())/1000 * 2;
		}
		return intSRebate;
	}
	public String mixing(){
		String mixing = mixStateofToggleButton.getText().toString();
		return mixing;
	}
	public int refreshcontentmatsa(){
		int totmatsa = (int) Integer.parseInt(ematsa.getText().toString());
		return totmatsa;
	}
	public int refreshcontentbonus(){
		int totbonus = (int) Integer.parseInt(ebonus.getText().toString());
		return totbonus;
	}
	public int refreshcontenttotal(){
		int total = (int) Integer.parseInt(etotal.getText().toString());
		return total;
	}
	 public String LoadData(String inFile) {
	        String tContents = "";
	    try {
	        InputStream stream = getAssets().open(inFile);
	        int size = stream.available();
	        byte[] buffer = new byte[size];
	        stream.read(buffer);
	        stream.close();
	        tContents = new String(buffer);
	    } catch (IOException e) {
	        // Handle exceptions here
	    }
	    return tContents;
	 }
	 private void gmail(){
//888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
		try {
			strFile = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/temp";
			File file = new File(strFile);
			if (!file.exists())
			file.mkdirs();
			strFile = strFile + "/report.html";
			createFile();
			
		//	Log.i(getClass().getSimpleName(), "send  task - start");
			
			final Intent emailIntent = new Intent(
			android.content.Intent.ACTION_SEND);
			
			address = "";
			subject = "Plan Calculation";
			emailtext = sms.getText().toString();
			
			emailIntent.setType("plain/text");
			 
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
			new String[] { address });
			 
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
			 
			/*emailIntent.putExtra(Intent.EXTRA_STREAM,
			Uri.parse("file:" + strFile));*/
			 
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailtext);
			 
			startActivity(Intent
			.createChooser(emailIntent, "Send mail..."));
			 
			} catch (Throwable t) {
			Toast.makeText(BlankActvity.this, "Request failed: " + t.toString(),
			Toast.LENGTH_LONG).show();
			}		
		    }
	//888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888    
	
private void createFile() {
try {
String data = "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/><html><body style='tab-interval: .5in'><div class=\"Section1\"><div>";
data += "<p class=\"MsoNormal\" dir=\"LTR\" style=\"text-align: left; unicode-bidi: embed\"><span lang=\"AR-EG\">";
FileOutputStream fos = new FileOutputStream(strFile);

Writer out = new OutputStreamWriter(fos, "UTF-8");

data += "Email data" + "<br />";
data += "bla bla bla" + "<br />";
data += "Footer" + "<br />";

data += "</span></p></div></body></html>";
out.write(data);
out.flush();
out.close();

} catch (Throwable t) {
Toast.makeText(this, "Request failed: " + t.toString(),
Toast.LENGTH_LONG).show();
}
}











}