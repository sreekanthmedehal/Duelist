package com.sreekanth.duelist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class StockDataLoader {

    private Context context;
    private SQLiteDatabase database;
    private String dataFile;

    // Inserting sample record
    ContentValues values;

    public StockDataLoader(Context context, SQLiteDatabase database,
            String strFile) {
        this.context = context;
        this.database = database;
        this.dataFile = strFile;
    }

    public void loadData() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(context
                .getAssets().open(dataFile)));
        String line;
        while ((line = input.readLine()) != null) {
            String[] RowData = line.split("#");
            if (RowData.length == 28) {
       
                values = new ContentValues();
            
               
                values.put(bmbillmast.BM_POLNO, RowData[0]);
                values.put(bmbillmast.BM_DOC, RowData[1]);
                values.put(bmbillmast.BM_PLAN, RowData[2]);
                values.put(bmbillmast.BM_TERM, RowData[3]);
                values.put(bmbillmast.BM_MOD, RowData[4]);
                values.put(bmbillmast.BM_SA, RowData[5]);
                values.put(bmbillmast.BM_PREM, RowData[6]);
                values.put(bmbillmast.BM_AGCODE, RowData[7]);
                values.put(bmbillmast.BM_DEVOFF, RowData[8]);
                values.put(bmbillmast.BM_DUEFROM, RowData[9]);
                values.put(bmbillmast.BM_DUETO, RowData[10]);
                values.put(bmbillmast.BM_NAME, RowData[11]);
                values.put(bmbillmast.BM_ADD1, RowData[12]);
                values.put(bmbillmast.BM_ADD2, RowData[13]);
                values.put(bmbillmast.BM_ADD3, RowData[14]);
                values.put(bmbillmast.BM_PIN, RowData[15]);
                values.put(bmbillmast.BM_APR, RowData[16]);
                values.put(bmbillmast.BM_MAY, RowData[17]);
                values.put(bmbillmast.BM_JUN, RowData[18]);
                values.put(bmbillmast.BM_JUL, RowData[19]);
                values.put(bmbillmast.BM_AUG, RowData[20]);
                values.put(bmbillmast.BM_SEP, RowData[21]);
                values.put(bmbillmast.BM_OCT, RowData[22]);
                values.put(bmbillmast.BM_NOV, RowData[23]);
                values.put(bmbillmast.BM_DECE, RowData[24]);
                values.put(bmbillmast.BM_JAN, RowData[25]);
                values.put(bmbillmast.BM_FEB, RowData[26]);
                values.put(bmbillmast.BM_MAR, RowData[27]);
                
                
                
                
                
                database.insert(bmbillmast.TABLE_STOCK, null, values);
            }
           

        } 
 
    }
}
