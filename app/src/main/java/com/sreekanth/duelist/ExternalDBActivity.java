package com.sreekanth.duelist;

/**
 * Created by msk on 11/24/13.
 */
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ExternalDBActivity extends Activity {
    /** Called when the activity is first created. */
  //  DBHelper dbhelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if you use siplecursoradapter then you should have _id as one of column name and its values should be integer in your db.
        // so "_id", "columnName1", "columnName2" are column names from your db.
        String[] from = new String[] { "_id", "policyno", "polname" };
        int[] to = new int[] { R.id.TextView1, R.id.TextView2, R.id.TextView3 };

     

        Cursor c = getData();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.list, c, from, to);

        ListView list = (ListView) findViewById(R.id.ListView1);

        list.setAdapter(adapter);
    }
    public Cursor getData() {
		DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
    
        Cursor c = database.rawQuery("SELECT * FROM bmpolmast", null);
       
        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.
        return c;
    }
    
}