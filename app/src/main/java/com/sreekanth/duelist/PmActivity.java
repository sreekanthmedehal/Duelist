package com.sreekanth.duelist;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PmActivity extends Activity {
	private MySimpleCursorAdapter DBadapter;
	private String TAG;
	
	String[] actions = new String[] {
		        "All Policies",
		         "Due This Month",
		        "Lapse",
		        "paid-up",
		        "Surren.",
		        "Death",
		        "Forecl.",
		        "sgl.",
		        "Mat.", 
		        "Tr.Out",
		        "SSS",
		        "Monthly","Yly","Hly","Qly"
		    };
	int latefee;
	String Currdate;
	String smsbody;
	String StartDay;
	String Fupdate;
	ListView list;
	ImageButton actionBarAdd;
	ImageButton actionBarFilter;
    ImageButton actionBarEdit;
    ImageButton actionBarDelete;
    Cursor cursor;
    @Override
    public void onCreate(Bundle savedInstanceState) {  
    	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    	  String name = preferences.getString("Theme","");
    	  if(!name.equalsIgnoreCase(""))
    	  {
    	    Utils.THEME=name;
            Utils.settingChanged=true;
    	  }else {
    		  Utils.THEME="Light";
              Utils.settingChanged=true;
    	  }
    	 
    	Utils.setThemeToActivity(this);
    	  super.onCreate(savedInstanceState);  
    	  
    	  ActionBar bar = getActionBar();
    	 // bar.setDisplayHomeAsUpEnabled(true);
    	    bar.setDisplayShowHomeEnabled(false);
    	    bar.setIcon(R.color.transparent);
    	    bar.setDisplayShowTitleEnabled(false);
		//	bar.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#178ab4")));
			bar.setCustomView(R.layout.actionbar_top); //load your layout
	  		bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_CUSTOM); //show it 
	  	//	bar.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#207584")));
	  		bar.setDisplayHomeAsUpEnabled(true);
	   //	bar.setDisplayShowTitleEnabled(false);
	  		actionBarFilter = (ImageButton) findViewById(R.id.action_filter);
	  		actionBarFilter.setVisibility(View.INVISIBLE);
	  	    actionBarFilter.setOnClickListener(onClickListener);
	  	    actionBarEdit = (ImageButton)findViewById(R.id.action_edit);
	  	    actionBarEdit.setOnClickListener(onClickListener);
	  	    actionBarDelete = (ImageButton) findViewById(R.id.action_delete);
	  	    actionBarDelete.setOnClickListener(onClickListener);
	  	    actionBarAdd = (ImageButton) findViewById(R.id.action_add);
	  	    actionBarAdd.setOnClickListener(onClickListener);
    	  
    	  
    	  setContentView(R.layout.activity_main); 
    	  // 	  handleIntent(getIntent());
 
   //	  handleIntent(getIntent());
    	  // if you use siplecursoradapter then you should have _id as one of column name and its values should be integer in your db.  
    	  // so "_id", "columnName1", "columnName2" are column names from your db.  
    	  String[] from = new String[] { "_id", "policyno", "polname", "prem", "fup","mode"};  
    	  int[] to = new int[] { R.id.TextView1, R.id.TextView2, R.id.TextView3, R.id.TextView4, R.id.TextView5,R.id.TextView6 };  
    	  	  
    	   Cursor c = getData();  

		   DBadapter = new MySimpleCursorAdapter(  
    	   this, R.layout.list1, c, from, to, 0);  
    	   list = (ListView) findViewById(R.id.ListView1);  
    	   list.setAdapter(DBadapter);  
    	   registerForContextMenu(list);
    	   
    	   list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> list,
					android.view.View arg1, int position, long id) {
				
				// TODO Auto-generated method stub
				   // Get the cursor, positioned to the corresponding row in the result set
				  cursor = (Cursor) list.getItemAtPosition(position);	   		  
	                 showAlertDialog();
	    			//	startActivity(intent);   
	    		 	}}	); 
	     
    	  
    	   EditText myFilter = (EditText) findViewById(R.id.myFilter);
    	   myFilter.addTextChangedListener(new TextWatcher() {
    	  
    	    public void afterTextChanged(Editable s) {
    	    }
    	    public void beforeTextChanged(CharSequence s, int start,
    	      int count, int after) {
    	    }
    	    public void onTextChanged(CharSequence s, int start,
    	      int before, int count) {
    	     DBadapter.getFilter().filter(s.toString());
    	    }
    	    });
    	    
    	   DBadapter.setFilterQueryProvider(new FilterQueryProvider() {
    	          public Cursor runQuery(CharSequence constraint) {
    	          return getDataf(constraint.toString());
    	          }
    	          });
    	   getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
     	  /** Create an array adapter to populate dropdownlist */
     	   ArrayAdapter<String> adapter = new ArrayAdapter<String>((getActionBar().getThemedContext()), android.R.layout.simple_spinner_dropdown_item, actions);

     	    /** Enabling dropdown list navigation for the action bar */
     	  

     	    /** Defining Navigation listener */
           ActionBar.OnNavigationListener navigationListener = new OnNavigationListener() {
     	   
 public boolean onNavigationItemSelected(int itemPosition, long itemId) {
 String dd = actions[itemPosition];
     	//            Toast.makeText(getBaseContext(), "You selected : " + actions[itemPosition]  , Toast.LENGTH_SHORT).show();
     	            DBadapter.getFilter().filter(dd.toString());
     	            return false;
     	           }
     	           };

     	    /** Setting dropdown items and item navigation listener for the actionbar */
     	    getActionBar().setListNavigationCallbacks(adapter, navigationListener);
    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	   // Inflate the menu; this adds items to the action bar if it is present.
	   getMenuInflater().inflate(R.menu.pmactivity_menu, menu);
	   return super.onCreateOptionsMenu(menu);

	}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        // Respond to the action bar's Up/Home button

            case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
            
            case R.id.action_exit:
            finish();          
            moveTaskToBack(true);
            return true;

	        case R.id.action_refresh:
