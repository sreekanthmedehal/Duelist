package com.sreekanth.duelist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FEActivity extends ListActivity {
 
 private List<String> item = null;
 private List<String> path = null;
 private String root;
 private String backupdir;
 private TextView myPath;
 SharedPreferences preferences;
 SharedPreferences.Editor editor;
 File file;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fe);
        myPath = (TextView)findViewById(R.id.path);
        root = Environment.getExternalStorageDirectory().getPath(); 
       
      backupdir = Environment.getExternalStorageDirectory().getPath()+"/Download/ms/" + "";
    //    backupdir = Environment.getExternalStorageDirectory().getPath();
        File dir = new File(backupdir);
        if(dir.exists() && dir.isDirectory()) {
            // do something here
        getDir(backupdir);
        }else
        {
        	AlertDialog.Builder alert = new AlertDialog.Builder(FEActivity.this); 
      	  alert.setTitle("NO BACKUP DIRECTORY");

      	 
      	  

      	 // alert.setMessage("Coming Soon");
      	  alert.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
      	      @Override
      	      public void onClick(DialogInterface dialog, int id) {
      	      dialog.dismiss();
      	      }
      	  });
      	  alert.show();	
        }
    }
    
    private void getDir(String dirPath)
    {
     myPath.setText("Location: " + dirPath);
     item = new ArrayList<String>();
     path = new ArrayList<String>();
     File f = new File(dirPath);
     File[] files = f.listFiles();
     
     if(!dirPath.equals(root))
     {
      item.add(root);
      path.add(root);
      item.add("../");
      path.add(f.getParent()); 
     }
     if(files.length>0){
     for(int i=0; i < files.length; i++)
     {
      file = files[i];
      
      if(!file.isHidden() && file.canRead()){
       path.add(file.getPath());
          if(file.isDirectory()){
           item.add(file.getName() + "/");
          }else{
        	  
           item.add(file.getName() + "   " + file.length()+ "  bytes");
        	 
          }
      } 
     }
     }
     ArrayAdapter<String> fileList =
       new ArrayAdapter<String>(this, R.layout.frow, item);
     setListAdapter(fileList); 
    }

 @Override
 protected void onListItemClick(ListView l, View v, int position, long id) {
  // TODO Auto-generated method stub
  file = new File(path.get(position));
  
  if (file.isDirectory())
  {
   if(file.canRead()){
    getDir(path.get(position));
   }else{
    new AlertDialog.Builder(this)
     .setIcon(R.drawable.file_icon)
     .setTitle("[" + file.getName() + "] folder can't be read!")
     .setPositiveButton("OK", null).show(); 
   } 
  }else {
	  if (importDatabase()){
		  AlertDialog.Builder builder = new AlertDialog.Builder(FEActivity.this);
     builder.setIcon(R.drawable.file_icon)
     .setTitle("[" + file.getName() + "]")
     .setMessage("[" + "Import OVer,Press Back Button" + "]")
    // .setPositiveButton("OK", null).show();
   //	Toast.makeText(FEActivity.this, , Toast.LENGTH_LONG).show();
	  .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	  public void onClick(DialogInterface dialog, int id) {
	       finish();
	  }});
	 builder.show();
	     
	
   
	  }
	  
  }
 }
 
 
 
 public boolean importDatabase()
 {
	 boolean success=false;
	 FileReader	yourFile = null;
 	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(FEActivity.this);
	 SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
 	 String state = Environment.getExternalStorageState();
 	 if (!Environment.MEDIA_MOUNTED.equals(state)) { 
 		return false;
 	}
 	else {
 		//We use the Download directory for saving our .csv file.
	//    File exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
	   
 	
		
 //	 FileReader yourFile = file;
		try {
			
			yourFile = new FileReader(file.getPath());
	        success=true;
	        if (file.length() == 0){
	        	success=false;
	        	Toast.makeText(FEActivity.this, "FileSize is Zero", Toast.LENGTH_LONG).show();
	        }
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(FEActivity.this, "File Not Found", Toast.LENGTH_LONG).show();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	if (success) {
 		
 	 database.beginTransaction();
 	 BufferedReader buffer = new BufferedReader(yourFile);
 	 String line = "";
   ContentValues values;
   database.delete(bmpolmast.TABLE_STOCK, null, null);
   
   database.execSQL("delete from sqlite_sequence where name= '" + bmpolmast.TABLE_STOCK + "'");
	try {
		while ((line = buffer.readLine()) != null) {
		String[] str = line.split("#");
		if (str.length == 21) {
values = new ContentValues();
int SChCode = (int) Integer.parseInt(str[0]);

values.put(bmpolmast.PM_POLNO, SChCode);
values.put(bmpolmast.PM_NAME, str[1]);
values.put(bmpolmast.PM_FUP, str[2]);
values.put(bmpolmast.PM_MOD, str[3]);
values.put(bmpolmast.PM_DOC, str[4]);
values.put(bmpolmast.PM_STATUS, str[5]);
values.put(bmpolmast.PM_UNITS, str[6]);
values.put(bmpolmast.PM_PLAN, str[7]);
values.put(bmpolmast.PM_TERM, str[8]);
values.put(bmpolmast.PM_AGCODE, str[9]);
values.put(bmpolmast.PM_SA, str[10]);
values.put(bmpolmast.PM_DOB, str[11]);
values.put(bmpolmast.PM_PREM, str[12]);
values.put(bmpolmast.PM_ADD1, str[13]);
values.put(bmpolmast.PM_ADD2, str[14]);
values.put(bmpolmast.PM_ADD3, str[15]);
values.put(bmpolmast.PM_PIN, str[16]);
values.put(bmpolmast.PM_MOBILE, str[17]);
values.put(bmpolmast.PM_EMAIL, str[18]);

values.put(bmpolmast.PM_TYPE,  str[19]);
values.put(bmpolmast.PM_BRANCH, str[20]);



//values.put(bmpolmast.PM_DOCODE, str[7]);











//System.out.println(SChCode+ "    " + str[1]);
//rowsUpdated =   database.update(bmpolmast.TABLE_STOCK,  values, "policyno  = " + SChCode ,null);
 database.insert(bmpolmast.TABLE_STOCK, null, values);
//int   rowsUpdated = database.update("products_web", values, "TRIM(inenumber) = '" + SChCode + "'", null);
}
		}
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		buffer.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 		
 		
 	  database.setTransactionSuccessful();
		  database.endTransaction();
		  database.close();
			stockSQLHelper.close();
// 	Toast.makeText(cloud.this, String.valueOf(rowsUpdated), Toast.LENGTH_LONG).show();
 	}
		return true;	
 	
 }
}