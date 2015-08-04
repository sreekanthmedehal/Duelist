package com.sreekanth.duelist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

public class DataViewerSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sri";
    private static final int DATABASE_VERSION = 13;
    private String dataFile;
    private Context context;
    private String polFile;
    private String agFile;
    private String regFile;
    private String tbFile;
    private String revFile;

    public DataViewerSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dataFile = context.getString(R.string.dataFile);
        polFile = context.getString(R.string.polFile);
        agFile = context.getString(R.string.agFile);
        regFile = context.getString(R.string.regFile);
        tbFile = context.getString(R.string.tbFile);
        revFile = context.getString(R.string.revFile);
    }

  
	@Override
    public void onCreate(SQLiteDatabase database) {
        bmbillmast.onCreate(database);
        bmpolmast.onCreate(database);
        AgentData.onCreate(database);
        NBData.onCreate(database);
        Registration.onCreate(database);
        Tbprem.onCreate(database);
        PremCalc.onCreate(database);
        Revival.onCreate(database);
        // Populating the stock table
        PmDataLoader pmLoader = new PmDataLoader(context, database, polFile);
           try {
            pmLoader.loadData();
     //       database.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         StockDataLoader stockLoader = new StockDataLoader(context, database, dataFile);
        try {
            stockLoader.loadData();
   //         database.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AgDataLoader agLoader = new AgDataLoader(context, database, agFile);
        try {
            agLoader.loadData();
   //         database.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        regDataLoader regLoader = new regDataLoader(context, database, regFile);
        try{
        	regLoader.loadData();
        } catch(IOException e){
        	e.printStackTrace();
        }
        tbDataLoader tbLoader = new tbDataLoader(context, database, tbFile);
        try{
        	tbLoader.loadData();
        } catch(IOException e){
        	e.printStackTrace();
        }
        revDataLoader revLoader = new revDataLoader(context, database, revFile);
        try{
        	revLoader.loadData();
        } catch(IOException e){
        	e.printStackTrace();
        } 
    }

    @Override																																																																																																																																																																																																			
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
            int newVersion) {
    
    	
    	if (exportDatabase(database)){
	  			Toast.makeText(context, "Export OVer,press Back Button", Toast.LENGTH_LONG).show();
	  		};
	
      bmbillmast.onUpgrade(database, oldVersion, newVersion);
      bmpolmast.onUpgrade(database, oldVersion, newVersion);
       AgentData.onUpgrade(database, oldVersion, newVersion);
       NBData.onUpgrade(database, oldVersion, newVersion);
       Registration.onUpgrade(database, oldVersion, newVersion);																																										
      Tbprem.onUpgrade(database, oldVersion, newVersion);
       PremCalc.onUpgrade(database, oldVersion, newVersion);
    Revival.onUpgrade(database, oldVersion, newVersion);
    onCreate(database);
    }
    public boolean exportDatabase(SQLiteDatabase database) {
       	/**First of all we check if the external storage of the device is available for writing.
       	 * Remember that the external storage is not necessarily the sd card. Very often it is
       	 * the device storage.
       	 */
       	String state = Environment.getExternalStorageState();
       	if (!Environment.MEDIA_MOUNTED.equals(state)) { 
       	   
       		return false;
       	}
       	else {
       		//We use the Download directory for saving our .csv file.
       		
       	    File exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/ms/");      
       	    if (!exportDir.exists()) 
       	    {
       	        exportDir.mkdirs();
       	    }
       	    
       	    File file;
       	    PrintWriter printWriter = null;
       	    try 
       	    {
       	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
       	    	Date now = new Date();
       	    	String fileName = "Policy_"+formatter.format(now) + ".csv";
       	    	
       	    	file = new File(exportDir, fileName );
       	        file.createNewFile();                
       	        printWriter = new PrintWriter(new FileWriter(file));
       	                   
       	        /**This is our database connector class that reads the data from the database.
       	         * The code of this class is omitted for brevity.
       	         */
       	        /*
    				 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(context);*/
    	     	 //    SQLiteDatabase database1 = this.getReadableDatabase();
       	    	
       	    	/**Let's read the first table of the database.
       	    	 * getFirstTable() is a method in our DBCOurDatabaseConnector class which retrieves a Cursor
       	    	 * containing all records of the table (all fields).
       	    	 * The code of this class is omitted for brevity.
       	    	 */
       	    	
       	    	String sql = "";
        		sql = "SELECT * FROM  bmpolmast";
        		Cursor curCSV = database.rawQuery(sql, null);
        		
       	    	//Write the name of the table and the name of the columns (comma separated values) in the .csv file.
       	//    	printWriter.println("FIRST TABLE OF THE DATABASE");
       	//    	printWriter.println("DATE,ITEM,AMOUNT,CURRENCY");
        		for (curCSV.moveToFirst(); !curCSV.isAfterLast(); curCSV.moveToNext()) {
    	   //  	Long polno = curCSV.getLong(curCSV.getColumnIndex("policyno"));
    	   //   Long mobileno = curCSV.getLong(curCSV.getColumnIndex("mobile"));
       	   //  	Double amount = curCSV.getDouble(curCSV.getColumnIndex("amount"));

    		   String PolicyNo = curCSV.getString(curCSV.getColumnIndexOrThrow("policyno"));
    		   String polname = curCSV.getString(curCSV.getColumnIndex("polname"));
    		   String FUP = curCSV.getString(curCSV.getColumnIndexOrThrow("fup"));
    		   String Mode = curCSV.getString(curCSV.getColumnIndexOrThrow("mode"));
    		   String DOC = curCSV.getString(curCSV.getColumnIndexOrThrow("doc"));
    		   String Status = curCSV.getString(curCSV.getColumnIndexOrThrow("status"));
    		   String Units = curCSV.getString(curCSV.getColumnIndexOrThrow("units"));
    		   String PlanNo = curCSV.getString(curCSV.getColumnIndexOrThrow("plan"));
    		   String TermNo = curCSV.getString(curCSV.getColumnIndexOrThrow("term"));
    		   String AgCode = curCSV.getString(curCSV.getColumnIndexOrThrow("agcode"));
    		   String SA = curCSV.getString(curCSV.getColumnIndexOrThrow("sa"));
    		   String DOB = curCSV.getString(curCSV.getColumnIndexOrThrow("dob"));
    		   String Prem = curCSV.getString(curCSV.getColumnIndexOrThrow("prem"));
    		   String Add1 = 
    				   curCSV.getString(curCSV.getColumnIndexOrThrow("add1"));
    		   String Add2 = 
    				   curCSV.getString(curCSV.getColumnIndexOrThrow("add2"));
    		   String Add3 = 
    				   curCSV.getString(curCSV.getColumnIndexOrThrow("add3"));
    		   String Pin = curCSV.getString(curCSV.getColumnIndexOrThrow("pin"));
    		   String Mobile = curCSV.getString(curCSV.getColumnIndexOrThrow("mobile"));
    		   String Email = curCSV.getString(curCSV.getColumnIndexOrThrow("email"));
    		   String Type = curCSV.getString(curCSV.getColumnIndexOrThrow("type"));
    		   String Branch = curCSV.getString(curCSV.getColumnIndexOrThrow("branch"));
       	        	/**Create the line to write in the .csv file. 
       	        	 * We need a String where values are comma separated.
       	        	 * The field date (Long) is formatted in a readable text. The amount field
       	        	 * is converted into String.
       	        	 */
       	       String record = PolicyNo + "#" +
       	        			polname + "#" +
       	        			FUP + "#" +
       	        			Mode + "#" +
       	        			DOC + "#" +
       	        			Status + "#" +
       	        			Units + "#" +
       	        			PlanNo + "#" +
       	        			TermNo + "#" +
       	        			AgCode + "#" +
       	        			SA + "#" +
       	        			DOB + "#" +
       	        			Prem + "#" +
       	        	        Add1 + "#" +
       	        	        Add2 + "#" +
       	        	        Add3 + "#" +
       	        	        Pin + "#" +
       	        	        Mobile + "#" +
       	        	        Email + "#" +
       	        	        Type   + "#" +
       	        	        Branch;
       	        	        
       	        	       
       	 //      Log.d("Output",record);
       			printWriter.println(record); //write the record in the .csv file
       			}
       			curCSV.close();
       		//	database.close();
       			//stockSQLHelper.close();
       		//	curCSV.close();
       	        }
       	   catch(Exception exc) {
       		   //if there are any exceptions, return false
       		Toast.makeText(context, exc.toString(), Toast.LENGTH_LONG).show();
       		   return false;
       	       }
       	   finally {
       		   if(printWriter != null) printWriter.close();
       	       }	
       	   //If there are no errors, return true.
       	   return true;
       	       }
               }
	

}
