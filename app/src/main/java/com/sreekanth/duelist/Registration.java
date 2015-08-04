package com.sreekanth.duelist;




	import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
	
	
	
	public class Registration {

	    // Database table
		
		
		
	    public static final String TABLE_STOCK = "registration";
	    public static final String ID = "_id";
	    public static final String REG_AGCODE = "regagcode";
	    public static final String REG_COUPONNO = "regcouponno";
	    public static final String REG_REGFLAG = "regflag";
	    public static final String REG_MODEL = "regmodel";  
	    public static final String REG_SDATE = "startdate";
	    public static final String REG_EDATE = "enddate";
	    public static final String REG_ACTIVATED = "regactivated";
	    public static final String REG_EMAIL  = "regemail";
	    public static final String REG_MOBILE = "regmobile";
	    public static final String REG_REGCODE = "regcode";
	    public static final String REG_PRODKEY = "prodkey";
	    public static final String REG_NAME = "agname";
	    public static final String REG_BRCODE = "brcode";
	    public static final String REG_DEALERCODE = "dealercode";
	    public static final String REG_ADD = "address";
	 

	    // Database creation SQL statement
	    private static final String DATABASE_CREATE = "create table " + TABLE_STOCK
	            + "(" + ID + " integer primary key autoincrement, " + REG_AGCODE
	            + " text, " + REG_COUPONNO + " integer, " + REG_REGFLAG
	            + " text,  " + REG_MODEL + " text, " + REG_SDATE + " date, " + REG_EDATE + " date, "
	            +  REG_ACTIVATED + " VARCHAR(1), " + REG_MOBILE + " varchar(10), " + REG_EMAIL + " VARCHAR(50), "
	            +  REG_REGCODE + " VARCHAR(20), " + REG_PRODKEY + " VARCHAR(25), " + REG_NAME + " VARCHAR(30), "
	            +  REG_BRCODE + " VARCHAR(4), " + REG_DEALERCODE + " VARCHAR(10), " + REG_ADD + " VARCHAR(60) "
	            	            + ");";

	    public static void onCreate(SQLiteDatabase database) {
	        database.execSQL(DATABASE_CREATE);
	       
	    }

	    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	            int newVersion) {
	        Log.w(Registration.class.getName(), "Upgrading database from version "
	                + oldVersion + " to " + newVersion
	                + ", which will destroy all old data");
	        database.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
	       // onCreate(database);
	    }
		
	}   

