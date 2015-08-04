package com.sreekanth.duelist;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PortalStatus extends Activity implements OnClickListener{
	String ipadd;
	EditText Epolno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.portalstatus);
		
		Epolno = (EditText)findViewById(R.id.editText1);
		Button ESubmit = (Button)findViewById(R.id.button1);
		ESubmit.setOnClickListener(this);
	}
		 public void onClick(View v) {
		if (Epolno.getText().toString().length()>7){
			 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(PortalStatus.this);
				 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
				 String sql = "select * from registration";
	  	         Cursor a = database.rawQuery(sql, null);
	  	         a.moveToFirst();
	  	 if (a.getCount()>0){
	  	      ipadd = a.getString(a.getColumnIndex("regflag")).trim();
	         database.close();
	         a.close();
	         stockSQLHelper.close();
	  	 }else {
	  		 Toast.makeText(PortalStatus.this, "Portal Id not Saved", Toast.LENGTH_LONG).show();
	  		
	  	 }
	  	 if (ipadd.equals("CLIA")){
                Intent psintent = new Intent(PortalStatus.this,PortalCLIA.class);
               
                psintent.putExtra("PolicyNo", Epolno.getText().toString());
                startActivity(psintent);}
	  	 else if (ipadd.equals("Agent")){
	  	 Intent psintent = new Intent(PortalStatus.this,Portal.class);
    
     psintent.putExtra("PolicyNo", Epolno.getText().toString());
     startActivity(psintent);}
		}
		 }
		
		
		


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.portal_status, menu);
		return true;
	}

}
