package com.sreekanth.duelist;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
	
	
	
	public class PremCalc {

	    // Database table
		
		
		
	    public static final String TABLE_STOCK = "premcalc";
	   
	    public static final String REG_ID = "_id";
		 public static final String REG_NAME = "name";
		 public static final String REG_EMAIL = "email";
		 public static final String REG_MOBILE = "mobile";
		 public static final String REG_PLAN = "plan";
		 public static final String REG_AGE = "age";
		 public static final String REG_TERM = "term";
		 public static final String REG_SA = "sa";
	    
	 

	    // Database creation SQL statement
	    private static final String DATABASE_CREATE = "create table " + TABLE_STOCK
	            + "(" + REG_ID + " integer primary key autoincrement, " + REG_NAME
	            + " VARCHAR(60), " + REG_EMAIL + " varchar(60), " + REG_MOBILE
	            + " integer,  " + REG_PLAN + " integer, " + REG_AGE + " integer, "
	            + REG_TERM + " INTEGER, " + REG_SA + " INTEGER "
	            	            + ");";

	    public static void onCreate(SQLiteDatabase database) {
	        database.execSQL(DATABASE_CREATE);
	       
	    }

	    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	            int newVersion) {
	        Log.w(PremCalc.class.getName(), "Upgrading database from version "
	                + oldVersion + " to " + newVersion
	                + ", which will destroy all old data");
	        database.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
//	        onCreate(database);
	    }
		
	}   

