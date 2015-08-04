package com.sreekanth.duelist;






import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class NBData {
    // Database table
    public static final String TABLE_STOCK = "nbcr";
    public static final String ID = "_id";
    public static final String NB_POLNO = "nbpolno";
    public static final String NB_COMMDT = "nbcommdt";
    public static final String NB_SA = "nbsa";
    public static final String NB_PREM = "nbprem";
    public static final String NB_PLAN = "nbplan";
    public static final String NB_TERM = "nbterm";
    public static final String NB_MODE = "nbmode";
    public static final String NB_NAME = "nbname";
    public static final String NB_AGCODE = "nbagcode";
    public static final String NB_DOCODE = "nbdocode";
  	 
   
   

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table " + TABLE_STOCK
            + "(" + ID + " integer primary key autoincrement, " + NB_POLNO + " INTEGER, " + NB_COMMDT + " date, "
    		+ NB_SA + " integer, " + NB_PREM + " integer, " + NB_PLAN + " integer, " + NB_TERM + " integer, " 
    		+ NB_AGCODE + " TEXT, " + NB_DOCODE + " VARCHAR(11), "
    		+ NB_MODE + " integer, " + NB_NAME + " VARCHAR(30)"   + ");";
    
    
    
    	
    	

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
       
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
            int newVersion) {
        Log.w(bmpolmast.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
     //   onCreate(database);
    }

}   


