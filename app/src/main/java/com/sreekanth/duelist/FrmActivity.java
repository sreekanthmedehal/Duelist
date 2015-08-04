package com.sreekanth.duelist;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class FrmActivity extends Activity {
	private RelativeLayout mainRelativeLayout;
	private ScrollView relativeLayout;
	Button mButton;
	EditText mEdit;
	TextView mText;
	String ipadd;
	 SQLiteDatabase database;
	 DataViewerSQLiteHelper stockSQLHelper;
	 
	 Cursor cursor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm);
        mButton = (Button)findViewById(R.id.button01);
   //     mButton.setEnabled(false);
    	mEdit   = (EditText)findViewById(R.id.editText01);
    	mEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
    	
    	
    	
    	
		mText = (TextView)findViewById(R.id.textView01); 
		
		
		mText.setText("Policy Status of "+mEdit.getText().toString());
// mButton.setEnabled(true);

        mainRelativeLayout = (RelativeLayout) findViewById(R.id.mainLinearLayout);
		relativeLayout = (ScrollView) View.inflate(FrmActivity.this,
		R.layout.pmdetail, null);
		
		LayoutParams lp = new LayoutParams(-1,-2);
		relativeLayout.setLayoutParams(lp);
		mainRelativeLayout.addView(relativeLayout);
	 
    
        mButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		
        		 switch( ( view.getId())){
        		
        		
        		  case R.id.button01:
        			  
             			 
        			  InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        			  imm.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
        		TextView Textv2 = (TextView)findViewById(R.id.textView2);
                TextView Textv4 = (TextView)findViewById(R.id.textView4);
                TextView Textv6 = (TextView)findViewById(R.id.textView6);
                TextView Textv8 = (TextView)findViewById(R.id.textView8);
                TextView Textv10 = (TextView)findViewById(R.id.textView10);
                TextView Textv12 = (TextView)findViewById(R.id.textView12);
                TextView Textv14 = (TextView)findViewById(R.id.textView14);
                TextView Textv16 = (TextView)findViewById(R.id.textView16);
                TextView Textv18 = (TextView)findViewById(R.id.textView18);
                TextView Textv20 = (TextView)findViewById(R.id.textView20);
                TextView Textv22 = (TextView)findViewById(R.id.textView22);
                TextView Textv24 = (TextView)findViewById(R.id.textView24);
                TextView Textv26 = (TextView)findViewById(R.id.textView26);
                TextView Textv28 = (TextView)findViewById(R.id.textView28);
                TextView Textv30 = (TextView)findViewById(R.id.textView30);
                TextView Textv32 = (TextView)findViewById(R.id.textView32);
                TextView Textv34 = (TextView)findViewById(R.id.textView34);
                Button pstatusbutton = (Button)findViewById(R.id.button1);
                TextView Textv38 = (TextView)findViewById(R.id.textView38);
                TextView Textv39 = (TextView)findViewById(R.id.textView39);
                TextView Textv40 = (TextView)findViewById(R.id.textView40);
                TextView Textv42 = (TextView)findViewById(R.id.textView42);
                TextView Textv44 = (TextView)findViewById(R.id.textView44);
                Textv2.setText(" ");
                Textv4.setText(" "); 
                Textv6.setText(" "); 
                Textv8.setText(" ");
                Textv10.setText(" ");
                Textv12.setText(" ");
                Textv14.setText(" ");
                Textv16.setText(" ");
                Textv18.setText(" ");
                Textv20.setText(" ");
                Textv22.setText(" ");
                Textv24.setText(" ");
                Textv26.setText(" ");
                Textv28.setText(" ");
                Textv30.setText(" ");
                Textv32.setText(" ");
                Textv34.setText(" ");
                Textv42.setText(" ");
                Textv44.setText(" ");
                pstatusbutton.setOnClickListener(this);
               
                stockSQLHelper = new DataViewerSQLiteHelper(FrmActivity.this);
                database = stockSQLHelper.getReadableDatabase(); 
               
               
                if(mEdit != null && mEdit.getText().toString().length()>0){
               	cursor = database.rawQuery("SELECT * FROM bmpolmast WHERE policyno = "+mEdit.getText().toString(), null);
           } else {
           	mEdit.setError("Policy Number Required"); break;}
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
    		   String Prem = cursor.getString(cursor.getColumnIndexOrThrow("prem"));
    		   String FUP = cursor.getString(cursor.getColumnIndexOrThrow("fup"));
    		   String DOB = cursor.getString(cursor.getColumnIndexOrThrow("dob"));
    		   String Pin = cursor.getString(cursor.getColumnIndexOrThrow("pin"));
    		   String PolicyNo = cursor.getString(cursor.getColumnIndexOrThrow("policyno"));
    		   String PlanNo = cursor.getString(cursor.getColumnIndexOrThrow("plan"));
    		   String TermNo = cursor.getString(cursor.getColumnIndexOrThrow("term"));
               String Mobile  = cursor.getString(cursor.getColumnIndexOrThrow("mobile"));
               String Email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                  
                    Textv2.setText(PolicyNo);
                    Textv4.setText(Name); 
                    Textv6.setText(FUP); 
                    Textv8.setText(Mode);
                    Textv10.setText(DOC);
                    Textv12.setText(Status);
                    Textv14.setText(Units);
                    Textv16.setText(PlanNo);
                    Textv18.setText(TermNo);
                    Textv20.setText(AgCode);
                    Textv22.setText(SA);
                    Textv24.setText(DOB);
                    Textv26.setText(Prem);
                    Textv28.setText(Add1);
                    Textv30.setText(Add2);
                    Textv32.setText(Add3);
                    Textv34.setText(Pin);
                    Textv42.setText(Mobile);
                    Textv44.setText(Email);
                    cursor.close();
                    database.close();
                    stockSQLHelper.close();
                } else {
                	Toast.makeText(FrmActivity.this, "No such Policy Number ....", Toast.LENGTH_LONG).show();}
        			  
                //DO something
                break;     
        			 
                          case R.id.button1:
                        	  stockSQLHelper = new DataViewerSQLiteHelper(FrmActivity.this);
                              database = stockSQLHelper.getReadableDatabase(); 
              				 String sql = "select * from registration";
              	  	         Cursor a = database.rawQuery(sql, null);
              	  	         a.moveToFirst();
              	  	 if (a.getCount()>0){
              	  	     ipadd = a.getString(a.getColumnIndex("regflag")).trim();
              	         database.close();
              	         a.close();
              	         stockSQLHelper.close();
              	  	 }else {
              	  		 Toast.makeText(FrmActivity.this, "No Portal Data", Toast.LENGTH_LONG).show();
              	  	  database.close();
           	         a.close();
           	         stockSQLHelper.close();
              	  		 break;
              	  	 }
              	  	 if (ipadd.equals("CLIA")){
                                Intent psintent = new Intent(FrmActivity.this,PortalCLIA.class);
                                 Textv2 = (TextView)findViewById(R.id.textView2);
                                psintent.putExtra("PolicyNo", Textv2.getText().toString());
                                startActivity(psintent);}
              	  	 else if (ipadd.equals("Agent")){
              	  	 Intent psintent = new Intent(FrmActivity.this,Portal.class);
                      Textv2 = (TextView)findViewById(R.id.textView2);
                     psintent.putExtra("PolicyNo", Textv2.getText().toString());
                     startActivity(psintent);}
              	  	
                           break;

                          case R.id.button3:
                               //DO something
                          break;
                      }
        }
        		
        	}
        );
    }}

