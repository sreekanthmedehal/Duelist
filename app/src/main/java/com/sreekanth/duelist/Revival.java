package com.sreekanth.duelist;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
	
	
	
	public class Revival {

	    // Database table
		
		
		
	    public static final String TABLE_STOCK = "revival_factors";
	    public static final String ID = "_id";
	    public static final String REG_MODE = "mode";
	    public static final String REG_INST = "noofinst";
	    public static final String REG_FACTOR = "factor";
	   

	    // Database creation SQL statement
	    private static final String DATABASE_CREATE = "create table " + TABLE_STOCK
	            + "(" + ID + " integer primary key autoincrement, " + REG_MODE + " text, "
	            + REG_INST + " text, "
	            + REG_FACTOR + " text" 
	            
	            
	            	            + ");";

	    public static void onCreate(SQLiteDatabase database) {
	        database.execSQL(DATABASE_CREATE);
	       
	    }

	    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	            int newVersion) {
	        Log.w(Revival.class.getName(), "Upgrading database from version "
	                + oldVersion + " to " + newVersion
	                + ", which will destroy all old data");
	        database.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
	//        onCreate(database);
	    }
		
	}   

