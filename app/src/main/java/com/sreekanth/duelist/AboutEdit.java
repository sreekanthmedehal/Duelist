package com.sreekanth.duelist;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AboutEdit extends Activity {
Spinner spinner;
Spinner spinner1;
Spinner spinner2;
Spinner spinner3;

EditText etDate;
Button change_date;

Button btn_submit;
Button btn_cancel;

EditText etDate1;
Button change_date1;

final int Date_Dialog_ID=0;
int cDay,cMonth,cYear; // this is the instances of the current date
Calendar cDate;
int sDay,sMonth,sYear; // this is the instances of the entered date
EditText PolicyNo;
EditText PolName;
EditText Premium;

EditText Add1; EditText Add2;EditText Add3;
EditText Pin; EditText Units;
EditText Plan;EditText Term;EditText SA;
Spinner Type;EditText Branch;
EditText Mobile;EditText Email;


String type;


String pmobile;
String ppolicyno;
String pfupmm;
String pfupyyyy;
String ptype;
String pmode;
String pstatus;
String pname;
String pprem;
String pbranch;
String padd1; String padd2; String padd3; String ppin;
String pemail; 
String pplan; String pterm; String psa; 
String punits; String pdoc; String pdob;
Date date;
Date date1;
String outputDateStr1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_add);
		  Intent iin= getIntent();
	         Bundle b = iin.getExtras();
	         if(b!=null)
	         {
	        	 ppolicyno = (String) b.get("Epolno");
	        	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(AboutEdit.this);
				 SQLiteDatabase database = stockSQLHelper.getWritableDatabase();  		       	 
	        	 String sql = "SELECT * FROM bmpolmast WHERE policyno = " + ppolicyno;
	        	 Cursor p = database.rawQuery(sql, null);
	        	 p.moveToFirst();
	        	 pmobile =  p.getString(p.getColumnIndexOrThrow("mobile"));
	        	 pfupmm = p.getString(p.getColumnIndexOrThrow("fup")).substring(0, 2);
	        	 pfupyyyy = p.getString(p.getColumnIndexOrThrow("fup")).substring(3, 7);
	        	 ptype = p.getString(p.getColumnIndexOrThrow("type"));
	        	 pmode = p.getString(p.getColumnIndex("mode"));
	        	 pstatus = p.getString(p.getColumnIndexOrThrow("status"));
	        	 pname = p.getString(p.getColumnIndexOrThrow("polname"));
	        	 pprem = p.getString(p.getColumnIndexOrThrow("prem"));
	        	 pbranch = p.getString(p.getColumnIndexOrThrow("branch"));
	        	 padd1 = p.getString(p.getColumnIndexOrThrow("add1"));
	        	 padd2 = p.getString(p.getColumnIndexOrThrow("add2"));
	        	 padd3 = p.getString(p.getColumnIndexOrThrow("add3"));
	        	 ppin = p.getString(p.getColumnIndexOrThrow("pin"));
	        	 pemail = p.getString(p.getColumnIndexOrThrow("email"));
	        	 pplan = p.getString(p.getColumnIndexOrThrow("plan"));
	        	 pterm = p.getString(p.getColumnIndexOrThrow("term"));
	        	 psa = p.getString(p.getColumnIndexOrThrow("sa"));
	        	 punits = p.getString(p.getColumnIndexOrThrow("units"));
	        	 pdoc = p.getString(p.getColumnIndexOrThrow("doc"));
	        	 pdob = p.getString(p.getColumnIndexOrThrow("dob"));
				 database.close();
				 stockSQLHelper.close();
	         }       
	         
	spinner = (Spinner)findViewById(R.id.textView16);
	spinner1 = (Spinner)findViewById(R.id.textView28);
	spinner2 = (Spinner)findViewById(R.id.textView18);
	spinner3 = (Spinner)findViewById(R.id.textView18a);
	Type = (Spinner)findViewById(R.id.editText1);
	btn_submit = (Button)findViewById(R.id.button3);
	btn_cancel = (Button)findViewById(R.id.button4);
	
final String[] str={"Y","H","Q","M"};		
spinner.setAdapter(new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, str, pmode));
		

	
final String[] str1={"InForce","Paid-up","Lapsed","Single"};	
spinner1.setAdapter(new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, str1, pstatus));	
	


final String[] str2={"01","02","03","04","05","06","07","08","09","10","11","12"};	
spinner2.setAdapter(new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, str2, pfupmm));		


final String[] str3={"2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};	
spinner3.setAdapter(new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, str3, pfupyyyy));

final String[] str4={"O","S"};
Type.setAdapter(new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, str4, ptype));

   




btn_submit.setOnClickListener(new OnClickListener() {

@Override
public void onClick(View v) {
// TODO Auto-generated method stub
//triggers the DatePickerDialog
	validate();

}
});


