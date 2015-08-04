package com.sreekanth.duelist;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class AgFrmActivity extends Activity {
	
	private RelativeLayout mainRelativeLayout;
	private ScrollView relativeLayout;
	Button mButton;
	EditText mEdit;
	TextView mText, aText;
	String ipadd;
	 SQLiteDatabase database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm);
        Bundle extras = getIntent().getExtras(); 
        String userPolicyNo = "";

        if (extras != null) {
            userPolicyNo = extras.getString("pagcode");
            // and get whatever type user account id is
        }
        mButton = (Button)findViewById(R.id.button01);
    	mEdit   = (EditText)findViewById(R.id.editText01);
    	if (userPolicyNo.length()>0){
    		mEdit.setText(userPolicyNo);
    	}
		mText = (TextView)findViewById(R.id.textView01); 
		mText.setText("Agent Status of "+mEdit.getText().toString());
		mText = (TextView)findViewById(R.id.textView01); 
		mText.setText("Agent Status of "+mEdit.getText().toString());
		aText = (TextView)findViewById(R.id.textView02); 
		aText.setText("Enter Agency Code");
        mainRelativeLayout = (RelativeLayout) findViewById(R.id.mainLinearLayout);
		relativeLayout = (ScrollView) View.inflate(AgFrmActivity.this,
		R.layout.agentdata, null);
		LayoutParams lp = new LayoutParams(-1,-2);
		relativeLayout.setLayoutParams(lp);
		
		mainRelativeLayout.addView(relativeLayout);
		 final DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(AgFrmActivity.this);
          database = stockSQLHelper.getReadableDatabase(); 
    	
        mButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		 switch( ( view.getId())){
        		  case R.id.button01:
        			  InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        			  imm.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
        			  TextView Textc2 = (TextView)findViewById(R.id.textView2);
        				TextView Textn2 = (TextView)findViewById(R.id.textView4);
        				TextView Textd2 = (TextView)findViewById(R.id.textView6);
        				TextView Textl2 = (TextView)findViewById(R.id.textView8);
        				TextView Texte2 = (TextView)findViewById(R.id.textView10);
        				TextView Textac2 = (TextView)findViewById(R.id.textView12);
        				TextView Textbn2 = (TextView)findViewById(R.id.textView14);
        				TextView Textt2 = (TextView)findViewById(R.id.textView16);
        				TextView Textnm2 = (TextView)findViewById(R.id.textView18);
        				TextView Textnmrl2 = (TextView)findViewById(R.id.textView20);
        				TextView Textdc2 = (TextView)findViewById(R.id.textView28);
        				TextView Textad1 = (TextView)findViewById(R.id.textView30);
        				TextView Textad2 = (TextView)findViewById(R.id.textView32);
        				TextView Textad3 = (TextView)findViewById(R.id.textView34);
        				TextView Textclcd = (TextView)findViewById(R.id.textView36);
        				TextView Textpn2 = (TextView)findViewById(R.id.textView40);
        				TextView Textp2 = (TextView)findViewById(R.id.textView38);
        				TextView TextE = (TextView)findViewById(R.id.textView23);
        			
               
                
        				Cursor a = database.rawQuery("SELECT * FROM agentdata WHERE TRIM(agcode) = '"+mEdit.getText().toString().trim()+"'", null);             
        				   
       			    
      
       	
       	a.moveToFirst();
        if(a.getCount()>0)
        {
       		String agcode = a.getString(a.getColumnIndex("agcode"));
       		String agname = a.getString(a.getColumnIndex("agname"));
       		String agdoa = a.getString(a.getColumnIndex("agdoa"));
       		String aglicenno = a.getString(a.getColumnIndex("licenno"));
       		String aglicexpdt = a.getString(a.getColumnIndex("licexpdate"));
       		String agaccountno = a.getString(a.getColumnIndex("accountno"));
       		String agbankname = a.getString(a.getColumnIndex("bankname"));
       		String agtelno = a.getString(a.getColumnIndex("tel"));
       		String agnominee = a.getString(a.getColumnIndex("nominee"));
       		String agnomineerel = a.getString(a.getColumnIndex("nomineerel"));
       		String agdocode = a.getString(a.getColumnIndex("docode"));
       		String agadr1 = a.getString(a.getColumnIndex("adr1"));
       		String agadr2 = a.getString(a.getColumnIndex("adr2"));
       		String agadr3 = a.getString(a.getColumnIndex("adr3"));
       		String agpin = a.getString(a.getColumnIndex("pin"));
       		String agpan = a.getString(a.getColumnIndex("agpan"));
       		String agcliacd = a.getString(a.getColumnIndex("cliacd"));
       		String agemail = a.getString(a.getColumnIndex("email"));
              
                  
       	 Textc2.setText(agcode);
   	  Textn2.setText(agname);
   	  Textd2.setText(agdoa);
   	  Textl2.setText(aglicenno);
   	  Texte2.setText(aglicexpdt);
   	  Textac2.setText(agaccountno);
   	  Textbn2.setText(agbankname);
   	  Textt2.setText(agtelno);
   	  Textnm2.setText(agnominee);
   	  Textnmrl2.setText(agnomineerel);
   	  Textdc2.setText(agdocode);
   	  Textad1.setText(agadr1);
   	  Textad2.setText(agadr2);
   	  Textad3.setText(agadr3);
   	  Textp2.setText(agpin);
   	  Textpn2.setText(agpan);
   	  Textclcd.setText(agcliacd);
   	  TextE.setText(agemail);
                    a.close();
                //    database.close();
             //      stockSQLHelper.close();
                }
                
               
                      }
       
        	}
        	
        });
    }
}
