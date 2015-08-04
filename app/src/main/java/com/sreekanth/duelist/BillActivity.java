package com.sreekanth.duelist;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class BillActivity extends Activity {
//  DBHelper dbhelper;
	
  SimpleCursorAdapter adapter;
  private String extraData;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		 this.requestWindowFeature(Window.FEATURE_ACTION_BAR);
	        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
	            WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	        android.view.WindowManager.LayoutParams params = this.getWindow().getAttributes(); 
	        params.alpha = 1.0f;
	        params.dimAmount = 0.5f;
	        this.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params); 

	        // This sets the window size, while working around the IllegalStateException thrown by ActionBarView
	        this.getWindow().setLayout(400,800);
			  ActionBar actionBar = getActionBar();
	    	  actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_bill);
	
     
	   /** Called when the activity is first created. */
		 Intent sender=getIntent();
		 extraData=sender.getExtras().getString("ComingFrom");
 


        // if you use siplecursoradapter then you should have _id as one of column name and its values should be integer in your db.
        // so "_id", "columnName1", "columnName2" are column names from your db.
        String[] from = new String[] { "_id", "bmpolno", "bmdoc", "bmprem" , "bmdueto" , "bmmod" , "bmname" , "bmadd1" , "bmadd2" , "bmadd3" , "bmduefrom", "nfdues"};
        int[] to = new int[] { R.id.TextView1, R.id.TextView2,R.id.TextView16, R.id.TextView3, R.id.TextView4, R.id.TextView5, R.id.TextView6, R.id.TextView7, R.id.TextView8, R.id.TextView9, R.id.TextView4a, R.id.TextView19};

      
      
        Cursor b = getDatab(extraData);
     
         adapter = new SimpleCursorAdapter(
         getApplicationContext(), R.layout.list, b, from, to,0);

        ListView list = (ListView) findViewById(R.id.ListView2);

        list.setAdapter(adapter);
        registerForContextMenu(list);
        
        
        
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
                public Cursor runQuery(CharSequence constraint) {
            	 
                return getDatab(constraint.toString());
            }
			
        }); 
      
    }
	  @Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.billactivity_menu, menu);
			

		        return super.onCreateOptionsMenu(menu);

		}
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        // Respond to the action bar's Up/Home button
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(this);
	            return true;
	        case com.sreekanth.duelist.R.id.menu_exitb:
	        finish();          
            moveTaskToBack(true);
	            return true;
	        case com.sreekanth.duelist.R.id.menu_sortpb:
	        	String s = this.extraData + "bmprem";
	        	
		       adapter.getFilter().filter(s.toString());
		       close();
		            return true;   
	        case com.sreekanth.duelist.R.id.menu_sortb:
	        	String s1 = extraData + "bmname";
	        	
		       adapter.getFilter().filter(s1.toString());
		       close();
		            return true;
	        case com.sreekanth.duelist.R.id.menu_sortnd:
	        	String s2 = extraData + "nfdues";
	        	
		       adapter.getFilter().filter(s2.toString());
		       close();
		            return true;     
	            
	        }
	        return super.onOptionsItemSelected(item);
	    }
	 @Override
	    public void onCreateContextMenu(ContextMenu menu, View v,
	                                    ContextMenuInfo menuInfo) {
	        super.onCreateContextMenu(menu, v, menuInfo);
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.sms_menu, menu);
	    }
	 

	    @Override
	    public boolean onContextItemSelected(MenuItem item) {
	        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	        switch (item.getItemId()) {
	            case R.id.Task_sms:
	          //      editNote(info.id);
	            	 Toast.makeText(getApplicationContext(),
		    				   "Id No: " + info.id + "\n",
		                               Toast.LENGTH_SHORT).show();
	            		
	    	//		 Intent intent=new Intent(this,SendSMSActivity.class);
	    	//	intent.putExtra("ID", ""+info.id);
	    	//	startActivity(intent); 
	            	    Cursor ddetails = getDatabs(info.id);
	                    Cursor agdetails = getDataag();
	                    
	                    String smspolno =  ddetails.getString(ddetails.getColumnIndexOrThrow("bmpolno"));
	                    String smspolname =  ddetails.getString(ddetails.getColumnIndexOrThrow("bmname"));
	                    String smsmobile =  ddetails.getString(ddetails.getColumnIndexOrThrow("bmmobile"));
	                    String smsfup = ddetails.getString(ddetails.getColumnIndexOrThrow("bmduefrom"));
	                    String smsprem = ddetails.getString(ddetails.getColumnIndexOrThrow("bmprem"));
	                    String smsagname = agdetails.getString(agdetails.getColumnIndexOrThrow("agname"));
	             //       String smsagtel = agdetails.getString(agdetails.getColumnIndexOrThrow("tel"));
	                    String smsbody = "Policy No : " + smspolno + "\n" + smspolname + "\n"  + "Due : " + smsfup + "\n" + "Instl Prem : " + smsprem
	                  		  + "\n" + "Please Pay Premiums at LIC Office" + "\n" + smsagname + "\n";
	               
	        		try {
	        			 Intent smsintent = new Intent(BillActivity.this,SMS.class);
    	            	 smsintent.putExtra("smsmobno", smsmobile);
    	            	 smsintent.putExtra("smsbody",smsbody);
    	            	 startActivity(smsintent);        
					     /*Intent sendIntent = new Intent(Intent.ACTION_VIEW);
					     sendIntent.putExtra("address",smsmobile);
					     sendIntent.putExtra("sms_body", smsbody); 
					     sendIntent.setType("vnd.android-dir/mms-sms");
					     startActivity(sendIntent);*/

					} catch (Exception e) {
						Toast.makeText(getApplicationContext(),
							"SMS failed, please try again later!",
							Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
				  
			
				
	        return true;
	        //   case R.id.Delete_Task:
	        //       deleteNote(info.id);
	            	
	          //  	 Toast.makeText(getApplicationContext(),
		    //				   "IdNo: " + info.id + "\n",
		     //                          Toast.LENGTH_LONG).show();
	            	
	          //      return true;
	            default:
	                return super.onContextItemSelected(item);
	        }     }

	
	 public Cursor getDatab(String getmonth) {
	    	DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	        SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
	     
	        String sql = "";
	        String sortParam = "Nothing";
	        if (getmonth.length() > 4){
	         sortParam = getmonth.substring(getmonth.length() -6,getmonth.length());
	         getmonth = getmonth.substring(0,getmonth.length() -6);
	        }
    
	     if (sortParam.equals("Nothing")) {
	      sql = "SELECT * FROM bmbillmast WHERE " + getmonth + " = 'Y'";
	       }else if (sortParam.equals("bmprem")) {
	        	 sql = "SELECT * FROM bmbillmast WHERE " + getmonth + " = 'Y' ORDER BY " + sortParam + " DESC";
	       }else if (sortParam.equals("bmname")) {
	      	 sql = "SELECT * FROM bmbillmast WHERE " + getmonth + " = 'Y' ORDER BY " + sortParam;
	       }else if (sortParam.equals("nfdues")) {
		      	 sql = "SELECT * FROM bmbillmast WHERE " + getmonth + " = 'Y' ORDER BY " + sortParam + " DESC";
		       }
	  
	     
	        
	        Cursor b = database.rawQuery(sql, null);
	       
	        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
	        return b;
	   
	    }
	 public void close() {
		 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
      database.close();
  } 	
	
	 private Cursor getDataag() {
			// TODO Auto-generated method stub
				DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
			     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
			     
			        String sql = "";
			        sql = "SELECT agname,tel FROM agentdata";
			      
			     
			        
			        Cursor agetails = database.rawQuery(sql, null);
			        agetails.moveToFirst();
			        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
			        return agetails;
			 
			 
			 
			
		}
		public Cursor getDatabs(long id) {
		    	DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
		     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
		     
		        String sql = "";
		        sql = "SELECT * FROM bmbillmast WHERE _id = " + id;
		      
		     
		        
		        Cursor ddetails = database.rawQuery(sql, null);
		        ddetails.moveToFirst();
		        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
		        return ddetails;
		   
		    }
	 

	 
	 
}