//	        String[] from = new String[] { "_id", "policyno", "polname", "prem", "fup"};  
//	        int[] to = new int[] { R.id.TextView1, R.id.TextView2, R.id.TextView3, R.id.TextView4, R.id.TextView5 };  
	        Cursor c = getData();  
	        DBadapter.changeCursor(c);
//	        ListAdapter	  DBadapter2 = new SimpleCursorAdapter(  
//	        this, R.layout.list1, c, from, to, 0);  
//	        list.setAdapter(DBadapter2);  
//	        registerForContextMenu(list);
	        return true;
	            	            
	        case R.id.action_sa:
	        DBadapter.getFilter().filter("sortaz");
           	return true;
           	
	        case R.id.action_sn:
       	    DBadapter.getFilter().filter("sortp");
       	    return true;
       	    case R.id.action_expimp:
       	    	finish();
       	   Intent nextActivity = new Intent(this,cloud.class);
       	   nextActivity.putExtra("source", "abar");
       	   startActivity(nextActivity);
       	   //push from bottom to top
       	   overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);	
       	    
       	    	
       	    	
       	    return true;
            default: 
            return super.onOptionsItemSelected(item);
    } 
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }
 

    @Override
    public boolean onContextItemSelected(MenuItem item) {
    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    Cursor cursor = (Cursor) list.getItemAtPosition(info.position);
    final String Id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
   	String PolicyNo = cursor.getString(cursor.getColumnIndexOrThrow("policyno"));
    String smspolno =  cursor.getString(cursor.getColumnIndexOrThrow("policyno"));
    String smsfup = cursor.getString(cursor.getColumnIndexOrThrow("fup"));
    String smsprem = cursor.getString(cursor.getColumnIndexOrThrow("prem"));
    String smsagname = cursor.getString(cursor.getColumnIndexOrThrow("agcode"));
    String smsagtel = cursor.getString(cursor.getColumnIndexOrThrow("mobile"));
    String smspolname = cursor.getString(cursor.getColumnIndexOrThrow("polname"));
    smsbody = "Policy No : " + smspolno + "\n" + "PolName : " + smspolname + "\n" + "Due : " + smsfup + "\n" + "Instl Prem : " + smsprem
   		  + "\n" + "Please Pay Premiums at LIC Office" + "\n" + smsagname + "\n";
        switch (item.getItemId()) {
            case R.id.Edit_Task:
          //      editNote(info.id);
            	            	/* Toast.makeText(getApplicationContext(),
	    				         "Pol No: " + PolicyNo + "\n",
	                              Toast.LENGTH_LONG).show();*/
            	            	 Intent editintent = new Intent(PmActivity.this,AboutEdit.class);
            	            	 editintent.putExtra("Epolno", PolicyNo);
            	            	 startActivity(editintent);
            	            	 
            return true;
            case R.id.details_Task:
                  	            	 /*Toast.makeText(getApplicationContext(),
      	    				         "Pol No: " + PolicyNo + "\n",
      	                              Toast.LENGTH_LONG).show();*/
                  	            	 Intent smsintent = new Intent(PmActivity.this,SMS.class);
                	            	 smsintent.putExtra("smsmobno", smsagtel);
                	            	 smsintent.putExtra("smsbody",smsbody);
                	            	 startActivity(smsintent);        
                  	            	/* try {
               					     Intent sendIntent = new Intent(Intent.ACTION_VIEW);
               					     sendIntent.putExtra("address",smsagtel);
               					     sendIntent.putExtra("sms_body", smsbody); 
               					     sendIntent.setType("vnd.android-dir/mms-sms");
               					     startActivity(sendIntent);
               					     } catch (Exception e) {
               						Toast.makeText(getApplicationContext(),
               						"SMS failed, please try again later!",
               						Toast.LENGTH_LONG).show();
               						e.printStackTrace();
               				 	    }*/
                  return true;
            case R.id.Delete_Task:
            	
            	/* Toast.makeText(getApplicationContext(),
	    				   "Pol No: " + PolicyNo + "\n",
	                       Toast.LENGTH_LONG).show();*/
            	 new AlertDialog.Builder(PmActivity.this)
 	            .setIcon(R.drawable.icon)
 	            .setTitle(PolicyNo)
 	            .setMessage("Want To Delete...!")
 	            .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int whichButton) {
            	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(PmActivity.this);
        	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
            	 database.delete(bmpolmast.TABLE_STOCK,"_id =" + Id,null);
            	 database.close();
            	 stockSQLHelper.close();
//            	 String[] from = new String[] { "_id", "policyno", "polname", "prem", "fup"};  
//              	 int[] to = new int[] { R.id.TextView1, R.id.TextView2, R.id.TextView3, R.id.TextView4, R.id.TextView5 };  
              	 Cursor c = getData();  
//              	 ListAdapter	  DBadapter3= new SimpleCursorAdapter(  
//              	    	    PmActivity.this, R.layout.list1, c, from, to, 0);  
//              	    	   list.setAdapter(DBadapter3);  
//              	    	   registerForContextMenu(list);   
              	 DBadapter.changeCursor(c);
                        } })
	          
	            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                            /* User clicked Cancel */
	                    	dialog.cancel();
	                    }
	                    })
	            .show();
            return true;
                
            case R.id.Sms_Task:
                //       deleteNote(info.id);
           	 
           	 Intent revintent = new Intent(PmActivity.this,RevActivity.class);
           	 revintent.putExtra("revpolno", PolicyNo);
           	 
           	 close();           	
     //      	 if (IsPortalPasswordSaved()){
           	 startActivity(revintent);//} //else {
          // 	 Toast.makeText(getApplicationContext(),
 			//	   "Portal Password not saved",
            //       Toast.LENGTH_LONG).show();}
            return true;
            
            case R.id.Surr_Task:
                //       deleteNote(info.id);
          /* 	 Intent surintent = new Intent(PmActivity.this,PortalSurr.class);
           	 surintent.putExtra("PolicyNo", PolicyNo);
        	 if (IsPortalPasswordSaved()){
           	 startActivity(surintent);} else {
        	 Toast.makeText(getApplicationContext(),
  				   "Portal Password not saved",
                   Toast.LENGTH_LONG).show();}*/
            	AlertDialog.Builder builder1 = new AlertDialog.Builder(PmActivity.this);
	            builder1.setMessage("Coming Soon ....");
	            builder1.setCancelable(true);
	            builder1.setPositiveButton("Continue ...",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });
	            AlertDialog alert11 = builder1.create();
	            alert11.show();	     	
            	
            	
            	
            return true;
            default:
            return super.onContextItemSelected(item);
        }
    }
    public Cursor getData() {
		DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	    SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
        Cursor c = database.rawQuery("SELECT * FROM bmpolmast", null);
        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.
        return c;
    }

    public Cursor getDataf(String xxxx) {
    	DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	    SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
    	
		
		Log.w(TAG, xxxx);
    	  Cursor mCursor = null;
    	  if (xxxx.contains("This Month")) {
    		
    		  String intMonth = (String) android.text.format.DateFormat.format("MM", new Date());
    	//	  Toast.makeText(getBaseContext(), "You selected : " + intMonth  , Toast.LENGTH_SHORT).show();	  
    		  
    		  
          	mCursor = database.rawQuery("SELECT * FROM bmpolmast where substr(doc,6,2) = '" + intMonth +"'", null);
          } else if
         (xxxx.equals("All Policies")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast", null);
        } else if (xxxx.equals("Lapse")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE status LIKE '%" + xxxx + "%'", null);
        } else if (xxxx.equals("Lapse")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE status LIKE '%" + xxxx + "%'", null);	
        } else if (xxxx.equals("paid-up")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE status LIKE '%" + xxxx + "%'", null);	
        } else if (xxxx.equals("Surren.")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE status LIKE '%" + xxxx + "%'", null);	
        } else if (xxxx.equals("Death")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE status LIKE '%" + xxxx + "%'", null);	
        } else if (xxxx.equals("Forecl.")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE status LIKE '%"  + xxxx + "%'", null);
        } else if (xxxx.equals("Mat.")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE status LIKE '%"  + xxxx + "%'", null);
        } else if (xxxx.equals("sgl.")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE status LIKE '%"  + xxxx + "%'", null);
        } else if (xxxx.equals("Tr.Out")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE status LIKE '%"  + xxxx + "%'", null);	
        } else if (xxxx.equals("Monthly")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE mode =  'M' " , null);
        } else if (xxxx.equals("Yly")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE mode =  'Y' " , null);	
        } else if (xxxx.equals("Hly")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE mode =  'H' " , null);	
        } else if (xxxx.equals("Qly")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE mode =  'Q' " , null);	
        	
        } else if (xxxx.equals("SSS")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE type =  'S'", null);	
        } else if (xxxx.equals("sortaz")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast ORDER BY polname", null);	
       
        } else if (xxxx.equals("sortp")) {
        	mCursor = database.rawQuery("SELECT * FROM bmpolmast ORDER BY prem DESC", null);	
        } else  {
        	 mCursor = database.rawQuery("SELECT * FROM bmpolmast WHERE polname LIKE '%" + xxxx + "%'", null);
        }
        ;
        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.
        return mCursor;
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
	        sql = "SELECT * FROM bmpolmast WHERE _id = " + id;
	        Cursor ddetails = database.rawQuery(sql, null);
	        ddetails.moveToFirst();
	        // Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
	        return ddetails;
	    }
 
 
    public void close() {
		 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
         database.close();
  } 	
    
  
    
    public boolean  IsPortalPasswordSaved()
    {
    	DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
	    SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
	    
	    boolean yesno = false;
	    String sql = "";
		sql = "SELECT * from registration";
		Cursor c = database.rawQuery(sql, null);
		c.moveToFirst();
		 database.close();
		if ((c.getCount()>0)){
			     String      REG_ACTIVATED = c.getString(c.getColumnIndexOrThrow("regactivated"));
			if ((REG_ACTIVATED.equals("Y"))){
			yesno=true;
			}else
			{
		    yesno=false;
			}
            } else {
    	    yesno=false;
            }
		return yesno;
        }
    private OnClickListener onClickListener = new OnClickListener() {
	    @Override
	    public void onClick(final View v) {
	        switch(v.getId()){
	        case R.id.action_add:
	        Intent addintent = new Intent(PmActivity.this,AboutAdd.class);
        	startActivity(addintent);
        	break;
	            case R.id.action_filter:
	                 //DO something   	
	            break;
	            
	            case R.id.action_edit:
	                 //DO something
	            	 String text1 = "Long Press the List Item for Edit Option";
	            	 AlertDialog.Builder alert = new AlertDialog.Builder(PmActivity.this);
	            	 alert.setTitle("Really want to edit?");
	             	 alert.setMessage(text1);
	            	 // Set an EditText view to get user input 
	            	 alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            	  public void onClick(DialogInterface dialog, int whichButton) {
	            		    // Canceled.
	            		  dialog.cancel();
	            		  }
	            		  });
	            	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	            	public void onClick(DialogInterface dialog, int whichButton) {
	            					  // Do something with value!
	            						 dialog.cancel();		
	            	   }
	            	   });
	            	alert.show();
	            break;
	            
	            case R.id.action_delete:
	                 //DO something
	   //         	 TextView textFragment1 = (TextView)findViewById(R.id.item_detail);swits          	
	             	 String text2 = "Long Press the List Item for Delete Option";
	            	 AlertDialog.Builder alert1 = new AlertDialog.Builder(PmActivity.this);
	            	 alert1.setTitle("Really want to delete?");
	             	 alert1.setMessage(text2);
	            	 // Set an EditText view to get user input 
	            	 alert1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            	  public void onClick(DialogInterface dialog, int whichButton) {
	            		    // Canceled.
	            		  dialog.cancel();
	            		  }
	            		  });
	            	alert1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	            	public void onClick(DialogInterface dialog, int whichButton) {
	            					  // Do something with value!
	            						 dialog.cancel();		
	            	   }
	            	   });
	            	alert1.show();
	            	break;			
	            		}	
	                   }
	                   };
	                   @Override
	                   protected void onResume() {
	                       super.onResume();
	                       // do what you need to do if your activity resumes
	                /*       Cursor c = getData();  
	           	        DBadapter.changeCursor(c);*/

	                   }
	                   private void showAlertDialog() {
	                       // Prepare grid view
	                	   final String Id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
	                	   final String Add1 = 
          	    				   cursor.getString(cursor.getColumnIndexOrThrow("add1"));
          	   		       final String Add2 = 
          	    				   cursor.getString(cursor.getColumnIndexOrThrow("add2"));
          	    		   final String Add3 = 
          	    				   cursor.getString(cursor.getColumnIndexOrThrow("add3"));
          	    		   final String Units = cursor.getString(cursor.getColumnIndexOrThrow("units"));
          	    		   final String AgCode = cursor.getString(cursor.getColumnIndexOrThrow("agcode"));
          	   // 		   final String DoCode = cursor.getString(cursor.getColumnIndexOrThrow("docode"));
          	    		   final String Name = cursor.getString(cursor.getColumnIndexOrThrow("polname"));
          	    		   final String Status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
          	    		   final String DOC = cursor.getString(cursor.getColumnIndexOrThrow("doc"));
          	    		   final String Mode = cursor.getString(cursor.getColumnIndexOrThrow("mode"));
          	    		   final String SA = cursor.getString(cursor.getColumnIndexOrThrow("sa"));
          	    		   final String Prem = cursor.getString(cursor.getColumnIndexOrThrow("prem"));
          	    		   final String FUP = cursor.getString(cursor.getColumnIndexOrThrow("fup"));
          	    		   final String DOB = cursor.getString(cursor.getColumnIndexOrThrow("dob"));
          	    		   final String Pin = cursor.getString(cursor.getColumnIndexOrThrow("pin"));
          	    		   final  String PolicyNo = cursor.getString(cursor.getColumnIndexOrThrow("policyno"));
          	    		   final String PlanNo = cursor.getString(cursor.getColumnIndexOrThrow("plan"));
          	    		   final String TermNo = cursor.getString(cursor.getColumnIndexOrThrow("term"));
          	    		   final String Mobile = cursor.getString(cursor.getColumnIndexOrThrow("mobile"));
          	    		   final String Email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
          	    		   final String loandt = cursor.getString(cursor.getColumnIndexOrThrow("lndate"));
          	    		   final String loanamt =cursor.getString(cursor.getColumnIndexOrThrow("lnamt"));
          	    		   final String memo = cursor.getString(cursor.getColumnIndexOrThrow("memo"));
          	    		   
          	    		 String smspolno =  cursor.getString(cursor.getColumnIndexOrThrow("policyno"));
          	    	    String smsfup = cursor.getString(cursor.getColumnIndexOrThrow("fup"));
          	    	    String smsprem = cursor.getString(cursor.getColumnIndexOrThrow("prem"));
          	    	    String smsagname = cursor.getString(cursor.getColumnIndexOrThrow("agcode"));
          	    	    final String smsagtel = cursor.getString(cursor.getColumnIndexOrThrow("mobile"));
          	    	    String smspolname = cursor.getString(cursor.getColumnIndexOrThrow("polname"));
          	    	    smsbody = "Policy No : " + smspolno + "\n" + "PolName : " + smspolname + "\n" + "Due : " + smsfup + "\n" + "Instl Prem : " + smsprem
          	    	   		  + "\n" + "Please Pay Premiums at LIC Office" + "\n" + smsagname + "\n";
          	    		   
          	    		   
          	    		   
          	    		   
          	    		   
          	    		   
	                       GridView gridView = new GridView(this);

	                       List<Integer>  mList = new ArrayList<Integer>();
	                       for (int i = 1; i < 36; i++) {
	                           mList.add(i);
	                       }
	                   	final String[] MOBILE_OS = new String[] { 
	            			"Share", "Edit","Delete","Detailed View","Revival Calc","Surrender Calc","Enter Loan","Loan Int Calc","Update FUP","Save Mobile No","Insert Memo"};

	//                       gridView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mList));

	                    gridView.setAdapter(new ImageAdapter2(this, MOBILE_OS));
	                	                      gridView.setNumColumns(2);
	                       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	                           @Override
	                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	                               // do something here
	                        		String gvpressed = 		 ((TextView) view.findViewById(R.id.grid_item_label)).getText().toString().trim();
	                        	/*	Toast.makeText(
	        					   getApplicationContext(),
	        					   gvpressed, Toast.LENGTH_SHORT).show();*/
	                        		if (gvpressed.equals("Revival Calc")) {
	                        		 	 Intent revintent = new Intent(PmActivity.this,RevActivity.class);
	                                   	 revintent.putExtra("revpolno", PolicyNo);
	                                   	 
	                                   	 close();           	
	                         
	                                   	 startActivity(revintent);
	           	    			}
	                        		if (gvpressed.equals("Insert Memo")) {
	                        			AlertDialog.Builder builder1 = new AlertDialog.Builder(PmActivity.this);
	                    	            builder1.setMessage("Coming Soon ....");
	                    	            builder1.setCancelable(true);
	                    	            builder1.setPositiveButton("Continue ...",
	                    	                    new DialogInterface.OnClickListener() {
	                    	                public void onClick(DialogInterface dialog, int id) {
	                    	                    dialog.cancel();
	                    	                }
	                    	            });
	                    	            AlertDialog alert11 = builder1.create();
	                    	            alert11.show();	     	
	           	    			}
	                        		if (gvpressed.equals("Update FUP")) {
	                        			AlertDialog.Builder builder1 = new AlertDialog.Builder(PmActivity.this);
	                    	            builder1.setMessage("Coming Soon ....");
	                    	            builder1.setCancelable(true);
	                    	            builder1.setPositiveButton("Continue ...",
	                    	                    new DialogInterface.OnClickListener() {
	                    	                public void onClick(DialogInterface dialog, int id) {
	                    	                    dialog.cancel();
	                    	                }
	                    	            });
	                    	            AlertDialog alert11 = builder1.create();
	                    	            alert11.show();	     	
	           	    			}
	                        		if (gvpressed.equals("Save Mobile No")) {
	                        			AlertDialog.Builder builder1 = new AlertDialog.Builder(PmActivity.this);
	                    	            builder1.setMessage("Coming Soon ....");
	                    	            builder1.setCancelable(true);
	                    	            builder1.setPositiveButton("Continue ...",
	                    	                    new DialogInterface.OnClickListener() {
	                    	                public void onClick(DialogInterface dialog, int id) {
	                    	                    dialog.cancel();
	                    	                }
	                    	            });
	                    	            AlertDialog alert11 = builder1.create();
	                    	            alert11.show();	     	
	           	    			}
	                        		if (gvpressed.equals("Loan Int Calc")) {
	                        			AlertDialog.Builder builder1 = new AlertDialog.Builder(PmActivity.this);
	                    	            builder1.setMessage("Coming Soon ....");
	                    	            builder1.setCancelable(true);
	                    	            builder1.setPositiveButton("Continue ...",
	                    	                    new DialogInterface.OnClickListener() {
	                    	                public void onClick(DialogInterface dialog, int id) {
	                    	                    dialog.cancel();
	                    	                }
	                    	            });
	                    	            AlertDialog alert11 = builder1.create();
	                    	            alert11.show();	     	
	           	    			}

	                        		if (gvpressed.equals("Enter Loan")) {
	                        			View view1 = View.inflate(PmActivity.this, R.layout.laon, null);
	                        			final Dialog dialog = new Dialog(PmActivity.this);
	                        			dialog.setContentView(view1);
	                        			dialog.setTitle("Enter Loan Amount...");
	                        			final SimpleDateFormat	dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
	                        			Button ChangeDate = (Button) dialog.findViewById(R.id.btnChangeDate);
	                        		final EditText	fromDateEtxt = (EditText) dialog.findViewById(R.id.tvDate);
	                        		fromDateEtxt.setText(loandt);
	                        		final EditText loanAmt = (EditText)dialog.findViewById(R.id.editText1);
	                        		loanAmt.setText(loanamt);
	                        	        fromDateEtxt.setInputType(InputType.TYPE_NULL);
	                        	        ChangeDate.setOnClickListener(new OnClickListener()
	                        	        {

											@Override
											public void onClick(View v) {
												// TODO Auto-generated method stub
												 Calendar newCalendar = Calendar.getInstance();
											DatePickerDialog        fromDatePickerDialog = new DatePickerDialog(PmActivity.this, new OnDateSetListener() {
											 
											            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
											                Calendar newDate = Calendar.getInstance();
											                newDate.set(year, monthOfYear, dayOfMonth);
											                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
											            }
											 
											        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
											fromDatePickerDialog.show();
											}
											
	                        	        });
	                    	           Button CloseButton = (Button)dialog.findViewById(R.id.button1);
	                    	           CloseButton.setOnClickListener(new OnClickListener(){

										@Override
										public void onClick(View arg0) {
											// TODO Auto-generated method stub
										dialog.dismiss();	
										}
	                    	        	   
	                    	        	   
	                    	           });
	                    	           Button savebtn =(Button)dialog.findViewById(R.id.button2);
	                    	           savebtn.setOnClickListener(new OnClickListener(){

										@Override
										public void onClick(View arg0) {
											// TODO Auto-generated method stub
											
		                             	     
		      if(loanAmt.getText().toString().length()==0){
		    	  loanAmt.setError("loanAmount required");
		      }else 
		      {
		    	  DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(PmActivity.this);
          	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();
          	   //                   	 database.update(bmpolmast.TABLE_STOCK,"_id =" + Id,null,null);
          	   database.close();
           	 stockSQLHelper.close();
		      }
		                             	     
		                             	     
		                             	     
		                             	     
		                             	     
		                             	     
		                             	     
		                             	     
		             
		                                 											
										}
	                    	        	   
	                    	           });
	                    	           dialog.show();
	           	    			}	
	                        		
	                        		
	                        		
	                        		
	                        		if (gvpressed.equals("Surrender Calc")) {
	                        			AlertDialog.Builder builder1 = new AlertDialog.Builder(PmActivity.this);
	                    	            builder1.setMessage("Coming Soon ....");
	                    	            builder1.setCancelable(true);
	                    	            builder1.setPositiveButton("Continue ...",
	                    	                    new DialogInterface.OnClickListener() {
	                    	                public void onClick(DialogInterface dialog, int id) {
	                    	                    dialog.cancel();
	                    	                }
	                    	            });
	                    	            AlertDialog alert11 = builder1.create();
	                    	            alert11.show();	     	
	           	    			}
	                        		if (gvpressed.equals("Share")) {
	                        			 Intent smsintent = new Intent(PmActivity.this,SMS.class);
	                	            	 smsintent.putExtra("smsmobno", smsagtel);
	                	            	 smsintent.putExtra("smsbody",smsbody);
	                	            	 startActivity(smsintent); 
	           	    			}
	                        		if (gvpressed.equals("Edit")) {
	                        			 Intent editintent = new Intent(PmActivity.this,AboutEdit.class);
	                	            	 editintent.putExtra("Epolno", PolicyNo);
	                	            	 startActivity(editintent);
	        	           	    			}
	                        		if (gvpressed.equals("Delete")) {
	                        			 new AlertDialog.Builder(PmActivity.this)
	                      	            .setIcon(R.drawable.icon)
	                      	            .setTitle(PolicyNo)
	                      	            .setMessage("Want To Delete...!")
	                      	            .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
	                                      public void onClick(DialogInterface dialog, int whichButton) {
	                                 	 DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(PmActivity.this);
	                             	     SQLiteDatabase database = stockSQLHelper.getReadableDatabase();  
	                                 	 database.delete(bmpolmast.TABLE_STOCK,"_id =" + Id,null);
	                                 	 database.close();
	                                 	 stockSQLHelper.close();
  
	                                   	 Cursor c = getData();  

	                                   	 DBadapter.changeCursor(c);
	                                             } })
	                     	          
	                     	            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	                     	                    public void onClick(DialogInterface dialog, int whichButton) {
	                     	                            /* User clicked Cancel */
	                     	                    	dialog.cancel();
	                     	                    }
	                     	                    })
	                     	            .show();
	        	           	    			}	
	                        		if (gvpressed.equals("Detailed View")) {

	                  	    		  /* Toast.makeText(getApplicationContext(),
	                  	    				   "PolicyNo: " + PolicyNo + "\n",
	                  	                               Toast.LENGTH_LONG).show();*/
	                  	                 Intent intent = new Intent(getApplicationContext(), pmdetail.class);
	                  	                 intent.putExtra("PolNo",PolicyNo);
	                  	                 intent.putExtra("PolName",Name);
	                  	                 intent.putExtra("PolFup",FUP);
	                  	                 intent.putExtra("PolMode",Mode);
	                  	                 intent.putExtra("PolDoc",DOC);
	                  	                 intent.putExtra("PolStatus",Status);
	                  	                 intent.putExtra("PolUnits",Units);
	                  	                 intent.putExtra("PolPlan",PlanNo);
	                  	                 intent.putExtra("PolTerm",TermNo);
	                  	                 intent.putExtra("PolPrem",Prem);
	                  	                 intent.putExtra("PolAgcode",AgCode);
	                  	                 intent.putExtra("PolSa",SA);
	                  	                 intent.putExtra("PolDob",DOB);
	                  	                 intent.putExtra("PolAdd1",Add1);
	                  	                 intent.putExtra("PolAdd2",Add2);
	                  	                 intent.putExtra("PolAdd3",Add3);
	                  	                 intent.putExtra("PolPin",Pin);
	                  	                 intent.putExtra("PolMobile", Mobile);
	                  	                 intent.putExtra("PolEmail", Email);
	            	            		 startActivity (intent);
	        	           	    			}                    		
	                        		
	                        		
	                           }
	                       });

	                       // Set grid view to alertDialog
	                       AlertDialog.Builder builder = new AlertDialog.Builder(this);
	                      builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
	                           public void onClick(DialogInterface dialog, int id) {
	                       //         PmActivity.this.finish();
	                        	   dialog.dismiss();
	                           }
	                       });
	                       builder.setView(gridView);
	                       builder.setTitle("Actions");
	                       builder.show();
	                   }                 
    }
