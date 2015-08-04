package com.sreekanth.duelist;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Agecalc extends Activity implements OnClickListener {
 private ImageButton ib;
 private Calendar cal;
 private int day;
 private int month;
 private int year;
 private EditText et;
 private TextView tv;
 String birthdate;
 DateTime FUP1cdate;
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.agecalc);
  // mDateButton = (Button) findViewById(R.id.date_button);
  tv = (TextView) findViewById(R.id.textView1);
  ib = (ImageButton) findViewById(R.id.imageButton1);
  cal = Calendar.getInstance();
  day = cal.get(Calendar.DAY_OF_MONTH);
  month = cal.get(Calendar.MONTH);
  year = cal.get(Calendar.YEAR);
  et = (EditText) findViewById(R.id.editText);
  ib.setOnClickListener(this);
  
 }

 @Override
 public void onClick(View v) {
  showDialog(0);
 }

 @Override
 @Deprecated
 protected Dialog onCreateDialog(int id) {
  return new DatePickerDialog(this, datePickerListener, year, month, day);
 }

 private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
  public void onDateSet(DatePicker view, int selectedYear,
    int selectedMonth, int selectedDay) {
   et.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
     + selectedYear);
  
  
  
   SimpleDateFormat  dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
  birthdate = selectedDay + "/" + (selectedMonth + 1) + "/"
		     + selectedYear+" 00:00:00";
  try {
		FUP1cdate = new DateTime(dateformat.parse(birthdate));
		
	} catch (java.text.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
   DateTime now = new DateTime();
   Period period = new Period(FUP1cdate, now, PeriodType.yearMonthDay());
 //Now access the values as below
int days = period.getDays();
 int months = period.getMonths();
 int years = period.getYears();
 
  if (days > 15){
	  months=months+1;
  }
  if (months > 6){
	  years = years +1;
  }
  tv.setText("Result : " +period.getYears() + " Years "+ period.getMonths() + " Months "+period.getDays() + " Days " +"\n"
               + "Age Near Birthday " + years); 
 };
};
}