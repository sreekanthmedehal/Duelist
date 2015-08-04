package com.sreekanth.duelist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
 
public class Calcstore extends Activity {
	 SharedPreferences preferences;
		SharedPreferences.Editor editor;
	 public static final String KEY_ROWID = "_id";
	 public static final String KEY_NAME = "name";
	 public static final String KEY_EMAIL = "email";
	 public static final String KEY_MOBILE = "mobile";
	 public static final String KEY_PLAN = "plan";
	 public static final String KEY_AGE = "age";
	 public static final String KEY_TERM = "term";
	 public static final String KEY_SA = "sa";
	 private static final String SQLITE_TABLE = PremCalc.TABLE_STOCK;
 private MySimpleCursorAdapter1 dataAdapter;
 SQLiteDatabase database;
 DataViewerSQLiteHelper stockSQLHelper;
 //Tabbed tabbed;
 private static final String TAG = "CountriesDbAdapter";
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_calcstore);
  stockSQLHelper = new DataViewerSQLiteHelper(this);
  database = stockSQLHelper.getReadableDatabase();  
  
 
  //Clean all data
 // deleteAllCountries();
  //Add some data
 // insertSomeCountries();
 
  //Generate ListView from SQLite Database
  displayListView();
 
 }
 
 private void displayListView() {
 
 
  Cursor cursor = fetchAllCountries();
 
  // The desired columns to be bound
  String[] columns = new String[] {
   // KEY_CODE,
	PremCalc.REG_ID,
    PremCalc.REG_NAME,
    PremCalc.REG_PLAN,
	PremCalc.REG_AGE,
	PremCalc.REG_TERM,
	PremCalc.REG_EMAIL,
	PremCalc.REG_MOBILE,
	PremCalc.REG_SA
   // KEY_CONTINENT,
   // KEY_REGION
  };
 
  // the XML defined views which the data will be bound to
  int[] to = new int[] { 
    R.id.code,
    R.id.name,
    R.id.continent,
    R.id.region,
  };
 
  // create the adapter using the cursor pointing to the desired data 
  //as well as the layout information
  dataAdapter = new MySimpleCursorAdapter1(
    this, R.layout.country_info, 
    cursor, 
    columns, 
    to,
    0);
 
  ListView listView = (ListView) findViewById(R.id.listView1);
  // Assign adapter to ListView
  listView.setAdapter(dataAdapter);
 
 
  listView.setOnItemClickListener(new OnItemClickListener() {
   @Override
   public void onItemClick(AdapterView<?> listView, View view, 
     int position, long id) {
   // Get the cursor, positioned to the corresponding row in the result set
   Cursor cursor = (Cursor) listView.getItemAtPosition(position);
 
   // Get the state's capital from this row in the database.
   String ClickedName = 
    cursor.getString(cursor.getColumnIndexOrThrow("name"));
   String ClickedPlan = 
		    cursor.getString(cursor.getColumnIndexOrThrow("plan"));
   String ClickedEmail = 
		    cursor.getString(cursor.getColumnIndexOrThrow("email"));
   String Clickedmobile = 
		    cursor.getString(cursor.getColumnIndexOrThrow("mobile"));
   String Clickedage = 
		    cursor.getString(cursor.getColumnIndexOrThrow("age"));
   String Clickedterm = 
		    cursor.getString(cursor.getColumnIndexOrThrow("term"));
   String Clickedsa = 
		    cursor.getString(cursor.getColumnIndexOrThrow("sa"));
   Toast.makeText(getApplicationContext(),
    ClickedName, Toast.LENGTH_SHORT).show();
   preferences = PreferenceManager.getDefaultSharedPreferences(Calcstore.this);
   editor = preferences.edit();
   editor.putString("TName", ClickedName);
   editor.putString("TPlan",ClickedPlan);
   editor.putString("TEmail",ClickedEmail);
   editor.putString("TMobile",Clickedmobile);
   editor.putString("TAge",Clickedage);
   editor.putString("TTerm",Clickedterm);
   editor.putString("TSa",Clickedsa);
   
   
   
   
   
   
   
   editor.apply();
   startActivity (new Intent(getApplicationContext(), BlankActvity.class)); 
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
 
 //8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
 public boolean deleteAllCountries() {
	 
	  int doneDelete = 0;
	  doneDelete = database.delete(SQLITE_TABLE, null , null);
	  Log.w(TAG, Integer.toString(doneDelete));
	  return doneDelete > 0;
	 
	 }
 
	 public Cursor fetchCountriesByName(String inputText) throws SQLException {
	  Log.w(TAG, inputText);
	  Cursor mCursor = null;
	  if (inputText == null  ||  inputText.length () == 0)  {
	   mCursor = database.query(SQLITE_TABLE, new String[] {KEY_ROWID,
	      KEY_NAME,KEY_PLAN,KEY_AGE,KEY_TERM,KEY_EMAIL,KEY_MOBILE,KEY_SA}, 
	     null, null, null, null, null);
	 
	  }
	  else {
	   mCursor = database.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
	      KEY_NAME,KEY_PLAN,KEY_AGE,KEY_TERM,KEY_EMAIL,KEY_MOBILE,KEY_SA}, 
	     KEY_NAME + " like '%" + inputText + "%'", null,
	     null, null, null, null);
	  }
	  if (mCursor != null) {
	   mCursor.moveToFirst();
	  }
	  return mCursor;
	 
	 }
	 
	 public Cursor fetchAllCountries() {
	 
	  Cursor mCursor = database.query(SQLITE_TABLE, new String[] {KEY_ROWID,
	     KEY_NAME,KEY_PLAN,KEY_AGE,KEY_TERM,KEY_EMAIL,KEY_MOBILE,KEY_SA}, 
	    null, null, null, null, null);
	 
	  if (mCursor != null) {
	   mCursor.moveToFirst();
	  }
	  return mCursor;
	 }
	 public void close() {
		  if (stockSQLHelper != null) {
			  stockSQLHelper.close();
		  }
		 }
}