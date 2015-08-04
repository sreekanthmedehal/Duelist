package com.sreekanth.duelist;




	import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

    public class AgentData {
	    // Database table
	    public static final String TABLE_STOCK = "agentdata";
	    public static final String ID = "_id";
	    public static final String AG_CODE = "agcode";
	    public static final String AG_NAME = "agname";
	    public static final String AG_ELGCODE = "elgcode";
	    public static final String AG_DOCODE = "docode";
	    public static final String AG_SEX = "sex";
	    public static final String AG_CLUB = "agclub";
	    public static final String AG_PAN = "agpan";
	    public static final String AG_DOA = "agdoa";
	    public static final String AG_DOB = "agdob";
	    public static final String AG_LICENNO = "licenno";
	    public static final String AG_LICEXPDATE = "licexpdate";
	    public static final String AG_ACCOUNTNO = "accountno";
	    public static final String AG_BANKNAME = "bankname";
	    public static final String AG_DOE = "agdoe";
	    public static final String AG_DOR = "agdor";
	    public static final String AG_ADR1 = "adr1";
	    public static final String AG_ADR2 = "adr2";
	    public static final String AG_ADR3 = "adr3";
	    public static final String AG_PIN = "pin";
	    public static final String AG_TEL = "tel";
	    public static final String AG_ENTRYMD = "entrymd";
	    public static final String AG_EXITMD = "exitmd";
	    public static final String AG_NOMINEE = "nominee";
	    public static final String AG_NOMINEEREL = "nomineerel";
	    public static final String AG_CLIACD = "cliacd";
	    public static final String AG_EMAIL = "email";
	
	  
	  	 
	   
	   

	    // Database creation SQL statement
	    private static final String DATABASE_CREATE = "create table " + TABLE_STOCK
	            + "(" + ID + " integer primary key autoincrement, " + AG_CODE + " text default null, " + AG_NAME + " text default null, "
	    		+ AG_ELGCODE + " integer, " + AG_DOCODE + " text, " + AG_SEX + " text, " + AG_CLUB + " TEXT, " 
	    		+ AG_PAN + " VARCHAR(11), " + AG_DOA + " VARCHAR(11), " + AG_DOB + " VARCHAR(10), " + AG_LICENNO + " text, " 
	    		+ AG_LICEXPDATE + " text, " + AG_ACCOUNTNO + " TEXT, " +  AG_BANKNAME + " TEXT, " + AG_DOE + " TEXT, " 
	    		+ AG_DOR + " text, " + AG_ADR1 + " varchar(40), " + AG_ADR2 + " varchar(40), "
	            + AG_ADR3 + " varchar(40), " + AG_PIN + " VARCHAR(6), " + AG_TEL + " VARCHAR(10), "
	    		 + AG_ENTRYMD + " text, " + AG_EXITMD + " VARCHAR(4), " + AG_NOMINEE + " VARCHAR(15), "
	             + AG_NOMINEEREL + " varchar(4), " + AG_CLIACD + " VARCHAR(10), " + AG_EMAIL + " VARCHAR(40) "	            
	            + ");";
	    
	    
	    
	    	
	    	

	    public static void onCreate(SQLiteDatabase database) {
	        database.execSQL(DATABASE_CREATE);
	       
	    }

	    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	            int newVersion) {
	        Log.w(AgentData.class.getName(), "Upgrading database from version "
	                + oldVersion + " to " + newVersion
	                + ", which will destroy all old data");
	        database.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK);
	       // onCreate(database);
	    }

	}   