btn_cancel.setOnClickListener(new OnClickListener() {

@Override
public void onClick(View v) {
// TODO Auto-generated method stub
//triggers the DatePickerDialog
AboutEdit.this.finish();
}
});






		
etDate=(EditText)findViewById(R.id.textView4);
change_date=(Button)findViewById(R.id.button1);	
		
change_date.setOnClickListener(new OnClickListener() {

@Override
public void onClick(View v) {
// TODO Auto-generated method stub
//triggers the DatePickerDialog
showDialog(Date_Dialog_ID);
}
});
//getting current date
cDate=Calendar.getInstance();
cDay=cDate.get(Calendar.DAY_OF_MONTH);
cMonth=cDate.get(Calendar.MONTH);
cYear=cDate.get(Calendar.YEAR);
//assigning the edittext with the current date in the beginning
sDay=cDay;
sMonth=cMonth;
sYear=cYear;
updateDateDisplay(sYear,sMonth,sDay);		

etDate1=(EditText)findViewById(R.id.textView31);
change_date1=(Button)findViewById(R.id.button2);	
		
change_date1.setOnClickListener(new OnClickListener() {

@Override
public void onClick(View v) {
// TODO Auto-generated method stub
//triggers the DatePickerDialog
showDialog(1);
}
});	
		
		updateDateDisplay1(sYear,sMonth,sDay);	
		
	PolicyNo = (EditText)findViewById(R.id.textView2);
	PolName = (EditText)findViewById(R.id.textView6);
	Premium = (EditText)findViewById(R.id.textView14);
	Add1 = (EditText)findViewById(R.id.textView20);
	Add2 = (EditText)findViewById(R.id.textView22);
	Add3 = (EditText)findViewById(R.id.textView24);
	Pin = (EditText)findViewById(R.id.textView26);
	Units = (EditText)findViewById(R.id.textView30);
	Plan = (EditText)findViewById(R.id.textView8);
	Term = (EditText)findViewById(R.id.textView10);
	SA = (EditText)findViewById(R.id.textView12);
	Mobile=(EditText)findViewById(R.id.textView34);
	Email=(EditText)findViewById(R.id.textView36);
	Branch = (EditText)findViewById(R.id.editText2);
	Mobile = (EditText)findViewById(R.id.textView34);
	
	

	PolicyNo.setText(ppolicyno);
	Mobile.setText(pmobile);
	PolName.setText(pname);
	Premium.setText(pprem);
	Branch.setText(pbranch);
	Add1.setText(padd1);
	Add2.setText(padd2);
	Add3.setText(padd3);
	Pin.setText(ppin);
	Email.setText(pemail);
	Plan.setText(pplan);
	Term.setText(pterm);
	SA.setText(psa);
	Units.setText(punits);
	etDate.setText(pdoc);
	if (pdob != null && pdob.toString().length()>0){
	etDate1.setText(pdob.substring(0,4)+"/"+pdob.substring(4,6)+"/"+pdob.substring(6,8));}else {
		etDate1.setText("");
	}
	Email.setText(pemail);
	
	
	
	}
	@Override
	protected Dialog onCreateDialog(int id) {
	switch (id) {
	case Date_Dialog_ID:
	return new DatePickerDialog(this, onDateSet, cYear, cMonth,	cDay);
	}
	switch (id) {
	case 1:
	return new DatePickerDialog(this, onDateSet1, cYear, cMonth,cDay);
	}
	return null;
	}
	
	private void updateDateDisplay(int year,int month,int date) {
		// TODO Auto-generated method stub
	//	etDate.setText(date+"-"+(month+1)+"-"+year);
		etDate.setText(year+"/"+(month+1)+"/"+date);
		}
	private void updateDateDisplay1(int year,int month,int date) {
		// TODO Auto-generated method stub
	//	etDate1.setText(date+"-"+(month+1)+"-"+year);
		etDate1.setText(year+"/"+(month+1)+"/"+date);
		}
		private OnDateSetListener onDateSet=new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
		// TODO Auto-generated method stub
		System.out.println("2");
		sYear=year;
		sMonth=monthOfYear;
		sDay=dayOfMonth;
		updateDateDisplay(sYear,sMonth,sDay);
		}};
		
		private OnDateSetListener onDateSet1=new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			// TODO Auto-generated method stub
			System.out.println("2");
			sYear=year;
			sMonth=monthOfYear;
			sDay=dayOfMonth;
			updateDateDisplay1(sYear,sMonth,sDay);
			}};	
		
		
		
		
		
		
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about_add, menu);
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
		return super.onOptionsItemSelected(item);
	}	
		
		
		
		
		
		
		
		
		private void insert(){
			DateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
			DateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
			DateFormat outputFormat1 = new SimpleDateFormat("yyyyMMdd");
			
			try {
				date = inputFormat.parse(etDate.getText().toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String outputDateStr = outputFormat.format(date);
			
			
			try {
				date1 = inputFormat.parse(etDate1.getText().toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(date1 != null && date1.toString().length()>0){
				 outputDateStr1 = outputFormat1.format(date1);
				}else{
					 outputDateStr1 = "";
				}
			
			 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(AboutEdit.this);
			 SQLiteDatabase database = stockSQLHelper.getWritableDatabase();  		
			 ContentValues values;
			  values = new ContentValues();
     /*       
              
             
              
              values.put(bmpolmast.PM_AGCODE, str[6]);
              values.put(bmpolmast.PM_DOCODE, str[7]);*/
              
              
              values.put(bmpolmast.PM_SA, SA.getText().toString());
              values.put(bmpolmast.PM_TERM, Term.getText().toString());
              values.put(bmpolmast.PM_TYPE,  Type.getSelectedItem().toString());
			  
              values.put(bmpolmast.PM_ADD1, Add1.getText().toString());
              values.put(bmpolmast.PM_ADD2, Add2.getText().toString());
              values.put(bmpolmast.PM_ADD3, Add3.getText().toString());
              values.put(bmpolmast.PM_PIN, Pin.getText().toString());
              values.put(bmpolmast.PM_PLAN, Plan.getText().toString());
              values.put(bmpolmast.PM_UNITS, Units.getText().toString());
              if (Branch.getText().toString().length() > 4){
              values.put(bmpolmast.PM_BRANCH, Branch.getText().toString());
              }else{
              	values.put(bmpolmast.PM_BRANCH, " ");	
              }
              
              values.put(bmpolmast.PM_DOB, outputDateStr1); 
              values.put(bmpolmast.PM_DOC, outputDateStr);
                values.put(bmpolmast.PM_POLNO, PolicyNo.getText().toString());
                values.put(bmpolmast.PM_NAME, PolName.getText().toString());
                values.put(bmpolmast.PM_PREM, Premium.getText().toString());  
                values.put(bmpolmast.PM_MOD, spinner.getSelectedItem().toString());   
                values.put(bmpolmast.PM_STATUS, spinner1.getSelectedItem().toString()); 
              String tfup = spinner2.getSelectedItem().toString().concat("/").concat(spinner3.getSelectedItem().toString());
              values.put(bmpolmast.PM_FUP, tfup);
                values.put(bmpolmast.PM_MOBILE, Mobile.getText().toString());  
               
                values.put(bmpolmast.PM_EMAIL, Email.getText().toString());
                
                
            int rowsUpdated =  database.update(bmpolmast.TABLE_STOCK, values, "policyno =" + ppolicyno,null);	
          	AlertDialog.Builder builder1 = new AlertDialog.Builder(AboutEdit.this);
            builder1.setMessage(rowsUpdated + " Record Updated successfully ...");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Continue ...",
                    new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    AboutEdit.this.finish();      
                }
            });
            AlertDialog alert11 = builder1.create();
            alert11.show();
	
		}
		private void validate(){
			String flag ="N";
			try
			{
				type = Type.getSelectedItem().toString().trim();
				 flag = "Y";
				
			}catch (Exception e) {
				AlertDialog.Builder builder1 = new AlertDialog.Builder(AboutEdit.this);
	            builder1.setMessage("Enter Type O/S ....");
	            builder1.setCancelable(true);
	            builder1.setPositiveButton("Continue ...",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });
	            AlertDialog alert11 = builder1.create();
	            alert11.show();	
				}
                 if (flag.equals("Y")){
			 if
			 (PolicyNo.getText().toString().trim().length() == 0 ){
				AlertDialog.Builder builder1 = new AlertDialog.Builder(AboutEdit.this);
	            builder1.setMessage("Enter Policy No.");
	            builder1.setCancelable(true);
	            builder1.setPositiveButton("Continue ...",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });
	            AlertDialog alert11 = builder1.create();
	            alert11.show();
			}
			else if (PolName.getText().toString().trim().length() == 0)   {
				AlertDialog.Builder builder1 = new AlertDialog.Builder(AboutEdit.this);
	            builder1.setMessage("Enter PolicyHolder Name ....");
	            builder1.setCancelable(true);
	            builder1.setPositiveButton("Continue ...",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });
	            AlertDialog alert11 = builder1.create();
	            alert11.show();	
			}
			else if (Premium.getText().toString().trim().length() == 0)   {
				AlertDialog.Builder builder1 = new AlertDialog.Builder(AboutEdit.this);
	            builder1.setMessage("Enter Premium Amount ....");
	            builder1.setCancelable(true);
	            builder1.setPositiveButton("Continue ...",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });
	            AlertDialog alert11 = builder1.create();
	            alert11.show();	
			}
			else if (Branch.getText().toString().trim().length() == 0)   {
				AlertDialog.Builder builder1 = new AlertDialog.Builder(AboutEdit.this);
	            builder1.setMessage("Enter Branch ....");
	            builder1.setCancelable(true);
	            builder1.setPositiveButton("Continue ...",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });
	            AlertDialog alert11 = builder1.create();
	            alert11.show();	
			}
			
			
			else {
            insert();
			}
		}}
			}

