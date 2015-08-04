package com.sreekanth.duelist;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


	public class welcome extends Activity implements OnClickListener{
		SQLiteDatabase database;
		 private String[] stockColumns = 
			   {AgentData.ID,
			    AgentData.AG_CODE,
			    AgentData.AG_NAME,
			    AgentData.AG_DOA,
			    AgentData.AG_LICENNO,
			    AgentData.AG_LICEXPDATE,
			    AgentData.AG_ACCOUNTNO,
			    AgentData.AG_BANKNAME,
			    AgentData.AG_TEL,
			    AgentData.AG_NOMINEE,
			    AgentData.AG_NOMINEEREL,
			    AgentData.AG_ADR1,
			    AgentData.AG_ADR2,
			    AgentData.AG_ADR3,
			    AgentData.AG_DOCODE,
			    AgentData.AG_PIN,
			    AgentData.AG_PAN,
			    AgentData.AG_CLIACD,
			    AgentData.AG_EMAIL
			   }; 
		private String[] regColumns = {Registration.REG_COUPONNO};
		String portalpwd;
		
		Button Cnlbutton;
		
		
		
		
		TextView Textc2;
		TextView Textn2;
		TextView Textd2;
		TextView Textl2;
		TextView Texte2;
		TextView Textac2;
		TextView Textbn2;
		TextView Textt2;
		TextView Textnm2;
		TextView Textnmrl2;
		TextView Textdc2;
		TextView Textad1;
		TextView Textad2;
		TextView Textad3;
		TextView Textclcd;
		TextView Textpn2;
		TextView Textp2;
		TextView TextE;
		TextView TextPP;
		
		String agcode;
		String agname;
		String agdoa;
		String aglicenno;
		String aglicexpdt;
		String agaccountno;
		String agbankname;
		String agtelno;
		String agnominee;
		String agnomineerel;
		String agdocode;
		String agadr1;
		String agadr2;
		String agadr3;
		String agpin;
		String agpan;
		String agcliacd;
		String agemail;
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
		    return super.onCreateOptionsMenu(menu);

		}
			@Override
			protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
     		setContentView(R.layout.agentdata);
     	
   
     		
			        DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
			        SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
			        Cursor a = database.query(AgentData.TABLE_STOCK, stockColumns,
			                null, null, null, null, null);
			    
			Cursor b = database.query(Registration.TABLE_STOCK, regColumns, null, null, null, null, null);
	        b.moveToFirst();
	
	a.moveToFirst();
	if (a != null){
	 agcode = a.getString(a.getColumnIndex("agcode"));
	 agname = a.getString(a.getColumnIndex("agname"));
	 agdoa = a.getString(a.getColumnIndex("agdoa"));
	 aglicenno = a.getString(a.getColumnIndex("licenno"));
	 aglicexpdt = a.getString(a.getColumnIndex("licexpdate"));
	 agaccountno = a.getString(a.getColumnIndex("accountno"));
	 agbankname = a.getString(a.getColumnIndex("bankname"));
	 agtelno = a.getString(a.getColumnIndex("tel"));
	 agnominee = a.getString(a.getColumnIndex("nominee"));
	 agnomineerel = a.getString(a.getColumnIndex("nomineerel"));
	 agdocode = a.getString(a.getColumnIndex("docode"));
	 agadr1 = a.getString(a.getColumnIndex("adr1"));
	 agadr2 = a.getString(a.getColumnIndex("adr2"));
	 agadr3 = a.getString(a.getColumnIndex("adr3"));
	 agpin = a.getString(a.getColumnIndex("pin"));
	 agpan = a.getString(a.getColumnIndex("agpan"));
	 agcliacd = a.getString(a.getColumnIndex("cliacd"));
	 agemail = a.getString(a.getColumnIndex("email"));
	}
    if (b.getCount()>0){
	 portalpwd = b.getString(b.getColumnIndex("regcouponno"));
    }
    else {portalpwd = "  ";}
    b.close();
  	database.close();
  	a.close();
  	stockSQLHelper.close();
	Textc2 = (TextView)findViewById(R.id.textView2);
	Textn2 = (TextView)findViewById(R.id.textView4);
	Textd2 = (TextView)findViewById(R.id.textView6);
	Textl2 = (TextView)findViewById(R.id.textView8);
	Texte2 = (TextView)findViewById(R.id.textView10);
	Textac2 = (TextView)findViewById(R.id.textView12);
	Textbn2 = (TextView)findViewById(R.id.textView14);
	Textt2 = (TextView)findViewById(R.id.textView16);
	Textnm2 = (TextView)findViewById(R.id.textView18);
	Textnmrl2 = (TextView)findViewById(R.id.textView20);
	Textdc2 = (TextView)findViewById(R.id.textView28);
	Textad1 = (TextView)findViewById(R.id.textView30);
	Textad2 = (TextView)findViewById(R.id.textView32);
	Textad3 = (TextView)findViewById(R.id.textView34);
	Textclcd = (TextView)findViewById(R.id.textView36);
	Textpn2 = (TextView)findViewById(R.id.textView40);
	Textp2 = (TextView)findViewById(R.id.textView38);
	TextE = (TextView)findViewById(R.id.textView23);
	TextPP = (TextView)findViewById(R.id.textView25);
	/*************************************************************************************************/
	
	
	
	
  

	Cnlbutton = (Button)findViewById(R.id.button3);
	Cnlbutton.setOnClickListener(this);
	
	 if(agcode!=null)
     {
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
	  TextPP.setText(portalpwd);
     }
			}	
		
			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
			    // Handle presses on the action bar items
				  switch (item.getItemId()) {
			  case R.id.action_nblist:
		        	 return true;
			  case R.id.search:
			         Intent intent=new Intent(this,PmActivity.class);
			         startActivity(intent);
			         return true;
			  case R.id.Portal:
			         return true;
			  case R.id.action_nav:
			         return true;
			  case R.id.action_gallery:
			         return true;
			  default:
			  return super.onOptionsItemSelected(item);
			    }
			}
			@Override
			public void onClick(View v) {
				switch( ( v.getId())){
				case R.id.button1:
					
				// TODO Auto-generated method stub
				
				break;
				case R.id.button3:
					
					  
					  
					
					
				break;
			}
			}
	}
	
	
	
	

