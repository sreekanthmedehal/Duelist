package com.sreekanth.duelist;



	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

	public class regDataLoader {

	    private Context context;
	    private SQLiteDatabase database;
	    private String regFile;

	    // Inserting sample record
	    ContentValues values;

	    public regDataLoader(Context context, SQLiteDatabase database,
	            String strFile) {
	        this.context = context;
	        this.database = database;
	        this.regFile = strFile;
	    }

	    public void loadData() throws IOException {
	        BufferedReader input = new BufferedReader(new InputStreamReader(context
	                .getAssets().open(regFile)));
	        String line;
	        while ((line = input.readLine()) != null) {
	            String[] RowData = line.split("#");
	            if (RowData.length == 15) {
	            	
	                values = new ContentValues();
	                values.put(Registration.REG_AGCODE, RowData[1]);
	                values.put(Registration.REG_COUPONNO, RowData[2]);
	                values.put(Registration.REG_REGFLAG, RowData[3]);
	                values.put(Registration.REG_MODEL, RowData[4]);
	                values.put(Registration.REG_SDATE, RowData[5]);
	                values.put(Registration.REG_EDATE, RowData[6]);
	                values.put(Registration.REG_ACTIVATED, RowData[7]);
	                values.put(Registration.REG_EMAIL, RowData[8].replace(",",""));
	                values.put(Registration.REG_MOBILE, RowData[9].replace(",",""));
	                values.put(Registration.REG_REGCODE, RowData[10]);
	                values.put(Registration.REG_PRODKEY, RowData[11]);
	                values.put(Registration.REG_NAME, RowData[12]);
	                values.put(Registration.REG_BRCODE, RowData[13]);
	                values.put(Registration.REG_DEALERCODE, RowData[14]);
	                values.put(Registration.REG_ADD, " " );
	               
	                
	                database.insert(Registration.TABLE_STOCK, null, values);
	            }

	        }
	      
	    }
	}


