package com.sreekanth.duelist;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Profile extends Activity {
	
	private String[] regColumns = {
			Registration.REG_AGCODE,
			Registration.REG_COUPONNO,
			Registration.REG_REGFLAG,
			Registration.REG_MODEL,
			Registration.REG_SDATE,
			Registration.REG_EDATE,
			Registration.REG_ACTIVATED,
			Registration.REG_EMAIL,
			Registration.REG_MOBILE,
			Registration.REG_REGCODE,
			Registration.REG_PRODKEY,
			Registration.REG_NAME,
			Registration.REG_BRCODE,
			Registration.REG_DEALERCODE,
			Registration.REG_ADD};
	
	

	String ragcode;
	String portalpwd;
	String designation;
	String model;
	String startdate;
	String enddate;
	String activated;
	String mobile;
	String regcode;
	String prodkey;
	String email;
	String agname;
	String brcode;
	String dealercode;
	String address;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		   DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	       SQLiteDatabase database = stockSQLHelper.getReadableDatabase();

	Cursor b = database.query(Registration.TABLE_STOCK, regColumns, null, null, null, null, null);
    b.moveToFirst();

   




if (b.getCount()>0){
ragcode = b.getString(b.getColumnIndexOrThrow("regagcode"));	
portalpwd = b.getString(b.getColumnIndex("regcouponno"));
designation = b.getString(b.getColumnIndex("regflag"));
model = b.getString(b.getColumnIndex("regmodel"));
startdate = b.getString(b.getColumnIndex("startdate"));
enddate = b.getString(b.getColumnIndex("enddate"));
activated = b.getString(b.getColumnIndex("regactivated"));
mobile =b.getString(b.getColumnIndex("regmobile"));
regcode =b.getString(b.getColumnIndex("regcode"));
prodkey = b.getString(b.getColumnIndex("prodkey"));
email = b.getString(b.getColumnIndex("regemail"));
agname = b.getString(b.getColumnIndex("agname"));
brcode = b.getString(b.getColumnIndex("brcode"));
dealercode = b.getString(b.getColumnIndex("dealercode"));
address = b.getString(b.getColumnIndex("address"));
}

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
TextView Textnmrl3 = (TextView)findViewById(R.id.textView22);
TextView Textnmrl4 = (TextView)findViewById(R.id.textView24);
TextView Textnmrl5 = (TextView)findViewById(R.id.textView26);
TextView Textdc2 = (TextView)findViewById(R.id.textView28);
TextView Textad1 = (TextView)findViewById(R.id.textView30);



if(ragcode!=null)
{
Textc2.setText(ragcode);
Textn2.setText(portalpwd);
Textd2.setText(designation);
Textl2.setText(model);
Texte2.setText(startdate);
Textac2.setText(enddate);
Textbn2.setText(activated);
Textt2.setText(mobile);
Textnm2.setText(regcode);
Textnmrl2.setText(prodkey);
Textnmrl3.setText(email);
Textnmrl4.setText(agname);
Textnmrl5.setText(brcode);
Textdc2.setText(dealercode);
Textad1.setText(address);

}

b.close();
database.close();
stockSQLHelper.close();	
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
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
}
