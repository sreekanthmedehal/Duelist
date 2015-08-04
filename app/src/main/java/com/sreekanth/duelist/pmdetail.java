package com.sreekanth.duelist;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class pmdetail extends Activity implements OnClickListener {
	
	String ipadd;

    public void onCreate(Bundle savedInstanceState) {  
    	  super.onCreate(savedInstanceState);  
    	  ActionBar bar = getActionBar();
    	  bar.setDisplayHomeAsUpEnabled(true);
    	  bar.setDisplayShowHomeEnabled(true);
    	  bar.setIcon(R.color.transparent);
    	  bar.setDisplayShowTitleEnabled(true);
    //	  bar.setDisplayHomeAsUpEnabled(true);
    	  
    	  
    	  
    	  
  	  setContentView(R.layout.pmdetail);
  	

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
         pstatusbutton.setOnClickListener(this);
          
         
         Intent iin= getIntent();
         Bundle b = iin.getExtras();
         if(b!=null)
         {
             String j2 =(String) b.get("PolNo");
             Textv2.setText(j2);
             String j4 =(String) b.get("PolName");
             Textv4.setText(j4);
             String j6 =(String) b.get("PolFup");
             Textv6.setText(j6);
             String j8 =(String) b.get("PolMode");
             Textv8.setText(j8);
             String j10 =(String) b.get("PolDoc");
             Textv10.setText(j10);
             String j12 =(String) b.get("PolStatus");
             Textv12.setText(j12);
             String j14 =(String) b.get("PolUnits");
             Textv14.setText(j14);
             String j16 =(String) b.get("PolPlan");
             Textv16.setText(j16);
             String j18 =(String) b.get("PolTerm");
             Textv18.setText(j18);
             String j20 =(String) b.get("PolAgcode");
             Textv20.setText(j20);
             String j22 =(String) b.get("PolSa");
             Textv22.setText(j22);
             String j24 =(String) b.get("PolDob");
             Textv24.setText(j24);
             String j26 =(String) b.get("PolPrem");
             Textv26.setText(j26);
             String j28 =(String) b.get("PolAdd1");
             Textv28.setText(j28);
             String j30 =(String) b.get("PolAdd2");
             Textv30.setText(j30);
             String j32 =(String) b.get("PolAdd3");
             Textv32.setText(j32);
             String j34 =(String) b.get("PolPin");
             Textv34.setText(j34);
             String j42 = (String)b.get("PolMobile");
             Textv42.setText(j42);
             String j44 = (String)b.get("PolEmail");
             Textv44.setText(j44);
         }
    }       

        	    public void onClick(View v) {
       
        
     	
     			// TODO Auto-generated method stub
     			  switch( ( v.getId())){
                   case R.id.button1:
                	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(pmdetail.this);
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
      	  		 Toast.makeText(pmdetail.this, "Portal Id not Saved", Toast.LENGTH_LONG).show();
      	  		 break;
      	  	 }
      	  	 if (ipadd.equals("CLIA")){
                        Intent psintent = new Intent(pmdetail.this,PortalCLIA.class);
                        TextView Textv2 = (TextView)findViewById(R.id.textView2);
                        psintent.putExtra("PolicyNo", Textv2.getText().toString());
                        startActivity(psintent);}
      	  	 else if (ipadd.equals("Agent")){
      	  	 Intent psintent = new Intent(pmdetail.this,Portal.class);
             TextView Textv2 = (TextView)findViewById(R.id.textView2);
             psintent.putExtra("PolicyNo", Textv2.getText().toString());
             startActivity(psintent);}
      	  	
                   break;
                   case R.id.button2:
                        //DO something
                   break;
                   case R.id.button3:
                        //DO something
                   break;
               }
        	    
     	

       
        
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