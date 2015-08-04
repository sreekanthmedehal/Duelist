package com.sreekanth.duelist;

import java.lang.reflect.Field;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

public class MainActivity extends Activity {
String EXTRA_MESSAGE;
//DBHelper dbhelper;
SQLiteDatabase database;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		  ActionBar actionBar = getActionBar();
    	  actionBar.setDisplayHomeAsUpEnabled(true);
	    
setContentView(R.layout.msk);

		
		try {
	        ViewConfiguration config = ViewConfiguration.get(this);
	        Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
	        if(menuKeyField != null) {
	            menuKeyField.setAccessible(true);
	            menuKeyField.setBoolean(config, false);
	        }
	    } catch (Exception ex) {
	        // Ignore
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	        return super.onCreateOptionsMenu(menu);

	}

public void NewApril(View view) {
	int norecs = getDuesCount("apr");
		Toast.makeText(this.getBaseContext(), "No.of Records(April) :: "+ norecs, 
 		        Toast.LENGTH_SHORT).show();
 Intent intent=new Intent(this,BillActivity.class);
intent.putExtra("ComingFrom", "apr");
startActivity(intent); 
}

public void NewMay(View view) {
	int norecs = getDuesCount("may");
	Toast.makeText(this.getBaseContext(), "No.of Records(May) :: "+ norecs, 
		        Toast.LENGTH_SHORT).show();
	 Intent intent=new Intent(this,BillActivity.class);
	intent.putExtra("ComingFrom", "may");
	startActivity(intent); 
	}
public void NewJune(View view) {
	int norecs = getDuesCount("jun");
	Toast.makeText(this.getBaseContext(), "No.of Records(June) :: "+ norecs, 
		        Toast.LENGTH_SHORT).show();
	 Intent intent=new Intent(this,BillActivity.class);
	intent.putExtra("ComingFrom", "jun");
	startActivity(intent); 
	}

public void NewJuly(View view) {
	int norecs = getDuesCount("jul");
	Toast.makeText(this.getBaseContext(), "No.of Records(July) :: "+ norecs, 
		        Toast.LENGTH_SHORT).show();
	 Intent intent=new Intent(this,BillActivity.class);
	intent.putExtra("ComingFrom", "jul");
	startActivity(intent); 
	}




public void NewAugust(View view) {
	int norecs = getDuesCount("aug");
	Toast.makeText(this.getBaseContext(), "No.of Records(August) :: "+ norecs, 
		        Toast.LENGTH_SHORT).show();
	 Intent intent=new Intent(this,BillActivity.class);
	intent.putExtra("ComingFrom", "aug");
	startActivity(intent); 
	}

public void NewSeptember(View view) {
	int norecs = getDuesCount("sep");
	Toast.makeText(this.getBaseContext(), "No.of Records(September) :: "+ norecs, 
		        Toast.LENGTH_SHORT).show();
	 Intent intent=new Intent(this,BillActivity.class);
	intent.putExtra("ComingFrom", "sep");
	startActivity(intent); 
	}

public void NewOctober(View view) {
	int norecs = getDuesCount("oct");
	Toast.makeText(this.getBaseContext(), "No.of Records(October) :: "+ norecs, 
		        Toast.LENGTH_SHORT).show();
	 Intent intent=new Intent(this,BillActivity.class);
	intent.putExtra("ComingFrom", "oct");
	startActivity(intent); 
	}

public void NewNovember(View view) {
	int norecs = getDuesCount("nov");
	Toast.makeText(this.getBaseContext(), "No.of Records(November) :: "+ norecs, 
		        Toast.LENGTH_SHORT).show();
	 Intent intent=new Intent(this,BillActivity.class);
	intent.putExtra("ComingFrom", "nov");
	startActivity(intent); 
	}
public void NewDecember(View view) {
	int norecs = getDuesCount("dece");
	Toast.makeText(this.getBaseContext(), "No.of Records(December) :: "+ norecs, 
		        Toast.LENGTH_SHORT).show();
	 Intent intent=new Intent(this,BillActivity.class);
	intent.putExtra("ComingFrom", "dece");
	startActivity(intent); 
	}
public void NewJanuary(View view) {
	int norecs = getDuesCount("jan");
	Toast.makeText(this.getBaseContext(), "No.of Records(Jan) :: "+ norecs, 
		        Toast.LENGTH_SHORT).show();
	 Intent intent=new Intent(this,BillActivity.class);
	intent.putExtra("ComingFrom", "jan");
	startActivity(intent); 
	}

public void NewFeb(View view) {
	int norecs = getDuesCount("feb");
	Toast.makeText(this.getBaseContext(), "No.of Records(Feb) :: "+ norecs, 
		        Toast.LENGTH_SHORT).show();
	 Intent intent=new Intent(this,BillActivity.class);
	intent.putExtra("ComingFrom", "feb");
	startActivity(intent); 
	}

public void NewMarch(View view) {
	int norecs = getDuesCount("mar");
	Toast.makeText(this.getBaseContext(), "No.of Records(March) :: "+ norecs, 
		        Toast.LENGTH_SHORT).show();
	 Intent intent=new Intent(this,BillActivity.class);
	intent.putExtra("ComingFrom", "mar");
	startActivity(intent); 
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

  public int getDuesCount(String wmonth) {
  DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
  SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
  int numDRows = (int) DatabaseUtils.longForQuery(database, "SELECT COUNT(*) FROM bmbillmast WHERE " + wmonth + " = 'Y' ", null);
  return numDRows;
    }
public void close() {
 
  database.close();
} 	
}


