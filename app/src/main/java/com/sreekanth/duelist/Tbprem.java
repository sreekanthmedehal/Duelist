package com.sreekanth.duelist;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
	
	
	
	public class Tbprem {

	    // Database table
		
		
		
	    public static final String TABLE_STOCK = "tbprem";
	    public static final String ID = "_id";
	    public static final String REG_PLAN = "plan";
	    public static final String REG_AGE = "age";
	    public static final String REG_TERM = "term";
	    public static final String REG_PREM = "prem";
	    public static final String REG_PTERM = "pterm";
	    public static final String REG_PPTERM = "ppterm";

	    // Database creation SQL statement
	    private static final String DATABASE_CREATE = "create table " + TABLE_STOCK
	            + "(" + ID + " integer primary key autoincrement, " + REG_PLAN
	            + " integer, " + REG_AGE + " integer, " + REG_TERM
	            + " integer,  " + REG_PREM + " integer, " + REG_PPTERM + " integer "
	            
	            	            + ");";

	    public static void onCreate(SQLiteDatabase database) {
	        database.execSQL(DATABASE_CREATE);
	       
	    }

	    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	            int newVersion) {
	        Log.w(Tbprem.class.getName(), "Upgrading database from version "
	                + oldVersion + " to " + newVersion
	                + ", which will destroy all old data");
	        database.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
	     //   onCreate(database);
	    }
		
	}   

