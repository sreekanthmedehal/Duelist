package com.sreekanth.duelist;



	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

	public class PmDataLoader {

	    private Context context;
	    private SQLiteDatabase database;
	    private String polFile;

	    // Inserting sample record
	    ContentValues values;

	    public PmDataLoader(Context context, SQLiteDatabase database,
	            String strFile) {
	        this.context = context;
	        this.database = database;
	        this.polFile = strFile;
	    }

	    public void loadData() throws IOException {
	        BufferedReader input = new BufferedReader(new InputStreamReader(context
	                .getAssets().open(polFile)));
	        String line;
	        while ((line = input.readLine()) != null) {
	            String[] RowData = line.split("#");
	            if (RowData.length == 24) {
	       
	                values = new ContentValues();
	                values.put(bmpolmast.PM_TYPE, RowData[1]);
	                values.put(bmpolmast.PM_BRANCH, RowData[2]);
	                values.put(bmpolmast.PM_POLNO, RowData[3]);
	                values.put(bmpolmast.PM_UNITS, RowData[4]);
	                values.put(bmpolmast.PM_PLAN, RowData[5]);
	                values.put(bmpolmast.PM_AGCODE, RowData[6]);
	                values.put(bmpolmast.PM_DOCODE, RowData[7]);
	                values.put(bmpolmast.PM_SA, RowData[8].replace(",",""));
	                values.put(bmpolmast.PM_PREM, RowData[9].replace(",",""));
	                values.put(bmpolmast.PM_TERM, RowData[10]);
	                values.put(bmpolmast.PM_FUP, RowData[11]);
	                values.put(bmpolmast.PM_NAME, RowData[12]);
	                values.put(bmpolmast.PM_STATUS, RowData[13]);
	                values.put(bmpolmast.PM_DOC, RowData[14]);
	                values.put(bmpolmast.PM_MOD, RowData[15]);
	                values.put(bmpolmast.PM_ADD1, RowData[16]);
	                values.put(bmpolmast.PM_ADD2, RowData[17]);
	                values.put(bmpolmast.PM_ADD3, RowData[18]);
	                values.put(bmpolmast.PM_PIN, RowData[19]);
	                values.put(bmpolmast.PM_DOB, RowData[20]);
	                values.put(bmpolmast.PM_LOANDT,RowData[21]);
	                values.put(bmpolmast.PM_LOANAMT,RowData[22]);
	                values.put(bmpolmast.PM_MEMO, RowData[23]);
	                
	                
	                database.insert(bmpolmast.TABLE_STOCK, null, values);
	            }

	        }
	      
	    }
	}


