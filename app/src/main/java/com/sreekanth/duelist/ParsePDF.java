package com.sreekanth.duelist;

import java.io.IOException;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ParsePDF extends Activity implements OnClickListener {
	CSVAdapter mAdapter;
	ListView mList;
	String userName;
	Button Backbtn;
	Button Updbtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parse_pdf);
		Bundle extras = getIntent().getExtras(); 
		

		if (extras != null) {
		    userName = extras.getString("PDFFILE");
		    // and get whatever type user account id is
		}
		
		
		String PREFACE = userName;
		String RESULT = "outputFile.txt";
		 
		 try {
			new PDFTextParser().parsePdf(PREFACE, RESULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    Button Viewbtn = (Button)findViewById(R.id.button1);
    Viewbtn.setText("Import");
    Backbtn = (Button)findViewById(R.id.button2);
    Backbtn.setText("Back");
    Updbtn = (Button)findViewById(R.id.button3);
    
	 mList = (ListView)findViewById(R.id.mList);
	
	//Create Adapter. The second parameter is required by ArrayAdapter
	//which our Adapter extends. In this example though it is unused,
	//so we'll pass it a "dummy" value of -1.
	Viewbtn.setOnClickListener(this);
	Backbtn.setOnClickListener(this);
	Updbtn.setOnClickListener(this);
	
	
	
	/*
	 * This listener will get a callback whenever the user clicks on a row. 
	 * The pos parameter will tell us which row got clicked.
	 * 
	 * For now we'll just show a Toast with the state capital for the state that was clicked.
	 */
	mList.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int pos,long id) {
			Toast.makeText(v.getContext(), mAdapter.getItem(pos).getName()+"\n"+mAdapter.getItem(pos).getPrem()+"\n"+mAdapter.getItem(pos).getComm()+"\n"+mAdapter.getItem(pos).getPremdue(), Toast.LENGTH_SHORT).show();
		}
	});
}
	public void onClick(View v) {	
		// TODO Auto-generated method stub
		  switch( ( v.getId())){
       case R.id.button1:
    	   mAdapter = new CSVAdapter(this, -1);
    		//attach our Adapter to the ListView. This will populate all of the rows.
    	   Updbtn.setText("Update with policy master " + "(" + mAdapter.getCount()+ ")");
    		mList.setAdapter(mAdapter);
    	   break;
       case R.id.button2:
    	   this.finish();
    	   break;
       case R.id.button3:
    	   DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(ParsePDF.this);
   	       SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
   	  int rowsupd = 0;
   	  int rowsins = 0;
   	String xx = " ";
   	    for(int i=0 ; i<mAdapter.getCount() ; i++){
   	     State obj = mAdapter.getItem(i);
   	     String polno = obj.getPolno().trim();
   	     String fup = obj.getPremdue().substring(3, 10);
   	     String fupmm = fup.substring(0,2);
   	     String fupyy = fup.substring(3,7);
   	    
   	     String doc = obj.getDueRiskDate();
   	     String docdd = doc.substring(0,2);
   	     String docmm = doc.substring(3,5);
   	     String docyy = doc.substring(6,10);
   	     String docu = docyy.concat("/").concat(docmm).concat("/").concat(docdd);
   	     String plan = obj.getPlan();
   	     String planp = plan.substring(0,3);
   	     String termp = plan.substring(4,6);
   	     String prem = obj.getPrem();
   		 String name = obj.getName();
   	  ContentValues values = new ContentValues();
   	  values.put(bmpolmast.PM_POLNO, polno);   
   
 	  
 	   values.put(bmpolmast.PM_NAME, name);
	   values.put(bmpolmast.PM_DOC, docu); 
 	   values.put(bmpolmast.PM_PLAN, planp);
      values.put(bmpolmast.PM_TERM,termp);
      values.put(bmpolmast.PM_PREM,prem);
      values.put(bmpolmast.PM_STATUS, "inforce"); 
      values.put(bmpolmast.PM_BRANCH, "662");
   	     Cursor PV = database.rawQuery("SELECT * FROM bmpolmast where policyno = '" + polno + "';", null);
   	     PV.moveToFirst();
   	     
   	     if (PV.getCount()>0){
   	     String psa = PV.getString(PV.getColumnIndexOrThrow("sa"));
   	     String ptype =  PV.getString(PV.getColumnIndexOrThrow("type"));
   	     String padd1 =  PV.getString(PV.getColumnIndexOrThrow("add1"));
   	     String padd2 =  PV.getString(PV.getColumnIndexOrThrow("add2"));
   	     String padd3 =  PV.getString(PV.getColumnIndexOrThrow("add3"));
   	     String ppin =  PV.getString(PV.getColumnIndexOrThrow("pin"));
   	     String punits =  PV.getString(PV.getColumnIndexOrThrow("units"));
   	     String pdob =  PV.getString(PV.getColumnIndexOrThrow("dob"));
   	     String pmod =  PV.getString(PV.getColumnIndexOrThrow("mode"));
   	     String pmobile =  PV.getString(PV.getColumnIndexOrThrow("mobile"));
   	     String pemail =  PV.getString(PV.getColumnIndexOrThrow("email"));
   	     String pname =  PV.getString(PV.getColumnIndexOrThrow("polname"));
   	    
   	     int xmod = 0;
   	  int tempmonth = Integer.parseInt(fupmm);
	     int tempyear = Integer.parseInt(fupyy);
	     if (pmod.equals("Y")){  xmod = 12;};
	     if (pmod.equals("H")){  xmod = 6;};
	     if (pmod.equals("Q")){  xmod = 3;};
	     if (pmod.equals("M")){  xmod = 1;};
	     tempmonth = tempmonth + xmod;
	     if (tempmonth > 12){
	     tempmonth = tempmonth -12;
	     tempyear = tempyear +1;
	     }
	     
	     String fupu = String.format("%02d", tempmonth).concat("/").concat(String.valueOf(tempyear));
	     values.put(bmpolmast.PM_FUP, fupu);  
         values.put(bmpolmast.PM_NAME, pname);
         values.put(bmpolmast.PM_SA, psa); 
         values.put(bmpolmast.PM_TYPE,  ptype);
         values.put(bmpolmast.PM_ADD1, padd1);
         values.put(bmpolmast.PM_ADD2, padd2);
         values.put(bmpolmast.PM_ADD3, padd3);
         values.put(bmpolmast.PM_PIN, ppin);
         values.put(bmpolmast.PM_UNITS, punits);
         values.put(bmpolmast.PM_DOB, pdob); 
         values.put(bmpolmast.PM_MOD, pmod);   
         values.put(bmpolmast.PM_MOBILE,pmobile);      
         values.put(bmpolmast.PM_EMAIL,pemail);
         int  rowsUpdated =   database.update(bmpolmast.TABLE_STOCK,  values, "policyno  = " + polno ,null);
          xx = xx + polno + ",  ";
         rowsupd++;
   	  }
   	  else {
   		 String fupu = String.valueOf(fupmm).concat("/").concat(String.valueOf(fupyy));
	     values.put(bmpolmast.PM_FUP, fupu);
    	
    		     values.put(bmpolmast.PM_SA, " "); 
    	         values.put(bmpolmast.PM_TYPE,  " ");
    	         values.put(bmpolmast.PM_ADD1, " ");
    	         values.put(bmpolmast.PM_ADD2, " ");
    	         values.put(bmpolmast.PM_ADD3, " ");
    	         values.put(bmpolmast.PM_PIN, " ");
    	         values.put(bmpolmast.PM_UNITS, "0");
    	         values.put(bmpolmast.PM_DOB, "         "); 
    	         values.put(bmpolmast.PM_MOD, " ");   
    	         values.put(bmpolmast.PM_MOBILE," ");      
    	         values.put(bmpolmast.PM_EMAIL," ");
    		
    		
    		
    		
    		database.insert(bmpolmast.TABLE_STOCK, null, values);
    		rowsins++;
    	}
    	
    		
    	}
   	 
   	    Toast.makeText(ParsePDF.this, "Rows Updated : " + rowsupd + xx +  "\n"+ "Rows Inserted : " + rowsins, Toast.LENGTH_LONG).show();   
   	       database.close();
   	       stockSQLHelper.close();
    	   break;
		  }
		  }
}