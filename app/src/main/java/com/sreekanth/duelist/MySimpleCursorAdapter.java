package com.sreekanth.duelist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

class MySimpleCursorAdapter extends SimpleCursorAdapter {
    public MySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }
    int no_of_inst = 0; TextView formloandt,formloanamt;
    
    public View newView(Context _context, Cursor _cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(_context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list1, parent, false);
        return view;
    }
//    String[] from = new String[] { "_id", "policyno", "polname", "prem", "fup"};  
//	  int[] to = new int[] { R.id.TextView1, R.id.TextView2, R.id.TextView3, R.id.TextView4, R.id.TextView5 };  
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        String id = cursor.getString(cursor.getColumnIndex(bmpolmast.ID));
        String policyno = cursor.getString(cursor.getColumnIndex(bmpolmast.PM_POLNO));
        String polname = cursor.getString(cursor.getColumnIndex(bmpolmast.PM_NAME)).trim();
        String prem = cursor.getString(cursor.getColumnIndex(bmpolmast.PM_PREM));
        String fup = cursor.getString(cursor.getColumnIndex(bmpolmast.PM_FUP));
        String mode = cursor.getString(cursor.getColumnIndex(bmpolmast.PM_MOD));
        String loandt = cursor.getString(cursor.getColumnIndex(bmpolmast.PM_LOANDT));
       String  loanamt = cursor.getString(cursor.getColumnIndex(bmpolmast.PM_LOANAMT));
        String duedate =  cursor.getString(cursor.getColumnIndexOrThrow("doc"));
        String memo = cursor.getString(cursor.getColumnIndexOrThrow(bmpolmast.PM_MEMO));
        if (duedate.trim().length()>0){
        String dtStart = duedate;  
        String datetime;
   	 String datetimedd;
   	 Date date = null;
   	 String FUP1;
   	 String datetimetoday;
   	 DateTime FUP1cdate = null;
   	 no_of_inst=0;
		   SimpleDateFormat  format = new SimpleDateFormat("yyyy/MM/dd");  
		   try {  
		        date = format.parse(dtStart);  
		  //     System.out.println(date);  
		   } catch (ParseException e) {  
		       // TODO Auto-generated catch block  
		       e.printStackTrace();  
		   }      
 	      
		   
		   SimpleDateFormat  dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		   Date date1 = date;  
		 datetime = dateformat.format(date1);
		 datetimedd = datetime.substring(0,2);
		 datetimetoday = dateformat.format(new Date());
	//	System.out.println("Current Date Time : " + datetime);
		   FUP1 = datetimedd+"/"+fup+" 00:00:00";
		
                  SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                  try {
      				FUP1cdate = new DateTime(format1.parse(FUP1));
      			} catch (java.text.ParseException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}  
                  DateTime test = new DateTime();
    String no_of_days =  String.valueOf(Days.daysBetween(FUP1cdate,test).getDays());  
        int mode_days =0 ;
      	 
    if (mode.equals("Y")){
	    mode_days = 365;
   }else if (mode.equals("H")){
	    mode_days = 180;
   }else if (mode.equals("Q")){
	    mode_days = 90;
   }else if (mode.equals("M")){
	    mode_days = 30;
   }
   // System.out.println("Mode : " + mode );
   
   
   
   
if (Integer.parseInt(no_of_days) > 0 && mode.trim().length()>0){
   no_of_inst = (Integer.parseInt(no_of_days)/(mode_days))+1;}  }
        TextView formid = (TextView) view.findViewById(R.id.TextView1);
        formid.setText(id);
        
        TextView formpolno = (TextView) view.findViewById(R.id.TextView2);
        formpolno.setText(policyno);
        
        TextView formname = (TextView) view.findViewById(R.id.TextView3);
        formname.setText("Name : " +polname);
        
        TextView formprem = (TextView) view.findViewById(R.id.TextView4);
        formprem.setText("Prem : " + prem);
        
        TextView formfup = (TextView) view.findViewById(R.id.TextView5);
        formfup.setText("FUP  : " + fup);
        TextView formmode = (TextView) view.findViewById(R.id.TextView6);
        formmode.setText("Mode : " + mode);
        
        
         formloandt = (TextView)view.findViewById(R.id.TextView7);
        formloanamt = (TextView)view.findViewById(R.id.TextView8);
        formloandt.setText("Loan Dt : "+ loandt);
        
        
        formloanamt.setText("Loan Amount : "+ loanamt);
        if (loanamt == null){
        
        
     
        	formloandt.setVisibility(View.GONE);
        	formloanamt.setVisibility(View.GONE);
        }else{
        	formloandt.setVisibility(View.VISIBLE);
        	formloanamt.setVisibility(View.VISIBLE);
        }
        	
        TextView formmemo = (TextView) view.findViewById(R.id.TextView9);
        formmemo.setText("Memo : " + memo);
        if (memo == null){
            
            
            
        	formmemo.setVisibility(View.GONE);
        	
        }else{
        	formmemo.setVisibility(View.VISIBLE);
        	
        }
        
        Button yourButton = (Button) view.findViewById(R.id.imageButton2);
        yourButton.setText(String.valueOf(no_of_inst));
        yourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view != null) {
                    Object obj = view.getTag();
                   
                   
                    String st = obj.toString();
                   
                //    Toast.makeText(context, " id = " + st, Toast.LENGTH_LONG).show();

                }
            }
        });
        Object obj = cursor.getString(cursor.getColumnIndex(bmpolmast.ID));
        yourButton.setTag(obj);


    }
}