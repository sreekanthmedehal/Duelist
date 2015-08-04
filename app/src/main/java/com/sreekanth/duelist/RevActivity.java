package com.sreekanth.duelist;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class RevActivity extends Activity {
	private RelativeLayout mainRelativeLayout;
	private ScrollView linearLayout;
	Button mButton;
	EditText mEdit;
	TextView mText;
	String ipadd;
	 SQLiteDatabase database;
	 String datetime;
	 String datetimedd;
	 Date date;
	 String FUP1;
	 String datetimetoday;
	 DateTime FUP1cdate;
	 DateTime FUP2cdate;
	 int mode_days,mode_months;
	 double rev_factor = 1.00;
	 ArrayList<YlyBean> yitems = new ArrayList<YlyBean>();
	   ArrayList<HlyBean> hitems = new ArrayList<HlyBean>();
	   ArrayList<QlyBean> qitems = new ArrayList<QlyBean>();
	   ArrayList<MlyBean> mitems = new ArrayList<MlyBean>();
	   int no_of_inst;
	   DataViewerSQLiteHelper stockSQLHelper; 
	   
	   String PolicyNo;
	   String Prem;
       int tot_prem;
       double late_fee;
       double tot_rev_amt;
       String sAgName;
       String sAgtel;
       TextView result;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar abar = getActionBar();
        abar.hide();
        setContentView(R.layout.activity_rev);
        Bundle extras = getIntent().getExtras(); 
        String userPolicyNo = "";

        if (extras != null) {
            userPolicyNo = extras.getString("revpolno");
            // and get whatever type user account id is
        }
        mButton = (Button)findViewById(R.id.button01);
        
    	mEdit   = (EditText)findViewById(R.id.editText01);
    	if (userPolicyNo.length()>0){
    		mEdit.setText(userPolicyNo);
    	}
		mText = (TextView)findViewById(R.id.textView01);
        mainRelativeLayout = (RelativeLayout) findViewById(R.id.mainLinearLayout);
		linearLayout = (ScrollView) View.inflate(RevActivity.this,
		R.layout.revival, null);
		LayoutParams lp = new LayoutParams(-1,-2);
		linearLayout.setLayoutParams(lp);
		
		mainRelativeLayout.addView(linearLayout);
		
    	
        mButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		 String smsbody;
				switch( ( view.getId())){
        		  case R.id.button01:
        			  InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        			  imm.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
        		TextView Textv2 = (TextView)findViewById(R.id.textView2);
        		TextView Textv2a = (TextView)findViewById(R.id.textView2a);
                TextView Textv4 = (TextView)findViewById(R.id.textView4);
                TextView Textv6 = (TextView)findViewById(R.id.textView6);
                TextView Textv8 = (TextView)findViewById(R.id.textView8);
                TextView Textv8a = (TextView)findViewById(R.id.textView8a);
                TextView Textv8b = (TextView)findViewById(R.id.textView8b);
                TextView Textv10 = (TextView)findViewById(R.id.textView10);
                TextView Textv12 = (TextView)findViewById(R.id.textView12);
                TextView Textv14 = (TextView)findViewById(R.id.textView14);
                TextView Textv16 = (TextView)findViewById(R.id.textView16);
                TextView Textv18 = (TextView)findViewById(R.id.textView18);
                TextView Textv20 = (TextView)findViewById(R.id.textView20);
                result = (TextView)findViewById(R.id.textView21);
         //       result.setVisibility(View.GONE);
                Textv2.setText(" ");
                Textv2a.setText(" ");
                Textv4.setText(" "); 
                Textv6.setText(" "); 
                Textv8.setText(" ");
                Textv8a.setText(" ");
                Textv8b.setText(" ");
                Textv10.setText(" ");
                Textv12.setText(" ");
                Textv14.setText(" ");
                Textv16.setText(" ");
                Textv18.setText(" ");
                Textv20.setText(" ");
                Button pstatusbutton = (Button)findViewById(R.id.button1);
                pstatusbutton.setOnClickListener(this);
                Button psmsbutton = (Button)findViewById(R.id.button2);
                psmsbutton.setOnClickListener(this);
                 stockSQLHelper = new DataViewerSQLiteHelper(RevActivity.this);
                database = stockSQLHelper.getReadableDatabase(); 
                Cursor agcursor = null;
                agcursor = database.rawQuery("SELECT agname,tel from agentdata",null);
                agcursor.moveToFirst();
                sAgName = agcursor.getString(agcursor.getColumnIndexOrThrow("agname"));
                sAgtel =  agcursor.getString(agcursor.getColumnIndexOrThrow("tel"));
                
           	    Cursor cursor = null;
           	    if(mEdit.getText().toString().length()>0){
               	cursor = database.rawQuery("SELECT * FROM bmpolmast WHERE policyno = "+mEdit.getText().toString(), null);
           	    }else{
           	    	mEdit.setError("policy number required");
           	    	break;
           	    }
           	 if(cursor.getCount()>0)
                {
                	cursor.moveToFirst();
         	    String Add1 = 
    				   cursor.getString(cursor.getColumnIndexOrThrow("add1"));
    		   
   		       
         	   String Add2 = 
    				   cursor.getString(cursor.getColumnIndexOrThrow("add2"));
    		   
    		   String Add3 = 
    				   cursor.getString(cursor.getColumnIndexOrThrow("add3"));
    		   String Units = cursor.getString(cursor.getColumnIndexOrThrow("units"));
    		   String AgCode = cursor.getString(cursor.getColumnIndexOrThrow("agcode"));
   // 		   String DoCode = cursor.getString(cursor.getColumnIndexOrThrow("docode"));
    		   String Name = cursor.getString(cursor.getColumnIndexOrThrow("polname"));
    		   String Status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
    		   String DOC = cursor.getString(cursor.getColumnIndexOrThrow("doc"));
    		   String Mode = cursor.getString(cursor.getColumnIndexOrThrow("mode"));
    		   String SA = cursor.getString(cursor.getColumnIndexOrThrow("sa"));
    		    Prem = cursor.getString(cursor.getColumnIndexOrThrow("prem"));
    		   String FUP = cursor.getString(cursor.getColumnIndexOrThrow("fup"));
    		   String DOB = cursor.getString(cursor.getColumnIndexOrThrow("dob"));
    		   String Pin = cursor.getString(cursor.getColumnIndexOrThrow("pin"));
    		    PolicyNo = cursor.getString(cursor.getColumnIndexOrThrow("policyno"));
    		   String PlanNo = cursor.getString(cursor.getColumnIndexOrThrow("plan"));
    		   String TermNo = cursor.getString(cursor.getColumnIndexOrThrow("term"));
               String Type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
    		   String dtStart = DOC;  
    		   SimpleDateFormat  format = new SimpleDateFormat("yyyy/MM/dd");  
    		   try {  
    		        date = format.parse(dtStart);  
    		       System.out.println(date);  
    		   } catch (ParseException e) {  
    		       // TODO Auto-generated catch block  
    		       e.printStackTrace();  
    		   } catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		  /* yitems = new ArrayList<YlyBean>();
    		   yitems.add(new YlyBean(1,1.0));
    		   yitems.add(new YlyBean(2,2.0973));
    		   yitems.add(new YlyBean(3,3.3012));
    		   yitems.add(new YlyBean(4,4.6223));
    		   yitems.add(new YlyBean(5,6.0718));
    		 
    		   hitems = new ArrayList<HlyBean>();
    		   hitems.add(new HlyBean(1,1.0));
    		   hitems.add(new HlyBean(2,2.0475));
    		   hitems.add(new HlyBean(3,3.1448));
    		   hitems.add(new HlyBean(4,4.2941));
    		   hitems.add(new HlyBean(5,5.4981));
    		   hitems.add(new HlyBean(6,6.7593));
    		   hitems.add(new HlyBean(7,8.0803));
    		   hitems.add(new HlyBean(8,9.4641));
    		   hitems.add(new HlyBean(9,10.9137));
    		   hitems.add(new HlyBean(10,12.4321));
    		   
    		   
    		   qitems = new ArrayList<QlyBean>();
    		   qitems.add(new QlyBean(1,1.0));
    		   qitems.add(new QlyBean(2,2.0235));
    		   qitems.add(new QlyBean(3,3.0710));
    		   qitems.add(new QlyBean(4,4.1431));
    		   qitems.add(new QlyBean(5,5.2403));
    		   qitems.add(new QlyBean(6,6.3633));
    		   qitems.add(new QlyBean(7,7.5127));
    		   qitems.add(new QlyBean(8,8.6891));
    		   qitems.add(new QlyBean(9,9.8930));
    		   qitems.add(new QlyBean(10,11.1253));
    		   qitems.add(new QlyBean(11,12.3864));
    		   qitems.add(new QlyBean(12,13.6772));
    		   qitems.add(new QlyBean(13,14.9983));
    		   qitems.add(new QlyBean(14,16.3503));
    		   qitems.add(new QlyBean(15,17.7342));
    		   qitems.add(new QlyBean(16,19.1505));
    		   qitems.add(new QlyBean(17,20.6000));
    		   qitems.add(new QlyBean(18,22.0836));
    		   qitems.add(new QlyBean(19,23.6020));
    		   qitems.add(new QlyBean(20,25.1560));


    		   mitems = new ArrayList<MlyBean>();
    		   mitems.add(new MlyBean(1,1.0079));
                   mitems.add(new MlyBean(2,1.0158));
                   mitems.add(new MlyBean(3,1.0238));
                   mitems.add(new MlyBean(4,1.0317));
                   mitems.add(new MlyBean(5,1.0396));
                   mitems.add(new MlyBean(6,1.0475));
                   mitems.add(new MlyBean(7,1.0554));
                   mitems.add(new MlyBean(8,1.0633));
                   mitems.add(new MlyBean(9,1.0713));
                   mitems.add(new MlyBean(10,1.0792));
                   mitems.add(new MlyBean(11,1.0871));
                   mitems.add(new MlyBean(12,1.0950));
                   mitems.add(new MlyBean(13,1.1029));
                   mitems.add(new MlyBean(14,1.1108));
                   mitems.add(new MlyBean(15,1.1188));
                   mitems.add(new MlyBean(16,1.1267));
                   mitems.add(new MlyBean(17,1.1346));
                   mitems.add(new MlyBean(18,1.1425));
                   mitems.add(new MlyBean(19,1.1504));
                   mitems.add(new MlyBean(20,1.1583));
                   mitems.add(new MlyBean(21,1.1663));
                   mitems.add(new MlyBean(22,1.1742));
                   mitems.add(new MlyBean(23,1.1821));
                   mitems.add(new MlyBean(24,1.1900));
                   mitems.add(new MlyBean(25,1.1979));
                   mitems.add(new MlyBean(26,1.2058));
                   mitems.add(new MlyBean(27,1.2138));
                   mitems.add(new MlyBean(28,1.2217));
                   mitems.add(new MlyBean(29,1.2296));
                   mitems.add(new MlyBean(30,1.2375));
                   mitems.add(new MlyBean(31,1.2454));
                   mitems.add(new MlyBean(32,1.2533));
                   mitems.add(new MlyBean(33,1.2613));
                   mitems.add(new MlyBean(34,1.2692));
                   mitems.add(new MlyBean(35,1.2771));
                   mitems.add(new MlyBean(36,1.2850));
                   mitems.add(new MlyBean(37,1.2929));
                   mitems.add(new MlyBean(38,1.3008));
                   mitems.add(new MlyBean(39,1.3088));
                   mitems.add(new MlyBean(40,1.3167));
                   mitems.add(new MlyBean(41,1.3242));
                   mitems.add(new MlyBean(42,1.3325));
                   mitems.add(new MlyBean(43,1.3404));
                   mitems.add(new MlyBean(44,1.3483));
                   mitems.add(new MlyBean(45,1.3563));
                   mitems.add(new MlyBean(46,1.3642));
                   mitems.add(new MlyBean(47,1.3721));
                   mitems.add(new MlyBean(48,1.3800));
                   mitems.add(new MlyBean(49,1.3879));
                   mitems.add(new MlyBean(50,1.3958));
                   mitems.add(new MlyBean(51,1.4038));
                   mitems.add(new MlyBean(52,1.4117));
                   mitems.add(new MlyBean(53,1.4196));
                   mitems.add(new MlyBean(54,1.4275));
                   mitems.add(new MlyBean(55,1.4354));
                   mitems.add(new MlyBean(56,1.4433));
                   mitems.add(new MlyBean(57,1.4513));
                   mitems.add(new MlyBean(58,1.4592));
                   mitems.add(new MlyBean(59,1.4671));
                   mitems.add(new MlyBean(60,1.4750));
*/
    		   
    		   SimpleDateFormat  dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    		   try {  
    		     Date date1 = date;  
    		     datetime = dateformat.format(date1);
    		     datetimedd = datetime.substring(0,2);
    		     datetimetoday = dateformat.format(new Date());
    		    System.out.println("Current Date Time : " + datetime); 
    		   } catch (ParseException e) {  
    		       // TODO Auto-generated catch block  
    		       e.printStackTrace();  
    		   }
    		   FUP1 = datetimedd+"/"+FUP+" 00:00:00";
    		
                         SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                         try {
             				FUP1cdate = new DateTime(format1.parse(FUP1));
             				FUP2cdate = new DateTime(format1.parse(FUP1));
             			} catch (java.text.ParseException e) {
             				// TODO Auto-generated catch block
             				e.printStackTrace();
             			}  
                         DateTime test = new DateTime();
//*******************************************************************************************************************                         
           /*  String no_of_months = String.valueOf(Months.monthsBetween(FUP1cdate, test).getMonths());
                 int mode_months = 0;        
                         
             if (Mode.equals("Y")){
         	    mode_months = 12;
            }else if (Mode.equals("H")){
         	    mode_months = 6;
            }else if (Mode.equals("Q")){
         	    mode_months = 3;
            }else if (Mode.equals("M")){
         	   mode_months = 1;
            }        
             no_of_inst = Integer.parseInt(no_of_months)/mode_months;  */          
           //*******************************************************************************************************************                        
                         
                         
            
           String no_of_days =  String.valueOf(Days.daysBetween(FUP1cdate,test).getDays());
           String no_of_months = String.valueOf(Months.monthsBetween(FUP1cdate, test).getMonths());
           if (Integer.parseInt(no_of_days)<=30){
        // to do	
        	   showAlertView(no_of_days);
           }
           if (Integer.parseInt(no_of_days)>=(365*5)){
        //to do	   
        	   	   showAlertView(no_of_days);
           }
           
           
    //       int no_of_years = Years.yearsBetween(FUP1cdate, test).getYears();
           
           
           if (Mode.equals("Y")){
        	    mode_days = 365;mode_months = 12;
           }else if (Mode.equals("H")){
        	    mode_days = 180;mode_months = 6;
           }else if (Mode.equals("Q")){
        	    mode_days = 90;mode_months = 3;
           }else if (Mode.equals("M")){
        	    mode_days = 30;mode_months = 1;
           }
           
           
           
           
           
 //          System.out.println(Integer.parseInt(no_of_days));
           no_of_inst = (Integer.parseInt(no_of_days)/(mode_days))+1;   
           no_of_inst = (Integer.parseInt(no_of_months)/(mode_months))+1;
           for (int i=0;i<no_of_inst;i++){
           FUP2cdate = FUP1cdate.plusMonths((no_of_inst-1)*mode_months);
        	  
        	   
           }
       /*    
           if (Mode.equals("Y")){
       	   for (int j = 0; j < yitems.size(); j++){
       		   if (yitems.get(j).getinst()==(no_of_inst)){
        	 rev_factor = yitems.get(j).getrevfactor();
        	 //	   System.out.println(no_of_inst+"------"+yitems.get(j).getinst()+"-------"+yitems.get(j).getrevfactor());
       }
       		   
       	   }
          }
          
           else if (Mode.equals("H")){
        	  for (int j = 0; j < hitems.size(); j++){
          		   if (hitems.get(j).getinst() == no_of_inst){
           	 rev_factor = hitems.get(j).getrevfactor();
           }
        	  }
          }
           else if (Mode.equals("Q")){
         	  for (int j = 0; j < qitems.size(); j++){
           		   if (qitems.get(j).getinst() == no_of_inst){
            	 rev_factor = qitems.get(j).getrevfactor();
            }
         	  }
           }
           else if (Mode.equals("M") && Type.equals("O")){
          	  for (int j = 0; j < mitems.size(); j++){
            		   if (mitems.get(j).getinst() == no_of_inst){
             	 rev_factor = mitems.get(j).getrevfactor();
             }
          	  }
            }
           else if (Mode.equals("M") && Type.equals("S")){
  // to do
        	   Toast.makeText(RevActivity.this, PolicyNo + " is SSS" + "Contact PS Dept", Toast.LENGTH_SHORT).show();
        	   RevActivity.this.finish();
             }*/
           
 //        DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(RevActivity.this);
//		 SQLiteDatabase database = stockSQLHelper.getWritableDatabase();  		       	 
      	 String sql = "SELECT * FROM revival_factors WHERE mode = '" + Mode + "' AND noofinst = '" + no_of_inst + "'" ;
      	
      	 Cursor p = database.rawQuery(sql, null);
      	 p.moveToFirst();
      	 rev_factor = (double) Double.parseDouble(p.getString(p.getColumnIndexOrThrow("factor")));
    //  	 Toast.makeText(RevActivity.this, sql + "REvFactor"+String.valueOf(rev_factor), Toast.LENGTH_LONG).show();
      	
	//		 database.close();
//			 stockSQLHelper.close();  
           
           
           
           tot_prem =   no_of_inst*Integer.parseInt(Prem);
           

            late_fee = (double) Integer.parseInt(Prem) * rev_factor; 
           
   //        int r_days = Integer.parseInt(no_of_days) - ((no_of_inst -1) * mode_days);
           
            int r_days =  Days.daysBetween(FUP2cdate,test).getDays();
           System.out.println(r_days);
           int remainder = r_days % 30; 
           if (remainder > 14){
        	   r_days = r_days -remainder + 30;
        	   }else {
        		   r_days = r_days - remainder;
        	   }
           
           System.out.println(r_days);
           result.setText("No.of Days " + no_of_days 
      			 + "\n" +        " No.of inst days "+ (no_of_inst -1) * mode_days
      			 + "\n" + "No.of Inst " + no_of_inst + " REvFactor "+String.valueOf(rev_factor)
      			                     + "\n" + "Remaining Days " + r_days );
           if (r_days > 0){
           String sql1 = "SELECT * FROM revival_factors WHERE mode = 'bp' AND noofinst = '" + r_days / 30 + "'" ;
         //  Toast.makeText(RevActivity.this, sql1, Toast.LENGTH_LONG).show();
        	 Cursor p1 = database.rawQuery(sql1, null);
        	 p1.moveToFirst();
      
        	 result.setText("No.of Days " + no_of_days 
        			 + "\n" +        " No.of inst days "+ (no_of_inst -1) * mode_days
        			 + "\n" + "No.of Inst " + no_of_inst + " REvFactor "+String.valueOf(rev_factor)
        			                     + "\n" + "Remaining Days " + r_days  + " BpFactor "+p1.getString(p1.getColumnIndexOrThrow("factor")));
           double late_fee_r_days = (late_fee)* Double.parseDouble(p1.getString(p1.getColumnIndexOrThrow("factor")));
           late_fee = late_fee_r_days;}
           
           late_fee = late_fee - tot_prem;
            tot_rev_amt = tot_prem + late_fee;
                    Textv2.setText(PolicyNo);
                    Textv2a.setText(DOC);
                    Textv4.setText(PlanNo); 
                    Textv6.setText(datetimetoday); 
                    Textv8.setText(FUP1cdate.toString().substring(0,10));
                    Textv8a.setText(Mode);
                    Textv8b.setText(FUP2cdate.toString().substring(0,10));
                    Textv10.setText(String.valueOf(no_of_inst));
                    Textv12.setText(Prem);
                    Textv14.setText(String.valueOf(tot_prem));
                    Textv16.setText("9.5");
                    Textv18.setText(String.valueOf(late_fee));
                    Textv20.setText(String.valueOf(tot_rev_amt));
                
                    cursor.close();
                  database.close();
                   stockSQLHelper.close();
                }else {
                	Toast.makeText(RevActivity.this, "No Such Policy Number", Toast.LENGTH_LONG).show();
                }
                
                //DO something
                break;     
                          case R.id.button1:
                        	   stockSQLHelper = new DataViewerSQLiteHelper(RevActivity.this);
                              database = stockSQLHelper.getReadableDatabase(); 
              				 String sql = "select * from registration";
              	  	         Cursor a = database.rawQuery(sql, null);
              	  	         a.moveToFirst();
              	  	 if (a.getCount()>0){
              	  	     ipadd = a.getString(a.getColumnIndex("regflag")).trim();
              	         database.close();
              	         a.close();
              	     //    stockSQLHelper.close();
              	  	 }else {
              	  		 Toast.makeText(RevActivity.this, "No Portal Data", Toast.LENGTH_LONG).show();
              	  	  database.close();
           	         a.close();
           	     //    stockSQLHelper.close();
              	  		 break;
              	  	 }
              	  	 if (ipadd.equals("CLIA")){
                                Intent psintent = new Intent(RevActivity.this,PortalCLIA.class);
                                 Textv2 = (TextView)findViewById(R.id.textView2);
                                psintent.putExtra("PolicyNo", Textv2.getText().toString());
                                startActivity(psintent);}
              	  	 else if (ipadd.equals("Agent")){
              	  	 Intent psintent = new Intent(RevActivity.this,Portalrev.class);
                      Textv2 = (TextView)findViewById(R.id.textView2);
                     psintent.putExtra("PolicyNo", Textv2.getText().toString());
                     startActivity(psintent);}
              	  	
                           break;

                          case R.id.button2:
                        	   stockSQLHelper = new DataViewerSQLiteHelper(RevActivity.this);
                              database = stockSQLHelper.getReadableDatabase(); 
                               //DO something
        	                    String smspolno = PolicyNo;
        	                    String smsfup = FUP1;
        	                    String smsprem = Prem;
        	                    String smsnoofinst = String.valueOf(no_of_inst);
        	                    String smstotprem = String.valueOf(tot_prem);
        	                    String smslatefee = String.valueOf(late_fee);
        	                    String smsrevamt = String.valueOf(tot_rev_amt);
        	                    String smsagname = sAgName;
        	                    String smsagtel = sAgtel;

        	          
        	                     smsbody = "Policy No : " + smspolno + "\n" + 
        	                               "Prem Due Since : " + smsfup + "\n" +
        	                    		   "Instl Prem : " + smsprem + "\n" +
        	                  	           "No.of Inst : " + smsnoofinst + "\n" + 
        	                    		   "Total Prem : " + smstotprem + "\n" +
        	                    		   "LateFee : " + smslatefee + "\n" + 
        	                  	           "Revival Amt : " + smsrevamt + "\n"+
        	                  	           "Please Pay Premiums at LIC Office" + "\n" + 
        	                  	           "Agent : " +  smsagname + "\n" +
        	                               "Mobile : " + smsagtel + "\n";
        	          
        	                   
        	        		try {
        						 
        					     Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        					     sendIntent.putExtra("address","");
        					     sendIntent.putExtra("sms_body", smsbody); 
        					     sendIntent.setType("vnd.android-dir/mms-sms");
        					     startActivity(sendIntent);

        					} catch (Exception e) {
        						Toast.makeText(getApplicationContext(),
        							"SMS failed, please try again later!",
        							Toast.LENGTH_LONG).show();
        						e.printStackTrace();
        					}
        				  
                             
                          break;
                      }
        }
  
        	
        });
   
    }
    private void showAlertView(String str) {
		AlertDialog alert = new AlertDialog.Builder(this).create();
	//	if (TextUtils.isEmpty(str)) {
		if (Integer.parseInt(str)<=30){
			alert.setTitle("Revival");
			alert.setMessage("With in Grace Period!!!");
		} else if (Integer.parseInt(str)>=(365*5)){
			// Remove , end of the name
//			String strContactList = str.substring(0, str.length() - 1);

			alert.setTitle("Revival");
			alert.setMessage("Cannot be revivied Lapsed more than 5 years");
		}
		alert.setButton("Ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				RevActivity.this.finish();
			}
		});
		alert.show();
	}
}
