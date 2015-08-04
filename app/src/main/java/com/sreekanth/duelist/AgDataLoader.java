package com.sreekanth.duelist;





	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AgDataLoader {

	    private Context context;
	    private SQLiteDatabase database;
	    private String agFile;

	    // Inserting sample record
	    ContentValues values;

	    public AgDataLoader(Context context, SQLiteDatabase database,
	            String strFile) {
	        this.context = context;
	        this.database = database;
	        this.agFile = strFile;
	    }

	    public void loadData() throws IOException {
	        BufferedReader input = new BufferedReader(new InputStreamReader(context
	                .getAssets().open(agFile)));
	        String line;
	        while ((line = input.readLine()) != null) {
	            String[] RowData = line.split("#");
	            if (RowData.length == 27) {
	       
	                values = new ContentValues();
	                values.put(AgentData.AG_CODE, RowData[1]);
	                values.put(AgentData.AG_NAME, RowData[2]);
	                values.put(AgentData.AG_ELGCODE, RowData[3]);
	                values.put(AgentData.AG_DOCODE, RowData[4]);
	                values.put(AgentData.AG_SEX, RowData[5]);
	                values.put(AgentData.AG_CLUB, RowData[6]);
	                values.put(AgentData.AG_PAN, RowData[7]);
	                values.put(AgentData.AG_DOA, RowData[8]);
	                values.put(AgentData.AG_DOB, RowData[9]);
	                values.put(AgentData.AG_LICENNO, RowData[10]);
	                values.put(AgentData.AG_LICEXPDATE, RowData[11]);
	                values.put(AgentData.AG_ACCOUNTNO, RowData[12]);
	                values.put(AgentData.AG_BANKNAME, RowData[13]);
	                values.put(AgentData.AG_DOE, RowData[14]);
	                values.put(AgentData.AG_DOR, RowData[15]);
	                values.put(AgentData.AG_ADR1, RowData[16]);
	                values.put(AgentData.AG_ADR2, RowData[17]);
	                values.put(AgentData.AG_ADR3, RowData[18]);
	                values.put(AgentData.AG_PIN, RowData[19]);
	                values.put(AgentData.AG_TEL, RowData[20]);
	                values.put(AgentData.AG_ENTRYMD, RowData[21]);
	                values.put(AgentData.AG_EXITMD, RowData[22]);
	                values.put(AgentData.AG_NOMINEE, RowData[23]);
	                values.put(AgentData.AG_NOMINEEREL, RowData[24]);
	                values.put(AgentData.AG_CLIACD, RowData[25]);
	                values.put(AgentData.AG_EMAIL, RowData[26]);
	                database.insert(AgentData.TABLE_STOCK, null, values);
	            }

	        }
	    
	    }
	   
	}



