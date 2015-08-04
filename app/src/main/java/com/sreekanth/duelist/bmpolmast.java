package com.sreekanth.duelist;




	import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class bmpolmast {
	    // Database table
	    public static final String TABLE_STOCK = "bmpolmast";
	    public static final String ID = "_id";
	    public static final String PM_TYPE = "type";
	    public static final String PM_BRANCH = "branch";
	    public static final String PM_POLNO = "policyno";
	    public static final String PM_UNITS = "units";
	    public static final String PM_PLAN = "plan";
	    public static final String PM_AGCODE = "agcode";
	    public static final String PM_DOCODE = "docode";
	    public static final String PM_SA = "sa";
	    public static final String PM_PREM = "prem";
	    public static final String PM_TERM = "term";
	    public static final String PM_FUP = "fup";
	    public static final String PM_NAME = "polname";
	    public static final String PM_STATUS = "status";
	    public static final String PM_DOC = "doc";
	    public static final String PM_MOD = "mode";
	    public static final String PM_ADD1 = "add1";
	    public static final String PM_ADD2 = "add2";
	    public static final String PM_ADD3 = "add3";
	    public static final String PM_PIN = "pin";
	    public static final String PM_DOB = "dob";
	    public static final String PM_MOBILE = "mobile";
	    public static final String PM_EMAIL = "email";
	    public static final String PM_LOANDT = "lndate";
	    public static final String PM_LOANAMT = "lnamt";
	    public static final String PM_MEMO = "memo";
	    public static final String PM_FAV = "favourtie";
	    public static final String COLUMN_TIME_STAMP = "CreatedOn";
	  	 
	   
	   

	    // Database creation SQL statement
	    private static final String DATABASE_CREATE = "create table " + TABLE_STOCK
	            + "(" + ID + " integer primary key autoincrement, " + PM_TYPE + " text default null, " + PM_BRANCH + " text default null, "
	    		+ PM_POLNO + " integer, " + PM_UNITS + " integer, " + PM_PLAN + " integer, " + PM_AGCODE + " TEXT, " 
	    		+ PM_DOCODE + " VARCHAR(11), " + PM_SA + " integer, " + PM_PREM + " integer, " + PM_TERM + " INTEGER, " 
	    		+ PM_FUP + " text, " + PM_NAME + " TEXT, " +  PM_STATUS + " TEXT, " + PM_DOC + " date, " 
	    		+ PM_MOD + " text, " + PM_ADD1 + " varchar(40), " + PM_ADD2 + " varchar(40), "
	            + PM_ADD3 + " varchar(40), " + PM_PIN + " VARCHAR(6), " + PM_DOB + " VARCHAR(10), " 
	            + PM_MOBILE + " varchar(10), " + PM_EMAIL + " varchar(50), "
	            + PM_LOANDT + " date, " + PM_LOANAMT + " integer, " + PM_MEMO + " varchar(60), " + PM_FAV + " VARCHAR(1), "
	            + COLUMN_TIME_STAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " + ");";
	    public static void onCreate(SQLiteDatabase database) {
	        database.execSQL(DATABASE_CREATE);
	       
	    }

	    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	            int newVersion) {
	        Log.w(bmpolmast.class.getName(), "Upgrading database from version "
	                + oldVersion + " to " + newVersion
	                + ", which will destroy all old data");
	        database.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
	    //    onCreate(database);
	    }

	}   
