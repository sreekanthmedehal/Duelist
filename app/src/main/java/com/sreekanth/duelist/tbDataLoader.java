package com.sreekanth.duelist;



	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

	public class tbDataLoader {

	    private Context context;
	    private SQLiteDatabase database;
	    private String tbFile;

	    // Inserting sample record
	    ContentValues values;

	    public tbDataLoader(Context context, SQLiteDatabase database,
	            String strFile) {
	        this.context = context;
	        this.database = database;
	        this.tbFile = strFile;
	    }

	    public void loadData() throws IOException {
	        BufferedReader input = new BufferedReader(new InputStreamReader(context
	                .getAssets().open(tbFile)));
	        String line;
	        while ((line = input.readLine()) != null) {
	            String[] RowData = line.split(",");
	            if (RowData.length == 4) {
	            	
	                values = new ContentValues();
	                values.put(Tbprem.REG_PLAN, RowData[0]);
	                values.put(Tbprem.REG_AGE, RowData[1]);
	                values.put(Tbprem.REG_TERM, RowData[2]);
	                values.put(Tbprem.REG_PREM, RowData[3]);
	                
	               
	               
	                
	                database.insert(Tbprem.TABLE_STOCK, null, values);
	            }

	        }
	      
	    }
	}


