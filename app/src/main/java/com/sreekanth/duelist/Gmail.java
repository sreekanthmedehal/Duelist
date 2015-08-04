package com.sreekanth.duelist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Gmail extends ListActivity{
   
    private List<String> item = null;
    private List<String> path = null;
    private String root;
    private String backupdir;
    private TextView myPath;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    File file;
    String strFile, address, subject, emailtext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_fe);
	        myPath = (TextView)findViewById(R.id.path);
	        root = Environment.getExternalStorageDirectory().getPath(); 
	        backupdir = Environment.getExternalStorageDirectory().getPath()+"/download/ms/" + "";
	        File dir = new File(backupdir);
	        if(dir.exists() && dir.isDirectory()) {
	            // do something here
	        getDir(backupdir);
	        }else
	        {
	        	AlertDialog.Builder alert = new AlertDialog.Builder(Gmail.this); 
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


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gmail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
	 sharing();
	
	     
	
   
	  }
	  
  }
	
	
	
private void sharing(){
Intent sharingIntent = new Intent(Intent.ACTION_SEND);
sharingIntent.setType("text/html");
//	sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
sharingIntent.putExtra(Intent.EXTRA_STREAM,
		Uri.parse("file://" + file.getPath().toString()));
//	sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM, value)
startActivity(Intent.createChooser(sharingIntent,"Share using"));	
//88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888		
//AlertDialog.Builder builder = new AlertDialog.Builder(Gmail.this);
//builder.setIcon(R.drawable.file_icon)
//.setTitle("[" + file.getName() + "]")
//.setMessage("[" + "Job OVer,Press Back Button" + "]")
//// .setPositiveButton("OK", null).show();
////	Toast.makeText(FEActivity.this, , Toast.LENGTH_LONG).show();
// .setPositiveButton("OK", new DialogInterface.OnClickListener() {
// public void onClick(DialogInterface dialog, int id) {
//      finish();
// }});
//builder.show();	
}
}