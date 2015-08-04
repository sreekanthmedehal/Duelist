package com.sreekanth.duelist;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class AndroidListViewCursorAdaptorActivity extends Activity {
 
	 private SimpleCursorAdapter dataAdapter;
     String gotmessage;
     String sql;
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_android_list_view_cursor_adaptor);
  Intent iin= getIntent();
  Bundle b = iin.getExtras();
  if(b!=null){ gotmessage =(String) b.get("MESSAGE");}
 
  //Generate ListView from SQLite Database
  displayListView();
 
 }
 
 private void displayListView() {
 
  Cursor cursor = fetchAllCountries(gotmessage);
 
  // The desired columns to be bound
  String[] columns = new String[] {
    AgentData.AG_CODE,
    AgentData.AG_NAME,
    AgentData.AG_ELGCODE,
    AgentData.AG_CLUB,
    AgentData.AG_TEL,
    AgentData.AG_EMAIL
  };
 
  // the XML defined views which the data will be bound to
  int[] to = new int[] {
    R.id.code,
    R.id.name,
    R.id.continent,
    R.id.region,
    R.id.mobile,
    R.id.email
  };
 
  // create the adapter using the cursor pointing to the desired data
  //as well as the layout information
  dataAdapter = new SimpleCursorAdapter(
    this, R.layout.country_info1,
    cursor,
    columns,
    to,
    0);
 
  ListView listView = (ListView) findViewById(R.id.listView1);
  // Assign adapter to ListView
  listView.setAdapter(dataAdapter);
  int agcount = cursor.getCount();
  TextView ctext = (TextView)findViewById(R.id.textView2);
  ctext.setText(String.valueOf(agcount));
 
  listView.setOnItemClickListener(new OnItemClickListener() {
   @Override
   public void onItemClick(AdapterView<?> listView, View view,
     int position, long id) {
   // Get the cursor, positioned to the corresponding row in the result set
   Cursor cursor = (Cursor) listView.getItemAtPosition(position);
 
   // Get the state's capital from this row in the database.
   String countryCode =
    cursor.getString(cursor.getColumnIndexOrThrow("agcode"));
   Toast.makeText(getApplicationContext(),
     countryCode, Toast.LENGTH_SHORT).show();
   Intent pintent=new Intent(getApplicationContext(),AgFrmActivity.class);
   pintent.putExtra("pagcode", countryCode);
   startActivity(pintent); 
 
   }
  });
 
  EditText myFilter = (EditText) findViewById(R.id.myFilter);
  myFilter.addTextChangedListener(new TextWatcher() {
 
   public void afterTextChanged(Editable s) {
   }
 
   public void beforeTextChanged(CharSequence s, int start,
     int count, int after) {
   }
 
   public void onTextChanged(CharSequence s, int start,
     int before, int count) {
    dataAdapter.getFilter().filter(s.toString());
   }
  });
   
  dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
         public Cursor runQuery(CharSequence constraint) {
             return fetchCountriesByName(constraint.toString());
         }
     });
  }
 private Cursor fetchAllCountries(String xxx){
	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
     Log.w("TAG", xxx);
     Cursor agdetails = null;
      sql = "";
     if (xxx.equals("InForce")){
    	  sql = "SELECT _id,agcode,agname,elgcode,agclub,tel,email FROM agentdata WHERE elgcode = '1'";
         agdetails = database.rawQuery(sql, null);   
     }
     if (xxx.equals("Probation")){
    	 sql = "SELECT _id,agcode,agname,elgcode,agclub,tel,email FROM agentdata WHERE elgcode = '8'";
         agdetails = database.rawQuery(sql, null);   
     }
     if (xxx.equals("CM")){
    	 sql = "SELECT _id,agcode,agname,elgcode,agclub,tel,email FROM agentdata WHERE agclub = '4'";
         agdetails = database.rawQuery(sql, null);   
     }
     if (xxx.equals("ZM")){
    	 sql = "SELECT _id,agcode,agname,elgcode,agclub,tel,email FROM agentdata WHERE agclub = '3'";
         agdetails = database.rawQuery(sql, null);   
     }
     if (xxx.equals("DM")){
    	 sql = "SELECT _id,agcode,agname,elgcode,agclub,tel,email FROM agentdata WHERE agclub = '2'";
         agdetails = database.rawQuery(sql, null);   
     }
     if (xxx.equals("BM")){
    	 sql = "SELECT _id,agcode,agname,elgcode,agclub,tel,email FROM agentdata WHERE agclub = '1'";
         agdetails = database.rawQuery(sql, null);   
     }
     if (xxx.equals("DA")){
    	 sql = "SELECT _id,agcode,agname,elgcode,agclub,tel,email FROM agentdata WHERE agclub = '9'";
         agdetails = database.rawQuery(sql, null);   
     }
     if (xxx.equals("All")){
    	 sql = "SELECT _id,agcode,agname,elgcode,agclub,tel,email FROM agentdata WHERE agcode <> ''";
         agdetails = database.rawQuery(sql, null);   
     }
     if (xxx.equals("No Email")){
    	 sql = "SELECT _id,agcode,agname,elgcode,agclub,tel,email FROM agentdata WHERE TRIM(email) = '' and elgcode = '1'";
    	 agdetails = database.rawQuery(sql, null);
     }
     if (xxx.equals("No Mobile")){
    	 sql = "SELECT _id,agcode,agname,elgcode,agclub,tel,email FROM agentdata WHERE tel = '0000000000' and elgcode = '1'";
    	 agdetails = database.rawQuery(sql, null);
     }
        agdetails.moveToFirst();
        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
        return agdetails;
 
	}
 private Cursor fetchCountriesByName(String acs){
	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
  
    //    if (yyy=="InForce"){
        sql = sql + " AND agname like '%"+acs+"%'" ;

        Cursor agetails = database.rawQuery(sql, null);
        agetails.moveToFirst();
        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
        return agetails;
	 
	}
}
 
