package com.sreekanth.duelist;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class bmbillmast {

    // Database table
	
	
	
    public static final String TABLE_STOCK = "bmbillmast";
    public static final String ID = "_id";
    public static final String BM_POLNO = "bmpolno";
    public static final String BM_PREM = "bmprem";
  //  public static final String BM_FUP = "bmfup";
    public static final String BM_MOD = "bmmod";
    public static final String BM_APR = "apr";
    public static final String BM_MAY = "may";
    public static final String BM_JUN = "jun";
    public static final String BM_JUL = "jul";
    public static final String BM_AUG = "aug";
    public static final String BM_SEP = "sep";
    public static final String BM_OCT = "oct";
    public static final String BM_NOV = "nov";
    public static final String BM_DECE = "dece";
    public static final String BM_JAN = "jan";
    public static final String BM_FEB = "feb";
    public static final String BM_MAR = "mar";
    public static final String BM_NAME = "bmname";
    public static final String BM_ADD1 = "bmadd1";
    public static final String BM_ADD2 = "bmadd2";
    public static final String BM_ADD3 = "bmadd3";
    public static final String BM_DOC = "bmdoc";
    public static final String BM_PLAN = "bmplan";
    public static final String BM_TERM = "bmterm";
    public static final String BM_SA = "bmsa";
    public static final String BM_AGCODE = "bmagcode";
    public static final String BM_DEVOFF = "bmdevoff";
    public static final String BM_DUEFROM = "bmduefrom";
    public static final String BM_PIN = "bmpin";
    public static final String BM_DUETO = "bmdueto";
    public static final String BM_MOBILE = "bmmobile";
    public static final String BM_NOFDUES = "nfdues";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table " + TABLE_STOCK
            + "(" + ID + " integer primary key autoincrement, " + BM_POLNO
            + " integer, " + BM_DOC + " date, " + BM_PLAN
            + " integer, " + BM_TERM + " integer, " + BM_MOD 
            + " text, " + BM_SA + " integer, " + BM_PREM + " integer, "
            +  BM_AGCODE + " VARCHAR(9), " + BM_DEVOFF + " VARCHAR(9), " + BM_DUEFROM + " VARCHAR(10), " + BM_DUETO + " VARCHAR(10)," + BM_NAME + " VARCHAR(40), "
            + BM_ADD1 + " VARCHAR(40), " + BM_ADD2 + " VARCHAR(40), " + BM_ADD3 + " VARCHAR(40), " + BM_PIN + " text, "
             + BM_APR + " text, " + BM_MAY + " text, "
            + BM_JUN + " text, " + BM_JUL + " text, "
            + BM_AUG + " text, " + BM_SEP + " text, " + BM_OCT + " text, " + BM_NOV + " text, "
            + BM_DECE + " text, " + BM_JAN + " text," + BM_FEB + " text, " + BM_MAR + " text, " + BM_MOBILE + " text, " 
            + BM_NOFDUES + " text "
            
            
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
       
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
            int newVersion) {
        Log.w(bmbillmast.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
     //   onCreate(database);
    }

}   