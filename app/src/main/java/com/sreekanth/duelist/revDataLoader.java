package com.sreekanth.duelist;



	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

	public class revDataLoader {

	    private Context context;
	    private SQLiteDatabase database;
	    private String revFile;

	    // Inserting sample record
	    ContentValues values;

	    public revDataLoader(Context context, SQLiteDatabase database,
	            String strFile) {
	        this.context = context;
	        this.database = database;
	        this.revFile = strFile;
	    }

	    public void loadData() throws IOException {
	        BufferedReader input = new BufferedReader(new InputStreamReader(context
	                .getAssets().open(revFile)));
	        String line;
	        while ((line = input.readLine()) != null) {
	            String[] RowData = line.split(",");
	            if (RowData.length == 3) {
	            	
	                values = new ContentValues();
	                values.put(Revival.REG_MODE, RowData[0]);
	                values.put(Revival.REG_INST, RowData[1]);
	                values.put(Revival.REG_FACTOR, RowData[2]);
	               
	                
	               
	               
	                
	                database.insert(Revival.TABLE_STOCK, null, values);
	            }

	        }
	      
	    }
	}


